package cn.cm.union.utils.jdbc;

public class PoolConfig {

  private String driver;
  private String url;
  private String user;
  private String password;
  private int initSize;
  private int maxSize;
  private long timeOut;

  public PoolConfig() {
    driver = "com.mysql.jdbc.Driver";
    url = "jdbc:mysql://localhost:3306/mysql";
    user = "root";
    password = "ins1234";
    initSize = 10;
    maxSize = 20;
    timeOut = 3000;
  }

  public String getDriver() {
    return driver;
  }

  public void setDriver(String driver) {
    this.driver = driver;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getInitSize() {
    return initSize;
  }

  public void setInitSize(int initSize) {
    this.initSize = initSize;
  }

  public int getMaxSize() {
    return maxSize;
  }

  public void setMaxSize(int maxSize) {
    this.maxSize = maxSize;
  }

  public long getTimeOut() {
    return timeOut;
  }

  public void setTimeOut(long timeOut) {
    this.timeOut = timeOut;
  }
}
