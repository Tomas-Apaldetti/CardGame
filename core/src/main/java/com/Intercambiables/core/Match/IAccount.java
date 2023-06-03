package com.Intercambiables.core.Match;

import com.Intercambiables.core.Commons.Amount;

public interface IAccount {
    String getUserName();

    void credit(Amount value);
}
