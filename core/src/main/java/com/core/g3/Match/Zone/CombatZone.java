package com.core.g3.Match.Zone;


import com.core.g3.Deck.ICard;
import com.core.g3.Match.GameMode.GameMode;
import com.core.g3.Match.Zone.Exceptions.CardLimitReachException;

import java.util.List;


public class CombatZone implements IZone {
    private int limit;
    private List<ICard> cards;

    CombatZone(GameMode mode) {
        this.limit = mode.getCombatZoneLimit();
    }

    public void addCard(ICard card) {
        if (this.cards.size() + 1 > this.limit) {
            throw new CardLimitReachException();
        }
        this.cards.add(card);
    }
}
