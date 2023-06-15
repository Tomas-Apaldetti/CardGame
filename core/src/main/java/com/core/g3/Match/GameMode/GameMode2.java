package com.core.g3.Match.GameMode;

import com.core.g3.Commons.Amount;
import com.core.g3.Deck.IDeck;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.CardInGame.IDeathSub;
import com.core.g3.Match.DeckPlayable.DeckPlayable;
import com.core.g3.Match.Player.MatchEndCondition.IMatchEndCondition;
import com.core.g3.Match.Player.MatchEndCondition.PointsCounter;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Zone.ActiveZone;
import com.core.g3.Match.Zone.ActiveZoneType;
import com.core.g3.User.User;

import java.util.Optional;

public class GameMode2 extends GameMode implements IDeathSub{
    private final static Amount winnerPoints = new Amount(6);

    public GameMode2() {
        this.initialPoints = 0;
        this.maxDeckCards = 60;
        this.minDeckCards = 60;
        this.maxRepeatedCards = 4;
        this.combatZoneLimit = 1;
        this.artifactZoneLimit = 5;
        this.reserveZoneLimit = 3;
        this.initialHandSize = 7;
    }

    protected PointsCounter getCondition() {
        return new PointsCounter(new Amount(this.initialPoints), GameMode2.winnerPoints);
    }

    @Override
    public Player addPlayer(User user, IDeck deck) {
        Player ret = super.addPlayer(user,deck);
        ret.subscribeToCardDeath(this);
        return ret;
    }

    public void initialStage(Player player) {
        player.drawCard();
    }

    @Override
    public Optional<Player> getWinner(Player player1, Player player2) {
        if (player1.matchEndConditionMet()) {
            return Optional.of(player1);
        } else if (player2.matchEndConditionMet()) {
            return Optional.of(player2);
        }
        return Optional.empty();
    }

    @Override
    public void notify(CardInGame card) {
        this.match.getRival(card.getOwner()).affectMatchEndCondition(new Amount(1));
    }
}