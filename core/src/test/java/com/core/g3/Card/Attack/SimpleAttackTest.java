package com.core.g3.Card.Attack;

import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;
import com.core.g3.Card.Type.Creature.Attribute;
import com.core.g3.Commons.Amount;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;
import com.core.g3.Match.Zone.ActiveZone;
import com.core.g3.Match.Zone.ActiveZoneType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleAttackTest {
    private CardBuilder getCardBuilder(IAttack attack) {
        CardBuilder builder = new CardBuilder(CardName.Alchemist);
        List<Attribute> attributes = new ArrayList<>();
        List<IAttack> attacks = new ArrayList<>();
        attacks.add(attack);
        builder.cardTypeBuilder.setTypeCreature(new Amount(5), attributes, attacks);
        return builder;
    }

    @Test
    public void ShouldAttackCard() {
        Player blue = new Player(null, null, null, null, null, null);
        Player green = new Player(null, null, null, null, null, null);
        ICard card1 = this.getCardBuilder(new SimpleAttack(new Amount(3))).build();
        ICard card2 = this.getCardBuilder(new SimpleAttack(new Amount(3))).build();

        CardInGame cig1 = new CardInGame(blue, card1, null);
        cig1.refreshUse();
        CardInGame cig2 = new CardInGame(green, card2, null);
        OriginalAction action = cig1.attack(cig2, blue, green, new Amount(0));
        action.apply();
        assertEquals(2, cig2.getHealth());
    }

    @Test
    public void ShouldKillCard() {
        Player blue = new Player(null, null, null, null, null, null);
        Player green = new Player(null, null, null, null, null, null);
        ICard card1 = this.getCardBuilder(new SimpleAttack(new Amount(2000))).build();
        ICard card2 = this.getCardBuilder(new SimpleAttack(new Amount(3))).build();

        ActiveZone activeZone = new ActiveZone(ActiveZoneType.Combat, new Amount(1));
        CardInGame cig1 = new CardInGame(blue, card1, null);
        cig1.refreshUse();
        CardInGame cig2 = new CardInGame(green, card2, activeZone);
        OriginalAction action = cig1.attack(cig2, blue, green, new Amount(0));
        action.apply();
        assertEquals(0, cig2.getHealth());
    }

}