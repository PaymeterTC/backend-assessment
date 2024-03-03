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
import java.time.temporal.ChronoUnit;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author oem
 */
@Slf4j
public class TimingUtilities {

    public static long retrieveAmountOfHours(LocalDateTime from) {
        return retrieveAmountOfHours(from, LocalDateTime.now());
    }

    public static long retrieveAmountOfHours(LocalDateTime from, LocalDateTime to) {

        long amountOfHours, amountOfCompleteHours = from.until(to, ChronoUnit.HOURS);
        amountOfHours = amountOfCompleteHours;
        long amountOfMinutes = from.until(to, ChronoUnit.MINUTES);

        if (amountOfMinutes > amountOfCompleteHours * 60) {
            log.trace("There are {} remaing minutes additionally to the complete hours", amountOfMinutes - (amountOfCompleteHours * 60));
            amountOfHours++;
        }
        return amountOfHours;
    }

}
