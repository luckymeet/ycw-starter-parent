package com.ycw.common.exception;

/**
 * 自定义系统异常
 * @author yuminjun
 * @date 2020/04/14 14:47:09
 * @version 1.00
 *
 * @record
 * <pre>
 * version  author      date          desc
 * -------------------------------------------------
 * 1.00     yuminjun    2020/04/14    新建
 * -------------------------------------------------
 * </pre>
 */
public class SysException extends Exception {

	private static final long serialVersionUID = 1L;

	private final int code;

	public SysException(int code, String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.code = code;
	}

	public SysException(int code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public SysException(int code, String message) {
		super(message);
		this.code = code;
	}

	public SysException(int code, Throwable cause) {
		super(cause);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
