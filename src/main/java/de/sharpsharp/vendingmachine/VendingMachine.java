package de.sharpsharp.vendingmachine;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * The machine. So far it does nothing: every action is ignored, and what it reports is the
 * state right after switching it on. The rules in the README are still to be built.
 * <p>
 * Amounts are in cents, messages are plain text. The methods are synchronized because the
 * web page may send several requests at once, while there is one machine and one customer at a time.
 */
public class VendingMachine {

    public static final int CANS_PER_SLOT = 5;
    public static final int SOFTDRINK_PRICE = 100;
    public static final int BEER_PRICE = 200;

    private static final Map<Drink, Integer> PRICES = new EnumMap<>(Drink.class);

    static {
        for (Drink d : Drink.values()) {
            PRICES.put(d, d == Drink.BEER ? BEER_PRICE : SOFTDRINK_PRICE);
        }
    }

    private final Map<Drink, Integer> stock = new EnumMap<>(Drink.class);
    /** The time of day, for rules that depend on it. Never read the system time directly: ask the clock. */
    private final Clock clock;

    private int credit = 0;

    private List<Drink> outputTrayDrinks;

    public VendingMachine(Clock clock) {
        this.clock = clock;
        outputTrayDrinks = new ArrayList<>();
        for (Drink drink : Drink.values()) {
            stock.put(drink, CANS_PER_SLOT);
        }
    }

    // ---- What a customer can do --------------------------------------------

    public synchronized void insertCoin(int cents) {
        credit += cents;
    }

    public synchronized void selectDrink(Drink drink) {
        outputTrayDrinks.add(drink);

    }

    public synchronized void cancel() {
    }

    /** Empties the output tray and returns the cans that were in it. */
    public synchronized List<Drink> takeDrinks() {
        return List.of();
    }

    /** Empties the coin return and returns the coins that were in it, in cents. */
    public synchronized List<Integer> takeCoins() {
        return List.of();
    }

    // ---- What the machine shows ---------------------------------------------

    /** In cents. */
    public synchronized int credit() {
        return credit;
    }

    public synchronized String message() {
        return "Bitte Münzen einwerfen";
    }

    /** True while the display shows a refusal such as "Ausverkauft"; the page then flashes it red. */
    public synchronized boolean refused() {
        return false;
    }

    public synchronized int stock(Drink drink) {
        return stock.get(drink);
    }

    /** The price shown behind the name of the drink, in cents. Null: the machine knows no price yet. */
    public synchronized Integer price(Drink drink) {
        return PRICES.get(drink);
    }

    /** The cans that dropped out and have not been taken yet. */
    public synchronized List<Drink> outputTray() {
        return outputTrayDrinks;
    }

    /** The coins that came back and have not been taken yet, in cents. */
    public synchronized List<Integer> coinReturn() {
        return List.of();
    }
}
