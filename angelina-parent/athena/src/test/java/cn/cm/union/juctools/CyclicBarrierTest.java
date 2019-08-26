package cn.cm.union.juctools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierTest {

  private final static CyclicBarrier CYCLIC_BARRIER = new CyclicBarrier(2, new ATest());

  public static void main(String[] args) {
    Thread thread = new Thread(() -> {
      try {
        CYCLIC_BARRIER.await();
      } catch (InterruptedException | BrokenBarrierException e) {
        e.printStackTrace();
      }
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(2);
    }, "Thread1");
    thread.start();
    System.out.println(CYCLIC_BARRIER.isBroken());
    try {
      CYCLIC_BARRIER.await();
    } catch (InterruptedException | BrokenBarrierException e) {
      e.printStackTrace();
    }
    System.out.println(3);
  }

  static class ATest implements Runnable {

    @Override
    public void run() {
      System.out.println(1);
    }
  }
}
