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
package io.paymeter.assessment.services;

import io.paymeter.assessment.Ticket;
import io.paymeter.assessment.ports.secundary.CalculateFeeServices;
import io.paymeter.assessment.services.princingplans.PricingPlan;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomCalculatorAdapter implements CalculateFeeServices {

    // TODO Move to database
    Map<String, String> customerPricingPlanMapping = new HashMap<String, String>() {
        {
            put("P000123", "normalPricingPlan");
            put("P000456", "firstHourFreePricingPlan");

        }
    };

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Ticket calculate(Ticket ticket) {
        log.info("CustomCalculatorAdapter calculate ticket {}", ticket);

        Ticket calculatedTicket = null;

        String princingPlan = customerPricingPlanMapping.get(ticket.getParkingId());

        log.info("Parking {} using {} pricing plan", ticket.getParkingId(), princingPlan);
        if (princingPlan != null) {
            PricingPlan customCalculator = (PricingPlan) applicationContext.getBean(princingPlan);
            calculatedTicket = customCalculator.calculate(ticket);
        }

        return calculatedTicket;
    }

}
