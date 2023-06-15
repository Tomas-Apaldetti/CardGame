package com.core.g3.Match.Phase;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Match;
import com.core.g3.Match.Player.Player;

public class AttackPhase implements IPhase {

    private final Player current;
    private final Player rival;
    private final Match match;

    public AttackPhase(Player current, Player rival, Match match) {
        this.current = current;
        this.rival = rival;
        this.match = match;
    }

    public void attack(CardInGame card, Amount index, IAttackable attackable) {
        card.attack(attackable, this.current, this.rival, index);
    }

    @Override
    public Player activePlayer() {
        return this.current;
    }
}
