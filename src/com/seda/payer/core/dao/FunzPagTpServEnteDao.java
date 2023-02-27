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
import com.seda.payer.core.bean.FunzPagTpServEnte;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;

public class FunzPagTpServEnteDao extends BaseDaoHandler {
	
	public FunzPagTpServEnteDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public FunzPagTpServEnte doDetail(String companyCode, String userCode, String chiaveEnte, String tipServizioCode, String nomeForm) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CTE_DODETAIL.routine());
			callableStatement = prepareCall(Routines.CTE_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, companyCode);
			callableStatement.setString(2, userCode);
			callableStatement.setString(3, chiaveEnte);
			callableStatement.setString(4, tipServizioCode);
			callableStatement.setString(5, nomeForm);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new FunzPagTpServEnte(data);
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

	public List<FunzPagTpServEnte> doDetail_Aggregato(String codiceSocieta, String codiceUtente, String chiaveEnte, String tipologiaServizio) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			List<FunzPagTpServEnte> listFunz = null;
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CTE_DODETAIL_AGG.routine());
			callableStatement = prepareCall(Routines.CTE_DODETAIL_AGG.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setString(3, chiaveEnte);
			callableStatement.setString(4, tipologiaServizio);
			
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				while (data.next())
				{
					if (listFunz == null)
						listFunz = new ArrayList<FunzPagTpServEnte>();
					listFunz.add(new FunzPagTpServEnte(data));
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
	
	public void doRowSets(FunzPagTpServEnte funzPagTpServEnte) throws DaoException {
		rowSets(funzPagTpServEnte, 0, 0);
	}

	public void doRowSets(FunzPagTpServEnte funzPagTpServEnte, int rowsPerPage, int pageNumber) throws DaoException {

			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
 
			rowSets(funzPagTpServEnte, rowsPerPage, pageNumber);

	}

	public void rowSets(FunzPagTpServEnte funzPagTpServEnte, int rowsPerPage, int pageNumber) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CTE_DOLIST.routine());
			callableStatement = prepareCall(Routines.CTE_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, funzPagTpServEnte.getTipServizio().getTipoServizio().getCompany().getCompanyCode());
			callableStatement.setString(2, funzPagTpServEnte.getTipServizio().getEnte().getUser().getUserCode());
			callableStatement.setString(3, funzPagTpServEnte.getTipServizio().getEnte().getAnagEnte().getChiaveEnte());
			callableStatement.setString(4, funzPagTpServEnte.getTipServizio().getTipoServizio().getCodiceTipologiaServizio());
			callableStatement.setString(5, funzPagTpServEnte.getNomeForm());
			callableStatement.setString(6, funzPagTpServEnte.getLabelFormFunPagamento());
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
	
	public void doRowSets_Aggregato(String descrizioneSocieta, String descrizioneUtente, String descrizioneEnte, String descrizioneTipologiaServizio,
			int rowsPerPage, int pageNumber) throws DaoException {

		if (rowsPerPage <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

		if (pageNumber <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));

		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CTE_DOLIST_AGG.routine());
			callableStatement = prepareCall(Routines.CTE_DOLIST_AGG.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, descrizioneSocieta);
			callableStatement.setString(2, descrizioneUtente);
			callableStatement.setString(3, descrizioneEnte);
			callableStatement.setString(4, descrizioneTipologiaServizio);
			callableStatement.setString(5, "");
			callableStatement.setInt(6, rowsPerPage);
			callableStatement.setInt(7, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(8, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(9, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(10, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(11, Types.SMALLINT);
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

	public void doSave(FunzPagTpServEnte funzPagTpServEnte, String codOp) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			if (funzPagTpServEnte.getTipServizio().getTipoServizio().getCompany().getCompanyCode() == null || funzPagTpServEnte.getTipServizio().getTipoServizio().getCompany().getCompanyCode().length()==0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("funzPagTpServEnte.companyCode"));
			if (funzPagTpServEnte.getTipServizio().getEnte().getUser().getUserCode()== null || funzPagTpServEnte.getTipServizio().getEnte().getUser().getUserCode().length() ==0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("funzPagTpServEnte.userCode"));
			if (funzPagTpServEnte.getTipServizio().getEnte().getAnagEnte().getChiaveEnte() == null || funzPagTpServEnte.getTipServizio().getEnte().getAnagEnte().getChiaveEnte().length() ==0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("funzPagTpServEnte.chiaveEnte"));
			if (funzPagTpServEnte.getTipServizio().getTipoServizio().getCodiceTipologiaServizio() == null || funzPagTpServEnte.getTipServizio().getTipoServizio().getCodiceTipologiaServizio().length() ==0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("funzPagTpServEnte.tipologiaServizio"));
			FunzPagTpServEnte data = doDetail(funzPagTpServEnte.getTipServizio().getTipoServizio().getCompany().getCompanyCode(),
					funzPagTpServEnte.getTipServizio().getEnte().getUser().getUserCode(),
					funzPagTpServEnte.getTipServizio().getEnte().getAnagEnte().getChiaveEnte(),
					funzPagTpServEnte.getTipServizio().getTipoServizio().getCodiceTipologiaServizio(),
					funzPagTpServEnte.getNomeForm());
			if (data != null && codOp!=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0)  throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("funzPagTpServEnte.saveadd.error"));
			if (data != null) {
				callableStatement = prepareCall(Routines.CTE_DOUPDATE.routine());
				funzPagTpServEnte.update(callableStatement);
			}
			else {
				callableStatement = prepareCall(Routines.CTE_DOINSERT.routine());
				funzPagTpServEnte.save(callableStatement);
			}
			callableStatement.execute();
			
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

	public void doDelete(FunzPagTpServEnte funzPagTpServEnte) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CTE_DODELETE.routine());
			callableStatement = prepareCall(Routines.CTE_DODELETE.routine());
			//fine LP PG21XX04 Leak
			if (funzPagTpServEnte.getTipServizio().getTipoServizio().getCompany().getCompanyCode() == null)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("funzPagTpServEnte.companyCode"));
			if (funzPagTpServEnte.getTipServizio().getEnte().getUser().getUserCode()== null)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("funzPagTpServEnte.userCode"));
			if (funzPagTpServEnte.getTipServizio().getEnte().getAnagEnte().getChiaveEnte() == null)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("funzPagTpServEnte.chiaveEnte"));
			if (funzPagTpServEnte.getTipServizio().getTipoServizio().getCodiceTipologiaServizio() == null)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("funzPagTpServEnte.tipologiaServizio"));
			
			callableStatement.setString(1, funzPagTpServEnte.getTipServizio().getTipoServizio().getCompany().getCompanyCode()  );
			callableStatement.setString(2, funzPagTpServEnte.getTipServizio().getEnte().getUser().getUserCode());
			callableStatement.setString(3, funzPagTpServEnte.getTipServizio().getEnte().getAnagEnte().getChiaveEnte());
			callableStatement.setString(4, funzPagTpServEnte.getTipServizio().getTipoServizio().getCodiceTipologiaServizio());
			callableStatement.setString(5, funzPagTpServEnte.getNomeForm());
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
	
	public void doDelete_Aggregato(String codiceSocieta, String codiceUtente, String chiaveEnte, String tipologiaServizio) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CTE_DODELETE_AGG.routine());
			callableStatement = prepareCall(Routines.CTE_DODELETE_AGG.routine());
			//fine LP PG21XX04 Leak
			if (codiceSocieta == null)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("codiceSocieta"));
			if (codiceUtente == null)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("codiceUtente"));
			if (chiaveEnte == null)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("chiaveEnte"));
			if (tipologiaServizio == null)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("tipologiaServizio"));
			
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setString(3, chiaveEnte);
			callableStatement.setString(4, tipologiaServizio);
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