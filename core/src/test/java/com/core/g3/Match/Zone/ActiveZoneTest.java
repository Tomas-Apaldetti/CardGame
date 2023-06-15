package com.core.g3.Match.Zone;

import java.util.ArrayList;
import java.util.List;

import com.core.g3.Card.Cost.Exception.CanNotPayException;
import com.core.g3.Card.Type.Exceptions.CardTypeNoSummonableInZoneException;
import com.core.g3.Match.Player.Resources.EnergyType;
import com.core.g3.Match.Zone.Exceptions.CardLimitReachedException;
import org.junit.jupiter.api.Test;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;
import com.core.g3.Card.Effects.IEffect;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ActiveZoneTest {

    private final List<IEffect> effects = new TestEffects().effects;

    @Test
    public void addCardNoCostEmptyZoneOk() {
        ActiveZone artifactsZone = new ActiveZone(ActiveZoneType.Artifacts, new Amount(1));

        CardBuilder builder = new CardBuilder(CardName.Antimagic);
        builder.cardTypeBuilder.setTypeArtifact(null);
        Card antimagic = builder.build();

        Player player = new Player(null, null, null, null,
                null, null);

        player.addToHand(antimagic);

        artifactsZone.addCard(antimagic, player);
        assertEquals(1, artifactsZone.currentCardCount());
    }

    @Test
    public void addCardCostCanPayEmptyZoneOk() {
        ActiveZone artifactsZone = new ActiveZone(ActiveZoneType.Artifacts, new Amount(3));

        CardBuilder builder = new CardBuilder(CardName.Antimagic);
        builder.cardTypeBuilder.setTypeArtifact(null);
        builder.invocationCost.addEnergyCost(EnergyType.Fire, new Amount(1));
        Card antimagic = builder.build();

        Player player = new Player(null, null, null, null,
                null, null);
        player.add(EnergyType.Fire, new Amount(1));
        player.addToHand(antimagic);

        artifactsZone.addCard(antimagic, player);
        assertEquals(1, artifactsZone.currentCardCount());
        assertEquals(0, player.getEnergy(EnergyType.Fire).available());
    }

    @Test
    public void addCardSummonableSpaceNotAvailableThrows() {
        ActiveZone artifactsZone = new ActiveZone(ActiveZoneType.Artifacts, new Amount(1));

        CardBuilder builder = new CardBuilder(CardName.Antimagic);
        builder.cardTypeBuilder.setTypeArtifact(null);

        builder.setSummonableSpace(new Amount(2));
        Card antimagic = builder.build();

        Player player = new Player(null, null, null, null,
                null, null);

        assertThrows(CardLimitReachedException.class, () -> artifactsZone.addCard(antimagic, player));
        assertEquals(0, artifactsZone.currentCardCount());
    }

    @Test
    public void addCardCantPayEnoughSpaceThrows() {
        ActiveZone artifactsZone = new ActiveZone(ActiveZoneType.Artifacts, new Amount(1));

        CardBuilder builder = new CardBuilder(CardName.Antimagic);
        builder.cardTypeBuilder.setTypeArtifact(null);
        builder.invocationCost.addEnergyCost(EnergyType.Fire, new Amount(1));
        builder.setSummonableSpace(new Amount(1));
        Card antimagic = builder.build();

        Player player = new Player(null, null, null, null,
                null, null);

        assertThrows(CanNotPayException.class, () -> artifactsZone.addCard(antimagic, player));
        assertEquals(0, artifactsZone.currentCardCount());
    }

    @Test
    public void addCardNotForThatZoneTypeThrows() {
        ActiveZone artifactsZone = new ActiveZone(ActiveZoneType.Combat, new Amount(1));

        CardBuilder builder = new CardBuilder(CardName.Antimagic);
        builder.cardTypeBuilder.setTypeArtifact(null);
        builder.setSummonableSpace(new Amount(1));
        Card antimagic = builder.build();

        Player player = new Player(null, null, null, null,
                null, null);

        assertThrows(CardTypeNoSummonableInZoneException.class, () -> artifactsZone.addCard(antimagic, player));
        assertEquals(0, artifactsZone.currentCardCount());
    }

    @Test
    public void addMultipleCardsOneFailsZoneStaysTheSame() {
        ActiveZone artifactsZone = new ActiveZone(ActiveZoneType.Artifacts, new Amount(10));

        CardBuilder builder = new CardBuilder(CardName.Antimagic);
        builder.cardTypeBuilder.setTypeArtifact(null);
        builder.setSummonableSpace(new Amount(1));
        Card antimagic = builder.build();

        CardBuilder builder2 = new CardBuilder(CardName.Alchemist);
        builder2.invocationCost.addEnergyCost(EnergyType.Fire, new Amount(1));
        builder2.cardTypeBuilder.setTypeArtifact(null);
        Card alchemist = builder2.build();

        Player player = new Player(null, null, null, null,
                null, null);

        player.addToHand(antimagic);

        artifactsZone.addCard(antimagic, player);
        assertThrows(CanNotPayException.class, () -> artifactsZone.addCard(alchemist, player));
        assertEquals(1, artifactsZone.currentCardCount());
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
