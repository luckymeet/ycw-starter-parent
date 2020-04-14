package com.ycw.common.response;

import java.io.Serializable;

import com.ycw.common.utils.SpringUtils;

import brave.Tracer;
import lombok.ToString;

/**
 * 统一响应对象
 * @author yuminjun
 * @date 2020/04/14 15:12:12
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
@ToString
public class ResponseVO<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int SUCCESS = 200;

	private Integer code;

	private String message;

	private T data;

	private Long timestamp = System.currentTimeMillis();

	private String traceId;

	private ResponseVO() {
	}

	public static <D> ResponseVO<D> success(D data) {
		return success(data, (String) null);
	}

	public static <D> ResponseVO<D> success(D data, String message) {
		ResponseVO<D> responseVO = new ResponseVO<>();
		responseVO.setCode(SUCCESS);
		responseVO.setMessage(message);
		responseVO.setData(data);
		responseVO.setTraceId(getTransferTrace());
		return responseVO;
	}

	public static <D> ResponseVO<D> fail(int code, String message) {
		ResponseVO<D> responseVO = new ResponseVO<>();
		responseVO.setCode(code);
		responseVO.setMessage(message);
		responseVO.setTraceId(getTransferTrace());
		return responseVO;
	}

	public static String getTransferTrace() {
		Tracer tracer = (Tracer) SpringUtils.getBean("tracer");
		if (tracer == null) {
			return null;
		} else {
			return tracer.currentSpan() != null && tracer.currentSpan().context() != null
					? tracer.currentSpan().context().traceIdString()
					: null;
		}
	}

	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return this.data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public long getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isSuccess() {
		return this.code == SUCCESS;
	}

	public String getTraceId() {
		return this.traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}
}
