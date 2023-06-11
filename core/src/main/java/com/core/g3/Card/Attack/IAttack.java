package com.core.g3.Card.Attack;

import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;

public interface IAttack {
    OriginalAction attack(OriginalAction action, IAttackable victim, Player user, Player rival);
}
