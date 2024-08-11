package com.seda.payer.core.dao;

import java.io.BufferedWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.UndeclaredThrowableException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.HelperException;
import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.TipologiaSoggetto;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;

public class TipologiaSoggettoDao extends BaseDaoHandler {
	
	public TipologiaSoggettoDao(Connection connection, String schema) {
		super(connection, schema);
	}
	
	//inizio LP PG21XX04 Leak
	//private void closeConnection(CallableStatement callableStatement)
	//{
	//	if (callableStatement != null)
	//		DAOHelper.closeIgnoringException(callableStatement);
	//}
	//fine LP PG21XX04 Leak
	
	private BufferedWriter getFilePath(String nomeFilePath) throws FileNotFoundException  {
		// TODO[AA]
		return  new BufferedWriter( new OutputStreamWriter( new FileOutputStream( new File(nomeFilePath) , true) )  );   // il true finale indica che siamo in append
	}
	
	public void doList_Belf(String codiceBelfiore) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
//		BufferedWriter filelog = null;
		try	
		{
//			try {
//				filelog = getFilePath("D:\\Applications\\Pagonet2\\Log\\logDAOWISMANAGER.log");
//			} catch (FileNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			callableStatement = prepareCall(Routines.SSG_DOLIST_BELF.routine());
			if (codiceBelfiore.length() > 4)
				callableStatement.setString(1, codiceBelfiore.substring(0, 4));
			else
				callableStatement.setString(1, codiceBelfiore.substring(0, 4));
			if (codiceBelfiore.length() > 4)
				callableStatement.setString(2, codiceBelfiore.substring(4));
			else
				callableStatement.setString(2, "N");
			
			
			if (callableStatement.execute())
			{
//				try {
//					filelog.write("inizio data = callableStatement.getResultSet();\r\n");
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				data = callableStatement.getResultSet();
				
//				try {
//					filelog.write("fine data = callableStatement.getResultSet();\r\n");
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				
//				while(data.next()) {
//					try {
//						filelog.write("RESULTSET POPOLATO PER = " + codiceBelfiore + "\r\n");
//						filelog.write("CODICE SOGGETTO = " + data.getString("SSG_KSSGKSSG") + "\r\n");
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
			
				
			
			}
			
			if (callableStatement.execute())
			{
//				try {
//					filelog.write("inizio this.loadWebRowSets(callableStatement);\r\n");
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				this.loadWebRowSets(callableStatement);
//				try {
//					filelog.write("fine this.loadWebRowSets(callableStatement);\r\n");
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
				
			
		} catch (SQLException x) {
//			try {
//				filelog.write("errore SQL = " + x.getMessage() + "\r\n");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
//			try {
//				filelog.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	public void doRowSets(TipologiaSoggetto tipologiaSoggetto,String descComune) throws DaoException {
		rowSets(tipologiaSoggetto, 0, 0,descComune);
	}

	public void doRowSets(TipologiaSoggetto tipologiaSoggetto, int rowsPerPage, int pageNumber,String descComune) throws DaoException {
		
			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
 
			rowSets(tipologiaSoggetto, rowsPerPage, pageNumber,descComune);

	}
	
	public void rowSets(TipologiaSoggetto tipologiaSoggetto, int rowsPerPage, int pageNumber,String descComune) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.SSG_DOLIST.routine());
			
			callableStatement.setString(1, descComune);
			callableStatement.setString(2, tipologiaSoggetto.getDescrizioneSoggetto());
			callableStatement.setString(3, tipologiaSoggetto.getFlagEsenzione());
			callableStatement.setInt(4, rowsPerPage);
			callableStatement.setInt(5, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(6, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(7, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(8, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(9, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(6), callableStatement.getInt(7), 
								 callableStatement.getInt(8), callableStatement.getInt(9));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		
	}
	public void doSave(TipologiaSoggetto tipologiaSogetto, String codOp) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			
			if (tipologiaSogetto.getCodiceBelfiore() == null)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("tipologiaSoggetto.comune"));

			if (tipologiaSogetto.getChiaveTipologiaSoggetto() == null)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("tipologiaSoggetto.chiaveTipoSogg"));

			
			if (tipologiaSogetto.getCodiceTipologiaSoggetti() == null)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("tipologiaSoggetto.codiceTipologiaSogg"));

			if (codOp.equals(TypeRequest.ADD_SCOPE.scope()))
			{
				callableStatement = prepareCall(Routines.SSG_DOINSERT.routine());
			}
			else if (codOp.equals(TypeRequest.EDIT_SCOPE.scope()))
			{
				callableStatement = prepareCall(Routines.SSG_DOUPDATE.routine());
			}
			else
			{
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("tipologiaSoggetto.saveadd.error"));
			}
			//21022011 GG fine
			tipologiaSogetto.save(callableStatement);
			callableStatement.execute();
			//commit();
		} catch (SQLException x) {
			if(x.getErrorCode()== -803){
				throw new DaoException(55,"esiste già una tipologia Soggetto per i parametri selezionati");
			}
			throw new DaoException(x);
		//inizio LP 20240811 - PGNTCORE-24
		} catch (UndeclaredThrowableException x) {
			DaoException.makeIfDuplicateKeyError(x, 55, "Esiste già una tipologia Soggetto per i parametri selezionati");
		//fine LP 20240811 - PGNTCORE-24
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}

	public void doDelete(TipologiaSoggetto tipologiaSogetto) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.SSG_DODELETE.routine());
			
			if (tipologiaSogetto.getCodiceBelfiore() == null)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("tipologiaSoggetto.comune"));

			if (tipologiaSogetto.getChiaveTipologiaSoggetto() == null)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("tipologiaSoggetto.chiaveTipoSogg"));

			callableStatement.setString(1, tipologiaSogetto.getChiaveTipologiaSoggetto());
			callableStatement.setString(2, tipologiaSogetto.getCodiceBelfiore());
			//callableStatement.registerOutParameter(3, Types.CHAR);
			callableStatement.execute();
			
		} catch (SQLException x) {
			if(x.getErrorCode()== -532){
				throw new DaoException(55,"Impossibile effettuare la cancellazione, sono presenti una o più informazioni correlate");
			}
			throw new DaoException(x.getErrorCode(),x.getMessage(),x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(Integer.parseInt(Messages.ILL_ARG_ERR_CODE.format()),x.getMessage(),x);
		} catch (HelperException x) {
			throw new DaoException(Integer.parseInt(Messages.HELPER_ERR_CODE.format()),x.getMessage(),x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	public TipologiaSoggetto doDetail(TipologiaSoggetto tipologiaSoggetto) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.SSG_DODETAIL.routine());
			callableStatement.setString(1, tipologiaSoggetto.getChiaveTipologiaSoggetto());
			callableStatement.setString(2, tipologiaSoggetto.getCodiceBelfiore());
			
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next())
					return new TipologiaSoggetto(data);
			}
			return null;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			//if (data != null)
			//	DAOHelper.closeIgnoringException(data);
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	public String doListCsv(TipologiaSoggetto tipologiaSoggetto, String descComune) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		
		try	{

			callableStatement = prepareCall(Routines.SSG_DOLIST_CSV.routine());
			
			callableStatement.setString(1, descComune);
			callableStatement.setString(2, tipologiaSoggetto.getDescrizioneSoggetto());
			callableStatement.setString(3, tipologiaSoggetto.getFlagEsenzione());

			StringBuffer sb = new StringBuffer();
			boolean resultsAvailable = callableStatement.execute();
			
			while (resultsAvailable) {
			
				data = callableStatement.getResultSet();
				
				while(data.next()) {
					sb.append(data.getString("RECORD"));
					sb.append("\r\n");
				}
				
				resultsAvailable = callableStatement.getMoreResults();
			}
			
			return sb.toString();
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			//if (data != null)
			//	DAOHelper.closeIgnoringException(data);
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}

}
