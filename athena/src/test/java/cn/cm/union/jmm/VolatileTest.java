package cn.cm.union.jmm;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VolatileTest {

  public static void increase() {
    lock.lock();
    System.out.println(Thread.currentThread().getName() + "获取锁");
    try {
      race++;
    } catch (Exception e) {
    } finally {
      lock.unlock();
      System.out.println(Thread.currentThread().getName() + "释放锁");
    }
  }

  public static volatile int race;
  private static Lock lock = new ReentrantLock();
  private static final int THREAD_COUNT = 20;
  private final static CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

  public static void main(String[] args) throws InterruptedException {
    long beginTime = System.currentTimeMillis();
    Thread[] threads = new Thread[THREAD_COUNT];
    for (int i = 0; i < threads.length; i++) {
      threads[i] = new Thread(() -> {
        for (int j = 0; j < 1000; j++) {
          increase();
        }
        latch.countDown();
      }, String.valueOf(i));
      threads[i].start();

    }
    latch.await();
    System.out.println("result:" + race);
    System.out.println("endTime:" + (System.currentTimeMillis() - beginTime));
  }
}
