package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.payer.core.bean.ConfigRendicontazioneUtenteImpostaServizio;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;
import com.seda.data.helper.HelperException;
/**
 * 
 * @author mmontisano
 *
 */
public class ConfigRendicontazioneUtenteImpostaServizioDao extends BaseDaoHandler {
	
	public ConfigRendicontazioneUtenteImpostaServizioDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public ConfigRendicontazioneUtenteImpostaServizio doDetail(String companyCode, String userCode, String codiceTipoServizio, String codiceImpostaServizio) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.RES_DODETAIL.routine());
			callableStatement = prepareCall(Routines.RES_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, companyCode);
			callableStatement.setString(2, userCode);
			callableStatement.setString(3, codiceTipoServizio);
			callableStatement.setString(4, codiceImpostaServizio);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new ConfigRendicontazioneUtenteImpostaServizio(data);
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

	public void doRowSets(ConfigRendicontazioneUtenteImpostaServizio config,String strDescrUtente,String strDescrTipologiaServizio,String strDescrImpostaServizio) throws DaoException {
		rowSets(config,0,0,strDescrUtente,strDescrTipologiaServizio,strDescrImpostaServizio); 
	}

	public void doRowSets(ConfigRendicontazioneUtenteImpostaServizio config, int rowsPerPage, int pageNumber,String strDescrUtente,String strDescrTipologiaServizio,String strDescrImpostaServizio) throws DaoException {
		if (rowsPerPage <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));
		if (pageNumber <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
		rowSets(config, rowsPerPage, pageNumber,strDescrUtente,strDescrTipologiaServizio,strDescrImpostaServizio);
	}
	
	public void rowSets(ConfigRendicontazioneUtenteImpostaServizio config, int rowsPerPage, int pageNumber,String strDescrUtente,String strDescrTipologiaServizio,String strDescrImpostaServizio) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.RES_DOLIST.routine());
			callableStatement = prepareCall(Routines.RES_DOLIST.routine());
			//fine LP PG21XX04 Leak
			//callableStatement.setString(1, config.getUser().getCompany().getCompanyCode());
			//callableStatement.setString(2, config.getUser().getUserCode());
			//callableStatement.setString(3, config.getImpostaServizio().getTipologiaServizio().getCodiceTipologiaServizio());
			//callableStatement.setString(4, config.getImpostaServizio().getCodiceImpostaServizio());
			callableStatement.setString(5, strDescrUtente);
			callableStatement.setString(6, strDescrTipologiaServizio);
			callableStatement.setString(7, strDescrImpostaServizio);
			callableStatement.setString(8, "");
			callableStatement.setInt(9, rowsPerPage);
			callableStatement.setInt(10, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(11, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(12, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(14, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(11), callableStatement.getInt(12), 
								 callableStatement.getInt(13), callableStatement.getInt(14));
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

	public void doSave(ConfigRendicontazioneUtenteImpostaServizio config, String codOp) throws DaoException {
		/*CallableStatement callableStatement = null;
		try	{

			if (config.getUser()== null || config.getUser().getUserCode() == null || config.getUser().getUserCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configRendicontazioneUtenteImpostaServizio.userCode"));

			if (                            config.getUser().getCompany() == null || config.getUser().getCompany().getCompanyCode() == null || config.getUser().getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configRendicontazioneUtenteImpostaServizio.user.company.companyCode"));

			if (config.getImpostaServizio() == null || config.getImpostaServizio().getTipologiaServizio() == null || config.getImpostaServizio().getTipologiaServizio().getCodiceTipologiaServizio() == null || config.getImpostaServizio().getTipologiaServizio().getCodiceTipologiaServizio().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configRendicontazioneUtenteImpostaServizio.impostaServizio.tipologiaServizio.codiceTipologiaServizio"));

			if (                                       config.getImpostaServizio().getCodiceImpostaServizio() == null || config.getImpostaServizio().getCodiceImpostaServizio().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configRendicontazioneUtenteImpostaServizio.impostaServizio.codiceImpostaServizio"));

			
			ConfigRendicontazioneUtenteImpostaServizio data = doDetail(config.getUser().getCompany().getCompanyCode(),config.getUser().getUserCode(),config.getImpostaServizio().getTipologiaServizio().getCodiceTipologiaServizio(),config.getImpostaServizio().getCodiceImpostaServizio());
			if ((data != null) && codOp!=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0) throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configRendicontazioneUtenteImpostaServizio.saveadd.error"));
			if (data != null) 
				callableStatement = prepareCall(Routines.RES_DOUPDATE.routine());
			else 
				callableStatement = prepareCall(Routines.RES_DOINSERT.routine());
				
			config.save(callableStatement);
			callableStatement.execute();
			//commit();
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}*/
	}

	public void doDelete(ConfigRendicontazioneUtenteImpostaServizio config) throws DaoException {
		/*try	{
			CallableStatement callableStatement = prepareCall(Routines.RES_DODELETE.routine());
			if (config.getUser()== null || config.getUser().getUserCode() == null || config.getUser().getUserCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configRendicontazioneUtenteImpostaServizio.userCode"));

			if (                            config.getUser().getCompany() == null || config.getUser().getCompany().getCompanyCode() == null || config.getUser().getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configRendicontazioneUtenteImpostaServizio.user.company.companyCode"));

			if (config.getImpostaServizio() == null || config.getImpostaServizio().getTipologiaServizio() == null || config.getImpostaServizio().getTipologiaServizio().getCodiceTipologiaServizio() == null || config.getImpostaServizio().getTipologiaServizio().getCodiceTipologiaServizio().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configRendicontazioneUtenteImpostaServizio.impostaServizio.tipologiaServizio.codiceTipologiaServizio"));

			if (                                       config.getImpostaServizio().getCodiceImpostaServizio() == null || config.getImpostaServizio().getCodiceImpostaServizio().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configRendicontazioneUtenteImpostaServizio.impostaServizio.codiceImpostaServizio"));

			callableStatement.setString(1, config.getUser().getCompany().getCompanyCode());
			callableStatement.setString(2, config.getUser().getUserCode());
			callableStatement.setString(3, config.getImpostaServizio().getTipologiaServizio().getCodiceTipologiaServizio());
			callableStatement.setString(4, config.getImpostaServizio().getCodiceImpostaServizio());
			callableStatement.execute();
			//commit();
		} catch (SQLException x) {
			throw new DaoException(x.getErrorCode(),x.getMessage(),x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(Integer.parseInt(Messages.ILL_ARG_ERR_CODE.format()),x.getMessage(),x);
		} catch (HelperException x) {
			throw new DaoException(Integer.parseInt(Messages.HELPER_ERR_CODE.format()),x.getMessage(),x);
		}*/
	}
}