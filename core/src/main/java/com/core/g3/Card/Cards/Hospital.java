package com.core.g3.Card.Cards;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;
import com.core.g3.Card.Artifact.HealCardArtifact;
import com.core.g3.Card.Artifact.IArtifactEffect;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Resources.EnergyType;

public class Hospital {
    public static Card create() {
        CardBuilder builder = new CardBuilder(CardName.Hospital);

        builder.invocationCost.addEnergyCost(EnergyType.Plant, new Amount(2));

        IArtifactEffect healEffect = new HealCardArtifact(new Amount(2));
        builder.cardTypeBuilder.setTypeArtifact(healEffect);

        return builder.build();
    }
}
