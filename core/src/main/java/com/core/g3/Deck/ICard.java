package com.core.g3.Deck;

import java.util.List;
import java.util.Optional;

import com.core.g3.Card.CardName;
import com.core.g3.Card.Type.CardTypeName;
import com.core.g3.Card.Artefact.IArtefactEffect;
import com.core.g3.Card.Attack.IAttack;
import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Card.Reaction.IReaction;
import com.core.g3.Card.Type.Creature.Attribute;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.CardInGame.AttackableManager.IAttackableManager;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;
import com.core.g3.Match.ResolutionStack.ResolutionStack;
import com.core.g3.Match.Zone.ActiveZoneType;

public interface ICard {

    public CardName getName();

    public boolean shouldCountAgainstNameLimit();

    public int getPrice();

    public Amount summonIn(ActiveZoneType artifacts);

    public void applySummonCost(Player player);

    List<ActiveZoneType> getAllowableZones();

    public List<CardTypeName> getTypes();

    IAttackableManager getHealth();

    List<IAttack> getAttacks();

    Optional<IArtefactEffect> getArtefactEffects();

    Optional<IReaction> getReactionEffects();

    OriginalAction attack(OriginalAction og, IAttackable victim, Player user, Player rival, int whichAttack);

    OriginalAction artefact(OriginalAction og, Player user, Player rival);

    OriginalAction artefact(OriginalAction og, IAttackable affected, Player user, Player rival);

    OriginalAction action(OriginalAction og, Player user, Player rival);

    OriginalAction action(OriginalAction og, IAttackable affected, Player user, Player rival);

    void reaction(CardInGame cardInGame, Player user, Player rival, ResolutionStack stack);

    Optional<List<Attribute>> getCreatureAttributes();
}
