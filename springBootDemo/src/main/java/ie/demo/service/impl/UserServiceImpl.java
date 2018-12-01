package ie.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import ie.demo.domain.StudentCard;
import ie.demo.mapper.StudentCardMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import ie.demo.domain.User;
import ie.demo.mapper.UserMapper;
import ie.demo.service.UserService;
import ie.util.StateCode;
import ie.demo.service.PasswordService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private StudentCardMapper studentCardMapper;

	@Autowired
	private PasswordService passwordService;

	@Override
	public int register(User u) {
		int result;
		String username = u.getUsername();
		String newPassword = passwordService.encryptPassword(u.getPassword());
		u.setPassword(newPassword);
		if(u.getStudentCardId() != null) {
			if(studentCardMapper.cardExists(u.getStudentCardId()) == 0) {
				StudentCard card = new StudentCard();
				card.setStudentCardId(u.getStudentCardId());
				studentCardMapper.createCard(card);
			}
		}
		if(userMapper.userExists(username) == 0) {
			try {
				result = userMapper.register(u);
			} catch (DataIntegrityViolationException e) {
				result = StateCode.FAIL.getCode();
			}
		} else {
			result = StateCode.ALREADY_EXISTS.getCode();
		}
		return result;
	}

	@Override
	public List<String> login(String username, String password) {
		User user = findUserByUserName(username);
		List<String> result = new ArrayList<>();
		if(user == null)
			result.add(0, "" + StateCode.ERROR.getCode());
		else {
			if(password.equals(passwordService.decryptPassword(user.getPassword()))) {
				result.add(0,"" + StateCode.PROCESS_SUCCESS.getCode());
				result.add(Integer.toString(user.getUserId()));
				result.add(Integer.toString(user.getUserTypeId()));
				result.add(user.getUsername());
				result.add(user.getEmail());
				result.add(Integer.toString(user.getIsBanned() ? 1 : 0 ));
				if(user.getStudentCardId() != null) {
					result.add(Integer.toString(user.getStudentCardId()));
				} else {
					result.add("null");
				}
			}
			else {
				result.add(0,"" + StateCode.USER_NOT_FOUND.getCode());
			}
		}

		return result;
	}

	@Override
	public User findUserByUserName(String userName) {
		User user = userMapper.findUserByUserName(userName);
		return user;
	}

}
