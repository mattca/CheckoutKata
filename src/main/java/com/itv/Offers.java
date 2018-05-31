package com.itv;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class Offers {

    private final Map<Item, MultibuyOffer> offers = new HashMap<>();

    public void registerOffer(Item item, MultibuyOffer multibuyOffer) {
        offers.put(item, multibuyOffer);
    }

    public Optional<MultibuyOffer> getOfferForItemWithSku(String sku) {
        return offers
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().getSku().equals(sku))
                .map(Entry::getValue)
                .findFirst();
    }
}
