/** 
* @file     AtLeastTwoAuthenticatorStrategy.java 
* @brief    shiro02-authenticator's file 
* @author   许立亢 
* @date     2015年9月1日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter2.realm.authenticator;

import java.util.Collection;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.pam.AbstractAuthenticationStrategy;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.util.CollectionUtils;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月1日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class AtLeastTwoAuthenticatorStrategy extends AbstractAuthenticationStrategy{
	
	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月1日 下午4:23:12
	 * @param realms
	 * @param token
	 * @return
	 * @throws AuthenticationException 
	 * @see org.apache.shiro.authc.pam.AbstractAuthenticationStrategy#beforeAllAttempts(java.util.Collection, org.apache.shiro.authc.AuthenticationToken) 
	 */ 
	
	
	@Override
	public AuthenticationInfo beforeAllAttempts(
			Collection<? extends Realm> realms, AuthenticationToken token)
			throws AuthenticationException {
		
		return new SimpleAuthenticationInfo();//返回一个权限的认证信息;
	}
	
	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月1日 下午4:23:19
	 * @param realm
	 * @param token
	 * @param aggregate
	 * @return
	 * @throws AuthenticationException 
	 * @see org.apache.shiro.authc.pam.AbstractAuthenticationStrategy#beforeAttempt(org.apache.shiro.realm.Realm, org.apache.shiro.authc.AuthenticationToken, org.apache.shiro.authc.AuthenticationInfo) 
	 */ 
	
	
	@Override
	public AuthenticationInfo beforeAttempt(Realm realm,
			AuthenticationToken token, AuthenticationInfo aggregate)
			throws AuthenticationException {
		
		 return aggregate;//返回之前合并的
	}
	
	
	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月1日 下午4:23:25
	 * @param realm
	 * @param token
	 * @param singleRealmInfo
	 * @param aggregateInfo
	 * @param t
	 * @return
	 * @throws AuthenticationException 
	 * @see org.apache.shiro.authc.pam.AbstractAuthenticationStrategy#afterAttempt(org.apache.shiro.realm.Realm, org.apache.shiro.authc.AuthenticationToken, org.apache.shiro.authc.AuthenticationInfo, org.apache.shiro.authc.AuthenticationInfo, java.lang.Throwable) 
	 */ 
	
	
	@Override
	public AuthenticationInfo afterAttempt(Realm realm,
			AuthenticationToken token, AuthenticationInfo singleRealmInfo,
			AuthenticationInfo aggregateInfo, Throwable t)
			throws AuthenticationException {
		AuthenticationInfo info;
        if (singleRealmInfo == null) {
            info = aggregateInfo;
        } else {
            if (aggregateInfo == null) {
                info = singleRealmInfo;
            } else {
                info = merge(singleRealmInfo, aggregateInfo);
            }
        }

        return info;
	}
	
	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月1日 下午4:23:31
	 * @param token
	 * @param aggregate
	 * @return
	 * @throws AuthenticationException 
	 * @see org.apache.shiro.authc.pam.AbstractAuthenticationStrategy#afterAllAttempts(org.apache.shiro.authc.AuthenticationToken, org.apache.shiro.authc.AuthenticationInfo) 
	 */ 
	
	
	@Override
	public AuthenticationInfo afterAllAttempts(AuthenticationToken token,
			AuthenticationInfo aggregate) throws AuthenticationException {
		if (aggregate == null || CollectionUtils.isEmpty(aggregate.getPrincipals()) || aggregate.getPrincipals().getRealmNames().size() < 2) {
            throw new AuthenticationException("Authentication token of type [" + token.getClass() + "] " +
                    "could not be authenticated by any configured realms.  Please ensure that at least two realm can " +
                    "authenticate these tokens.");
        }

        return aggregate;
	}
	
	

}
