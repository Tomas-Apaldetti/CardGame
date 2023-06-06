package com.core.tcg.driver.Adapter;

import com.core.g3.Card.Card;
import com.core.g3.Match.IMatch;
import com.core.g3.Match.Match;
import com.core.tcg.driver.*;

import java.util.List;
import java.util.Optional;

public class MatchDriverClass implements MatchDriver<Card> {
    private IMatch match;

    public MatchDriverClass(IMatch match) {
        this.match = match;
    }

    @Override
    public List<DriverCardName> deckOrder(DriverMatchSide player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deckOrder'");
    }

    @Override
    public void forceDeckOrder(DriverMatchSide player, List<DriverCardName> cards) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'forceDeckOrder'");
    }

    @Override
    public void start() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }

    @Override
    public void skipToPhase(DriverMatchSide player, DriverTurnPhase phase) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'skipToPhase'");
    }

    @Override
    public Card summon(DriverMatchSide player, DriverCardName card, DriverActiveZone zone) {
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
    public void activateArtifact(Card artifact, int index, Optional<DriverMatchSide> targetPlayer, List<Card> targets) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'activateArtifact'");
    }

    @Override
    public void activateAction(DriverMatchSide player, DriverCardName card, int index,
                               Optional<DriverMatchSide> targetPlayer, List<Card> targetCards) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'activateAction'");
    }

    @Override
    public void startReactionWindow() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startReactionWindow'");
    }

    @Override
    public void endReactionWindow() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'endReactionWindow'");
    }

    @Override
    public void activateReactionFromHand(DriverMatchSide player, DriverCardName card,
                                         Optional<DriverMatchSide> targetPlayer, List<Card> targetCards) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'activateReactionFromHand'");
    }

    @Override
    public void activateReactionFromActiveZone(Card card, Optional<DriverMatchSide> targetPlayer,
                                               List<Card> targetCards) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'activateReactionFromActiveZone'");
    }

    @Override
    public int playerHealth(DriverMatchSide player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'playerEnergy'");
    }

    @Override
    public int playerEnergy(DriverMatchSide player, DriverEnergyType energyType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'playerEnergy'");
    }

    @Override
    public Optional<DriverMatchSide> winner() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'winner'");
    }

}

