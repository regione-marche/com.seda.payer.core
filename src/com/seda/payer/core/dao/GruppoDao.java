package com.seda.payer.core.dao;

import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.Gruppo;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class GruppoDao extends BaseDaoHandler {

	private static final LoggerWrapper log =  CustomLoggerManager.get(GruppoDao.class);

	/**
	 * Default constructor
	 * @param connection
	 * @param schema
	 */
	public GruppoDao(Connection connection, String schema) {
		super(connection, schema);
		log.debug("GruppoDao: Dao avviato");
	}
	
	/**
	 * @param chiaveGruppo
	 * @return
	 * @throws DaoException
	 */
	public Gruppo doDetail(BigInteger chiaveGruppo) throws DaoException {
		// we define callableStatement
		CallableStatement callableStatement = null;
		ResultSet data = null;
		
		log.debug("GruppoDao: \n\tOP: DETAIL \n\t ROUTINE: " + 
				Routines.PYGRPSP_GET + "\n\tPARAM: chiaveGruppo:" + chiaveGruppo );
		try {
			// we prepare callableStatement
			callableStatement = prepareCall(Routines.PYGRPSP_GET.routine());
			Gruppo bean = new Gruppo();
			bean.setChiaveGruppo(chiaveGruppo);
			// we invoke method load
			bean.load(callableStatement, Gruppo.VIEW_SCOPE);
			//we execute callableStatement
			
			if (callableStatement.execute()) {
				System.out.println("GruppoDao: esecuzione chiamata db");
				data = callableStatement.getResultSet();
				if (data.next()){
					System.out.println("GruppoDao: Stai ritornando un valore");
					return new Gruppo(data);
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
		}
	}
	
	/**
	 * @param codiceGruppo
	 * @return
	 * @throws DaoException
	 */
	public boolean isOverlay(BigInteger chiaveGruppo, String codiceGruppo) throws DaoException {
		// we define callableStatement
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			// we prepare callableStatement
			callableStatement = prepareCall(Routines.PYGRPSP_SEL.routine());
			Gruppo bean = new Gruppo();
			bean.setCodiceGruppo(codiceGruppo);
			log.info(bean.toString());
			System.out.println(bean);
			// we invoke method load
			bean.load(callableStatement, Gruppo.OVERLAY_SCOPE);
			//we execute callableStatement
			if (callableStatement.execute()) {
				log.debug("isOverlay - start");
				data = callableStatement.getResultSet();
				if (data.next()) {
					if(chiaveGruppo == null || chiaveGruppo.equals(BigInteger.ZERO)) {
						log.debug("isOverlay - is found");
						return true;
					} else {
						Gruppo check = new Gruppo(data);
						if(!check.getChiaveGruppo().equals(chiaveGruppo)) {
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
		}
	}
	/**
	 * @param bean
	 * @param rowsPerPage
	 * @param pageNumber
	 * @param order
	 * @throws DaoException
	 */
	public void doWebRowSets(Gruppo bean, int rowsPerPage, int pageNumber, String order) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			log.debug("GruppoDao: OP: doWebRowSets\n\tOBJ: " + bean.toString());
			callableStatement = prepareCall(Routines.PYGRPSP_LST.routine());
			bean.load(callableStatement, rowsPerPage, pageNumber, order);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(8), callableStatement.getInt(9), 
								 callableStatement.getInt(10), callableStatement.getInt(11));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * @param bean
	 * @return
	 */
	public void doInsert(Gruppo bean) throws DaoException {
		CallableStatement callableStatement = null;
		try {
			log.debug("GruppoDao: \n\tOP: INSERT \n\t ROUTINE: " + 
					Routines.PYGRPSP_INS + "\n\tOBJ: " + bean.toString());
			callableStatement = prepareCall(Routines.PYGRPSP_INS.routine());
			bean.save(callableStatement);
			callableStatement.executeUpdate();
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		//inizio LP 20240811  - PGNTCORE-24 	
		} catch (UndeclaredThrowableException x) {
			DaoException.makeIfDuplicateKeyError(x);
		//fine LP 20240811  - PGNTCORE-24 	
		} finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * @param bean
	 * @return
	 * @throws DaoException
	 */
	public void doUpdate(Gruppo bean) throws DaoException {
		CallableStatement callableStatement = null;
		log.debug("GruppoDao: \n\tOP: UPDATE \n\t ROUTINE: " + 
				Routines.PYGRPSP_UPD + "\n\tOBJ: " + bean.toString());
		try {
			callableStatement = prepareCall(Routines.PYGRPSP_UPD.routine());
			bean.update(callableStatement);
			callableStatement.executeUpdate();
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		//inizio LP 20240811  - PGNTCORE-24 	
		} catch (UndeclaredThrowableException x) {
			DaoException.makeIfDuplicateKeyError(x);
		//fine LP 20240811  - PGNTCORE-24 	
		} finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * @param chiaveGruppo
	 * @return
	 * @throws DaoException
	 */
	public void doDelete(BigInteger chiaveGruppo) throws DaoException {
		CallableStatement callableStatement = null;
		log.debug("GruppoDao: \n\tOP: DELETE \n\t ROUTINE: " + 
				Routines.PYGRPSP_DEL);
		try {
			callableStatement = prepareCall(Routines.PYGRPSP_DEL.routine());
			callableStatement.setInt(1, chiaveGruppo.intValue());
			callableStatement.executeUpdate();
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}