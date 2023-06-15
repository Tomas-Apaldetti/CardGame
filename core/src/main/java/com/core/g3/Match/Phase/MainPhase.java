package com.core.g3.Match.Phase;

import com.core.g3.Deck.ICard;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Zone.ActiveZoneType;

public class MainPhase implements IPhase {
    @Override
    public ICard summon(ICard card, ActiveZoneType zone, Player player) {
        player.summonInZone(card, zone);
        return card;
    }
}
