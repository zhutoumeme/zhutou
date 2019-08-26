package cn.cm.union.juctools;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

  private static final int TOTAL_THREAD_COUNT = 30;

  private static ExecutorService threadPool = Executors.newFixedThreadPool(TOTAL_THREAD_COUNT);
  private static Semaphore semaphore = new Semaphore(10);
  private static CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

  public static void main(String[] args) {
    System.out.println(Runtime.getRuntime().availableProcessors());
  }
}
