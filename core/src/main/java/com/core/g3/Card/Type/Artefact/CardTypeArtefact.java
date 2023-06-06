package com.core.g3.Card.Type.Artefact;

import java.util.Arrays;
import java.util.List;

import com.core.g3.Card.Effects.IEffect;
import com.core.g3.Card.Type.CardType;
import com.core.g3.Card.Type.ICardType;
import com.core.g3.Card.Type.Zone;

public class CardTypeArtefact extends CardType {

    private final List<IEffect> effects;
    protected List<Zone> allowedZones;

    public CardTypeArtefact(List<IEffect> effects) {
        assertEffects(effects);
        this.type = ICardType.CardType.Artefact;
        this.effects = effects;
        this.allowedZones = Arrays.asList(Zone.Artefacts);
    }

    public CardTypeArtefact(List<IEffect> effects, List<Zone> allowedZones) {
        assertEffects(effects);
        this.type = ICardType.CardType.Artefact;
        this.effects = effects;
        this.allowedZones = allowedZones;
    }
}
