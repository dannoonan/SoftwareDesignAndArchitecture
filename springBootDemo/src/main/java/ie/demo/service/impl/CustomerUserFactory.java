package ie.demo.service.impl;

import ie.demo.domain.CustomerUser;
import ie.demo.domain.PremiumCustomer;
import ie.demo.domain.StandardCustomer;
import ie.demo.service.AbstractUserFactory;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserFactory implements AbstractUserFactory {

	@Override
	public CustomerUser createUser(String username, String password, Integer studentCardId, int userTypeId, String email) {
		if(userTypeId == 1) {
			return new StandardCustomer(username, password, studentCardId, userTypeId, email);
		} else if (userTypeId == 2) {
			return new PremiumCustomer(username, password, studentCardId, userTypeId, email);
		}

		return null;
	}

}
