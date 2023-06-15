package com.core.g3.Card.Cards;

import java.util.Optional;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;
import com.core.g3.Card.Action.DrawCardAction;
import com.core.g3.Card.Action.IAction;
import com.core.g3.Card.Cost.CostEnergy;
import com.core.g3.Card.Cost.HandDiscardCost;
import com.core.g3.Card.Cost.ICost;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Resources.EnergyType;

public class Recycle {
    public static Card create() {
        CardBuilder builder = new CardBuilder(CardName.Recycle);

        ICost costEnergy = new HandDiscardCost(new CostEnergy(Optional.of(EnergyType.Plant), new Amount(1)));
        IAction drawCardAction = new DrawCardAction(Optional.of(new Amount(3)));
        builder.cardTypeBuilder.setTypeAction(costEnergy, drawCardAction);        

        return builder.build();
    }
}