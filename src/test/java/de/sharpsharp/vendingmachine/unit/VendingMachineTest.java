package de.sharpsharp.vendingmachine.unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import de.sharpsharp.vendingmachine.Drink;
import de.sharpsharp.vendingmachine.FakeClock;
import de.sharpsharp.vendingmachine.VendingMachine;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class VendingMachineTest {
  private VendingMachine vendingMachine;
  @BeforeEach
  public void setup() {
    vendingMachine = new VendingMachine(new FakeClock());
  }

  @Test
  public void noMoneyInserted_freeDrink() {
    vendingMachine.selectDrink(Drink.COLA);

    assertThat(vendingMachine.outputTray(), contains(Drink.COLA));
  }
}
