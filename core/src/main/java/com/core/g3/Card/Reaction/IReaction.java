package com.core.g3.Card.Reaction;

import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.Reactions.IReactionEffect;
import com.core.g3.Match.ResolutionStack.ResolutionStack;

public interface IReaction {

    IReactionEffect getEffect();

    boolean discardOnUse(CardInGame cardInGame);

    default void assertApplicability(CardInGame cardInGame, ResolutionStack stack, Player user, Player rival){
        return;
    }
}
