package com.core.g3.Card.Cards;

import java.util.ArrayList;
import java.util.Optional;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;
import com.core.g3.Card.Artifact.DrawCardArtifact;
import com.core.g3.Card.Attack.AddEnergyAttack;
import com.core.g3.Card.Attack.DrawCardAttack;
import com.core.g3.Card.Attack.IAttack;
import com.core.g3.Card.Attack.SimpleAttack;
import com.core.g3.Card.Type.Creature.Attribute;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Resources.EnergyType;

public class Alchemist {
        public static Card create() {
                CardBuilder builder = new CardBuilder(CardName.Alchemist);

                builder.invocationCost.addEnergyCost(EnergyType.Fire, new Amount(1));
                builder.invocationCost.addEnergyCost(EnergyType.Plant, new Amount(1));

                builder.setShouldCount(true);

                ArrayList<Attribute> creatureAttributes = new ArrayList<>();
                creatureAttributes.add(Attribute.Science);

                ArrayList<IAttack> creatureAttacks = new ArrayList<>();
                creatureAttacks.add(new DrawCardAttack(new SimpleAttack(new Amount(1))));
                creatureAttacks.add(
                                new AddEnergyAttack(EnergyType.Fire, new Amount(1), new SimpleAttack(new Amount(1))));

                builder.cardTypeBuilder.setTypeCreature(new Amount(3), creatureAttributes, creatureAttacks);

                DrawCardArtifact drawCardArtifact = new DrawCardArtifact(Optional.of(new Amount(1)));
                builder.cardTypeBuilder.setTypeArtifact(drawCardArtifact);

                return builder.build();
        }
}
