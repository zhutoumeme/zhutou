package cn.zyy.union.thread;

import java.util.concurrent.CountDownLatch;

public class ThreadTest {

  private static int a = 2;

  public static void main(String[] args) throws Throwable {
    final CountDownLatch countDownLatch = new CountDownLatch(2);

    Thread aa = new Thread(new Runnable() {
      public void run() {
        a--;
        System.out.println("aa:"+a);
        try {
          countDownLatch.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    Thread bb = new Thread(new Runnable() {
      public void run() {
        a--;
        System.out.println("bb:"+a);
        try {
          countDownLatch.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    aa.start();
    bb.start();
    countDownLatch.countDown();
    System.out.println("main:"+a);
    ThreadTest threadTest = new ThreadTest();
    threadTest.finalize();
  }

}
