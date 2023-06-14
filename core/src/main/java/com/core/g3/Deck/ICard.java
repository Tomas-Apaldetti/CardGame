package com.core.g3.Deck;

import java.util.List;

import com.core.g3.Card.CardName;
import com.core.g3.Card.Type.ICardType;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Zone.ActiveZoneType;

public interface ICard {

    public CardName getName();

    public boolean shouldCountAgainstNameLimit();

    public int getPrice();

    public Amount summonIn(ActiveZoneType artifacts);

    public void applySummonCost(Player player);

    List<ActiveZoneType> getAllowableZones();

    public List<ICardType> getTypes();
}
