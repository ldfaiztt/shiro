/** 
* @file     ResourceService.java 
* @brief    shiro16-colligate's file 
* @author   许立亢 
* @date     2015年9月16日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter16.service;

import java.util.List;
import java.util.Set;

import com.github.star45.shiro.chapter16.entity.Resource;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月16日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public interface ResourceService {
	 public Resource createResource(Resource resource);
	    public Resource updateResource(Resource resource);
	    public void deleteResource(Long resourceId);

	    Resource findOne(Long resourceId);
	    List<Resource> findAll();

	    /**
	     * 得到资源对应的权限字符串
	     * @param resourceIds
	     * @return
	     */
	    Set<String> findPermissions(Set<Long> resourceIds);

	    /**
	     * 根据用户权限得到菜单
	     * @param permissions
	     * @return
	     */
	    List<Resource> findMenus(Set<String> permissions);
}
