package com.Intercambiables.core.Match.Player.HP;

import com.Intercambiables.core.Commons.Amount;

public interface IHP {

    IHP receiveDamage(Amount value);

    boolean isDepleted();

    int getNumeric();
}
