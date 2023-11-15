package com.seda.payer.core.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.commons.security.TokenGenerator;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.TestataFlussoOttico;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
/**
 * @author aniello.labua
 */
public class TestataFlussoOtticoDao extends  BaseDaoHandler{
	
	private static final LoggerWrapper log =  CustomLoggerManager.get(TestataFlussoOtticoDao.class);
	/**
	 * Default constructor
	 * @param connection
	 * @param schema
	 */
	public TestataFlussoOtticoDao(Connection connection, String schema) {
		super(connection, schema);
		log.debug("TestataFlussoOtticoDao: Dao avviato");
	}
	
	/**
	 * 
	 * @param bean
	 * @return
	 * @throws DaoException
	 */
	public Boolean doInsert(TestataFlussoOttico bean) throws DaoException{
		CallableStatement callableStatement = null; 
		try {
			bean.setChiaveFlusso(TokenGenerator.generateUUIDToken());
			log.debug("TestataFlussoOtticoDao: \n\tOP: INSERT \n\t ROUTINE: " + 
					Routines.INSERT_CONFIGURAZIONE_FLUSSO + "\n\tOBJ: " + bean.toString());

			callableStatement = prepareCall(Routines.INSERT_CONFIGURAZIONE_FLUSSO.routine());
			bean.save(callableStatement);
			boolean result = callableStatement.execute();
			//commit();
			return result;

		} catch (SQLException x) {
			rollback();
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} catch (NoSuchAlgorithmException e) {
			throw new DaoException(e);
		} finally {
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
	 * @param bean
	 * @return
	 * @throws DaoException
	 */
	public boolean doUpdate(TestataFlussoOttico bean) throws DaoException{
		CallableStatement callableStatement = null;
		
		try {
			log.debug("TestataFlussoFlussoOtticoDao: \n\tOP: UPDATE \n\t ROUTINE: " + 
					Routines.UPDATE_CONFIGURAZIONE_FLUSSO + "\n\tOBJ: " + bean.toString());
			
			callableStatement = prepareCall(Routines.UPDATE_CONFIGURAZIONE_FLUSSO.routine());
			bean.update(callableStatement);
			
      callableStatement.registerOutParameter(18, Types.INTEGER); //PG190360
      callableStatement.execute();
      int rowsUpd = callableStatement.getInt(18); //PG190360
			commit();
			return rowsUpd == 1 ? true : false;
			
		} catch (SQLException x) {
			rollback();
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
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
	 * @param bean
	 * @return
	 * @throws DaoException
	 */
	public boolean doDelete(TestataFlussoOttico bean) throws DaoException{
		CallableStatement callableStatement = null;
		
		try {
			log.debug("TestataFlussoOtticoDao: \n\tOP: DELETE");
			callableStatement = prepareCall(Routines.DELETE_CONFIGURAZIONE_FLUSSO.routine());
			boolean result = callableStatement.execute();
			commit();
			return result;
			
		} catch (SQLException x) {
			rollback();
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
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

	public TestataFlussoOttico doDetail(String codiceSocieta, String codiceUtente, String codiceEnte, String nomeFileDati) throws DaoException{
		
		// we define callableStatement
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		log.debug("TestataFlussoOtticoDao: \n\tOP: DETAIL \n\t ROUTINE: " + 
				  Routines.SELECT_CONFIGURAZIONE_FLUSSO + "\n\tPARAM: COD_SOC:" + codiceSocieta + "\n\t\tCOD_UT: " + codiceUtente + "\n\t\tCOD_ENTE: " + codiceEnte + "\n\t\tNOME DATI: " + nomeFileDati );
		try {
			// we prepare callableStatement
			callableStatement = prepareCall(Routines.SELECT_CONFIGURAZIONE_FLUSSO.routine());
			// we invoke method load
			(new TestataFlussoOttico(codiceSocieta, codiceUtente, codiceEnte,nomeFileDati)).load(callableStatement);
			// we execute callableStatement
			if (callableStatement.execute()) {
				log.debug("TestataFlussoOtticoDao: esecuzione chiamata db");
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next()){
					log.debug("TestataFlussoOtticoDao: Stai ritornando un valore");
					return new TestataFlussoOttico(data);
				}
			}
			return null;

		} catch (SQLException x) {
			rollback();
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
	/**
	 * @param bean
	 * @param rowsPerPage
	 * @param pageNumber
	 * @param order
	 * @throws DaoException
	 */
	public void doWebRowSets(TestataFlussoOttico bean, int rowsPerPage, int pageNumber, String order) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			log.debug("TestataFlussoOtticoDao: OP: doWebRowSets\n\tOBJ: " + bean.toString());
			callableStatement = prepareCall(Routines.SELECT_BY_FILTER_CONFIGURAZIONE_FLUSSO.routine());
			bean.load(callableStatement, rowsPerPage, pageNumber, order);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				// PG22XX09_YL5
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(14), callableStatement.getInt(15), 
								 callableStatement.getInt(16), callableStatement.getInt(17));
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
	 * @param codiceSocieta
	 * @param codiceUtente
	 * @param codiceEnte
	 * @param nomeFileDati
	 * @return
	 * @throws DaoException
	 */
	public TestataFlussoOttico verificaFlusso(String codiceSocieta, String codiceUtente, String  codiceEnte, String nomeFileDati) throws DaoException {
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			log.debug("TestataFlussoOtticoDao:\n\tOP: verificaFlussoOttico");
			callableStatement = prepareCall(Routines.SELECT_CONFIGURAZIONE_FLUSSO.routine());

			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setString(3, codiceEnte);	
			callableStatement.setString(4, nomeFileDati);	

			if (callableStatement.execute()) {
				log.debug("TestataFlussoOtticoDao: esecuzione chiamata db");
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next()){
					log.debug("TestataFlussoOtticoDao: Stai ritornando un valore");
					return new TestataFlussoOttico(data);
				}
			}
			log.warn("TestataFlussoOtticoDao: Stai ritornando null!!!");
			return null;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}finally{
			//inizio LP PG21XX04 Leak
			//this.closeCallableStatement(callableStatement);
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
	//inizio LP PG21XX04 Leak
	//private void closeCallableStatement(CallableStatement callableStatement)
	//{
	//	if (callableStatement != null)
	//		DAOHelper.closeIgnoringException(callableStatement);
	//}
	//fine LP PG21XX04 Leak
}