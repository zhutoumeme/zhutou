package cn.cm.union.utils.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest {

  public static void main(String[] args) {

  }

  public void test() throws ClassNotFoundException, SQLException {
    //1.注册驱动
    Class.forName("com.mysql.jdbc.Driver");

    //2.建立链接
    String url = "jdbc:mysql://localhost:3306/asdasd";
    String user = "root";
    String password = "asdasd889988";
    Connection connection = DriverManager.getConnection(url, user, password);
    //3.创建语句
    Statement statement = connection.createStatement();
    //4.执行语句
    ResultSet resultSet = statement.executeQuery("SELECT * FROM user_info ");
    //5.处理结果
    while (resultSet.next()) {
      System.out.println(resultSet.getObject(1) + "/t" + resultSet.getObject(2));
    }
  }

}
