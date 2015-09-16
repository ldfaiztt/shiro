/** 
* @file     UserRealmTest.java 
* @brief    shiro11-cache's file 
* @author   许立亢 
* @date     2015年9月10日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter11;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.junit.Test;

import com.github.star45.shiro.chapter11.realm.UserRealm;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月10日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class UserRealmTest extends BaseTest {
	 @Override
	    public void tearDown() throws Exception {
	        userService.changePassword(u1.getId(), password);
	        RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
	        UserRealm userRealm = (UserRealm) securityManager.getRealms().iterator().next();
	        userRealm.clearCachedAuthenticationInfo(subject().getPrincipals());

	        super.tearDown();
	    }

	    @Test
	      public void testClearCachedAuthenticationInfo() {
	        login(u1.getUsername(), password);
	        userService.changePassword(u1.getId(), password + "1");

	        RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
	        UserRealm userRealm = (UserRealm) securityManager.getRealms().iterator().next();
	        userRealm.clearCachedAuthenticationInfo(subject().getPrincipals());

	        login(u1.getUsername(), password + "1");


	    }

	    @Test
	    public void testClearCachedAuthorizationInfo() {
	        login(u1.getUsername(), password);
	        subject().checkRole(r1.getRole());
	        userService.correlationRoles(u1.getId(), r2.getId());

	        RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
	        UserRealm userRealm = (UserRealm) securityManager.getRealms().iterator().next();
	        userRealm.clearCachedAuthorizationInfo(subject().getPrincipals());

	        subject().checkRole(r2.getRole());
	    }



	    @Test
	    public void testClearCache() {
	        login(u1.getUsername(), password);
	        subject().checkRole(r1.getRole());

	        userService.changePassword(u1.getId(), password + "1");
	        userService.correlationRoles(u1.getId(), r2.getId());

	        RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
	        UserRealm userRealm = (UserRealm) securityManager.getRealms().iterator().next();
	        userRealm.clearCache(subject().getPrincipals());

	        login(u1.getUsername(), password + "1");
	        subject().checkRole(r2.getRole());
	    }
}
