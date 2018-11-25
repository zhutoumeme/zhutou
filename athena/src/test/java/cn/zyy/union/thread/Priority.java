package cn.zyy.union.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Priority {

  public static void main(String[] args) throws InterruptedException {
    List<Job> jobs = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      int priority = i < Thread.NORM_PRIORITY ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
      Job job = new Job(priority);
      jobs.add(job);
      Thread thread = new Thread(job, "Thread-" + i);
      thread.setPriority(priority);
      thread.start();
    }
    notStart = false;
    Thread.currentThread().setPriority(8);
    System.out.println("done");
    TimeUnit.SECONDS.sleep(10);
    notEnd = false;
    jobs.stream().forEach(
        job -> System.out.println("job priority:" + job.priority + ",jobCount:" + job.jobCount));
  }

  private static volatile boolean notStart = true;
  private static volatile boolean notEnd = true;

  static class Job implements Runnable {

    private int priority;
    private long jobCount;

    public Job(int priority) {
      this.priority = priority;
    }

    @Override
    public void run() {
      while (notStart) {
        Thread.yield();
      }
      while (notEnd) {
        Thread.yield();
        jobCount++;
      }
    }
  }
}
