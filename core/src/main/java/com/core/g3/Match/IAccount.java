package com.core.g3.Match;

import com.core.g3.Commons.Amount;

public interface IAccount {
    String getUserName();

    void credit(Amount value);
}
