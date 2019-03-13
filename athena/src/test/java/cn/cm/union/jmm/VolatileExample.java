package cn.cm.union.jmm;

import java.util.ArrayList;
import java.util.List;

public class VolatileExample {

  public static void main(String[] args) {
    VolatileExample example = new VolatileExample();
    Thread a = new Thread(() -> {

      example.write();
    });
    Thread b = new Thread(() -> {

      try {
        example.read();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    List<Thread> list = new ArrayList(2);
    list.add(a);
    list.add(b);
    for (Thread thread : list) {
      thread.start();
    }

  }

  int a = 0;
  volatile boolean flag = false;
  public void write() {
    for (; ; ) {
      if (flag == true) {
        System.out.println("A线程已经退出");
        System.out.println("write time:"+System.currentTimeMillis());
        break;
      }
    }
  }

  public void read() throws InterruptedException {
    for (; ; ) {
      Thread.sleep(2000);//等2秒，让A线程线执行
      System.out.println("flag已设置为true");
      flag = true;
      System.out.println("read time:"+System.currentTimeMillis());
      break;
    }
  }

}
