package com.core.g3.Match.CardInGame.CardTypeStateManager;

import com.core.g3.Card.Artefact.IArtefactEffect;
import com.core.g3.Card.Effects.IEffect;

import java.util.List;
import java.util.Optional;

public class ArtefactStateManager {

    private final Optional<IArtefactEffect> effect;
    private boolean alreadyUsed;

    public ArtefactStateManager(Optional<IArtefactEffect> effect){
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
