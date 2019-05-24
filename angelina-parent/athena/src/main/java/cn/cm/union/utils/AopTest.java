package cn.cm.union.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {

  public static void main(String[] args) {
    ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
    User user = (User) ac.getBean("userInfo");
    try {
      user.save();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(user.getClass());
  }
}
