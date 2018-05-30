package com.itv;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckoutScanTest {

    private static final MultibuyOffer BUY_TWO_GET_40_PC_OFF_THIRD_ITEM =
            new MultibuyOffer(3, 40);

    private static final MultibuyOffer BUY_ONE_GET_SECOND_HALF_PRICE =
            new MultibuyOffer(3, 50);

    @Test
    public void scanItemShouldAddToCheckoutItems() {
        // given
        Checkout checkout = new Checkout();
        Item item = new Item("A", BigDecimal.valueOf(0.5), BUY_TWO_GET_40_PC_OFF_THIRD_ITEM);

        // when
        checkout.scanItem(item);

        // then
        assertTrue(checkout.getCheckoutItems().containsKey(item));
        assertEquals(1, checkout.getCheckoutItems().size());
        assertEquals(1, checkout.getTotalItemsInCheckout());
    }

    @Test
    public void scanMultipleItemsOfSameTypeShouldAddToCheckoutItems() {
        // given
        Checkout checkout = new Checkout();
        Item item = new Item("A", BigDecimal.valueOf(0.5), BUY_TWO_GET_40_PC_OFF_THIRD_ITEM);

        // when
        checkout.scanItem(item);
        checkout.scanItem(item);

        // then
        assertTrue(checkout.getCheckoutItems().containsKey(item));
        assertEquals(Integer.valueOf(2), checkout.getCheckoutItems().get(item));
        assertEquals(1, checkout.getCheckoutItems().size());
        assertEquals(2, checkout.getTotalItemsInCheckout());
    }

    @Test
    public void scanMultipleItemsOfVariousTypesShouldAddToCheckoutItems() {
        // given
        Checkout checkout = new Checkout();
        Item itemA = new Item("A", BigDecimal.valueOf(0.5), BUY_TWO_GET_40_PC_OFF_THIRD_ITEM);
        Item itemB = new Item("B", BigDecimal.valueOf(0.3), BUY_ONE_GET_SECOND_HALF_PRICE);

        // when
        checkout.scanItem(itemA);
        checkout.scanItem(itemA);
        checkout.scanItem(itemB);
        checkout.scanItem(itemB);

        // then
        assertTrue(checkout.getCheckoutItems().containsKey(itemA));
        assertTrue(checkout.getCheckoutItems().containsKey(itemB));
        assertEquals(Integer.valueOf(2), checkout.getCheckoutItems().get(itemA));
        assertEquals(Integer.valueOf(2), checkout.getCheckoutItems().get(itemB));
        assertEquals(2, checkout.getCheckoutItems().size());
        assertEquals(4, checkout.getTotalItemsInCheckout());
    }
}
