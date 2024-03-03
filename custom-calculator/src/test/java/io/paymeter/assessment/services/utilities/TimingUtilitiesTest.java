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
package io.paymeter.assessment.services.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author oem
 */
@Slf4j
public class TimingUtilitiesTest {

    @Test
    public void testingDateFormatter() {
        TemporalAccessor parseDate = DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse("2024-02-27T09:00:00");
        log.info("parseDate {}", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(parseDate));
        Assertions.assertEquals("2024-02-27T09:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(parseDate));
    }

    @Test
    public void amountFromExactHours() {
        long amount = TimingUtilities.retrieveAmountOfHours(LocalDateTime.now().minus(1, ChronoUnit.HOURS));
        log.info("amountFromExactHours amount {}", amount);
        assertTrue(amount == 1);
    }

    @Test
    public void amountFromHalfHourInMinutes() {
        long amount = TimingUtilities.retrieveAmountOfHours(LocalDateTime.now().minus(30, ChronoUnit.MINUTES));
        log.info(" amountFromHalfHourInMinutes amount {}", amount);
        assertTrue(amount == 1);
    }

    @Test
    public void amountFrom60Minutes() {
        long amount = TimingUtilities.retrieveAmountOfHours(LocalDateTime.now().minus(60, ChronoUnit.MINUTES));
        log.info("amountFrom60Minutes amount {}", amount);
        assertTrue(amount == 1);
    }

    @Test
    public void amountFromNow() {
        long amount = TimingUtilities.retrieveAmountOfHours(LocalDateTime.now());
        log.info("amountFromNow amount {}", amount);
        assertTrue(amount == 0);
    }
    
    @Test
    public void amountFrom15Seconds() {
        long amount = TimingUtilities.retrieveAmountOfHours(LocalDateTime.now().minus(15, ChronoUnit.SECONDS));
        log.info("amountFrom15Seconds amount {}", amount);
        assertTrue(amount == 0);
    }

}
