package com.itec.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.itec.mapper.UserMapper;
import com.itec.pojo.User;
import com.itec.pojo.vo.LoginUser;
import com.itec.pojo.vo.R;
import com.itec.pojo.vo.UserQuaryCondition;
import com.itec.service.UserService;
import com.itec.util.JwtUtil;
import com.itec.util.Md5Util;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	@Override
	public R login(LoginUser loginUser) {
		// 用户名密码为空时直接返回失败
		if (StringUtils.isEmpty(loginUser.getUserMailaddress()) || StringUtils.isEmpty(loginUser.getUserPwd())) {
			return R.error().message("ユーザー名またはパスワードが間違っています");
		}
		// 密码MD5加密
		Md5Util.string2MD5(loginUser.getUserPwd());

		// 使用用户名密码登录
		User user = userMapper.login(loginUser.getUserMailaddress(), loginUser.getUserPwd());
		if (user == null) {
			return R.error().message("ユーザー名またはパスワードが間違っています");
		}

		// 判断用户的密码过期
		Date userExPwDay = user.getUserExPwDay();
		Date now = new Date();
		if (userExPwDay != null && now.after(userExPwDay)) {
			return R.error().message("パスワードが期限切れです。新しいパスワードを設定してください");
		}

		String token = JwtUtil.getJWTToken(loginUser.getUserMailaddress(), loginUser.getUserPwd());
		return R.success().data("token", token).data("username", loginUser.getUserMailaddress());
	}

	@Override
	public R selectUserInfo(UserQuaryCondition userQuaryCondition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R selectUserInfoByMail(String token) {
		boolean checkToken = JwtUtil.checkToken(token);
		if (!checkToken) {
			return R.error().message("Token失効です。再度ログインしてください");
		}
		String userMailaddress = JwtUtil.getUsernameByToken(token);
		User selectUserInfoByMail = userMapper.selectUserInfoByMail(userMailaddress);

		return R.success().data("mail", selectUserInfoByMail.getUserMailaddress());
	}

}
