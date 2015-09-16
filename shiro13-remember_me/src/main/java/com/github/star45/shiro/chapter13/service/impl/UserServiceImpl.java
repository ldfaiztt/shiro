/** 
* @file     UserServiceImpl.java 
* @brief    shiro06-realm's file 
* @author   许立亢 
* @date     2015年9月7日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter13.service.impl;

import java.util.Set;

import com.github.star45.shiro.chapter13.dao.UserDao;
import com.github.star45.shiro.chapter13.entity.User;
import com.github.star45.shiro.chapter13.service.UserService;
import com.github.star45.shiro.chapter13.utils.PasswordHelper;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月7日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class UserServiceImpl implements UserService {

	
	 /** 
	 * @param userDao 要设置的 userDao 
	 */
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/** 
	 * @param passwordHelper 要设置的 passwordHelper 
	 */
	
	public void setPasswordHelper(PasswordHelper passwordHelper) {
		this.passwordHelper = passwordHelper;
	}

	private UserDao userDao;
	 private PasswordHelper passwordHelper;
	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月7日 上午10:34:23
	 * @param user
	 * @return 
	 * @see com.github.star45.shiro.chapter6.service.UserService#createUser(com.github.star45.shiro.chapter6.entity.User) 
	 */

	@Override
	public User createUser(User user) {
		passwordHelper.encryptPassword(user);
		return userDao.createUser(user);
	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月7日 上午10:34:23
	 * @param username
	 * @return 
	 * @see com.github.star45.shiro.chapter6.service.UserService#findByUsername(java.lang.String) 
	 */

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月7日 上午10:34:23
	 * @param userId
	 * @param newPassword 
	 * @see com.github.star45.shiro.chapter6.service.UserService#changePassword(java.lang.Long, java.lang.String) 
	 */

	@Override
	public void changePassword(Long userId, String newPassword) {
		User user =userDao.findOne(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        userDao.updateUser(user);

	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月7日 上午10:34:23
	 * @param userId
	 * @param roleId 
	 * @see com.github.star45.shiro.chapter6.service.UserService#correlationRoles(java.lang.Long, java.lang.Long[]) 
	 */

	@Override
	public void correlationRoles(Long userId, Long... roleIds) {
		userDao.correlationRoles(userId, roleIds);

	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月7日 上午10:34:23
	 * @param userId
	 * @param roleId 
	 * @see com.github.star45.shiro.chapter6.service.UserService#uncorrelationRoles(java.lang.Long, java.lang.Long[]) 
	 */

	@Override
	public void uncorrelationRoles(Long userId, Long... roleIds) {
		userDao.uncorrelationRoles(userId, roleIds);

	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月7日 上午10:34:23
	 * @param username
	 * @return 
	 * @see com.github.star45.shiro.chapter6.service.UserService#findRoles(java.lang.String) 
	 */

	@Override
	public Set<String> findRoles(String username) {
		return userDao.findRoles(username);
	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月7日 上午10:34:23
	 * @param username
	 * @return 
	 * @see com.github.star45.shiro.chapter6.service.UserService#findPermissions(java.lang.String) 
	 */

	@Override
	public Set<String> findPermissions(String username) {
		return userDao.findPermissions(username);
	}

}
