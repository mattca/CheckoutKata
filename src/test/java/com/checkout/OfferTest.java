package com.checkout;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
}
