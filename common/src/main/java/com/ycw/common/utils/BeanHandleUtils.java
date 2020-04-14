package com.ycw.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanMap;

import lombok.extern.slf4j.Slf4j;

/**
 * bean处理工具类
 * @author yuminjun
 * @date 2020/04/14 15:00:32
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
@Slf4j
public class BeanHandleUtils extends BeanUtils {

	/**
	 * bean转换
	 * @author yuminjun
	 * @date 2020/04/14 14:53:11
	 * @param <T>
	 * @param source
	 * @param targetClass
	 * @return
	 */
	public static <T> T beanCopy(Object source, Class<T> targetClass) {
		if (null == source) {
			return null;
		}
		T target = null;
		try {
			target = targetClass.newInstance();
			copyProperties(source, target);
		} catch (InstantiationException | IllegalAccessException e) {
			log.error("bean转换异常：【" + source.getClass().getName() + "】转换成【" + targetClass.getName() + "】失败", e);
		}
		return target;
	}

	/**
	 * 列表bean元素统一转换
	 * @author yuminjun
	 * @date 2020/04/14 14:58:11
	 * @param <T>
	 * @param source
	 * @param targetClass
	 * @return
	 */
	public static <T> List<T> listCopy(List<?> source, Class<T> targetClass) {
		if (null == source || source.isEmpty()) {
			return Collections.emptyList();
		}
		try {
			List<T> target = new ArrayList<>(source.size());
			for (Object o : source) {
				T e = targetClass.newInstance();
				BeanUtils.copyProperties(o, e);
				target.add(e);
			}
			return target;
		} catch (Exception e) {
			log.error("bean转换异常：【" + source.getClass().getName() + "】转换成【" + targetClass.getName() + "】失败", e);
			return Collections.emptyList();
		}
	}

	/**
	 * map转换成bean
	 * @author yuminjun
	 * @date 2020/04/14 14:53:26
	 * @param <T>
	 * @param map
	 * @param targetClass
	 * @return
	 */
	public static <T> T mapToBean(Map<String, Object> map, Class<T> targetClass) {
		if (map == null) {
			return null;
		}
		T obj = null;
		try {
			obj = targetClass.newInstance();
			org.apache.commons.beanutils.BeanUtils.populate(obj, map);
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
			log.error("map转换异常：【" + map.getClass().getName() + "】转换成【" + targetClass.getName() + "】失败", e);
		}
		return obj;
	}

	/**
	 * bean转换成map
	 * @author yuminjun
	 * @date 2020/04/14 14:53:42
	 * @param source
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> beanToMap(Object source) {
		if (source == null) {
			return null;
		}
		return BeanMap.create(source);
	}
}
