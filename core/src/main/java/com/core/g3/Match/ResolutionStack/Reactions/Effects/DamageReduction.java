package com.core.g3.Match.ResolutionStack.Reactions.Effects;

import com.core.g3.Commons.Amount;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.Reactions.IReactionEffect;
import com.core.g3.Match.ResolutionStack.Reactions.Reaction;
import com.core.g3.Match.ResolutionStack.Reactions.ReactionNames;
import com.core.g3.Match.ResolutionStack.ResolutionStack;

public class DamageReduction implements IReactionEffect {

    private final Amount maxDamage;

    public DamageReduction(Amount maxDamage){
        this.maxDamage = maxDamage;
    }
    @Override
    public void apply(Reaction reaction, CardInGame source, ResolutionStack stack, Player user, Player rival) {
        if(stack.original().isObjective(source)){
            stack.original().setMaxDamage(this.maxDamage);
        }
    }

    @Override
    public boolean is(ReactionNames name) {
        return ReactionNames.DamageReduction == name;
    }
}
