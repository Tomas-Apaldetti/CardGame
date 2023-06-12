package com.core.tcg.driver.Adapter;

import com.core.g3.Card.Card;
import com.core.g3.Commons.Amount;
import com.core.g3.Deck.Deck;
import com.core.g3.Match.IMatch;
import com.core.g3.Match.Match;
import com.core.g3.Match.DeckPlayable.DeckPlayable;
import com.core.g3.Match.GameMode.GameMode1;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Player.PlayerZone;
import com.core.g3.Match.Player.MatchEndCondition.PlainHP;
import com.core.g3.User.User;
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
        return match.getPlayer(DriverMapper.toPlayerZone(player)).retrieveDeckOrder();
    }

    @Override
    public void forceDeckOrder(DriverMatchSide player, List<DriverCardName> cards) {
        match.forceDeckOrder(DriverMapper.toPlayerZone(player), DriverMapper.toDriverCardName(cards));
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
        Player playerBlue = this.createPlayer("blue", "test1", 100);
        Player playerGreen = this.createPlayer("green", "test2", 0);
        GameMode1 gameMode = new GameMode1();

        Match match = new Match(playerBlue, playerGreen, gameMode);
        Optional<PlayerZone> winner = match.getWinner();
        if (winner.isPresent()) {
            return Optional.of(DriverMapper.toDriverMatchSide(winner.get()));
        } else {
            return Optional.empty();
        }
    }

    private Player createPlayer(String username, String deckName, Integer initialAmount) {
        User user = new User(username);
        Deck deck = new Deck(deckName);
        DeckPlayable playableDeck = new DeckPlayable(deck);
        PlainHP condition = new PlainHP(new Amount(initialAmount));
        return new Player(user, playableDeck, condition, null, null, null);
    }

}

