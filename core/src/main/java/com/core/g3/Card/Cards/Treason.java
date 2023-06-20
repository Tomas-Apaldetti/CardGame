package com.core.g3.Card.Cards;

import com.core.g3.Card.Action.TreasonAction;
import com.core.g3.Card.Card;
import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;
import com.core.g3.Card.InvocationCostBuilder;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Resources.EnergyType;

public class Treason {
    public static Card create() {
        CardBuilder builder = new CardBuilder(CardName.Treason);

        InvocationCostBuilder cost = new InvocationCostBuilder();
        cost.addEnergyCost(EnergyType.Water, new Amount(1));
        cost.addEnergyCost(EnergyType.Plant, new Amount(1));

        builder.cardTypeBuilder.setTypeAction(cost.getCost(), new TreasonAction());

        return builder.build();
    }
}
