package cn.cm.union.utils.producter;

import java.util.concurrent.ArrayBlockingQueue;

public class Producter implements Runnable {

  private ArrayBlockingQueue queue;

  public Producter(ArrayBlockingQueue q) {
    this.queue = q;
  }

  public void run() {
    try {
      while (true) {
        getResouce();
        queue.put(new Object());
        System.out.println("生产资源队列大小。。。=" + queue.size());
      }
    } catch (InterruptedException e) {
      System.out.println("生产者中断");
    }
  }

  private void getResouce() {
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
