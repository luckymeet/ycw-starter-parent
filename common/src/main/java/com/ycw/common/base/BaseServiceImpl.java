package com.ycw.common.base;

import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

public class BaseServiceImpl<M extends BaseCrudMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {

	/**
	 * 全量更新，不忽略null字段，等价于update
	 *
	 * @author yuminjun
	 * @date 2020/06/03 10:15:14
	 * @param entity
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
    public boolean updateAllById(T entity) {
        return retBool(baseMapper.alwaysUpdateSomeColumnById(entity));
    }

}
