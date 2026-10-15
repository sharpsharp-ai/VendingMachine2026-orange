package de.sharpsharp.vendingmachine.unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import de.sharpsharp.vendingmachine.Drink;
import de.sharpsharp.vendingmachine.FakeClock;
import de.sharpsharp.vendingmachine.VendingMachine;
import de.sharpsharp.vendingmachine.Coin;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

public class VendingMachineTest {
  private VendingMachine vendingMachine;
  @BeforeEach
  public void setup() {
    vendingMachine = new VendingMachine(new FakeClock());
  }

  @Test
  public void viewDrink_returnPrice(){
    Drink beer = Drink.BEER;
    int price = vendingMachine.price(beer);

    assertThat(price,is(beer.price()));
  }

  @Test
  public void insertCoins_showsCredit() {
    vendingMachine.insertCoin(50);
    vendingMachine.insertCoin(50);
    vendingMachine.insertCoin(50);

    assertThat(vendingMachine.displayCredit(), is("1,50 €"));
  }

  @Test
  public void guthabenReichtNicht_KeineDose() {
    vendingMachine.insertCoin(50);
    vendingMachine.selectDrink(Drink.COLA);

    assertThat(vendingMachine.outputTray().isEmpty(), is(true));
  }

  @Test
  public void guthabenReichtNicht_Meldung() {
    vendingMachine.insertCoin(50);
    vendingMachine.selectDrink(Drink.COLA);

    assertThat(vendingMachine.message(), is("Zu wenig Geld"));
  }

  @Test
  public void guthabenReichtPreisWirdAbgezogen() {
    vendingMachine.insertCoin(150);
    vendingMachine.selectDrink(Drink.COLA);

    assertThat(vendingMachine.outputTray(), contains(Drink.COLA));
    assertThat(vendingMachine.credit(), is(50));
  }

  @Test
  public void cancelGibtWechselgeld() {
    vendingMachine.insertCoin(Coin.ONE_EURO.cents());
    vendingMachine.insertCoin(Coin.FIFTY_CENTS.cents());
    vendingMachine.cancel();
    assertThat(vendingMachine.credit(), is(0));
    assertThat(vendingMachine.coinReturn().size(), is(2));
  }

  @Test
  public void cancelGibtKorrektenWertZurueck() {
    vendingMachine.insertCoin(Coin.ONE_EURO.cents());
    vendingMachine.insertCoin(Coin.FIFTY_CENTS.cents());
    vendingMachine.cancel();
    int sum = vendingMachine.coinReturn().stream().mapToInt(Integer::intValue).sum();
    assertThat(sum, is(150));
  }

  @Test
  public void cancelOhneGuthabenGibtKeineMuenzen() {
    vendingMachine.cancel();
    assertThat(vendingMachine.credit(), is(0));
    assertThat(vendingMachine.coinReturn().isEmpty(), is(true));
  }

  @Test
  public void takeCoinsClearsReturn() {
    vendingMachine.insertCoin(Coin.ONE_EURO.cents());
    vendingMachine.cancel();
    vendingMachine.takeCoins();
    assertThat(vendingMachine.coinReturn().isEmpty(), is(true));
  }
}
