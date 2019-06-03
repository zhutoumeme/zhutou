/*
Copyright 2018 Focus Technology, Co., Ltd. All rights reserved.
 */
package cn.zyy.union.jdbc;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhuyingye
 * @date 2019-06-03
 */
public class ShiroTest {
	private static final transient Logger	log	= LoggerFactory.getLogger(ShiroTest.class);

	public static void main(String[] args) {
		log.info("My First Apache Shiro Application");

		// 1.
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");

		// 2.
		SecurityManager securityManager = factory.getInstance();

		// 3.
		SecurityUtils.setSecurityManager(securityManager);

		System.exit(0);
	}

}
