package com.itv;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

public class ItemTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void checkIllegalArgumentExceptionThrownWhenPassingNullSkuConstructorA() {
        // given
        exception.expect(IllegalArgumentException.class);

        // when
        Item item = new Item(null, BigDecimal.ONE);

        // then
    }

    @Test
    public void checkIllegalArgumentExceptionThrownWhenPassingNullPriceConstructorA() {
        // given
        exception.expect(IllegalArgumentException.class);

        // when
        Item item = new Item("ABC", null);

        // then
    }
}
