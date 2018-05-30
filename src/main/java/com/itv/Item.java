package com.itv;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {

    private final String sku;

    private final BigDecimal price;

    private final MultibuyOffer multibuyOffer;

    public Item(String sku, BigDecimal price) {
        this.sku = sku;
        this.price = price;
        this.multibuyOffer = null;
    }

    public Item(String sku, BigDecimal price, MultibuyOffer multibuyOffer) {
        this.sku = sku;
        this.price = price;
        this.multibuyOffer = multibuyOffer;
    }

    public String getSku() {
        return sku;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public MultibuyOffer getMultibuyOffer() {
        return multibuyOffer;
    }

    // Identity based on sku
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(sku, item.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }

    @Override
    public String toString() {
        return "Item{" +
                "sku='" + sku + '\'' +
                ", price=" + price +
                ", multibuyOffer=" + multibuyOffer +
                '}';
    }
}
