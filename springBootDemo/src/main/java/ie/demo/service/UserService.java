package ie.demo.service;

import java.util.List;

import ie.demo.domain.User;

public interface UserService {
	
	public List<User> findAll();
	
	public void insertUser(User u);
}
