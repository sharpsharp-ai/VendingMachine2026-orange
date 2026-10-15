package de.sharpsharp.vendingmachine;

/** The three coin denominations the machine accepts. */
public enum Coin {
    FIFTY_CENTS(50),
    ONE_EURO(100),
    TWO_EUROS(200);

    private final int cents;

    Coin(int cents) {
        this.cents = cents;
    }

    public int cents() {
        return cents;
    }
}
