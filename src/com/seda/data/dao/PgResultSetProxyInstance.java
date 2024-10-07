package com.seda.data.dao;

import java.lang.reflect.Proxy;
import java.sql.CallableStatement;

public class PgResultSetProxyInstance {
	
	private static CallableStatement pgCallableStatement;
	
	/**
	 * 
	 * @param callableStatement
	 * @return batsa proxy il DriverManager get connection e dare una connection proxata
	 */
	public static CallableStatement getCallableStatement(CallableStatement callableStatement) {
		//if(pgCallableStatement!=null) return pgCallableStatement;
		pgCallableStatement = (CallableStatement) Proxy.newProxyInstance(
				PgResultSetProxyInstance.class.getClassLoader(), 
				  new Class[] { CallableStatement.class }, 
				  new ResultSetProxyHandler(callableStatement));
		 return pgCallableStatement;
	}
	
	public static void closeCallableStatement() {
		pgCallableStatement = null;
	}
	

}
