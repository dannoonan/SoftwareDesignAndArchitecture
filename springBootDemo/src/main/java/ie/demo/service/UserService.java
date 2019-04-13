package ie.demo.service;


import ie.demo.domain.User;

import java.util.List;

public interface UserService {
	
	int register(User u);
	List<String> login(String username, String password);
	Object findUserByUserName(String userName);
	
}
