package com.Intercambiables.core.Market;

import com.Intercambiables.core.User.User;

public interface ITransactionable {

    void removeFrom(ISeller seller);
    void removeFrom(User user);
    void addTo(IBuyer buyer);
    void addTo(User user);
}
