package cn.zyy.union.proxy;

public class BoxingTest {

  public static void main(String[] args) {
    if (true) {
      System.out.println("block 1");
    } else {
      System.out.println("block 2");
    }
  }

  public String contactString(String s1, String s2, String s3) {
    return s1 + s2 + s3;
  }
}
