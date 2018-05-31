package com.checkout;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

/**
 * Class used to register offers
 */
public class Offers {

    private final Map<Item, MultibuyOffer> offers = new HashMap<>();

    /**
     * Register that an item has a particular multibuy offer
     *
     * @param item          Item which has an offer
     * @param multibuyOffer Offer to relate to the item
     */
    public void registerOffer(Item item, MultibuyOffer multibuyOffer) {
        offers.put(item, multibuyOffer);
    }

    /**
     * Retrieves details of an offer given an item SKU
     *
     * @param sku Item SKU
     * @return Optional of the offer. Empty optional if no offer found for that SKU
     */
    public Optional<MultibuyOffer> getOfferForItemWithSku(String sku) {
        return offers
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().getSku().equals(sku))
                .map(Entry::getValue)
                .findFirst();
    }
}
