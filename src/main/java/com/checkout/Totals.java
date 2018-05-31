package com.checkout;

import java.math.BigDecimal;

/**
 * Class to represent totals at the checkout
 */
public class Totals {

    private final BigDecimal subTotal;

    private final BigDecimal reductions;

    private final BigDecimal total;

    // package private
    Totals(BigDecimal subTotal, BigDecimal reductions) {
        this.subTotal = subTotal;
        this.reductions = reductions;
        this.total = subTotal.subtract(reductions);
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public BigDecimal getReductions() {
        return reductions;
    }

    public BigDecimal getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Totals{" +
                "subTotal=" + subTotal +
                ", reductions=" + reductions +
                ", total=" + total +
                '}';
    }
}
