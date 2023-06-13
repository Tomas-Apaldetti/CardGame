package com.core.g3.Match.ResolutionStack.OriginalAction;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Commons.Amount;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.Action.Damage;
import com.core.g3.Match.ResolutionStack.OriginalAction.Action.IEffect;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OriginalAction {

    private final ICard source;
    private ActionType type;
    private Optional<Damage> damage = Optional.empty();
    private List<IEffect> effects;

    public OriginalAction(ICard source) {
        this.source = source;
        this.effects = new ArrayList<>();
    }

    public void setType(ActionType type) {
        this.type = type;
    }

    public void addAttack(Amount damage, IAttackable victim) {
        this.damage = Optional.of(new Damage(damage, victim));
    }

    public void addEffect(IEffect effect) {
        this.effects.add(effect);
    }

    public void apply() {
        if (this.damage.isPresent()) {
            this.damage.get().apply();
        }
        for (IEffect effect : effects) {
            effect.apply();
        }
    }
}
