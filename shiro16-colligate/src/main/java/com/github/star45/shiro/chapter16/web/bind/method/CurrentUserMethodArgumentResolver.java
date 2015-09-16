/** 
 * @file     CurrentUserMethodArgumentResolver.java 
 * @brief    shiro16-colligate's file 
 * @author   许立亢 
 * @date     2015年9月16日
 * @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
 */

package com.github.star45.shiro.chapter16.web.bind.method;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.github.star45.shiro.chapter16.web.bind.annotation.CurrentUser;

/**
 * @brief 类简短说明
 * @details 详细说明
 * @warning 注意事项
 * @date 2015年9月16日
 * @author 许立亢
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class CurrentUserMethodArgumentResolver implements
		HandlerMethodArgumentResolver {

	public CurrentUserMethodArgumentResolver() {
	}

	/**
	 * @brief 方法简短说明
	 * @details 详细说明
	 * @warning 注意事项
	 * @date 2015年9月16日 下午1:52:02
	 * @param parameter
	 * @return
	 * @see org.springframework.web.method.support.HandlerMethodArgumentResolver#supportsParameter(org.springframework.core.MethodParameter)
	 */

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if (parameter.hasParameterAnnotation(CurrentUser.class)) {
			return true;
		}
		return false;
	}

	/**
	 * @brief 方法简短说明
	 * @details 详细说明
	 * @warning 注意事项
	 * @date 2015年9月16日 下午1:52:02
	 * @param parameter
	 * @param mavContainer
	 * @param webRequest
	 * @param binderFactory
	 * @return
	 * @throws Exception
	 * @see org.springframework.web.method.support.HandlerMethodArgumentResolver#resolveArgument(org.springframework.core.MethodParameter,
	 *      org.springframework.web.method.support.ModelAndViewContainer,
	 *      org.springframework.web.context.request.NativeWebRequest,
	 *      org.springframework.web.bind.support.WebDataBinderFactory)
	 */

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		CurrentUser currentUserAnnotation = parameter
				.getParameterAnnotation(CurrentUser.class);
		return webRequest.getAttribute(currentUserAnnotation.value(),
				NativeWebRequest.SCOPE_REQUEST);
	}

}
