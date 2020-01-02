package cn.zyy.union.user.controller;

import org.apache.coyote.OutputBuffer;
import org.springframework.context.ApplicationEvent;

/**
 * @program: union
 * @description: ${description}
 * @author: ${author}
 * @create: 2020-01-03 00:02
 **/
public class RegisterEvent extends ApplicationEvent {

  /**
   * 消息
   */
  private String message;

  /**
   * Create a new ApplicationEvent.
   *
   * @param source the object on which the event initially occurred (never {@code null})
   */
  public RegisterEvent(Object source, String message) {
    super(source);
    this.setMessage(message);
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void register(){
    //1.
    insertUser();
    //2.
    insertUserPoint();
    //3.
    sendSms();
    //4.
    senEmail();
  }

  public void insertUser(){

  }
  public void insertUserPoint(){

  }
  public void sendSms(){

  }
  public void senEmail(){

  }
}