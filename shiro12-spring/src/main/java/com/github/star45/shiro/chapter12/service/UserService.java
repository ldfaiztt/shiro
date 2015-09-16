/** 
* @file     UserService.java 
* @brief    shiro06-realm's file 
* @author   许立亢 
* @date     2015年9月7日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter12.service;

import java.util.Set;

import com.github.star45.shiro.chapter12.entity.User;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月7日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public interface UserService {
	
	public User createUser(User user);
	
	public User findByUsername(String username);
	
	public void changePassword(Long userId,String newPassword);
	
	public void correlationRoles(Long userId,Long ... roleId);
	
	public void uncorrelationRoles(Long userId,Long ... roleId);
	
	public Set<String> findRoles(String username);
	
	public Set<String> findPermissions(String username);

}
