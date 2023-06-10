package com.core.g3.Match.Zone;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;

import com.core.g3.Card.Card;
import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;
import com.core.g3.Card.Effects.IEffect;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Player;

public class ActiveZoneTest {

    private final List<IEffect> effects = new TestEffects().effects;

    @Test
    public void addCard() {
        ActiveZone artefactsZone = new ActiveZone(ActiveZoneType.Artefacts, new Amount(3));

        CardBuilder builder = new CardBuilder(CardName.Antimagic);
        builder.cardTypeBuilder.setTypeArtefact(this.effects);
        builder.setSummonableSpace(new Amount(0));
        Card antimagic = builder.build();

        Player player = new Player(null, null, null, null,
                null, null);

        artefactsZone.addCard(antimagic, player);
    }

    private class TestEffects {
        public List<IEffect> effects;

        public TestEffects() {
            this.effects = new ArrayList<IEffect>();
            this.effects.add(new TestEffect());
        }
    }

    private class TestEffect implements IEffect {
    }
}
