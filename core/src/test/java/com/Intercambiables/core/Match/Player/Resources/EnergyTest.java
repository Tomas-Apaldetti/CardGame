package com.Intercambiables.core.Match.Player.Resources;

import com.Intercambiables.core.Commons.Amount;
import com.Intercambiables.core.Commons.Exception.InvalidAmountException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnergyTest {

    @Test
    public void addAmountTest() {
        IModifiableResource energy = new Energy(EnergyType.Fire, new Amount(10));

        energy.add(new Amount(2));

        assertEquals(12, energy.available());
    }

    @Test
    public void subtractWhenAvailableTest() {
        IModifiableResource energy = new Energy(EnergyType.Fire, new Amount(10));

        energy.consume(new Amount(2));

        assertEquals(8, energy.available());
    }

    @Test
    public void subtractWhenAvailableUntilZeroTest() {
        IModifiableResource energy = new Energy(EnergyType.Fire, new Amount(10));

        energy.consume(new Amount(2));
        energy.consume(new Amount(8));

        assertEquals(0, energy.available());
    }

    @Test
    public void subtractWhenNotEnoughAvailableThrows() {
        IModifiableResource energy = new Energy(EnergyType.Fire, new Amount(10));

        assertThrows(InvalidAmountException.class, () -> energy.consume(new Amount(11)));
    }

    @Test
    public void energyIsEqualIfSameType() {
        IResource energy = new Energy(EnergyType.Fire, new Amount(10));
        IResource a = new Energy(EnergyType.Fire, new Amount(2));

        assertEquals(energy, a);
    }

    @Test
    public void energyIsEqualToItsType() {
        IResource energy = new Energy(EnergyType.Fire, new Amount(10));
        EnergyType a = EnergyType.Fire;
        EnergyType b = EnergyType.Water;

        assertEquals(energy, a);
        assertNotEquals(energy, b);
    }
}