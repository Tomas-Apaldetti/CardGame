package com.core.g3.Match;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Card.Card;
import com.core.g3.Card.CardName;
import com.core.g3.Card.Type.Creature.Attribute;
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
import com.core.g3.Match.TurnManager.TurnManager;
import com.core.g3.Match.Zone.ActiveZoneType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Match {
    private Player bluePlayer;
    private Player greenPlayer;
    private GameMode gameMode;
    private IPhase phase;
    private TurnManager turnManager;

    public Match(Player bluePlayer, Player greenPlayer, GameMode gameMode) {
        this.bluePlayer = bluePlayer;
        this.greenPlayer = greenPlayer;
        this.gameMode = gameMode;
        this.phase = new NotPlayable();
        this.turnManager = new TurnManager(this.bluePlayer, this.greenPlayer);
    }

    public void startMatch(PlayerZone firstTurn) {
        this.turnManager.setSide(firstTurn);
        this.gameMode.drawInitialCards(bluePlayer);
        this.gameMode.drawInitialCards(greenPlayer);
        this.phase = new InitialPhase(this.turnManager.getPlayer(), this.turnManager.getRival());
    }

    public void skipToPhase(PlayerZone playerSide, PhaseType phase) {
        this.turnManager.setSide(playerSide);
        this.phase = PhaseFactory.createNewPhase(phase, this.turnManager.getPlayer(), this.turnManager.getRival());
    }

    public Player getCurrentPlayerTurn() {
        return this.turnManager.getPlayer();
    }

    public void forceDeckOrder(PlayerZone side, List<CardName> cards) {
        this.turnManager.getPlayerFrom(side).forceDeckOrder(cards);
    }

    public ICard summon(PlayerZone side, CardName cardName, ActiveZoneType zone) {
        this.assertCurrentPlayer(side);
        Player player = this.turnManager.getPlayer();
        ICard cardToPlay = player.getCardByCardName(cardName);
        this.phase = this.phase.summon(cardToPlay, zone);
        return cardToPlay;
    }

    public List<CardInGame> getCardsInGame(List<ICard> cards) {
        List<CardInGame> cigs = new ArrayList<>();
        cigs.addAll(this.turnManager.getPlayer().getCardsInGame(cards));
        cigs.addAll(this.turnManager.getRival().getCardsInGame(cards));
        return cigs;
    }

    public void activateAction(PlayerZone side, CardName cardName, int index, Optional<PlayerZone> targetPlayer,
            List<ICard> targetCards) {
        this.assertCurrentPlayer(side);
        Player player = this.turnManager.getPlayer();
        ICard cardToPlay = player.getCardByCardName(cardName);

        if (targetPlayer.isPresent()) {
            this.phase = this.phase.useAction(cardToPlay, this.turnManager.getPlayerFrom(targetPlayer.get()));
            return;
        }

        List<CardInGame> cigs = this.getCardsInGame(targetCards);

        this.phase = this.phase.useAction(cardToPlay, cigs);
    }

    public void activateArtifact(ICard artifact, int index, Optional<PlayerZone> toOptionalPlayerZone,
            List<ICard> targets) {
    }

    public int getCreatureHitpoints(ICard card) {
        return card.getCreatureHP();
    }

    public void attackCreature(ICard creature, int index, ICard target) {
        this.assertCurrentPlayer();
        CardInGame playerCIG = this.turnManager.getPlayer().getCardInGame(creature);
        CardInGame targetCIG = this.turnManager.getRival().getCardInGame(target);
        this.phase.attack(playerCIG, new Amount(index), targetCIG);
    }

    public void attackPlayer(ICard creature, int index) {
        this.assertCurrentPlayer();
        CardInGame playerCIG = this.turnManager.getPlayer().getCardInGame(creature);
        this.phase.attack(playerCIG, new Amount(index), this.turnManager.getRival());
    }

    public int playerHealth(PlayerZone side) {
        return this.turnManager.getPlayerFrom(side).matchEndConditionPoints();
    }

    public IResource playerEnergy(PlayerZone side, EnergyType energyType) {
        return this.turnManager.getPlayerFrom(side).getEnergy(energyType);
    }

    public Optional<PlayerZone> getWinner() {
        Optional<Player> winner = this.gameMode.getWinner(this.bluePlayer, this.greenPlayer);

        if (winner.isPresent()) {
            return Optional.of(winner.get().getZone());
        } else {
            return Optional.empty();
        }
    }

    private void assertCurrentPlayer() {
        if (!this.turnManager.getPlayer().equals(this.phase.activePlayer())) {
            throw new CurrentPhaseDoesNotBelongToUserException();
        }
    }

    private void assertCurrentPlayer(PlayerZone zone) {
        if (!this.turnManager.getPlayerFrom(zone).equals(this.phase.activePlayer())) {
            throw new CurrentPhaseDoesNotBelongToUserException();
        }
    }

    public Player getPlayer(PlayerZone playerZone) {
        return this.turnManager.getPlayerFrom(playerZone);
    }
}
