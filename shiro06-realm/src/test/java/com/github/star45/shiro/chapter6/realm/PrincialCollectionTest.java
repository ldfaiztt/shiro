/** 
* @file     PrincialCollectionTest.java 
* @brief    shiro06-realm's file 
* @author   许立亢 
* @date     2015年9月7日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter6.realm;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import com.github.star45.shiro.chapter6.BaseTest;
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

public class PrincialCollectionTest extends BaseTest{
	 @Test
	    public void test() {

	        //因为Realm里没有进行验证，所以相当于每个Realm都身份验证成功了
	        login("classpath:shiro-multirealm.ini", "star45", "admin");
	        Subject subject = subject();
	        //获取Primary Principal（即第一个）
	        Object primaryPrincipal1 = subject.getPrincipal();
	        PrincipalCollection princialCollection = subject.getPrincipals();
	        Object primaryPrincipal2 = princialCollection.getPrimaryPrincipal();

	        //但是因为多个Realm都返回了Principal，所以此处到底是哪个是不确定的
	        Assert.assertEquals(primaryPrincipal1, primaryPrincipal2);


	        //返回 a b c
	        Set<String> realmNames = princialCollection.getRealmNames();
	        System.out.println(realmNames);

	        //因为MyRealm1和MyRealm2返回的凭据都是star45，所以排重了
	        Set<Object> principals = princialCollection.asSet(); //asList和asSet的结果一样
	        System.out.println(principals);

	        //根据Realm名字获取
	        Collection<User> users = princialCollection.fromRealm("c");
	        System.out.println(users);
	    }

}
