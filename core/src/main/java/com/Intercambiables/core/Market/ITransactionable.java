package com.Intercambiables.core.Market;

import com.Intercambiables.core.User.User;

public interface ITransactionable {
    void removeFrom(User user);
    void addTo(User user);
}
