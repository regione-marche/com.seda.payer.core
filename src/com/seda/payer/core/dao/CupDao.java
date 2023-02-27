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
import com.seda.payer.core.bean.Cup;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
/**
 * @author aniello.labua
 */
public class CupDao extends BaseDaoHandler {

	private Logger log = Logger.getLogger(CupDao.class);
	/**
	 * Default constructor
	 * @param connection
	 * @param schema
	 */
	public CupDao(Connection connection, String schema) {
		super(connection, schema);
		log.debug("CupDao: Dao avviato");
	}
	
	/**
	 * @param bean
	 * @return
	 */
	public void doInsert(Cup bean) throws DaoException {
		CallableStatement callableStatement = null;
		try {
			log.debug("ConfiguazioneDao: \n\tOP: INSERT \n\t ROUTINE: " + Routines.PYCUPSP_INS + "\n\tOBJ: " + bean.toString());
			callableStatement = prepareCall(Routines.PYCUPSP_INS.routine());
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
	public void doUpdate(Cup bean) throws DaoException{
		CallableStatement callableStatement = null;
		log.debug("CupDao: \n\tOP: UPDATE \n\t ROUTINE: " + 
				Routines.PYCUPSP_UPD + "\n\tOBJ: " + bean.toString());
		try {
			callableStatement = prepareCall(Routines.PYCUPSP_UPD.routine());
			bean.update(callableStatement);			
			callableStatement.executeUpdate();
//			commit();

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
//	/**
//	 * @param bean
//	 * @return
//	 * @throws DaoException
//	 */
//	public void doDelete(Cup bean) throws DaoException{
//		CallableStatement callableStatement = null;
//		try {
//			callableStatement = prepareCall(Routines.DELETE_CONFIGURAZIONE.routine());
//			bean.delete(callableStatement);
//			callableStatement.executeUpdate();
//			commit();
//
//		} catch (SQLException x) {
//			rollback();
//			throw new DaoException(x);
//		} catch (IllegalArgumentException x) {
//			throw new DaoException(x);
//		} catch (HelperException x) {
//			throw new DaoException(x);
//		} finally {
//			closeCallableStatement(callableStatement);
//		}
//	}
	
	/**
	 * @param codiceSocieta
	 * @param codiceUtente
	 * @param codiceEnte
	 * @return
	 * @throws DaoException
	 */
	public Cup doDetail(String codiceFiscale, String codicePagamento) throws DaoException {
		// we define callableStatement
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		log.debug("CupDao: \n\tOP: DETAIL \n\t ROUTINE: " + 
				  Routines.PYCUPSP_SEL + "\n\tPARAM: codiceFiscale:" + codiceFiscale + "\n\t\tcodicePagamento: " + codicePagamento);
		try {
			// we prepare callableStatement
			callableStatement = prepareCall(Routines.PYCUPSP_SEL.routine());
			// we invoke method load
			(new Cup(codiceFiscale, codicePagamento)).load(callableStatement);
			// we execute callableStatement
			if (callableStatement.execute()) {
				log.debug("CupDao: esecuzione chiamata db");
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next()){
					log.debug("CupDao: Stai ritornando un valore");
					return new Cup(data);
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
	
//	/**
//	 * @param bean
//	 * @param rowsPerPage
//	 * @param pageNumber
//	 * @throws DaoException
//	 */
//	public void doWebRowSets(Cup bean, int rowsPerPage, int pageNumber) throws DaoException {
//		CallableStatement callableStatement = null;
//		try	{
//			log.debug("CupDao: doWebRowSets\n\tOBJ: " + bean.toString());
//			callableStatement = prepareCall(Routines.SELECT_BY_FILTER_CONFIGURAZIONE.routine());
//			bean.load(callableStatement, rowsPerPage, pageNumber, "");
//			/* we execute procedure */
//			if (callableStatement.execute()) {
//				this.loadWebRowSets(callableStatement);
//				/* we register page info */
//				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(9), callableStatement.getInt(10), 
//						callableStatement.getInt(11), callableStatement.getInt(12));
//			}
//		} catch (SQLException x) {
//			throw new DaoException(x);
//		} catch (IllegalArgumentException x) {
//			throw new DaoException(x);
//		} catch (HelperException x) {
//			throw new DaoException(x);
//		} finally {
//			closeCallableStatement(callableStatement);
//		}
//	}
	/**
	 * @return
	 * @throws DaoException
	 */
	public List<Cup> doList(Cup bean) throws DaoException {
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		List<Cup> list = null;
		try	{
			// we prepare callableStatement
			callableStatement = prepareCall(Routines.PYCUPSP_LST.routine());
			bean.update(callableStatement);
			list = new ArrayList<Cup>();
			// we execute callableStatement
			if (callableStatement.execute()) {
				log.debug("CupDao: esecuzione chiamata db");
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next()) {
					do {
						list.add(new Cup(data));
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

	
//	/**
//	 * @param codiceSocieta
//	 * @param codiceUtente
//	 * @param codiceEnte
//	 * @return
//	 * @throws DaoException
//	 */
//	public Configurazione verificaConfigurazioneOttico(String codiceSocieta, String codiceUtente, String  codiceEnte) throws DaoException {
//		CallableStatement callableStatement = null;
//		try	{
//			log.debug("Metodo verificaConfigurazioneOttico");
//			callableStatement = prepareCall(Routines.VERIFICA_CONFIGURAZIONE.routine());
//			callableStatement.setString(1, codiceSocieta);
//			callableStatement.setString(2, codiceUtente);
//			callableStatement.setString(3, codiceEnte);	
//			if (callableStatement.execute()) {
//				log.debug("CupDao: esecuzione chiamata db");
//				ResultSet data = callableStatement.getResultSet();
//				if (data.next()){
//					log.debug("CupDao: Stai ritornando un valore");
//					return new Configurazione(data,Configurazione.DEFAULT_SCOPE);
//				}
//			}
//			log.warn("CupDao: Stai ritornando null!!!");
//			return null;
//		} catch (SQLException x) {
//			throw new DaoException(x);
//		} catch (IllegalArgumentException x) {
//			throw new DaoException(x);
//		} catch (HelperException x) {
//			throw new DaoException(x);
//		} finally {
//			this.closeCallableStatement(callableStatement);
//		}
//	}
//	

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