/*
Copyright 2018 Focus Technology, Co., Ltd. All rights reserved.
 */
package cn.cm.union;

import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.springframework.stereotype.Component;

/**
 * @author zhuyingye
 * @date 2019-03-25
 */
@Component
public class Commponent {

	public Commponent() {
		System.out.println("init Commponent");
	}

	public void test(Long orderId, List<Long> appIds) {
		System.out.println("orderId:" + orderId);
		System.out.println("appIds:");
		appIds.stream().forEach(System.out::println);
	}
}
