package com.core.Match.Player;

import com.core.Commons.Amount;
import com.core.Deck.ICard;
import com.core.Match.CardContainer.CardContainer;
import com.core.Match.DeckPlayable.IDeckPlayable;
import com.core.Match.IAccount;
import com.core.Match.Player.Exception.HandIsEmptyException;
import com.core.Match.Player.MatchEndCondition.IMatchEndCondition;
import com.core.Match.Player.Resources.EnergyType;
import com.core.Match.Player.Resources.IResource;

import java.util.*;
import java.util.stream.Collectors;

public class Player {
    private final IAccount account; // TODO -> remove?
    private IMatchEndCondition condition;
    private final IDeckPlayable deck;
    private final CardContainer hand;
    private final CardContainer discard;
    private PlayerEnergies energies;

    public Player(IAccount account, IDeckPlayable deck, IMatchEndCondition condition) {
        this.account = account;
        this.condition = condition;
        this.deck = deck;
        this.deck.shuffle();
        this.hand = new CardContainer();
        this.discard = new CardContainer();
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
}
