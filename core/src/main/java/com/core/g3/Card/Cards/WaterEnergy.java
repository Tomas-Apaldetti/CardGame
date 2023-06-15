package com.core.g3.Card.Cards;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;
import com.core.g3.Card.Artifact.AddEnergyArtifact;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Resources.EnergyType;

public class WaterEnergy {
    public static Card create() {
        CardBuilder builder = new CardBuilder(CardName.WaterEnergy);
        AddEnergyArtifact waterEffect = new AddEnergyArtifact(EnergyType.Water, new Amount(1));
        builder.cardTypeBuilder.setTypeArtifact(waterEffect);
        builder.setSummonableSpace(new Amount(0));
        return builder.build();
    }
}
