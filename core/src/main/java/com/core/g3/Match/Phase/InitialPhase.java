package com.core.g3.Match.Phase;

import com.core.g3.Match.Player.Player;

import java.util.List;

public class InitialPhase implements IPhase {
    private final Player current;
    private final Player rival;

    public InitialPhase(Player current, Player rival){
        this.current = current;
        this.rival = rival;
    }

    public void applyLingeringEffects(List<Object> effects){
        effects.forEach((e) -> e.toString()); //@TODO
    }

    @Override
    public Player activePlayer() {
        return this.current;
    }
}
