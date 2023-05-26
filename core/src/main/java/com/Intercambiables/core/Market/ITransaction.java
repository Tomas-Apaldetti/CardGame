package com.Intercambiables.core.Market;

import com.Intercambiables.core.User.User;

public interface ITransaction {

    TransactionStatus apply(IBuyer buyer);
}
