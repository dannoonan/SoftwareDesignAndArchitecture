package ie.demo.service;

import ie.demo.domain.User;

public interface UserFactory {
	
	public User createUser(String username, String password, long studentCardId, String email);

}
