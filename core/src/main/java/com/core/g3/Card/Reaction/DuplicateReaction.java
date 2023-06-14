package com.core.g3.Card.Reaction;

import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.ResolutionStack.Reactions.Effects.Duplicate;
import com.core.g3.Match.ResolutionStack.Reactions.IReactionEffect;

public class DuplicateReaction implements IReaction{
    @Override
    public IReactionEffect getEffect() {
        return new Duplicate();
    }

    @Override
    public boolean discardOnUse(CardInGame cardInGame) {
        return cardInGame.isInActiveZone();
    }
}
