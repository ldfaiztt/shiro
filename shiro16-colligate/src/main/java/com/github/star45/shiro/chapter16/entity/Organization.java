/** 
* @file     Organization.java 
* @brief    shiro16-colligate's file 
* @author   许立亢 
* @date     2015年9月16日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter16.entity;

import java.io.Serializable;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月16日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

@SuppressWarnings("serial")
public class Organization implements Serializable{
	 private Long id; //编号
	    private String name; //组织机构名称
	    private Long parentId; //父编号
	    private String parentIds; //父编号列表，如1/2/
	    private Boolean available = Boolean.FALSE;


	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public Long getParentId() {
	        return parentId;
	    }

	    public void setParentId(Long parentId) {
	        this.parentId = parentId;
	    }

	    public String getParentIds() {
	        return parentIds;
	    }

	    public void setParentIds(String parentIds) {
	        this.parentIds = parentIds;
	    }

	    public Boolean getAvailable() {
	        return available;
	    }

	    public void setAvailable(Boolean available) {
	        this.available = available;
	    }

	    public boolean isRootNode() {
	        return parentId == 0;
	    }

	    public String makeSelfAsParentIds() {
	        return getParentIds() + getId() + "/";
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;

	        Organization that = (Organization) o;

	        if (id != null ? !id.equals(that.id) : that.id != null) return false;

	        return true;
	    }

	    @Override
	    public int hashCode() {
	        return id != null ? id.hashCode() : 0;
	    }

	    @Override
	    public String toString() {
	        return "Organization{" +
	                "id=" + id +
	                ", name='" + name + '\'' +
	                ", parentId=" + parentId +
	                ", parentIds='" + parentIds + '\'' +
	                ", available=" + available +
	                '}';
	    }
}
