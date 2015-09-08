/** 
* @file     PasswordHelper.java 
* @brief    shiro06-realm's file 
* @author   许立亢 
* @date     2015年9月7日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter6.utils;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.github.star45.shiro.chapter6.entity.User;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月7日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class PasswordHelper {
	
	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	
	 private String algorithmName = "md5";  
	 private final int hashIterations = 2;  
	 public void encryptPassword(User user) {  
		 
		 user.setSalt(randomNumberGenerator.nextBytes().toHex());
		 
		 String newPassword = new SimpleHash(
				 algorithmName, 
				 user.getPassword(), 
				 ByteSource.Util.bytes(user.getCredentialsSalt()), 
				 hashIterations
				 ).toHex();
		 user.setPassword(newPassword);
	 }
	
	

}
