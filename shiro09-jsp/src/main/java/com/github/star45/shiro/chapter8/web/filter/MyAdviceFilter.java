/** 
* @file     MyAdviceFilter.java 
* @brief    shiro08-filter's file 
* @author   许立亢 
* @date     2015年9月8日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter8.web.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.servlet.AdviceFilter;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月8日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class MyAdviceFilter extends AdviceFilter {
	@Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("====预处理/前置处理");
        return true;//返回false将中断后续拦截器链的执行
    }
    @Override
    protected void postHandle(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("====后处理/后置返回处理");
    }
    @Override
    public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception) throws Exception {
        System.out.println("====完成处理/后置最终处理");
    }

}
