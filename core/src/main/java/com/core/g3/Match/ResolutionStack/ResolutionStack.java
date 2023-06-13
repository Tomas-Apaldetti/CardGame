package com.core.g3.Match.ResolutionStack;

import com.core.g3.Match.ResolutionStack.OriginalAction.IOriginal;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;
import com.core.g3.Match.ResolutionStack.Reactions.Reaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ResolutionStack {

    private final OriginalAction original;

    private final List<Reaction> reactionStack;
    public ResolutionStack(OriginalAction og){
        this.original = og;
        this.reactionStack = new ArrayList<>();
    }

    public Optional<Reaction> nextReaction(Reaction to){

        Optional<Integer> toIdx = this.getIdx(to);
        if(!toIdx.isPresent()){
            return Optional.empty();
        }

        return Optional.ofNullable(this.reactionStack.get(toIdx.get() - 1));
    }

    public Optional<List<Reaction>> nextReactions(Reaction to){
        Optional<Integer> toIdx = this.getIdx(to);
        if(!toIdx.isPresent()){
            return Optional.empty();
        }

        return Optional.ofNullable(this.reactionStack.subList(0,toIdx.get() - 1));
    }

    private Optional<Integer> getIdx(Reaction to){
        if(!this.reactionStack.contains(to)){
            //@TODO: exceptionname
            throw new RuntimeException();
        }

        int toIdx = this.reactionStack.indexOf(to);
        return toIdx == 0 ? Optional.empty() : Optional.of(toIdx);

    }
    public IOriginal original(){
        return this.original;
    }
}
