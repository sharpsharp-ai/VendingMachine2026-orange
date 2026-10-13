package de.sharpsharp.vendingmachine.unit;

import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import de.sharpsharp.vendingmachine.Drink;
import de.sharpsharp.vendingmachine.FakeClock;
import de.sharpsharp.vendingmachine.VendingMachine;
import io.cucumber.java.BeforeAll;
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
    vendingMachine.selectDrink(Drink.COLA);


    assertThat(vendingMachine.outputTray(), contains(Drink.COLA));
  }
}
