/** 
* @file     MyOncePerRequestFilter.java 
* @brief    shiro08-filter's file 
* @author   许立亢 
* @date     2015年9月8日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter8.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.servlet.OncePerRequestFilter;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月8日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class MyOncePerRequestFilter extends OncePerRequestFilter {

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月8日 下午1:39:36
	 * @param request
	 * @param response
	 * @param chain
	 * @throws ServletException
	 * @throws IOException 
	 * @see org.apache.shiro.web.servlet.OncePerRequestFilter#doFilterInternal(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain) 
	 */

	@Override
	protected void doFilterInternal(ServletRequest request,
			ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		System.out.println("===请求======once per request filter");  
        chain.doFilter(request, response);  

	}

}
