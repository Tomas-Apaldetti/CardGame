package com.core.g3.Card.Type.Artefact;

import java.util.Arrays;
import java.util.List;

import com.core.g3.Card.Effects.IEffect;
import com.core.g3.Card.Type.CardType;
import com.core.g3.Card.Type.ICardType;
import com.core.g3.Match.Zone.ActiveZoneType;

public class CardTypeArtefact extends CardType {

    private final List<IEffect> effects;
    protected List<ActiveZoneType> allowedZones;

    public CardTypeArtefact(List<IEffect> effects) {
        super(ICardType.CardType.Artefact, Arrays.asList(ActiveZoneType.Artefacts));
        assertEffects(effects);
        this.effects = effects;
    }

    public CardTypeArtefact(List<IEffect> effects, List<ActiveZoneType> allowedZones) {
        super(ICardType.CardType.Artefact, allowedZones);
        assertEffects(effects);
        this.effects = effects;
    }
}
