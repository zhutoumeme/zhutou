package cn.cm.union.jmm;

public class FinalExample {

  public static void main(String[] args) {
    FinalExample finalExample = new FinalExample();
    Thread a = new Thread(()-> finalExample.write());
    Thread b = new Thread(()->finalExample.read());
    a.start();
    b.start();
  };
  int i;
  final int j;
  static FinalExample obj;

  public FinalExample() {
    i = 1;
    j = 1;
  }

  public void write() {
    obj = new FinalExample();
  }

  public void read() {
    FinalExample object = obj;
    int a = object.i;
    int b = object.j;
    System.out.println("a:"+a);
    System.out.println("b:"+b);
  }

}
