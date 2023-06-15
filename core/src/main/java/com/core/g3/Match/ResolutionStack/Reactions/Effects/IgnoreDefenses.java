package com.core.g3.Match.ResolutionStack.Reactions.Effects;

import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.LingeringEffect.ILingeringEffect;
import com.core.g3.Match.ResolutionStack.Reactions.IReactionEffect;
import com.core.g3.Match.ResolutionStack.Reactions.Reaction;
import com.core.g3.Match.ResolutionStack.Reactions.ReactionNames;
import com.core.g3.Match.ResolutionStack.ResolutionStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IgnoreDefenses implements IReactionEffect {
    @Override
    public List<ILingeringEffect> apply(Reaction reaction, CardInGame source, ResolutionStack stack, Player user, Player rival) {
        if(stack.original().isSource(source)){
            Optional<List<Reaction>> p = stack.nextReactions(reaction);
            if(!p.isPresent()){
                return new ArrayList<>();
            }
            p.get().forEach(r -> r.cancel());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean is(ReactionNames name) {
        return false;
    }
}
