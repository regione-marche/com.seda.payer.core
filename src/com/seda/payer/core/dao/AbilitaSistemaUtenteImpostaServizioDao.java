package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.AbilitaSistemaUtenteImpostaServizio;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;
import com.seda.data.helper.HelperException;
/**
 * 
 * @author mmontisano
 *
 */
public class AbilitaSistemaUtenteImpostaServizioDao extends BaseDaoHandler {
	
	public AbilitaSistemaUtenteImpostaServizioDao(Connection connection, String schema) {
		super(connection, schema);
	}
 
	public AbilitaSistemaUtenteImpostaServizio doDetail(String companyCode, String userCode, String codiceTipoServizio, String codiceImpostaServizio, String urlSistemaEsterno) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.EXI_DODETAIL.routine());
			callableStatement = prepareCall(Routines.EXI_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, companyCode);
			callableStatement.setString(2, userCode);
			callableStatement.setString(3, codiceTipoServizio);	
			callableStatement.setString(4, codiceImpostaServizio);	
			callableStatement.setString(5, urlSistemaEsterno);	
			
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				//inizio LP PG21XX04 Leak
				if (data.next())
					return new AbilitaSistemaUtenteImpostaServizio(data);
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

	public void doRowSets(AbilitaSistemaUtenteImpostaServizio abilita,String strDescrUtente,String strDescrTipologiaServizio,String strDescrImpostaServizio) throws DaoException {
		rowSets(abilita, 0, 0,strDescrUtente,strDescrTipologiaServizio,strDescrImpostaServizio);
	}

	public void doRowSets(AbilitaSistemaUtenteImpostaServizio abilita, int rowsPerPage, int pageNumber,String strDescrUtente,String strDescrTipologiaServizio,String strDescrImpostaServizio) throws DaoException {
		
			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
 
			rowSets(abilita, rowsPerPage, pageNumber,strDescrUtente,strDescrTipologiaServizio,strDescrImpostaServizio);

	}
	
	public void rowSets(AbilitaSistemaUtenteImpostaServizio abilita, int rowsPerPage, int pageNumber,String strDescrUtente,String strDescrTipologiaServizio,String strDescrImpostaServizio) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.EXI_DOLIST.routine());
			callableStatement = prepareCall(Routines.EXI_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, abilita.getUser().getCompany().getCompanyCode());
			callableStatement.setString(2, abilita.getUser().getUserCode());
			callableStatement.setString(3, abilita.getImpostaServizio().getTipologiaServizio().getCodiceTipologiaServizio());
			callableStatement.setString(4, abilita.getImpostaServizio().getCodiceImpostaServizio());
			callableStatement.setString(5, abilita.getUrlSistemaEsterno());
			callableStatement.setString(6, strDescrUtente);
			callableStatement.setString(7, strDescrTipologiaServizio);
			callableStatement.setString(8, strDescrImpostaServizio);
			callableStatement.setString(9, "");
			callableStatement.setInt(10, rowsPerPage);
			callableStatement.setInt(11, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(12, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(14, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(15, Types.SMALLINT);
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

	public void doSave(AbilitaSistemaUtenteImpostaServizio abilita,String codOp) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			if (abilita.getUser() == null || abilita.getUser().getCompany() == null || abilita.getUser().getCompany().getCompanyCode() == null || abilita.getUser().getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaSistemaUtenteImpostaServizio.user.company.companyCode"));

			if (abilita.getUser().getUserCode() == null || abilita.getUser().getUserCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaSistemaUtenteImpostaServizio.user.userCode"));
			
			if (abilita.getImpostaServizio() == null || abilita.getImpostaServizio().getTipologiaServizio() == null || abilita.getImpostaServizio().getTipologiaServizio().getCodiceTipologiaServizio() == null || abilita.getImpostaServizio().getTipologiaServizio().getCodiceTipologiaServizio().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaSistemaUtenteImpostaServizio.impostaServizio.tipologiaServizio.codiceTipologiaServizio"));

			if (                                         abilita.getImpostaServizio().getCodiceImpostaServizio() == null || abilita.getImpostaServizio().getCodiceImpostaServizio().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaSistemaUtenteImpostaServizio.impostaServizio.codiceImpostaServizio"));
			
			if (abilita.getUrlSistemaEsterno() == null || abilita.getUrlSistemaEsterno().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaSistemaUtenteImpostaServizio.urlSistemaEsterno"));

			AbilitaSistemaUtenteImpostaServizio data = doDetail(abilita.getUser().getCompany().getCompanyCode(),abilita.getUser().getUserCode(),abilita.getImpostaServizio().getTipologiaServizio().getCodiceTipologiaServizio(),abilita.getImpostaServizio().getCodiceImpostaServizio(),abilita.getUrlSistemaEsterno());
			if ((data != null) && codOp!=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0) throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaSistemaUtenteImpostaServizio.saveadd.error"));
			if (data != null) 
				callableStatement = prepareCall(Routines.EXI_DOUPDATE.routine());
			else callableStatement = prepareCall(Routines.EXI_DOINSERT.routine());
			
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

	public void doDelete(AbilitaSistemaUtenteImpostaServizio abilita) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.EXI_DODELETE.routine());
			callableStatement = prepareCall(Routines.EXI_DODELETE.routine());
			//fine LP PG21XX04 Leak
			if (abilita.getUser() == null || abilita.getUser().getCompany() == null || abilita.getUser().getCompany().getCompanyCode() == null || abilita.getUser().getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaSistemaUtenteImpostaServizio.user.company.companyCode"));

			if (abilita.getUser().getUserCode() == null || abilita.getUser().getUserCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaSistemaUtenteImpostaServizio.user.userCode"));
			
			if (abilita.getImpostaServizio() == null || abilita.getImpostaServizio().getTipologiaServizio() == null || abilita.getImpostaServizio().getTipologiaServizio().getCodiceTipologiaServizio() == null || abilita.getImpostaServizio().getTipologiaServizio().getCodiceTipologiaServizio().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaSistemaUtenteImpostaServizio.impostaServizio.tipologiaServizio.codiceTipologiaServizio"));

			if (abilita.getImpostaServizio().getCodiceImpostaServizio() == null || abilita.getImpostaServizio().getCodiceImpostaServizio().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaSistemaUtenteImpostaServizio.impostaServizio.codiceImpostaServizio"));
			
			if (abilita.getUrlSistemaEsterno() == null || abilita.getUrlSistemaEsterno().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaSistemaUtenteImpostaServizio.urlSistemaEsterno"));


			callableStatement.setString(1, abilita.getUser().getCompany().getCompanyCode());
			callableStatement.setString(2, abilita.getUser().getUserCode());
			callableStatement.setString(3, abilita.getImpostaServizio().getTipologiaServizio().getCodiceTipologiaServizio());
			callableStatement.setString(4, abilita.getImpostaServizio().getCodiceImpostaServizio());
			callableStatement.setString(5, abilita.getUrlSistemaEsterno());
			
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