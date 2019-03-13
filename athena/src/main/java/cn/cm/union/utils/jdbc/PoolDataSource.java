package cn.cm.union.utils.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.util.CollectionUtils;

public class PoolDataSource {

  private PoolConfig poolConfig = new PoolConfig();
  private AtomicInteger activeConnections = new AtomicInteger();

  Vector<Connection> freePools = new Vector<>();
  Vector<PoolEntry> usePools = new Vector<>();

  public PoolDataSource() {
    init();
  }

  private void init() {
    try {
      Class.forName(poolConfig.getDriver());
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      try {
        Thread.currentThread().getContextClassLoader().loadClass(poolConfig.getDriver());
      } catch (ClassNotFoundException e1) {
        e1.printStackTrace();
      }
    }
    for (int initSize = poolConfig.getInitSize(); initSize > 0; initSize--) {
      Connection connection = createConnection();
      freePools.add(connection);
    }
    check();
  }

  private Connection createConnection() {
    Connection connection = null;
    try {
      connection = DriverManager
          .getConnection(poolConfig.getUrl(), poolConfig.getUser(), poolConfig.getPassword());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    activeConnections.incrementAndGet();
    System.out.println("创建一个连接,当前的活跃的连接数目为：" + activeConnections.get() + "连接：" + connection);
    return connection;
  }

  private PoolEntry getConnection() {
    Connection connection = null;
    if (!CollectionUtils.isEmpty(freePools)) {
      connection = freePools.remove(0);
    } else {
      if (activeConnections.get() < poolConfig.getMaxSize()) {
        connection = createConnection();
      } else {
        System.out.println("连接池已经满了，需要等待...");
        try {
          TimeUnit.SECONDS.sleep(1000);
          return getConnection();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
    PoolEntry poolEntry = new PoolEntry(connection, System.currentTimeMillis());
    usePools.add(poolEntry);
    return poolEntry;
  }

  public void release(Connection connection) {
    try {
      if (null != connection && !connection.isClosed()) {
        freePools.add(connection);
        System.out.println("回收了一个连接，当前空闲连接数为：" + freePools.size());
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void check() {
    TimeOutWork timeOutWork = new TimeOutWork(usePools, activeConnections, poolConfig.getTimeOut());
    ScheduledExecutorService executorService = Executors
        .newScheduledThreadPool(poolConfig.getInitSize());
    executorService.scheduleWithFixedDelay(timeOutWork, 1000, 3000, TimeUnit.MILLISECONDS);
  }

  public static void main(String[] args) {

    PoolDataSource dataSource = new PoolDataSource();

    Runnable runnable = () -> {
      Connection conn = dataSource.getConnection().getConnection();
      System.out.println(conn);
    };

    ExecutorService executorService = Executors.newFixedThreadPool(5);
    for (int i = 0; i < 60; i++) {
      executorService.submit(runnable);
    }
    executorService.shutdown();
  }


}
