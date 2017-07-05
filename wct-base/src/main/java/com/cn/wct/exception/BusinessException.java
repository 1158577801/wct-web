package com.cn.wct.exception;
/**
 * 业务异常类
 * @author	zhouzb
 * @date	2015年12月8日
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 4370693606942229390L;

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
