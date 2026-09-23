package de.sharpsharp.vendingmachine;

import io.cucumber.java.PendingException;
import io.cucumber.java.de.Angenommen;
import io.cucumber.java.de.Dann;
import io.cucumber.java.de.Wenn;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Step definitions for the feature files. The scenarios talk to the machine directly,
 * never through the web page or HTTP. Cucumber creates a new instance of this class
 * for every scenario, so every scenario starts with a freshly built machine.
 */
public class VendingMachineSteps {

    private final FakeClock clock = new FakeClock();
    private final VendingMachine machine = new VendingMachine(clock);

    @Angenommen("der Automat ist frisch gestartet")
    public void theMachineIsFreshlyStarted() {
        // Nothing to do: Cucumber builds this class, and with it the machine, fresh for every scenario.
    }

    @Wenn("ich {drink} wähle")
    public void iSelect(Drink drink) {
        machine.selectDrink(drink);
    }

    @Dann("liegt eine Dose {drink} im Ausgabefach")
    public void oneCanLiesInTheOutputTray(Drink drink) {
        assertThat(machine.outputTray(), contains(drink));
    }

    @Dann("wird auf dem Display unter jedem Getränk der zugehörige Preis angezeigt")
    public void everyDrinkHasAPrice() {
        for (Drink drink: Drink.values()) {
            assertThat(machine.price(drink), notNullValue());
        }
    }

    @Wenn("ich {int} Münze zu je {int} Cent einwerfe")
    public void insertXCoinsValuedY(int numberCoins, int coinValueCents) {
        for (int i = 0; i < numberCoins; i++)
        {
            machine.insertCoin(coinValueCents);
        }
    }

    @Dann("habe ich {int} Cent Guthaben")
    public void habeIchCentGuthaben(int creditInCents) {
        assertThat(machine.credit(), is(creditInCents));
    }
}
