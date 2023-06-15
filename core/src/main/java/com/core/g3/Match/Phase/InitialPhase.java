package com.core.g3.Match.Phase;

import java.util.List;

import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.LingeringEffect.ILingeringEffect;

public class InitialPhase implements IPhase {

    @Override
    public void applyLingeringEffects(List<ILingeringEffect> effectsToApply, Player activePlayer) {
        effectsToApply.forEach(e -> e.apply(this, activePlayer));
    }

}
