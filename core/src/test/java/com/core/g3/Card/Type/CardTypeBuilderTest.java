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
        cardBuilder.cardTypeBuilder.setTypeArtefact(null);
        Card card = cardBuilder.build();

        ArrayList<ICardType.CardType> actual = new ArrayList<ICardType.CardType>();
        actual.add(ICardType.CardType.Artefact);

        assertEquals(CardName.Antimagic, card.getName());
        assertEquals(true, card.getTypes().containsAll(actual));
    }

    @Test
    public void createCardOfTwoTypes() {
        CardBuilder cardBuilder = new CardBuilder(CardName.Antimagic);
        cardBuilder.cardTypeBuilder.setTypeArtefact(null);
        cardBuilder.cardTypeBuilder.setTypeCreature(null, null, null);
        Card card = cardBuilder.build();

        ArrayList<ICardType.CardType> actual = new ArrayList<ICardType.CardType>();
        actual.add(ICardType.CardType.Artefact);
        actual.add(ICardType.CardType.Creature);

        assertEquals(CardName.Antimagic, card.getName());
        assertEquals(true, card.getTypes().containsAll(actual));
    }

    @Test
    public void createCardOfDuplicatedTypesThrows() {
        CardBuilder cardBuilder = new CardBuilder(CardName.Antimagic);

        ArrayList<ICardType.CardType> actual = new ArrayList<ICardType.CardType>();
        actual.add(ICardType.CardType.Action);
        actual.add(ICardType.CardType.Artefact);
        actual.add(ICardType.CardType.Artefact);

        cardBuilder.cardTypeBuilder.setTypeAction(null, null);
        cardBuilder.cardTypeBuilder.setTypeArtefact(null);

        assertThrows(CardTypeIsAlreadyContainedInCardException.class,
                () -> cardBuilder.cardTypeBuilder.setTypeArtefact(null));
    }
}
