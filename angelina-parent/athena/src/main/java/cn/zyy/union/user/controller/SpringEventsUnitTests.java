package cn.zyy.union.user.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @program: union
 * @description: ${description}
 * @author: ${author}
 * @create: 2020-01-03 00:10
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext.xml"})
public class SpringEventsUnitTests {

  @Autowired
  private RegisterEventPublisher registerEventPublisher;

  @Test
  public void test1() {
    registerEventPublisher.publish("我已经注册成功，可以发短信啦");
  }

}