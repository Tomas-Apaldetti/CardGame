package com.core.g3.Card.Cost;

import com.core.g3.Card.Cost.Exception.CanNotPayException;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.Player.Exception.HandIsEmptyException;
import com.core.g3.Match.Player.Player;

import java.util.Optional;

public class HandDiscardCost implements ICost {

    private Optional<ICost> next;

    public HandDiscardCost() {
        this.next = Optional.empty();
    }

    public HandDiscardCost(ICost next) {
        this.next = Optional.ofNullable(next);
    }

    private void applyNext(Player player) {
        if (this.next.isPresent()) {
            this.next.get().apply(player);
        }
    }

    private ICard applyThis(Player player) {
        try {
            return player.discardFromHand();
        } catch (HandIsEmptyException e) {
            throw new CanNotPayException();
        }
    }

    @Override
    public void apply(Player player) {
        ICard discarded = this.applyThis(player);
        try {
            this.applyNext(player);
        } catch (CanNotPayException e) {
            player.recoverCard(discarded);
            throw e;
        }
    }
}
