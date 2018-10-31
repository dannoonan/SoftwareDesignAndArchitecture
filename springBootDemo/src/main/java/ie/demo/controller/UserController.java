package ie.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import ie.demo.domain.User;
import ie.demo.service.UserService;
import ie.response.MsgResponse;


@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value= "/user", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public MsgResponse insertUser(@RequestParam(value = "username")String username,
								  @RequestParam(value = "password")String password,
								  @RequestParam(value = "studentCardId") String studentCardId) {
		User u = new User(username, password, Long.parseLong(studentCardId));
		int result = userService.register(u);
		if(result > 0) {
			return MsgResponse.success();
		} else {
			return MsgResponse.fail().add("error", "fail to register");
		}
	}

	@RequestMapping(value= "/user", method=RequestMethod.PUT, produces="application/json;charset=UTF-8")
	public MsgResponse login(@RequestParam(value = "username")String username,
							 @RequestParam(value = "password")String password) {
		int result = userService.login(username, password);
		if(result > 0) {
			return MsgResponse.success();
		} else {
			return MsgResponse.fail().add("error", "fail to login");
		}
	}
}
