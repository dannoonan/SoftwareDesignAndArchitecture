package ie.demo.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ie.demo.service.CalculatePayment;
@Service
@Component("GreaterOneHourPayment")

public class GreaterOneHourPayment extends CalculatePayment{
	
	@Override
	public void calculatePaidAmount(float minutes) {
		setFinalPay((float) (0.1 * minutes));
	}

}
