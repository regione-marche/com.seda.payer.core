package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

//inizio LP PG21XX04 Leak
//import com.seda.data.dao.DAOHelper;
//fine LP PG21XX04 Leak
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.Configurazione;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
/**
 * @author aniello.labua
 */
public class ConfigurazioneDao extends BaseDaoHandler {

	private Logger log = Logger.getLogger(ConfigurazioneDao.class);
	/**
	 * Default constructor
	 * @param connection
	 * @param schema
	 */
	public ConfigurazioneDao(Connection connection, String schema) {
		super(connection, schema);
		log.debug("ConfigurazioneDao: Dao avviato");
	}
	
	/**
	 * @param bean
	 * @return
	 */
	public void doInsert(Configurazione bean) throws DaoException {
		CallableStatement callableStatement = null;
		try {
			log.debug("ConfiguazioneDao: \n\tOP: INSERT \n\t ROUTINE: " + Routines.INSERT_CONFIGURAZIONE + "\n\tOBJ: " + bean.toString());
			callableStatement = prepareCall(Routines.INSERT_CONFIGURAZIONE.routine());
			bean.save(callableStatement);
			callableStatement.executeUpdate();
			commit();

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
	 * @param bean
	 * @return
	 * @throws DaoException
	 */
	public void doUpdate(Configurazione bean) throws DaoException{
		CallableStatement callableStatement = null;
		log.debug("ConfigurazioneDao: \n\tOP: UPDATE \n\t ROUTINE: " + 
				Routines.UPDATE_CONFIGURAZIONE + "\n\tOBJ: " + bean.toString());
		try {
			callableStatement = prepareCall(Routines.UPDATE_CONFIGURAZIONE.routine());
			bean.update(callableStatement);			
			callableStatement.executeUpdate();
			commit();

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
	 * @param bean
	 * @return
	 * @throws DaoException
	 */
	public void doDelete(Configurazione bean) throws DaoException{
		CallableStatement callableStatement = null;
		try {
			callableStatement = prepareCall(Routines.DELETE_CONFIGURAZIONE.routine());
			bean.delete(callableStatement);
			callableStatement.executeUpdate();
			commit();

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
	 * @param codiceSocieta
	 * @param codiceUtente
	 * @param codiceEnte
	 * @return
	 * @throws DaoException
	 */
	public Configurazione doDetail(String codiceSocieta, String codiceUtente, String chiaveEnte) throws DaoException {
		// we define callableStatement
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
//		log.debug("ConfigurazioneDao: \n\tOP: DETAIL \n\t ROUTINE: " + 
//				  Routines.SELECT_CONFIGURAZIONE + "\n\tPARAM: COD_SOC:" + codiceSocieta + "\n\t\tCOD_UT: " + codiceUtente + "\n\t\tCOD_ENTE: " + chiaveEnte);
		try {
			// we prepare callableStatement
			callableStatement = prepareCall(Routines.SELECT_CONFIGURAZIONE.routine());
			// we invoke method load
			(new Configurazione(codiceSocieta, codiceUtente, chiaveEnte)).load(callableStatement);
			// we execute callableStatement
			if (callableStatement.execute()) {
				//log.debug("ConfigurazioneDao: esecuzione chiamata db");
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next()){
					//log.debug("ConfigurazioneDao: Stai ritornando un valore");
					return new Configurazione(data, Configurazione.DEFAULT_SCOPE);
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
	 * @throws DaoException
	 */
	public void doWebRowSets(Configurazione bean, int rowsPerPage, int pageNumber) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			//log.debug("ConfigurazioneDao: OP: doWebRowSets\n\tOBJ: " + bean.toString());
			callableStatement = prepareCall(Routines.SELECT_BY_FILTER_CONFIGURAZIONE.routine());
			bean.load(callableStatement, rowsPerPage, pageNumber, "");
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(9), callableStatement.getInt(10), 
						callableStatement.getInt(11), callableStatement.getInt(12));
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
	 * @return
	 * @throws DaoException
	 */
	public List<Configurazione> doList() throws DaoException {
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		List<Configurazione> list = null;
		try	{
			// we prepare callableStatement
			callableStatement = prepareCall(Routines.SELECT_ALL_CONFIGURAZIONE.routine());
			list = new ArrayList<Configurazione>();
			// we execute callableStatement
			if (callableStatement.execute()) {
				//log.debug("ConfigurazioneDao: esecuzione chiamata db");
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next()) {
					do {
						list.add(new Configurazione(data, Configurazione.PROCESS_FLOW_SCOPE));
					} while (data.next());
				}
				//log.debug("La lista ha: " + list.size() + " elementi");
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

	
	/**
	 * @param codiceSocieta
	 * @param codiceUtente
	 * @param codiceEnte
	 * @return
	 * @throws DaoException
	 */
	public Configurazione verificaConfigurazioneOttico(String codiceSocieta, String codiceUtente, String  codiceEnte) throws DaoException {
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//log.debug("Metodo verificaConfigurazioneOttico");
			callableStatement = prepareCall(Routines.VERIFICA_CONFIGURAZIONE.routine());
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setString(3, codiceEnte);	
			if (callableStatement.execute()) {
				//log.debug("ConfigurazioneDao: esecuzione chiamata db");
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next()){
				//	log.debug("ConfigurazioneDao: Stai ritornando un valore");
					return new Configurazione(data,Configurazione.DEFAULT_SCOPE);
				}
			}
		//	log.warn("ConfigurazioneDao: Stai ritornando null!!!");
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
	

	/**
	 * @param callableStatement
	 */
	//inizio LP PG21XX04 Leak
	//private void closeCallableStatement(CallableStatement callableStatement) {
	//	if (callableStatement != null)
	//		DAOHelper.closeIgnoringException(callableStatement);
	//}
	//fine LP PG21XX04 Leak
}