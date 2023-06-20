package com.core.g3.Deck;

import java.util.List;
import java.util.Optional;

import com.core.g3.Card.CardName;
import com.core.g3.Card.Type.CardTypeName;
import com.core.g3.Card.Artifact.IArtifactEffect;
import com.core.g3.Card.Attack.IAttack;
import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Card.Reaction.IReaction;
import com.core.g3.Card.Type.Creature.Attribute;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.CardInGame.AttackableManager.IAttackableManager;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.IAffectable;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;
import com.core.g3.Match.ResolutionStack.ResolutionStack;
import com.core.g3.Match.Zone.ActiveZoneType;

public interface ICard {

    CardName getName();

    boolean shouldCountAgainstNameLimit();

    int getPrice();

    Amount summonIn(ActiveZoneType artifacts);

    Amount summonSize();

    void applySummonCost(Player player);

    List<ActiveZoneType> getAllowableZones();

    List<CardTypeName> getTypes();

    IAttackableManager getHealth();

    List<IAttack> getAttacks();

    Optional<IArtifactEffect> getArtifactEffects();

    Optional<IReaction> getReactionEffects();

    OriginalAction attack(OriginalAction og, IAttackable victim, Player user, Player rival, int whichAttack);

    OriginalAction artifact(OriginalAction og, Player user, Player rival);

    OriginalAction artifact(OriginalAction og, List<IAffectable> affected, Player user, Player rival);

    OriginalAction action(OriginalAction og, Player user, Player rival);

    OriginalAction action(OriginalAction og, List<IAffectable> affected, Player user, Player rival);

    void reaction(CardInGame cardInGame, Player user, Player rival, ResolutionStack stack);

    Optional<List<Attribute>> getCreatureAttributes();

    int getCreatureHP();
}
