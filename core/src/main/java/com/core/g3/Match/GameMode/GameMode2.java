package com.core.g3.Match.GameMode;

import com.core.g3.Commons.Amount;
import com.core.g3.Deck.IDeck;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.CardInGame.IDeathSub;
import com.core.g3.Match.DeckPlayable.DeckPlayable;
import com.core.g3.Match.Player.MatchEndCondition.IConditionMetPub;
import com.core.g3.Match.Player.MatchEndCondition.IConditionMetSub;
import com.core.g3.Match.Player.MatchEndCondition.IMatchEndCondition;
import com.core.g3.Match.Player.MatchEndCondition.PointsCounter;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Zone.ActiveZone;
import com.core.g3.Match.Zone.ActiveZoneType;
import com.core.g3.User.User;

import java.util.Optional;

public class GameMode2 extends GameMode implements IDeathSub {
    private final static Amount winnerPoints = new Amount(6);

    public Optional<Player> winner;

    public GameMode2() {
        this.initialPoints = 0;
        this.maxDeckCards = 60;
        this.minDeckCards = 60;
        this.maxRepeatedCards = 4;
        this.combatZoneLimit = 1;
        this.artifactZoneLimit = 3;
        this.reserveZoneLimit = 5;
        this.initialHandSize = 7;
        this.winner = Optional.empty();
        this.gameModeType = GameModeType.CreatureSlayer;
    }

    public GameModeType getGameModeType() {
        return this.gameModeType;
    }

    protected PointsCounter getCondition() {
        return new PointsCounter(new Amount(this.initialPoints), GameMode2.winnerPoints);
    }

    @Override
    public Player addPlayer(User user, IDeck deck) {
        Player ret = super.addPlayer(user, deck);
        ret.subscribeToCardDeath(this);
        ret.addConditionMet(this);
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
        Player rival = this.match.getRival(card.getOwner());
        rival.affectMatchEndCondition(new Amount(1));
        rival.drawCard();
    }

    @Override
    public void conditionMet(Player who) {
        if (this.winner.isPresent()) {
            return;
        }
        this.winner = Optional.ofNullable(who);
    }

    @Override
    public void onInitialPhase(Player current, Player rival) {
        super.onInitialPhase(current, rival);
        if (this.winner.isPresent()) {
            this.match.setWinner(this.winner.get());
        }
    }
}