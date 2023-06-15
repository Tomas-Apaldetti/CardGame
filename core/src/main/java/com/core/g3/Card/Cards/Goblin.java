package com.core.g3.Card.Cards;

import java.util.ArrayList;
import com.core.g3.Card.Card;
import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;
import com.core.g3.Card.Attack.IAttack;
import com.core.g3.Card.Attack.SimpleAttack;
import com.core.g3.Card.Type.Creature.Attribute;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Resources.EnergyType;

public class Goblin {
    public static Card create() {
        CardBuilder builder = new CardBuilder(CardName.Goblin);

        builder.invocationCost.addEnergyCost(EnergyType.Plant, new Amount(1));

        Amount baseHp = new Amount(5);
        ArrayList<Attribute> creatureAttributes = new ArrayList<>();
        ArrayList<IAttack> attacks = new ArrayList<>();
        IAttack goblinAttack = new SimpleAttack(new Amount(1));
        attacks.add(goblinAttack);
        builder.cardTypeBuilder.setTypeCreature(baseHp, creatureAttributes, attacks);

        return builder.build();
    }
}
