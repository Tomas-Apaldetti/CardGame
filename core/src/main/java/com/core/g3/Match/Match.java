package com.core.g3.Match;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Card.Card;
import com.core.g3.Card.CardName;
import com.core.g3.Card.Type.Creature.Attribute;
import com.core.g3.Commons.Amount;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.GameMode.GameMode;
import com.core.g3.Match.Phase.IPhase;
import com.core.g3.Match.Phase.InitialPhase;
import com.core.g3.Match.Phase.PhaseFactory;
import com.core.g3.Match.Phase.PhaseType;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Player.PlayerZone;
import com.core.g3.Match.Player.Resources.EnergyType;
import com.core.g3.Match.Player.Resources.IResource;
import com.core.g3.Match.Zone.ActiveZoneType;

import java.util.List;
import java.util.Optional;

public class Match implements IMatch {
    private Player bluePlayer;
    private Player greenPlayer;
    private GameMode gameMode;
    private Player turn;
    private IPhase phase;

    public Match(Player bluePlayer, Player greenPlayer, GameMode gameMode) {
        this.bluePlayer = bluePlayer;
        this.greenPlayer = greenPlayer;
        this.gameMode = gameMode;
        this.turn = null;
        this.phase = null;
    }

    @Override
    public void startMatch(PlayerZone firstTurn) {
        this.gameMode.drawInitialCards(bluePlayer);
        this.gameMode.drawInitialCards(greenPlayer);
        this.turn = this.getPlayer(firstTurn);
        this.phase = new InitialPhase();
    }

    public void skipToPhase(PlayerZone player, PhaseType phase) {
        this.turn = this.getPlayer(player);
        this.phase = PhaseFactory.createNewPhase(phase);
    }

    public Player getCurrentPlayerTurn() {
        return this.turn;
    }

    @Override
    public void forceDeckOrder(PlayerZone side, List<CardName> cards) {
        filterPlayer(side).forceDeckOrder(cards);
    }

    @Override
    public ICard summon(PlayerZone side, CardName cardName, ActiveZoneType zone) {
        Player player = filterPlayer(side);
        ICard cardToPlay = player.getCardByCardName(cardName);
        return this.phase.summon(cardToPlay,zone, player);
    }

    @Override
    public void activateAction(PlayerZone playerZone, CardName cardName, int index, Optional<PlayerZone> targetPlayer, List<ICard> targetCards) {
        Player player = filterPlayer(playerZone);
        ICard cardToPlay = player.getCardByCardName(cardName);
        Player playerToReact = optionalFilterPlayer(targetPlayer);
        this.phase = this.phase.useAction(cardToPlay,player,index,playerToReact,targetCards);
    }

    @Override
    public void activateArtifact(ICard artifact, int index, Optional<PlayerZone> toOptionalPlayerZone, List<ICard> targets) {

    }

    @Override
    public int getCreatureHitpoints(ICard card) {
        return card.getCreatureHP();
    }

    @Override
    public void attackCreature(ICard creature, int index, ICard target) {
        Player rival = this.turn.equals(this.bluePlayer) ? greenPlayer : bluePlayer;
        CardInGame cig = this.turn.seeActiveZone(ActiveZoneType.Combat).getCardInGame(creature);
        IAttackable targetAttackable = rival.getAttackable(target);
        this.phase.attack(cig,new Amount(index),targetAttackable);
    }

    @Override
    public void attackPlayer(ICard creature, int index) {
        Player rival = this.turn.equals(this.bluePlayer) ? greenPlayer : bluePlayer;
        CardInGame cig = this.turn.seeActiveZone(ActiveZoneType.Combat).getCardInGame(creature);
        this.phase.attack(cig,new Amount(index),rival);
    }

    @Override
    public int playerHealth(PlayerZone side) {
        Player player = filterPlayer(side);
        return player.matchEndConditionPoints();
    }

    @Override
    public IResource playerEnergy(PlayerZone side, EnergyType energyType) {
        Player player = filterPlayer(side);
        return player.getEnergy(energyType);
    }

    @Override
    public Player getPlayer(PlayerZone side) {
        return filterPlayer(side);
    }

    private Player filterPlayer(PlayerZone side) {
        return side.equals(PlayerZone.Blue) ? bluePlayer : greenPlayer;
    }

    private Player optionalFilterPlayer(Optional<PlayerZone> side) {
        if(side.isPresent()) {
            return side.get().equals(PlayerZone.Blue) ? bluePlayer : greenPlayer;
        }
        return null;
    }

    private PlayerZone getPlayerSide(Player player) {
        return player.equals(bluePlayer) ? PlayerZone.Blue : PlayerZone.Green;
    }

    @Override
    public Optional<PlayerZone> getWinner() {
        Player bluePlayer = getPlayer(PlayerZone.Blue);
        Player greenPlayer = getPlayer(PlayerZone.Green);
        Optional<Player> winnerPlayer = this.gameMode.getWinner(greenPlayer, bluePlayer);

        if (winnerPlayer.isPresent()) {
            return Optional.of(this.getPlayerSide(winnerPlayer.get()));
        } else {
            return Optional.empty();
        }
    }

}
