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
        actual.add(ICardType.CardType.Artifact);

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
        actual.add(ICardType.CardType.Artifact);
        actual.add(ICardType.CardType.Combined);

        assertEquals(CardName.Antimagic, card.getName());
        assertEquals(true, card.getTypes().containsAll(actual));
    }

    @Test
    public void createCardOfAllTypes() {
        CardBuilder cardBuilder = new CardBuilder(CardName.Antimagic);

        cardBuilder.setAllTypes();
        Card card = cardBuilder.build();

        ArrayList<ICardType.CardType> actual = new ArrayList<ICardType.CardType>();
        actual.add(ICardType.CardType.Action);
        actual.add(ICardType.CardType.Artifact);
        actual.add(ICardType.CardType.Combined);
        actual.add(ICardType.CardType.Creature);
        actual.add(ICardType.CardType.Reaction);

        assertEquals(CardName.Antimagic, card.getName());
        assertEquals(true, card.getTypes().containsAll(actual));
    }

    @Test
    public void createCardOfDuplicatedTypesThrows() {
        CardBuilder cardBuilder = new CardBuilder(CardName.Antimagic);

        ArrayList<ICardType.CardType> actual = new ArrayList<ICardType.CardType>();
        actual.add(ICardType.CardType.Action);
        actual.add(ICardType.CardType.Artifact);
        actual.add(ICardType.CardType.Artifact);

        cardBuilder.setTypeAccion();
        cardBuilder.setTypeArtefacto();

        assertThrows(CardTypeIsAlreadyContainedInCardException.class, () -> cardBuilder.setTypeArtefacto());
    }
}
