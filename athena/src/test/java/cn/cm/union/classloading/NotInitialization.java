package cn.cm.union.classloading;

import cn.cm.union.DeadLoopClass;

public class NotInitialization {

  static {
    System.out.println(Thread.currentThread()+"NotInitialzation init");
  }

  /*public static void main(String[] args) {
    //System.out.println(SubClass.value);
    //SuperClass[] superClasses = new SuperClass[10];
    System.out.println(ConstantClass.HELLOWORLD);
  }*/
  public static void main(String[] args) {
    Runnable runnable = new Runnable() {
      public void run() {
        System.out.println(Thread.currentThread() + "start");
        DeadLoopClass dlc = new DeadLoopClass();
        System.out.println(Thread.currentThread() + "run over");
      }
    };
    Thread thread1 = new Thread(runnable);
    Thread thread2 = new Thread(runnable);
    thread1.start();
    thread2.start();

  }
}
