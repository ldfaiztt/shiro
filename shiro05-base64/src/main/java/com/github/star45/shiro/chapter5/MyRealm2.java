/** 
* @file     MyRealm2.java 
* @brief    shiro05-base64's file 
* @author   许立亢 
* @date     2015年9月2日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter5;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @brief 使用生成密码散列值
此处我们使用MD5算法，“密码+盐（用户名+随机数）”的方式生成散列值：
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月2日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class MyRealm2 extends AuthorizingRealm {

	 @Override
	    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
	        return null;
	    }

	    @Override
	    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
	        String username = "xulikang$"; //用户名及salt1
	        String salt2 = "71f850aa94fd3eff3f23aa08b4b60ee4";
	        String password = "a7d9c80e362884e1d5ca74e5dd5c9772"; //加密后的密码
	        SimpleAuthenticationInfo ai = new SimpleAuthenticationInfo(username, password, getName());
	        ai.setCredentialsSalt(ByteSource.Util.bytes(username+salt2)); //盐是用户名+随机数
	        return ai;
	    }
}
