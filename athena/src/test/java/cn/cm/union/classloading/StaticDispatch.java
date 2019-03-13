package cn.cm.union.classloading;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StaticDispatch {

  static abstract class Human {

  }

  static class Man extends Human {

  }

  static class Woman extends Human {

  }
  public void sayHello(Human guy){
    System.out.println("hello,guy!");
  }
  public void sayHello(Man guy){
    System.out.println("hello gentleman");
  }
  public void sayHello(Woman guy){
    System.out.println("hello,lady");
  }

  public static void main(String[] args) throws InterruptedException {
    StaticDispatch dispatch = new StaticDispatch();
    Human man = new Man();
    Human woman = new Woman();
    dispatch.sayHello(man);
    dispatch.sayHello(woman);

    List<Integer> list = IntStream.range(1,6).boxed().collect(Collectors.toList());
    ConcurrentHashMap map = new ConcurrentHashMap(list.size());
    list.parallelStream().forEach(a->{
      System.out.println(a);
      map.putIfAbsent(a,Thread.currentThread().getContextClassLoader());

    });
    Thread.sleep(5000);
    map.values().stream().forEach(System.out::println);
  }

}
