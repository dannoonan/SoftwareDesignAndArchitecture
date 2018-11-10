package ie.demo.service;

import ie.demo.domain.User;

public interface UserFactory {
	
	User createUser(String username, String password, int studentCardId, String email);

}
