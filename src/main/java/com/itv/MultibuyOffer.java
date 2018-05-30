package com.itv;

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
}
