package com.core.g3.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.core.g3.Card.Artefact.Exceptions.ArtefactNotUsableException;
import com.core.g3.Card.Artefact.IArtefactEffect;
import com.core.g3.Card.Attack.Exceptions.CardCantAttackException;
import com.core.g3.Card.Action.Exceptions.ActionNotUsableException;
import com.core.g3.Card.Attack.IAttack;
import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Card.Cost.ICost;
import com.core.g3.Card.Cost.NullInvocationCost;
import com.core.g3.Card.Reaction.Exceptions.ReactionNotUsableException;
import com.core.g3.Card.Reaction.IReaction;
import com.core.g3.Card.Type.Artefact.CardTypeArtefact;
import com.core.g3.Card.Type.CardTypeName;
import com.core.g3.Card.Type.Creature.Attribute;
import com.core.g3.Card.Type.Creature.CardTypeCreature;
import com.core.g3.Card.Type.ICardType;
import com.core.g3.Card.Type.Exceptions.CardTypeNoSummonableInZoneException;
import com.core.g3.Card.Type.Reaction.CardTypeReaction;
import com.core.g3.Commons.Amount;
import com.core.g3.Deck.ICard;
import com.core.g3.Market.Transactions.IBuyer;
import com.core.g3.Market.Transactions.ISeller;
import com.core.g3.Market.Transactions.ITransactionable;
import com.core.g3.Match.CardInGame.AttackableManager.Health;
import com.core.g3.Match.CardInGame.AttackableManager.IAttackableManager;
import com.core.g3.Match.CardInGame.AttackableManager.NoAttackable;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;
import com.core.g3.Match.ResolutionStack.ResolutionStack;
import com.core.g3.Match.Zone.ActiveZoneType;

public class Card implements ITransactionable, ICard {

    private final CardName name;
    private final boolean shouldCount;
    private final ICost invocationCost;
    private List<ICardType> cardTypes;
    private Amount price;
    private Amount summonableSpace;

    public Card(CardName name, boolean shouldCount) {
        this.name = name;
        this.shouldCount = shouldCount;
        this.invocationCost = new NullInvocationCost();
        this.price = new Amount(0);
        this.summonableSpace = new Amount(1);
    }

    public Card(CardName name, boolean shouldCount, ICost invocationCost, List<ICardType> cardTypes,
            Amount summonableSpace) {
        this.name = name;
        this.shouldCount = shouldCount;
        this.invocationCost = invocationCost;
        this.cardTypes = cardTypes;
        this.price = new Amount(0);
        this.summonableSpace = summonableSpace;
    }

    @Override
    public void removeFrom(ISeller seller) {
        seller.removeItem(this);
    }

    @Override
    public void addTo(IBuyer buyer) {
        buyer.addItem(this);
    }

    @Override
    public CardName getName() {
        return this.name;
    }

    @Override
    public boolean shouldCountAgainstNameLimit() {
        return this.shouldCount;
    }

    @Override
    public int getPrice() {
        return this.price.value();
    }

    public List<CardTypeName> getTypes() {
        ArrayList<CardTypeName> types = new ArrayList<>();

        this.cardTypes.forEach(cardType -> types.add(cardType.getType()));

        return types;
    }

    @Override
    public Amount summonIn(ActiveZoneType zoneType) {
        for (ICardType cardType : this.cardTypes) {
            if (cardType.isSummonableIn(zoneType)) {
                return this.summonableSpace;
            }
        }
        throw new CardTypeNoSummonableInZoneException();
    }

    @Override
    public void applySummonCost(Player player) {
        this.invocationCost.apply(player);
    }

    @Override
    public IAttackableManager getHealth() {
        for (ICardType cardType : this.cardTypes) {
            if (cardType.is(CardTypeName.Creature)) {
                CardTypeCreature cast = (CardTypeCreature) cardType;
                return new Health(cast.getBaseHealth());
            }
        }
        return new NoAttackable();
    }

    @Override
    public List<IAttack> getAttacks() {
        for (ICardType cardType : this.cardTypes) {
            if (cardType.is(CardTypeName.Creature)) {
                CardTypeCreature cast = (CardTypeCreature) cardType;
                return cast.getAttacks();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<IArtefactEffect> getArtefactEffects() {
        for (ICardType cardType : this.cardTypes) {
            if (cardType.is(CardTypeName.Artefact)) {
                CardTypeArtefact cast = (CardTypeArtefact) cardType;
                return cast.getEffects();
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<IReaction> getReactionEffects() {
        for (ICardType cardType : this.cardTypes) {
            if (cardType.is(CardTypeName.Reaction)) {
                CardTypeReaction cast = (CardTypeReaction) cardType;
                return cast.getEffect();
            }
        }
        return Optional.empty();
    }

    @Override
    public OriginalAction attack(OriginalAction og, IAttackable victim, Player user, Player rival, int idx) {
        for (ICardType cardType : this.cardTypes) {
            if (cardType.canAttack()) {
                return cardType.attack(og, victim, user, rival, idx);
            }
        }
        throw new CardCantAttackException();
    }

    @Override
    public OriginalAction artefact(OriginalAction og, Player user, Player rival) {
        for (ICardType cardType : this.cardTypes) {
            if (cardType.is(CardTypeName.Artefact)) {
                return cardType.artefact(og, user, rival);
            }
        }
        throw new ArtefactNotUsableException();
    }

    @Override
    public OriginalAction artefact(OriginalAction og, IAttackable affected, Player user, Player rival) {
        for (ICardType cardType : this.cardTypes) {
            if (cardType.is(CardTypeName.Artefact)) {
                return cardType.artefact(og, affected, user, rival);
            }
        }
        throw new ArtefactNotUsableException();
    }

    @Override
    public void reaction(CardInGame cardInGame, Player user, Player rival, ResolutionStack stack) {
        for (ICardType cardType : this.cardTypes) {
            if (cardType.is(CardTypeName.Reaction)) {
                cardType.reaction(cardInGame, stack, user, rival);
                return;
            }
        }
        throw new ReactionNotUsableException();
    }

    @Override
    public OriginalAction action(OriginalAction og, List<IAttackable> victims, Player user, Player rival) {
        for (ICardType cardType : this.cardTypes) {
            if (cardType.is(CardTypeName.Action)) {
                return cardType.action(og, victims, user, rival);
            }
        }
        throw new ActionNotUsableException();
    }

    @Override
    public Optional<List<Attribute>> getCreatureAttributes() {
        for (ICardType cardType : this.cardTypes) {
            if (cardType.is(CardTypeName.Creature)) {
                return Optional.ofNullable(cardType.getAttributes());
            }
        }

        return Optional.empty();
    }

}