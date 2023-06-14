package com.core.g3.Card.Action;

import java.util.List;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;

public interface IAction {

    OriginalAction apply(OriginalAction action, Player user, Player rival);

    default OriginalAction apply(OriginalAction action, List<IAttackable> victims, Player user, Player rival) {
        return this.apply(action, user, rival);
    }
}
