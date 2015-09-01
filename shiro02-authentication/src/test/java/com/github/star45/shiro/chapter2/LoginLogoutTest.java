/** 
 * @file     LoginLogout.java 
 * @brief    shiro01's file 
 * @author   许立亢 
 * @date     2015年8月31日
 * @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
 */

package com.github.star45.shiro.chapter2;


import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @brief 类简短说明
 * @details 详细说明
 * @warning 注意事项
 * @date 2015年8月31日
 * @author 许立亢
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class LoginLogoutTest {

	private static final Logger log = Logger.getLogger(LoginLogoutTest.class);

	@Test
	public void helloworld() {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:shiro.ini");
		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		SecurityManager securityManager = factory.getInstance();

		SecurityUtils.setSecurityManager(securityManager);

		Subject subject = SecurityUtils.getSubject();

		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

		try {
			subject.login(token);
			log.info("helloworld 登录成功......");
		} catch (AuthenticationException e) {

			log.info("登录失败......");
			e.printStackTrace();
		}
		Assert.assertEquals(true, subject.isAuthenticated());

		subject.logout();

	}
	@Test
	public void myRealm() {

		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:shiro-realm.ini");

		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory
				.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("star45",
				"admin");

		try {
			// 4、登录，即身份验证
			subject.login(token);
			log.info("myRealm 登录成功......");
		} catch (AuthenticationException e) {
			// 5、身份验证失败
			e.printStackTrace();
		}

		Assert.assertEquals(true, subject.isAuthenticated()); // 断言用户已经登录

		// 6、退出
		subject.logout();
	}
	@Test
	public void multiRealm() {
		// 1、获取SecurityManager工厂类，使用ini配置文件初始化SecurityManager
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:shiro-multi-realm.ini");

		// 2、获得securityManager实例，并且绑定到SecurityUitls上
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// 3、得到subject且创建用户名/密码身份验证token(即用户身份/凭证)
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("xulikang",
				"admin");

		try {
			// 4、登录，即身份验证
			subject.login(token);
			log.info("multiRealm 登录成功......");
		} catch (AuthenticationException e) {
			// 5、身份验证失败
			log.error("登录失败......");
			e.printStackTrace();
		}
		Assert.assertEquals(true, subject.isAuthenticated());
		// 6、退出
		subject.logout();
	}
	@Test
	public void jdbcRealm() {
		// 1、获取SecurityManager工厂类，使用ini配置文件初始化SecurityManager
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:shiro-jdbc-realm.ini");

		// 2、获得securityManager实例，并且绑定到SecurityUitls上
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// 3、得到subject且创建用户名/密码身份验证token(即用户身份/凭证)
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("star45",
				"admin");

		try {
			// 4、登录，即身份验证
			subject.login(token);
			log.info("jdbcRealm 登录成功......");
		} catch (AuthenticationException e) {
			// 5、身份验证失败
			log.error("登录失败......");
			e.printStackTrace();
		}
		Assert.assertEquals(true, subject.isAuthenticated());
		// 6、退出
		subject.logout();

	}

}
