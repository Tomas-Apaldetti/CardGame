package com.core.g3.Card.Type.Reaction;

import java.util.Optional;

import com.core.g3.Card.Cost.ICost;
import com.core.g3.Card.Cost.NullInvocationCost;
import com.core.g3.Card.Reaction.IReaction;
import com.core.g3.Card.Type.CardType;
import com.core.g3.Card.Type.CardTypeName;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.Reactions.IReactionEffect;
import com.core.g3.Match.ResolutionStack.Reactions.Reaction;
import com.core.g3.Match.ResolutionStack.ResolutionStack;

public class CardTypeReaction extends CardType {

    private final ICost useCost;
    private final IReaction effect;

    public CardTypeReaction(IReaction effect) {
        super(CardTypeName.Reaction);
        this.useCost = new NullInvocationCost();
        this.effect = effect;
    }

    public CardTypeReaction(ICost useCost, IReaction effect) {
        super(CardTypeName.Reaction);
        this.useCost = useCost;
        this.effect = effect;
    }

    public Optional<IReaction> getEffect(){
        return Optional.ofNullable(this.effect);
    }

    @Override
    public boolean is(CardTypeName cardType) {
        return CardTypeName.Reaction == cardType;
    }

    @Override
    public void reaction(CardInGame cardInGame, ResolutionStack stack, Player user, Player rival){
        this.effect.assertApplicability(cardInGame, stack, user, rival);
        useCost.apply(user);
        IReactionEffect effect = this.effect.getEffect();
        Reaction reaction = new Reaction(cardInGame, effect, user, rival, this.effect.discardOnUse(cardInGame));
        stack.addReaction(reaction);
    }
}