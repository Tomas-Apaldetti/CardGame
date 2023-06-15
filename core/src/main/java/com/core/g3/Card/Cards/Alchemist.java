package com.core.g3.Card.Cards;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;
import com.core.g3.Card.Artefact.AddEnergyArtefact;
import com.core.g3.Card.Artefact.DrawCardArtefact;
import com.core.g3.Card.Attack.AddEnergyAttack;
import com.core.g3.Card.Attack.DrawCardAttack;
import com.core.g3.Card.Attack.IAttack;
import com.core.g3.Card.Type.Creature.Attribute;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Resources.EnergyType;

public class Alchemist {
    public static Card create() {
        CardBuilder builder = new CardBuilder(CardName.Alchemist);
        
        builder.invocationCost.addEnergyCost(EnergyType.Water, new Amount(1));
        builder.invocationCost.addEnergyCost(EnergyType.Plant, new Amount(1));

        ArrayList<Attribute> creatureAttributes = new ArrayList<>();
        creatureAttributes.add(Attribute.Science);

        ArrayList<IAttack> creatureAttacks = new ArrayList<>();
        creatureAttacks.add(new DrawCardAttack()); // TODO: ver como pasarle el amount de daño que causa y cantidad de cartas
        creatureAttacks.add(new AddEnergyAttack(EnergyType.Fire, new Amount(1))); // TODO: agregar cuanto daño hace

        builder.cardTypeBuilder.setTypeCreature(new Amount(3), creatureAttributes, creatureAttacks);

        DrawCardArtefact drawCardArtifact = new DrawCardArtefact(Optional.of(new Amount(1)));
        builder.cardTypeBuilder.setTypeArtefact(drawCardArtifact);
        
        builder.setSummonableSpace(new Amount(0));
        return builder.build();
    }
}
