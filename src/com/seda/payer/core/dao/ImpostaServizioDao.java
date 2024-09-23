package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;

import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.ImpostaServizio;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;
import com.seda.data.helper.HelperException;
/**
 * 
 * @author mmontisano
 *
 */
public class ImpostaServizioDao extends BaseDaoHandler {
	
	static SimpleDateFormat sdfIso = new SimpleDateFormat("yyyy-MM-dd");
	
	public ImpostaServizioDao(Connection connection, String schema) {
		super(connection, schema);
	}

//	private void closeConnection(CallableStatement callableStatement)
//	{
//		if (callableStatement != null)
//			DAOHelper.closeIgnoringException(callableStatement);
//	}
	
	public ImpostaServizio doDetail(String companyCode, String codiceTipologiaServizio, String codiceImpostaServizio) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.ISE_DODETAIL.routine());
			callableStatement = prepareCall(Routines.ISE_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, companyCode);
			callableStatement.setString(2, codiceTipologiaServizio);
			callableStatement.setString(3, codiceImpostaServizio);			
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new ImpostaServizio(data);
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

	public void doRowSets(ImpostaServizio impostaServizio,String strDescrSocieta,String strDescrTipologiaServizio) throws DaoException {
		rowSets(impostaServizio, 0, 0,strDescrSocieta,strDescrTipologiaServizio);
	}

	public void doRowSets(ImpostaServizio impostaServizio, int rowsPerPage, int pageNumber,String strDescrSocieta,String strDescrTipologiaServizio) throws DaoException {
		if (rowsPerPage <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));
		if (pageNumber <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
		rowSets(impostaServizio, rowsPerPage, pageNumber,strDescrSocieta,strDescrTipologiaServizio);
	}

	public void rowSets(ImpostaServizio imp, int rowsPerPage, int pageNumber,String strDescrSocieta,String strDescrTipologiaServizio) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.ISE_DOLIST.routine());
			callableStatement = prepareCall(Routines.ISE_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, imp.getTipologiaServizio().getCompany().getCompanyCode());
			callableStatement.setString(2, imp.getTipologiaServizio().getCodiceTipologiaServizio());
			callableStatement.setString(3, imp.getCodiceImpostaServizio());
			callableStatement.setString(4, imp.getDescrizioneImpostaServizio());
			callableStatement.setString(5, strDescrSocieta);
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

	public void doSave(ImpostaServizio imp,String codOp) throws DaoException {
	    CallableStatement callableStatement=null;		
		try	{

			if (imp.getCodiceImpostaServizio() == null || imp.getCodiceImpostaServizio().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("impostaServizio.codiceImpostaServizio"));
			if (imp.getTipologiaServizio() == null || imp.getTipologiaServizio().getCompany() == null || imp.getTipologiaServizio().getCompany().getCompanyCode() == null || imp.getTipologiaServizio().getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("impostaServizio.tipologiaServizio.company.companyCode"));			
			if (imp.getTipologiaServizio().getCodiceTipologiaServizio() == null || imp.getTipologiaServizio().getCodiceTipologiaServizio().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("impostaServizio.tipologiaServizio.codiceTipologiaServizio"));			
			ImpostaServizio data = doDetail(imp.getTipologiaServizio().getCompany().getCompanyCode(),imp.getTipologiaServizio().getCodiceTipologiaServizio(), imp.getCodiceImpostaServizio());
			if ((data != null) && codOp != null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope()) == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("impostaServizio.saveadd.error"));
			if (data != null) 
				callableStatement = prepareCall(Routines.ISE_DOUPDATE.routine());
			else callableStatement = prepareCall(Routines.ISE_DOINSERT.routine());
			imp.save(callableStatement);
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

	public void doDelete(ImpostaServizio imp) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.ISE_DODELETE.routine());
			callableStatement = prepareCall(Routines.ISE_DODELETE.routine());
			//fine LP PG21XX04 Leak
			if (imp.getTipologiaServizio().getCodiceTipologiaServizio() == null || imp.getTipologiaServizio().getCodiceTipologiaServizio().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("tipo.codiceTipologiaServizio"));
			if (imp.getTipologiaServizio().getCompany() == null || imp.getTipologiaServizio().getCompany().getCompanyCode() == null || imp.getTipologiaServizio().getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("tipo.company.companyCode"));
			callableStatement.setString(1, imp.getTipologiaServizio().getCompany().getCompanyCode());
			callableStatement.setString(2, imp.getTipologiaServizio().getCodiceTipologiaServizio());
			callableStatement.setString(3, imp.getCodiceImpostaServizio());
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
	
	public void doListBySoc(String codSocieta) throws DaoException
	{
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.ISE_DOLIST_SOC.routine());
			callableStatement.setString(1, codSocieta);

			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);	
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
	
	public void doListTSEBySoc() throws DaoException
	{
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.TSE_DOLIST_SOC.routine());

			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);	
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
}