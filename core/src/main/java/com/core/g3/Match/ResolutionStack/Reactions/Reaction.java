package com.core.g3.Match.ResolutionStack.Reactions;

import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.ResolutionStack.ResolutionStack;

public class Reaction {

    private final CardInGame source;
    private final IReactionEffect effect;
    private boolean discardOnUse;

    private boolean isCanceled;

    public Reaction(CardInGame source, IReactionEffect reactionEffect, boolean discardOnUse){
        this.source = source;
        this.effect = reactionEffect;
        this.discardOnUse = discardOnUse;
        this.isCanceled = false;
    }

    public void apply(ResolutionStack stack){
        if(!this.isCanceled) {
            this.effect.apply(this, this.source, stack, , );
        }
        this.onUse();
    }

    public void discardOnUse(boolean discardOnUse){
        this.discardOnUse = discardOnUse;
    }

    public void cancel(){
        this.isCanceled = true;
    }

    private void onUse(){
        if(this.discardOnUse){
            this.source.discard();
        }
    }

    public boolean is(ReactionNames reaction){
        return this.effect.is(reaction);
    }

}
