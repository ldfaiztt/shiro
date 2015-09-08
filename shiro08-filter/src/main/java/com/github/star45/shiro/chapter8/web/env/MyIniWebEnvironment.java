/** 
* @file     MyIniWebEnvironment.java 
* @brief    shiro08-filter's file 
* @author   许立亢 
* @date     2015年9月8日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter8.web.env;

import javax.servlet.Filter;

import org.apache.shiro.util.ClassUtils;
import org.apache.shiro.web.env.IniWebEnvironment;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月8日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class MyIniWebEnvironment extends IniWebEnvironment {
	
	 @Override  
	    protected FilterChainResolver createFilterChainResolver() {  
	        //在此处扩展自己的FilterChainResolver  
		 
		//1、创建FilterChainResolver
	        PathMatchingFilterChainResolver filterChainResolver =
	                new PathMatchingFilterChainResolver();
	        //2、创建FilterChainManager
	        DefaultFilterChainManager filterChainManager = new DefaultFilterChainManager();
	        //3、注册Filter
	        for(DefaultFilter filter : DefaultFilter.values()) {
	            filterChainManager.addFilter(filter.name(), (Filter) ClassUtils.newInstance(filter.getFilterClass()));
	        }
	        //4、注册URL-Filter的映射关系
	        filterChainManager.addToChain("/login.jsp", "authc");
	        filterChainManager.addToChain("/unauthorized.jsp", "anon");
	        filterChainManager.addToChain("/**", "authc");
	        filterChainManager.addToChain("/**", "roles", "admin");

	        //5、设置Filter的属性
	        FormAuthenticationFilter authcFilter =
	                (FormAuthenticationFilter)filterChainManager.getFilter("authc");
	        authcFilter.setLoginUrl("/login.jsp");
	        RolesAuthorizationFilter rolesFilter = (RolesAuthorizationFilter)filterChainManager.getFilter("roles");
	        rolesFilter.setUnauthorizedUrl("/unauthorized.jsp");

	        filterChainResolver.setFilterChainManager(filterChainManager);
	        return filterChainResolver;
//	        return super.createFilterChainResolver();  
	    } 

}
