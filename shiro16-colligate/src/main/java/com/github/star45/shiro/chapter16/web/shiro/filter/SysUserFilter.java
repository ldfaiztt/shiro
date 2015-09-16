/** 
 * @file     SysUserFilter.java 
 * @brief    shiro16-colligate's file 
 * @author   许立亢 
 * @date     2015年9月16日
 * @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
 */

package com.github.star45.shiro.chapter16.web.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.star45.shiro.chapter16.service.UserService;
import com.github.star45.shiro.chapter16.web.Constants;

/**
 * @brief 类简短说明
 * @details 详细说明
 * @warning 注意事项
 * @date 2015年9月16日
 * @author 许立亢
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class SysUserFilter extends PathMatchingFilter {
	@Autowired
	private UserService userService;

	@Override
	protected boolean onPreHandle(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {

		String username = (String) SecurityUtils.getSubject().getPrincipal();
		request.setAttribute(Constants.CURRENT_USER,
				userService.findByUsername(username));
		return true;
	}
}
