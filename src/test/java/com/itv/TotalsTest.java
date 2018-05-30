package com.itv;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TotalsTest {
    @Test
    public void totalsTotalShouldBeSubTotalMinusReductions() {
        // given
        BigDecimal subTotal = BigDecimal.valueOf(3);
        BigDecimal reductions = BigDecimal.ONE;

        // when
        Totals totals = new Totals(subTotal, reductions);

        // then
        assertEquals(0, BigDecimal.valueOf(3).compareTo(totals.getSubTotal()));
        assertEquals(0, BigDecimal.ONE.compareTo(totals.getReductions()));
        assertEquals(0, BigDecimal.valueOf(2).compareTo(totals.getTotal()));
    }
}
