package com.core.g3.Match.Phase;

import com.core.g3.Match.Match;
import com.core.g3.Match.Player.Player;

public class EndPhase implements IPhase {

    private final Player current;
    private final Player rival;
    private final Match match;

    public EndPhase(Player current, Player rival, Match match) {
        this.current = current;
        this.rival = rival;
        this.match = match;
    }
    @Override
    public Player activePlayer() {
        return null;
    }
}
