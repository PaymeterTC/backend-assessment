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
package io.paymeter.assessment;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.paymeter.assessment.pricing.CustomMoneySerializer;
import io.paymeter.assessment.pricing.Money;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author oem
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    private UUID id;
    private String parkingId;
    private String from;
    private String to;
    private long duration;
    @JsonSerialize(using = CustomMoneySerializer.class)
    private Money price;

}
