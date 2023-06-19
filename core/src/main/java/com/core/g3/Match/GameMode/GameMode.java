package com.core.g3.Match.GameMode;

import com.core.g3.Card.CardName;
import com.core.g3.Commons.Amount;
import com.core.g3.Deck.IDeck;
import com.core.g3.Match.DeckPlayable.DeckPlayable;
import com.core.g3.Match.GameMode.Exceptions.InvalidDeckCount;
import com.core.g3.Match.Match;
import com.core.g3.Match.Player.MatchEndCondition.IConditionMetSub;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Player.MatchEndCondition.IMatchEndCondition;
import com.core.g3.Match.Zone.ActiveZone;
import com.core.g3.Match.Zone.ActiveZoneType;
import com.core.g3.User.User;

import java.util.HashMap;
import java.util.Optional;
import java.util.stream.IntStream;

public abstract class GameMode implements IConditionMetSub {
    protected int initialPoints;
    protected int maxDeckCards;
    protected int minDeckCards;
    protected int maxRepeatedCards;
    protected int combatZoneLimit;
    protected int artifactZoneLimit;
    protected int reserveZoneLimit;
    protected int initialHandSize;
    protected Match match;
    protected GameModeType gameModeType;

    protected abstract IMatchEndCondition getCondition();

    public Player addPlayer(User user, IDeck deck) {
        this.checkDecks(deck);
        this.checkRepeatedCards(deck, maxRepeatedCards);

        DeckPlayable playableDeck = new DeckPlayable(deck);

        IMatchEndCondition condition = this.getCondition();

        ActiveZone artifactZone = new ActiveZone(ActiveZoneType.Artifacts, new Amount(this.artifactZoneLimit));
        ActiveZone combatZone = new ActiveZone(ActiveZoneType.Combat, new Amount(this.combatZoneLimit));
        ActiveZone reserveZone = new ActiveZone(ActiveZoneType.Reserve, new Amount(this.reserveZoneLimit));

        Player player = new Player(user, playableDeck, condition, artifactZone, combatZone, reserveZone);

        player.addConditionMet(this);
        return player;
    }

    public abstract GameModeType getGameModeType();

    protected void checkRepeatedCards(IDeck deck, int maxRepeatedCards) {
        HashMap<CardName, Integer> countEachCard = deck.getRepeatedCards();
        countEachCard.forEach((cardType, cardCount) -> {
            if (cardCount > maxRepeatedCards) {
                throw new InvalidDeckCount("El mazo no puede tener mas de 3 copias de cada carta");
            }
        });
    }

    private void checkDecks(IDeck deck) {
        System.out.println("El mazo tiene: " + deck.getCards().size());
        if (deck.getCards().size() < minDeckCards || deck.getCards().size() > maxDeckCards) {
            throw new InvalidDeckCount("El mazo debe tener entre " + minDeckCards + " y " + maxDeckCards + " cartas");
        }
    }

    public void drawInitialCards(Player player) {
        IntStream.range(0, this.initialHandSize).forEach(i -> player.drawCard());
    }

    public abstract Optional<Player> getWinner(Player player1, Player player2);

    public void setMatch(Match match) {
        this.match = match;
    }

    public void onInitialPhase(Player current, Player rival) {
        current.drawCard();
    }
}