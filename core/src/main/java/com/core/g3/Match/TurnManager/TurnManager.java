package com.core.g3.Match.TurnManager;

import java.util.Optional;

import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Player.PlayerZone;
import com.core.g3.Match.TurnManager.Exceptions.TurnUnstartedException;

public class TurnManager {

    private final Player bluePlayer;
    private final Player greenPlayer;
    private Optional<PlayerZone> side;

    public TurnManager(Player bluePlayer, Player greenPlayer) {
        this.bluePlayer = bluePlayer;
        this.greenPlayer = greenPlayer;
    }

    public void assertSide() {
        if (!this.side.isPresent()) {
            throw new TurnUnstartedException();
        }
    }

    public Player getPlayer() {
        assertSide();
        return this.side.get() == PlayerZone.Blue ? this.bluePlayer : this.greenPlayer;
    }

    public Player getRival() {
        assertSide();
        return this.side.get() == PlayerZone.Blue ? this.greenPlayer : this.bluePlayer;
    }

    public void setSide(PlayerZone side) {
        this.side = Optional.of(side);
    }

    public Player getPlayerFrom(PlayerZone playerZone) {
        return playerZone == PlayerZone.Blue ? this.bluePlayer : this.greenPlayer;
    }

    public Player getRivalTo(Player to){
        if(this.bluePlayer.equals(to)){
            return this.greenPlayer;
        }
        return this.bluePlayer;
    }
}
