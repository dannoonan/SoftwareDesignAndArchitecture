package ie.demo.service;

import java.util.List;

import ie.demo.domain.User;

public interface UserService {
	
	public int register(User u);
	int login(String username, String password);
	
}
