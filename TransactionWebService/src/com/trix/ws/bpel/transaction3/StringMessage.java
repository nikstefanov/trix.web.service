/**
 * StringMessage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.trix.ws.bpel.transaction3;

public class StringMessage extends org.apache.axis.AxisFault {
    public java.lang.String strContent;
    public java.lang.String getStrContent() {
        return this.strContent;
    }

    public StringMessage() {
    }

    public StringMessage(java.lang.Exception target) {
        super(target);
    }

    public StringMessage(java.lang.String message, java.lang.Throwable t) {
        super(message, t);
    }

      public StringMessage(java.lang.String strContent) {
        this.strContent = strContent;
    }

    /**
     * Writes the exception data to the faultDetails
     */
    public void writeDetails(javax.xml.namespace.QName qname, org.apache.axis.encoding.SerializationContext context) throws java.io.IOException {
        context.serialize(qname, null, strContent);
    }
}
