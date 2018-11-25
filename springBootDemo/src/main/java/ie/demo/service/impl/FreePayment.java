package ie.demo.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ie.demo.service.CalculatePayment;

@Service
@Component("FreePayment")
public class FreePayment extends CalculatePayment{

	@Override
	public void calculatePaidAmount(float minutes) {
		if(minutes <= 1) {
			setFinalPay(0);
		} else {
			if(this.nextPaymentMethod != null) {
				this.nextPaymentMethod.calculatePaidAmount(minutes);
			}
		}
	}
}	
