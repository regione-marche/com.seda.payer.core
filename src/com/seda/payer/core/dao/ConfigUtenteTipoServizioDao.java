package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.commons.utility.StringUtility;
import com.seda.payer.core.bean.ConfigUtenteTipoServizio;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;
import com.seda.data.helper.HelperException;
/**
 * 
 * @author mmontisano
 *
 */
public class ConfigUtenteTipoServizioDao extends BaseDaoHandler {
	
	public ConfigUtenteTipoServizioDao(Connection connection, String schema) {
		super(connection, schema);
	}
 
	public ConfigUtenteTipoServizio doDetail(String companyCode, String userCode, String codiceTipoServizio) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CFS_DODETAIL.routine());
			callableStatement = prepareCall(Routines.CFS_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, companyCode);
			callableStatement.setString(2, userCode);
			callableStatement.setString(3, codiceTipoServizio);			
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new ConfigUtenteTipoServizio(data);
			}
			return null;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
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
		//fine LP PG21XX04 Leak
	}

	public void doRowSets(ConfigUtenteTipoServizio config, String strDescrSocieta, String strDescrUtente, String strDescrTipologiaServizio) throws DaoException {
		rowSets(config, 0, 0, strDescrSocieta, strDescrUtente, strDescrTipologiaServizio);
	}

	public void doRowSets(ConfigUtenteTipoServizio config, int rowsPerPage, int pageNumber,String strDescrSocieta,String strDescrUtente,String strDescrTipologiaServizio) throws DaoException {

			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
 
			rowSets(config, rowsPerPage, pageNumber,strDescrSocieta,strDescrUtente,strDescrTipologiaServizio);
	}

	public void rowSets(ConfigUtenteTipoServizio config, int rowsPerPage, int pageNumber,String strDescrSocieta,String strDescrUtente,String strDescrTipologiaServizio) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CFS_DOLIST.routine());
			callableStatement = prepareCall(Routines.CFS_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, config.getUser().getCompany().getCompanyCode());
			callableStatement.setString(2, config.getUser().getUserCode());
			callableStatement.setString(3, config.getTipoServizio().getCodiceTipologiaServizio());
			callableStatement.setString(4, strDescrSocieta);
			callableStatement.setString(5, strDescrUtente);
			callableStatement.setString(6, strDescrTipologiaServizio);
			callableStatement.setString(7, "");
			callableStatement.setInt(8, rowsPerPage);
			callableStatement.setInt(9, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(10, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(11, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(12, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(13, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(10), callableStatement.getInt(11), 
								 callableStatement.getInt(12), callableStatement.getInt(13));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}

	public void doRowSets2(String procName, String companyCode, String codiceUtente) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{  
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = 
			//	prepareCall(!StringUtility.isEmpty(procName) ? procName : Routines.CFS_DOLIST_INS_BASE.routine());
			callableStatement = prepareCall(!StringUtility.isEmpty(procName) ? procName : Routines.CFS_DOLIST_INS_BASE.routine());
			//fine LP PG21XX04 Leak
	        callableStatement.setString(1, companyCode);
	        callableStatement.setString(2, codiceUtente);

	        /* we execute procedure */
			if (callableStatement.execute())  
				this.loadWebRowSets(callableStatement);
		} catch (SQLException x) { throw new DaoException(x);
		} catch (IllegalArgumentException x) { throw new DaoException(x);
		} catch (HelperException x) { throw new DaoException(x); }
		//inizio LP PG21XX04 Leak
		finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}
	
	public void doRowSets3(String companyCode) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{  
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = 
			//	prepareCall (Routines.CFS_DOLIST_INS_BASE2.routine());
			callableStatement = prepareCall(Routines.CFS_DOLIST_INS_BASE2.routine());
			//fine LP PG21XX04 Leak
	        callableStatement.setString(1, companyCode);
	        /* we execute procedure */
			if (callableStatement.execute())  
				this.loadWebRowSets(callableStatement);
		} catch (SQLException x) { throw new DaoException(x);
		} catch (IllegalArgumentException x) { throw new DaoException(x);
		} catch (HelperException x) { throw new DaoException(x); }
		//inizio LP PG21XX04 Leak
		finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}

	public void doSave(ConfigUtenteTipoServizio config,String codOp) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			if ((config.getUser() == null || config.getUser().getUserCode() == null || config.getUser().getUserCode().length() == 0))
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("config.user.userCode"));
			if ((                            config.getUser().getCompany() == null || config.getUser().getCompany().getCompanyCode() == null || config.getUser().getCompany().getCompanyCode().length() == 0))
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("config.user.companyCode"));
			if ((config.getTipoServizio() == null || config.getTipoServizio().getCodiceTipologiaServizio() == null || config.getTipoServizio().getCodiceTipologiaServizio().length() == 0))
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("config.tipologiaServizio.codiceTipologiaServizio"));
			ConfigUtenteTipoServizio data = doDetail(config.getUser().getCompany().getCompanyCode(),config.getUser().getUserCode(),config.getTipoServizio().getCodiceTipologiaServizio());
			if ((data != null) && codOp!=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0) throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configUtenteTipoServizio.saveadd.error"));
			if (data != null) 
				callableStatement = prepareCall(Routines.CFS_DOUPDATE.routine());
			else 
				callableStatement = prepareCall(Routines.CFS_DOINSERT.routine());
			config.save(callableStatement);
			callableStatement.execute();
			//commit();
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}

	public void doDelete(ConfigUtenteTipoServizio config) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CFS_DODELETE.routine());
			callableStatement = prepareCall(Routines.CFS_DODELETE.routine());
			//fine LP PG21XX04 Leak
			if (config.getUser().getUserCode() == null || config.getUser().getUserCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("ente.userCode"));

			if (config.getUser().getCompany() == null || config.getUser().getCompany().getCompanyCode() == null || config.getUser().getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("ente.user.company.companyCode"));

			callableStatement.setString(1, config.getUser().getCompany().getCompanyCode());
			callableStatement.setString(2, config.getUser().getUserCode());
			callableStatement.setString(3, config.getTipoServizio().getCodiceTipologiaServizio());
			callableStatement.execute();
			//commit();
		} catch (SQLException x) {
			throw new DaoException(x.getErrorCode(),x.getMessage(),x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(Integer.parseInt(Messages.ILL_ARG_ERR_CODE.format()),x.getMessage(),x);
		} catch (HelperException x) {
			throw new DaoException(Integer.parseInt(Messages.HELPER_ERR_CODE.format()),x.getMessage(),x);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}
}