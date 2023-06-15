package com.core.g3.Card.Cards;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;

public class Treason {
    public static Card create() {
        CardBuilder builder = new CardBuilder(CardName.Treason);

        // ICost costEnergy = new CostEnergy(Optional.of(EnergyType.Water), new
        // Amount(1),
        // Optional.of(new CostEnergy(Optional.of(EnergyType.Plant), new Amount(1)))
        // );
        // TODO: Efecto: Selecciona una carta en las zonas activas de cualquier jugador.
        // Transfiere dicha carta a la misma zona del jugador opuesto.
        // builder.cardTypeBuilder.setTypeReaction(costEnergy);

        return builder.build();
    }
}
