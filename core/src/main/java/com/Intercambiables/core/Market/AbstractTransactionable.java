package com.Intercambiables.core.Market;

import com.Intercambiables.core.User.User;

public abstract class AbstractTransactionable implements ITransactionable{


    @Override
    public void removeFrom(ISeller seller) {
        seller.removeItem(this);
    }

    @Override
    public void removeFrom(User user) {
        user.removeItem(this);
    }

    @Override
    public void addTo(IBuyer buyer) {
        buyer.addItem(this);
    }

    @Override
    public void addTo(User user) {
        user.addItem(this);
    }
}
