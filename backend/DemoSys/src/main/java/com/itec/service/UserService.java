package com.itec.service;

import org.springframework.stereotype.Service;

import com.itec.pojo.User;
import com.itec.pojo.vo.LoginUser;
import com.itec.pojo.vo.R;
import com.itec.pojo.vo.UserQuaryCondition;

@Service
public interface UserService {
	R login(LoginUser loginUser);

	R selectUserInfo(Integer currentPage, Integer limit, UserQuaryCondition userQuaryCondition);

	R selectUserInfoByMail(String token);

	R deleteUserById(String userId);

	R insertUser(User user);

	R selectUserById(String userId);

	R updateUserByUserId(User user);

	R updatePwdByUserId(User user);

	R updateUserLockFlg(String userMailaddress);
}
