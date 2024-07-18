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
	private static int flagUpdateAutocommit;

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
		    	} else {
					//Dal momento che in caso la sp abbia dei cursori di output è necessario impostare l'autocommit a fakse (vedi sopra)
					//occorre reimpostare a true l'autocommit in caso di successive sp richiamate senza cursori
					//Ad esempio se per fare un aggiornamento/inserimento si richiama prima una sp di select e poi una sp di update/insert
					//Con la sp di select sarebbe impostato autocommit a false, è necessario remipostare autocommit a true per consentire il commit per update/insert
					//Dal momento che diversi batch impostano l'autocommit a false, non deve essere impostato l'autocommit a true in caso di chiamata di sp senza cursori
					//per cui è stato introdotto il parametro flagUpdateAutocommit che può assumere valori 0 o 1
					//Per default flagUpdateAutocommit è settato ad 1 e reimposta l'autocommit a true per le operazioni senza cursore
					//Si dovrà richiamare il Metadata.preparecall settando il flagUpdateAutocommit a 0 nei batch che impostano autocommit a false.
					if (flagUpdateAutocommit == 1)
						pgConnection.setAutoCommit(true);
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

	public static int getFlagUpdateAutocommit() {
		return flagUpdateAutocommit;
	}

	public static void setFlagUpdateAutocommit(int flagUpdateAutocommit) {
		ConnectionProxyInstance.flagUpdateAutocommit = flagUpdateAutocommit;
	}
}
