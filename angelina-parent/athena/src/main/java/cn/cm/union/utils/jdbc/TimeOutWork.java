package cn.cm.union.utils.jdbc;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class TimeOutWork implements Runnable {

  private Vector<PoolEntry> usePools;
  private AtomicInteger activeConnections;
  private long timeOut;

  public TimeOutWork(Vector<PoolEntry> usePools, AtomicInteger atomicInteger, long timeOut) {
    this.activeConnections = atomicInteger;
    this.usePools = usePools;
    this.timeOut = timeOut;
  }

  @Override
  public void run() {
    System.out.println("超时检查");
    Iterator<PoolEntry> iterator = usePools.iterator();
    while (iterator.hasNext()) {
      PoolEntry poolEntry = iterator.next();
      if (System.currentTimeMillis() - poolEntry.getCreateTime() > timeOut) {
        try {
          activeConnections.decrementAndGet();
          System.out.println("发现有超时连接，强行关闭，当前活动的连接数：" + activeConnections.get());
          poolEntry.getConnection().close();
          iterator.remove();
        } catch (SQLException e) {
          e.printStackTrace();
        }

      }
    }
  }
}
