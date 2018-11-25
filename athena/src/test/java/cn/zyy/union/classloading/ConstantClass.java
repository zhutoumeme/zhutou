package cn.zyy.union.classloading;

public class ConstantClass {

  static {
    System.out.println("ConstantClass init!");
  }

  public static final String HELLOWORLD = "hello world";
}
