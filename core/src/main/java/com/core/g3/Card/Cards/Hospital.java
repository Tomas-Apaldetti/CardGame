package com.core.g3.Card.Cards;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;
import com.core.g3.Card.Artefact.HealCardArtefact;
import com.core.g3.Card.Artefact.IArtefactEffect;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Resources.EnergyType;

public class Hospital {
    public static Card create() {
        CardBuilder builder = new CardBuilder(CardName.Hospital);

        builder.invocationCost.addEnergyCost(EnergyType.Plant, new Amount(2));

        IArtefactEffect healEffect = new HealCardArtefact(new Amount(2));
        builder.cardTypeBuilder.setTypeArtefact(healEffect);

        return builder.build();
    }
}
