package com.core.g3.Card.Cards;

import java.util.Optional;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;
import com.core.g3.Card.Action.IAction;
import com.core.g3.Card.Cost.CostEnergy;
import com.core.g3.Card.Cost.ICost;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Resources.EnergyType;

public class Sacrifice {
    public static Card create() {
        CardBuilder builder = new CardBuilder(CardName.Sacrifice);

        // TODO ADD: Efecto: Obten tres veces la energía que lista en su costo de
        // invocación
        ICost costEnergy = new CostEnergy(Optional.of(EnergyType.Fire), new Amount(1));
        // IAction action = new SacrificeCreatureAction();
        builder.cardTypeBuilder.setTypeAction(costEnergy, null);

        return builder.build();
    }
}
