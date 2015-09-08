/** 
* @file     PermissionServiceImpl.java 
* @brief    shiro06-realm's file 
* @author   许立亢 
* @date     2015年9月7日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter6.service.impl;

import com.github.star45.shiro.chapter6.dao.PermissionDao;
import com.github.star45.shiro.chapter6.dao.impl.PermissionDaoImpl;
import com.github.star45.shiro.chapter6.entity.Permission;
import com.github.star45.shiro.chapter6.service.PermissionService;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月7日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class PermissionServiceImpl implements PermissionService {

	 private PermissionDao permissionDao = new PermissionDaoImpl();
	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月7日 上午11:23:21
	 * @param permission
	 * @return 
	 * @see com.github.star45.shiro.chapter6.service.PermissionService#createPermission(com.github.star45.shiro.chapter6.entity.Permission) 
	 */

	@Override
	public Permission createPermission(Permission permission) {
		
		return permissionDao.createPermission(permission);
	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月7日 上午11:23:21
	 * @param permissionId 
	 * @see com.github.star45.shiro.chapter6.service.PermissionService#deletePermission(java.lang.Long) 
	 */

	@Override
	public void deletePermission(Long permissionId) {
		
		permissionDao.deletePermission(permissionId);

	}

}
