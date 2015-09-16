/** 
* @file     RoleDao.java 
* @brief    shiro06-realm's file 
* @author   许立亢 
* @date     2015年9月7日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter13.dao;

import com.github.star45.shiro.chapter13.entity.Role;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月7日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public interface RoleDao {
	 public Role createRole(Role role);
	    public void deleteRole(Long roleId);

	    public void correlationPermissions(Long roleId, Long... permissionIds);
	    public void uncorrelationPermissions(Long roleId, Long... permissionIds);

}
