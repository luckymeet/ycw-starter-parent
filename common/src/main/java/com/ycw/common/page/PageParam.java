package com.ycw.common.page;

import lombok.ToString;

/**
 * 分页参数
 * @author yuminjun
 * @date 2020/04/14 14:49:06
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
public class PageParam {

	/**
	 * 当前页码
	 */
	private int pageNum;

	/**
	 * 每页条数
	 */
	private int pageSize;

	@SuppressWarnings("unused")
	private PageParam() {}

	public PageParam(int pageNum, int pageSize) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
