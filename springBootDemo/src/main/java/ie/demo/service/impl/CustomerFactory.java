package ie.demo.service.impl;

import org.springframework.stereotype.Service;

import ie.demo.domain.User;
import ie.demo.service.UserFactory;

@Service
public class CustomerFactory implements UserFactory {

	@Override
	public User createUser(String username, String password, Integer studentCardId, int userTypeId, String email) {
		return new User(username, password, studentCardId, userTypeId, email);
	}

}
