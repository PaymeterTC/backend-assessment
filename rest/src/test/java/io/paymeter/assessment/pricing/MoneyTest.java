package io.paymeter.assessment.pricing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    @Test
    void testCurrencyIsAlwaysEuro() {
        var money = new Money(123);

        assertEquals("EUR", money.getCurrencyCode());
    }
}
