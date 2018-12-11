package com.tortoise.framework.exception;

/**
 * 消息异常类
 * 
 * @author chensheng
 */
public class MessageException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MessageException(String message) {
		super(message);
	}

	public MessageException(String message, Exception ex) {
		super(message, ex);
	}
}
