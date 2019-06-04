package com.live.demo;

/**
 * @Author zhouyuhao
 * @Date 2019/5/31
 */
public class FizzBuzz {
  /**
   *  打印所有对应数字或字母
   * @param arr
   */
    public void printArr (String [] arr){
      for (String num: arr) {
        System.out.println(num);
      }
    }

    public String [] getArr(){
      String [] arr = new String[100];
      for (int i = 1;i<101;i++){
        if (i % 3 ==0 && i % 5 ==0){
          arr[i - 1] = "FizzBuzz";
        }else if(i % 3 ==0){
          arr[i - 1] = "Fizz";
        }else if(i%5==0){
          arr[i - 1] = "Buzz";
        }else {
          arr[i - 1] = i+"";
        }
      }
      return arr;
    }

    public void printNumOrStr(){
      String [] arr = getArr();
      printArr(arr);
    }

}
