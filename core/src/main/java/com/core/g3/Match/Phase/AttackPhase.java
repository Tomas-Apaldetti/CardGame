package com.core.g3.Match.Phase;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Player.Player;

public class AttackPhase implements IPhase {

    private final Player current;
    private final Player rival;

    public AttackPhase(Player current, Player rival) {
        this.current = current;
        this.rival = rival;
    }

    public void attack(CardInGame card, Amount index, IAttackable attackable) {
        card.attack(attackable, this.current, this.rival, index);
    }

    @Override
    public Player activePlayer() {
        return this.current;
    }
}
