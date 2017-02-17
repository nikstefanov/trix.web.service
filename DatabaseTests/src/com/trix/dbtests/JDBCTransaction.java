package com.trix.dbtests;


import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTransaction {
  
  
  public void runTransaction() {
    Connection conn = null;
    Properties connectionProps = new Properties();
    connectionProps.put("user", "root");
    connectionProps.put("password", "123");
    Statement stmt = null;
    
    try{
      conn = DriverManager.getConnection(    
        "jdbc:mysql://localhost:3306/classicmodels", connectionProps);
      stmt = conn.createStatement();
      conn.setAutoCommit(false);
      stmt.executeUpdate(
          "insert into offices values "+
          "('10', 'Burgas', '+359988848691', 'Meden rudnik, 21', NULL, 'Burgas', 'Bulgaria', '8011', 'NA')");
      stmt.executeUpdate(
          "insert into offices values "+
          "('11', 'Varna', '+359988848874', 'Vladislav Varnenchik, 09', NULL, 'Varna', 'Bulgaria', '7000', 'NA')");
      conn.commit();
    }catch(Exception e) {
      e.printStackTrace();
    }finally {
      if (stmt != null) try{stmt.close();}catch(SQLException se){se.printStackTrace();}
      if (conn != null) try{conn.close();}catch(SQLException se){se.printStackTrace();}
    }
  } 
  
  

  public static void main(String[] args) {
    JDBCTransaction T = new JDBCTransaction();
    T.runTransaction();
  }
}
