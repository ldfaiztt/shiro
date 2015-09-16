/** 
* @file     UserDao.java 
* @brief    shiro06-realm's file 
* @author   许立亢 
* @date     2015年9月7日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter11.dao;

import java.util.Set;

import com.github.star45.shiro.chapter11.entity.User;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月7日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public interface UserDao {
	 public User createUser(User user);
	    public void updateUser(User user);
	    public void deleteUser(Long userId);

	    public void correlationRoles(Long userId, Long... roleIds);
	    public void uncorrelationRoles(Long userId, Long... roleIds);

	    User findOne(Long userId);

	    User findByUsername(String username);

	    Set<String> findRoles(String username);

	    Set<String> findPermissions(String username);
}
