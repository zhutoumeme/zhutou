package cn.cm.union.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WaitNotify {

  public static void main(String[] args) throws InterruptedException {
    Thread waitThread = new Thread(new Wait(), "waitThread");
    waitThread.start();
    TimeUnit.SECONDS.sleep(1);
    Thread notifyThread = new Thread(new Notify(), "notifyThread");
    notifyThread.start();
  }

  private static boolean flag = true;
  private static Object lock = new Object();

  static class Wait implements Runnable {

    @Override
    public void run() {
      synchronized (lock) {
        while (flag) {
          System.out.println(
              Thread.currentThread() + "flag is true.wait@" + new SimpleDateFormat("HH:mm:ss")
                  .format(new Date()));
          try {
            lock.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        System.out.println(
            Thread.currentThread() + "flag is false.running@" + new SimpleDateFormat("HH:mm:ss")
                .format(new Date()));
      }
    }
  }

  static class Notify implements Runnable {

    @Override
    public void run() {
      synchronized (lock) {
        System.out.println(
            Thread.currentThread() + "hold lock.notify@" + new SimpleDateFormat("HH:mm:ss")
                .format(new Date()));
        lock.notify();
        flag = false;
          //TimeUnit.SECONDS.sleep(5);
      }
      synchronized (lock) {
        System.out.println(
            Thread.currentThread() + "hold lock again.sleep@" + new SimpleDateFormat("HH:mm:ss")
                .format(new Date()));
        try {
          TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
