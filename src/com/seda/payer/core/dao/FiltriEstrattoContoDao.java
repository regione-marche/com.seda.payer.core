package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.FiltriEstrattoConto;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;

public class FiltriEstrattoContoDao extends BaseDaoHandler {
	
	public FiltriEstrattoContoDao(Connection connection, String schema) {
		
		super(connection, schema);
	}

	public FiltriEstrattoConto doDetail(String chiaveFiltroEstrattoConto) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.FEC_DODETAIL.routine());
			callableStatement.setString(1, chiaveFiltroEstrattoConto);
			
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next())
					return new FiltriEstrattoConto(data);
			}
			return null;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
			//if (data != null)
			//	DAOHelper.closeIgnoringException(data);
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
	
	public List<FiltriEstrattoConto> doDetailAll() throws DaoException {
		List<FiltriEstrattoConto> listRes = null;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.FEC_DODETAIL.routine());
			callableStatement.setString(1, "");
			
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				listRes = new ArrayList<FiltriEstrattoConto>();
				while (data.next())
					listRes.add(new FiltriEstrattoConto(data));
			}
			return listRes;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
			//if (data != null)
			//	DAOHelper.closeIgnoringException(data);
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

	public void doList(String codiceFiscale, String codiceSocieta, String codiceUtente, String chiaveEnte,
			String impostaServizio, String numeroEmissione, String numeroDocumento, 
			int pageNumber, int rowsPerPage) throws DaoException 
	{
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.FEC_DOLIST.routine());
			callableStatement.setString(1, codiceFiscale);
			/*Giulia 250313--inizio*/
			/*callableStatement.setString(2, codiceSocieta);*/
			/*callableStatement.setString(3, codiceUtente);*/
			/*Giulia 250313--fine*/
			callableStatement.setString(2, chiaveEnte);
			callableStatement.setString(3, impostaServizio);
			callableStatement.setString(4, numeroEmissione);
			callableStatement.setString(5, numeroDocumento);
			callableStatement.setInt(6, rowsPerPage);
			callableStatement.setInt(7, pageNumber);
			
			 //OUT O_ROWINI INT
			callableStatement.registerOutParameter(8, Types.INTEGER);
			//OUT O_ROWEND INT
			callableStatement.registerOutParameter(9, Types.INTEGER);
			//OUT O_TOTROWS INT
			callableStatement.registerOutParameter(10, Types.INTEGER);
			//OUT O_TOTPAGES INT
			callableStatement.registerOutParameter(11, Types.SMALLINT);
			
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(8), 
						callableStatement.getInt(10), callableStatement.getInt(9), 
						callableStatement.getInt(11));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
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

	public void doSave(FiltriEstrattoConto filtriEstrattoConto) throws DaoException {
		CallableStatement callableStatement=null;
		try	{
			
			callableStatement = prepareCall(Routines.FEC_DOINSERT.routine());
			callableStatement.setString(1, filtriEstrattoConto.getCodiceFiscale());
			//Giulia
//			callableStatement.setString(2, filtriEstrattoConto.getCodiceSocieta());
//			callableStatement.setString(3, filtriEstrattoConto.getCodiceUtente());
			callableStatement.setString(2, filtriEstrattoConto.getChiaveEnte());
			callableStatement.setString(3, filtriEstrattoConto.getCodiceImpostaServizio());
			callableStatement.setString(4, filtriEstrattoConto.getNumeroEmissione());
			callableStatement.setString(5, filtriEstrattoConto.getNumeroDocumento());
			callableStatement.setString(6, filtriEstrattoConto.getOperatoreUltimoAggiornamento());
			
			callableStatement.execute();
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
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
	
	public void doUpdate(FiltriEstrattoConto filtriEstrattoConto) throws DaoException {
		if (filtriEstrattoConto.getChiaveFiltroEstrattoConto() == null || filtriEstrattoConto.getChiaveFiltroEstrattoConto().length() == 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("chiaveFiltroEstrattoConto"));
		
		CallableStatement callableStatement = null;
		try	{
			
			callableStatement = prepareCall(Routines.FEC_DOUPDATE.routine());
			callableStatement.setString(1, filtriEstrattoConto.getChiaveFiltroEstrattoConto());
			callableStatement.setString(2, filtriEstrattoConto.getCodiceFiscale());
//			callableStatement.setString(3, filtriEstrattoConto.getCodiceSocieta());
//			callableStatement.setString(4, filtriEstrattoConto.getCodiceUtente());
			callableStatement.setString(3, filtriEstrattoConto.getChiaveEnte());
			callableStatement.setString(4, filtriEstrattoConto.getCodiceImpostaServizio());
			callableStatement.setString(5, filtriEstrattoConto.getNumeroEmissione());
			callableStatement.setString(6, filtriEstrattoConto.getNumeroDocumento());
			callableStatement.setString(7, filtriEstrattoConto.getOperatoreUltimoAggiornamento());
			
			callableStatement.execute();
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
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

	public void doDelete(String chiaveFiltroEstrattoConto) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.FEC_DODELETE.routine());
			
			if (chiaveFiltroEstrattoConto == null || chiaveFiltroEstrattoConto.length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("chiaveFiltriEstrattoConto"));
			
			callableStatement.setString(1, chiaveFiltroEstrattoConto);
			
			callableStatement.execute();
		} catch (SQLException x) {
			throw new DaoException(x.getErrorCode(),x.getMessage(),x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(Integer.parseInt(Messages.ILL_ARG_ERR_CODE.format()),x.getMessage(),x);
		} catch (HelperException x) {
			throw new DaoException(Integer.parseInt(Messages.HELPER_ERR_CODE.format()),x.getMessage(),x);
		} finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
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
	
	public void doListEntiDdl(String formSearch) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.FEC_DOLIST_ANE.routine());
			callableStatement.setString(1, formSearch);
			
			if (callableStatement.execute()) 
				this.loadWebRowSets(callableStatement);
			
		} catch (SQLException x) {
			throw new DaoException(x.getErrorCode(),x.getMessage(),x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(Integer.parseInt(Messages.ILL_ARG_ERR_CODE.format()),x.getMessage(),x);
		} catch (HelperException x) {
			throw new DaoException(Integer.parseInt(Messages.HELPER_ERR_CODE.format()),x.getMessage(),x);
		} finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
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
	
	public List<FiltriEstrattoConto> doListFiltri_CF_Ente(String codiceFiscale, String codiceSocieta, String codiceUtente, String chiaveEnte) throws DaoException {
		List<FiltriEstrattoConto> listRes = null;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.FEC_DOLIST_CFENT.routine());
			callableStatement.setString(1, codiceFiscale);
			callableStatement.setString(2, codiceSocieta);
			callableStatement.setString(3, codiceUtente);
			callableStatement.setString(4, chiaveEnte);
			
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				listRes = new ArrayList<FiltriEstrattoConto>();
				while (data.next())
					listRes.add(new FiltriEstrattoConto(data));
			}
			return listRes;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
			//if (data != null)
			//	DAOHelper.closeIgnoringException(data);
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
}