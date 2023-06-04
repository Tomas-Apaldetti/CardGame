package com.Intercambiables.core.Match.Player;

import com.Intercambiables.core.Commons.Amount;
import com.Intercambiables.core.Match.IAccount;
import com.Intercambiables.core.Match.Player.MatchEndCondition.IMatchEndCondition;
import com.Intercambiables.core.Match.Player.MatchEndCondition.PlainHP;
import com.Intercambiables.core.Match.Player.Resources.EnergyType;
import com.Intercambiables.core.Match.Player.Resources.IResource;

import java.util.*;
import java.util.stream.Collectors;

public class Player {

    private final IAccount account;
    private IMatchEndCondition condition;
    private PlayerEnergies energies;

    public Player(IAccount account, IMatchEndCondition victoryCondition){
        this.account = account;
        this.condition = victoryCondition;
        this.energies = new PlayerEnergies();
    }

    public void affectMatchEndCondition(Amount value){
        this.condition = this.condition.modify(value);
    }

    public boolean matchEndConditionMet(){
        return this.condition.isMet();
    }

    public int matchEndConditionPoints(){
        return this.condition.getNumeric();
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
