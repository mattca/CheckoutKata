package com.checkout;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class OfferTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void checkIllegalArgumentExceptionThrownWhenPassingQuantityToEnableReductionUnder1() {
        // given
        exception.expect(IllegalArgumentException.class);

        // when
        Offer offer = new Offer(0, 50);

        // then
        // exception is thrown
    }

    @Test
    public void checkValidOfferPopulatesCorrectValues() {
        // given

        // when
        Offer offer = new Offer(2, 100);

        // then
        assertEquals(2, offer.getQuantityToEnableReduction());
        assertEquals(100, offer.getReducedItemPercentOff());
    }

    @Test
    public void checkIllegalArgumentExceptionThrownWhenPassingZeroReducedItemPercentOff() {
        // given
        exception.expect(IllegalArgumentException.class);

        // when
        Offer offer = new Offer(1, 0);

        // then
        // exception is thrown
    }

    @Test
    public void checkIllegalArgumentExceptionThrownWhenPassingReducedItemPercentOffOver100() {
        // given
        exception.expect(IllegalArgumentException.class);

        // when
        Offer offer = new Offer(2, 101);

        // then
        // exception is thrown
    }

    @Test
    public void checkIllegalArgumentExceptionThrownIfOfferParametersDescribeASingleFreeItem() {
        // given
        exception.expect(IllegalArgumentException.class);

        // when
        Offer offer = new Offer(1, 100);

        // then
        // exception is thrown
    }
}
