package com.checkout;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

/**
 * Class used to register offers
 */
public class Offers {

    private final Map<Item, Offer> offers = new HashMap<>();

    /**
     * Register that an item has a particular offer
     *
     * @param item  Item which has an offer
     * @param offer Offer to relate to the item
     */
    public void registerOffer(Item item, Offer offer) {
        if (item == null || offer == null) {
            throw new IllegalArgumentException("Item or offer must not be null");
        }
        offers.put(item, offer);
    }

    /**
     * Retrieves details of an offer given an item SKU
     *
     * @param sku Item SKU
     * @return Optional of the offer. Empty optional if no offer found for that SKU
     */
    public Optional<Offer> getOfferForItemWithSku(String sku) {
        return offers
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().getSku().equals(sku))
                .map(Entry::getValue)
                .findFirst();
    }
}
