/** 
 * @file     RoleTest.java 
 * @brief    shiro03-authorization's file 
 * @author   许立亢 
 * @date     2015年9月1日
 * @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
 */

package com.github.star45.shiro.chapter3;

import java.util.Arrays;

import org.apache.shiro.authz.UnauthorizedException;
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

public class RoleTest extends BaseTest {

	@Test
	public void testHasRole() {
		login("classpath:shiro.ini", "star45", "admin");
		// 判断是否拥有role1角色
		Assert.assertTrue(subject().hasRole("role1"));
		// 判断是否拥有role1、role2角色
		Assert.assertTrue(subject()
				.hasAllRoles(Arrays.asList("role1", "role2")));
		boolean[] result = subject().hasRoles(
				Arrays.asList("role1", "role2", "role3"));
		Assert.assertTrue(result[0]);
		Assert.assertTrue(result[1]);
		Assert.assertFalse(result[2]);

	}

	/**
	 * 
	 * @brief 方法简短说明
	 * @details Shiro提供的checkRole/checkRoles和hasRole/
	 *          hasAllRoles不同的地方是它在判断为假的情况下会抛出UnauthorizedException异常。
	 * @warning 注意事项
	 * @date 2015年9月1日 下午5:45:15
	 */
	@Test(expected = UnauthorizedException.class)
	public void testCheckRole() {
		login("classpath:shiro-role.ini", "star45", "admin");
		// 断言拥有角色：role1
		subject().checkRole("role1");
		// 断言拥有角色：role1 and role3 失败抛出异常
		subject().checkRoles("role1", "role3");
	}
   

}
