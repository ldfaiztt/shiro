/** 
* @file     HelloController.java 
* @brief    springmvc's file 
* @author   许立亢 
* @date     2015年8月26日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter2.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月26日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */
//告诉DispatcherServlet相关的容器， 这是一个Controller， 管理好这个bean哦
@Controller
//类级别的RequestMapping，告诉DispatcherServlet由这个类负责处理以跟URL。
//HandlerMapping依靠这个标签来工作
@RequestMapping("/hello")
public class HelloController {
	
	private static final Logger log = Logger.getLogger(HelloController.class);
	
	//方法级别的RequestMapping， 限制并缩小了URL路径匹配，同类级别的标签协同工作，最终确定拦截到的URL由那个方法处理
	@RequestMapping("/mvc")
	public String helloMVC(){
		log.info("访问home页面...");
		//视图渲染，/WEB-INF/view/home.jsp
		return "home";
	}
	
	
}
