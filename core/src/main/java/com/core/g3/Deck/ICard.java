package com.core.g3.Deck;

import com.core.g3.Card.Attack.IAttack;
import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Card.CardName;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.CardInGame.AttackableManager.IAttackableManager;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;
import com.core.g3.Match.Zone.ActiveZoneType;

import java.util.List;

public interface ICard {

    public CardName getName();

    public boolean shouldCountAgainstNameLimit();

    public int getPrice();

    public Amount summonIn(ActiveZoneType artifacts);

    public void applySummonCost(Player player);

    OriginalAction attack(IAttackable victim, Player user, Player rival, int whichAttack);

    IAttackableManager getHealth();

    List<IAttack> getAttacks();
}
