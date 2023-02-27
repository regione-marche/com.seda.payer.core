package com.seda.payer.core.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class DaoUtil {

	static final private Logger log = Logger.getLogger(DaoUtil.class);

	public static void closeConnection(final Connection con) {
		if (con != null) {
			 try {
				 con.close();
			 }
			 catch (SQLException ex) {
				log.error(con, ex);
			 }
		}
	}
		public static void closeStatement(final Statement stmt) {
			if (stmt != null) {
				 try {
					stmt.close();
				 }
				 catch (SQLException ex) {
					log.error(stmt, ex);
				 }
			}
		}

		public static void closeResultSet(final ResultSet rs) {
			if (rs != null) {
				 try {
					rs.close();
				 }
				 catch (SQLException ex) {
					log.error(rs, ex);
				 }
			}
		}
}
