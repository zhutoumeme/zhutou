package cn.zyy.union.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache {

  public static Map<String, Object> map = new HashMap<>();
  public static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
  public static Lock r = rwl.readLock();
  public static Lock w = rwl.writeLock();

  /**
   * 获取一个key对应的value
   */
  public static final Object get(String key) {
    r.lock();
    try {
      return map.get(key);
    } finally {
      r.unlock();
    }
  }

  public static final Object put(String key, Object value) {
    w.lock();
    try {
      return map.put(key, value);
    } finally {
      w.unlock();
    }

  }

  public static final void clearByKey(String key) {
    w.lock();
    try {
      map.remove(key);
    } finally {
      w.unlock();
    }
  }

  public static final void clear() {
    w.lock();
    try {
      map.clear();
    } finally {
      w.unlock();
    }

  }

  public static void main(String[] args) {
    Cache cache = new Cache();
    Thread a = new Thread(() -> {
      System.out.println(cache.put("1", 1));
    }, "a");
    Thread b = new Thread(() -> {
      System.out.println(cache.get("1"));
    }, "b");
    a.start();
    b.start();
  }

}
