package com.core.g3.Match.Zone;

import com.core.g3.Card.CardName;
import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Card.Type.Creature.Attribute;
import com.core.g3.Commons.Amount;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.CardInGame.IDeathPub;
import com.core.g3.Match.CardInGame.IDeathSub;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Zone.Exceptions.CardLimitReachedException;
import com.core.g3.Match.Zone.Exceptions.CardNotInZoneException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ActiveZone implements IDeathSub, IDeathPub {

    private ActiveZoneType zoneType;
    private Amount limit;
    private Amount cardsSize;
    private List<CardInGame> cards;
    private final List<IDeathSub> subs = new ArrayList<>();
    private boolean isActive;
    private List<IDeathSub> subsRemovePetitions = new ArrayList<>();

    public ActiveZone(ActiveZoneType zoneType, Amount limit) {
        this(zoneType, limit, true);
    }

    public ActiveZone(ActiveZoneType zoneType, Amount limit, boolean isActive) {
        this.zoneType = zoneType;
        this.limit = limit;
        this.cardsSize = new Amount(0);
        this.cards = new ArrayList<>();
        this.isActive = isActive;
    }

    public List<CardInGame> getCardsInGameByCardName(CardName cardName) {
        return this.cards.stream()
                .filter(cig -> cig.getBase().getName().equals(cardName))
                .collect(Collectors.toList());
    }

    public CardInGame addCard(ICard card, Player player) {

        this.assertCanAdd(card);

        this.assertPayCard(card, player);

        player.moveFromHand(card);

        CardInGame lCard = new CardInGame(player, card, this);

        this.addCard(lCard);

        return lCard;
    }

    public CardInGame moveToHere(CardInGame card) {
        this.assertCanAdd(card.getBase());

        this.addCard(card);

        card.changeZone(this);

        return card;
    }

    public CardInGame addCard(CardInGame card) {
        Amount size = card.getBase().summonSize();
        this.cardsSize.add(size);
        this.cards.add(card);
        return card;
    }

    private void assertCanAdd(ICard card) {
        Amount size = card.summonIn(this.zoneType);

        size.add(this.cardsSize);
        if (size.gt(this.limit)) {
            throw new CardLimitReachedException();
        }
    }

    private void assertPayCard(ICard card, Player player) {
        if (this.isActive) {
            player.pay(card);
        }
    }

    public void remove(CardInGame card) {
        this.remove(card, () -> {
            throw new CardNotInZoneException();
        });
    }

    private void remove(CardInGame card, Runnable onMiss) {
        if (!this.cards.contains(card)) {
            onMiss.run();
            return;
        }
        Amount size = card.getBase().summonSize();
        card.remove(this);
        card.putInDiscard();
        this.cards.remove(card);
        this.cardsSize.subtract(size);
    }

    public List<IAttackable> getCreatures(Attribute attrFilter) {
        return this.cards.stream()
                .filter(card -> {
                    Optional<List<Attribute>> cAttr = card.getCreatureAttributes();
                    return cAttr.isPresent() && cAttr.get().contains(attrFilter);
                }).map(c -> (IAttackable) c).collect(Collectors.toList());
    }

    public int currentCardCount() {
        return this.cards.size();
    }

    public List<IAttackable> getCreatures() {
        return this.cards.stream().filter(card -> card.getCreatureAttributes().isPresent()).map(c -> (IAttackable) c)
                .collect(Collectors.toList());
    }

    public boolean countsAsActive() {
        return this.isActive;
    }

    public CardInGame getCardInGame(ICard card) {
        for (CardInGame cig : this.cards) {
            if (cig.getBase().equals(card)) {
                return cig;
            }
        }
        return null;
    }

    public List<CardInGame> getCardsInGame(List<ICard> cards) {
        return this.cards.stream()
                .filter(cig -> cards.contains(cig.getBase()))
                .collect(Collectors.toList());
    }

    public List<CardInGame> getCardsInGame() {
        return this.cards;
    }

    public ActiveZoneType getType() {
        return this.zoneType;
    }

    @Override
    public void notify(CardInGame card) {
        this.remove(card, () -> {
        });
        this.onCardDeath(card);
    }

    private void onCardDeath(CardInGame card) {
        this.applyRemovals();
        this.subs.forEach((i) -> i.notify(card));
    }

    private void applyRemovals() {
        this.subsRemovePetitions.forEach(s -> this.subs.remove(s));
        this.subsRemovePetitions.clear();
    }

    @Override
    public void add(IDeathSub sub) {
        this.applyRemovals();
        this.subs.add(sub);
    }

    @Override
    public void remove(IDeathSub sub) {
        this.subsRemovePetitions.add(sub);
    }
}
