package cn.cm.union.utils.jdbc;

import java.sql.Connection;

public class PoolEntry {

  private Connection connection;
  private long createTime;

  public PoolEntry(Connection connection, long createTime) {
    this.connection = connection;
    this.createTime = createTime;
  }

  public Connection getConnection() {
    return connection;
  }

  public void setConnection(Connection connection) {
    this.connection = connection;
  }

  public long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(long createTime) {
    this.createTime = createTime;
  }
}
