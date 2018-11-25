package cn.zyy.union.utils;

public class Factorial {

  public static int getFactorialResult(int number) {
    int sum = 0;
    int factorialNum = 1;
    if (number > 0) {
      for (int i = 1; i <= number; i++) {
        factorialNum = factorialNum * i;
        sum += factorialNum;
      }
    }
    return sum;
  }

  public static void main(String[] args) {
    int result = getFactorialResult(3);
    System.out.println(result);
  }

}
