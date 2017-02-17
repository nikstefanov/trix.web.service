/**
 * StringMessage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.trix.xmlns.ws.bpel.transaction;

public class StringMessage extends org.apache.axis.AxisFault {
    public java.lang.String stringMessagePart;
    public java.lang.String getStringMessagePart() {
        return this.stringMessagePart;
    }

    public StringMessage() {
    }

    public StringMessage(java.lang.Exception target) {
        super(target);
    }

    public StringMessage(java.lang.String message, java.lang.Throwable t) {
        super(message, t);
    }

      public StringMessage(java.lang.String stringMessagePart) {
        this.stringMessagePart = stringMessagePart;
    }

    /**
     * Writes the exception data to the faultDetails
     */
    public void writeDetails(javax.xml.namespace.QName qname, org.apache.axis.encoding.SerializationContext context) throws java.io.IOException {
        context.serialize(qname, null, stringMessagePart);
    }
}
