package com.core.g3.Card.Reaction;

import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.ResolutionStack.Reactions.Effects.Resonance;
import com.core.g3.Match.ResolutionStack.Reactions.IReactionEffect;

public class ResonanceReaction implements IReaction{
    @Override
    public IReactionEffect getEffect() {
        return new Resonance();
    }

    @Override
    public boolean discardOnUse(CardInGame cardInGame) {
        return true;
    }
}
