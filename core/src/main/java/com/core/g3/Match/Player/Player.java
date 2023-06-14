package com.core.g3.Match.Player;

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
import com.core.g3.Match.Zone.ActiveZone;

import java.util.*;
import java.util.stream.Collectors;

public class Player {
    private final IAccount account; // TODO -> remove?
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

    public List<IAttackable> getCreatures(){
        List<IAttackable> total = new ArrayList<>();
        total.addAll(this.artifactZone.getCreatures());
        total.addAll(this.combatZone.getCreatures());
        total.addAll(this.reserveZone.getCreatures());
        return total;
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
    public void addToHand(ICard card){
        this.hand.add(card);
    }
}
