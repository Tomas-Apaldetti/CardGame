package com.core.g3.Match.Zone;

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

    public CardInGame addCard(ICard card, Player player) {
        Amount size = card.summonIn(this.zoneType);

        size.add(this.cardsSize);
        if (size.gt(this.limit)) {
            throw new CardLimitReachedException();
        }

        player.pay(card);
        player.moveFromHand(card);

        CardInGame livingCard = new CardInGame(player, card, this);

        this.cardsSize = size;
        this.cards.add(livingCard);

        return livingCard;
    }

    public void addCard(CardInGame card) {
        Amount size = card.getBase().summonIn(this.zoneType);
        this.cardsSize.add(size);
        this.cards.add(card);
    }

    public void remove(CardInGame card) {
        if (!this.cards.contains(card)) {
            throw new CardNotInZoneException();
        }
        Amount size = card.getBase().summonIn(this.zoneType);
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
        return this.cards.stream().filter(card -> {
            return card.getCreatureAttributes().isPresent();
        }).map(c -> (IAttackable) c).collect(Collectors.toList());
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

    @Override
    public void notify(CardInGame card) {
        this.remove(card);
    }

    @Override
    public void add(IDeathSub sub) {
        this.subs.add(sub);
    }

    @Override
    public void remove(IDeathSub sub) {
        this.subs.remove(subs);
    }
}
