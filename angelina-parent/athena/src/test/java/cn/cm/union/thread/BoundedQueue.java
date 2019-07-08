package cn.cm.union.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有界队列
 */
public class BoundedQueue<T> {

  private Object[] items;
  private Lock lock = new ReentrantLock(false);
  private Condition notEmpty = lock.newCondition();
  private Condition notFull = lock.newCondition();
  //添加位置，删除位置，元素数量
  private int addIndex, removeIndex, count;

  public BoundedQueue(int size) {
    items = new Object[size];
  }

  /**
   * 添加一个元素，如果数组满，则添加线程进入等待状态，直到有"空位"
   */
  public void add(T t) throws InterruptedException {
    lock.lock();
    try {
      while (count == items.length) {
        notFull.await();
      }
      items[addIndex] = t;
      if (++addIndex == items.length) {
        addIndex = 0;
      }
      ++count;
      notEmpty.signal();
    } finally {
      lock.unlock();
    }

  }

  /**
   * 由头部删除一个元素，如果数组空，则删除线程进入等待状态，直到有新添加元素
   */
  public T remove() throws InterruptedException {
    lock.lock();
    try {
      while (count == 0) {
        System.out.println("element is empty");
        notEmpty.await();
      }
      Object x = items[removeIndex];
      if (++removeIndex == items.length) {
        removeIndex = 0;
      }
      --count;
      notFull.signal();
      return (T) x;
    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    BoundedQueue<Integer> boundedQueue = new BoundedQueue<>(1);
    Thread thread1 = new Thread(() -> {
      try {
        System.out.println("thread-1 add begin");
        boundedQueue.add(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }, "thread-1");
    final Integer[] a = {null};
    Thread thread2 = new Thread(() -> {
      try {
        System.out.println("thread-2 remove begin");
        a[0] = boundedQueue.remove();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }, "thread-2");
    thread2.start();
    Thread.sleep(5000);
    thread1.start();
    Thread.sleep(5000);
    System.out.println("remove success,element:" + a[0]);
  }

}
