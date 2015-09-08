/** 
* @file     AnyRolesFilter.java 
* @brief    shiro08-filter's file 
* @author   许立亢 
* @date     2015年9月8日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter8.web.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月8日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class AnyRolesFilter extends AccessControlFilter {
	private String unauthorizedUrl = "/unauthorized.jsp";
    private String loginUrl = "/login.jsp";
	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月8日 下午1:52:00
	 * @param request
	 * @param response
	 * @param mappedValue
	 * @return
	 * @throws Exception 
	 * @see org.apache.shiro.web.filter.AccessControlFilter#isAccessAllowed(javax.servlet.ServletRequest, javax.servlet.ServletResponse, java.lang.Object) 
	 */

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
	        String[] roles = (String[])mappedValue;
	        if(roles == null) {
	            return true;//如果没有设置角色参数，默认成功
	        }
	        for(String role : roles) {
	            if(getSubject(request, response).hasRole(role)) {
	                return true;
	            }
	        }
	        return false;//跳到onAccessDenied处理
	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月8日 下午1:52:00
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * @see org.apache.shiro.web.filter.AccessControlFilter#onAccessDenied(javax.servlet.ServletRequest, javax.servlet.ServletResponse) 
	 */

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		 Subject subject = getSubject(request, response);
	        if (subject.getPrincipal() == null) {//表示没有登录，重定向到登录页面
	            saveRequest(request);
	            WebUtils.issueRedirect(request, response, loginUrl);
	        } else {
	            if (StringUtils.hasText(unauthorizedUrl)) {//如果有未授权页面跳转过去
	                WebUtils.issueRedirect(request, response, unauthorizedUrl);
	            } else {//否则返回401未授权状态码
	                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
	            }
	        }
	        return false;
	}

}
