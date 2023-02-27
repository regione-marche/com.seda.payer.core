package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.apache.log4j.Logger;
import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.TemplateDocumento;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
/**
 * @author aniello.labua
 */
public class TemplateDocumentoDao extends BaseDaoHandler {

	private Logger log = Logger.getLogger(TemplateDocumentoDao.class);
	/**
	 * Default constructor
	 * @param connection
	 * @param schema
	 */
	public TemplateDocumentoDao(Connection connection, String schema) {
		super(connection, schema);
		log.debug("TemplateDocumentoDao: Dao avviato");
	}
	/**
	 * @param tipoDocumento
	 * @param codiceSocieta
	 * @param codiceUtente
	 * @param codiceEnte
	 * @param tipologiaServizio
	 * @param tipoBollettino
	 * @param dataInizio
	 * @param dataFine
	 * @param chiaveTemplate
	 * @return
	 * @throws DaoException
	 */
	public TemplateDocumento doDetail(String tipoDocumento, String codiceSocieta, String codiceUtente, String codiceEnte, 
									  String tipologiaServizio, String tipoBollettino, Timestamp dataInizio, Timestamp dataFine, 
									  String chiaveTemplate) throws DaoException {
		// we define callableStatement
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
//		log.debug("TemplateDocumentoDao: \n\tOP: DETAIL \n\t ROUTINE: " + 
//				Routines.SELECT_TEMPLATE_DOC+ "\n\tPARAM: CHI_TEMP:" + chiaveTemplate );
		try {
			// we prepare callableStatement
			callableStatement = prepareCall(Routines.SELECT_TEMPLATE_DOC.routine());
			TemplateDocumento bean = new TemplateDocumento(); {
				bean.setTipoDocumento(tipoDocumento);
				bean.setCodiceSocieta(codiceSocieta);
				bean.setCodiceUtente(codiceUtente);
				bean.setCodiceEnte(codiceEnte);
				bean.setTipologiaServizio(tipologiaServizio);
				bean.setTipoBollettino(tipoBollettino);
				bean.setDataInizio(dataInizio);
				bean.setDataFine(dataFine);
				bean.setChiaveTemplate(chiaveTemplate);
			}
			// we invoke method load
			bean.load(callableStatement, TemplateDocumento.VIEW_SCOPE);
			//we execute callableStatement
			
			if (callableStatement.execute()) {
				//System.out.println("TemplateDocumentoDao: esecuzione chiamata db");
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next()){
					//System.out.println("TemplateDocumentoDao: Stai ritornando un valore");
					return new TemplateDocumento(data);
				}
			}
			return null;

		} catch (SQLException x) {
			//inizio LP PG21XX04 Bug "You cannot commit with autocommit set" 
			//rollback();
			//fine LP PG21XX04 Bug "You cannot commit with autocommit set" 
			System.out.println("errore1: " + x.getMessage());
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			System.out.println("errore2: " + x.getMessage());
			throw new DaoException(x);
		} catch (HelperException x) {
			System.out.println("errore3: " + x.getMessage());
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Bug "You cannot commit with autocommit set" 
			//commit();
			//System.out.println("commit eseguita");
			//fine LP PG21XX04 Bug "You cannot commit with autocommit set" 
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
			//System.out.println("closeCallableStatement(callableStatement) eseguito su finally ");
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
					//System.out.println("callableStatement.close eseguito su finally ");
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("callableStatement.close errore su finally ");
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	/**
	 * @param tipoDocumento
	 * @param codiceSocieta
	 * @param codiceUtente
	 * @param codiceEnte
	 * @param tipologiaServizio
	 * @param dataInizio
	 * @param dataFine
	 * @param chiaveTemplate
	 * @return
	 * @throws DaoException
	 */
	public boolean isOverlay(String tipoDocumento, String codiceSocieta, String codiceUtente,String codiceEnte, 
							 String tipologiaServizio, Timestamp dataInizio, Timestamp dataFine, String chiaveTemplate) throws DaoException {
		// we define callableStatement
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try {
			// we prepare callableStatement
			callableStatement = prepareCall(Routines.SELECT_TEMPLATE_DOC_OVERLAY.routine());
			TemplateDocumento bean = new TemplateDocumento(); {
				bean.setTipoDocumento(tipoDocumento);
				bean.setCodiceSocieta(codiceSocieta);
				bean.setCodiceUtente(codiceUtente);
				bean.setCodiceEnte(codiceEnte);
				bean.setTipologiaServizio(tipologiaServizio);
				bean.setDataInizio(dataInizio);
				bean.setDataFine(dataFine);
				bean.setChiaveTemplate(chiaveTemplate);
			}
			log.info(bean);
			System.out.println(bean);
			// we invoke method load
			bean.load(callableStatement, TemplateDocumento.OVERLAY_SCOPE);
			//we execute callableStatement
			if (callableStatement.execute()) {
				log.debug("isOverlay - start");
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next()) {
					log.debug("isOverlay - is found");
					return true;
				}
			}
			return false;

		} catch (SQLException x) {
			//inizio LP PG21XX04 Bug "You cannot commit with autocommit set" 
			//rollback();
			//fine LP PG21XX04 Bug "You cannot commit with autocommit set" 
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
	public void doWebRowSets(TemplateDocumento bean, int rowsPerPage, int pageNumber) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			log.debug("TemplateDocumentoDao: OP: doWebRowSets\n\tOBJ: " + bean.toString());
			callableStatement = prepareCall(Routines.SELECT_BY_FILTER_TEMPLATE_DOC.routine());
			bean.load(callableStatement, rowsPerPage, pageNumber, "");
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(12), callableStatement.getInt(13), 
								 callableStatement.getInt(14), callableStatement.getInt(15));
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
	 * @param bean
	 * @return
	 */
	public void doInsert(TemplateDocumento bean) throws DaoException {
		CallableStatement callableStatement = null;
		try {
			log.debug("TemplateDocumentoDao: \n\tOP: INSERT \n\t ROUTINE: " + 
					Routines.INSERT_TEMPLATE_DOC + "\n\tOBJ: " + bean.toString());
			callableStatement = prepareCall(Routines.INSERT_TEMPLATE_DOC.routine());
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
	public void doUpdate(TemplateDocumento bean) throws DaoException {
		CallableStatement callableStatement = null;
		log.debug("TemplateDocumentoDao: \n\tOP: UPDATE \n\t ROUTINE: " + 
				Routines.UPDATE_TEMPLATE_DOC + "\n\tOBJ: " + bean.toString());
		try {
			callableStatement = prepareCall(Routines.UPDATE_TEMPLATE_DOC.routine());
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
	 * @param chiaveTemplate
	 * @return
	 * @throws DaoException
	 */
	public void doDelete(String chiaveTemplate) throws DaoException {
		CallableStatement callableStatement = null;
		log.debug("TemplateDocumentoDao: \n\tOP: DELETE \n\t ROUTINE: " + 
				Routines.INSERT_TEMPLATE_DOC);
		try {
			callableStatement = prepareCall(Routines.DELETE_TEMPLATE_DOC.routine());
			callableStatement.setString(1, chiaveTemplate);
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
	 * @param callableStatement
	 */
	//inizio LP PG21XX04 Leak
	//private void closeCallableStatement(CallableStatement callableStatement) {
	//	if (callableStatement != null)
	//		DAOHelper.closeIgnoringException(callableStatement);
	//}
	//fine LP PG21XX04 Leak
}