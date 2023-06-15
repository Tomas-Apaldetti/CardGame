package com.core.tcg.driver.Adapter;

import com.core.g3.Card.CardName;
import com.core.g3.Match.Phase.PhaseType;
import com.core.g3.Match.Player.PlayerZone;
import com.core.g3.Match.Player.Resources.EnergyType;
import com.core.g3.Match.Zone.ActiveZoneType;
import com.core.tcg.driver.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public static Optional<PlayerZone> toOptionalPlayerZone(Optional<DriverMatchSide> matchSide) {
        if(matchSide.isPresent()) {
            return Optional.of(PlayerZone.valueOf(matchSide.get().name()));
        }
        return Optional.empty();
    }

    public static DriverMatchSide toDriverMatchSide(PlayerZone playerZone) {
        return DriverMatchSide.valueOf(playerZone.name());
    }

    public static Optional<DriverMatchSide> toOptionalDriverMatchSide(Optional<PlayerZone> playerZone) {
        if(playerZone.isPresent()) {
            return Optional.of(DriverMatchSide.valueOf(playerZone.get().name()));
        }
        return Optional.empty();
    }

    public static PhaseType toTurnPhase(DriverTurnPhase phase) {
        return PhaseType.valueOf(phase.name());
    }

    public static ActiveZoneType toActiveZoneType(DriverActiveZone zone) {
        if (zone == DriverActiveZone.Combat) {
            return ActiveZoneType.Combat;
        } else if (zone == DriverActiveZone.Reserve) {
            return ActiveZoneType.Reserve;
        } else {
            return ActiveZoneType.Artifacts;
        }
    }

    public static EnergyType toEnergyType(DriverEnergyType energyType) {
        return EnergyType.valueOf(energyType.name());
    }
}
