package com.core.g3.Match.ResolutionStack.Reactions.Effects;

import com.core.g3.Card.CardName;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.Reactions.IReactionEffect;
import com.core.g3.Match.ResolutionStack.Reactions.Reaction;
import com.core.g3.Match.ResolutionStack.Reactions.ReactionNames;
import com.core.g3.Match.ResolutionStack.ResolutionStack;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Resonance implements IReactionEffect {
    @Override
    public void apply(Reaction reaction, CardInGame source, ResolutionStack stack, Player user, Player rival) {

        int total = 0;
        if(this.searchForOriginal(stack)){
            total = this.previousResonances(reaction, stack) + 1;
        }
        rival.destroyCreatures(total);
    }

    private boolean searchForOriginal(ResolutionStack stack){
        if (stack.original().sourceIs(CardName.Resonance)){
            stack.original().cancel();
            return true;
        }
        return false;
    }

    private int previousResonances(Reaction container, ResolutionStack stack){
        Optional<List<Reaction>> previous = stack.nextReactions(container);
        List<Reaction> prev = previous.get();
        List<Reaction> resonances = prev.stream().filter(p -> p.is(ReactionNames.Resonance)).collect(Collectors.toList());
        resonances.forEach(p->p.cancel());
        return resonances.size();
    }

    @Override
    public boolean is(ReactionNames name) {
        return ReactionNames.Resonance == name;
    }
}
