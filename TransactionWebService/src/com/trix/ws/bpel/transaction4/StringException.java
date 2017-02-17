package com.trix.ws.bpel.transaction4;

public class StringException extends Exception {
	private static final long serialVersionUID = -4804780766044802411L;
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public StringException(String message) {
		super();
		this.message = message;
	}
	public StringException() {
		super();
	}
}
