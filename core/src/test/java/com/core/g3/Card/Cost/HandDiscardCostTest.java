package com.core.g3.Card.Cost;

import com.core.g3.Card.Cost.Exception.CanNotPayException;
import com.core.g3.Commons.Amount;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Player.MatchEndCondition.PlainHP;
import com.core.g3.User.TestUserRegister;
import com.core.g3.User.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HandDiscardCostTest {

    @Test
    public void discardOneCardOk() {
        ICost cost = new HandDiscardCost();
        User user = TestUserRegister.createUser("a", "a");
        Player player = new Player(user, new DeckPlayableMock(), new PlainHP(new Amount(40)), null, null, null);
        player.drawCard();

        cost.apply(player);

        assertEquals(0, player.seeHand().size());
        assertEquals(1, player.seeDiscard().size());
    }

    @Test
    public void discardOneCardWithThreeOnHandOk() {
        ICost cost = new HandDiscardCost();
        User user = TestUserRegister.createUser("a", "a");
        Player player = new Player(user, new DeckPlayableMock(), new PlainHP(new Amount(40)), null, null, null);
        player.drawCard();
        player.drawCard();
        player.drawCard();

        cost.apply(player);

        assertEquals(2, player.seeHand().size());
        assertEquals(1, player.seeDiscard().size());
    }

    @Test
    public void discardWithNoCardOnHandThrows() {
        ICost cost = new HandDiscardCost();
        User user = TestUserRegister.createUser("a", "a");
        Player player = new Player(user, new DeckPlayableMock(), new PlainHP(new Amount(40)), null, null, null);

        assertThrows(CanNotPayException.class, () -> cost.apply(player));

        assertEquals(0, player.seeHand().size());
        assertEquals(0, player.seeDiscard().size());
    }

    @Test
    public void discardWithOtherCostFailsThrowsHandStayTheSame() {
        ICost cost = new HandDiscardCost(new ErrorCost());
        User user = TestUserRegister.createUser("a", "a");
        Player player = new Player(user, new DeckPlayableMock(), new PlainHP(new Amount(40)), null, null, null);
        player.drawCard();
        assertThrows(CanNotPayException.class, () -> cost.apply(player));

        assertEquals(1, player.seeHand().size());
        assertEquals(0, player.seeDiscard().size());
    }
}

class ErrorCost implements ICost {

    @Override
    public void apply(Player player) {
        throw new CanNotPayException();
    }
}