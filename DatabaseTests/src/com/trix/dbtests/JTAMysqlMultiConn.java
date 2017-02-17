package com.trix.dbtests;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.XAConnection;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlXid;

public class JTAMysqlMultiConn {
  private MysqlXADataSource xaDataSource;
  
  private JTAMysqlMultiConn() {
    xaDataSource = new MysqlXADataSource();
    xaDataSource.setURL("jdbc:mysql://localhost:3306/classicmodels");
    xaDataSource.setUser("root");
    xaDataSource.setPassword("123");
  }
  
  public void runMultiConn() {
    String tableName = "offices";
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
          + "('10', 'Burgas', '+359988848691', 'Meden rudnik, 21', NULL, 'Burgas', 'Bulgaria', '8011', 'NA')");
      xaRes1.end(xid, XAResource.TMSUCCESS);
  
      xaRes2.start(xid, XAResource.TMJOIN);
      int count2 = stmt2.executeUpdate("INSERT INTO " + tableName + " VALUES "
          + "('11', 'Varna', '+359988848874', 'Vladislav Varnenchik, 09', NULL, 'Varna', 'Bulgaria', '7000', 'NA')");
      xaRes2.end(xid, XAResource.TMSUCCESS);
  
      xaRes3.start(xid, XAResource.TMJOIN);
      int count3 = stmt3.executeUpdate("INSERT INTO " + tableName + " VALUES "
          + "('12', 'Rouse', '+359988848987', 'Kajlyka, 87', NULL, 'Rouse', 'Bulgaria', '5000', 'NA')");
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
  
  
  

  public static void main(String[] args) {
    JTAMysqlMultiConn A = new JTAMysqlMultiConn();
    A.runMultiConn();
  }

}
