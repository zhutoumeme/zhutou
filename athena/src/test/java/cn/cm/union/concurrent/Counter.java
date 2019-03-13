package cn.cm.union.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

  private static AtomicInteger atomicInteger = new AtomicInteger();
  private static int i;

  public static void main(String[] args) throws InterruptedException {
    List<Thread> threadList = new ArrayList<>(600);
    long start = System.currentTimeMillis();
    for (int i = 0; i < 100; i++) {
      Thread thread = new Thread(() -> {
        for (int j = 0; j < 10000; j++) {
          safeCount();
          count();
        }
      });
      threadList.add(thread);
    }
    threadList.forEach(thread -> thread.start());
    for (Thread thread : threadList) {
      thread.join();
    }
    System.out.println(i);
    System.out.println(atomicInteger.get());
    System.out.println(System.currentTimeMillis() - start);
  }

  private static void safeCount() {
    for (; ; ) {
      int i = atomicInteger.get();
      boolean suc = atomicInteger.compareAndSet(i, ++i);
      if (suc) {
        break;
      }
    }
  }

  private static void count() {
    i++;
  }

}
