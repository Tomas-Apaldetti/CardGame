package com.core.g3.Match.Phase;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Match;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;
import com.core.g3.Match.ResolutionStack.ResolutionStack;

public class AttackPhase implements IPhase {

    private final Player current;
    private final Player rival;
    private final Match match;

    public AttackPhase(Player current, Player rival, Match match) {
        this.current = current;
        this.rival = rival;
        this.match = match;
    }

    public IPhase attack(CardInGame card, Amount index, IAttackable attackable) {
        OriginalAction og = card.attack(attackable, this.current, this.rival, index);
        ResolutionStack rStack = new ResolutionStack(og);
        return new ReactionPhase(this.current, this.rival, rStack, this, this.match);
    }

    @Override
    public Player activePlayer() {
        return this.current;
    }

    @Override
    public IPhase next() {
        return new EndPhase(this.current, this.rival, this.match);
    }

    @Override
    public boolean coincide(Player desiredCurrentPlayer, PhaseType phase) {
        return this.current.equals(desiredCurrentPlayer) && PhaseType.Attack == phase;
    }

    @Override
    public PhaseType getPhaseType() {
        return PhaseType.Attack;
    }
}
