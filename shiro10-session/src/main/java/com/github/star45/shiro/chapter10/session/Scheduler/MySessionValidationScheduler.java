/** 
 * @file     MySessionValidationScheduler.java 
 * @brief    shiro10-session's file 
 * @author   许立亢 
 * @date     2015年9月8日
 * @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
 */

package com.github.star45.shiro.chapter10.session.Scheduler;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.AbstractValidatingSessionManager;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ReflectionUtils;

import com.github.star45.shiro.chapter10.utils.JdbcTemplateUtils;
import com.github.star45.shiro.chapter10.utils.SerializableUtils;

/**
 * @brief 类简短说明
 * @details 详细说明
 * @warning 注意事项
 * @date 2015年9月8日
 * @author 许立亢
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class MySessionValidationScheduler implements
		SessionValidationScheduler, Runnable {

	private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();
	private static final Logger log = Logger
			.getLogger(MySessionValidationScheduler.class);

	ValidatingSessionManager sessionManager;
	private ScheduledExecutorService service;
	private long interval = DefaultSessionManager.DEFAULT_SESSION_VALIDATION_INTERVAL;
	private boolean enabled = false;

	public MySessionValidationScheduler() {
		super();
	}

	public ValidatingSessionManager getSessionManager() {
		return sessionManager;
	}

	public void setSessionManager(ValidatingSessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}

	/**
	 * @brief 方法简短说明
	 * @details 详细说明
	 * @warning 注意事项
	 * @date 2015年9月8日 下午3:56:12
	 * @see java.lang.Runnable#run()
	 */

	@Override
	public void run() {
		if (log.isDebugEnabled()) {
			log.debug("Executing session validation...");
		}
		long startTime = System.currentTimeMillis();

		// 分页获取会话并验证
		String sql = "select session from sessions limit ?,?";
		int start = 0; // 起始记录
		int size = 20; // 每页大小
		List<String> sessionList = jdbcTemplate.queryForList(sql, String.class,
				start, size);
		while (sessionList.size() > 0) {
			for (String sessionStr : sessionList) {
				try {
					Session session = SerializableUtils.deserialize(sessionStr);
					Method validateMethod = ReflectionUtils.findMethod(
							AbstractValidatingSessionManager.class, "validate",
							Session.class, SessionKey.class);
					validateMethod.setAccessible(true);
					ReflectionUtils.invokeMethod(validateMethod,
							sessionManager, session, new DefaultSessionKey(
									session.getId()));
				} catch (Exception e) {
					// ignore
				}
			}
			start = start + size;
			sessionList = jdbcTemplate.queryForList(sql, String.class, start,
					size);
		}

		long stopTime = System.currentTimeMillis();
		if (log.isDebugEnabled()) {
			log.debug("Session validation completed successfully in "
					+ (stopTime - startTime) + " milliseconds.");
		}

	}

	/**
	 * @brief 方法简短说明
	 * @details 详细说明
	 * @warning 注意事项
	 * @date 2015年9月8日 下午3:56:12
	 * @return
	 * @see org.apache.shiro.session.mgt.SessionValidationScheduler#isEnabled()
	 */

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @brief 方法简短说明
	 * @details 详细说明
	 * @warning 注意事项
	 * @date 2015年9月8日 下午3:56:12
	 * @see org.apache.shiro.session.mgt.SessionValidationScheduler#enableSessionValidation()
	 */

	@Override
	public void enableSessionValidation() {
		if (this.interval > 0l) {
			this.service = Executors
					.newSingleThreadScheduledExecutor(new ThreadFactory() {
						public Thread newThread(Runnable r) {
							Thread thread = new Thread(r);
							thread.setDaemon(true);
							return thread;
						}
					});
			this.service.scheduleAtFixedRate(this, interval, interval,
					TimeUnit.MILLISECONDS);
			this.enabled = true;
		}

	}

	/**
	 * @brief 方法简短说明
	 * @details 详细说明
	 * @warning 注意事项
	 * @date 2015年9月8日 下午3:56:12
	 * @see org.apache.shiro.session.mgt.SessionValidationScheduler#disableSessionValidation()
	 */

	@Override
	public void disableSessionValidation() {
		this.service.shutdownNow();
		this.enabled = false;

	}

}
