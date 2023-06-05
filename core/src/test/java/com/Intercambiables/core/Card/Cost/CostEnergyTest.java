package com.Intercambiables.core.Card.Cost;

import com.Intercambiables.core.Card.Cost.Exception.CanNotPayException;
import com.Intercambiables.core.Commons.Amount;
import com.Intercambiables.core.Match.Player.Player;
import com.Intercambiables.core.Match.Player.MatchEndCondition.PlainHP;
import com.Intercambiables.core.Match.Player.Resources.EnergyType;
import com.Intercambiables.core.User.TestUserRegister;
import com.Intercambiables.core.User.User;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CostEnergyTest {

        @Test
        public void energyCostReducesCorrectlyOk() {
                ICost cost = new CostEnergy(
                                Optional.of(EnergyType.Fire),
                                new Amount(3));
                User user = TestUserRegister.createUser("a", "a");
                Player player = new Player(user, new DeckPlayableMock(), new PlainHP(new Amount(40)));
                player.add(EnergyType.Fire, new Amount(5));

                cost.apply(player);

                assertEquals(2, player.getEnergy(EnergyType.Fire).available());
        }

        @Test
        public void energyCostReducesCorrectlyComplexOk() {
                ICost cost = new CostEnergy(
                                Optional.of(EnergyType.Plant),
                                new Amount(3),
                                Optional.of(new CostEnergy(
                                                Optional.of(EnergyType.Fire),
                                                new Amount(3))));
                User user = TestUserRegister.createUser("a", "a");
                Player player = new Player(user, new DeckPlayableMock(), new PlainHP(new Amount(40)));
                player.add(EnergyType.Fire, new Amount(5));
                player.add(EnergyType.Plant, new Amount(5));

                cost.apply(player);

                assertEquals(2, player.getEnergy(EnergyType.Fire).available());
                assertEquals(2, player.getEnergy(EnergyType.Plant).available());
        }

        @Test
        public void energyCostIsMoreThanPlayerHasThrowsAndEnergiesStayTheSame() {
                ICost cost = new CostEnergy(
                                Optional.of(EnergyType.Plant),
                                new Amount(2),
                                Optional.of(new CostEnergy(
                                                Optional.of(EnergyType.Fire),
                                                new Amount(3))));
                User user = TestUserRegister.createUser("a", "a");
                Player player = new Player(user, new DeckPlayableMock(), new PlainHP(new Amount(40)));
                player.add(EnergyType.Fire, new Amount(2));
                player.add(EnergyType.Plant, new Amount(2));

                assertThrows(CanNotPayException.class, () -> cost.apply(player));

                assertEquals(2, player.getEnergy(EnergyType.Fire).available());
                assertEquals(2, player.getEnergy(EnergyType.Plant).available());
        }

        @Test
        public void energyCostMultipleOfSameOk() {
                ICost cost = new CostEnergy(
                                Optional.of(EnergyType.Fire),
                                new Amount(3),
                                Optional.of(new CostEnergy(
                                                Optional.of(EnergyType.Fire),
                                                new Amount(3))));
                User user = TestUserRegister.createUser("a", "a");
                Player player = new Player(user, new DeckPlayableMock(), new PlainHP(new Amount(40)));
                player.add(EnergyType.Fire, new Amount(6));

                cost.apply(player);

                assertEquals(0, player.getEnergy(EnergyType.Fire).available());
        }

        @Test
        public void energyCostNoEnergyConsumesAnyOk() {
                ICost cost = new CostEnergy(
                                Optional.empty(),
                                new Amount(3));
                User user = TestUserRegister.createUser("a", "a");
                Player player = new Player(user, new DeckPlayableMock(), new PlainHP(new Amount(40)));
                player.add(EnergyType.Fire, new Amount(3));

                cost.apply(player);

                assertEquals(0, player.getEnergy(EnergyType.Fire).available());

                player.add(EnergyType.Water, new Amount(4));
                cost.apply(player);
                assertEquals(1, player.getEnergy(EnergyType.Water).available());
        }

        @Test
        public void energyCostMultiplesOneEmptyOk() {
                ICost cost = new CostEnergy(
                                Optional.empty(),
                                new Amount(3),
                                Optional.of(new CostEnergy(
                                                Optional.of(EnergyType.Fire),
                                                new Amount(3))));
                User user = TestUserRegister.createUser("a", "a");
                Player player = new Player(user, new DeckPlayableMock(), new PlainHP(new Amount(40)));
                player.add(EnergyType.Fire, new Amount(3));
                player.add(EnergyType.Water, new Amount(3));

                cost.apply(player);

                assertEquals(0, player.getEnergy(EnergyType.Fire).available());
                assertEquals(0, player.getEnergy(EnergyType.Water).available());
        }

        @Test
        public void energyCostMultiplesFirstEmptyOk() {
                ICost cost = new CostEnergy(
                                Optional.of(EnergyType.Fire),
                                new Amount(3),
                                Optional.of(new CostEnergy(
                                                Optional.empty(),
                                                new Amount(3))));
                User user = TestUserRegister.createUser("a", "a");
                Player player = new Player(user, new DeckPlayableMock(), new PlainHP(new Amount(40)));
                player.add(EnergyType.Fire, new Amount(3));
                player.add(EnergyType.Water, new Amount(3));

                cost.apply(player);

                assertEquals(0, player.getEnergy(EnergyType.Fire).available());
                assertEquals(0, player.getEnergy(EnergyType.Water).available());
        }

        @Test
        public void energyCostMultiplesOneEmptyNotEnoughEnergyThrowsAndStaysTheSame() {
                ICost cost = new CostEnergy(
                                Optional.empty(),
                                new Amount(4),
                                Optional.of(new CostEnergy(
                                                Optional.of(EnergyType.Fire),
                                                new Amount(4))));
                User user = TestUserRegister.createUser("a", "a");
                Player player = new Player(user, new DeckPlayableMock(), new PlainHP(new Amount(40)));
                player.add(EnergyType.Fire, new Amount(3));
                player.add(EnergyType.Water, new Amount(3));

                assertThrows(CanNotPayException.class, () -> cost.apply(player));

                assertEquals(3, player.getEnergy(EnergyType.Fire).available());
                assertEquals(3, player.getEnergy(EnergyType.Water).available());
        }
}