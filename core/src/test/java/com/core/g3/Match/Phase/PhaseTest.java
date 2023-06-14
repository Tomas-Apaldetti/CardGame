package com.core.g3.Match.Phase;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;
import com.core.g3.Card.Effects.IEffect;
import com.core.g3.Match.Phase.Exceptions.NotPossibleToSummonInPhase;
import com.core.g3.Match.Phase.IPhase.PhaseType;

public class PhaseTest {
    private final List<IEffect> effects = new TestEffects().effects;

    @Test
    public void testCanSummonPhaseInitial() {
        IPhase phase = PhaseFactory.createNewPhase(PhaseType.Initial);

        CardBuilder cardBuilder = new CardBuilder(CardName.WaterEnergy);
        cardBuilder.cardTypeBuilder.setTypeArtefact(this.effects);
        Card card = cardBuilder.build();

        assertThrows(NotPossibleToSummonInPhase.class, () -> phase.canSummon(card));
    }

    @Test
    public void testCanSummonPhaseMain() {
        IPhase phase = PhaseFactory.createNewPhase(PhaseType.Main);

        CardBuilder cardBuilder = new CardBuilder(CardName.WaterEnergy);
        cardBuilder.cardTypeBuilder.setTypeCreature(null, null, null);
        Card card = cardBuilder.build();

        phase.canSummon(card);
    }

    @Test
    public void testCanSummonPhaseMainAnArtefact() {
        IPhase phase = PhaseFactory.createNewPhase(PhaseType.Main);

        CardBuilder cardBuilder = new CardBuilder(CardName.WaterEnergy);
        cardBuilder.cardTypeBuilder.setTypeArtefact(this.effects);
        Card card = cardBuilder.build();

        phase.canSummon(card);
    }

    @Test
    public void testCanSummonPhaseMainAnArtefactAndCreature() {
        IPhase phase = PhaseFactory.createNewPhase(PhaseType.Main);

        CardBuilder cardBuilder = new CardBuilder(CardName.WaterEnergy);
        cardBuilder.cardTypeBuilder.setTypeArtefact(this.effects);
        cardBuilder.cardTypeBuilder.setTypeCreature(null, null, null);
        Card card = cardBuilder.build();

        phase.canSummon(card);
    }

    @Test
    public void testCantSummonPhaseMain() {
        IPhase phase = PhaseFactory.createNewPhase(PhaseType.Main);

        CardBuilder cardBuilder = new CardBuilder(CardName.WaterEnergy);
        cardBuilder.cardTypeBuilder.setTypeAction(null, effects);
        Card card = cardBuilder.build();

        phase.canSummon(card);
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
