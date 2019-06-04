package com.live.demo.args;

import lombok.Data;

/** 真实的传入arg，需要被校验合法性
 * @Author zhouyuhao
 * @Date 2019/6/4 1:44 PM
 */
@Data
public class Arg<T> {
  private String flag;
  private T value;

  public Arg(String flag, T value) {
    this.flag = flag;
    this.value = value;
  }

  public Arg() {
  }
}
