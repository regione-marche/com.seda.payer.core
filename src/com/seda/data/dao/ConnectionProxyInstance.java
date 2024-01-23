package com.seda.data.dao;

import java.sql.Connection;
import java.util.ArrayList;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class ConnectionProxyInstance {
	
	private  Connection pgConnection;
	private static int cursorNumber;
	private static int variableNumberNoCursor;
	private static ArrayList<Integer> dataTypeInOut;
	
	public ConnectionProxyInstance(Connection connection) {
		this.pgConnection = connection;
	}

	public  Connection getConenction() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Connection.class);
		
		enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
		    if (method.getName().equals("prepareCall")) {
		    	variableNumberNoCursor = ((String)args[0]).length()-((String)args[0]).replace("?","").length();
		    	variableNumberNoCursor = variableNumberNoCursor - cursorNumber;
		    	//Se la stored ha un cursore devo mettere autocommit a false, altrimenti su postgres non va
		    	if (cursorNumber>0) {
		    		pgConnection.setAutoCommit(false);
		    	}
		    	return  PgResultSetProxyInstance.getCallableStatement(pgConnection.prepareCall((String)args[0]));
		      
		    } else {
		        return method.invoke(pgConnection, args);
				// return proxy.invokeSuper(obj, args);
		    }
		});
		return (Connection) enhancer.create();

	}
	
	public static int getVariableNumber() {
		return variableNumberNoCursor;
	}
	
	public static int getRefCursorNumber() {
		return cursorNumber;
	}
	public static ArrayList<Integer> getDataTypeInOut() {
		return dataTypeInOut;
	}
	
	public static void setRefCursorNumber(int cursorNumberIn) {
		cursorNumber=cursorNumberIn;
	}
	public static void setDataTypeInOut(ArrayList<Integer> dataTypeInOutIn) {
		dataTypeInOut=dataTypeInOutIn;
	}

}
