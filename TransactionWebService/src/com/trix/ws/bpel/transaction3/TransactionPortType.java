/**
 * TransactionPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.trix.ws.bpel.transaction3;

public interface TransactionPortType extends java.rmi.Remote {
    public void updateDb(javax.xml.rpc.holders.StringHolder strContent) throws java.rmi.RemoteException, com.trix.ws.bpel.transaction3.StringMessage;
    public void commitTransaction() throws java.rmi.RemoteException;
    public void rollbackTransaction() throws java.rmi.RemoteException;
}
