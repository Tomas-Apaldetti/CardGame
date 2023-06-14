package com.core.g3.Card.Reaction;

import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.ResolutionStack.Reactions.Effects.IgnoreDefenses;
import com.core.g3.Match.ResolutionStack.Reactions.IReactionEffect;


public class IgnoreDefensesReaction implements IReaction{
    @Override
    public IReactionEffect getEffect() {
        return new IgnoreDefenses();
    }

    @Override
    public boolean discardOnUse(CardInGame cardInGame) {
        return false;
    }
}
