package com.itec.pojo.vo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class R {
	private boolean success;
	// 返回状态码:20000成功 20001失败
	private int code;
	// 返回的消息
	private String message;
	// 返回的数据
	private Map<String, Object> data = new HashMap<String, Object>();

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "R [success=" + success + ", code=" + code + ", message=" + message + ", data=" + data + "]";
	}

	private R() {

	}

	public static R success() {
		R r = new R();
		// 成功时给属性赋值
		r.setSuccess(true);
		r.setCode(ResultCode.SUCCESS);
		r.setMessage("成功");
		return r;
	}

	public static R error() {
		R r = new R();
		// 失败时给属性赋值
		r.setSuccess(false);
		r.setCode(ResultCode.ERROR);
		r.setMessage("失败");
		return r;
	}

	public R code(Integer code) {
		this.setCode(code);
		return this;
	}

	public R message(String message) {
		this.setMessage(message);
		return this;
	}

	public R data(Map<String, Object> map) {
		this.setData(map);
		return this;
	}

	public R data(String key, Object object) {
		this.data.put(key, object);
		return this;
	}

}
