package com.tortoise.framework.dto;

import java.io.Serializable;

public class ApiResult implements Serializable {
	private static final long serialVersionUID = 5328934827636086473L;
	
	public static final Integer SUCCESS = 1;
	public static final Integer FAILURE = 0;

	/**
	 * 操作成功标识
	 */
	private Integer state = SUCCESS;

	/**
	 * 消息
	 */
	private String message;

	private Object datas;

	public ApiResult() {
		super();
	}

	public ApiResult(Integer state, String message, Object datas) {
		super();
		this.state = state;
		this.message = message;
		this.datas = datas;
	}

	public Object getDatas() {
		return datas;
	}

	public void setDatas(Object datas) {
		this.datas = datas;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}