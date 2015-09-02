/** 
* @file     NonConfigurationCreateTest.java 
* @brief    shiro04-ini's file 
* @author   许立亢 
* @date     2015年9月2日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter4;

import java.util.Arrays;



import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.AuthenticationStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月2日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class NonConfigurationCreateTest {

	@Test
	public void test(){
		DefaultSecurityManager securityManager = new DefaultSecurityManager();
		
		//设置 authenticator
		ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
		//创建策略
		AuthenticationStrategy authenticationStrategy = new AtLeastOneSuccessfulStrategy();
		authenticator.setAuthenticationStrategy(authenticationStrategy);
		securityManager.setAuthenticator(authenticator);
		
		//设置authorizer
		ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
		PermissionResolver permissionResolver = new WildcardPermissionResolver();
		authorizer.setPermissionResolver(permissionResolver);
		securityManager.setAuthorizer(authorizer);
	
		//设置realm
		DruidDataSource ds = new DruidDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/shiro");
		ds.setUsername("root");
		ds.setPassword("root");
		ds.setTestWhileIdle(false);
		
		JdbcRealm jdbcRealm = new JdbcRealm();
		jdbcRealm.setDataSource(ds);
		jdbcRealm.setPermissionsLookupEnabled(true);
		securityManager.setRealms(Arrays.asList((Realm)jdbcRealm));
		
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject = SecurityUtils.getSubject();
		
		UsernamePasswordToken token = new UsernamePasswordToken("star45", "admin");  
		subject.login(token);  
		Assert.assertTrue(subject.isAuthenticated());  
		
	}
}
