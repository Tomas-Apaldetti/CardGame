package com.core.g3.Match.Phase;

import com.core.g3.Deck.ICard;

public interface IPhase {
    public enum PhaseType {
        Initial,
        Main,
        Attack,
        End
    }

    public void canSummon(ICard card);

    public void canAttack();
}