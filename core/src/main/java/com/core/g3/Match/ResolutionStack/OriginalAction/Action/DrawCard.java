package com.core.g3.Match.ResolutionStack.OriginalAction.Action;

import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Player;

import java.util.stream.IntStream;

public class DrawCard implements IEffect{
    private final Amount value;
    private final Player user;

    public DrawCard(Amount value, Player who){
        this.value = value;
        this.user = who;
    }

    public void apply(){
        IntStream.range(0, this.value.value())
                .forEach(i-> this.user.drawCard());
    }
}
