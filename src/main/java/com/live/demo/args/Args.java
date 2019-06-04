package com.live.demo.args;

import lombok.Data;

import java.util.Map;

/**
 * 创建Args时候由那些key (ArgSchema中确定)已经确定，输出不存在的key
 * @Author zhouyuhao
 * @Date 2019/6/4
 */
@Data
public class Args {
  private  Map<String, Arg> argMap;

  public Args(Map<String, Arg> argMap) {
    this.argMap = argMap;
  }

  public Args() {
  }
}
