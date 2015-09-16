/** 
* @file     RoleServiecImpl.java 
* @brief    shiro06-realm's file 
* @author   许立亢 
* @date     2015年9月7日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter13.service.impl;

import com.github.star45.shiro.chapter13.dao.RoleDao;
import com.github.star45.shiro.chapter13.entity.Role;
import com.github.star45.shiro.chapter13.service.RoleService;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月7日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class RoleServiceImpl implements RoleService {
	private RoleDao roleDao;
	/** 
	 * @param roleDao 要设置的 roleDao 
	 */
	
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月7日 上午11:22:55
	 * @param role
	 * @return 
	 * @see com.github.star45.shiro.chapter6.service.RoleService#createRole(com.github.star45.shiro.chapter6.entity.Role) 
	 */

	@Override
	public Role createRole(Role role) {
		
		return roleDao.createRole(role);
	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月7日 上午11:22:55
	 * @param roleId 
	 * @see com.github.star45.shiro.chapter6.service.RoleService#delateRole(java.lang.Long) 
	 */

	@Override
	public void delateRole(Long roleId) {
		roleDao.deleteRole(roleId);

	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月7日 上午11:22:55
	 * @param roleId
	 * @param permissionId 
	 * @see com.github.star45.shiro.chapter6.service.RoleService#correlationPermissions(java.lang.Long, java.lang.Long[]) 
	 */

	@Override
	public void correlationPermissions(Long roleId, Long... permissionIds) {
		roleDao.correlationPermissions(roleId, permissionIds);

	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月7日 上午11:22:55
	 * @param roleId
	 * @param permissionId 
	 * @see com.github.star45.shiro.chapter6.service.RoleService#uncorrelationPermissions(java.lang.Long, java.lang.Long[]) 
	 */

	@Override
	public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
		roleDao.uncorrelationPermissions(roleId, permissionIds);

	}

}
