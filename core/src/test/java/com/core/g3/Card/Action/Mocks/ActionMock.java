package com.core.g3.Card.Action.Mocks;

import com.core.g3.Card.Action.IAction;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;
import com.core.g3.Mock.Exceptions.ExpectedException;

public class ActionMock implements IAction {

    @Override
    public OriginalAction apply(OriginalAction action, Player user, Player rival) {
        throw new ExpectedException();
    }
}
