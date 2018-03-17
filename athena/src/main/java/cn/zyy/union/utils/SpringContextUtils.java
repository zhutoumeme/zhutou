package cn.zyy.union.utils;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtils implements ApplicationContextAware {

  private static ApplicationContext context;

  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    context = applicationContext;
  }

  public static ApplicationContext getContext() {
    if (ObjectUtils.NULL == context) {
      throw new IllegalStateException(
          "applicaitonContext未注入,请在applicationContext.xml中定义SpringContextUtil");
    }
    return context;
  }

  public static <T> T getBean(String name) {
    return (T) context.getBean(name);
  }
}
