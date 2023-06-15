package com.core.g3.Card.Cards;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;
import com.core.g3.Card.Artefact.AddEnergyArtefact;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Resources.EnergyType;

public class WaterEnergy {
    public static Card create() {
        CardBuilder builder = new CardBuilder(CardName.WaterEnergy);
        AddEnergyArtefact waterEffect = new AddEnergyArtefact(EnergyType.Water, new Amount(1));
        builder.cardTypeBuilder.setTypeArtefact(waterEffect);
        builder.setSummonableSpace(new Amount(0));
        return builder.build();
    }
}
