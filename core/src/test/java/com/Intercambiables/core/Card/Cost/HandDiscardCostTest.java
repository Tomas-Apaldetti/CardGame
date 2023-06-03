package com.Intercambiables.core.Card.Cost;

import com.Intercambiables.core.Card.Card;
import com.Intercambiables.core.Card.CardName;
import com.Intercambiables.core.Card.Cost.Exception.CanNotPayException;
import com.Intercambiables.core.Commons.Amount;
import com.Intercambiables.core.Deck.ICard;
import com.Intercambiables.core.Match.DeckPlayable.IDeckPlayable;
import com.Intercambiables.core.Match.Player.Player;
import com.Intercambiables.core.User.TestUserRegister;
import com.Intercambiables.core.User.User;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class HandDiscardCostTest {

    @Test
    public void discardOneCardOk(){
        ICost cost = new HandDiscardCost();
        User user = TestUserRegister.createUser("a", "a");
        Player player = new Player(user, new TestDeckPlayable(), new Amount(40));
        player.drawCard();

        cost.apply(player);

        assertEquals(0, player.seeHand().size());
        assertEquals(1, player.seeDiscard().size());
    }

    @Test
    public void discardOneCardWithThreeOnHandOk(){
        ICost cost = new HandDiscardCost();
        User user = TestUserRegister.createUser("a", "a");
        Player player = new Player(user, new TestDeckPlayable(), new Amount(40));
        player.drawCard();
        player.drawCard();
        player.drawCard();

        cost.apply(player);

        assertEquals(2, player.seeHand().size());
        assertEquals(1, player.seeDiscard().size());
    }

    @Test
    public void discardWithNoCardOnHandThrows(){
        ICost cost = new HandDiscardCost();
        User user = TestUserRegister.createUser("a", "a");
        Player player = new Player(user, new TestDeckPlayable(), new Amount(40));

        assertThrows(CanNotPayException.class, ()-> cost.apply(player));

        assertEquals(0, player.seeHand().size());
        assertEquals(0, player.seeDiscard().size());
    }

    @Test
    public void discardWithOtherCostFailsThrowsHandStayTheSame(){
        ICost cost = new HandDiscardCost(new ErrorCost());
        User user = TestUserRegister.createUser("a", "a");
        Player player = new Player(user, new TestDeckPlayable(), new Amount(40));
        player.drawCard();
        assertThrows(CanNotPayException.class, ()-> cost.apply(player));

        assertEquals(1, player.seeHand().size());
        assertEquals(0, player.seeDiscard().size());
    }
}

class TestDeckPlayable implements IDeckPlayable{

    @Override
    public ICard getCard() {
        return new Card(CardName.Alquimista, false);
    }

    @Override
    public void shuffle() {
        return;
    }
}

class ErrorCost implements ICost{

    @Override
    public void apply(Player player) {
        throw new CanNotPayException();
    }
}