/*
 * Copyright 2024 Autumn Framework.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.paymeter.assessment.services.princingplans;

import io.paymeter.assessment.Ticket;
import io.paymeter.assessment.pricing.Money;
import io.paymeter.assessment.services.utilities.TimingUtilities;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 *
 * @author oem
 */
@Slf4j
@Component("normalPricingPlan")
public class NormalPricingPlan extends AbstractPricingPlan {

    private final int HOURLY_PRICE = 2;
    private final int MAX_PRICE_PER_DAY_PRICE = 15;

    @Override
    public void applySpecificRules() {

        // First extract days
        long amountDays = to.until(from, ChronoUnit.DAYS);

        long price;
        if (amountDays == 0) {
            // There is no max price per day business case
            price = TimingUtilities.retrieveAmountOfHours(from, to) * HOURLY_PRICE;
        } else {
            // There is max price per day business case

            // Extract remaining hours and round hour in case of no exact amount of hours
            LocalDateTime remainingTo = to.minus(amountDays, ChronoUnit.DAYS);
            long amountHours = TimingUtilities.retrieveAmountOfHours(from, remainingTo);

            // Calculate price taking into account max price per day
            price = amountDays * MAX_PRICE_PER_DAY_PRICE;
            price += amountHours * HOURLY_PRICE;
        }

        this.ticket.setPrice(Money.builder()
                .amount(price)
                .currency(Money.DEFAULT_CURRENCY)
                .build());
    }

}
