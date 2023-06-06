package com.core.g3.Card.Type;

import java.util.ArrayList;
import java.util.List;

import com.core.g3.Card.Type.Exceptions.CardTypeIsAlreadyContainedInCardException;

public class CardTypeBuilder {

    private List<CardType> cardTypes;

    public CardTypeBuilder() {
        this.cardTypes = new ArrayList<CardType>();
    }

    private void checkType(ICardType.CardType type) {
        for (CardType cardType : this.cardTypes) {
            if (cardType.getType().equals(type)) {
                throw new CardTypeIsAlreadyContainedInCardException();
            }
        }
    }

    public void setTypeCriatura() {
        CardTypeCriatura creature = new CardTypeCriatura();
        checkType(creature.getType());
        this.cardTypes.add(creature);
    }

    public void setTypeArtefacto() {
        CardTypeArtefacto artifact = new CardTypeArtefacto();
        checkType(artifact.getType());
        this.cardTypes.add(artifact);
    }

    public void setTypeAccion() {
        CardTypeAccion action = new CardTypeAccion();
        checkType(action.getType());
        this.cardTypes.add(action);
    }

    public void setTypeReaccion() {
        CardTypeReaccion reaction = new CardTypeReaccion();
        checkType(reaction.getType());
        this.cardTypes.add(reaction);
    }

    public void setTypeCombinada() {
        CardTypeCombinada combined = new CardTypeCombinada();
        checkType(combined.getType());
        this.cardTypes.add(combined);
    }

    // TODO -> refactor of setTypes?

    public List<ICardType.CardType> getTypes() {
        ArrayList<ICardType.CardType> types = new ArrayList<ICardType.CardType>();

        this.cardTypes.forEach(cardType -> types.add(cardType.getType()));

        return types;
    }
}
