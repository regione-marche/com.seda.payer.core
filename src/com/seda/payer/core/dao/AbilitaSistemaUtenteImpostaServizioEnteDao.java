package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.AbilitaSistemaUtenteImpostaServizioEnte;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;
import com.seda.data.helper.HelperException;
/**
 * 
 * @author mmontisano
 *
 */
public class AbilitaSistemaUtenteImpostaServizioEnteDao extends BaseDaoHandler {
	
	public AbilitaSistemaUtenteImpostaServizioEnteDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public AbilitaSistemaUtenteImpostaServizioEnte doDetail(String companyCode, String userCode, String chiaveEnte, String codiceTipoServizio, String codiceImpostaServizio, String urlSistemaEsterno) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.EXT_DODETAIL.routine());
			callableStatement = prepareCall(Routines.EXT_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, companyCode);
			callableStatement.setString(2, userCode);
			callableStatement.setString(3, chiaveEnte);		
			callableStatement.setString(4, codiceTipoServizio);	
			callableStatement.setString(5, codiceImpostaServizio);	
			callableStatement.setString(6, urlSistemaEsterno);			
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new AbilitaSistemaUtenteImpostaServizioEnte(data);
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
			if(data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}
	
	public void doRowSets(AbilitaSistemaUtenteImpostaServizioEnte abilita, String strDescrEnte,String strDescrUtente,String strDescrTipologiaServizio,String strDescrImpostaServizio) throws DaoException {
		rowSets(abilita, 0, 0,strDescrEnte,strDescrUtente,strDescrTipologiaServizio,strDescrImpostaServizio);
	}

	public void doRowSets(AbilitaSistemaUtenteImpostaServizioEnte abilita, int rowsPerPage, int pageNumber, String strDescrEnte,String strDescrUtente,String strDescrTipologiaServizio,String strDescrImpostaServizio) throws DaoException {
		
			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
 
			rowSets(abilita, rowsPerPage, pageNumber,strDescrEnte,strDescrUtente,strDescrTipologiaServizio,strDescrImpostaServizio);

	}
	
	public void rowSets(AbilitaSistemaUtenteImpostaServizioEnte abilita, int rowsPerPage, int pageNumber, String strDescrEnte,String strDescrUtente,String strDescrTipologiaServizio,String strDescrImpostaServizio) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.EXT_DOLIST.routine());
			callableStatement = prepareCall(Routines.EXT_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, abilita.getEnte().getUser().getCompany().getCompanyCode());
			callableStatement.setString(2, abilita.getEnte().getUser().getUserCode());
			callableStatement.setString(3, abilita.getEnte().getAnagEnte().getChiaveEnte());
			callableStatement.setString(4, abilita.getImpostaServizio().getTipologiaServizio().getCodiceTipologiaServizio());
			callableStatement.setString(5, abilita.getImpostaServizio().getCodiceImpostaServizio());
			callableStatement.setString(6, abilita.getUrlSistemaEsterno());
			callableStatement.setString(7, strDescrEnte);
			callableStatement.setString(8, strDescrUtente);
			callableStatement.setString(9, strDescrTipologiaServizio);
			callableStatement.setString(10, strDescrImpostaServizio);
			callableStatement.setString(11, "");
			callableStatement.setInt(12, rowsPerPage);
			callableStatement.setInt(13, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(14, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(15, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(16, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(17, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(14), callableStatement.getInt(15), 
								 callableStatement.getInt(16), callableStatement.getInt(17));
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
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}

	public void doSave(AbilitaSistemaUtenteImpostaServizioEnte abilita, String codOp) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			if (abilita.getEnte() == null || abilita.getEnte().getUser() == null || abilita.getEnte().getUser().getCompany() == null || abilita.getEnte().getUser().getCompany().getCompanyCode() == null || abilita.getEnte().getUser().getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaSistemaUtenteImpostaServizioEnte.ente.user.company.companyCode"));

			if (                                                                    abilita.getEnte().getUser().getUserCode() == null || abilita.getEnte().getUser().getUserCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaSistemaUtenteImpostaServizioEnte.ente.user.userCode"));
			
			if (                             abilita.getEnte().getAnagEnte() == null || abilita.getEnte().getAnagEnte().getChiaveEnte() == null || abilita.getEnte().getAnagEnte().getChiaveEnte().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaSistemaUtenteImpostaServizioEnte.ente.anagEnte.chiaveEnte"));
						
			if (abilita.getImpostaServizio() == null || abilita.getImpostaServizio().getTipologiaServizio() == null || abilita.getImpostaServizio().getTipologiaServizio().getCodiceTipologiaServizio() == null || abilita.getImpostaServizio().getTipologiaServizio().getCodiceTipologiaServizio().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaSistemaUtenteImpostaServizioEnte.impostaServizio.tipologiaServizio.codiceTipologiaServizio"));

			if (                                         abilita.getImpostaServizio().getCodiceImpostaServizio() == null || abilita.getImpostaServizio().getCodiceImpostaServizio().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaSistemaUtenteImpostaServizioEnte.impostaServizio.codiceImpostaServizio"));
						
			if (abilita.getUrlSistemaEsterno() == null || abilita.getUrlSistemaEsterno().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaSistemaUtenteImpostaServizioEnte.urlSistemaEsterno"));

			AbilitaSistemaUtenteImpostaServizioEnte data = doDetail(abilita.getEnte().getUser().getCompany().getCompanyCode(),abilita.getEnte().getUser().getUserCode(),abilita.getEnte().getAnagEnte().getChiaveEnte(),abilita.getImpostaServizio().getTipologiaServizio().getCodiceTipologiaServizio(),abilita.getImpostaServizio().getCodiceImpostaServizio(),abilita.getUrlSistemaEsterno());
			if ((data != null) && codOp!=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0) throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaSistemaUtenteImpostaServizioEnte.saveadd.error"));
			if (data != null) 
				callableStatement = prepareCall(Routines.EXT_DOUPDATE.routine());
			else callableStatement = prepareCall(Routines.EXT_DOINSERT.routine());
			
			abilita.save(callableStatement);
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
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}

	public void doDelete(AbilitaSistemaUtenteImpostaServizioEnte abilita) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.EXT_DODELETE.routine());
			callableStatement = prepareCall(Routines.EXT_DODELETE.routine());
			//fine LP PG21XX04 Leak
			if (abilita.getEnte() == null || abilita.getEnte().getUser() == null || abilita.getEnte().getUser().getCompany() == null || abilita.getEnte().getUser().getCompany().getCompanyCode() == null || abilita.getEnte().getUser().getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaSistemaUtenteImpostaServizioEnte.ente.user.company.companyCode"));

			if (abilita.getEnte().getUser().getUserCode() == null || abilita.getEnte().getUser().getUserCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaSistemaUtenteImpostaServizioEnte.ente.user.userCode"));
			
			if (abilita.getEnte().getAnagEnte() == null || abilita.getEnte().getAnagEnte().getChiaveEnte() == null || abilita.getEnte().getAnagEnte().getChiaveEnte().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaSistemaUtenteImpostaServizioEnte.ente.anagEnte.chiaveEnte"));
						
			if (abilita.getImpostaServizio() == null || abilita.getImpostaServizio().getTipologiaServizio() == null || abilita.getImpostaServizio().getTipologiaServizio().getCodiceTipologiaServizio() == null || abilita.getImpostaServizio().getTipologiaServizio().getCodiceTipologiaServizio().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaSistemaUtenteImpostaServizioEnte.impostaServizio.tipologiaServizio.codiceTipologiaServizio"));

			if (abilita.getImpostaServizio().getCodiceImpostaServizio() == null || abilita.getImpostaServizio().getCodiceImpostaServizio().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaSistemaUtenteImpostaServizioEnte.impostaServizio.codiceImpostaServizio"));
						
			if (abilita.getUrlSistemaEsterno() == null || abilita.getUrlSistemaEsterno().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaSistemaUtenteImpostaServizioEnte.urlSistemaEsterno"));

			callableStatement.setString(1, abilita.getEnte().getUser().getCompany().getCompanyCode());
			callableStatement.setString(2, abilita.getEnte().getUser().getUserCode());
			callableStatement.setString(3, abilita.getEnte().getAnagEnte().getChiaveEnte());
			callableStatement.setString(4, abilita.getImpostaServizio().getTipologiaServizio().getCodiceTipologiaServizio());
			callableStatement.setString(5, abilita.getImpostaServizio().getCodiceImpostaServizio());
			callableStatement.setString(6, abilita.getUrlSistemaEsterno());
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
			if(callableStatement != null) {
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