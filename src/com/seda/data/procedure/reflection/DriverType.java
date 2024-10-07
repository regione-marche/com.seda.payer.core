package com.seda.data.procedure.reflection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class DriverType {
	public static int getDriverType(Connection connection) {
		//RTC: Dalla connessione prendo il nome del driver,
		//se e' postgres faccio la lower
		String driverClass="";
		String nomeDriver = "";
		int driverType = -1;
		try {
			String originalURL = connection.getMetaData().getURL();
			Driver drv = DriverManager.getDriver(originalURL);
			driverClass = drv.getClass().getName();
		}
		catch (Exception e) {
			driverClass = "";
			//Se ho un'eccezione tento di recuperare il nome del driver dalla connessione
			try {
				nomeDriver = connection.getMetaData().getDriverName();
			}
			catch (Exception p) {
				nomeDriver = "";
			}
		}
		//Per ora drivertype 2 -> postgresql, 1 -> DB2
		//if (driverClass!= null && driverClass.compareTo("org.postgresql.Driver")==0) {
		if (driverClass.equals("org.postgresql.Driver") ||  nomeDriver.equals("PostgreSQL JDBC Driver")) {
			driverType = 2;
		}
		else {
			driverType = 1;
		}
		
		return driverType;
	}
	// Centralizzazione modifiche getConnection()
	public static boolean isPostgres(Connection connection) {
		return getDriverType(connection) == 2;
	}
	
}
