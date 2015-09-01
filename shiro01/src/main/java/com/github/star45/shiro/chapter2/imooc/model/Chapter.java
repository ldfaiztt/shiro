/** 
* @file     Chapter.java 
* @brief    springmvc's file 
* @author   许立亢 
* @date     2015年8月26日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter2.imooc.model;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月26日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class Chapter {

	private Integer id;
	private Integer courseId;
	private Integer order;
	private String title;
    private String descr;
	/** 
	 * @return id 
	 */
	
	public Integer getId() {
		return id;
	}
	/** 
	 * @param id 要设置的 id 
	 */
	
	public void setId(Integer id) {
		this.id = id;
	}
	/** 
	 * @return courseId 
	 */
	
	public Integer getCourseId() {
		return courseId;
	}
	/** 
	 * @param courseId 要设置的 courseId 
	 */
	
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	/** 
	 * @return order 
	 */
	
	public Integer getOrder() {
		return order;
	}
	/** 
	 * @param order 要设置的 order 
	 */
	
	public void setOrder(Integer order) {
		this.order = order;
	}
	/** 
	 * @return title 
	 */
	
	public String getTitle() {
		return title;
	}
	/** 
	 * @param title 要设置的 title 
	 */
	
	public void setTitle(String title) {
		this.title = title;
	}
	/** 
	 * @return descr 
	 */
	
	public String getDescr() {
		return descr;
	}
	/** 
	 * @param descr 要设置的 descr 
	 */
	
	public void setDescr(String descr) {
		this.descr = descr;
	}
    
}
