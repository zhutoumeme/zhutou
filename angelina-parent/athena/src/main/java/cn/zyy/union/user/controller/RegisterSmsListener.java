package cn.zyy.union.user.controller;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @program: union
 * @description: ${description}
 * @author: ${author}
 * @create: 2020-01-03 00:07
 **/
@Component
public class RegisterSmsListener implements ApplicationListener<RegisterEvent> {

  @Override
  public void onApplicationEvent(RegisterEvent event) {

    /**
     * 获取发布者的消息
     */
    System.out.println(event.getMessage());
  }
}