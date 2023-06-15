package com.core.g3.Match.GameMode;

import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.MatchEndCondition.PointsCounter;
import com.core.g3.Match.Player.Player;

import java.util.Optional;

public class GameMode2 extends GameMode {
    private final static Amount winnerPoints = new Amount(6);

    public GameMode2() {
        this.initialPoints = 0;
        this.maxDeckCards = 60;
        this.minDeckCards = 60;
        this.maxRepeatedCards = 4;
        this.combatZoneLimit = 1;
        this.artefactZoneLimit = 5;
        this.reserveZoneLimit = 3;
        this.initialHandSize = 7;
    }

    protected PointsCounter getCondition() {
        return new PointsCounter(new Amount(this.initialPoints), GameMode2.winnerPoints);
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
}