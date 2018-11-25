package cn.zyy.union.thread;

public class ThreadInterrupt {

  public static void main(String[] args) {
    Thread sleepThread = new Thread(new Runnable() {
      public void run() {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    Thread busyThread = new Thread(new Runnable() {
      public void run() {
        while (true) {
        }
      }
    });
    sleepThread.start();
    busyThread.start();
    sleepThread.interrupt();
    busyThread.interrupt();
    while (sleepThread.isInterrupted()) {
    }
    System.out.println("sleepThread isInterrupted: " + sleepThread.isInterrupted());
    System.out.println("busyThread isInterrupted: " + busyThread.isInterrupted());

  }

}
