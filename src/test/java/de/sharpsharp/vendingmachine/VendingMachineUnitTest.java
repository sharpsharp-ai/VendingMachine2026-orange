package de.sharpsharp.vendingmachine;

import java.util.List;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class VendingMachineUnitTest {
  @Test
  public void selectDrinkCola_ColaIsThere() {
    // arrange
    Clock clock = new FakeClock();
    Drink cola = Drink.COLA;

    VendingMachine vendingMachine = new VendingMachine(clock);
    // act
    vendingMachine.selectDrink(cola);
    // assert
    List<Drink> drinks = vendingMachine.outputTray();
    assertThat(drinks, contains(cola));
  }
}
