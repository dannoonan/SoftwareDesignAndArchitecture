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
	public List<User> findAll(){
		List<User> list = userMapper.findAll();
		return list;
	}
	
	@Override
	public void insertUser(User u) {
		userMapper.insertUser(u);
	}

}
