/** 
* @file     RoleService.java 
* @brief    shiro06-realm's file 
* @author   许立亢 
* @date     2015年9月7日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter16.service;

import java.util.List;
import java.util.Set;

import com.github.star45.shiro.chapter16.entity.Role;


/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月7日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public interface RoleService {
	
	 public Role createRole(Role role);
	    public Role updateRole(Role role);
	    public void deleteRole(Long roleId);

	    public Role findOne(Long roleId);
	    public List<Role> findAll();

	    /**
	     * 根据角色编号得到角色标识符列表
	     * @param roleIds
	     * @return
	     */
	    Set<String> findRoles(Long... roleIds);

	    /**
	     * 根据角色编号得到权限字符串列表
	     * @param roleIds
	     * @return
	     */
	    Set<String> findPermissions(Long[] roleIds);

}
