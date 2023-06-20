package com.core.g3.Card.Artifact;

import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.IAffectable;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;

import java.util.List;

public interface IArtifactEffect {
    OriginalAction apply(OriginalAction action, Player user, Player rival);

    default OriginalAction apply(OriginalAction action, List<IAffectable> affected, Player user, Player rival) {
        return this.apply(action, user, rival);
    }
}
