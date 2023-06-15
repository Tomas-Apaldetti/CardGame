package com.core.g3.Match.Phase;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import com.core.g3.Card.Action.AddEnergyAction;
import com.core.g3.Card.Artefact.AddEnergyArtefact;
import com.core.g3.Card.Artefact.IArtefactEffect;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Resources.EnergyType;
import org.junit.jupiter.api.Test;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;

public class PhaseTest {
//
//    @Test
//    public void testCanSummonPhaseInitial() {
//        IPhase phase = PhaseFactory.createNewPhase(PhaseType.Initial);
//
//        CardBuilder cardBuilder = new CardBuilder(CardName.WaterEnergy);
//        cardBuilder.cardTypeBuilder.setTypeArtefact(new AddEnergyArtefact(EnergyType.Fire, new Amount(10)));
//        Card card = cardBuilder.build();
//
//        assertThrows(NotPossibleToSummonInPhase.class, () -> phase.canSummon(card));
//    }
//
//    @Test
//    public void testCanSummonPhaseMain() {
//        IPhase phase = PhaseFactory.createNewPhase(PhaseType.Main);
//
//        CardBuilder cardBuilder = new CardBuilder(CardName.WaterEnergy);
//        cardBuilder.cardTypeBuilder.setTypeCreature(null, null, null);
//        Card card = cardBuilder.build();
//
//        phase.canSummon(card);
//    }
//
//    @Test
//    public void testCanSummonPhaseMainAnArtefact() {
//        IPhase phase = PhaseFactory.createNewPhase(PhaseType.Main);
//
//        CardBuilder cardBuilder = new CardBuilder(CardName.WaterEnergy);
//        cardBuilder.cardTypeBuilder.setTypeArtefact(new AddEnergyArtefact(EnergyType.Fire, new Amount(10)));
//        Card card = cardBuilder.build();
//
//        phase.canSummon(card);
//    }
//
//    @Test
//    public void testCanSummonPhaseMainAnArtefactAndCreature() {
//        IPhase phase = PhaseFactory.createNewPhase(PhaseType.Main);
//
//        CardBuilder cardBuilder = new CardBuilder(CardName.WaterEnergy);
//        cardBuilder.cardTypeBuilder.setTypeArtefact(new AddEnergyArtefact(EnergyType.Fire, new Amount(10)));
//        cardBuilder.cardTypeBuilder.setTypeCreature(null, null, null);
//        Card card = cardBuilder.build();
//
//        phase.canSummon(card);
//    }
//
//    @Test
//    public void testCantSummonPhaseMain() {
//        IPhase phase = PhaseFactory.createNewPhase(PhaseType.Main);
//
//        CardBuilder cardBuilder = new CardBuilder(CardName.WaterEnergy);
//
//        cardBuilder.cardTypeBuilder.setTypeAction(null, new AddEnergyAction(EnergyType.Fire, new Amount(10)));
//        Card card = cardBuilder.build();
//
//        phase.canSummon(card);
//    }

}
