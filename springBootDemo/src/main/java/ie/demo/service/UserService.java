package ie.demo.service;

import ie.demo.domain.User;

public interface UserService {
	
	int register(User u);
	int login(String username, String password);
	User findUserByUserName(String userName);
	
}
