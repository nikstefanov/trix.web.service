/**
 * TransactionPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.trix.xmlns.ws.bpel.transaction;

public interface TransactionPortType extends java.rmi.Remote {
    public void updateDb(javax.xml.rpc.holders.StringHolder stringMessagePart) throws java.rmi.RemoteException, com.trix.xmlns.ws.bpel.transaction.StringMessage;
    public void commitTransaction(com.trix.xmlns.ws.bpel.transaction.EmptyElement emptyMessagePart) throws java.rmi.RemoteException;
    public void rollbackTransaction(com.trix.xmlns.ws.bpel.transaction.EmptyElement emptyMessagePart) throws java.rmi.RemoteException;
}
