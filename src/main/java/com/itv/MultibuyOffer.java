package com.itv;

import java.util.Objects;

public class MultibuyOffer {

    private final int quantityToEnableReduction;

    private final int reducedItemPercentOff;

    public MultibuyOffer(int quantityToEnableReduction, int reducedItemPercentOff) {
        this.quantityToEnableReduction = quantityToEnableReduction;
        this.reducedItemPercentOff = reducedItemPercentOff;
    }

    public int getQuantityToEnableReduction() {
        return quantityToEnableReduction;
    }

    public int getReducedItemPercentOff() {
        return reducedItemPercentOff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultibuyOffer that = (MultibuyOffer) o;
        return quantityToEnableReduction == that.quantityToEnableReduction &&
                reducedItemPercentOff == that.reducedItemPercentOff;
    }

    @Override
    public int hashCode() {

        return Objects.hash(quantityToEnableReduction, reducedItemPercentOff);
    }
}
