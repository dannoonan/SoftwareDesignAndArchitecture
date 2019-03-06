package ie.demo.service.impl;

import ie.demo.domain.*;
import ie.demo.service.AbstractUserFactory;
import org.springframework.stereotype.Service;

@Service
public class AdminUserFactory implements AbstractUserFactory {

	@Override
	public AdminUser createUser(String username, String password, Integer studentCardId, int userTypeId, String email) {

		if(userTypeId == 3) {
			return new StandardAdmin(username, password, studentCardId, userTypeId, email);
		} else if (userTypeId == 4) {
			return new MaintenanceAdmin(username, password, studentCardId, userTypeId, email);
		}

		return null;
	}

}
