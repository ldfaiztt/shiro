/** 
* @file     OnlineSessionFilter.java 
* @brief    shiro10-session's file 
* @author   许立亢 
* @date     2015年9月8日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package org.apache.shiro.session.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.ShiroConstants;
import org.apache.shiro.session.mgt.OnlineSession;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月8日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class OnlineSessionFilter extends AccessControlFilter {
	 /**
     * 强制退出后重定向的地址
     */
    private String forceLogoutUrl;

    private SessionDAO sessionDAO;
	/** 
	 * @return forceLogoutUrl 
	 */
	
	public String getForceLogoutUrl() {
		return forceLogoutUrl;
	}

	/** 
	 * @param forceLogoutUrl 要设置的 forceLogoutUrl 
	 */
	
	public void setForceLogoutUrl(String forceLogoutUrl) {
		this.forceLogoutUrl = forceLogoutUrl;
	}

	/** 
	 * @param sessionDAO 要设置的 sessionDAO 
	 */
	
	public void setSessionDAO(SessionDAO sessionDAO) {
		this.sessionDAO = sessionDAO;
	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月8日 下午4:33:45
	 * @param request
	 * @param response
	 * @param mappedValue
	 * @return
	 * @throws Exception 
	 * @see org.apache.shiro.web.filter.AccessControlFilter#isAccessAllowed(javax.servlet.ServletRequest, javax.servlet.ServletResponse, java.lang.Object) 
	 */

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
			Subject subject = getSubject(request, response);
    if (subject == null || subject.getSession(false) == null) {
        return true;
    }
    Session session = sessionDAO.readSession(subject.getSession().getId());
    if (session != null && session instanceof OnlineSession) {
        OnlineSession onlineSession = (OnlineSession) session;
        request.setAttribute(ShiroConstants.ONLINE_SESSION, onlineSession);

        if (onlineSession.getStatus() == OnlineSession.OnlineStatus.force_logout) {
            return false;
        }
    }
    return true;
	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月8日 下午4:33:45
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * @see org.apache.shiro.web.filter.AccessControlFilter#onAccessDenied(javax.servlet.ServletRequest, javax.servlet.ServletResponse) 
	 */

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		 Subject subject = getSubject(request, response);
	        if (subject != null) {
	            subject.logout();
	        }
	        saveRequestAndRedirectToLogin(request, response);
	        return true;
	}
	protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        WebUtils.issueRedirect(request, response, getForceLogoutUrl());
    }

}
