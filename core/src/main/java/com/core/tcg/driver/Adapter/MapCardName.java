package com.core.tcg.driver.Adapter;


import com.core.g3.Card.CardName;
import com.core.tcg.driver.DriverCardName;

public class MapCardName {

    public static CardName from(DriverCardName cardName) {
        return CardName.valueOf(cardName.name());
    }

}
