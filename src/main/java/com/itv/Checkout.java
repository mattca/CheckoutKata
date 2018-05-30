package com.itv;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Checkout {

    // item -> quantity
    private Map<Item, Integer> checkoutItems = new HashMap<>();

    public void scanItem(Item item) {
        checkoutItems.merge(item, 1, (a, b) -> a + b);
    }

    public Map<Item, Integer> getCheckoutItems() {
        return checkoutItems;
    }

    public Totals calculateTotals() {
        return new Totals(BigDecimal.ZERO, BigDecimal.ZERO);
    }

    public int getTotalItemsInCheckout() {
        return checkoutItems
                .values()
                .stream()
                .reduce(0, Integer::sum);
    }
}
