package cn.cm.union.juctools;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExchangerTest {

  public static final ExecutorService SERVICE = new ThreadPoolExecutor(4, 4, 5, TimeUnit.SECONDS,
      new ArrayBlockingQueue(2));

  public static final Exchanger<String> exr = new Exchanger<String>();

  public static void main(String[] args) {
    SERVICE.submit(() -> {
      //a录入银行数据流水
      String a = "银行流水A";
      try {
        exr.exchange(a);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    SERVICE.submit(() -> {
      //b录入银行数据流水
      String b = "银行流水B";
      try {
        String a = exr.exchange(b);
        System.out.println("A和B的数据是否一致：" + a.equals(b) + ",a录入的数据是" + a + ",b录入的数据是" + b);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });


    SERVICE.shutdown();
  }
}
