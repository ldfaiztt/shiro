/** 
* @file     MySessionListener1.java 
* @brief    shiro10-session's file 
* @author   许立亢 
* @date     2015年9月8日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter10.web.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月8日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class MySessionListener1 implements SessionListener {

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月8日 下午3:51:46
	 * @param session 
	 * @see org.apache.shiro.session.SessionListener#onStart(org.apache.shiro.session.Session) 
	 */

	@Override
	public void onStart(Session session) {//会话创建时触发
		System.out.println("会话创建：" + session.getId());

	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月8日 下午3:51:46
	 * @param session 
	 * @see org.apache.shiro.session.SessionListener#onStop(org.apache.shiro.session.Session) 
	 */

	@Override
	public void onStop(Session session) {//会话停止时触发
		System.out.println("会话停止：" + session.getId());

	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月8日 下午3:51:46
	 * @param session 
	 * @see org.apache.shiro.session.SessionListener#onExpiration(org.apache.shiro.session.Session) 
	 */

	@Override
	public void onExpiration(Session session) {//会话过期时触发
		 System.out.println("会话过期：" + session.getId());

	}

}
