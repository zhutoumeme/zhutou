package cn.cm.union.thread;

import java.util.HashMap;
import java.util.UUID;

public class ConcurrentPutHashMap {

  static final HashMap<String, String> map = new HashMap<String, String>(2);

  /**
   * 并发修改抛异常
   *
   * @seejava.util.HashMap.HashIterator#nextNode()
   */
  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread(new curPut(), "thread1");
    thread.start();
    thread.join();
    System.out.println(map.values());
  }

  static class curPut implements Runnable {

    @Override
    public void run() {

      for (int i = 0; i < 10000; i++) {
        Thread thread = new Thread(() -> map.put(UUID.randomUUID().toString(), ""), "ftf" + i);
        thread.start();
      }
    }
  }
}
