package com.seda.payer.core.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;

public class DaoUtil {

	private static final LoggerWrapper log =  CustomLoggerManager.get(DaoUtil.class);

	public static void closeConnection(final Connection con) {
		if (con != null) {
			 try {
				 con.close();
			 }
			 catch (SQLException ex) {
				log.error(ex);
			 }
		}
	}
		public static void closeStatement(final Statement stmt) {
			if (stmt != null) {
				 try {
					stmt.close();
				 }
				 catch (SQLException ex) {
					log.error(ex);
				 }
			}
		}

		public static void closeResultSet(final ResultSet rs) {
			if (rs != null) {
				 try {
					rs.close();
				 }
				 catch (SQLException ex) {
					log.error(ex);
				 }
			}
		}
}
