package com.core.g3.Match.ResolutionStack.Reactions.Effects;

import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.Reactions.IReactionEffect;
import com.core.g3.Match.ResolutionStack.Reactions.Reaction;
import com.core.g3.Match.ResolutionStack.Reactions.ReactionNames;
import com.core.g3.Match.ResolutionStack.ResolutionStack;

import java.util.Optional;

public class PreventReaction implements IReactionEffect {
    @Override
    public void apply(Reaction reaction, CardInGame source, ResolutionStack stack, Player user, Player rival) {
        Optional<Reaction> next = stack.nextReaction(reaction);
        if(!next.isPresent()){
            return;
        }
        next.get().cancel();
        next.get().discardOnUse(true);
    }

    @Override
    public boolean is(ReactionNames name) {
        return ReactionNames.PreventReaction == name;
    }
}
