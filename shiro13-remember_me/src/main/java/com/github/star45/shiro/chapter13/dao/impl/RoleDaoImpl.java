/** 
* @file     RoleDaoImpl.java 
* @brief    shiro06-realm's file 
* @author   许立亢 
* @date     2015年9月7日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter13.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.github.star45.shiro.chapter13.dao.RoleDao;
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

public class RoleDaoImpl extends JdbcDaoSupport implements RoleDao {
	
	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月7日 上午11:17:26
	 * @param role
	 * @return 
	 * @see com.github.star45.shiro.chapter6.dao.RoleDao#createRole(com.github.star45.shiro.chapter6.entity.Role) 
	 */ 
	
	
	@Override
	public Role createRole(final Role role) {
		final String sql = "insert into sys_roles(role, description, available) values(?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql, new String[] { "id" });
                psst.setString(1, role.getRole());
                psst.setString(2, role.getDescription());
                psst.setBoolean(3, role.getAvailable());
                return psst;
            }
        }, keyHolder);
        role.setId(keyHolder.getKey().longValue());
        return role;
	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月7日 上午11:17:26
	 * @param roleId 
	 * @see com.github.star45.shiro.chapter6.dao.RoleDao#deleteRole(java.lang.Long) 
	 */ 
	
	
	@Override
	public void deleteRole(Long roleId) {
		 //首先把和role关联的相关表数据删掉
        String sql = "delete from sys_users_roles where role_id=?";
        getJdbcTemplate().update(sql, roleId);

        sql = "delete from sys_roles where id=?";
        getJdbcTemplate().update(sql, roleId);
		
	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月7日 上午11:17:26
	 * @param roleId
	 * @param permissionIds 
	 * @see com.github.star45.shiro.chapter6.dao.RoleDao#correlationPermissions(java.lang.Long, java.lang.Long[]) 
	 */ 
	
	
	@Override
	public void correlationPermissions(Long roleId, Long... permissionIds) {
		if(permissionIds == null || permissionIds.length == 0) {
            return;
        }
        String sql = "insert into sys_roles_permissions(role_id, permission_id) values(?,?)";
        for(Long permissionId : permissionIds) {
            if(!exists(roleId, permissionId)) {
            	getJdbcTemplate().update(sql, roleId, permissionId);
            }
        }
		
	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月7日 上午11:17:26
	 * @param roleId
	 * @param permissionIds 
	 * @see com.github.star45.shiro.chapter6.dao.RoleDao#uncorrelationPermissions(java.lang.Long, java.lang.Long[]) 
	 */ 
	
	
	@Override
	public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
		 if(permissionIds == null || permissionIds.length == 0) {
	            return;
	        }
	        String sql = "delete from sys_roles_permissions where role_id=? and permission_id=?";
	        for(Long permissionId : permissionIds) {
	            if(exists(roleId, permissionId)) {
	            	getJdbcTemplate().update(sql, roleId, permissionId);
	            }
	        }
		
	}
	  private boolean exists(Long roleId, Long permissionId) {
	        String sql = "select count(1) from sys_roles_permissions where role_id=? and permission_id=?";
	        return getJdbcTemplate().queryForObject(sql, Integer.class, roleId, permissionId) != 0;
	    }

	
}
