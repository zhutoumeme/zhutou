package cn.cm.union.thread;

public class YeildThread {

  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread(new MyThread());
    thread.start();
    Thread.sleep(1000);
    while (true) {
      System.out.println("主线程");
    }
  }

  static class MyThread implements Runnable {

    public void run() {
      while (true){
        System.out.println("被放弃线程");
        Thread.yield();
      }
    }
  }

}
