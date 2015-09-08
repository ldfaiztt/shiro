/** 
* @file     ServiceTest.java 
* @brief    shiro06-realm's file 
* @author   许立亢 
* @date     2015年9月7日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter6.service;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.github.star45.shiro.chapter6.BaseTest;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月7日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class ServiceTest extends BaseTest {
	 @Test
	    public void testUserRolePermissionRelation() {

	        //zhang
	        Set<String> roles = userService.findRoles(u1.getUsername());
	        Assert.assertEquals(1, roles.size());
	        Assert.assertTrue(roles.contains(r1.getRole()));

	        Set<String> permissions = userService.findPermissions(u1.getUsername());
	        Assert.assertEquals(3, permissions.size());
	        Assert.assertTrue(permissions.contains(p3.getPermission()));

	        //li
	        roles = userService.findRoles(u2.getUsername());
	        Assert.assertEquals(0, roles.size());
	        permissions = userService.findPermissions(u2.getUsername());
	        Assert.assertEquals(0, permissions.size());


	        //解除 admin-menu:update关联
	        roleService.uncorrelationPermissions(r1.getId(), p3.getId());
	        permissions = userService.findPermissions(u1.getUsername());
	        Assert.assertEquals(2, permissions.size());
	        Assert.assertFalse(permissions.contains(p3.getPermission()));


	        //删除一个permission
	        permissionService.deletePermission(p2.getId());
	        permissions = userService.findPermissions(u1.getUsername());
	        Assert.assertEquals(1, permissions.size());

	        //解除 zhang-admin关联
	        userService.uncorrelationRoles(u1.getId(), r1.getId());
	        roles = userService.findRoles(u1.getUsername());
	        Assert.assertEquals(0, roles.size());


	    }
}
