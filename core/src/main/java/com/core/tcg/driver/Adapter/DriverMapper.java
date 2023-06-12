package com.core.tcg.driver.Adapter;


import com.core.g3.Card.CardName;
import com.core.g3.Match.Player.PlayerZone;
import com.core.tcg.driver.DriverCardName;
import com.core.tcg.driver.DriverMatchSide;

import java.util.ArrayList;
import java.util.List;

public class DriverMapper {

    public static CardName toCardName(DriverCardName cardName) {
        return CardName.valueOf(cardName.name());
    }

    public static DriverCardName toDriverCardName(CardName cardName) {
        return DriverCardName.valueOf(cardName.name());
    }

    public static List<CardName> toDriverCardName(List<DriverCardName> cards) {
        List<CardName> names = new ArrayList<>();
        for (DriverCardName card: cards){
            names.add(CardName.valueOf(card.name()));
        }
        return names;
    }

    public static PlayerZone toPlayerZone(DriverMatchSide matchSide) {
        return PlayerZone.valueOf(matchSide.name());
    }
}
