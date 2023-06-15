package com.core.g3.Card.Reaction;

import com.core.g3.Card.Reaction.Exceptions.ReactionNotUsableException;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.Reactions.Effects.DamageReduction;
import com.core.g3.Match.ResolutionStack.Reactions.IReactionEffect;
import com.core.g3.Match.ResolutionStack.ResolutionStack;

public class DamageReductionReaction implements IReaction{

    private final Amount maxDamage;

    public DamageReductionReaction(Amount maxDamage){
        this.maxDamage = maxDamage;
    }
    @Override
    public IReactionEffect getEffect() {
        return new DamageReduction(maxDamage);
    }

    @Override
    public boolean discardOnUse(CardInGame cardInGame) {
        return false;
    }

    @Override
    public void assertApplicability(CardInGame cardInGame, ResolutionStack stack, Player user, Player rival){
        if(!cardInGame.isInActiveZone()){
            throw new ReactionNotUsableException();
        }
    }
}
