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
        BigDecimal reductions = BigDecimal.ZERO;
        BigDecimal subTotal = BigDecimal.ZERO;

        for (Entry<Item, Integer> itemAndQuantity : checkoutItems.entrySet()) {
            Item item = itemAndQuantity.getKey();
            int quantity = itemAndQuantity.getValue();

            subTotal = subTotal.add(item.getPrice().multiply(new BigDecimal(quantity)));
        }

        return new Totals(subTotal, reductions);
    }

    public int getTotalItemsInCheckout() {
        return checkoutItems
                .values()
                .stream()
                .reduce(0, Integer::sum);
    }
}
