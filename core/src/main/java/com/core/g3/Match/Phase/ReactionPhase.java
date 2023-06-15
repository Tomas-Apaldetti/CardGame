package com.core.g3.Match.Phase;

import java.util.Optional;

import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.ResolutionStack;
import com.core.g3.Match.Zone.ActiveZone;

public class ReactionPhase implements IPhase {

    private Player current;
    private Player rival;
    private ResolutionStack rstak;
    private MainPhase previous;
    private Optional<ActiveZone> zone;
    private Player activePlayer;

    public ReactionPhase(Player current, Player rival, ResolutionStack rstack, MainPhase previous,
            ActiveZone zone) {
        this.current = current;
        this.rival = rival;
        this.activePlayer = rival;
        this.rstak = rstack;
        this.previous = previous;
        this.zone = Optional.of(zone);
    }

    public ReactionPhase(Player current, Player rival, ResolutionStack rStack, MainPhase previous) {
        this.current = current;
        this.rival = rival;
        this.activePlayer = rival;
        this.rstak = rStack;
        this.previous = previous;
        this.zone = Optional.empty();
    }

    void switchPlayers() {
        this.activePlayer = this.activePlayer.equals(current) ? this.rival : this.current;
    }

    void acceptReaction(CardInGame cig) {
        cig.reaction(this.current, this.rival, this.rstak);
        switchPlayers();
    }

    @Override
    public Player activePlayer() {
        return this.activePlayer;
    }

}
