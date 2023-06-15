package com.core.g3.Card;

import org.junit.jupiter.api.Test;

import com.core.g3.Card.Cards.Alchemist;
import com.core.g3.Card.Cards.FireEnergy;
import com.core.g3.Card.Cards.WaterEnergy;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardsTest {
    @Test
    public void testFireEnergy() {
        Card card = FireEnergy.create();
        assertEquals(CardName.FireEnergy, card.getName());
        assertEquals(0, card.getAttacks().size());
        assertEquals(true, card.getArtefactEffects().isPresent());
    }

    @Test
    public void testWaterEnergy() {
        Card card = WaterEnergy.create();
        assertEquals(CardName.WaterEnergy, card.getName());
        assertEquals(0, card.getAttacks().size());
        assertEquals(true, card.getArtefactEffects().isPresent());
    }

    @Test
    public void testPlantEnergy() {
        Card card = WaterEnergy.create();
        assertEquals(CardName.WaterEnergy, card.getName());
        assertEquals(0, card.getAttacks().size());
        assertEquals(true, card.getArtefactEffects().isPresent());
    }
    

    @Test
    public void testAlchemist() {
        Card card = Alchemist.create();
    }
}
