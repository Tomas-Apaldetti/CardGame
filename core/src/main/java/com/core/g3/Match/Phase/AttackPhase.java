package com.core.g3.Match.Phase;

import com.core.g3.Deck.ICard;
import com.core.g3.Match.Phase.Exceptions.NotPossibleToSummonInPhase;

public class AttackPhase implements IPhase {

    @Override
    public void canSummon(ICard card) {
        throw new NotPossibleToSummonInPhase(PhaseType.Attack);
    }

    @Override
    public void canAttack() {

    }
}
