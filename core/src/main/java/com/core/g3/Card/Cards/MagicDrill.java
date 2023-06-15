package com.core.g3.Card.Cards;

import java.util.ArrayList;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;
import com.core.g3.Card.Attack.IAttack;
import com.core.g3.Card.Attack.SimpleAttack;
import com.core.g3.Card.Reaction.IReaction;
import com.core.g3.Card.Reaction.IgnoreDefensesReaction;
import com.core.g3.Card.Type.Creature.Attribute;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Resources.EnergyType;

public class MagicDrill {
    public static Card create() {
        CardBuilder builder = new CardBuilder(CardName.MagicBarrier);

        builder.invocationCost.addEnergyCost(EnergyType.Fire, new Amount(1));


        Amount baseHp = new Amount(3);
        ArrayList<Attribute> creatureAttributes = new ArrayList<>();
        creatureAttributes.add(Attribute.Magic);
        creatureAttributes.add(Attribute.Metal);
        ArrayList<IAttack> creatureAttacks = new ArrayList<>();
        creatureAttacks.add(new SimpleAttack(new Amount(5)));
        builder.cardTypeBuilder.setTypeCreature(baseHp, creatureAttributes, creatureAttacks);

        IReaction ignoreDefensesReaction = new IgnoreDefensesReaction();
        builder.cardTypeBuilder.setTypeReaction(ignoreDefensesReaction);
        
        return builder.build();
    }
}
