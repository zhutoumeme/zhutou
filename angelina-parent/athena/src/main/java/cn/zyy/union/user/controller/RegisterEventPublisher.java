package cn.zyy.union.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @program: union
 * @description: ${description}
 * @author: ${author}
 * @create: 2020-01-03 00:04
 **/
@Component
public class RegisterEventPublisher {

  @Autowired
  private ApplicationEventPublisher applicationEventPublisher;

  /**
   * 发布
   */
  public void publish(String message) {
    RegisterEvent event = new RegisterEvent(this, message);
    applicationEventPublisher.publishEvent(event);
  }

}