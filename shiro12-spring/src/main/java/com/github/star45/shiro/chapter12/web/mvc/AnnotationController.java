/** 
* @file     AnnotationController.java 
* @brief    shiro12-spring's file 
* @author   许立亢 
* @date     2015年9月15日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter12.web.mvc;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月15日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */
@Controller
public class AnnotationController {
	 @RequestMapping("/hello1")
	    public String hello1() {
	        SecurityUtils.getSubject().checkRole("admin");
	        return "success";
	    }

	    @RequiresRoles("admin")
	    @RequestMapping("/hello2")
	    public String hello2() {
	        return "success";
	    }
}
