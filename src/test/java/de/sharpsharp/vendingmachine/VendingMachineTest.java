package de.sharpsharp.vendingmachine;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class VendingMachineTest {

    @Test
    public void priceForEveryDrink() {
        var machine = new VendingMachine(new FakeClock());
        assertThat(machine.price(Drink.COLA), is(100));
        assertThat(machine.price(Drink.ORANGE), is(100));
        assertThat(machine.price(Drink.LEMON), is(100));
        assertThat(machine.price(Drink.BEER), is(200));
    }
}
