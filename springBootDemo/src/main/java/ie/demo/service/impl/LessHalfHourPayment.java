package ie.demo.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ie.demo.service.CalculatePayment;

@Service
@Component("LessHalfHourPayment")
public class LessHalfHourPayment extends CalculatePayment{

	@Override
	public void calculatePaidAmount(float minutes) {
		if(minutes <= 30) {
			setFinalPay(2);
		} else {
			if(this.nextPaymentMethod != null) {
				this.nextPaymentMethod.calculatePaidAmount(minutes);
			}
		}
		
	}

}
