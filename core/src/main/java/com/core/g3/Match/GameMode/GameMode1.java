package com.core.g3.Match.GameMode;

import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.MatchEndCondition.IMatchEndCondition;
import com.core.g3.Match.Player.MatchEndCondition.PlainHP;
import com.core.g3.Match.Player.Player;

import java.util.Optional;

public class GameMode1 extends GameMode {

    public GameMode1() {
        this.initialPoints = 20;
        this.maxDeckCards = 60;
        this.minDeckCards = 40;
        this.maxRepeatedCards = 3;
        this.combatZoneLimit = 5;
        this.artifactZoneLimit = 5;
        this.reserveZoneLimit = 0;
        this.initialHandSize = 5;
    }

    protected IMatchEndCondition getCondition() {
        return new PlainHP(new Amount(this.initialPoints));
    }

    public void initialStage(Player player) {
        player.drawCard();
    }

    public Optional<Player> getWinner(Player player1, Player player2) {
        if (player1.matchEndConditionMet()) {
            return Optional.of(player2);
        } else if (player2.matchEndConditionMet()) {
            return Optional.of(player1);
        }
        return Optional.empty();
    }
}