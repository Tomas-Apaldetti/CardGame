package com.core.Match;

import com.core.Commons.Amount;

public interface IAccount {
    String getUserName();

    void credit(Amount value);
}
