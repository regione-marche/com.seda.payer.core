package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.seda.data.helper.HelperException;
import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.FunzPagTpServ;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;

public class FunzPagTpServDao extends BaseDaoHandler {
	
	public FunzPagTpServDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public FunzPagTpServ doDetail(String companyCode, String userCode, String tipServizioCode, String nomeForm) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CTS_DODETAIL.routine());
			callableStatement = prepareCall(Routines.CTS_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, companyCode);
			callableStatement.setString(2, userCode);
			callableStatement.setString(3, tipServizioCode);
			callableStatement.setString(4, nomeForm);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new FunzPagTpServ(data);
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
	
	public List<FunzPagTpServ> doDetail_Aggregato(String codiceSocieta, String codiceUtente, String tipologiaServizio) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			List<FunzPagTpServ> listFunz = null;
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CTS_DODETAIL_AGG.routine());
			callableStatement = prepareCall(Routines.CTS_DODETAIL_AGG.routine());
			//inizio LP PG21XX04 Leak
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setString(3, tipologiaServizio);
			
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//inizio LP PG21XX04 Leak
				while (data.next())
				{
					if (listFunz == null)
						listFunz = new ArrayList<FunzPagTpServ>();
					listFunz.add(new FunzPagTpServ(data));
				}
			}
			return listFunz;
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

	public void doRowSets(FunzPagTpServ funzPagTpServ) throws DaoException {
		rowSets(funzPagTpServ, 0, 0);
	}

	public void doRowSets(FunzPagTpServ funzPagTpServ, int rowsPerPage, int pageNumber) throws DaoException {

			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
 
			rowSets(funzPagTpServ, rowsPerPage, pageNumber);

	}

	public void rowSets(FunzPagTpServ funzPagTpServ, int rowsPerPage, int pageNumber) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CTS_DOLIST.routine());
			callableStatement = prepareCall(Routines.CTS_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1,funzPagTpServ.getTipServizio().getTipoServizio().getCompany().getCompanyCode());
			callableStatement.setString(2,funzPagTpServ.getTipServizio().getUser().getUserCode());
			callableStatement.setString(3, funzPagTpServ.getTipServizio().getTipoServizio().getCodiceTipologiaServizio());
			callableStatement.setString(4, funzPagTpServ.getNomeForm());
			callableStatement.setString(5, funzPagTpServ.getLabelFormPagamento());
			callableStatement.setString(6, "");
			callableStatement.setInt(7, rowsPerPage);
			callableStatement.setInt(8, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(9, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(10, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(11, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(12, Types.SMALLINT);
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
	
	public void doRowSets_Aggregato(String descrizioneSocieta, String descrizioneUtente, String descrizioneTipologiaServizio,
			int rowsPerPage, int pageNumber) throws DaoException {

		if (rowsPerPage < 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

		if (pageNumber < 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));

		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CTS_DOLIST_AGG.routine());
			callableStatement = prepareCall(Routines.CTS_DOLIST_AGG.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, descrizioneSocieta);
			callableStatement.setString(2, descrizioneUtente);
			callableStatement.setString(3, descrizioneTipologiaServizio);
			callableStatement.setString(4, "");
			callableStatement.setInt(5, rowsPerPage);
			callableStatement.setInt(6, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(7, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(8, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(9, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(10, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(7), callableStatement.getInt(8), 
								 callableStatement.getInt(9), callableStatement.getInt(10));
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

	public void doSave(FunzPagTpServ funzPagTpServ, String codOp) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			if (funzPagTpServ.getTipServizio().getTipoServizio().getCompany().getCompanyCode() == null || funzPagTpServ.getTipServizio().getTipoServizio().getCompany().getCompanyCode().length()==0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("funzPagTpServ.companyCode"));
			if (funzPagTpServ.getTipServizio().getUser().getUserCode() == null || funzPagTpServ.getTipServizio().getUser().getUserCode().length() ==0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("funzPagTpServ.userCode"));
			if (funzPagTpServ.getTipServizio().getTipoServizio().getCodiceTipologiaServizio() == null || funzPagTpServ.getTipServizio().getTipoServizio().getCodiceTipologiaServizio().length() ==0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("funzPagTpServ.chiaveTipServizio"));
			if (funzPagTpServ.getNomeForm() == null || funzPagTpServ.getNomeForm().length() ==0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("funzPagTpServ.nomeForm"));
			FunzPagTpServ data = doDetail(funzPagTpServ.getTipServizio().getTipoServizio().getCompany().getCompanyCode(),
					funzPagTpServ.getTipServizio().getUser().getUserCode(),
					funzPagTpServ.getTipServizio().getTipoServizio().getCodiceTipologiaServizio(),
					funzPagTpServ.getNomeForm());
			if (data != null  && codOp != null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope()) == 0)  
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("funzPagTpServ.saveadd.error"));
			if (data != null) {
				callableStatement = prepareCall(Routines.CTS_DOUPDATE.routine());
				funzPagTpServ.update(callableStatement);
			}
			else {
				callableStatement = prepareCall(Routines.CTS_DOINSERT.routine());
				funzPagTpServ.save(callableStatement);
			}
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

	public void doDelete(FunzPagTpServ funzPagTpServ) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CTS_DODELETE.routine());
			callableStatement = prepareCall(Routines.CTS_DODELETE.routine());
			//fine LP PG21XX04 Leak
			if (funzPagTpServ.getTipServizio().getTipoServizio().getCompany().getCompanyCode() == null)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("funzPagTpServ.companyCode"));
			if (funzPagTpServ.getTipServizio().getUser().getUserCode() == null)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("funzPagTpServ.userCode"));
			if (funzPagTpServ.getTipServizio().getTipoServizio().getCodiceTipologiaServizio() == null)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("funzPagTpServ.chiaveTipServizio"));
			if (funzPagTpServ.getNomeForm() == null)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("funzPagTpServ.nomeForm"));
			
			callableStatement.setString(1, funzPagTpServ.getTipServizio().getTipoServizio().getCompany().getCompanyCode());
			callableStatement.setString(2, funzPagTpServ.getTipServizio().getUser().getUserCode());
			callableStatement.setString(3, funzPagTpServ.getTipServizio().getTipoServizio().getCodiceTipologiaServizio());
			callableStatement.setString(4, funzPagTpServ.getNomeForm());
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
	
	public void doDelete_Aggregato(String codiceSocieta, String codiceUtente, String tipologiaServizio) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CTS_DODELETE_AGG.routine());
			callableStatement = prepareCall(Routines.CTS_DODELETE_AGG.routine());
			//fine LP PG21XX04 Leak
			if (codiceSocieta == null)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("codiceSocieta"));
			if (codiceUtente == null)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("codiceUtente"));
			if (tipologiaServizio == null)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("tipologiaServizio"));
			
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setString(3, tipologiaServizio);
			callableStatement.execute();
			
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