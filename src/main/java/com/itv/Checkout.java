package com.itv;

import java.util.HashMap;

public class Checkout {

    private HashMap<Item, Integer> itemsAndQuantities;

    public void scanItem(Item item) {
        itemsAndQuantities.merge(item, 1, (a, b) -> a + b);
    }
}
