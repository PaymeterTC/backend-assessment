package io.paymeter.assessment;

import io.paymeter.assessment.rest.RestAdapterConfig;
import io.paymeter.assessment.services.CustomCalculatorAdapterConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import org.springframework.context.annotation.Import;

@Configuration
@Import(
        {
            CustomCalculatorAdapterConfig.class,
            RestAdapterConfig.class
        }
)
public class Config {

    @Bean
    public Clock getClock() {
        return Clock.systemUTC();
    }
}
