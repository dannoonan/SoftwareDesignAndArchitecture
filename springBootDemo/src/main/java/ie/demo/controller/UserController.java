package ie.demo.controller;

import ie.demo.service.AbstractUserFactory;
import ie.demo.service.impl.FactoryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ie.demo.domain.User;
import ie.demo.service.UserService;
import ie.util.MsgResponse;
import ie.util.StateCode;

import java.util.List;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private FactoryProvider factoryProvider;
	
	@RequestMapping(value= "/user", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public MsgResponse insertUser(@RequestParam(value = "username")String username,
								  @RequestParam(value = "password")String password,
								  @RequestParam(value = "studentCardId", required = false) Integer studentCardId,
								  @RequestParam(value = "userTypeId") int userTypeId,
								  @RequestParam(value = "email") String email) {
		AbstractUserFactory factory = factoryProvider.getFactory(userTypeId);
		User u = (User) factory.createUser(username, password, studentCardId, userTypeId, email);
		int result = userService.register(u);
		if(result == StateCode.SUCCESS.getCode()) {
			return MsgResponse.success();
		}
		else if(result == StateCode.ALREADY_EXISTS.getCode()) {
			return MsgResponse.fail(result).add("error", "User already exists.");
		}
		else {
			return MsgResponse.fail(result).add("error", "Register failed.");
		}
	}

	@RequestMapping(value= "/user", method=RequestMethod.PUT, produces="application/json;charset=UTF-8")
	public MsgResponse login(@RequestParam(value = "username")String username,
							 @RequestParam(value = "password")String password) {
		List<String> result = userService.login(username, password);
		if(result.get(0).equals("" + StateCode.PROCESS_SUCCESS.getCode())) {
			return MsgResponse.success()
					.add("userId: ", result.get(1))
					.add("userTypeId: ", result.get(2))
					.add("username: ", result.get(3))
					.add("email: ", result.get(4)).add("isBanned: ", result.get(5))
					.add("studentCardId: ", result.get(6));
		}
		else if(result.get(0).equals("404")) {
			return MsgResponse.fail(StateCode.USER_NOT_FOUND.getCode()).add("error", "User not found.");
		}
		else {
			return MsgResponse.fail(StateCode.ERROR.getCode()).add("error", "Login failed.");
		}
	}
}
