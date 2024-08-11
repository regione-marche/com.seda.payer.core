package com.seda.payer.core.exception;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;

import org.postgresql.util.PSQLException;

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

	//inizio LP 20240811  - PGNTCORE-24
	static public void makeIfDuplicateKeyError(UndeclaredThrowableException x) throws DaoException
	{
		x.printStackTrace();
		if(x.getUndeclaredThrowable() != null) {
			if(x.getUndeclaredThrowable() instanceof InvocationTargetException) {
				InvocationTargetException a = (InvocationTargetException) x.getUndeclaredThrowable();
				if(a.getTargetException() instanceof PSQLException) {
					PSQLException p = (PSQLException) a.getTargetException(); 
					if(p.getSQLState().equalsIgnoreCase("23505")) { //unique_violation
						throw new DaoException(-803, p.getMessage());
					} else if (p.getSQLState().equalsIgnoreCase("23000")) {//integrity_constraint_violation
						throw new DaoException(-1062, p.getMessage());
					}
					/*
					TODO: codici su sqlserver ?
					23001	restrict_violation
					23502	not_null_violation
					23503	foreign_key_violation
					23514	check_violation
					23P01	exclusion_violation
					*/
				}
			}
		}
		throw new DaoException(x);
	}

	static public void makeIfDuplicateKeyError(UndeclaredThrowableException x, int code, String mess) throws DaoException
	{
		x.printStackTrace();
		if(x.getUndeclaredThrowable() != null) {
			if(x.getUndeclaredThrowable() instanceof InvocationTargetException) {
				InvocationTargetException a = (InvocationTargetException) x.getUndeclaredThrowable();
				if(a.getTargetException() instanceof PSQLException) {
					PSQLException p = (PSQLException) a.getTargetException(); 
					if(p.getSQLState().equalsIgnoreCase("23505")) {
						throw new DaoException(code, mess);
					} else if (p.getSQLState().equalsIgnoreCase("23000")) {
						throw new DaoException(code, mess);
					}
				}
			}
		}
		throw new DaoException(x);
	}

	static public void makeIfDuplicateKeyError(UndeclaredThrowableException x, String mess) throws DaoException
	{
		x.printStackTrace();
		if(x.getUndeclaredThrowable() != null) {
			if(x.getUndeclaredThrowable() instanceof InvocationTargetException) {
				InvocationTargetException a = (InvocationTargetException) x.getUndeclaredThrowable();
				if(a.getTargetException() instanceof PSQLException) {
					PSQLException p = (PSQLException) a.getTargetException(); 
					if(p.getSQLState().equalsIgnoreCase("23505")) {
						throw new DaoException(-803, mess);
					} else if (p.getSQLState().equalsIgnoreCase("23000")) {
						throw new DaoException(-1602, mess);
					}
				}
			}
		}
		throw new DaoException(x);
	}
	//fine LP 20240811  - PGNTCORE-24 	

}
