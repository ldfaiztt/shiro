/** 
 * @file     AuthorizerTest.java 
 * @brief    shiro03-authorization's file 
 * @author   许立亢 
 * @date     2015年9月1日
 * @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
 */

package com.github.star45.shiro.chapter3;

import org.junit.Assert;
import org.junit.Test;

/**
 * @brief 类简短说明
 * @details 详细说明
 * @warning 注意事项
 * @date 2015年9月1日
 * @author 许立亢
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class AuthorizerTest extends BaseTest {
	@Test
	public void testIsPermitted() {
		login("classpath:shiro-authorizer.ini", "star45", "admin");
		// 判断拥有权限：user:create
		Assert.assertTrue(subject().isPermitted("user1:update"));
		Assert.assertTrue(subject().isPermitted("user2:update"));
		// 通过二进制位的方式表示权限
		Assert.assertTrue(subject().isPermitted("+user1+2"));// 新增权限
		Assert.assertTrue(subject().isPermitted("+user1+8"));// 查看权限
		Assert.assertTrue(subject().isPermitted("+user2+10"));// 新增及查看

		Assert.assertFalse(subject().isPermitted("+user1+4"));// 没有删除权限

		Assert.assertTrue(subject().isPermitted("menu:view"));// 通过MyRolePermissionResolver解析得到的权限
	}

	@Test
	public void testIsPermitted2() {
		login("classpath:shiro-jdbc-authorizer.ini", "star45", "admin");
		// 判断拥有权限：user:create
		Assert.assertTrue(subject().isPermitted("user1:update"));
		Assert.assertTrue(subject().isPermitted("user2:update"));
		// 通过二进制位的方式表示权限
		Assert.assertTrue(subject().isPermitted("+user1+2"));// 新增权限
		Assert.assertTrue(subject().isPermitted("+user1+8"));// 查看权限
		Assert.assertTrue(subject().isPermitted("+user2+10"));// 新增及查看

		Assert.assertFalse(subject().isPermitted("+user1+4"));// 没有删除权限

		Assert.assertTrue(subject().isPermitted("menu:view"));// 通过MyRolePermissionResolver解析得到的权限
	}
}
