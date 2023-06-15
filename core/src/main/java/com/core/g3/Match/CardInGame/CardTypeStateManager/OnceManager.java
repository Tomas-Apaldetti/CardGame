package com.core.g3.Match.CardInGame.CardTypeStateManager;

import java.util.Optional;

public class OnceManager<T> {

    private final Optional<T> effect;
    private boolean alreadyUsed;

    public OnceManager(Optional<T> effect){
        this.effect = effect;
        this.alreadyUsed = true;
    }

    public boolean canActivate(){
        return this.effect.isPresent() && !this.alreadyUsed;
    }

    public void reset(){
        this.alreadyUsed = false;
    }

    public void deplete(){
        this.alreadyUsed = true;
    }
}
