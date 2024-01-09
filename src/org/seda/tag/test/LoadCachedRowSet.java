package org.seda.tag.test;

import java.io.IOException;
import java.math.BigDecimal;

import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import javax.sql.rowset.RowSetMetaDataImpl;

import com.seda.commons.string.Convert;

//import com.seda.data.spi.PageInfo;

import com.seda.j2ee5.servlet.spi.ServletHandler;
import com.sun.rowset.WebRowSetImpl;

/**
 * Servlet implementation class LoadCachedRowSet
 */
public class LoadCachedRowSet extends ServletHandler {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadCachedRowSet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(request, response);
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	super.doPost(request, response);
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// l'esempio deve essere completato utilizzando un oggetto PageInfo o PageMetaData
		// creato dinamicamente senza avere riferimetni diretti alla versione del framework data
		
		WebRowSetImpl rowSet=null;
		
//		PageInfo pInfo = new PageInfo();
		try {
			//connection = jndiProxy().getSqlConnection("sample52", true);
		//	statement = new HelperStatement(connection,"SELECT * FROM TAGTABLE");
		//	statement.execute();			
			rowSet = new WebRowSetImpl();
		//	rowSet.populate(statement.getResultSet());
			RowSetMetaDataImpl rsMdData = new RowSetMetaDataImpl();
			
			rsMdData.setColumnCount(3);
		    rsMdData.setColumnName(1, "id");
			rsMdData.setColumnType(1, Types.VARCHAR);
			rsMdData.setColumnName(2, "name");
			rsMdData.setColumnType(2, Types.VARCHAR);
			rsMdData.setColumnName(3, "importo");
			rsMdData.setColumnType(3, Types.DECIMAL);
			
			rowSet.setMetaData(rsMdData);
			

			rowSet.moveToInsertRow();
			rowSet.updateString(1, "1111");
			rowSet.updateString(2, "alex");
			rowSet.updateBigDecimal(3, new BigDecimal("15.25"));
			rowSet.insertRow();

			
			rowSet.updateString(1, "2222");
			rowSet.updateString(2, "jane");
			rowSet.updateBigDecimal(3, new BigDecimal("8237.64"));
			rowSet.insertRow();
			
			
			rowSet.moveToCurrentRow();
			rowSet.beforeFirst();
			
			request.setAttribute("rowSet", Convert.webRowSetToString(rowSet));
//			pInfo.setFirstRow(1);
//			pInfo.setLastRow(2);
//			pInfo.setNumPages(1);
//			pInfo.setNumRows(2);
//			pInfo.setPageNumber(1);
//			pInfo.setRowsPerPage(10);
			
		//	request.setAttribute("entegestione","S");
//			request.setAttribute("rowSet.pageInfo", pInfo);
			request.setAttribute("rowSet.pageInfo", null);
			//request.setAttribute("rowSetddl", Convert.webRowSetToString(rowSet));
			
			Calendar calendar=Calendar.getInstance();
			calendar.set(1970, 0, 1);
			
			request.setAttribute("tx_data_da", calendar);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Default.jsp");
		dispatcher.forward(request, response);
	}
}
