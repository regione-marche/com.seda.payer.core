package com.seda.data.dao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.util.ArrayList;

import org.postgresql.core.BaseConnection;
import org.postgresql.jdbc.PgClob;


public class ResultSetProxyHandler implements InvocationHandler{
	

	private CallableStatement callableStatement;
	private int i; //da intervenire
	private ArrayList<Integer> DataTypeInOut = ConnectionProxyInstance.getDataTypeInOut();
	
	public ResultSetProxyHandler(CallableStatement resultSet) {
		this.callableStatement=resultSet;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//System.out.println(method.getName());
		if(method.getName().equals("getResultSet")) {
			//System.out.println("Get record "+i);
			return callableStatement.getObject(i);
		}
		if(method.getName().equals("getMoreResults"))
		{
			i++;
			//provo a vedere se ha risultati nel caso torno true
			try{
				callableStatement.getObject(i);
				return true;
			}catch(Exception e) 
			{
				return false;
			}
		}
		if(method.getName().equals("hasMore"))
		{
		  try{
				callableStatement.getObject(i+1);
				return true;
			}catch(Exception e) 
			{
			return false;
		}
		}
		if(method.getName().equals("close"))
		{
			//i=1;
			//Chiudo anche la Callabel statement modificata
			PgResultSetProxyInstance.closeCallableStatement();	
		}
		if(method.getName().equals("execute"))
		{
			int counterIni = ConnectionProxyInstance.getVariableNumber()+1;
			int counterFin = ConnectionProxyInstance.getRefCursorNumber()+ConnectionProxyInstance.getVariableNumber();
			for (int j=counterIni;j<=counterFin;j++) {
				callableStatement.setNull(j,java.sql.Types.REF_CURSOR);
				callableStatement.registerOutParameter(j, java.sql.Types.REF_CURSOR);
			}
			//Stampo la query che sta per eseguire
			//System.out.println("query="+callableStatement);
			//inizializzo il ref cursor number
			i = counterIni;
			callableStatement.execute();
			try {
				callableStatement.getObject(i);
				return true;
			}
			catch (Exception e) {
				return false;
			}
			
		}
		
		//Gestione parametri inout, prima bisogna fare la registerouputparameter e poi la set del valore
		if (method.getName().contains("set")) {
			if (DataTypeInOut.get((int)args[0]-1) ==1) {
				//callableStatement.setNull((int)args[0],java.sql.Types.INTEGER); //da Riccardo Ticci
				callableStatement.setNull((int)args[0],(int)args[1]);
			}
		}

		if(method.getName().equals("registerOutParameter")){
			//Intercetto i tipi clob e li strasformo in stringa. In PostgreSQL il clob è tradotto come text
			if (args[1].equals(java.sql.Types.CLOB)) {
				args[1]=java.sql.Types.VARCHAR;
			}
			//Intercetto i tipi smallint e li trasformo in integer. La gestione degli smallint crea confusione anche perchè sul bap sono usati come integer (viene fatta la getInt)
			if (args[1].equals(java.sql.Types.SMALLINT)) {
				args[1]=java.sql.Types.INTEGER;
			}
			//Intercetto i tipi decimal e li trasformo in numeric.
			if (args[1].equals(java.sql.Types.DECIMAL)) {
				args[1]=java.sql.Types.NUMERIC;
			}
			//setto a null solo se il parametro non e' di tipo inout, perchè per gli inout l'ho gia' fatto sopra
			if (DataTypeInOut.get((int)args[0]-1) !=1) {
				callableStatement.setNull((int)args[0],(int)args[1]);
			}
		}
		
		/*if(method.getName().equals("getClob"))
		{

			return new javax.sql.rowset.serial.SerialClob((callableStatement.getString((int)args[0])).toCharArray());
			//return (Clob)callableStatement.getObject((int)args[0]);
		
		}*/
		return  method.invoke(callableStatement, args);		
	}

}
