package com.ycw.common.interceptor.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.ycw.common.page.PageParam;

/**
 * 分页方法参数处理
 * @author yuminjun
 * @date 2020/04/14 14:48:12
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
@Component
public class PageParamMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType().isAssignableFrom(PageParam.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		/* json类型的请求不处理 */
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		if (request.getContentType() != null && request.getContentType().contains("application/json")) {
			return null;
		}

		/* 组装分页参数 */
		String pageNum = webRequest.getParameter("pageNum");
		String pageSize = webRequest.getParameter("pageSize");
		PageParam pageParam = null;
		if(StringUtils.isNotBlank(pageNum) && StringUtils.isNotBlank(pageSize)) {
			pageParam = new PageParam(Integer.valueOf(pageNum), Integer.valueOf(pageSize));
		}

		return pageParam;
	}

}
