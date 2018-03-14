package cn.zyy.union.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

  @RequestMapping("/info")
  public String getuserInfo() {
    return "index";
  }

}
