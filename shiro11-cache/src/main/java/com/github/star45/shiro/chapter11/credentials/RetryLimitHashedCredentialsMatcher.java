/** 
* @file     RetryLimitHashedCredentialsMatcher.java 
* @brief    shiro06-realm's file 
* @author   许立亢 
* @date     2015年9月7日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter11.credentials;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月7日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class RetryLimitHashedCredentialsMatcher extends
		HashedCredentialsMatcher {
	 private Ehcache passwordRetryCache;

	    public RetryLimitHashedCredentialsMatcher() {
	        CacheManager cacheManager = CacheManager.newInstance(CacheManager.class.getClassLoader().getResource("ehcache.xml"));
	        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	    }

	    @Override
	    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
	        String username = (String)token.getPrincipal();
	        //retry count + 1
	        Element element = passwordRetryCache.get(username);
	        if(element == null) {
	            element = new Element(username , new AtomicInteger(0));
	            passwordRetryCache.put(element);
	        }
	        AtomicInteger retryCount = (AtomicInteger)element.getObjectValue();
	        if(retryCount.incrementAndGet() > 5) {
	            //if retry count > 5 throw
	            throw new ExcessiveAttemptsException();
	        }

	        boolean matches = super.doCredentialsMatch(token, info);
	        if(matches) {
	            //clear retry count
	            passwordRetryCache.remove(username);
	        }
	        return matches;
	    }

}
