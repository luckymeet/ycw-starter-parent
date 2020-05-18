package com.ycw.common.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.filter.OrderedHiddenHttpMethodFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ycw.common.interceptor.web.PageParamsMethodArgumentResolver;

/**
 * Web拦截器配置
 * @author yuminjun
 * @date 2020/04/14 14:44:26
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
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private PageParamsMethodArgumentResolver pageParamsMethodArgumentResolver;

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(pageParamsMethodArgumentResolver);// 分页参数处理
	}

//	@Bean
//	@ConditionalOnMissingBean({HiddenHttpMethodFilter.class})
//	@ConditionalOnProperty(prefix = "spring.mvc.hiddenmethod.filter", name = "enabled", matchIfMissing = true)
//	public OrderedHiddenHttpMethodFilter hiddenHttpMethodFilter () {
//		return new OrderedHiddenHttpMethodFilter();
//	}

}
