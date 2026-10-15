package de.sharpsharp.vendingmachine.unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import de.sharpsharp.vendingmachine.Drink;
import de.sharpsharp.vendingmachine.FakeClock;
import de.sharpsharp.vendingmachine.VendingMachine;
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
  public void noMoneyInserted_freeDrink() {
    Drink cola = Drink.COLA;
    vendingMachine.selectDrink(cola);

    assertThat(vendingMachine.outputTray(), contains(cola));
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
}
