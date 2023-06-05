package com.core.g3.Match.Player;

import com.core.g3.Commons.Amount;
import com.core.g3.Commons.Exception.InvalidAmountException;
import com.core.g3.Match.Player.Resources.EnergyType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerEnergiesTest {

    @Test
    public void addEnergyOk() {
        PlayerEnergies energies = new PlayerEnergies();
        energies.add(EnergyType.Fire, new Amount(10));

        assertEquals(energies.getEnergy(EnergyType.Fire).available(), 10);
        assertEquals(energies.getEnergy(EnergyType.Water).available(), 0);
        assertEquals(energies.getEnergy(EnergyType.Plant).available(), 0);
    }

    @Test
    public void addMultipleEnergyOk() {
        PlayerEnergies energies = new PlayerEnergies();
        energies.add(EnergyType.Fire, new Amount(10));
        energies.add(EnergyType.Water, new Amount(20));
        energies.add(EnergyType.Plant, new Amount(15));
        energies.add(EnergyType.Fire, new Amount(7));

        assertEquals(energies.getEnergy(EnergyType.Fire).available(), 10 + 7);
        assertEquals(energies.getEnergy(EnergyType.Water).available(), 20);
        assertEquals(energies.getEnergy(EnergyType.Plant).available(), 15);
    }

    @Test
    public void consumeAvailableEnergyOk() {
        PlayerEnergies energies = new PlayerEnergies();
        energies.add(EnergyType.Fire, new Amount(10));
        energies.consume(EnergyType.Fire, new Amount(5));

        assertEquals(energies.getEnergy(EnergyType.Fire).available(), 5);
        assertEquals(energies.getEnergy(EnergyType.Water).available(), 0);
        assertEquals(energies.getEnergy(EnergyType.Plant).available(), 0);
    }

    @Test
    public void consumeMultipleAvailableOk() {
        PlayerEnergies energies = new PlayerEnergies();
        energies.add(EnergyType.Fire, new Amount(10));
        energies.add(EnergyType.Water, new Amount(20));
        energies.add(EnergyType.Plant, new Amount(15));
        energies.consume(EnergyType.Fire, new Amount(5));
        energies.consume(EnergyType.Water, new Amount(5));
        energies.consume(EnergyType.Plant, new Amount(5));

        assertEquals(energies.getEnergy(EnergyType.Fire).available(), 5);
        assertEquals(energies.getEnergy(EnergyType.Water).available(), 15);
        assertEquals(energies.getEnergy(EnergyType.Plant).available(), 10);
    }

    @Test
    public void consumeMoreThanAvailableThrows() {
        PlayerEnergies energies = new PlayerEnergies();
        energies.add(EnergyType.Fire, new Amount(10));

        assertThrows(InvalidAmountException.class, () -> energies.consume(EnergyType.Fire, new Amount(15)));
    }

    @Test
    public void consumeFreshThrows() {
        PlayerEnergies energies = new PlayerEnergies();

        assertThrows(InvalidAmountException.class, () -> energies.consume(EnergyType.Fire, new Amount(1)));
    }

    @Test
    public void consumeAnyWithOnlyFireAvailableOk() {
        PlayerEnergies energies = new PlayerEnergies();
        energies.add(EnergyType.Fire, new Amount(10));
        energies.consumeAny(new Amount(10));
        assertEquals(energies.getEnergy(EnergyType.Fire).available(), 0);
    }
}