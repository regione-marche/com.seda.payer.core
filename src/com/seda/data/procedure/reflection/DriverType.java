package com.seda.data.procedure.reflection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverType {
	public static int getDriverType(Connection connection) {
		//RTC: Dalla connessione prendo il nome del driver,
		//se e' postgres faccio la lower
		String driverClass="";
		int driverType = -1;
		try {
			String originalURL = connection.getMetaData().getURL();
			Driver drv = DriverManager.getDriver(originalURL);
			driverClass = drv.getClass().getName();
		}
		catch (Exception e) {
			try {
				driverClass = connection.getMetaData().getDriverName().toLowerCase().contains("postgres")
					?"org.postgresql.Driver"
					:"";
			} catch (SQLException e1) {
				driverClass="";
			}
		}
		//Per ora drivertype 2 -> postgresql, 1 -> DB2
		if (driverClass!= null && driverClass.compareTo("org.postgresql.Driver")==0) {
			driverType = 2;
		}
		else {
			driverType = 1;
		}
		
		return driverType;
	}
}
