package cn.zyy.union.utils.producter;

import java.util.concurrent.ArrayBlockingQueue;

public class Customer implements Runnable {

  private ArrayBlockingQueue queue;

  public Customer(ArrayBlockingQueue q) {
    this.queue = q;
  }

  public void run() {
    while (true) {
      try {
        Object r = queue.take();
        System.out.println("消费者资源队列大小。。。=" + queue.size());

      } catch (InterruptedException e) {
        System.out.println("消费者 中断");
      }
    }
  }

  private void take(Object o) {
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      System.out.println("消费者 读 中断");
    }
    System.out.println("消费对象：" + o);
  }
}
