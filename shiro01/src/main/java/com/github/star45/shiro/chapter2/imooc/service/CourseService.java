/** 
* @file     CoureService.java 
* @brief    springmvc's file 
* @author   许立亢 
* @date     2015年8月26日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter2.imooc.service;

import com.github.star45.shiro.chapter2.imooc.model.Course;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月26日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public interface CourseService {
	
	Course getCoursebyId(Integer courseId);
}
