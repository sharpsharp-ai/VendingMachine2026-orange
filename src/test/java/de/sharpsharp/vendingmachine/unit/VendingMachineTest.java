package de.sharpsharp.vendingmachine.unit;

import org.junit.Test;

import java.time.LocalTime;

import de.sharpsharp.vendingmachine.Drink;
import de.sharpsharp.vendingmachine.FakeClock;
import de.sharpsharp.vendingmachine.VendingMachine;
import de.sharpsharp.vendingmachine.Coin;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

public class VendingMachineTest {

    @Test
    public void viewDrink_returnPrice() {
        Drink beer = Drink.BEER;
        int price = new VendingMachine(new FakeClock()).price(beer);

        assertThat(price, is(beer.price()));
    }

    @Test
    public void insertCoins_showsCredit() {
        VendingMachine machine = new VendingMachine(new FakeClock());
        machine.insertCoin(50);
        machine.insertCoin(50);
        machine.insertCoin(50);

        assertThat(machine.displayCredit(), is("1,50 €"));
    }

    @Test
    public void guthabenReichtNicht_KeineDose() {
        VendingMachine machine = new VendingMachine(new FakeClock());
        machine.insertCoin(50);
        machine.selectDrink(Drink.COLA);

        assertThat(machine.outputTray().isEmpty(), is(true));
    }

    @Test
    public void guthabenReichtNicht_Meldung() {
        VendingMachine machine = new VendingMachine(new FakeClock());
        machine.insertCoin(50);
        machine.selectDrink(Drink.COLA);

        assertThat(machine.message(), is("Zu wenig Geld"));
    }

    @Test
    public void guthabenReichtPreisWirdAbgezogen() {
        VendingMachine machine = new VendingMachine(new FakeClock());
        machine.insertCoin(150);
        machine.selectDrink(Drink.COLA);

        assertThat(machine.outputTray(), contains(Drink.COLA));
        assertThat(machine.credit(), is(50));
    }

    @Test
    public void cancelGibtWechselgeld() {
        VendingMachine machine = new VendingMachine(new FakeClock());
        machine.insertCoin(Coin.ONE_EURO.cents());
        machine.insertCoin(Coin.FIFTY_CENTS.cents());
        machine.cancel();
        assertThat(machine.credit(), is(0));
        assertThat(machine.coinReturn().size(), is(2));
    }

    @Test
    public void cancelGibtKorrektenWertZurueck() {
        VendingMachine machine = new VendingMachine(new FakeClock());
        machine.insertCoin(Coin.ONE_EURO.cents());
        machine.insertCoin(Coin.FIFTY_CENTS.cents());
        machine.cancel();
        int sum = machine.coinReturn().stream().mapToInt(Integer::intValue).sum();
        assertThat(sum, is(150));
    }

    @Test
    public void cancelOhneGuthabenGibtKeineMuenzen() {
        VendingMachine machine = new VendingMachine(new FakeClock());
        machine.cancel();
        assertThat(machine.credit(), is(0));
        assertThat(machine.coinReturn().isEmpty(), is(true));
    }

    @Test
    public void takeCoinsClearsReturn() {
        VendingMachine machine = new VendingMachine(new FakeClock());
        machine.insertCoin(Coin.ONE_EURO.cents());
        machine.cancel();
        machine.takeCoins();
        assertThat(machine.coinReturn().isEmpty(), is(true));
    }

    @Test
    public void bierVor16UhrWirdAbgelehnt() {
        FakeClock clock = new FakeClock();
        clock.set(LocalTime.of(15, 59));
        VendingMachine machine = new VendingMachine(clock);

        machine.insertCoin(200);
        machine.selectDrink(Drink.BEER);

        assertThat(machine.message(), is("Kein Bier vor 16"));
        assertThat(machine.credit(), is(200));
        assertThat(machine.outputTray().isEmpty(), is(true));
    }
}
