package com.seda.payer.core.dao;

import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.Tassonomia;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class TassonomiaDao extends BaseDaoHandler {

	private Logger log = Logger.getLogger(TassonomiaDao.class);

	/**
	 * Default constructor
	 * @param connection
	 * @param schema
	 */
	public TassonomiaDao(Connection connection, String schema) {
		super(connection, schema);
		log.debug("TassonomiaDao: Dao avviato");
	}
	
	/**
	 * @param chiaveTassonomia
	 * @return
	 * @throws DaoException
	 */
	public Tassonomia doDetail(BigInteger chiaveTassonomia) throws DaoException {
		// we define callableStatement
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		
		log.debug("TassonomiaDao: \n\tOP: DETAIL \n\t ROUTINE: " + 
				Routines.PYTASSP_GET + "\n\tPARAM: chiaveTassonomia:" + chiaveTassonomia );
		try {
			// we prepare callableStatement
			callableStatement = prepareCall(Routines.PYTASSP_GET.routine());
			Tassonomia bean = new Tassonomia();
			bean.setChiaveTassonomia(chiaveTassonomia);
			// we invoke method load
			bean.load(callableStatement, Tassonomia.VIEW_SCOPE);
			//we execute callableStatement
			
			if (callableStatement.execute()) {
				System.out.println("TassonomiaDao: esecuzione chiamata db");
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next()){
					System.out.println("TassonomiaDao: Stai ritornando un valore");
					return new Tassonomia(data);
				}
			}
			return null;
		} catch (SQLException x) {
			System.out.println("errore1: " + x.getMessage());
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			System.out.println("errore2: " + x.getMessage());
			throw new DaoException(x);
		} catch (HelperException x) {
			System.out.println("errore3: " + x.getMessage());
			throw new DaoException(x);
		} finally {
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
					System.out.println("callableStatement.close eseguito su finally ");
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("callableStatement.close errore su finally ");
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	/**
	 * @param codiceTipoEnteCreditore
	 * @param progressivoMacroAreaPerEnteCreditore
	 * @param codiceTipologiaServizio
	 * @param motivoGiuridicoDellaRiscossione
	 * @return
	 * @throws DaoException
	 */
	public boolean isOverlay(BigInteger chiaveTassonomia, String codiceTipoEnteCreditore, String progressivoMacroAreaPerEnteCreditore,
			                 String codiceTipologiaServizio, String motivoGiuridicoDellaRiscossione) throws DaoException {
		// we define callableStatement
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try {
			// we prepare callableStatement
			callableStatement = prepareCall(Routines.PYTASSP_SEL.routine());
			Tassonomia bean = new Tassonomia();
			bean.setCodiceTipoEnteCreditore(codiceTipoEnteCreditore);
			bean.setProgressivoMacroAreaPerEnteCreditore(progressivoMacroAreaPerEnteCreditore);
			bean.setCodiceTipologiaServizio(codiceTipologiaServizio);
			bean.setMotivoGiuridicoDellaRiscossione(motivoGiuridicoDellaRiscossione);
			log.info(bean);
			System.out.println(bean);
			// we invoke method load
			bean.load(callableStatement, Tassonomia.OVERLAY_SCOPE);
			//we execute callableStatement
			if (callableStatement.execute()) {
				log.debug("isOverlay - start");
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next()) {
					if(chiaveTassonomia == null || chiaveTassonomia.equals(BigInteger.ZERO)) {
						log.debug("isOverlay - is found");
						return true;
					} else {
						Tassonomia check = new Tassonomia(data);
						if(!check.getChiaveTassonomia().equals(chiaveTassonomia)) {
							log.debug("isOverlay - is found");
							return true;
						}
					}
				}
			}
			return false;

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
	 * @param bean
	 * @param rowsPerPage
	 * @param pageNumber
	 * @param order
	 * @throws DaoException
	 */
	public void doWebRowSets(Tassonomia bean, int rowsPerPage, int pageNumber, String order) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			log.debug("TassonomiaDao: OP: doWebRowSets\n\tOBJ: " + bean.toString());
			callableStatement = prepareCall(Routines.PYTASSP_LST.routine());
			bean.load(callableStatement, rowsPerPage, pageNumber, order);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(18), callableStatement.getInt(19), 
								 callableStatement.getInt(20), callableStatement.getInt(21));
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
	public void doInsert(Tassonomia bean) throws DaoException {
		CallableStatement callableStatement = null;
		try {
			log.debug("TassonomiaDao: \n\tOP: INSERT \n\t ROUTINE: " + 
					Routines.PYTASSP_INS + "\n\tOBJ: " + bean.toString());
			callableStatement = prepareCall(Routines.PYTASSP_INS.routine());
			bean.save(callableStatement);
			callableStatement.executeUpdate();
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
	 * @throws DaoException
	 */
	public void doUpdate(Tassonomia bean) throws DaoException {
		CallableStatement callableStatement = null;
		log.debug("TassonomiaDao: \n\tOP: UPDATE \n\t ROUTINE: " + 
				Routines.PYTASSP_UPD + "\n\tOBJ: " + bean.toString());
		try {
			callableStatement = prepareCall(Routines.PYTASSP_UPD.routine());
			bean.update(callableStatement);
			callableStatement.executeUpdate();
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
	 * @param chiaveTemplate
	 * @return
	 * @throws DaoException
	 */
	public void doDelete(BigInteger chiaveTassonomia) throws DaoException {
		CallableStatement callableStatement = null;
		log.debug("TassonomiaDao: \n\tOP: DELETE \n\t ROUTINE: " + 
				Routines.PYTASSP_DEL);
		try {
			callableStatement = prepareCall(Routines.PYTASSP_DEL.routine());
			callableStatement.setInt(1, chiaveTassonomia.intValue());
			callableStatement.executeUpdate();
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
	 * @param callableStatement
	 */
	//inizio LP PG21XX04 Leak
	//private void closeCallableStatement(CallableStatement callableStatement) {
	//	if (callableStatement != null)
	//		DAOHelper.closeIgnoringException(callableStatement);
	//}
	//fine LP PG21XX04 Leak
}