/** 
 * @file     OnlineSessionFactory.java 
 * @brief    shiro10-session's file 
 * @author   许立亢 
 * @date     2015年9月8日
 * @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
 */

package org.apache.shiro.session.mgt;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.web.session.mgt.WebSessionContext;

import com.github.star45.shiro.chapter10.utils.IpUtils;

/**
 * @brief 类简短说明
 * @details 详细说明
 * @warning 注意事项
 * @date 2015年9月8日
 * @author 许立亢
 * @version 1.0
 */

public class OnlineSessionFactory implements SessionFactory {

	/**
	 * @brief 方法简短说明
	 * @details 详细说明
	 * @warning 注意事项
	 * @date 2015年9月8日 下午4:40:02
	 * @param initData
	 * @return
	 * @see org.apache.shiro.session.mgt.SessionFactory#createSession(org.apache.shiro.session.mgt.SessionContext)
	 */

	@Override
	public Session createSession(SessionContext initData) {
		OnlineSession session = new OnlineSession();
		if (initData != null && initData instanceof WebSessionContext) {
			WebSessionContext sessionContext = (WebSessionContext) initData;
			HttpServletRequest request = (HttpServletRequest) sessionContext
					.getServletRequest();
			if (request != null) {
				session.setHost(IpUtils.getIpAddr(request));
				session.setUserAgent(request.getHeader("User-Agent"));
				session.setSystemHost(request.getLocalAddr() + ":"
						+ request.getLocalPort());
			}
		}
		return session;
	}

}
