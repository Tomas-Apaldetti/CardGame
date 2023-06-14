package com.core.g3.Match;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardName;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.GameMode.GameMode;
import com.core.g3.Match.Phase.Phase;
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
    private Phase phase;

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
        this.phase = Phase.Initial;
    }

    public void moveToNextPhase() {
        if (this.phase.equals(Phase.Initial)) {
            this.phase = Phase.Initial;
        } else if (this.phase.equals(Phase.Main)) {
            this.phase = Phase.Attack;
        } else if (this.phase.equals(Phase.Attack)) {
            this.phase = Phase.End;
        } else if (this.phase.equals(Phase.End)) {
            this.phase = Phase.Initial;
            this.moveToNextTurn();
        }
    }

    public void skipToPhase(PlayerZone player, Phase phase) {
        this.turn = this.getPlayer(player);
        this.phase = phase;
    }

    public void moveToNextTurn() {
        if (this.turn.equals(this.bluePlayer)) {
            this.turn = this.greenPlayer;
        } else {
            this.turn = this.bluePlayer;
        }
    }

    public Player getCurrentPlayerTurn() {
        return this.turn;
    }

    @Override
    public void forceDeckOrder(PlayerZone side, List<CardName> cards) {
        filterPlayer(side).forceDeckOrder(cards);
    }

    @Override
    public void summon(PlayerZone side, ICard card, ActiveZoneType zone) {
        Player player = filterPlayer(side);
        player.summonInZone(card, zone);
    }

    @Override
    public int getCreatureHitpoints(Card card) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCreatureHitpoints'");
    }

    @Override
    public void attackCreature(Card creature, int index, Card target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'attackCreature'");
    }

    @Override
    public void attackPlayer(Card creature, int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'attackPlayer'");
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
