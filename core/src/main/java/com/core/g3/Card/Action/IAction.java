package com.core.g3.Card.Action;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;

import java.util.List;

public interface IAction {

    OriginalAction apply(OriginalAction action, Player user, Player rival);

    OriginalAction apply(OriginalAction action, List<IAttackable> affected, Player user);
}
