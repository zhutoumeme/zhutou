package cn.cm.union.juctools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

  private static final int TOTAL_THREAD_COUNT = 30;

  private static ExecutorService threadPool = Executors.newFixedThreadPool(TOTAL_THREAD_COUNT);
  private static Semaphore semaphore = new Semaphore(10);

  public static void main(String[] args) {
    for (int i = 0; i < TOTAL_THREAD_COUNT; i++) {
      threadPool.execute(() -> {
        try {
          semaphore.acquire();
          System.out.println("save data");
          semaphore.release();
          System.out.println(semaphore.availablePermits());
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
    }
    threadPool.shutdown();
  }
}
