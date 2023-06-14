package com.core.g3.Match.Phase;

import java.util.List;

import com.core.g3.Card.Type.ICardType;
import com.core.g3.Card.Type.CardTypeName;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.Phase.Exceptions.NotPossibleToAttack;
import com.core.g3.Match.Phase.Exceptions.NotPossibleToSummonInPhase;

public class MainPhase implements IPhase {

    @Override
    public void canSummon(ICard card) {
        List<CardTypeName> cardType = card.getTypes();
        cardType.stream()
                .filter(type -> type == CardTypeName.Creature || type == CardTypeName.Artefact
                        || type == CardTypeName.Action)
                .findFirst().orElseThrow(() -> new NotPossibleToSummonInPhase(PhaseType.Main));
    }

    @Override
    public void canAttack() {
        throw new NotPossibleToAttack();
    }
}
