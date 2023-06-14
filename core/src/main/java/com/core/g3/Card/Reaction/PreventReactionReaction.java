package com.core.g3.Card.Reaction;

import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.ResolutionStack.Reactions.Effects.PreventReaction;
import com.core.g3.Match.ResolutionStack.Reactions.IReactionEffect;

public class PreventReactionReaction implements IReaction{
    @Override
    public IReactionEffect getEffect() {
        return new PreventReaction();
    }

    @Override
    public boolean discardOnUse(CardInGame cardInGame) {
        return true;
    }
}
