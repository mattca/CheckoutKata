package com.itv;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CheckoutTest {

    private static final MultibuyOffer BUY_THREE_GET_40_PC_OFF_THIRD_ITEM =
            new MultibuyOffer(3, 40);

    private static final MultibuyOffer BUY_TWO_GET_SECOND_HALF_PRICE =
            new MultibuyOffer(2, 50);

    private static final int IS_EQUAL = 0; // Used for BigDecimal compareTo method

    @Test
    public void testCheckoutTotalsWithSingleItemNoReductionsApply() {
        // given
        Item item = new Item("A", BigDecimal.valueOf(0.5));

        Offers offers = new Offers();
        offers.registerOffer(item, BUY_THREE_GET_40_PC_OFF_THIRD_ITEM);

        Checkout checkout = new Checkout(offers);

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
        Item itemC = new Item("C", BigDecimal.valueOf(0.2));
        Item itemD = new Item("D", BigDecimal.valueOf(0.15));

        // when
        checkout.scanItem(itemC);
        checkout.scanItem(itemD);
        Totals totals = checkout.calculateTotals();

        // then
        assertEquals(IS_EQUAL, BigDecimal.ZERO.compareTo(totals.getReductions()));
        assertEquals(IS_EQUAL, BigDecimal.valueOf(0.35).compareTo(totals.getSubTotal()));
        assertEquals(IS_EQUAL, BigDecimal.valueOf(0.35).compareTo(totals.getTotal()));
    }

    @Test
    public void testCheckoutTotalsWithAnItemWithReductions() {
        // given
        Item itemB = new Item("B", BigDecimal.valueOf(0.3));

        Offers offers = new Offers();
        offers.registerOffer(itemB, BUY_TWO_GET_SECOND_HALF_PRICE);

        Checkout checkout = new Checkout(offers);

        // when
        checkout.scanItem(itemB);
        checkout.scanItem(itemB);
        checkout.scanItem(itemB);
        Totals totals = checkout.calculateTotals();

        // then
        assertEquals(IS_EQUAL, BigDecimal.valueOf(0.15).compareTo(totals.getReductions()));
        assertEquals(IS_EQUAL, BigDecimal.valueOf(0.90).compareTo(totals.getSubTotal()));
        assertEquals(IS_EQUAL, BigDecimal.valueOf(0.75).compareTo(totals.getTotal()));
    }

    @Test
    public void testCheckoutTotalsWithAnItemWithReductionsAndAnItemWhereReductionDidntApply() {
        // given
        Item itemA = new Item("A", BigDecimal.valueOf(0.5));
        Item itemB = new Item("B", BigDecimal.valueOf(0.3));

        Offers offers = new Offers();
        offers.registerOffer(itemA, BUY_THREE_GET_40_PC_OFF_THIRD_ITEM);
        offers.registerOffer(itemB, BUY_TWO_GET_SECOND_HALF_PRICE);

        Checkout checkout = new Checkout(offers);

        // when
        checkout.scanItem(itemB);
        checkout.scanItem(itemA);
        checkout.scanItem(itemB);
        Totals totals = checkout.calculateTotals();

        // then
        assertEquals(IS_EQUAL, BigDecimal.valueOf(0.15).compareTo(totals.getReductions()));
        assertEquals(IS_EQUAL, BigDecimal.valueOf(1.10).compareTo(totals.getSubTotal()));
        assertEquals(IS_EQUAL, BigDecimal.valueOf(0.95).compareTo(totals.getTotal()));
    }

    @Test
    public void testCheckoutTotalsWhereMultipleReductionsApplyForASingleItemType() {
        // given
        Item itemB = new Item("B", BigDecimal.valueOf(0.3));

        Offers offers = new Offers();
        offers.registerOffer(itemB, BUY_TWO_GET_SECOND_HALF_PRICE);

        Checkout checkout = new Checkout(offers);

        // when
        checkout.scanItem(itemB);
        checkout.scanItem(itemB);
        checkout.scanItem(itemB);
        checkout.scanItem(itemB);
        checkout.scanItem(itemB);
        checkout.scanItem(itemB);
        Totals totals = checkout.calculateTotals();

        // then
        assertEquals(IS_EQUAL, BigDecimal.valueOf(0.45).compareTo(totals.getReductions()));
        assertEquals(IS_EQUAL, BigDecimal.valueOf(1.80).compareTo(totals.getSubTotal()));
        assertEquals(IS_EQUAL, BigDecimal.valueOf(1.35).compareTo(totals.getTotal()));
    }

    @Test
    public void testCheckoutTotalsComplexBasketCheck() {
        // given
        Item itemA = new Item("A", BigDecimal.valueOf(0.5));
        Item itemB = new Item("B", BigDecimal.valueOf(0.3));
        Item itemC = new Item("C", BigDecimal.valueOf(0.2));
        Item itemD = new Item("D", BigDecimal.valueOf(0.15));

        Offers offers = new Offers();
        offers.registerOffer(itemA, BUY_THREE_GET_40_PC_OFF_THIRD_ITEM);
        offers.registerOffer(itemB, BUY_TWO_GET_SECOND_HALF_PRICE);

        Checkout checkout = new Checkout(offers);

        // when
        checkout.scanItem(itemB);
        checkout.scanItem(itemA);
        checkout.scanItem(itemB);
        checkout.scanItem(itemA);
        checkout.scanItem(itemB);
        checkout.scanItem(itemA);
        checkout.scanItem(itemD);
        checkout.scanItem(itemB);
        checkout.scanItem(itemB);
        checkout.scanItem(itemC);
        checkout.scanItem(itemB);
        checkout.scanItem(itemD);
        checkout.scanItem(itemB);
        checkout.scanItem(itemC);
        checkout.scanItem(itemC);
        Totals totals = checkout.calculateTotals();

        // then
        assertEquals(IS_EQUAL, BigDecimal.valueOf(0.65).compareTo(totals.getReductions()));
        assertEquals(IS_EQUAL, BigDecimal.valueOf(4.50).compareTo(totals.getSubTotal()));
        assertEquals(IS_EQUAL, BigDecimal.valueOf(3.85).compareTo(totals.getTotal()));
    }
}
