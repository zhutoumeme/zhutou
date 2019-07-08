package cn.cm.union.thread;

import java.util.concurrent.TimeUnit;

public class Join {

  public static void main(String[] args) throws InterruptedException {
    Thread preThread = Thread.currentThread();
    for (int i = 0; i < 10; i++) {
      Thread demoThread = new Thread(new Demo(preThread), String.valueOf(i));
      demoThread.start();
      preThread = demoThread;
    }
    TimeUnit.SECONDS.sleep(5);
    System.out.println(Thread.currentThread().getName() + "terminate...");
  }

  static class Demo implements Runnable {

    private Thread thread;

    public Demo(Thread thread) {
      this.thread = thread;
    }

    @Override
    public void run() {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread().getName() + "terminate.");
    }
  }
}
