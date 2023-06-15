package com.core.g3.Match.Phase;


public class PhaseFactory {
    public static IPhase createNewPhase(PhaseType type) {
        switch (type) {
            case Initial:
                return new InitialPhase();
            case Main:
                return new MainPhase();
            case Attack:
                return new AttackPhase();
            case End:
                return new EndPhase();
            default:
                return new InitialPhase();
        }
    }
}
