package com.core.g3.Card.Type.Artefact;

import java.util.List;

import com.core.g3.Card.Effects.IEffect;
import com.core.g3.Card.Type.CardType;
import com.core.g3.Card.Type.ICardType;

public class CardTypeArtefact extends CardType {

    private final List<IEffect> effects;

    public CardTypeArtefact(List<IEffect> effects) {
        this.type = ICardType.CardType.Artefact;
        // TODO -> check effects.length
        this.effects = effects;
    }

}
