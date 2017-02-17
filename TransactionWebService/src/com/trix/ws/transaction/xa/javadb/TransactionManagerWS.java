package com.trix.ws.transaction.xa.javadb;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.XAConnection;
import javax.sql.XADataSource;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import org.apache.derby.jdbc.EmbeddedXADataSource40;

import com.mysql.jdbc.jdbc2.optional.MysqlXid;

public class TransactionManagerWS {
  private static XADataSource xaDataSource;
  private XAConnection xaConn;
  private XAResource xaRes;
  private Connection conn;
  private Statement stmt;
  private Xid xid;
  
  static{
    EmbeddedXADataSource40 xaDS = new EmbeddedXADataSource40();
    xaDS.setDatabaseName(
        "D:\\Users\\User\\Documents\\jdk1.7.0_51\\demo\\db\\databases\\toursdb");
    xaDataSource = xaDS;
  }
  
  public void addOperation(
      String gtrid,String bqual,int formatId,int startFlag,String values)
      throws StringException {
    try{
      xaConn = xaDataSource.getXAConnection();
      xaRes = xaConn.getXAResource();
      xid = new MysqlXid(gtrid.getBytes(),bqual.getBytes(),formatId);
      conn = xaConn.getConnection();
      stmt = conn.createStatement();
      xaRes.start(xid, startFlag);
      try {
        stmt.executeUpdate("INSERT INTO CITIES VALUES (" + values +  ')');               
        xaRes.end(xid, XAResource.TMSUCCESS);
      }catch (SQLException se){
        printSQLException(se);
        xaRes.end(xid, XAResource.TMFAIL);
      }
    }catch(SQLException se){
      printSQLException(se);
      throw new StringException("Do nothing.");
    }catch(XAException xe){
      xe.printStackTrace();
    }finally{
      if (xaConn != null){
        try{
          xaConn.close();         
        }catch(SQLException se){
          printSQLException(se);
        }
      }
    }
  }
  
  public String commit(String gtrid,String bqual,int formatId){
    String outMessage;
    try{
      xaConn = xaDataSource.getXAConnection();
      xaRes = xaConn.getXAResource();
      xid = new MysqlXid(gtrid.getBytes(),bqual.getBytes(),formatId);
      xaRes.commit(xid, true);
      outMessage = "Commited.";
    }catch (Exception e){
      e.printStackTrace();
      outMessage = "Not commited normally.";
    }finally{
      if (xaConn != null){
        try{
          xaConn.close();         
        }catch(SQLException se){
          printSQLException(se);
        }        
      }      
    }
    return outMessage;
  }
  
  public String rollback(String gtrid,String bqual,int formatId){
    String outMessage;
    try{
      xaConn = xaDataSource.getXAConnection();
      xaRes = xaConn.getXAResource();
      xid = new MysqlXid(gtrid.getBytes(),bqual.getBytes(),formatId);
      xaRes.rollback(xid);
      outMessage = "Rolled-back.";
    }catch (Exception e){
      e.printStackTrace();
      outMessage = "Not rolled-back normally.";
    }finally{
      if (xaConn != null){
        try{
          xaConn.close();         
        }catch(SQLException se){
          printSQLException(se);
        }        
      }      
    }
    return outMessage;    
  }
  
  private static void printSQLException(SQLException ex){
//  ex.printStackTrace(System.err);
  System.err.println();
    for (Throwable e : ex) {      
      if (e instanceof SQLException) {
        System.err.println("SQLState: " +
            ((SQLException)e).getSQLState());
        System.err.println("Error Code: " +
            ((SQLException)e).getErrorCode());
      }
      System.err.println("Message: " + e.getMessage());

      Throwable t = ex.getCause();
      while(t != null) {
          System.out.println("Cause: " + t);
          t = t.getCause();
      }
      System.err.println();
    }
  }
}
