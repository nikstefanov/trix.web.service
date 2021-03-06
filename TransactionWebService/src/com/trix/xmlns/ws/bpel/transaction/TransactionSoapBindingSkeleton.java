/**
 * TransactionSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.trix.xmlns.ws.bpel.transaction;

public class TransactionSoapBindingSkeleton implements com.trix.xmlns.ws.bpel.transaction.TransactionPortType, org.apache.axis.wsdl.Skeleton {
    private com.trix.xmlns.ws.bpel.transaction.TransactionPortType impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.trix.com/ws/bpel/transaction", "stringElement"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("updateDb", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("", "updateDb"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("updateDb") == null) {
            _myOperations.put("updateDb", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("updateDb")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("updateDbFault");
        _fault.setQName(new javax.xml.namespace.QName("http://xmlns.trix.com/ws/bpel/transaction", "stringElement"));
        _fault.setClassName("com.trix.xmlns.ws.bpel.transaction.StringMessage");
        _fault.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.trix.com/ws/bpel/transaction", "emptyElement"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://xmlns.trix.com/ws/bpel/transaction", ">emptyElement"), com.trix.xmlns.ws.bpel.transaction.EmptyElement.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("commitTransaction", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("", "commitTransaction"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("commitTransaction") == null) {
            _myOperations.put("commitTransaction", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("commitTransaction")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.trix.com/ws/bpel/transaction", "emptyElement"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://xmlns.trix.com/ws/bpel/transaction", ">emptyElement"), com.trix.xmlns.ws.bpel.transaction.EmptyElement.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("rollbackTransaction", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("", "rollbackTransaction"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("rollbackTransaction") == null) {
            _myOperations.put("rollbackTransaction", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("rollbackTransaction")).add(_oper);
    }

    public TransactionSoapBindingSkeleton() {
        this.impl = new com.trix.xmlns.ws.bpel.transaction.TransactionSoapBindingImpl();
    }

    public TransactionSoapBindingSkeleton(com.trix.xmlns.ws.bpel.transaction.TransactionPortType impl) {
        this.impl = impl;
    }
    public void updateDb(javax.xml.rpc.holders.StringHolder stringMessagePart) throws java.rmi.RemoteException, com.trix.xmlns.ws.bpel.transaction.StringMessage
    {
        impl.updateDb(stringMessagePart);
    }

    public void commitTransaction(com.trix.xmlns.ws.bpel.transaction.EmptyElement emptyMessagePart) throws java.rmi.RemoteException
    {
        impl.commitTransaction(emptyMessagePart);
    }

    public void rollbackTransaction(com.trix.xmlns.ws.bpel.transaction.EmptyElement emptyMessagePart) throws java.rmi.RemoteException
    {
        impl.rollbackTransaction(emptyMessagePart);
    }

}
