package com.core.g3.Card.Action;

import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Zone.ActiveZone;
import com.core.g3.Match.Zone.ActiveZoneType;

public interface IMovable {

    boolean canBeMoved();

    ActiveZoneType currentPlace();

    Player getOwner();

    void transferTo(ActiveZone zone);
}
