package com.itec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itec.pojo.User;
import com.itec.pojo.vo.LoginUser;
import com.itec.pojo.vo.R;
import com.itec.pojo.vo.UserQuaryCondition;
import com.itec.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	// 登录逻辑
	@PostMapping("/login")
	public R login(@RequestBody LoginUser loginUser) {
		System.out.println("login success" + loginUser);
		R r = userService.login(loginUser);
		return r;
	}

	// 获取当前用户
	@GetMapping("/info")
	public R userInfo(@RequestParam("token") String token) {
		System.out.println("get userInfo" + token);
		R r = userService.selectUserInfoByMail(token);
		return r;
	}

	// 查询用户信息
	@PostMapping("/userInfo/{currentPage}/{limit}")
	public R selectUserInfo(@PathVariable("currentPage") Integer currentPage, @PathVariable("limit") Integer limit,
			@RequestBody UserQuaryCondition userQuaryCondition) {
		return userService.selectUserInfo(currentPage, limit, userQuaryCondition);
	}

	// 逻辑删除
	@Transactional
	@DeleteMapping("/deleteUserByID/{userId}")
	public R deleteUserById(@PathVariable("userId") String userId) {
		return userService.deleteUserById(userId);
	}

	// 新增
	@Transactional
	@PostMapping("/insertUser")
	public R insertUser(@RequestBody User user) {
		return userService.insertUser(user);
	}

	// 通过id查询
	@GetMapping("/selectUserById/{userId}")
	public R selectUserById(@PathVariable("userId") String userId) {
		return userService.selectUserById(userId);
	}

	// 修改
	@Transactional
	@PutMapping("/updateUserByUserId")
	public R updateUserByUserId(@RequestBody User user) {
		return userService.updateUserByUserId(user);
	}

	// 修改密码
	@Transactional
	@PutMapping("/updatePwdByUserId")
	public R updatePwdByUserId(@RequestBody User user) {
		return userService.updatePwdByUserId(user);
	}

	// 锁定账户
	@Transactional
	@PutMapping("/updateUserLockFlg")
	public R updateUserLockFlg(@RequestParam("userMailaddress") String userMailaddress) {
		return userService.updateUserLockFlg(userMailaddress);
	}
}
