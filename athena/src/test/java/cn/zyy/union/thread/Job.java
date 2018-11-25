package cn.zyy.union.thread;

public class Job implements Runnable {

  @Override
  public void run() {
    System.out.println("job is running...:" + System.currentTimeMillis());
  }
}
