package com.checkout;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class OffersTest {

    private static final Offer BUY_THREE_GET_40_PC_OFF_THIRD_ITEM =
            new Offer(3, 40);

    private static final Offer BUY_TWO_GET_SECOND_HALF_PRICE =
            new Offer(2, 50);

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void checkIllegalArgumentExceptionThrownWhenPassingNullItemWhenRegisteringOffer() {
        // given
        exception.expect(IllegalArgumentException.class);
        Offers offers = new Offers();

        // when
        offers.registerOffer(null, new Offer(2, 50));

        // then
        // exception is thrown
    }

    @Test
    public void checkIllegalArgumentExceptionThrownWhenPassingNullOfferWhenRegisteringOffer() {
        // given
        exception.expect(IllegalArgumentException.class);
        Offers offers = new Offers();

        // when
        offers.registerOffer(new Item("A", BigDecimal.ONE), null);

        // then
        // exception is thrown
    }

    @Test
    public void givenAnOfferIsRegisteredCheckItCanBeRetrievedBySku() {
        // given
        Item itemA = new Item("A", BigDecimal.valueOf(0.5));
        Item itemB = new Item("B", BigDecimal.valueOf(0.3));

        Offers offers = new Offers();

        // when
        offers.registerOffer(itemA, BUY_THREE_GET_40_PC_OFF_THIRD_ITEM);
        offers.registerOffer(itemB, BUY_TWO_GET_SECOND_HALF_PRICE);

        // then
        assertEquals(BUY_THREE_GET_40_PC_OFF_THIRD_ITEM, offers.getOfferForItemWithSku("A").get());
        assertEquals(BUY_TWO_GET_SECOND_HALF_PRICE, offers.getOfferForItemWithSku("B").get());
    }
}
