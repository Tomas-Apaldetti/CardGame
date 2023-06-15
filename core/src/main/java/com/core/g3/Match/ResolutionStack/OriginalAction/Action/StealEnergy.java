package com.core.g3.Match.ResolutionStack.OriginalAction.Action;

import java.util.ArrayList;
import java.util.List;

import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.LingeringEffect.ILingeringEffect;
import com.core.g3.Match.ResolutionStack.LingeringEffect.OnInitialDrain;

public class StealEnergy implements IEffect {

    private final Amount value;
    private final Player user;

    public StealEnergy(Amount value, Player user) {
        this.value = value;
        this.user = user;
    }

    @Override
    public ArrayList<ILingeringEffect> apply() {
        ArrayList<ILingeringEffect> l = new ArrayList<ILingeringEffect>();
        l.add(new OnInitialDrain(this.value, this.user));
        return l;
    }

    @Override
    public List<ILingeringEffect> apply(Integer times) {
        ArrayList<ILingeringEffect> l = new ArrayList<ILingeringEffect>();
        l.add(new OnInitialDrain(new Amount(this.value.value() * times), this.user));
        return l;
    }
}
