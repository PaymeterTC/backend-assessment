package io.paymeter.assessment.parking;

import io.paymeter.assessment.pricing.PricingRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tickets")
public class TicketController {

	private PricingRepository pricingRepository;

	@PostMapping("/calculate")
	public CalculationResponse calculate(@RequestBody CalculationRequest request) {

		// TODO

		return new CalculationResponse();
	}

	private static class CalculationRequest {
		// TODO
	}

	private static class CalculationResponse {
		// TODO
	}
}
