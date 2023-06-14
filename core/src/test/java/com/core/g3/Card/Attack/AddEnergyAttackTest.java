package com.core.g3.Card.Attack;

import com.core.g3.Card.Attack.Mocks.AttackMock;
import com.core.g3.Card.CardBuilder;
import com.core.g3.Card.CardName;
import com.core.g3.Card.Type.Creature.Attribute;
import com.core.g3.Commons.Amount;
import com.core.g3.Deck.ICard;
import com.core.g3.Match.CardInGame.CardInGame;
import com.core.g3.Match.Player.Player;
import com.core.g3.Match.Player.Resources.EnergyType;
import com.core.g3.Match.ResolutionStack.OriginalAction.OriginalAction;
import com.core.g3.Mock.Exceptions.ExpectedException;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddEnergyAttackTest {

    private CardBuilder getCardBuilder(IAttack attack) {
        CardBuilder builder = new CardBuilder(CardName.Alchemist);
        List<Attribute> attributes = new ArrayList<>();
        List<IAttack> attacks = new ArrayList<>();
        attacks.add(attack);
        builder.cardTypeBuilder.setTypeCreature(new Amount(3), attributes, attacks);
        return builder;
    }

    @Test
    public void energyAddAttackAddCorrectAmount() {
        Player blue = new Player(null, null, null, null, null, null);
        Player green = new Player(null, null, null, null, null, null);
        ICard card1 = this.getCardBuilder(new AddEnergyAttack(EnergyType.Water, new Amount(3))).build();
        ICard card2 = this.getCardBuilder(new AddEnergyAttack(EnergyType.Fire, new Amount(3))).build();

        CardInGame cig1 = new CardInGame(blue, card1, null);
        cig1.refreshUse();
        CardInGame cig2 = new CardInGame(green, card2, null);
        OriginalAction action = cig1.attack(cig2, blue, green, new Amount(0));
        action.apply();
        assertEquals(3, cig2.getHealth());
        assertEquals(3, blue.getEnergy(EnergyType.Water).available());
    }

    @Test
    public void executeInternalAttackException() {
        Player blue = new Player(null, null, null, null, null, null);
        Player green = new Player(null, null, null, null, null, null);
        ICard card1 = this.getCardBuilder(new AddEnergyAttack(EnergyType.Plant, new Amount(3), new AttackMock()))
                .build();
        ICard card2 = this.getCardBuilder(new AddEnergyAttack(EnergyType.Fire, new Amount(3))).build();

        CardInGame cig1 = new CardInGame(blue, card1, null);
        cig1.refreshUse();
        CardInGame cig2 = new CardInGame(green, card2, null);
        assertThrows(ExpectedException.class, () -> cig1.attack(cig2, blue, green, new Amount(0)));
        assertEquals(3, cig2.getHealth());
        assertEquals(0, blue.getEnergy(EnergyType.Plant).available());
    }
}