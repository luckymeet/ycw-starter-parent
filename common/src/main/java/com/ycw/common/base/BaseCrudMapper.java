package com.ycw.common.base;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;

public interface BaseCrudMapper<T> extends BaseMapper<T> {

	/**
	 * 全量更新，不忽略null字段，等价于update
	 * 解决mybatis-plus会自动忽略null字段不更新
	 * {@link com.baomidou.mybatisplus.extension.injector.methods.additional.AlwaysUpdateSomeColumnById}
	 *
	 * @author yuminjun
	 * @date 2020/06/03 10:14:06
	 * @param entity
	 * @return
	 */
	int alwaysUpdateSomeColumnById(@Param(Constants.ENTITY) T entity);

}
