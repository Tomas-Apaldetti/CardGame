package com.core.g3.Card.Attack;

import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Player.Resources.EnergyType;
import com.core.g3.Match.ResolutionStack.OriginalAction.Action.AddEnergy;
import com.core.g3.Match.ResolutionStack.OriginalAction.ActionType;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;

import java.util.Optional;

public class AddEnergyAttack implements IAttack{

    private final EnergyType energyToAdd;
    private final Amount amountToAdd;
    private Optional<IAttack> next = Optional.empty();

    public AddEnergyAttack(EnergyType type, Amount value){
        this.energyToAdd = type;
        this.amountToAdd = value;
    }

    public AddEnergyAttack(EnergyType type, Amount value, IAttack next){
        this.energyToAdd = type;
        this.amountToAdd = value;
        this.next = Optional.ofNullable(next);
    }

    public OriginalAction attack(OriginalAction action, IAttackable victim, Player user, Player rival) {
        if(this.next.isPresent()){
            this.next.get().attack(action,victim,user,rival);
        }
        action.setType(ActionType.Attack);
        action.addEffect(new AddEnergy(this.energyToAdd, this.amountToAdd, user));
        return action;
    }
}
