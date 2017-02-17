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

public class XATransact {
  private XADataSource xaDataSource;
  private XAConnection xaConn;
  private XAResource xaRes;
  private Xid xid;
  
  private XATransact(){
    EmbeddedXADataSource40 xaDS = new EmbeddedXADataSource40();
    xaDS.setDatabaseName(
        "D:\\Users\\User\\Documents\\jdk1.7.0_51\\demo\\db\\databases\\toursdb");
    xaDataSource = xaDS;
    xaConn = null;
    xaRes = null;
    xid = new MysqlXid((new String("Transaction")).getBytes(),
        (new String("Branch")).getBytes(),1);
  }
  
  private void transaction_branch(int startFlag,String values)
      throws SQLException,XAException {
    Connection conn = xaConn.getConnection();
    Statement stmt = conn.createStatement();    
    xaRes.start(xid, startFlag);
    try {
      stmt.executeUpdate("INSERT INTO CITIES VALUES (" + values +  ')');               
      xaRes.end(xid, XAResource.TMSUCCESS);
    }catch (SQLException se){
      printSQLException(se);
      xaRes.end(xid, XAResource.TMFAIL);
    }
  }
  
  private void commit_transaction() throws XAException{   
    xaRes.commit(xid, true);
  }
  
  private void rollback_transaction() throws XAException {
      xaRes.rollback(xid);    
//    try{
//      int res = xaRes.prepare(xid);
//      if (res == XAResource.XA_OK){
//        xaRes.rollback(xid);
//      }
//    }catch(XAException xe){
//      if (xe.errorCode == XAException.XAER_RMERR
//          || xe.errorCode == XAException.XAER_RMFAIL){
//        xaRes.rollback(xid);
//      }
//    }
  }
  
  private static void printSQLException(SQLException ex){
//    ex.printStackTrace(System.err);
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
  
  private void multi_connection_transaction(){
    boolean commit_phase = false;
    try{
      xaConn = xaDataSource.getXAConnection();
      xaRes = xaConn.getXAResource();
      transaction_branch(XAResource.TMNOFLAGS,
          "'91', 'Burgas', 'Bulgaria', 'BOJ', 'Bulgarian', 'BG'");
      xaConn.close();xaConn = null;
      xaConn = xaDataSource.getXAConnection();
      xaRes = xaConn.getXAResource();
      transaction_branch(XAResource.TMJOIN,
          "92, 'Varna', 'Bulgaria', 'VAR', 'Bulgarian', 'BG'");
      xaConn.close();xaConn = null;
      xaConn = xaDataSource.getXAConnection();
      xaRes = xaConn.getXAResource();
      transaction_branch(XAResource.TMJOIN,
          "'93', 'Sofia', 'Bulgaria', 'SOF', 'Bulgarian', 'BG'");
      commit_phase = true;
      commit_transaction();
      xaConn.close();xaConn = null;
    }catch(Exception e){
      if (xaConn != null){
        if (!commit_phase
              || commit_phase && e instanceof XAException && ((XAException)e).errorCode == XAException.XA_RETRY){
          try{
            rollback_transaction();
            System.out.println("Rolled-back!");
          }catch(XAException xe){
            xe.printStackTrace();
            System.err.println("XAException error code: " + xe.errorCode);
          }
        }
        try{
          xaConn.close();
        }catch(SQLException se){
          printSQLException(se);
        }
        xaConn = null;      
      }
    }
  }
  
  public static void main(String[] args) {
    XATransact xaTransact = new XATransact();
    xaTransact.multi_connection_transaction();
  }

}
