package com.core.g3.Card.Type;

import java.util.ArrayList;
import java.util.List;

import com.core.g3.Card.Artefact.IArtefactEffect;
import com.core.g3.Card.Cost.ICost;
import com.core.g3.Card.Effects.IEffect;
import com.core.g3.Card.Reaction.IReaction;
import com.core.g3.Card.Type.Action.CardTypeAction;
import com.core.g3.Card.Type.Artefact.CardTypeArtefact;
import com.core.g3.Card.Type.Creature.Attribute;
import com.core.g3.Card.Type.Creature.CardTypeCreature;
import com.core.g3.Card.Attack.IAttack;
import com.core.g3.Card.Type.Exceptions.CardTypeIsAlreadyContainedInCardException;
import com.core.g3.Card.Type.Reaction.CardTypeReaction;
import com.core.g3.Commons.Amount;

public class CardTypeBuilder {

    private List<ICardType> cardTypes;

    public CardTypeBuilder() {
        this.cardTypes = new ArrayList<>();
    }

    private void checkType(ICardType.CardType type) {
        for (ICardType cardType : this.cardTypes) {
            if (cardType.getType().equals(type)) {
                throw new CardTypeIsAlreadyContainedInCardException();
            }
        }
    }

    public void setTypeCreature(Amount baseHP, List<Attribute> attributes, List<IAttack> attacks) {
        CardTypeCreature creature = new CardTypeCreature(baseHP, attributes, attacks);
        checkType(creature.getType());
        this.cardTypes.add(creature);
    }

    public void setTypeArtefact(IArtefactEffect effects) {
        CardTypeArtefact artefact = new CardTypeArtefact(effects);
        checkType(artefact.getType());
        this.cardTypes.add(artefact);
    }

    public void setTypeAction(List<IEffect> effects) {
        CardTypeAction action = new CardTypeAction(effects);
        checkType(action.getType());
        this.cardTypes.add(action);
    }

    public void setTypeAction(ICost useCost, List<IEffect> effects) {
        CardTypeAction action = new CardTypeAction(useCost, effects);
        checkType(action.getType());
        this.cardTypes.add(action);
    }

    public void setTypeReaction(IReaction effect) {
        CardTypeReaction reaction = new CardTypeReaction(effect);
        checkType(reaction.getType());
        this.cardTypes.add(reaction);
    }

    public void setTypeReaction(ICost useCost, IReaction effect) {
        CardTypeReaction reaction = new CardTypeReaction(useCost, effect);
        checkType(reaction.getType());
        this.cardTypes.add(reaction);
    }
    public List<ICardType> getTypes() {
        return this.cardTypes;
    }
}
