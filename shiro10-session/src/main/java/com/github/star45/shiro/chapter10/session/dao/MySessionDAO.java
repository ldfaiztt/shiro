/** 
* @file     MySessionDAO.java 
* @brief    shiro10-session's file 
* @author   许立亢 
* @date     2015年9月8日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter10.session.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.jdbc.core.JdbcTemplate;

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

public class MySessionDAO extends CachingSessionDAO {

	private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();
	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月8日 下午3:55:30
	 * @param session 
	 * @see org.apache.shiro.session.mgt.eis.CachingSessionDAO#doUpdate(org.apache.shiro.session.Session) 
	 */

	@Override
	protected void doUpdate(Session session) {
		if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
            return; //如果会话过期/停止 没必要再更新了
        }
        String sql = "update sessions set session=? where id=?";
        jdbcTemplate.update(sql, SerializableUtils.serialize(session), session.getId());

	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月8日 下午3:55:30
	 * @param session 
	 * @see org.apache.shiro.session.mgt.eis.CachingSessionDAO#doDelete(org.apache.shiro.session.Session) 
	 */

	@Override
	protected void doDelete(Session session) {
		String sql = "delete from sessions where id=?";
        jdbcTemplate.update(sql, session.getId());

	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月8日 下午3:55:30
	 * @param session
	 * @return 
	 * @see org.apache.shiro.session.mgt.eis.AbstractSessionDAO#doCreate(org.apache.shiro.session.Session) 
	 */

	@Override
	protected Serializable doCreate(Session session) {
		 Serializable sessionId = generateSessionId(session);
	        assignSessionId(session, sessionId);
	        String sql = "insert into sessions(id, session) values(?,?)";
	        jdbcTemplate.update(sql, sessionId, SerializableUtils.serialize(session));
	        return session.getId();
	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年9月8日 下午3:55:30
	 * @param sessionId
	 * @return 
	 * @see org.apache.shiro.session.mgt.eis.AbstractSessionDAO#doReadSession(java.io.Serializable) 
	 */

	@Override
	protected Session doReadSession(Serializable sessionId) {
		String sql = "select session from sessions where id=?";
        List<String> sessionStrList = jdbcTemplate.queryForList(sql, String.class, sessionId);
        if(sessionStrList.size() == 0) return null;
        return SerializableUtils.deserialize(sessionStrList.get(0));
	}

}
