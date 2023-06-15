package com.core.g3.Match.Phase;

import com.core.g3.Match.Player.Player;

public class PhaseFactory {
    public static IPhase createNewPhase(PhaseType type, Player current, Player rival) {
        switch (type) {
            case Initial:
                return new InitialPhase(current, rival);
            case Main:
                return new MainPhase(current, rival);
            case Attack:
                return new AttackPhase(current, rival);
            case End:
                return new EndPhase();
            default:
                throw new RuntimeException();
        }
    }
}
