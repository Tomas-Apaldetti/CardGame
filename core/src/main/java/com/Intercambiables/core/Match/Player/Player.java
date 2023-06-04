package com.Intercambiables.core.Match.Player;

import com.Intercambiables.core.Commons.Amount;
import com.Intercambiables.core.Commons.Exception.InvalidAmountException;
import com.Intercambiables.core.Deck.IDeck;
import com.Intercambiables.core.Match.IAccount;
import com.Intercambiables.core.Match.Player.Exception.InvalidEnergyTypeException;
import com.Intercambiables.core.Match.Player.HP.IHP;
import com.Intercambiables.core.Match.Player.HP.PlainHP;
import com.Intercambiables.core.Match.Player.Resources.Energy;
import com.Intercambiables.core.Match.Player.Resources.EnergyType;
import com.Intercambiables.core.Match.Player.Resources.IModifiableResource;
import com.Intercambiables.core.Match.Player.Resources.IResource;

import java.util.*;
import java.util.stream.Collectors;

public class Player {

    private final IAccount account;
    private IHP hp;
    private PlayerEnergies energies;

    public Player(IAccount account, Amount baseHp){
        this.account = account;
        this.hp = new PlainHP(baseHp);
    }
    public Player(IAccount account, IHP baseHp){
        this.account = account;
        this.hp = baseHp;
        this.energies = new PlayerEnergies();
    }


    public void receiveDamage(Amount value){
        this.hp = this.hp.receiveDamage(value);
    }

    public int currentHp(){
        return this.hp.getNumeric();
    }

    public IResource getEnergy(EnergyType energyType){
        return this.energies.getEnergy(energyType);
    }

    public Collection<IResource> getEnergies(){
        List<IResource> energies = this.energies.getEnergies()
                .stream()
                .map(e -> (IResource) e)
                .collect(Collectors.toList());
        return energies;
    }

    public void add(EnergyType energyType, Amount value){
        this.energies.add(energyType, value);
    }

    public void consume(Optional<EnergyType> energyType, Amount value){
        if(energyType.isPresent()){
            this.energies.consume(energyType.get(), value);
            return;
        }
        this.energies.consumeAny(value);
    }
}
