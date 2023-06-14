package com.core.tcg.driver.Adapter;

import com.core.g3.Card.CardName;
import com.core.g3.Match.Phase.Phase;
import com.core.g3.Match.Player.PlayerZone;
import com.core.g3.Match.Zone.ActiveZoneType;
import com.core.tcg.driver.DriverActiveZone;
import com.core.tcg.driver.DriverCardName;
import com.core.tcg.driver.DriverMatchSide;
import com.core.tcg.driver.DriverTurnPhase;

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
        for (DriverCardName card : cards) {
            names.add(CardName.valueOf(card.name()));
        }
        return names;
    }

    public static PlayerZone toPlayerZone(DriverMatchSide matchSide) {
        return PlayerZone.valueOf(matchSide.name());
    }

    public static DriverMatchSide toDriverMatchSide(PlayerZone playerZone) {
        return DriverMatchSide.valueOf(playerZone.name());
    }

    public static Phase toTurnPhase(DriverTurnPhase phase) {
        return Phase.valueOf(phase.name());
    }

    public static ActiveZoneType toActiveZoneType(DriverActiveZone zone) {
        if (zone == DriverActiveZone.Combat) {
            return ActiveZoneType.Combat;
        } else if (zone == DriverActiveZone.Reserve) {
            return ActiveZoneType.Reserve;
        } else {
            return ActiveZoneType.Artefacts;
        }
    }
}
