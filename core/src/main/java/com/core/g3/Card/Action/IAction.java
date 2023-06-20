package com.core.g3.Card.Action;

import com.core.g3.Card.Action.Exceptions.ActionNotUsableException;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.IAffectable;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;

import java.util.List;

public interface IAction {

    default OriginalAction apply(OriginalAction action, Player user, Player rival) {
        throw new ActionNotUsableException();
    }

    default OriginalAction apply(OriginalAction action, List<IAffectable> affected, Player user, Player rival) {
        throw new ActionNotUsableException();
    }
}
