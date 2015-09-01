/** 
 * @file     CoureController.java 
 * @brief    springmvc's file 
 * @author   许立亢 
 * @date     2015年8月26日
 * @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
 */

package com.github.star45.shiro.chapter2.imooc.controlloer;

import java.util.Map;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.star45.shiro.chapter2.imooc.model.Course;
import com.github.star45.shiro.chapter2.imooc.service.CourseService;

/**
 * @brief 类简短说明
 * @details 详细说明
 * @warning 注意事项
 * @date 2015年8月26日
 * @author 许立亢
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */
@Controller
@RequestMapping("/courses")
public class CoureController {

	private static final Logger log = Logger.getLogger(CoureController.class);

	@Autowired
	private CourseService courseService;

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String viewCoure(@RequestParam("courseId") Integer courseId,
			Model model) {

		log.info("传递的CoureId为   ： " + courseId);

		Course course = courseService.getCoursebyId(courseId);
		model.addAttribute(course);

		return "imooc/course_overview";
	}

	// 本方法将处理 /courses/view2/123 形式的URL
	@RequestMapping(value = "/view2/{courseId}", method = RequestMethod.GET)
	public String view2(@PathVariable("courseId") Integer courseId,
			Map<String, Object> model) {
		log.info("传递的CoureId为   ： " + courseId);

		Course course = courseService.getCoursebyId(courseId);
		model.put("course", course);

		return "imooc/course_overview";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET, params = "add")
	public String createCourse() {

		return "imooc/course_admin/edit";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String dosave(@ModelAttribute Course course) {

		log.info("课程信息  ：");
		log.info(ReflectionToStringBuilder.toString(course));

		course.setCourseId(123);
		return "redirect:view2/" + course.getCourseId();
	}
	
	@RequestMapping(value = "/show",method = RequestMethod.GET)
	public String show(Integer courseId,Model model){
		model.addAttribute("courseId",123);
		return "imooc/course_json";
	}
	
	@RequestMapping(value = "/view3/{courseId}",method = RequestMethod.GET)
	public @ResponseBody Course getCourse4Json(@PathVariable Integer courseId){
		log.info("查询的courseId为：  "+courseId);
		return courseService.getCoursebyId(courseId);
	}
	
	
	
	/**
	 * @param courseService
	 *            要设置的 courseService
	 */

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

}
