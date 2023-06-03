package com.Intercambiables.core.Match.Player;

import com.Intercambiables.core.Commons.Amount;
import com.Intercambiables.core.Match.IAccount;
import com.Intercambiables.core.Match.Player.HP.IHP;
import com.Intercambiables.core.Match.Player.HP.PlainHP;

public class Player {

    private final IAccount account;
    private IHP hp;
    public Player(IAccount account, IHP baseHp){
        this.account = account;
        this.hp = baseHp;
    }

    public Player(IAccount account, Amount baseHp){
        this.account = account;
        this.hp = new PlainHP(baseHp);
    }
    public void receiveDamage(Amount value){
        this.hp = this.hp.receiveDamage(value);
    }

    public int currentHp(){
        return this.hp.getNumeric();
    }
}
