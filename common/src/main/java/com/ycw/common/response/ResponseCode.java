package com.ycw.common.response;

/**
 * 响应码枚举类
 * @author yuminjun yuminjun@lexiangbao.com
 * @date 2020/04/14 14:49:26
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
public enum ResponseCode {

	SUCCESS (200, "SUCCESS", "成功"),

	ERR_400 (400, "ERR_400", "错误请求"),
	ERR_401 (401, "ERR_401", "未授权"),
	ERR_403 (403, "ERR_403", "禁止"),
	ERR_404 (404, "ERR_404", "未找到"),
	ERR_405 (405, "ERR_405", "方法禁用"),
	ERR_406 (406, "ERR_406", "不接受"),
	ERR_407 (407, "ERR_407", "需要代理授权"),
	ERR_408 (408, "ERR_408", "请求超时"),
	ERR_409 (409, "ERR_409", "冲突"),
	ERR_410 (410, "ERR_410", "已删除"),
	ERR_411 (411, "ERR_411", "需要有效长度"),
	ERR_412 (412, "ERR_412", "未满足前提条件"),
	ERR_415 (415, "ERR_415", "不支持的媒体类型"),
	ERR_416 (416, "ERR_416", "请求范围不符合要求"),
	ERR_417 (417, "ERR_417", "请求参数不合法"),
	ERR_418 (418, "ERR_418", "缺少请求参数"),
	ERR_419 (419, "ERR_419", "唯一性判定"),
	ERR_420 (420, "ERR_420", "资源被占用"),
	ERR_421 (421, "ERR_421", "数据异常"),
	ERR_422 (422, "ERR_422", "远程服务调用异常"),
	ERR_500 (500, "ERR_500", "服务器内部错误"),

	ERR_SAVE (501, "ERR_501", "保存失败"),
	ERR_UPDATE (502, "ERR_502", "修改失败"),
	ERR_DELETE (503, "ERR_503", "删除失败"),

	ERR_999 (999, "ERR_999", "其他错误");

	/**
	 *编码
	 */
	private final Integer code;

	/**
	 *名称
	 */
	private final String name;

	/**
	 *描述
	 */
	private final String desc;

	ResponseCode(Integer code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

	public Integer getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

}
