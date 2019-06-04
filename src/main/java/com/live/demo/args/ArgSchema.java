package com.live.demo.args;

import lombok.Data;

import java.util.function.Predicate;

/** 与定义的合法  predicate 判断传入的value 类型是否恰当
 * @Author zhouyuhao
 * @Date 2019/6/4 12:49 PM
 */
@Data
public class ArgSchema<T> {
  private String flag;

  private T defaultValue;

  private Predicate<T> predicate;
  public ArgSchema(String flag, T defaultValue,Predicate<T> predicate) {
    this.flag = flag;
    this.defaultValue = defaultValue;
    this.predicate = predicate;
  }

  public ArgSchema() {
  }
}
