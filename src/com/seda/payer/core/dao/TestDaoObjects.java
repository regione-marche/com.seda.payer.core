package com.seda.payer.core.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seda.payer.core.bean.Menu;

public class TestDaoObjects {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = null;
		try {
			con = getConnection();
/*			WebRowSetImpl listaFlussi = null;
			WebRowSetImpl listaFlussiGrouped = null;			
			GatewayPagamentoDao gateway = new GatewayPagamentoDao(getConnection(),"SE000SV");
			String listaGateway = gateway.getListaGatewayXml("00004", "", "");
		    System.out.println(listaGateway);
*/			
			MenuDao md = new MenuDao(con,"SE000SV");
			Menu menu = md.getMenu(new Long(1));
			System.out.println(menu.getMenuLivelloUno());
			System.out.println(menu.getMenuLivelloDue());
			System.out.println(menu.getMenuLivelloTre());
			//inizio LP PG21XX04 Leak
			//closeConnection(con);
			//fine LP PG21XX04 Leak

		} catch (Exception e) {
			e.printStackTrace();
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fineo LP PG21XX04 Leak
	}
		public static Connection getConnection() {
			Connection connection = null;
			try {
				String driverName = "com.ibm.db2.jcc.DB2Driver";
				Class.forName(driverName);
				String url = "jdbc:db2://10.10.9.52:50000/PAY00DB0"; 
				String username = "SE000SV";
				String password = "SVSVIL09$";
				connection = DriverManager.getConnection(url, username, password);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return connection;

		}
		
		public static void closeConnection(Connection connection) {
			try {
				connection.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}






