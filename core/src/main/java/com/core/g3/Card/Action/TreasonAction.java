package com.core.g3.Card.Action;

import com.core.g3.Card.Action.Exceptions.ActionNotUsableException;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.IAffectable;
import com.core.g3.Match.ResolutionStack.OriginalAction.Action.TransferToRival;
import com.core.g3.Match.ResolutionStack.OriginalAction.ActionType;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;

import java.util.List;

public class TreasonAction implements IAction{


    public OriginalAction apply(OriginalAction action, List<IAffectable> affected, Player user, Player rival) {
        affected.forEach(a -> {
            if(!a.canBeMoved()){
                throw new ActionNotUsableException();
            }
        });

        action.setType(ActionType.Action);
        action.addEffect(new TransferToRival(affected, user, rival));

        return action;
    }
}
