/** 
* @file     UserDaoImpl.java 
* @brief    shiro06-realm's file 
* @author   许立亢 
* @date     2015年9月7日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter13.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.github.star45.shiro.chapter13.dao.UserDao;
import com.github.star45.shiro.chapter13.entity.User;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月7日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月7日 上午11:13:12
	 * @param user
	 * @return 
	 * @see com.github.star45.shiro.chapter6.dao.UserDao#createUser(com.github.star45.shiro.chapter6.entity.User) 
	 */

	@Override
	public User createUser(final User user) {
		 final String sql = "insert into sys_users(username, password, salt, locked) values(?,?,?, ?)";

	        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
	        getJdbcTemplate().update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement psst = connection.prepareStatement(sql, new String[] { "id" });
	                psst.setString(1, user.getUsername());
	                psst.setString(2, user.getPassword());
	                psst.setString(3, user.getSalt());
	                psst.setBoolean(4, user.getLocked());
	                return psst;
	            }
	        }, keyHolder);

	        user.setId(keyHolder.getKey().longValue());
	        return user;
	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月7日 上午11:13:12
	 * @param user 
	 * @see com.github.star45.shiro.chapter6.dao.UserDao#updateUser(com.github.star45.shiro.chapter6.entity.User) 
	 */

	@Override
	public void updateUser(User user) {
		 String sql = "update sys_users set username=?, password=?, salt=?, locked=? where id=?";
	        getJdbcTemplate().update(sql, user.getUsername(), user.getPassword(), user.getSalt(), user.getLocked(), user.getId());

	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月7日 上午11:13:12
	 * @param userId 
	 * @see com.github.star45.shiro.chapter6.dao.UserDao#deleteUser(java.lang.Long) 
	 */

	@Override
	public void deleteUser(Long userId) {
		 String sql = "delete from sys_users where id=?";
	        getJdbcTemplate().update(sql, userId);

	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月7日 上午11:13:12
	 * @param userId
	 * @param roleIds 
	 * @see com.github.star45.shiro.chapter6.dao.UserDao#correlationRoles(java.lang.Long, java.lang.Long[]) 
	 */

	@Override
	public void correlationRoles(Long userId, Long... roleIds) {
		if(roleIds == null || roleIds.length == 0) {
            return;
        }
        String sql = "insert into sys_users_roles(user_id, role_id) values(?,?)";
        for(Long roleId : roleIds) {
            if(!exists(userId, roleId)) {
                getJdbcTemplate().update(sql, userId, roleId);
            }
        }

	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月7日 上午11:13:12
	 * @param userId
	 * @param roleIds 
	 * @see com.github.star45.shiro.chapter6.dao.UserDao#uncorrelationRoles(java.lang.Long, java.lang.Long[]) 
	 */

	@Override
	public void uncorrelationRoles(Long userId, Long... roleIds) {
		if(roleIds == null || roleIds.length == 0) {
            return;
        }
        String sql = "delete from sys_users_roles where user_id=? and role_id=?";
        for(Long roleId : roleIds) {
            if(exists(userId, roleId)) {
                getJdbcTemplate().update(sql, userId, roleId);
            }
        }

	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月7日 上午11:13:12
	 * @param userId
	 * @return 
	 * @see com.github.star45.shiro.chapter6.dao.UserDao#findOne(java.lang.Long) 
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public User findOne(Long userId) {
		String sql = "select id, username, password, salt, locked from sys_users where id=?";
        List<User> userList = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(User.class), userId);
        if(userList.size() == 0) {
            return null;
        }
        return userList.get(0);
	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月7日 上午11:13:12
	 * @param username
	 * @return 
	 * @see com.github.star45.shiro.chapter6.dao.UserDao#findByUsername(java.lang.String) 
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public User findByUsername(String username) {
		 String sql = "select id, username, password, salt, locked from sys_users where username=?";
	        List<User> userList = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(User.class), username);
	        if(userList.size() == 0) {
	            return null;
	        }
	        return userList.get(0);	
	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月7日 上午11:13:12
	 * @param username
	 * @return 
	 * @see com.github.star45.shiro.chapter6.dao.UserDao#findRoles(java.lang.String) 
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Set<String> findRoles(String username) {
		String sql = "select role from sys_users u, sys_roles r,sys_users_roles ur where u.username=? and u.id=ur.user_id and r.id=ur.role_id";
        return new HashSet(getJdbcTemplate().queryForList(sql, String.class, username));
	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月7日 上午11:13:12
	 * @param username
	 * @return 
	 * @see com.github.star45.shiro.chapter6.dao.UserDao#findPermissions(java.lang.String) 
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Set<String> findPermissions(String username) {
		//TODO 此处可以优化，比如查询到role后，一起获取roleId，然后直接根据roleId获取即可
        String sql = "select permission from sys_users u, sys_roles r, sys_permissions p, sys_users_roles ur, sys_roles_permissions rp where u.username=? and u.id=ur.user_id and r.id=ur.role_id and r.id=rp.role_id and p.id=rp.permission_id";
        return new HashSet(getJdbcTemplate().queryForList(sql, String.class, username));
	}
	private boolean exists(Long userId, Long roleId) {
        String sql = "select count(1) from sys_users_roles where user_id=? and role_id=?";
        return getJdbcTemplate().queryForObject(sql, Integer.class, userId, roleId) != 0;
    }

}
