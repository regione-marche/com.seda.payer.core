package com.seda.payer.core.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.seda.commons.security.TokenGenerator;
//inizio LP PG21XX04 Leak
//import com.seda.data.dao.DAOHelper;
//fine LP PG21XX04 Leak
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.DettaglioFlussoOttico;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;


public class DettaglioFlussoOtticoDao extends BaseDaoHandler {

	private Logger log = Logger.getLogger(DettaglioFlussoOtticoDao.class);
	
	/**
	 * Default constructor
	 * @param connection
	 * @param schema
	 */
	public DettaglioFlussoOtticoDao(Connection connection, String schema) {
		super(connection, schema);
	}
	
	public Boolean doInsert(DettaglioFlussoOttico bean) throws DaoException{
		CallableStatement callableStatement = null; 
		try {
			bean.setChiaveDettaglioFlussoOttico((TokenGenerator.generateUUIDToken()));
			log.debug("DettaglioFlussoFlussoOtticoDao: \n\tOP: INSERT \n\t ROUTINE: " + 
					Routines.INSERT_DETTAGLIO_FLUSSO_OTTICO + "\n\tOBJ: " + bean.toString());
			
			callableStatement = prepareCall(Routines.INSERT_DETTAGLIO_FLUSSO_OTTICO.routine());
			bean.save(callableStatement);
			boolean result = callableStatement.execute();
			//commit();
			return result;
			
		} catch (SQLException x) {
			//rollback();
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} catch (NoSuchAlgorithmException e) {
			throw new DaoException(e);
		}  finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
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
	
	public Boolean doUpdate(DettaglioFlussoOttico bean) throws DaoException{
		CallableStatement callableStatement = null; 
		try {
			log.debug("DettaglioFlussoFlussoOtticoDao: \n\tOP: UPDATE \n\t ROUTINE: " + 
					Routines.UPDATE_DETTAGLIO_FLUSSO_OTTICO + "\n\tOBJ: " + bean.toString());
			
			callableStatement = prepareCall(Routines.UPDATE_DETTAGLIO_FLUSSO_OTTICO.routine());
			bean.update(callableStatement);
			callableStatement.registerOutParameter(18, Types.INTEGER);
			callableStatement.execute();
			int rowsUpd = callableStatement.getInt(18);
			commit();
			return rowsUpd == 1 ? true : false;
			
		} catch (SQLException x) {
			//rollback();
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}  finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
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
	
	/**
	 * 
	 * @return
	 * @throws DaoException
	 */
	public DettaglioFlussoOttico doDetail() throws DaoException {
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{	
			callableStatement =  prepareCall(Routines.SELECT_DETTAGLIO_FLUSSO_OTTICO.routine());
		
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new DettaglioFlussoOttico(data, DettaglioFlussoOttico.DEFAULT_SCOPE);
			}
			return null;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
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
	
	/*MOD*/
	/**
	 * 
	 * @param codiceSocieta
	 * @param codiceUtente
	 * @param codiceEnte
	 * @param documento
	 * @param tipoImmagine
	 * @return
	 * @throws DaoException
	 */
	public List<DettaglioFlussoOttico> doList(String codiceSocieta, String codiceUtente, String codiceEnte,
			 String documento, String tipoImmagine, String codiceFiscale, String codiceImpostaServizio) throws DaoException {
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		List<DettaglioFlussoOttico> list = null;
		try	{
			// we prepare callableStatement
			callableStatement = prepareCall(Routines.SELECT_ALL_DETTAGLIO_FLUSSO_OTTICO.routine());
			/**/
			System.out.println("codiceSocieta = " + codiceSocieta);
			System.out.println("codiceUtente = " + codiceUtente);
			System.out.println("codiceEnte =" +  codiceEnte);
			System.out.println("documento =" + documento);
			System.out.println("tipoImmagine =" + tipoImmagine);
			System.out.println("codiceFiscale =" + codiceFiscale);
			System.out.println("codiceImpostaServizio =" + codiceImpostaServizio);
			
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setString(3, codiceEnte);
			callableStatement.setString(4, documento);
			callableStatement.setString(5, tipoImmagine);
			callableStatement.setString(6, codiceFiscale);
			callableStatement.setString(7, codiceImpostaServizio);
			
			/**/
			list = new ArrayList<DettaglioFlussoOttico>();
			// we execute callableStatement
			log.debug("PRE DettaglioFlussoOtticoDao: esecuzione chiamata db");
			if (callableStatement.execute()) {
				log.debug("DettaglioFlussoOtticoDao: esecuzione chiamata db");
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next()) {
					do {
							list.add(new DettaglioFlussoOttico(data, DettaglioFlussoOttico.PROCESS_FLOW_SCOPE));
					
					} while (data.next());
				}
				log.debug("La lista ha: " + list.size() + " elementi");
				
				if (list.size() > 1)
					return null;
				else
					return list; 
			}
		} catch (SQLException x) {
			x.printStackTrace();
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
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
		
		return list;
	}

	public List<DettaglioFlussoOttico> doListDoc(String codiceSocieta, String codiceUtente,String flusso) throws DaoException {
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		List<DettaglioFlussoOttico> list = null;
		try	{
			// we prepare callableStatement
			callableStatement = prepareCall(Routines.SELECT_ALL_DETTAGLIO_DOC_FLUSSO_OTTICO.routine());
			/**/
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setString(3, flusso);
			/**/
			list = new ArrayList<DettaglioFlussoOttico>();
			// we execute callableStatement
			if (callableStatement.execute()) {
				log.debug("DettaglioFlussoOtticoDao: esecuzione chiamata db");
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next()) {
					do {
							list.add(new DettaglioFlussoOttico(data, DettaglioFlussoOttico.DEFAULT_SCOPE));
					
					} while (data.next());
				}
				log.debug("La lista ha: " + list.size() + " elementi");
				return list; 
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
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
		return list;
	}

	//inizio LP PG21XX04 Leak
	//private void closeCallableStatement(CallableStatement callableStatement)
	//{
	//	if (callableStatement != null)
	//		DAOHelper.closeIgnoringException(callableStatement);
	//}
	//fine LP PG21XX04 Leak
}
