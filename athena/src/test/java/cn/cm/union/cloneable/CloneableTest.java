package cn.cm.union.cloneable;

public class CloneableTest implements Cloneable {

  public Test         data1 = null;
  public double       data2 = 0;
  public String       data3 = null;
  public StringBuffer data4 = null;

  public CloneableTest(Test data1, double data2, String data3, StringBuffer data4) {
    this.data1 = data1;
    this.data2 = data2;
    this.data3 = data3;
    this.data4 = data4;
  }// constructor

  /**
   * 用于显示对象中各字段的值
   */
  public void show() {
    System.out.println("data1 = " + data1.userData);
    System.out.println("data2 = " + data2);
    System.out.println("data3 = " + data3);
    System.out.println("data4 = " + data4);
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
}
