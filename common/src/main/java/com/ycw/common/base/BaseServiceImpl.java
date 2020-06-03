package com.ycw.common.base;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

public class BaseServiceImpl<M extends BaseCrudMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {

	/**
	 * 全量更新，不忽略null字段，等价于update
	 *
	 * @author yuminjun
	 * @date 2020/06/03 10:15:14
	 * @param t
	 * @return
	 */
	@Override
    public boolean updateAllById(T t) {
        return retBool(baseMapper.alwaysUpdateSomeColumnById(t));
    }

}
