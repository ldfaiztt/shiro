/** 
* @file     MyRealm1.java 
* @brief    shiro02's file 
* @author   许立亢 
* @date     2015年8月31日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter2.realm;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月31日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class MyRealm1 implements Realm{
	private static final Logger log = Logger.getLogger(MyRealm1.class);
	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月31日 下午5:26:08
	 * @param arg0
	 * @return
	 * @throws AuthenticationException 
	 * @see org.apache.shiro.realm.Realm#getAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken) 
	 */ 
	
	
	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		log.info("验证realm1......");
		String username = (String)token.getPrincipal(); //获取用户名
		String password = new String((char[])token.getCredentials()); //得到密码 
		if(!"star45".equals(username)) {  
            throw new UnknownAccountException(); //如果用户名错误  
        }  
        if(!"admin".equals(password)) {  
            throw new IncorrectCredentialsException(); //如果密码错误  
        }  
		
		return new SimpleAuthenticationInfo(username,password,getName());
	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月31日 下午5:26:08
	 * @return 
	 * @see org.apache.shiro.realm.Realm#getName() 
	 */ 
	
	
	@Override
	public String getName() {
		
		return "myRealm1";
	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月31日 下午5:26:08
	 * @param arg0
	 * @return 
	 * @see org.apache.shiro.realm.Realm#supports(org.apache.shiro.authc.AuthenticationToken) 
	 */ 
	
	
	@Override
	public boolean supports(AuthenticationToken token) {
		
		//仅支持UsernamePasswordToken类型的Token  
		
		return token instanceof UsernamePasswordToken;
	}

}
