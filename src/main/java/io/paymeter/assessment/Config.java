package io.paymeter.assessment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class Config {

    @Bean
    public Clock getClock() {
        return Clock.systemUTC();
    }
}
