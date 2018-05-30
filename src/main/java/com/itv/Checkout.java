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

            int itemsEligibleForDiscount = 0;
            BigDecimal percentOffMultiplier = BigDecimal.ONE;
            if (item.getMultibuyOffer() != null) {
                itemsEligibleForDiscount = quantity / item.getMultibuyOffer().getQuantityToEnableReduction();
                percentOffMultiplier = BigDecimal.valueOf((double) item.getMultibuyOffer().getReducedItemPercentOff() / 100);
            }

            subTotal = subTotal.add(item.getPrice().multiply(new BigDecimal(quantity)));
            reductions = reductions.add(
                    item.getPrice()
                            .multiply(percentOffMultiplier)
                            .multiply(BigDecimal.valueOf(itemsEligibleForDiscount)));
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
