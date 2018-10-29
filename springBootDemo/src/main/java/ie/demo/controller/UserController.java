package ie.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import ie.demo.domain.User;
import ie.demo.service.UserService;
import ie.response.MsgResponse;


@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public MsgResponse findAll() {
		List<User> list = userService.findAll();
		return MsgResponse.success().add("users", list);
	}

	@RequestMapping(value= "/insert", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public MsgResponse insertUser(@RequestBody User u) {
		userService.insertUser(u);
		return MsgResponse.success();
	}
}
