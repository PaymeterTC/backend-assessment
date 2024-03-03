package io.paymeter.assessment.pricing;

import java.util.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Money {

    public static final Currency DEFAULT_CURRENCY = Currency.getInstance("EUR");

    private long amount;
    private Currency currency;

}
