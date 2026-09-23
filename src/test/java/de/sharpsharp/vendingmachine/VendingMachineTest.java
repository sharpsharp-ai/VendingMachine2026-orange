package de.sharpsharp.vendingmachine;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class VendingMachineTest {

    @Test
    public void priceForEveryDrink() {
        var machine = new VendingMachine(new FakeClock());
        assertThat(machine.price(Drink.COLA), is(100));
        assertThat(machine.price(Drink.ORANGE), is(100));
        assertThat(machine.price(Drink.LEMON), is(100));
        assertThat(machine.price(Drink.BEER), is(200));
    }

    @Test
    public void insertCoinAddsToCredit() {
        var machine = new VendingMachine(new FakeClock());
        machine.insertCoin(50);
        assertThat(machine.credit(), is(50));
    }

    @Test
    public void selectDrinkWithNoCreditGivesNoCan() {
        var machine = new VendingMachine(new FakeClock());
        machine.selectDrink(Drink.COLA);
        assertThat(machine.outputTray(), not(contains(Drink.COLA)));
    }

    @Test
    public void selectDrinkReducesCredit() {
        var machine = new VendingMachine(new FakeClock());
        machine.insertCoin(100);
        machine.selectDrink(Drink.COLA);
        assertThat(machine.credit(), is(0));
    }
}
