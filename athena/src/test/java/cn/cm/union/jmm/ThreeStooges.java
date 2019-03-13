package cn.cm.union.jmm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class ThreeStooges {

  private final Set<String> stooges = new HashSet<>();

  public ThreeStooges() {
    stooges.add("asda");
    stooges.add("sadfasd");
    stooges.add("45redf");
  }

  public boolean isStooge(String name) {
    return stooges.contains(name);
  }

  public static void main(String[] args) {
    ThreeStooges threeStooges = new ThreeStooges();
    threeStooges.stooges.add("1111");
    System.out.println(threeStooges.stooges);
    Map<Integer,Integer>  map = new HashMap<>();
    map.put(1,1);
  }

}
