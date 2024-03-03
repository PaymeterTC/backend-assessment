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
import io.paymeter.assessment.ports.primary.CalculateFeeUseCase;
import io.paymeter.assessment.ports.secundary.CalculateFeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author oem
 */
@Service
public class BackendAssessmentService implements CalculateFeeUseCase {

    @Autowired
    private CalculateFeeServices calculateFeeServices;

    @Override
    public Ticket calculate(Ticket ticket) {
        
        //TODO Monitoring ticket for data mining purposes
        return calculateFeeServices.calculate(ticket);
    }

}
