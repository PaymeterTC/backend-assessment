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
import org.springframework.util.StringUtils;

/**
 *
 * @author oem
 */
@Slf4j
public abstract class AbstractPricingPlan implements PricingPlan {

    @Autowired
    protected DateTimeFormatter formatter;

    protected LocalDateTime from;

    protected LocalDateTime to;

    protected Ticket ticket;

    @Override
    public Ticket calculate(Ticket ticket) {
        this.ticket = ticket;

        this.from = LocalDateTime.parse(ticket.getFrom(), formatter);

        this.to = null;
        if (StringUtils.hasText(ticket.getTo())) {
            this.to = LocalDateTime.parse(ticket.getTo(), formatter);
        } else {
            this.to = LocalDateTime.now();
            ticket.setTo(formatter.format(to));
        }

        this.ticket.setDuration(from.until(to, ChronoUnit.MINUTES));

        applySpecificRules();
        
        return this.ticket;
    }

    abstract public void applySpecificRules();

}
