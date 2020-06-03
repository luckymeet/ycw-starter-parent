package com.ycw.common.base;

import com.baomidou.mybatisplus.extension.service.IService;

public interface BaseService<T> extends IService<T> {

	/**
	 * 全量更新，不忽略null字段，等价于update
	 *
	 * @author yuminjun
	 * @date 2020/06/03 10:15:14
	 * @param entity
	 * @return
	 */
	boolean updateAllById(T entity);

}
