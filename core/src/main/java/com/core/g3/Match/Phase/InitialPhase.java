package com.core.g3.Match.Phase;

import com.core.g3.Match.Match;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.LingeringEffect.ILingeringEffect;

import java.util.List;

public class InitialPhase implements IPhase {
    private final Player current;
    private final Player rival;
    private final Match match;

    public InitialPhase(Player current, Player rival, Match match){
        this.current = current;
        this.rival = rival;
        this.match = match;
    }

    @Override
    public void applyLingeringEffects(List<ILingeringEffect> effects, Player current){
        effects.forEach(e->e.apply(this, current));
    }

    @Override
    public Player activePlayer() {
        return this.current;
    }
}
