/** 
* @file     MyRolePermissionResolver.java 
* @brief    shiro03-authorization's file 
* @author   许立亢 
* @date     2015年9月1日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter3.permission;

import java.util.Arrays;
import java.util.Collection;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月1日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class MyRolePermissionResolver implements RolePermissionResolver {

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月1日 下午6:57:42
	 * @param roleString
	 * @return 
	 * @see org.apache.shiro.authz.permission.RolePermissionResolver#resolvePermissionsInRole(java.lang.String) 
	 */

	@Override
	public Collection<Permission> resolvePermissionsInRole(String roleString) {
		if("role1".equals(roleString)){
			
			return Arrays.asList((Permission)new WildcardPermission("menu:*"));
			
		}
		return null;
	}

}
