package cn.zyy.union;

import cn.zyy.union.pojo.LoginInfo;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestMybatis {

  Logger logger = LoggerFactory.getLogger(this.getClass());

  @Resource
  LoginInfoService loginInfoService = null;

  @Test
  public void test() {
    LoginInfo loginInfo = loginInfoService.queryLoginInfoByLoginId(1l);
    logger.info("loginId={}", loginInfo.getLoginId());
  }
}
