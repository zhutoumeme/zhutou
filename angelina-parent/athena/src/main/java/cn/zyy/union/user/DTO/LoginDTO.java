package cn.zyy.union.user.DTO;

import java.io.Serializable;

/**
 * 登录实体
 */
public class LoginDTO implements Serializable {

  private static final long serialVersionUID = 6744798557785315841L;

  private String loginName;
  private String password;

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "LoginDTO{" +
        "loginName='" + loginName + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
