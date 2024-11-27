package com.itec.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.itec.mapper.UserMapper;
import com.itec.pojo.User;
import com.itec.pojo.vo.LoginUser;
import com.itec.pojo.vo.R;
import com.itec.pojo.vo.UserQuaryCondition;
import com.itec.service.UserService;
import com.itec.util.IDUtil;
import com.itec.util.JwtUtil;
import com.itec.util.Md5Util;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	private Map<String, LoginAttempt> loginAttempts = new HashMap<>();

	@Override
	public R login(LoginUser loginUser) {
		// 用户名密码为空时直接返回失败
		if (StringUtils.isEmpty(loginUser.getUserMailaddress()) || StringUtils.isEmpty(loginUser.getUserPwd())) {
			return R.error().message("ユーザー名またはパスワードが間違っています");
		}
		// 密码MD5加密
		Md5Util.string2MD5(loginUser.getUserPwd());

		// 检查登录尝试次数
		String username = loginUser.getUserMailaddress();
		LoginAttempt attempt = loginAttempts.get(username);
		if (attempt != null && attempt.getFailedAttempts() >= 3
				&& System.currentTimeMillis() - attempt.getLastAttemptTime() < TimeUnit.MINUTES.toMillis(1)) {
			userMapper.updateUserLockFlg(username);
			return R.error().message("アカウントがロックされています。管理者に連絡してください。");
		}

		// 使用用户名密码登录
		User user = userMapper.login(loginUser.getUserMailaddress(), loginUser.getUserPwd());
		if (user == null) {
			// 更新登录尝试次数
			if (attempt == null) {
				attempt = new LoginAttempt();
				loginAttempts.put(username, attempt);
			}
			attempt.incrementFailedAttempts();
			attempt.setLastAttemptTime(System.currentTimeMillis());
			return R.error().message("ユーザー名またはパスワードが間違っています");
		}

		// 重置登录尝试次数
		if (attempt != null) {
			attempt.resetFailedAttempts();
		}

		// 判断用户的密码过期
		Date userPwExpiration = user.getUserPwExpiration();
		Date now = new Date();
		if (userPwExpiration != null && now.after(userPwExpiration)) {
			return R.error().message("パスワードが期限切れです。新しいパスワードを設定してください");
		}

		String userLockFlg = user.getUserLockFlg();
		if (!"0".equals(userLockFlg)) {
			return R.error().message("アカウントがロックされています。管理者に連絡してください");
		}

		String token = JwtUtil.getJWTToken(loginUser.getUserMailaddress(), loginUser.getUserPwd());
		return R.success().data("token", token).data("username", loginUser.getUserNm());
	}

	private static class LoginAttempt {
		private int failedAttempts = 0;
		private long lastAttemptTime = 0;

		public void incrementFailedAttempts() {
			failedAttempts++;
		}

		public void resetFailedAttempts() {
			failedAttempts = 0;
		}

		public int getFailedAttempts() {
			return failedAttempts;
		}

		public void setLastAttemptTime(long lastAttemptTime) {
			this.lastAttemptTime = lastAttemptTime;
		}

		public long getLastAttemptTime() {
			return lastAttemptTime;
		}
	}

	@Override
	public R selectUserInfo(Integer currentPage, Integer limit, UserQuaryCondition userQuaryCondition) {
		Integer startIndex = (currentPage - 1) * limit;
		List<User> selectUserInfo = userMapper.selectUserInfo(startIndex, limit, userQuaryCondition);
		Integer selectUserNum = userMapper.selectUserNum(userQuaryCondition);
		if (selectUserNum > 0) {
			return R.success().data("items", selectUserInfo).data("itemsNum", selectUserNum);
		} else {
			return R.success().message("検索結果は0件です").data("itemsNum", selectUserNum);
		}
	}

	@Override
	public R selectUserInfoByMail(String token) {
		boolean checkToken = JwtUtil.checkToken(token);
		if (!checkToken) {
			return R.error().message("Token失効です。再度ログインしてください");
		}
		String userMailaddress = JwtUtil.getUsernameByToken(token);
		User selectUserInfoByMail = userMapper.selectUserInfoByMail(userMailaddress);

		return R.success().data("username", selectUserInfoByMail.getUserNm()).data("userId",
				selectUserInfoByMail.getUserId());
	}

	@Override
	public R deleteUserById(String userId) {
		int i = userMapper.deleteUserById(userId);
		if (i > 0) {
			return R.success().message("削除が完了しました");
		} else {
			return R.success().message("削除に失敗しました");
		}
	}

	@Override
	public R insertUser(User user) {
		String userId = IDUtil.getID();
		user.setUserId(userId);
		user.setUserDelFlg("0");
		user.setUserLockFlg("0");
		user.setManagerCrdDt(new Date());
		user.setManagerUpdDt(new Date());
		user.setUserExPwDay(new Date());
		int i = userMapper.insertUser(user);
		if (i > 0) {
			return R.success().message("登録成功");
		} else {
			return R.error().message("登録エラー");
		}
	}

	@Override
	public R updateUserByUserId(User user) {
		user.setManagerUpdDt(new Date());
		int i = userMapper.updateUserByUserId(user);
		if (i > 0) {
			return R.success().message("更新成功");
		} else {
			return R.error().message("更新エラー");
		}
	}

	@Override
	public R selectUserById(String userId) {
		User selectUserById = userMapper.selectUserById(userId);
		return R.success().data("items", selectUserById);
	}

	@Override
	public R updatePwdByUserId(User user) {
		user.setManagerUpdDt(new Date());
		user.setUserExPwDay(new Date());
		int i = userMapper.updatePwdByUserId(user);
		if (i > 0) {
			return R.success().message("変更成功");
		} else {
			return R.error().message("変更エラー");
		}
	}

	@Override
	public R updateUserLockFlg(String userMailaddress) {
		int i = userMapper.updateUserLockFlg(userMailaddress);
		if (i > 0) {
			return R.success().message("アカウントがロックされています。管理者に連絡してください。");
		} else {
			return R.error().message("ロックエラー");
		}
	}

}
