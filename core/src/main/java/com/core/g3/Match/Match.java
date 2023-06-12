package com.core.g3.Match;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardName;
import com.core.g3.Match.GameMode.GameMode;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Player.PlayerZone;
import com.core.g3.Match.Player.Resources.EnergyType;
import com.core.g3.Match.Player.Resources.IResource;
import com.core.tcg.driver.DriverCardName;

import java.util.List;

public class Match implements IMatch {
    private Player bluePlayer;
    private Player greenPlayer;
    private GameMode gameMode;

    public Match(Player bluePlayer, Player greenPlayer, GameMode gameMode) {
        this.bluePlayer = bluePlayer;
        this.greenPlayer = greenPlayer;
        this.gameMode = gameMode;
    }

    @Override
    public void startMatch() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startMatch'");
    }

    @Override
    public void forceDeckOrder(PlayerZone side, List<CardName> cards) {
        filterPlayer(side).forceDeckOrder(cards);
    }

    @Override
    public void summon(Player player, DriverCardName card, String zone) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'summon'");
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
    public int playerHealth(Player player) {
        return player.matchEndConditionPoints();
    }

    @Override
    public IResource playerEnergy(Player player, EnergyType energyType) {
        return player.getEnergy(energyType);
    }

    @Override
    public Player getPlayer(PlayerZone side) {
        return filterPlayer(side);
    }

    private Player filterPlayer(PlayerZone side) {
        return side.equals(PlayerZone.Blue) ? bluePlayer : greenPlayer;
    }

}
