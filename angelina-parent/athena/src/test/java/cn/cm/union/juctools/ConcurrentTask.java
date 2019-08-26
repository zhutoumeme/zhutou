package cn.cm.union.juctools;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import org.springframework.util.ObjectUtils;

public class ConcurrentTask<T, V> {

  private final ConcurrentHashMap<T, FutureTask<V>> taskCache = new ConcurrentHashMap<>();

  private V executionTask(final T taskName) {
    while (true) {
      FutureTask<V> futureTask = taskCache.get(taskName);
      if (ObjectUtils.isEmpty(futureTask)) {
        Callable<T> task = () -> taskName;
        FutureTask future = new FutureTask(task);
        futureTask = taskCache.putIfAbsent(taskName, future);
        if (ObjectUtils.isEmpty(futureTask)) {
          futureTask = future;
          futureTask.run();
        }
      }
      try {
        return futureTask.get();
      } catch (InterruptedException | ExecutionException e) {
        taskCache.remove(taskName, futureTask);
      }
    }
  }

  public static void main(String[] args) {
    ConcurrentTask concurrentTask = new ConcurrentTask();
    String result = (String) concurrentTask.executionTask("asdad");
    System.out.println(result);
  }
}
