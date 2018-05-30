package com.itv;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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
        BigDecimal reductions = BigDecimal.ZERO;

        return new Totals(checkoutItems.entrySet().stream().findFirst().get().getKey().getPrice(), reductions);
    }

    public int getTotalItemsInCheckout() {
        return checkoutItems
                .values()
                .stream()
                .reduce(0, Integer::sum);
    }
}
