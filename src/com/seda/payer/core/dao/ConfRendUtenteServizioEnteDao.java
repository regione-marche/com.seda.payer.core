package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

//inizio LP PG21XX04 Leak
//import com.seda.data.dao.DAOHelper;
//fine LP PG21XX04 Leak
import com.seda.data.helper.HelperException;
import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.ConfRendUtenteServizioEnte;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;

public class ConfRendUtenteServizioEnteDao  extends BaseDaoHandler{

	public ConfRendUtenteServizioEnteDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public ConfRendUtenteServizioEnte doDetail
	(
		String codiceSocieta,
		String codiceUtente,
		String chiaveEnte,
		String tipologiaServizio
	) throws NumberFormatException, DaoException
	{
		ConfRendUtenteServizioEnte bean = null;
		ResultSet data = null;
		CallableStatement callableStatement = null;
		try {
			if (codiceSocieta == null || codiceSocieta.equals(""))
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("ConfRendUtenteServizioEnte.codiceSocieta"));
			if (codiceUtente == null || codiceUtente.equals(""))
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("ConfRendUtenteServizioEnte.codiceUtente"));
			if (chiaveEnte == null || chiaveEnte.equals(""))
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("ConfRendUtenteServizioEnte.chiaveEnte"));
			if (tipologiaServizio == null || tipologiaServizio.equals(""))
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("ConfRendUtenteServizioEnte.tipologiaServizio"));

			callableStatement = prepareCall(Routines.REE_DODETAIL.routine());
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setString(3, chiaveEnte);
			callableStatement.setString(4, tipologiaServizio);
			if (callableStatement.execute())
			{
				data = callableStatement.getResultSet();
				if (data.next()) bean = new  ConfRendUtenteServizioEnte(data);
			}
			
		} catch (IllegalArgumentException e) {
			throw new DaoException(Integer.parseInt(Messages.ILL_ARG_ERR_CODE.format()),e.getMessage(),e);
		} catch (SQLException e) {
			throw new DaoException(e.getErrorCode(),e.getMessage(),e);
		} catch (HelperException e) {
			throw new DaoException(Integer.parseInt(Messages.HELPER_ERR_CODE.format()),e.getMessage(),e);
		}
		finally
		{
			//inizio LP PG21XX04 Leak
            //DAOHelper.closeIgnoringException(data);
			//DAOHelper.closeIgnoringException(callableStatement);
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
		return bean;
	}

	public void doSave(ConfRendUtenteServizioEnte bean,String codOp) throws DaoException
	{
		CallableStatement callableStatement = null;
		try {
			if (bean.getCodiceSocieta() == null || bean.getCodiceSocieta().equals(""))
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("bean.getCodiceSocieta()"));
			if (bean.getCodiceUtente() == null || bean.getCodiceUtente().equals(""))
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("bean.getCodiceUtente()"));
			if (bean.getChiaveEnte() == null || bean.getChiaveEnte().equals(""))
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("bean.getChiaveEnte()"));
			if (bean.getTipologiaServizio() == null || bean.getTipologiaServizio().equals(""))
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("bean.getTipologiaServizio()"));

			/*
			 * Controllo se il record corrispondente alla chiave esiste o meno
			 */
			ConfRendUtenteServizioEnte data = doDetail(bean.getCodiceSocieta(), bean.getCodiceUtente(),  bean.getChiaveEnte(), bean.getTipologiaServizio());
			if ((data != null) && codOp!=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0) throw new IllegalArgumentException(Messages.UNIQUE_CONSTRAINT_VIOLATION_SIMPLE.format());
			if (data != null)	
			    callableStatement = prepareCall(Routines.REE_DOUPDATE.routine());
			else callableStatement = prepareCall(Routines.REE_DOINSERT.routine());
			bean.save(callableStatement);
			callableStatement.executeUpdate();
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		}
		finally
		{
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
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
	
	
	public boolean deleteRecord
	(
		String codiceSocieta,
		String codiceUtente,
		String chiaveEnte,
		String tipologiaServizio
	) throws NumberFormatException, DaoException
	{
		CallableStatement callableStatement = null;
		boolean esito = false;
		try {
			if (codiceSocieta == null || codiceSocieta.equals(""))
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("ConfRendUtenteServizioEnte.codiceSocieta"));
			if (codiceUtente == null || codiceUtente.equals(""))
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("ConfRendUtenteServizioEnte.codiceUtente"));
			if (chiaveEnte == null || chiaveEnte.equals(""))
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("ConfRendUtenteServizioEnte.chiaveEnte"));
			if (tipologiaServizio == null || tipologiaServizio.equals(""))
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("ConfRendUtenteServizioEnte.tipologiaServizio"));
			callableStatement = prepareCall(Routines.REE_DODELETE.routine());
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setString(3, chiaveEnte);
			callableStatement.setString(4, tipologiaServizio);
			callableStatement.registerOutParameter(5, Types.INTEGER);
			callableStatement.executeUpdate();
			if (callableStatement.getInt(5) == 1) esito = true;
		} catch (IllegalArgumentException e) {
			throw new DaoException(Integer.parseInt(Messages.ILL_ARG_ERR_CODE.format()),e.getMessage(),e);
		} catch (SQLException e) {
			throw new DaoException(e.getErrorCode(),e.getMessage(),e);
		} catch (HelperException e) {
			throw new DaoException(Integer.parseInt(Messages.HELPER_ERR_CODE.format()),e.getMessage(),e);
		}
		finally
		{
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return esito;
	}

	public void doRowSetsTail(
			int pageNumber,
			int rowsPerPage, 
			String order,
			String descrizioneSocieta,
			String descrizioneUtente,
			String descrizioneEnte,
			String descrizioneTipologiaServizio,
			String codiceTipologiaServizio
	) throws DaoException
	{
		CallableStatement callableStatement = null;
		try {
			callableStatement = prepareCall(Routines.REE_DOLIST.routine());
			callableStatement.setInt(1, pageNumber);
			callableStatement.setInt(2, rowsPerPage);
			callableStatement.setString(3, order);
			callableStatement.setString(4, descrizioneSocieta);
			callableStatement.setString(5, descrizioneUtente);
			callableStatement.setString(6, descrizioneEnte);
			callableStatement.setString(7, descrizioneTipologiaServizio);
			//callableStatement.setString(8, codiceTipologiaServizio);
			/*
			 * 	OUT O_ROWINI INT
			 */
			callableStatement.registerOutParameter(8, Types.INTEGER);
			/*
			 * 	OUT O_ROWEND INT
			 */
			callableStatement.registerOutParameter(9, Types.INTEGER);
			/*
			 * 	OUT O_TOTROWS INT
			 */
			callableStatement.registerOutParameter(10, Types.INTEGER);
			/*
			 * 	OUT O_TOTPAGES INT
			 */
			callableStatement.registerOutParameter(11, Types.SMALLINT);
			if (callableStatement.execute())
			{
				this.loadWebRowSet(callableStatement);
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(9), callableStatement.getInt(10), callableStatement.getInt(11), callableStatement.getInt(12));
			}

		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally
		{
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
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
	public void doRowSets 
	(
			int pageNumber,
			int rowsPerPage, 
			String order,
			String descrizioneSocieta,
			String descrizioneUtente,
			String descrizioneEnte,
			String descrizioneTipologiaServizio
	) throws DaoException
	{
		this.doRowSetsTail(pageNumber,rowsPerPage,order,descrizioneSocieta,descrizioneUtente,descrizioneEnte,descrizioneTipologiaServizio,"");
	}
}
