package cn.zyy.union;

import cn.zyy.union.dao.LoginInfoMapper;
import cn.zyy.union.pojo.LoginInfo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginInfoService {

  @Resource
  LoginInfoMapper loginInfoMapper;

  public LoginInfo queryLoginInfoByLoginId(Long loginId) {
    if (null == loginId) {
      return null;
    }
    return loginInfoMapper.selectByPrimaryKey(loginId.intValue());
  }
}
