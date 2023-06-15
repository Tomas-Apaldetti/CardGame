package com.core.g3.Match.ResolutionStack.OriginalAction;

import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Card.CardName;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.ResolutionStack.LingeringEffect.ILingeringEffect;
import com.core.g3.Match.ResolutionStack.OriginalAction.Action.Damage;
import com.core.g3.Match.ResolutionStack.OriginalAction.Action.IEffect;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OriginalAction implements IOriginal {

    private final CardInGame source;
    private boolean isCanceled;
    private ActionType type;
    private Optional<Damage> damage = Optional.empty();
    private Optional<IAttackable> attackObjective = Optional.empty();
    private List<IEffect> effects;
    private boolean discardOnUse;
    private List<ILingeringEffect> lingerings;

    public OriginalAction(CardInGame source) {
        this.source = source;
        this.effects = new ArrayList<>();
        this.isCanceled = false;
        this.discardOnUse = false;
        this.lingerings = new ArrayList<>();
    }

    public void setType(ActionType type) {
        this.type = type;
    }

    public void addAttack(Amount damage, IAttackable victim) {
        this.damage = Optional.of(new Damage(damage, victim));
        this.attackObjective = Optional.of(victim);
    }

    public void addEffect(IEffect effect) {
        this.effects.add(effect);
    }

    public void apply() {
        if (!this.isCanceled) {
            if (this.damage.isPresent()) {
                this.damage.get().apply();
            }
            for (IEffect effect : effects) {
                this.lingerings.addAll(effect.apply());
            }
        }
        if (this.discardOnUse) {
            this.source.discard();
        }
    }

    @Override
    public boolean isSource(CardInGame questioner) {
        return this.source.getBase().equals(questioner.getBase());
    }

    @Override
    public boolean is(ActionType other) {
        return this.type == other;
    }

    @Override
    public void setMaxDamage(Amount value) {
        if (!this.damage.isPresent()) {
            return;
        }
        this.damage.get().setMaxDamage(value);
    }

    @Override
    public void duplicate() {
        // @TODO
    }

    @Override
    public boolean sourceIs(CardName cardName) {
        return this.source.getBase().getName() == cardName;
    }

    @Override
    public void cancel() {
        this.isCanceled = true;
    }

    @Override
    public void discardSource() {
        this.discardOnUse = true;
    }

    @Override
    public boolean isObjective(CardInGame questioner) {
        if (!this.attackObjective.isPresent()) {
            return false;
        }
        return this.attackObjective.get().equals(questioner);
    }

}
