package com.core.g3.Match.Phase;

import com.core.g3.Deck.ICard;
import com.core.g3.Match.Phase.Exceptions.NotPossibleToAttack;
import com.core.g3.Match.Phase.Exceptions.NotPossibleToSummonInPhase;

public class EndPhase implements IPhase {

    @Override
    public void canSummon(ICard card) {
        throw new NotPossibleToSummonInPhase(PhaseType.End);
    }

    @Override
    public void canAttack() {
        throw new NotPossibleToAttack();
    }

}
