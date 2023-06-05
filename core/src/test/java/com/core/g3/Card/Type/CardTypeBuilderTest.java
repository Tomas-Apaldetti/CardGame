package com.core.g3.Card.Type;

import org.junit.jupiter.api.Test;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;
import com.core.g3.Card.Type.Exceptions.CardTypeIsAlreadyContainedInCardException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

public class CardTypeBuilderTest {

    @Test
    public void createCardOfType() {
        CardBuilder cardBuilder = new CardBuilder(CardName.Antimagic);
        cardBuilder.setTypeArtefacto();
        Card card = cardBuilder.build();

        ArrayList<ICardType.CardType> actual = new ArrayList<ICardType.CardType>();
        actual.add(ICardType.CardType.Artefacto);

        assertEquals(CardName.Antimagic, card.getName());
        assertEquals(true, card.getTypes().containsAll(actual));
    }

    @Test
    public void createCardOfTwoTypes() {
        CardBuilder cardBuilder = new CardBuilder(CardName.Antimagic);
        cardBuilder.setTypeArtefacto();
        cardBuilder.setTypeCombinada();
        Card card = cardBuilder.build();

        ArrayList<ICardType.CardType> actual = new ArrayList<ICardType.CardType>();
        actual.add(ICardType.CardType.Artefacto);
        actual.add(ICardType.CardType.Combinada);

        assertEquals(CardName.Antimagic, card.getName());
        assertEquals(true, card.getTypes().containsAll(actual));
    }

    @Test
    public void createCardOfAllTypes() {
        CardBuilder cardBuilder = new CardBuilder(CardName.Antimagic);

        cardBuilder.setAllTypes();
        Card card = cardBuilder.build();

        ArrayList<ICardType.CardType> actual = new ArrayList<ICardType.CardType>();
        actual.add(ICardType.CardType.Accion);
        actual.add(ICardType.CardType.Artefacto);
        actual.add(ICardType.CardType.Combinada);
        actual.add(ICardType.CardType.Criatura);
        actual.add(ICardType.CardType.Reaccion);

        assertEquals(CardName.Antimagic, card.getName());
        assertEquals(true, card.getTypes().containsAll(actual));
    }

    @Test
    public void createCardOfDuplicatedTypesThrows() {
        CardBuilder cardBuilder = new CardBuilder(CardName.Antimagic);

        ArrayList<ICardType.CardType> actual = new ArrayList<ICardType.CardType>();
        actual.add(ICardType.CardType.Accion);
        actual.add(ICardType.CardType.Artefacto);
        actual.add(ICardType.CardType.Artefacto);

        cardBuilder.setTypeAccion();
        cardBuilder.setTypeArtefacto();

        assertThrows(CardTypeIsAlreadyContainedInCardException.class, () -> cardBuilder.setTypeArtefacto());
    }
}
