/** 
* @file     User.java 
* @brief    shiro06-realm's file 
* @author   许立亢 
* @date     2015年9月7日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter16.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月7日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

@SuppressWarnings("serial")
public class User implements Serializable{
	 private Long id; //编号
	    private Long organizationId; //所属公司
	    private String username; //用户名
	    private String password; //密码
	    private String salt; //加密密码的盐
	    private List<Long> roleIds; //拥有的角色列表
	    private Boolean locked = Boolean.FALSE;

	    public User() {
	    }

	    public User(String username, String password) {
	        this.username = username;
	        this.password = password;
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public Long getOrganizationId() {
	        return organizationId;
	    }

	    public void setOrganizationId(Long organizationId) {
	        this.organizationId = organizationId;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getSalt() {
	        return salt;
	    }

	    public void setSalt(String salt) {
	        this.salt = salt;
	    }

	    public String getCredentialsSalt() {
	        return username + salt;
	    }

	    public List<Long> getRoleIds() {
	        if(roleIds == null) {
	            roleIds = new ArrayList<Long>();
	        }
	        return roleIds;
	    }

	    public void setRoleIds(List<Long> roleIds) {
	        this.roleIds = roleIds;
	    }


	    public String getRoleIdsStr() {
	        if(CollectionUtils.isEmpty(roleIds)) {
	            return "";
	        }
	        StringBuilder s = new StringBuilder();
	        for(Long roleId : roleIds) {
	            s.append(roleId);
	            s.append(",");
	        }
	        return s.toString();
	    }

	    public void setRoleIdsStr(String roleIdsStr) {
	        if(StringUtils.isEmpty(roleIdsStr)) {
	            return;
	        }
	        String[] roleIdStrs = roleIdsStr.split(",");
	        for(String roleIdStr : roleIdStrs) {
	            if(StringUtils.isEmpty(roleIdStr)) {
	                continue;
	            }
	            getRoleIds().add(Long.valueOf(roleIdStr));
	        }
	    }
	    
	    public Boolean getLocked() {
	        return locked;
	    }

	    public void setLocked(Boolean locked) {
	        this.locked = locked;
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;

	        User user = (User) o;

	        if (id != null ? !id.equals(user.id) : user.id != null) return false;

	        return true;
	    }

	    @Override
	    public int hashCode() {
	        return id != null ? id.hashCode() : 0;
	    }

	    @Override
	    public String toString() {
	        return "User{" +
	                "id=" + id +
	                ", organizationId=" + organizationId +
	                ", username='" + username + '\'' +
	                ", password='" + password + '\'' +
	                ", salt='" + salt + '\'' +
	                ", roleIds=" + roleIds +
	                ", locked=" + locked +
	                '}';
	    }
}
