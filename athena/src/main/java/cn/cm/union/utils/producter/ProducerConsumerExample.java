package cn.cm.union.utils.producter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
