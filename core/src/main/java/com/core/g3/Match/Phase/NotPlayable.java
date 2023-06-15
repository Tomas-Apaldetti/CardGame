package com.core.g3.Match.Phase;

import com.core.g3.Match.Player.Player;

public class NotPlayable implements IPhase{
    @Override
    public Player activePlayer() {
        return null;
    }
}
