package com.core.g3.Card.Cards;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;
import com.core.g3.Card.Action.IAction;
import com.core.g3.Card.Action.SacrificeCreatureAction;
import com.core.g3.Card.Cost.NullInvocationCost;
import com.core.g3.Commons.Amount;

public class Sacrifice {
    public static Card create() {
        CardBuilder builder = new CardBuilder(CardName.Sacrifice);

        IAction action = new SacrificeCreatureAction(new Amount(3));
        builder.cardTypeBuilder.setTypeAction(new NullInvocationCost(), action);

        return builder.build();
    }
}
