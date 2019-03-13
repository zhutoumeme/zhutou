package cn.cm.union.generic;

import java.util.ArrayList;
import java.util.List;

public class Reduce implements Function {


  @Override
  public Object apply(Object arg1, Object arg2) {
    return (int) arg1 - (int) arg2;
  }

  public static void main(String[] args) {

    List<Integer> list = new ArrayList<Integer>() {{
      add(1);
      add(2);
    }};
    Integer init = 5;
    Function function = new Reduce();
    System.out.println(reduce(list, function, init));
  }

  static <TR> TR reduce(List<TR> list, Function function, TR initVal) {

    TR result = initVal;

    for (TR t : list) {
      result = function.apply(initVal, t);
    }
    return result;
  }
}
