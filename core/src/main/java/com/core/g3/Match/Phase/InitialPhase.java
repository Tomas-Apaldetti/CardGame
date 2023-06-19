package com.core.g3.Match.Phase;

import com.core.g3.Match.Match;
import com.core.g3.Match.Player.Player;

public class InitialPhase implements IPhase {
    private final Player current;
    private final Player rival;
    private final Match match;

    public InitialPhase(Player current, Player rival, Match match) {
        this.current = current;
        this.rival = rival;
        this.match = match;
    }

    @Override
    public Player activePlayer() {
        return this.current;
    }

    @Override
    public IPhase next() {
        return new MainPhase(this.current, this.rival, this.match);
    }

    @Override
    public void initialEffects() {
        this.match.getLingeringEffects().forEach(l -> l.apply(this, this.current));
        this.match.gamemode().onInitialPhase(this.current, this.rival);
        this.current.resetCards();
    }

    @Override
    public boolean coincide(Player desiredCurrentPlayer, PhaseType phase) {
        return this.current.equals(desiredCurrentPlayer) && PhaseType.Initial == phase;
    }

    @Override
    public PhaseType getPhaseType() {
        return PhaseType.Initial;
    }
}
