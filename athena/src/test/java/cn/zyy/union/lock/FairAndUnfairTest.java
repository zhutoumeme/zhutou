package cn.zyy.union.lock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairAndUnfairTest {

  public static void main(String[] args) {
    FairAndUnfairTest fairAndUnfairTest = new FairAndUnfairTest();
    //fairAndUnfairTest.fair();
    fairAndUnfairTest.unfair();
  }

  private static Lock unfairLock = new ReentrantLock2(false);
  private static Lock fairLock = new ReentrantLock2(true);
  private static CountDownLatch latch;

  public void fair() {
    testLock(fairLock);
  }

  public void unfair() {
    testLock(unfairLock);
  }

  private void testLock(Lock lock) {
    latch = new CountDownLatch(1);
    for (int i = 0; i < 5; i++) {
      Thread thread = new Thread(new Job(lock));
      thread.setName("" + i);
      thread.start();
    }
    latch.countDown();
  }

  private static class Job extends Thread {

    private Lock lock;

    public Job(Lock lock) {
      this.lock = lock;
    }

    @Override
    public void run() {

      try {
        latch.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      for (int i = 0; i < 2; i++) {
        lock.lock();
        try {
          System.out.println("Lock by [" + getName() + "],Waiting by " + ((ReentrantLock2) lock)
              .getQueuedThreads());
        } finally {
          lock.unlock();
        }
      }
    }
  }

  private static class ReentrantLock2 extends ReentrantLock {

    public ReentrantLock2(boolean fair) {
      super(fair);
    }

    @Override
    protected Collection<Thread> getQueuedThreads() {
      List<Thread> list = new ArrayList<>(super.getQueuedThreads());
      Collections.reverse(list);
      return list;
    }
  }
}
