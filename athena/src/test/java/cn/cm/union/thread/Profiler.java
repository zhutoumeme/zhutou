package cn.cm.union.thread;

import java.util.concurrent.TimeUnit;

public class Profiler {

  private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>() {
    @Override
    protected Long initialValue() {
      return System.currentTimeMillis();
    }
  };

  public static void main(String[] args) throws InterruptedException {
    System.out.println(TIME_THREADLOCAL.get());
    TimeUnit.SECONDS.sleep(1);
    System.out.println(Profiler.end());
    System.out.println("Cost:" + (Profiler.end() - TIME_THREADLOCAL.get()));
  }

  public static final void begin() {
    TIME_THREADLOCAL.set(System.currentTimeMillis());
  }

  public static final Long end() {
    return System.currentTimeMillis();
  }
}
