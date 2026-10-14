package de.sharpsharp.vendingmachine;

/** The four slots of the machine, one drink per slot, in their order on the front. */
public enum Drink {
    COLA("Cola",100),
    ORANGE("Orange",200),
    LEMON("Zitrone",300),
    BEER("Bier",400);

    private final String displayName;
    private final int price;

    Drink(String displayName, int price) {
      this.displayName = displayName;
      this.price = price;
    }

    /** The German name shown on the slot, e.g. "Zitrone". */
    public String displayName() {
        return displayName;
    }

  public int price() {
      return price;
  }
}
