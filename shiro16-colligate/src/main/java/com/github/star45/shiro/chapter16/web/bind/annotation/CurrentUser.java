/** 
* @file     CurrentUser.java 
* @brief    shiro16-colligate's file 
* @author   许立亢 
* @date     2015年9月16日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter16.web.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.star45.shiro.chapter16.web.Constants;

/**
 * @brief  * <p>绑定当前登录的用户</p>
 * <p>不同于@ModelAttribute</p>
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月16日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

    /**
     * 当前用户在request中的名字
     *
     * @return
     */
    String value() default Constants.CURRENT_USER;

}
