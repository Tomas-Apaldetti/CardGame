package com.core.g3.Match.ResolutionStack.OriginalAction.Action;

import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.LingeringEffect.ILingeringEffect;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class DrawCard implements IEffect {
    private final Amount value;
    private final Player user;

    public DrawCard(Amount value, Player who) {
        this.value = value;
        this.user = who;
    }

    public List<ILingeringEffect> apply() {
        IntStream.range(0, this.value.value())
                .forEach(i -> this.user.drawCard());
        return new ArrayList<>();
    }

    @Override
    public List<ILingeringEffect> apply(Integer times) {
        IntStream.range(0, this.value.value() * times)
                .forEach(i -> this.user.drawCard());
        return new ArrayList<>();
    }
}
