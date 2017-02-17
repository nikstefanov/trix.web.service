package com.trix.dbtests;

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

public class XAMultiConnTransaction {
  private XADataSource xaDataSource;
  
  private XAMultiConnTransaction(){
    EmbeddedXADataSource40 xaDS = new EmbeddedXADataSource40();
    xaDS.setDatabaseName("D:\\Users\\User\\Documents\\jdk1.7.0_51\\demo\\db\\databases\\toursdb");
    xaDataSource = xaDS;
  }

  public void runMultiConn() {
    String tableName = "cities";
    Connection c1 = null;
    Connection c2 = null;
    Connection c3 = null;
    
    try {
      XAConnection xaConn1 = xaDataSource.getXAConnection();
      XAConnection xaConn2 = xaDataSource.getXAConnection();
      XAConnection xaConn3 = xaDataSource.getXAConnection();
      
      XAResource xaRes1 = xaConn1.getXAResource();
      XAResource xaRes2 = xaConn2.getXAResource();
      XAResource xaRes3 = xaConn3.getXAResource();
      
      c1 = xaConn1.getConnection();
      c2 = xaConn2.getConnection();
      c3 = xaConn3.getConnection();
      
      Statement stmt1 = c1.createStatement();
      Statement stmt2 = c2.createStatement();
      Statement stmt3 = c3.createStatement();
      
      Xid xid = new MysqlXid((new String("Transaction")).getBytes("utf-8"),
          (new String("Branch")).getBytes("utf-8"),1);
      
      xaRes1.start(xid, XAResource.TMNOFLAGS);
      int count1 = stmt1.executeUpdate("INSERT INTO " + tableName + " VALUES "
          + "(91, 'Burgas', 'Bulgaria', 'BOJ', 'Bulgarian', 'BG')");
      xaRes1.end(xid, XAResource.TMSUSPEND);
  
      xaRes2.start(xid, XAResource.TMRESUME);
      int count2 = stmt2.executeUpdate("INSERT INTO " + tableName + " VALUES "
          + "('92', 'Varna', 'Bulgaria', 'VAR', 'Bulgarian', 'BG')");
      xaRes2.end(xid, XAResource.TMSUSPEND);
  
      xaRes3.start(xid, XAResource.TMRESUME);
      int count3 = stmt3.executeUpdate("INSERT INTO " + tableName + " VALUES "
          + "('93', 'Sofia', 'Bulgaria', 'SOF', 'Bulgarian', 'BG')");
      xaRes3.end(xid, XAResource.TMSUCCESS);
      // When completed, commit the transaction as a single unit.
      // A prepare() and commit() or 1 phase commit() is required for
      // each separate database (XAResource) that participated in the
      // transaction. Since the resources accessed (xaRes1, xaRes2, and xaRes3)
      // all refer to the same database, only one prepare or commit is required.
      int rc = xaRes1.prepare(xid);
      xaRes1.commit(xid, false);
    }catch (Exception e) {
      System.out.println("Something has gone wrong.");
      e.printStackTrace();      
      if (e instanceof XAException) {
        System.out.println(((XAException)e).errorCode);
      }
    }finally {
      try {
        if (c1 != null) {
          c1.close();
        }
      }catch (SQLException e) {
        System.out.println("Note: Cleaup exception " +
                          e.getMessage());
      }
      try {
        if (c2 != null) {
          c2.close();
        }
      }catch (SQLException e) {
        System.out.println("Note: Cleaup exception " +
                          e.getMessage());
      }
      try {
        if (c3 != null) {
          c3.close();
        }
      }catch (SQLException e) {
        System.out.println("Note: Cleaup exception " +
                          e.getMessage());
      }
    }
  }
  
  private static void printSQLException(SQLException ex) {
    for (Throwable e : ex) {
      e.printStackTrace(System.err);
      System.err.println("SQLState: " +
          ((SQLException)e).getSQLState());

      System.err.println("Error Code: " +
          ((SQLException)e).getErrorCode());

      System.err.println("Message: " + e.getMessage());

      Throwable t = ex.getCause();
      while(t != null) {
          System.out.println("Cause: " + t);
          t = t.getCause();
      }
    }
  }

  
  public static void main(String[] args) {
    XAMultiConnTransaction A = new XAMultiConnTransaction();
    A.runMultiConn();
  }
}
