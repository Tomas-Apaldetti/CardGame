package com.core.g3.Match.ResolutionStack.OriginalAction;

import com.core.g3.Card.CardName;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.CardInGame.CardInGame;

public interface IOriginal {

    boolean isSource(CardInGame questioner);

    boolean isObjective(CardInGame questioner);

    boolean is(ActionType type);

    void setMaxDamage(Amount value);

    void duplicate();

    boolean sourceIs(CardName cardName);

    void cancel();

    void discardSource();
}
