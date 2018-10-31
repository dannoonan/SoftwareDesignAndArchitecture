package ie.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.demo.domain.User;
import ie.demo.mapper.UserMapper;
import ie.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public int register(User u) {
		int result;
		String username = u.getUsername();
		if(userMapper.userExists(username) == 0) {
			result = userMapper.register(u);
		} else {
			result = 0;
		}
		return result;
	}

	@Override
	public int login(String username, String password) {
		User user = userMapper.findUserByUserName(username);
		if(user == null)
			return 0;
		if(user.getPassword().equals(password))
			return 1;
		else
			return 0;
	}

}
