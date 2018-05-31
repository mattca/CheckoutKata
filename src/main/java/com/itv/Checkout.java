package com.itv;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class Checkout {

    private final Offers offers;

    // Map to represent items to checkout. Item -> Quantity of Item
    private Map<Item, Integer> checkoutItems = new HashMap<>();

    public Checkout() {
        this.offers = null;
    }

    public Checkout(Offers offers) {
        this.offers = offers;
    }

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
            reductions = reductions.add(calculateReductionsForItemWithQuantity(item, quantity));
        }

        return new Totals(subTotal, reductions);
    }

    private BigDecimal calculateReductionsForItemWithQuantity(Item item, int quantity) {
        if (offers == null) {
            return BigDecimal.ZERO;
        }

        Optional<MultibuyOffer> offerForItem = offers.getOfferForItemWithSku(item.getSku());
        if (!offerForItem.isPresent()) {
            return BigDecimal.ZERO;
        }

        MultibuyOffer offer = offerForItem.get();
        int itemsEligibleForDiscount = quantity / offer.getQuantityToEnableReduction();
        BigDecimal percentOffMultiplier = BigDecimal.valueOf((double) offer.getReducedItemPercentOff() / 100);

        return item.getPrice()
                .multiply(percentOffMultiplier)
                .multiply(BigDecimal.valueOf(itemsEligibleForDiscount));
    }

    public int getTotalItemsInCheckout() {
        return checkoutItems
                .values()
                .stream()
                .reduce(0, Integer::sum);
    }
}
