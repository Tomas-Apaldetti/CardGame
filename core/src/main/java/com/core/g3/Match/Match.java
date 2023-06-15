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

    public void activateAction(PlayerZone side, CardName cardName, int index, Optional<PlayerZone> targetPlayer,
            List<ICard> targetCards) {
        this.assertCurrentPlayer(side);
        Player player = this.turnManager.getPlayer();
        ICard cardToPlay = player.getCardByCardName(cardName);

        if (targetPlayer.isPresent()) {
            this.phase = this.phase.useAction(cardToPlay, this.turnManager.getPlayerFrom(targetPlayer.get()));
            return;
        }

        this.phase = this.phase.useAction(cardToPlay, targetCards);
    }

    public void activateArtifact(ICard artifact, int index, Optional<PlayerZone> toOptionalPlayerZone,
            List<ICard> targets) {
    }

    public int getCreatureHitpoints(ICard card) {
        return card.getCreatureHP();
    }

    public void attackCreature(ICard creature, int index, ICard target) {
        // Player rival = this.turnManager.getRival();
        // CardInGame cig =
        // this.turn.seeActiveZone(ActiveZoneType.Combat).getCardInGame(creature);
        // IAttackable targetAttackable = rival.getAttackable(target);
        // this.phase.attack(cig, new Amount(index), targetAttackable);
    }

    public void attackPlayer(ICard creature, int index) {
        // Player rival = this.turnManager.getRival();
        // CardInGame cig =
        // this.turn.seeActiveZone(ActiveZoneType.Combat).getCardInGame(creature);
        // this.phase.attack(cig, new Amount(index), rival);
    }

    public int playerHealth(PlayerZone side) {
        // return this.turnManager.getPlayerFrom(side).matchEndConditionPoints();
        return 0;
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

    private void assertCurrentPlayer(PlayerZone zone) {
        if (!this.turnManager.getPlayer().equals(this.phase.activePlayer())) {
            throw new CurrentPhaseDoesNotBelongToUserException();
        }
    }

    public Player getPlayer(PlayerZone playerZone) {
        return this.turnManager.getPlayerFrom(playerZone);
    }
}
