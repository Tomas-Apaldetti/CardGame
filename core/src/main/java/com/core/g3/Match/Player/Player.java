package com.core.g3.Match.Player;

import com.core.g3.Card.CardName;
import com.core.g3.Card.Attack.IAttackable;
import com.core.g3.Card.Type.Creature.Attribute;
import com.core.g3.Commons.Amount;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.CardContainer.CardContainer;
import com.core.g3.Match.DeckPlayable.IDeckPlayable;
import com.core.g3.Match.IAccount;
import com.core.g3.Match.Player.Exception.HandIsEmptyException;
import com.core.g3.Match.Player.MatchEndCondition.IMatchEndCondition;
import com.core.g3.Match.Player.Resources.EnergyType;
import com.core.g3.Match.Player.Resources.IResource;
import com.core.tcg.driver.Adapter.DriverMapper;
import com.core.tcg.driver.DriverCardName;
import com.core.g3.Match.Zone.ActiveZone;
import com.core.g3.Match.Zone.ActiveZoneType;

import java.util.*;
import java.util.stream.Collectors;

public class Player implements IAttackable {
    private final IAccount account;
    private IMatchEndCondition condition;
    private final IDeckPlayable deck;
    private final CardContainer hand;
    private final CardContainer discard;
    private ActiveZone artifactZone;
    private ActiveZone combatZone;
    private ActiveZone reserveZone;
    private PlayerEnergies energies;

    public Player(IAccount account, IDeckPlayable deck, IMatchEndCondition condition, ActiveZone artifactZone,
            ActiveZone combatZone, ActiveZone reserveZone) {
        this.account = account;
        this.condition = condition;
        this.deck = deck;
        this.hand = new CardContainer();
        this.discard = new CardContainer();
        this.artifactZone = artifactZone;
        this.combatZone = combatZone;
        this.reserveZone = reserveZone;
        this.energies = new PlayerEnergies();
    }

    public void addCardToHand(ICard card) {
        this.hand.add(card);
    }

    public IDeckPlayable getDeck() {
        return this.deck;
    }

    public ICard getCardByCardName(CardName cardName) {
        return this.hand.getCardByName(cardName);
    }

    public void summonInZone(ICard card, ActiveZone zone) {
        zone.addCard(card, this);
    }

    public void summonInZone(ICard card, ActiveZoneType zone) {
        if (this.hand.peek().contains(card)) {
            if (zone.equals(ActiveZoneType.Combat)) {
                this.summonInZone(card, this.combatZone);
            } else if (zone.equals(ActiveZoneType.Reserve)) {
                this.summonInZone(card, this.reserveZone);
            } else if (zone.equals(ActiveZoneType.Artifacts)) {
                this.summonInZone(card, this.artifactZone);
            }
        } else {
            throw new RuntimeException("Card not in hand");

        }
    }

    public void affectMatchEndCondition(Amount value) {
        this.condition = this.condition.modify(value);
    }

    public boolean matchEndConditionMet() {
        return this.condition.isMet();
    }

    public int matchEndConditionPoints() {
        return this.condition.getNumeric();
    }

    public IResource getEnergy(EnergyType energyType) {
        return this.energies.getEnergy(energyType);
    }

    public Collection<IResource> getEnergies() {
        List<IResource> energies = this.energies.getEnergies()
                .stream()
                .map(e -> (IResource) e)
                .collect(Collectors.toList());
        return energies;
    }

    public void add(EnergyType energyType, Amount value) {
        this.energies.add(energyType, value);
    }

    public void add(IResource resource, Amount value) {
        this.energies.add(resource, value);
    }

    public IResource consume(Optional<EnergyType> energyType, Amount value) {
        if (energyType.isPresent()) {
            return this.energies.consume(energyType.get(), value);
        }
        return this.energies.consumeAny(value);
    }

    public void consumeMax(Amount value) {
        Optional<EnergyType> a = this.getMaxEnergyType();
        if (!a.isPresent()) {
            return;
        }
        IResource maxEnergy = this.getEnergy(a.get());

        if (!value.gt(new Amount(maxEnergy.available()))) {
            this.consume(this.getMaxEnergyType(), new Amount(maxEnergy.available()));
            return;
        }

        this.consume(this.getMaxEnergyType(), value);
    }

    private Optional<EnergyType> getMaxEnergyType() {
        return this.energies.getMaxType();
    }

    public void drawCard() {
        this.hand.add(this.deck.getCard());
    }

    public List<ICard> seeHand() {
        return this.hand.peek();
    }

    public List<ICard> seeDiscard() {
        return this.discard.peek();
    }

    public void recoverCard(ICard card) {
        this.hand.add(this.discard.remove(card));
    }

    public void discardFromHand(ICard card) {
        this.discard.add(this.hand.remove(card));
    }

    public ICard discardFromHand() {
        if (this.hand.peek().isEmpty()) {
            throw new HandIsEmptyException();
        }
        ICard card = this.hand.peek().get(0);
        this.discardFromHand(this.hand.peek().get(0));
        return card;
    }

    public List<DriverCardName> retrieveDeckOrder() {
        List<DriverCardName> cards = new ArrayList<>();
        for (CardName name : this.deck.getCards()) {
            cards.add(DriverMapper.toDriverCardName(name));
        }
        return cards;
    }

    public void forceDeckOrder(List<CardName> cards) {
        deck.forceOrder(cards);
    }

    public void pay(ICard card) {
        card.applySummonCost(this);
    }

    public void discard(ICard base) {
        this.discard.add(base);
    }

    public List<IAttackable> getCreatures(Attribute attrFilter) {
        List<IAttackable> total = new ArrayList<>();
        total.addAll(this.artifactZone.getCreatures(attrFilter));
        total.addAll(this.combatZone.getCreatures(attrFilter));
        total.addAll(this.reserveZone.getCreatures(attrFilter));
        return total;
    }

    public List<IAttackable> getCreatures() {
        List<IAttackable> total = new ArrayList<>();
        total.addAll(this.artifactZone.getCreatures());
        total.addAll(this.combatZone.getCreatures());
        total.addAll(this.reserveZone.getCreatures());
        return total;
    }

    public IAttackable getAttackable(ICard card) {
        IAttackable cig = this.artifactZone.getCardInGame(card);
        if(cig != null){
            return cig;
        }
        cig = this.combatZone.getCardInGame(card);
        if(cig != null){
            return cig;
        }
        cig = this.reserveZone.getCardInGame(card);
        return cig;
    }

    public void destroyCreatures(int upTo) {
        List<IAttackable> allCreatures = this.getCreatures();
        Collections.shuffle(allCreatures);
        for (int i = 0; i < Math.min(allCreatures.size(), upTo); i++) {
            allCreatures.get(i).destroy();
        }
    }

    public void moveFromHand(ICard card) {
        this.hand.remove(card);
    }

    public void addToHand(ICard card) {
        this.hand.add(card);
    }

    @Override
    public boolean isAttackable() {
        return this.condition.isAttackable();
    }

    @Override
    public void receiveAttack(Amount damage) {
        this.condition.receiveAttack(damage);
    }

    @Override
    public void destroy() {
        this.condition.destroy();
    }

    @Override
    public void heal(Amount heal) {
        this.condition.heal(heal);
    }

    public ActiveZone seeActiveZone(ActiveZoneType activeZoneType) {
        if (activeZoneType.equals(ActiveZoneType.Combat)) {
            return this.combatZone;
        } else if (activeZoneType.equals(ActiveZoneType.Reserve)) {
            return this.reserveZone;
        } else if (activeZoneType.equals(ActiveZoneType.Artifacts)) {
            return this.artifactZone;
        }
        throw new RuntimeException("Invalid active zone type");
    }
}
