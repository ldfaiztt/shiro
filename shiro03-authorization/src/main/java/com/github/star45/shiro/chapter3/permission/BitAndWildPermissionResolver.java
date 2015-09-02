/** 
* @file     BitAndWildPermissionResolver.java 
* @brief    shiro03-authorization's file 
* @author   许立亢 
* @date     2015年9月1日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter3.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
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

public class BitAndWildPermissionResolver implements PermissionResolver {

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月1日 下午7:02:38
	 * @param permissionString
	 * @return 
	 * @see org.apache.shiro.authz.permission.PermissionResolver#resolvePermission(java.lang.String) 
	 */

	@Override
	public Permission resolvePermission(String permissionString) {
		
		 if(permissionString.startsWith("+")) {
	            return new BitPermission(permissionString);
	        }
	        return new WildcardPermission(permissionString);
	}

}
