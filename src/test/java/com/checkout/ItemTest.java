package com.checkout;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

public class ItemTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void checkIllegalArgumentExceptionThrownWhenPassingNullSkuToConstructor() {
        // given
        exception.expect(IllegalArgumentException.class);

        // when
        Item item = new Item(null, BigDecimal.ONE);

        // then
        // exception is thrown
    }

    @Test
    public void checkIllegalArgumentExceptionThrownWhenPassingNullPriceToConstructor() {
        // given
        exception.expect(IllegalArgumentException.class);

        // when
        Item item = new Item("ABC", null);

        // then
        // exception is thrown
    }
}
