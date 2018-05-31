package com.itv;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class OffersTest {

    private static final MultibuyOffer BUY_TWO_GET_40_PC_OFF_THIRD_ITEM =
            new MultibuyOffer(3, 40);

    private static final MultibuyOffer BUY_ONE_GET_SECOND_HALF_PRICE =
            new MultibuyOffer(2, 50);

    @Test
    public void givenAnOfferIsRegisteredCheckItCanBeRetrievedBySku() {
        // given
        Item itemA = new Item("A", BigDecimal.valueOf(0.5));
        Item itemB = new Item("B", BigDecimal.valueOf(0.3));

        Offers offers = new Offers();

        // when
        offers.registerOffer(itemA, BUY_TWO_GET_40_PC_OFF_THIRD_ITEM);
        offers.registerOffer(itemB, BUY_ONE_GET_SECOND_HALF_PRICE);

        // then
        assertEquals(BUY_TWO_GET_40_PC_OFF_THIRD_ITEM, offers.getOfferForItemWithSku("A").get());
        assertEquals(BUY_ONE_GET_SECOND_HALF_PRICE, offers.getOfferForItemWithSku("B").get());
    }
}
