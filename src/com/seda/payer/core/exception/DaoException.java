package com.seda.payer.core.exception;

public class DaoException extends Exception {

	private static final long serialVersionUID = -7452891424230360179L;
	private int errorCode;
	private String errorMessage;
	/**
	 * Constructor of DaoException.
	 * @param message - The message that we pass in case of this exception.
	 * @param cause - The {@link Throwable} class is the superclass of all errors and exceptions in the Java language.
	 */
	public DaoException(int code, String message, Throwable cause) {
		super(message, cause);
		this.errorCode=code;
		this.errorMessage=message;
		
	}
	/**
	 * Constructor of DaoException.
	 * @param message - The message that we pass in case of this exception.
	 */
	public DaoException(int code, String message) {
		super(message);
		this.errorCode=code;
		this.errorMessage=message;
	}
	/**
	 * Constructor of DaoException.
	 * @param cause - The {@link Throwable} class is the superclass of all errors and exceptions in the Java language.
	 */
	public DaoException(Throwable cause) {
		super(cause);
	}
	public int getErrorCode() {
		return errorCode<0?-errorCode:errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	

	
	
}
