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

    @Wenn("ich eine {coin} Münze einwerfe")
    public void iInsertCoin(Coin coin) {
        machine.insertCoin(coin.cents());
    }

    @Dann("wird {string} angezeigt")
    public void showResult(String betrag) {
        assertThat(machine.displayCredit(), is(betrag));
    }

    @Dann("liegt kein Getränk im Ausgabefach")
    public void noDrinkInOutputTray() {
        assertThat(machine.outputTray().isEmpty(), is(true));
    }

    @Dann("der Automat meldet {string}")
    public void theMachineSays(String text) {
        assertThat(machine.message(), is(text));
    }

    @Dann("bleibt das Guthaben bei {string}")
    public void creditRemainsAt(String betrag) {
        assertThat(machine.displayCredit(), is(betrag));
    }

    @Wenn("ich Abbruch drücke")
    public void iCancel() {
        machine.cancel();
    }

    @Dann("liegen {int} Münzen in der Münzrückgabe")
    public void coinsInCoinReturn(int count) {
        assertThat(machine.coinReturn().size(), is(count));
    }

    @Dann("liegen keine Münzen in der Münzrückgabe")
    public void noCoinsInCoinReturn() {
        assertThat(machine.coinReturn().isEmpty(), is(true));
    }

    @Wenn("ich die Münzrückgabe leere")
    public void iTakeCoins() {
        machine.takeCoins();
    }

}
