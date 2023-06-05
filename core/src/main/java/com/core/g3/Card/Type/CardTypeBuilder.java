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
        CardTypeCriatura criatura = new CardTypeCriatura();
        checkType(criatura.getType());
        this.cardTypes.add(criatura);
    }

    public void setTypeArtefacto() {
        CardTypeArtefacto artefacto = new CardTypeArtefacto();
        checkType(artefacto.getType());
        this.cardTypes.add(artefacto);
    }

    public void setTypeAccion() {
        CardTypeAccion accion = new CardTypeAccion();
        checkType(accion.getType());
        this.cardTypes.add(accion);
    }

    public void setTypeReaccion() {
        CardTypeReaccion reaccion = new CardTypeReaccion();
        checkType(reaccion.getType());
        this.cardTypes.add(reaccion);
    }

    public void setTypeCombinada() {
        CardTypeCombinada combinada = new CardTypeCombinada();
        checkType(combinada.getType());
        this.cardTypes.add(combinada);
    }

    // TODO -> refactor of setTypes?

    public List<ICardType.CardType> getTypes() {
        ArrayList<ICardType.CardType> types = new ArrayList<ICardType.CardType>();

        this.cardTypes.forEach(cardType -> types.add(cardType.getType()));

        return types;
    }
}
