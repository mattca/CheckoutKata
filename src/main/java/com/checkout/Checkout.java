package com.checkout;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

/**
 * Class which encapsulates the concept of a checkout
 */
public class Checkout {

    private final Offers offers;

    private Map<Item, Integer> checkoutItems = new HashMap<>();

    public Checkout() {
        this.offers = null;
    }

    public Checkout(Offers offers) {
        this.offers = offers;
    }

    /**
     * Scan an item at the checkout
     * @param item
     */
    public void scanItem(Item item) {
        checkoutItems.merge(item, 1, (a, b) -> a + b);
    }

    /**
     * Retrieves checkout items
     * @return A map where the key is the item, and the value is the quantity of the item
     */
    public Map<Item, Integer> getCheckoutItems() {
        return checkoutItems;
    }

    /**
     * Calculates the totals of the items scanned through the checkout
     * @return Totals object representing subtotal, reductions, total
     */
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

        Optional<Offer> offerForItem = offers.getOfferForItemWithSku(item.getSku());
        if (!offerForItem.isPresent()) {
            return BigDecimal.ZERO;
        }

        Offer offer = offerForItem.get();
        int itemsEligibleForDiscount = quantity / offer.getQuantityToEnableReduction();
        BigDecimal percentOffMultiplier = BigDecimal.valueOf((double) offer.getReducedItemPercentOff() / 100);

        return item.getPrice()
                .multiply(percentOffMultiplier)
                .multiply(BigDecimal.valueOf(itemsEligibleForDiscount));
    }

    /**
     * Returns a count of the total number of items in the checkout
     * @return
     */
    public int getTotalNumberOfItemsInCheckout() {
        return checkoutItems
                .values()
                .stream()
                .reduce(0, Integer::sum);
    }
}
