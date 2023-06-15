package com.core.g3.Match.ResolutionStack.Reactions;

import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.LingeringEffect.ILingeringEffect;
import com.core.g3.Match.ResolutionStack.ResolutionStack;

import java.util.ArrayList;
import java.util.List;

public class Reaction {

    private final CardInGame source;
    private final IReactionEffect effect;
    private final Player user;
    private final Player rival;
    private boolean discardOnUse;

    private boolean isCanceled;

    public Reaction(CardInGame source, IReactionEffect reactionEffect, Player user, Player rival, boolean discardOnUse){
        this.source = source;
        this.effect = reactionEffect;
        this.user = user;
        this.rival = rival;
        this.discardOnUse = discardOnUse;
        this.isCanceled = false;
    }

    public List<ILingeringEffect> apply(ResolutionStack stack){
        List<ILingeringEffect> effects = new ArrayList<>();
        if(!this.isCanceled) {
            effects.addAll(this.effect.apply(this, this.source, stack, user, rival));
        }
        this.onUse();
        return effects;
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
