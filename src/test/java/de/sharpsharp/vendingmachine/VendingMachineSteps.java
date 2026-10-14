package de.sharpsharp.vendingmachine;

import java.text.DecimalFormat;
import io.cucumber.java.de.Angenommen;
import io.cucumber.java.de.Dann;
import io.cucumber.java.de.Wenn;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

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

    @Dann("ist der Preis von der {drink} erkennbar")
    public void lookAtDrink(Drink drink){
        DecimalFormat df = new DecimalFormat("0,00");
        String formattedPrice = df.format(drink.price());
        assertThat(machine.displayPrice(drink), is("%s %s €".formatted(drink.displayName(), formattedPrice)));
    }

    @Wenn("man {int} Münzen a {int} Cent einwirft")
    public void insertCoins(int anzahl, int betrag) {
        for (int i = 0; i< anzahl; i++) {
            machine.insertCoin(betrag);
        }
    }

    @Dann("wird {string} angezeigt")
    public void showResult(String betrag) {
        assertThat(machine.displayCredit(), is(betrag));
    }

}
