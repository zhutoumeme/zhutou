package cn.zyy.union.classloading;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SubClass extends SuperClass {

  static {
    System.out.println("SubClass init!");
  }

  public int inc() {
    int x;
    try {
      x = 1;
      return x;
    } catch (Exception e) {
      x = 2;
      return x;
    } finally {
      x = 3;
    }
  }

  private static  final AtomicInteger seq = new AtomicInteger();

  public synchronized static int seq() {
      if (seq.get() > 9999) {
        try {
          Thread.sleep(1000);
          seq.set(0);
          return seq();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    if (seq.get() > 9999) {
    }
    return seq.getAndIncrement(); }

  public static void main(String[] args) {

    List<Integer> list = IntStream.rangeClosed(0, 99999).boxed().parallel().map(i -> seq()).collect(
        Collectors.toList());
    list.parallelStream().filter(i -> i > 9999).findAny().ifPresent(System.out::println);
  }
}
