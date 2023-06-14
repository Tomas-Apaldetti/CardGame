package com.core.g3.Card.Attack;

import com.core.g3.Card.Attack.Mocks.AttackMock;
import com.core.g3.Mock.Exceptions.ExpectedException;
import com.core.g3.Card.Attack.Mocks.PlayableDeckMock;
import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;
import com.core.g3.Card.Type.Creature.Attribute;
import com.core.g3.Commons.Amount;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.DeckPlayable.IDeckPlayable;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DrawCardAttackTest {
    private CardBuilder getCardBuilder(IAttack attack) {
        CardBuilder builder = new CardBuilder(CardName.Alchemist);
        List<Attribute> attributes = new ArrayList<>();
        List<IAttack> attacks = new ArrayList<>();
        attacks.add(attack);
        builder.cardTypeBuilder.setTypeCreature(new Amount(3), attributes, attacks);
        return builder;
    }

    @Test
    public void shouldWorks() {
        IDeckPlayable deck = new PlayableDeckMock();
        Player blue = new Player(null, deck, null, null, null, null);
        Player green = new Player(null, null, null, null, null, null);

        ICard card1 = this.getCardBuilder(new DrawCardAttack()).build();

        CardInGame cig1 = new CardInGame(blue, card1, null);
        cig1.refreshUse();
        OriginalAction action = cig1.attack(null, blue, null, new Amount(0));
        assertEquals(1, deck.size());
        assertEquals(0, blue.seeHand().size());
        action.apply();
        assertEquals(0, deck.size());
        assertEquals(1, blue.seeHand().size());
    }

    @Test
    public void shouldCallNextAttack() {
        IDeckPlayable deck = new PlayableDeckMock();
        Player blue = new Player(null, deck, null, null, null, null);
        Player green = new Player(null, null, null, null, null, null);

        ICard card1 = this.getCardBuilder(new DrawCardAttack(new AttackMock())).build();

        CardInGame cig1 = new CardInGame(blue, card1, null);
        cig1.refreshUse();
        assertThrows(ExpectedException.class, () -> cig1.attack(null, blue, null, new Amount(0)));
        assertEquals(1, deck.size());
        assertEquals(0, blue.seeHand().size());
    }
}