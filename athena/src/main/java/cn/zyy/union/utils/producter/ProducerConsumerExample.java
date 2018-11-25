package cn.zyy.union.utils.producter;

import java.awt.geom.QuadCurve2D;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.prefs.BackingStoreException;

public class ProducerConsumerExample {

  private ArrayBlockingQueue queue = new ArrayBlockingQueue(5);
  private ExecutorService producter = Executors.newFixedThreadPool(4);
  private ExecutorService customer = Executors.newFixedThreadPool(3);
  public static void main(String[] args) throws InterruptedException {
    ProducerConsumerExample p = new ProducerConsumerExample();
    p.producter.submit(new Producter(p.queue));
    p.customer.execute(new Customer(p.queue));


    Thread.sleep(1000);
    System.exit(0);
  }

}
