/** 
* @file     Coure.java 
* @brief    springmvc's file 
* @author   许立亢 
* @date     2015年8月26日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter2.imooc.model;

import java.util.List;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月26日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class Course {
	// 课程Id
	private Integer courseId;
	// 课程名称
	private String title;
	// 图片路径
	private String imgPath;
	// 学习人数
	private Integer learningNum;
	// 课程时长
	private Long duration;
	// 课程难度
	private Integer level;
	// 课程难度描述
	private String levelDesc;
	// 课程介绍
	private String descr;
	// 课程提纲
	private List<Chapter> chapterList;
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
	 * @return imgPath 
	 */
	
	public String getImgPath() {
		return imgPath;
	}
	/** 
	 * @param imgPath 要设置的 imgPath 
	 */
	
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	/** 
	 * @return learningNum 
	 */
	
	public Integer getLearningNum() {
		return learningNum;
	}
	/** 
	 * @param learningNum 要设置的 learningNum 
	 */
	
	public void setLearningNum(Integer learningNum) {
		this.learningNum = learningNum;
	}
	/** 
	 * @return duration 
	 */
	
	public Long getDuration() {
		return duration;
	}
	/** 
	 * @param duration 要设置的 duration 
	 */
	
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	/** 
	 * @return level 
	 */
	
	public Integer getLevel() {
		return level;
	}
	/** 
	 * @param level 要设置的 level 
	 */
	
	public void setLevel(Integer level) {
		this.level = level;
	}
	/** 
	 * @return levelDesc 
	 */
	
	public String getLevelDesc() {
		return levelDesc;
	}
	/** 
	 * @param levelDesc 要设置的 levelDesc 
	 */
	
	public void setLevelDesc(String levelDesc) {
		this.levelDesc = levelDesc;
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
	/** 
	 * @return chapterList 
	 */
	
	public List<Chapter> getChapterList() {
		return chapterList;
	}
	/** 
	 * @param chapterList 要设置的 chapterList 
	 */
	
	public void setChapterList(List<Chapter> chapterList) {
		this.chapterList = chapterList;
	}
	
	
	
}
