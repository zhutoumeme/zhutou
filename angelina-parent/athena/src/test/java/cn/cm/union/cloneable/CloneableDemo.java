package cn.cm.union.cloneable;

public class CloneableDemo {

  public static void main(String[] args) throws CloneNotSupportedException {
    Test test1 = new Test("original data");
    StringBuffer strBuf = new StringBuffer("origin data");

    CloneableTest org = new CloneableTest(test1, 1.0, "original", strBuf);
    CloneableTest copy = null;
    Object objTemp = org.clone();
    if (objTemp instanceof CloneableTest) {
      copy = (CloneableTest)objTemp;
    }// if

    System.out.println("copy == original? " + (copy == org));
    System.out.println();
    System.out.println("data of original:");
    org.show();
    System.out.println();
    System.out.println("data of copy:");
    copy.show();

    System.out.println();
    System.out.println("org.data1 == copy.data1? " + (org.data1 == copy.data1));
    System.out.println("org.data1 equals copy.data1? " + (org.data1.equals(copy.data1) ));
    System.out.println("org.data2 == copy.data2? " + (org.data2 == copy.data2));
    System.out.println("org.data3 == copy.data3? " + (org.data3 == copy.data3));
    System.out.println("org.data4 == copy.data4? " + (org.data4 == copy.data4));

  }
}
