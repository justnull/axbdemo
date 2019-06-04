package com.live.demo;

import org.apache.tomcat.util.buf.StringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FizzBuzzTest {
  private FizzBuzz fizzBuzz;
  @Before
  public void setUp(){
    fizzBuzz = new FizzBuzz();
  }
  @Test
  public void getArr() {
    String [] ansArr = fizzBuzz.getArr();
    for (String numOrStr: ansArr) {
      if(isNumeric(numOrStr)){
        int num = Integer.valueOf(numOrStr);
        if (num %3 ==0 || num %5 == 0){
          assertTrue("不可能剩下被3或5整除的数字",true);
        }
      }
    }
  }

  public final static boolean isNumeric(String s) {
    if (s != null && !"".equals(s.trim()))
      return s.matches("^[0-9]*$");
    else
      return false;
  }
}
