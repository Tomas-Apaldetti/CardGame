package com.core.g3.Match;

import com.core.g3.Card.CardName;
import com.core.g3.Card.Cost.Exception.CanNotPayException;
import com.core.g3.Card.Type.Exceptions.CardTypeNoSummonableInZoneException;
import com.core.g3.Commons.Amount;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.GameMode.GameMode;
import com.core.g3.Match.Phase.*;
import com.core.g3.Match.Phase.Exceptions.CurrentPhaseDoesNotBelongToUserException;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Player.PlayerZone;
import com.core.g3.Match.Player.Resources.EnergyType;
import com.core.g3.Match.Player.Resources.IResource;
import com.core.g3.Match.ResolutionStack.LingeringEffect.ILingeringEffect;
import com.core.g3.Match.Zone.ActiveZone;
import com.core.g3.Match.Zone.ActiveZoneType;
import com.core.g3.Match.Zone.Exceptions.CardLimitReachedException;
import com.core.g3.Match.Zone.Exceptions.InvalidMovementException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Match {
    private final Player bluePlayer;
    private final Player greenPlayer;
    private final GameMode gameMode;
    private IPhase phase;
    private final List<ILingeringEffect> lingeringEffects;
    private Optional<Player> winner;

    public Match(Player bluePlayer, Player greenPlayer, GameMode gameMode) {
        this.bluePlayer = bluePlayer;
        this.greenPlayer = greenPlayer;
        this.gameMode = gameMode;
        this.gameMode.setMatch(this);
        this.phase = new NotPlayable();
        this.lingeringEffects = new ArrayList<>();
        this.winner = Optional.empty();
    }

    public PhaseType getPhaseType() {
        return this.phase.getPhaseType();
    }

    public void startMatch(PlayerZone firstTurn) {
        Player first = this.getPlayer(firstTurn);
        Player rival = this.getRival(first);
        this.gameMode.drawInitialCards(bluePlayer);
        this.gameMode.drawInitialCards(greenPlayer);
        this.phase = new InitialPhase(first, rival, this);
    }

    public void skipToPhase(PlayerZone playerSide, PhaseType phase) {
        Player desired = this.getPlayer(playerSide);
        this.phase = this.phase.next();
        this.phase.initialEffects();
        while (!this.phase.coincide(desired, phase)) {
            this.phase = this.phase.next();
            this.phase.initialEffects();
        }
    }

    public void forceDeckOrder(PlayerZone side, List<CardName> cards) {
        this.getPlayer(side).forceDeckOrder(cards);
    }

    public ICard summon(
            PlayerZone side,
            CardName cardName,
            ActiveZoneType zone) {
        this.assertCurrentPlayer(side);
        Player player = this.getPlayer(side);
        ICard cardToPlay = player.getCardByCardName(cardName);
        this.phase = this.phase.summon(cardToPlay, zone);
        return cardToPlay;
    }

    public List<CardInGame> getCardsInGame(List<ICard> cards) {
        List<CardInGame> cigs = new ArrayList<>();
        cigs.addAll(this.currentActivePlayer().getCardsInGame(cards));
        cigs.addAll(this.getRival(this.currentActivePlayer()).getCardsInGame(cards));
        return cigs;
    }

    public PlayerZone currentActivePlayerZone() {
        Player current = this.phase.activePlayer();
        return current.equals(this.bluePlayer) ? PlayerZone.Blue : PlayerZone.Green;
    }

    public void activateAction(
            PlayerZone side,
            CardName cardName,
            int index,
            Optional<PlayerZone> targetPlayer,
            List<ICard> targetCards) {
        this.assertCurrentPlayer(side);
        Player player = this.currentActivePlayer();
        ICard cardToPlay = player.getCardByCardName(cardName);

        if (targetPlayer.isPresent()) {
            this.phase = this.phase.useAction(cardToPlay, this.getPlayer(targetPlayer.get()));
            return;
        }

        List<CardInGame> cigs = this.getCardsInGame(targetCards);

        this.phase = this.phase.useAction(cardToPlay, cigs);
    }

    public void attackPlayer(CardName cardName, int index) {
        ActiveZone combatZone = this.currentActivePlayer().getZone(ActiveZoneType.Combat);
        List<CardInGame> creatures = combatZone.getCardsInGameByCardName(cardName);
        CardInGame firstCreature = creatures.get(0);
        this.phase = this.phase.attack(
                firstCreature,
                new Amount(index),
                this.getRival(this.currentActivePlayer()));
    }

    public void activateArtifact(CardName cardName, int index, Optional<PlayerZone> toOptionalPlayerZone,
            List<ICard> targets) {
        ActiveZone artifactZone = this.currentActivePlayer().getZone(ActiveZoneType.Artifacts);
        List<CardInGame> artifacts = artifactZone.getCardsInGameByCardName(cardName);
        CardInGame cig = artifacts.get(0);

        if (cig == null) {
            throw new RuntimeException();
        }

        if (toOptionalPlayerZone.isPresent()) {
            this.phase = this.phase.useArtifact(cig, this.getPlayer(toOptionalPlayerZone.get()));
            return;
        }

        List<CardInGame> cigs = this.getCardsInGame(targets);

        this.phase = this.phase.useArtifact(cig, cigs);

    }

    public void activateArtifact(
            ICard artifact,
            int index,
            Optional<PlayerZone> toOptionalPlayerZone,
            List<ICard> targets) {
        Player player = this.currentActivePlayer();
        CardInGame cig = player.getCardInGame(artifact);

        if (cig == null) {
            throw new RuntimeException();
        }

        if (toOptionalPlayerZone.isPresent()) {
            this.phase = this.phase.useArtifact(cig, this.getPlayer(toOptionalPlayerZone.get()));
            return;
        }

        List<CardInGame> cigs = this.getCardsInGame(targets);

        this.phase = this.phase.useArtifact(cig, cigs);
    }

    public void skipReaction() {
        this.phase = this.phase.skipReaction();
    }

    public void activateReactionFromHand(
            PlayerZone side,
            CardName cardName,
            Optional<PlayerZone> targetPlayer,
            List<ICard> targetCards) {
        this.assertCurrentPlayer(side);
        Player player = this.currentActivePlayer();
        ICard cardToPlay = player.getCardByCardName(cardName);

        if (targetPlayer.isPresent()) {
            this.phase = this.phase.useReaction(cardToPlay, this.getPlayer(targetPlayer.get()));
            return;
        }

        List<CardInGame> cigs = this.getCardsInGame(targetCards);

        this.phase = this.phase.useReaction(cardToPlay, cigs);
    }

    public void activateReactionFromZone(
            ICard card,
            Optional<PlayerZone> targetPlayer,
            List<ICard> targetCards) {
        Player player = this.currentActivePlayer();
        CardInGame cig = player.getCardInGame(card);

        if (cig == null) {
            throw new RuntimeException();
        }

        if (targetPlayer.isPresent()) {
            this.phase = this.phase.useReaction(cig, this.getPlayer(targetPlayer.get()));
            return;
        }

        List<CardInGame> cigs = this.getCardsInGame(targetCards);

        this.phase = this.phase.useReaction(cig, cigs);
    }

    public int getCreatureHitpoints(ICard card) {
        return card.getCreatureHP();
    }

    public void attackCreature(
            ICard creature,
            int index,
            ICard target) {
        CardInGame playerCIG = this.currentActivePlayer().getCardInGame(creature);
        CardInGame targetCIG = this.getRival(this.currentActivePlayer()).getCardInGame(target);
        this.phase = this.phase.attack(
                playerCIG,
                new Amount(index),
                targetCIG);
    }

    public void attackPlayer(ICard creature, int index) {
        CardInGame playerCIG = this.currentActivePlayer().getCardInGame(creature);
        this.phase = this.phase.attack(
                playerCIG,
                new Amount(index),
                this.getRival(this.currentActivePlayer()));
    }

    public void moveCreature(ICard creature, ActiveZoneType from, ActiveZoneType to) {
        this.assertValidCreatureMovement(from, to);

        Player currentPlayer = this.currentActivePlayer();
        CardInGame card = currentPlayer.getCardInGame(creature);
        ActiveZone original = currentPlayer.getZone(from);
        ActiveZone newZone = currentPlayer.getZone(to);

        newZone.moveToHere(card);

        original.remove(card);
    }

    private void assertValidCreatureMovement(ActiveZoneType from, ActiveZoneType to) {
        boolean combatToReserve = ActiveZoneType.Combat == from && ActiveZoneType.Reserve == to;
        boolean reserveToCombat = ActiveZoneType.Reserve == from && ActiveZoneType.Combat == to;
        if (combatToReserve || reserveToCombat) {
            throw new InvalidMovementException();
        }
    }

    public int playerHealth(PlayerZone side) {
        return this.getPlayer(side).matchEndConditionPoints();
    }

    public IResource playerEnergy(PlayerZone side, EnergyType energyType) {
        return this.getPlayer(side).getEnergy(energyType);
    }

    public Optional<PlayerZone> getWinner() {
        return winner.map(player -> player.equals(bluePlayer) ? PlayerZone.Blue : PlayerZone.Green);
    }

    private void assertCurrentPlayer(PlayerZone zone) {
        Player current = this.phase.activePlayer();
        Player desired = this.getPlayer(zone);
        if (!desired.equals(current)) {
            throw new CurrentPhaseDoesNotBelongToUserException();
        }
    }

    public Player currentActivePlayer() {
        return this.phase.activePlayer();
    }

    public Player getPlayer(PlayerZone playerZone) {
        return PlayerZone.Blue == playerZone ? this.bluePlayer : this.greenPlayer;
    }

    public Player getRival(Player current) {
        return this.bluePlayer.equals(current) ? this.greenPlayer : this.bluePlayer;
    }

    public void addLingering(List<ILingeringEffect> effects) {
        this.lingeringEffects.addAll(effects);
    }

    public Iterable<ILingeringEffect> getLingeringEffects() {
        return this.lingeringEffects;
    }

    public GameMode gamemode() {
        return this.gameMode;
    }

    public void setWinner(Player player) {
        this.winner = Optional.ofNullable(player);
    }
}
