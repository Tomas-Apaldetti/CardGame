package com.core.g3.Match.Phase;

import com.core.g3.Match.Match;
import com.core.g3.Match.Player.Player;

public class PhaseFactory {
    public static IPhase createNewPhase(PhaseType type, Player current, Player rival, Match match) {
        switch (type) {
            case Initial:
                return new InitialPhase(current, rival, match);
            case Main:
                return new MainPhase(current, rival, match);
            case Attack:
                return new AttackPhase(current, rival, match);
            case End:
                return new EndPhase(current,rival,match);
            default:
                throw new RuntimeException();
        }
    }
}
