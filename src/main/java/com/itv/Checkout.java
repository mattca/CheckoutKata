package com.itv;

import java.math.BigDecimal;
import java.util.HashMap;

public class Checkout {

    // item -> quantity
    private HashMap<Item, Integer> checkoutItems = new HashMap<>();

    public void scanItem(Item item) {
        checkoutItems.merge(item, 1, (a, b) -> a + b);
    }

    public HashMap<Item, Integer> getCheckoutItems() {
        return checkoutItems;
    }

    public Totals calculateTotals() {
        return new Totals(BigDecimal.ZERO, BigDecimal.ZERO);
    }

    public int getTotalItemsInCheckout() {
        return 0;
    }
}
