package io.paymeter.assessment.pricing;

public interface PricingRepository {
    Pricing get(String parkingId);
}
