package cn.zyy.union.proxy;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoxingTest {

  public static void main(String[] args) {
    List<Integer> list = IntStream.range(0, 1000000).boxed().collect(Collectors.toList());
    LinkedList<Integer> linkedList = new LinkedList(list);
    long begin = System.currentTimeMillis();
    for (Integer integer : list) {
      System.out.print(integer);
    }
    System.out.println();
    System.out.print("foreach spend:" + (System.currentTimeMillis() - begin));
    begin = System.currentTimeMillis();
    Iterator<Integer> integers = list.iterator();
    System.out.println();
    while (integers.hasNext()) {
      System.out.print(integers.next());
    }
    System.out.println();
    System.out.println("iterator spend:" + (System.currentTimeMillis() - begin));
  }

}
