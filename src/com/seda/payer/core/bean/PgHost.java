package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.PagamentoHost;

public class PgHost implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codUte = "";
	private ArrayList<PagamentoHost> listPagamenti;
	
	public PgHost(CallableStatement cs) throws SQLException, DaoException {

		if (cs == null)
			return;
		
		ResultSet data = null;
		try{
			
			data = cs.getResultSet();
		    if (data != null)
		    {
		    	while (data.next())
		    	{
		    		if (listPagamenti == null)
		    			listPagamenti = new ArrayList<PagamentoHost>();
		    		
		    		listPagamenti.add(new PagamentoHost(data));
		    	}
		    }
		}
		catch(Exception e){
			e.printStackTrace();
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//if (data != null)
			//	DAOHelper.closeIgnoringException(data);
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return;
	}

	public PgHost() {
	}

		

	public void setListPagamenti(ArrayList<PagamentoHost> listPagamenti) {
		this.listPagamenti = listPagamenti;
	}

	public List<PagamentoHost> getListPagamenti() {
		return listPagamenti;
	}

}
