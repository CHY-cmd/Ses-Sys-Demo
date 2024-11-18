package com.itec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itec.pojo.vo.LoginUser;
import com.itec.pojo.vo.R;
import com.itec.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public R login(@RequestBody LoginUser loginUser) {
		System.out.println("login success" + loginUser);
		R r = userService.login(loginUser);
		return r;
	}

	@GetMapping("/info")
	public R userInfo(@RequestParam("token") String token) {
		System.out.println("get userInfo" + token);
		R r = userService.selectUserInfoByMail(token);
		return r;
	}

}
