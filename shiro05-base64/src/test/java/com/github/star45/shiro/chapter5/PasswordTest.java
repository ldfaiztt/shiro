/** 
* @file     PasswordTest.java 
* @brief    shiro05-base64's file 
* @author   许立亢 
* @date     2015年9月2日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter5;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月2日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class PasswordTest extends BaseTest {
	@Test
    public void testPasswordServiceWithMyRealm() {
        login("classpath:shiro-passwordservice.ini", "star45$", "admin");
        
        Subject subject = subject();
        subject.getPrincipals();
    }

    @Test
    public void testPasswordServiceWithJdbcRealm() {
        login("classpath:shiro-jdbc-passwordservice.ini", "star45$", "admin");
    }
    /**
     * 
     * @brief 产生一个随机数+用户名作为盐值，生成密码
     * @details 将这个随机数记录到数据库中
     * @warning 注意事项
     * @date 2015年9月2日 下午5:14:21
     */
    @Test
    public void testGeneratePassword() {
        String algorithmName = "md5";
        String username = "xulikang$";
        String password = "admin";
        String salt1 = username;
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
//        String salt2 ="4f5c96845b106781057cbbb18008345a";//ed622a59b4e81be40784f130ab533a37
        int hashIterations = 2;
        //此处我们使用MD5算法，“密码+盐（用户名+随机数）”的方式生成散列值：
        SimpleHash hash = new SimpleHash(algorithmName, password, salt1 + salt2, hashIterations);
        String encodedPassword = hash.toHex();
        System.out.println(salt2);
        System.out.println(encodedPassword);
    }

    @Test
    public void testHashedCredentialsMatcherWithMyRealm2() {
        //使用testGeneratePassword生成的散列密码
        login("classpath:shiro-hashedCredentialsMatcher.ini", "xulikang$", "admin");
    }

    @Test
    public void testHashedCredentialsMatcherWithJdbcRealm() {

        BeanUtilsBean.getInstance().getConvertUtils().register(new EnumConverter(), JdbcRealm.SaltStyle.class);

        //使用testGeneratePassword生成的散列密码
        login("classpath:shiro-jdbc-hashedCredentialsMatcher.ini", "xulikang$", "admin");
    }


    private class EnumConverter extends AbstractConverter {
        @Override
        protected String convertToString(final Object value) throws Throwable {
            return ((Enum) value).name();
        }
        @Override
        protected Object convertToType(final Class type, final Object value) throws Throwable {
            return Enum.valueOf(type, value.toString());
        }

        @Override
        protected Class getDefaultType() {
            return null;
        }

    }

    @Test(expected = ExcessiveAttemptsException.class)
    public void testRetryLimitHashedCredentialsMatcherWithMyRealm() {
        for(int i = 1; i <= 6; i++) {
            try {
            	
                login("classpath:shiro-retryLimitHashedCredentialsMatcher.ini", "xulikang", "admin");
            } catch (Exception e) {/*ignore*/}
        }
        
        login("classpath:shiro-retryLimitHashedCredentialsMatcher.ini", "xulikang$", "admin");
    }
}
