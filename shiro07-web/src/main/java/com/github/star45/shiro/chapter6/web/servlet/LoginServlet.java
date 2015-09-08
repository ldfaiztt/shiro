/** 
* @file     LoginServlet.java 
* @brief    shiro07-web's file 
* @author   许立亢 
* @date     2015年9月7日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter6.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月7日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */
@SuppressWarnings("serial")
@WebServlet(name="loginServlet",urlPatterns="/login")
public class LoginServlet extends HttpServlet{
	 @Override  
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  
	      throws ServletException, IOException {  
	        req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);  
	    }  
	    @Override  
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  
	      throws ServletException, IOException {  
	        String error = null;  
	        String username = req.getParameter("username");  
	        String password = req.getParameter("password");  
	        Subject subject = SecurityUtils.getSubject();  
	        UsernamePasswordToken token = new UsernamePasswordToken(username, password);  
	        try {  
	            subject.login(token);  
	        } catch (UnknownAccountException e) {  
	            error = "用户名/密码错误";  
	        } catch (IncorrectCredentialsException e) {  
	            error = "用户名/密码错误";  
	        } catch (AuthenticationException e) {  
	            //其他错误，比如锁定，如果想单独处理请单独catch处理  
	            error = "其他错误：" + e.getMessage();  
	        }  
	        if(error != null) {//出错了，返回登录页面  
	            req.setAttribute("error", error);  
	            req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);  
	        } else {//登录成功  
	            req.getRequestDispatcher("/WEB-INF/view/loginSuccess.jsp").forward(req, resp);  
	        }  
	    }  
}
