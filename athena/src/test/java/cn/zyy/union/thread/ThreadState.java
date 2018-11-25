package cn.zyy.union.thread;

import java.util.concurrent.TimeUnit;

public class ThreadState {

  static class TimeWaiting implements Runnable {

    @Override
    public void run() {
      for (; ; ) {
        try {
          TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  static class Waiting implements Runnable {

    public static void main(String[] args) {
      new Thread(new TimeWaiting(),"TimeWaitingThread").start();
      new Thread(new Waiting(),"WaitingThread").start();
      new Thread(new Blocked(),"BlockedThread1").start();
      new Thread(new Blocked(),"BlockedThread2").start();
    }
    @Override
    public void run() {
      for (; ; ) {
        synchronized (Waiting.class) {
          try {
            Waiting.class.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }
  static class Blocked implements Runnable{

    @Override
    public void run() {
      synchronized (Blocked.class){
        for(;;){
          try {
            TimeUnit.SECONDS.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }

}
