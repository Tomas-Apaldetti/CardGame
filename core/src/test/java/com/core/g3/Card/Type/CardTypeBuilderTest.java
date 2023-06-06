package com.core.g3.Card.Type;

import org.junit.jupiter.api.Test;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;
import com.core.g3.Card.Effects.IEffect;
import com.core.g3.Card.Type.Exceptions.CardTypeIsAlreadyContainedInCardException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

public class CardTypeBuilderTest {

    private final List<IEffect> effects = new TestEffects().effects;

    @Test
    public void createCardOfType() {
        CardBuilder cardBuilder = new CardBuilder(CardName.Antimagic);
        cardBuilder.cardTypeBuilder.setTypeArtefact(this.effects);
        Card card = cardBuilder.build();

        ArrayList<ICardType.CardType> actual = new ArrayList<ICardType.CardType>();
        actual.add(ICardType.CardType.Artefact);

        assertEquals(CardName.Antimagic, card.getName());
        assertEquals(true, card.getTypes().containsAll(actual));
    }

    @Test
    public void createCardOfTwoTypes() {
        CardBuilder cardBuilder = new CardBuilder(CardName.Antimagic);
        cardBuilder.cardTypeBuilder.setTypeArtefact(effects);
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

        cardBuilder.cardTypeBuilder.setTypeAction(null, this.effects);
        cardBuilder.cardTypeBuilder.setTypeArtefact(this.effects);

        assertThrows(CardTypeIsAlreadyContainedInCardException.class,
                () -> cardBuilder.cardTypeBuilder.setTypeArtefact(this.effects));
    }

    private class TestEffects {
        public List<IEffect> effects;

        public TestEffects() {
            this.effects = new ArrayList<IEffect>();
            this.effects.add(new TestEffect());
        }
    }

    private class TestEffect implements IEffect {
    }

}