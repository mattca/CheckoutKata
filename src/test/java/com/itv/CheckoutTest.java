package com.itv;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CheckoutTest {

    private static final MultibuyOffer BUY_TWO_GET_40_PC_OFF_THIRD_ITEM =
            new MultibuyOffer(3, 40);

    private static final MultibuyOffer BUY_ONE_GET_SECOND_HALF_PRICE =
            new MultibuyOffer(3, 50);

    private static final int IS_EQUAL = 0;

    @Test
    public void testCheckoutTotalsWithSingleItemNoReductionsApply() {
        // given
        Checkout checkout = new Checkout();
        Item item = new Item("A", BigDecimal.valueOf(0.5), BUY_TWO_GET_40_PC_OFF_THIRD_ITEM);

        // when
        checkout.scanItem(item);
        Totals totals = checkout.calculateTotals();

        // then
        assertEquals(IS_EQUAL, BigDecimal.ZERO.compareTo(totals.getReductions()));
        assertEquals(IS_EQUAL, BigDecimal.valueOf(0.5).compareTo(totals.getSubTotal()));
        assertEquals(IS_EQUAL, BigDecimal.valueOf(0.5).compareTo(totals.getTotal()));
    }

    @Test
    public void testCheckoutTotalIsZeroWithNoItems() {
        // given
        Checkout checkout = new Checkout();

        // when
        Totals totals = checkout.calculateTotals();

        // then
        assertEquals(IS_EQUAL, BigDecimal.ZERO.compareTo(totals.getReductions()));
        assertEquals(IS_EQUAL, BigDecimal.ZERO.compareTo(totals.getSubTotal()));
        assertEquals(IS_EQUAL, BigDecimal.ZERO.compareTo(totals.getTotal()));
    }

    @Test
    public void testCheckoutTotalsWithMultipleItemsNoReductions() {
        // given
        Checkout checkout = new Checkout();
        Item itemC = new Item("C", BigDecimal.valueOf(0.2), null);
        Item itemD = new Item("D", BigDecimal.valueOf(0.15), null);

        // when
        checkout.scanItem(itemC);
        checkout.scanItem(itemD);
        Totals totals = checkout.calculateTotals();

        // then
        assertEquals(IS_EQUAL, BigDecimal.ZERO.compareTo(totals.getReductions()));
        assertEquals(IS_EQUAL, BigDecimal.valueOf(0.35).compareTo(totals.getSubTotal()));
        assertEquals(IS_EQUAL, BigDecimal.valueOf(0.35).compareTo(totals.getTotal()));
    }
}
