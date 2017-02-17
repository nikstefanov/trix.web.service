/**
 * TransactionServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.trix.xmlns.ws.bpel.transaction;

public class TransactionServiceLocator extends org.apache.axis.client.Service implements com.trix.xmlns.ws.bpel.transaction.TransactionService {

    public TransactionServiceLocator() {
    }


    public TransactionServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public TransactionServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for transaction
    private java.lang.String transaction_address = "http://localhost:8090/TransactionWebService/services/transaction";

    public java.lang.String gettransactionAddress() {
        return transaction_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String transactionWSDDServiceName = "transaction";

    public java.lang.String gettransactionWSDDServiceName() {
        return transactionWSDDServiceName;
    }

    public void settransactionWSDDServiceName(java.lang.String name) {
        transactionWSDDServiceName = name;
    }

    public com.trix.xmlns.ws.bpel.transaction.TransactionPortType gettransaction() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(transaction_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return gettransaction(endpoint);
    }

    public com.trix.xmlns.ws.bpel.transaction.TransactionPortType gettransaction(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.trix.xmlns.ws.bpel.transaction.TransactionSoapBindingStub _stub = new com.trix.xmlns.ws.bpel.transaction.TransactionSoapBindingStub(portAddress, this);
            _stub.setPortName(gettransactionWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void settransactionEndpointAddress(java.lang.String address) {
        transaction_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.trix.xmlns.ws.bpel.transaction.TransactionPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.trix.xmlns.ws.bpel.transaction.TransactionSoapBindingStub _stub = new com.trix.xmlns.ws.bpel.transaction.TransactionSoapBindingStub(new java.net.URL(transaction_address), this);
                _stub.setPortName(gettransactionWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("transaction".equals(inputPortName)) {
            return gettransaction();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://xmlns.trix.com/ws/bpel/transaction", "transactionService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://xmlns.trix.com/ws/bpel/transaction", "transaction"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("transaction".equals(portName)) {
            settransactionEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
