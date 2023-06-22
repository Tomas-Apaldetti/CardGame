package com.core.g3.Match.ResolutionStack.OriginalAction.Action;

import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.IAffectable;
import com.core.g3.Match.ResolutionStack.LingeringEffect.ILingeringEffect;

import java.util.ArrayList;
import java.util.List;

public class TransferToRival implements IEffect {
    private final List<IAffectable> toMove;
    private final Player one;
    private final Player other;

    public TransferToRival(List<IAffectable> affected, Player user, Player rival) {
        this.toMove = affected;
        this.one = user;
        this.other = rival;
    }

    private Player getRival(Player player){
        return player.equals(this.one) ? this.other : this.one;
    }

    @Override
    public List<ILingeringEffect> apply() {

        this.toMove.forEach(m-> {
            if(!m.canBeMoved()) return;

            Player owner = m.getOwner();
            Player other = this.getRival(owner);
            m.transferTo(
              other.getZone(m.currentPlace())
            );
        });

        return new ArrayList<>();
    }

    @Override
    public List<ILingeringEffect> apply(Integer times) {
        return this.apply();
    }
}
