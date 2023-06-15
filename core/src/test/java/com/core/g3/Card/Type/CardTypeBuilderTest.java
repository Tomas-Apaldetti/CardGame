package com.core.g3.Card.Type;

import com.core.g3.Card.Artefact.AddEnergyArtefact;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Resources.EnergyType;
import org.junit.jupiter.api.Test;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;
import com.core.g3.Card.Effects.IEffect;
import com.core.g3.Card.Type.Exceptions.CardTypeIsAlreadyContainedInCardException;
import com.core.g3.Match.Zone.ActiveZoneType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

public class CardTypeBuilderTest {

    @Test
    public void createCardOfType() {
        CardBuilder cardBuilder = new CardBuilder(CardName.Antimagic);
        cardBuilder.cardTypeBuilder.setTypeArtefact(null);
        Card card = cardBuilder.build();

        ArrayList<CardTypeName> actual = new ArrayList<CardTypeName>();
        actual.add(CardTypeName.Artefact);

        assertEquals(CardName.Antimagic, card.getName());
        assertEquals(true, actual.containsAll(card.getTypes()));
    }

    @Test
    public void createCardOfTwoTypes() {
        CardBuilder cardBuilder = new CardBuilder(CardName.Antimagic);
        cardBuilder.cardTypeBuilder.setTypeArtefact(null);
        cardBuilder.cardTypeBuilder.setTypeCreature(null, null, null);
        Card card = cardBuilder.build();

        ArrayList<CardTypeName> actual = new ArrayList<CardTypeName>();
        actual.add(CardTypeName.Artefact);
        actual.add(CardTypeName.Creature);

        assertEquals(CardName.Antimagic, card.getName());
        assertEquals(true, actual.containsAll(card.getTypes()));
    }

    @Test
    public void createCardOfDuplicatedTypesThrows() {
        CardBuilder cardBuilder = new CardBuilder(CardName.Antimagic);

        ArrayList<CardTypeName> actual = new ArrayList<CardTypeName>();
        actual.add(CardTypeName.Action);
        actual.add(CardTypeName.Artefact);
        actual.add(CardTypeName.Artefact);

        cardBuilder.cardTypeBuilder.setTypeAction(null);
        cardBuilder.cardTypeBuilder.setTypeArtefact(null);

        assertThrows(CardTypeIsAlreadyContainedInCardException.class,
                () -> cardBuilder.cardTypeBuilder.setTypeArtefact(null));
    }

    @Test
    public void testAllowedZones() {
        CardBuilder cardBuilder = new CardBuilder(CardName.WaterEnergy);
        cardBuilder.cardTypeBuilder.setTypeArtefact(new AddEnergyArtefact(EnergyType.Fire, new Amount(10)));
        Card card = cardBuilder.build();

        assertEquals(true, card.getAllowableZones().contains(ActiveZoneType.Artifacts));
    }

}