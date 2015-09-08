/** 
* @file     MyPathMatchingFilter.java 
* @brief    shiro08-filter's file 
* @author   许立亢 
* @date     2015年9月8日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter8.web.filter;

import java.util.Arrays;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.PathMatchingFilter;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月8日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class MyPathMatchingFilter extends PathMatchingFilter {
	@Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        System.out.println("url matches,config is " + Arrays.toString((String[])mappedValue));
        return true;
    }
}
