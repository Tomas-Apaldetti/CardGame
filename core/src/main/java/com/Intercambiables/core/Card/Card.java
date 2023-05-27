package com.Intercambiables.core.Card;

import com.Intercambiables.core.Market.AbstractTransactionable;
import com.Intercambiables.core.Market.IBuyer;
import com.Intercambiables.core.Market.ISeller;
import com.Intercambiables.core.User.User;

public class Card extends AbstractTransactionable{

    public String name;
    public Card(String name){
        this.name = name;
    }

    @Override
    public void removeFrom(User user){
        user.removeItem(this);
    }

    @Override
    public void addTo(User user) {
        user.addItem(this);
    }
}
