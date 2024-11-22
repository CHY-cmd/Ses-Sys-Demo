package com.itec.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.druid.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

	// 令牌的有效期 1小时;定义密钥
	private static final long EXPIRE = 1000 * 60 * 60;
	private static final String APP_KEY = "grts";

	// 通过用户名和密码生成令牌
	public static String getJWTToken(String username, String password) {
		Map<String, Object> map = new HashMap<>();
		map.put("username", username);
		map.put("password", password);

		String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, APP_KEY).setClaims(map)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRE)).compact();

		return token;
	}

	// 验证token是否生效
	public static boolean checkToken(String token) {
		// token为空时也异常 StringUtils下的方法，为空时返回true
		if (StringUtils.isEmpty(token)) {
			return false;
		}
		// 失效时会报错
		try {
			Jwts.parser().setSigningKey(APP_KEY).parseClaimsJws(token);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	// 通过token来获取用户名
	public static String getUsernameByToken(String token) {
		Claims body = Jwts.parser().setSigningKey(APP_KEY).parseClaimsJws(token).getBody();
		String userMailaddress = (String) body.get("username");
		return userMailaddress;
	}

}