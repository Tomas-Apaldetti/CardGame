package com.core.g3.Match.ResolutionStack.Reactions.Effects;

import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.ActionType;
import com.core.g3.Match.ResolutionStack.Reactions.IReactionEffect;
import com.core.g3.Match.ResolutionStack.Reactions.Reaction;
import com.core.g3.Match.ResolutionStack.Reactions.ReactionNames;
import com.core.g3.Match.ResolutionStack.ResolutionStack;

public class Sabotage implements IReactionEffect {
    @Override
    public void apply(Reaction reaction, CardInGame source, ResolutionStack stack, Player user, Player rival) {
        if (stack.original().is(ActionType.ArtifactEffect)) {
            stack.original().cancel();
            stack.original().discardSource();
        }
    }

    @Override
    public boolean is(ReactionNames name) {
        return false;
    }
}
