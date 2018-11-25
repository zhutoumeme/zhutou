package cn.zyy.union.thread;

import java.util.concurrent.TimeUnit;
import org.omg.PortableServer.THREAD_POLICY_ID;

public class Deamon {

  public static void main(String[] args) {
    Thread thread = new Thread(new DeamonRunner(),"DeamonThread");
    thread.setDaemon(true);
    thread.start();
  }
  static class DeamonRunner implements  Runnable{
    @Override
    public void run() {
      try {
        TimeUnit.SECONDS.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }finally {
        System.out.println("Deamon Thread finally run...");
      }
    }
  }
}
