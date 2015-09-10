/** 
* @file     JdbcTemplateUtils.java 
* @brief    shiro10-session's file 
* @author   许立亢 
* @date     2015年9月8日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter10.utils;

import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月8日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class JdbcTemplateUtils {
	  private static JdbcTemplate jdbcTemplate;

	    public static JdbcTemplate jdbcTemplate() {
	        if(jdbcTemplate == null) {
	            jdbcTemplate = createJdbcTemplate();
	        }
	        return jdbcTemplate;
	    }

	    private static JdbcTemplate createJdbcTemplate() {

	        DruidDataSource ds = new DruidDataSource();
	        ds.setDriverClassName("com.mysql.jdbc.Driver");
	        ds.setUrl("jdbc:mysql://localhost:3306/shiro?characterEncoding=UTF-8&useUnicode=true");
	        ds.setUsername("root");
	        ds.setPassword("root");

	        return new JdbcTemplate(ds);
	    }
}
