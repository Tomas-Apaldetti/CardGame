package com.core.g3.Match.ResolutionStack.Reactions;

import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.LingeringEffect.ILingeringEffect;
import com.core.g3.Match.ResolutionStack.ResolutionStack;

import java.util.List;

public interface IReactionEffect {

    List<ILingeringEffect> apply(Reaction reaction, CardInGame source, ResolutionStack stack, Player user, Player rival);

    boolean is(ReactionNames name);
}
