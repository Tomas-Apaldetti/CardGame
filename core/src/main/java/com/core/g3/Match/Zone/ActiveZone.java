package com.core.g3.Match.Zone;

import com.core.g3.Commons.Amount;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Zone.Exceptions.CardLimitReachedException;

import java.util.ArrayList;
import java.util.List;

public class ActiveZone {

    private ActiveZoneType zoneType;
    private Amount limit;
    private Amount cardsSize;
    private List<ICard> cards;

    public ActiveZone(ActiveZoneType zoneType, Amount limit) {
        this.zoneType = zoneType;
        this.limit = limit;
        this.cardsSize = new Amount(0);
        this.cards = new ArrayList<>();
    }

    public void addCard(ICard card, Player player) {
        Amount size = card.summonIn(this.zoneType);

        size.add(this.cardsSize);
        if (size.gt(this.limit)) {
            throw new CardLimitReachedException();
        }

        player.pay(card);

        this.cardsSize = size;
        this.cards.add(card);
    }
}
