package cn.zyy.union;

import java.math.BigDecimal;

public class EqualTest {

  public static void main(String[] args) {
    double s = 333.22;
    System.out.println(new BigDecimal(s));
    System.out.println(BigDecimal.valueOf(s));
    System.out.println(new BigDecimal(Double.toString(s)));
  }
}
