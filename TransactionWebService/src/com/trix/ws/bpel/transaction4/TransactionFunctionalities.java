package com.trix.ws.bpel.transaction4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.rmi.RemoteException;

public class TransactionFunctionalities {
  static Connection conn = null;
  static PreparedStatement updateStatement = null;
	
  public String updateDb(String valuesString)
      throws RemoteException, StringException {
    System.out.println("Update");
    try{
      if (conn == null || updateStatement == null){
        init();
      }
      String[] values = valuesString.split(",");
      for (int i = 0; i < values.length; i++){
        updateStatement.setString(i+1, values[i]);
      }
      int updatedRows = updateStatement.executeUpdate();
      return updatedRows+"";
    }catch(SQLException se){
      se.printStackTrace();
      System.err.println("SQLState: " + se.getSQLState());
      System.err.println("Error Code: " +se.getErrorCode());
      throw new StringException(se.getSQLState());
    }catch(Exception e){
      e.printStackTrace();
      throw new RemoteException("",e);
    }
  }
  
  public void commitTransaction() throws RemoteException {
    System.out.println("Commit");
    try{
      if (conn != null) conn.commit();      
    }catch(Exception e){
      e.printStackTrace();
      throw new RemoteException("",e);
    }finally{
      try{
        exit();
      }catch(SQLException se){
        se.printStackTrace();
        throw new RemoteException("",se);
      }
    }
  }
  
  public void rollbackTransaction() throws RemoteException {
    System.out.println("Rollback");
    try{
      if (conn != null) conn.rollback();      
    }catch(Exception e){
      e.printStackTrace();
      throw new RemoteException("",e);
    }finally{
      try{
        exit();
      }catch(SQLException se){
        se.printStackTrace();
        throw new RemoteException("",se);
      }
    }
  }
  
  private void init() throws SQLException {
    System.out.println("Init");
    Properties connectionProps = new Properties();
    connectionProps.put("user", "root");
    connectionProps.put("password", "123");
    conn = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/classicmodels", connectionProps);
    updateStatement = conn.prepareStatement( "insert into offices values "+
        "(?, ?, ?, ?, ?, ?, ?, ?, ?)");
    conn.setAutoCommit(false);
  }
  
  private void exit() throws SQLException {
    System.out.println("Exit");
    if (updateStatement != null) updateStatement.close();
    if (conn != null) conn.close();
    updateStatement = null;
    conn = null;
  }
}
