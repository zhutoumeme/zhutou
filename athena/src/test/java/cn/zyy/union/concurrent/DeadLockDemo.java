package cn.zyy.union.concurrent;

public class DeadLockDemo {

  private static final String a = "a";
  private static final String b = "b";

  public static void main(String[] args) {
    deadLock();
  }
  private static void deadLock(){
    Thread threadA = new Thread(()->{
      synchronized (a){
        System.out.println(a);
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        synchronized (b){
          System.out.println(b);
        }
      }
    });
    Thread threadB = new Thread(()->{
      synchronized (b){
        System.out.println(" threadB :"+b);
        synchronized (a){
          System.out.println("threadB:"+a);
        }
      }
    });
    threadA.start();
    threadB.start();
  }
}
