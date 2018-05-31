package com.itv;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {

    private final String sku;

    private final BigDecimal price;

    public Item(String sku, BigDecimal price) {
        if (sku == null || price == null) {
            throw new IllegalArgumentException("sku or price must not be null when creating an Item");
        }
        this.sku = sku;
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public BigDecimal getPrice() {
        return price;
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
                '}';
    }
}
