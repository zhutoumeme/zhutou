package cn.zyy.union.user.controller;

import cn.zyy.union.user.DTO.LoginDTO;
import cn.zyy.union.utils.DTO.ResponseDTO;
import java.util.Arrays;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: union
 * @description: 登录
 * @author: 朱营业
 * @create: 2019-10l-13 22:47
 **/
@RestController
@RequestMapping(value = "login")
public class LoginController {

  /**
   * 登录
   */
  @RequestMapping(value = "/login")
  public ResponseDTO login(@RequestBody LoginDTO login) {

    if (ObjectUtils.isEmpty(login)) {
      return new ResponseDTO(ResponseDTO.ERROR, Arrays.asList("参数错误"));
    }
    return new ResponseDTO(ResponseDTO.OK);
  }

  @RequestMapping("/unauthorized")
  public ResponseDTO unauthorized() {
    return new ResponseDTO(ResponseDTO.ERROR, Arrays.asList("未授权"));
  }

  @RequestMapping("/out")
  public ResponseDTO out() {
    return new ResponseDTO(ResponseDTO.ERROR, Arrays.asList("用户未登录"));
  }

}