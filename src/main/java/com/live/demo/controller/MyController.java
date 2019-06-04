package com.live.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhouyuhao
 * @Date 2019/5/30
 */
@RestController
public class MyController {
  @RequestMapping(path = "/")
  public String index() {
    String a = "";
    return "Hello world,I'm writin Hello code now!";
  }

  public static void main(String[] args) {

  }
}
