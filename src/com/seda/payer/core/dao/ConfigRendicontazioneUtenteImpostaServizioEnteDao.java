package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.ConfigRendicontazioneUtenteImpostaServizioEnte;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;
import com.seda.data.helper.HelperException;
/**
 * 
 * @author mmontisano
 *
 */
public class ConfigRendicontazioneUtenteImpostaServizioEnteDao extends BaseDaoHandler {
	
	public ConfigRendicontazioneUtenteImpostaServizioEnteDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public ConfigRendicontazioneUtenteImpostaServizioEnte doDetail(String companyCode, String userCode, String chiaveEnte, String codiceTipoServizio, String codiceImpostaServizio) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.REE_DODETAIL.routine());
			callableStatement = prepareCall(Routines.REE_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, companyCode);
			callableStatement.setString(2, userCode);
			callableStatement.setString(3, chiaveEnte);
			callableStatement.setString(4, codiceTipoServizio);			
			callableStatement.setString(5, codiceImpostaServizio);	
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new ConfigRendicontazioneUtenteImpostaServizioEnte(data);
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

	public void doRowSets(ConfigRendicontazioneUtenteImpostaServizioEnte config, String strDescrEnte,String strDescrUtente,String strDescrTipologiaServizio,String strDescrImpostaServizio) throws DaoException {
		rowSets(config,0,0,strDescrEnte,strDescrUtente,strDescrTipologiaServizio,strDescrImpostaServizio); 
	}

	public void doRowSets(ConfigRendicontazioneUtenteImpostaServizioEnte config, int rowsPerPage, int pageNumber, String strDescrEnte,String strDescrUtente,String strDescrTipologiaServizio,String strDescrImpostaServizio) throws DaoException {
		
			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
 
			rowSets(config, rowsPerPage, pageNumber, strDescrEnte,strDescrUtente,strDescrTipologiaServizio,strDescrImpostaServizio);

	}
	
	public void rowSets(ConfigRendicontazioneUtenteImpostaServizioEnte config, int rowsPerPage, int pageNumber, String strDescrEnte,String strDescrUtente,String strDescrTipologiaServizio,String strDescrImpostaServizio) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.REE_DOLIST.routine());
			callableStatement = prepareCall(Routines.REE_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, config.getEnte().getUser().getCompany().getCompanyCode());
			callableStatement.setString(2, config.getEnte().getUser().getUserCode());
			callableStatement.setString(3, config.getEnte().getAnagEnte().getChiaveEnte());
			callableStatement.setString(4, config.getImpostaServizio().getTipologiaServizio().getCodiceTipologiaServizio());
			callableStatement.setString(5, config.getImpostaServizio().getCodiceImpostaServizio());
			callableStatement.setString(6, strDescrEnte);
			callableStatement.setString(7, strDescrUtente);
			callableStatement.setString(8, strDescrTipologiaServizio);
			callableStatement.setString(9, strDescrImpostaServizio);
			callableStatement.setString(10, "");
			callableStatement.setInt(11, rowsPerPage);
			callableStatement.setInt(12, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(14, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(15, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(16, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(13), callableStatement.getInt(14), 
								 callableStatement.getInt(15), callableStatement.getInt(16));
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

	public void doSave(ConfigRendicontazioneUtenteImpostaServizioEnte config, String codOp) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			if (config.getEnte()== null || config.getEnte().getUser() == null || config.getEnte().getUser().getUserCode() == null || config.getEnte().getUser().getUserCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configRendicontazioneUtenteImpostaServizioEnte.ente.userCode"));

			if (                                                                  config.getEnte().getUser().getCompany() == null || config.getEnte().getUser().getCompany().getCompanyCode() == null || config.getEnte().getUser().getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configRendicontazioneUtenteImpostaServizioEnte.ente.user.company.companyCode"));

			if (                            config.getEnte().getAnagEnte() == null || config.getEnte().getAnagEnte().getChiaveEnte() == null || config.getEnte().getAnagEnte().getChiaveEnte().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configRendicontazioneUtenteImpostaServizioEnte.ente.anagEnte.chiaveEnte"));

			if (config.getImpostaServizio() == null || config.getImpostaServizio().getTipologiaServizio() == null || config.getImpostaServizio().getTipologiaServizio().getCodiceTipologiaServizio() == null || config.getImpostaServizio().getTipologiaServizio().getCodiceTipologiaServizio().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configRendicontazioneUtenteImpostaServizioEnte.impostaServizio.tipologiaServizio.codiceTipologiaServizio"));

			if (                                       config.getImpostaServizio().getCodiceImpostaServizio() == null || config.getImpostaServizio().getCodiceImpostaServizio().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configRendicontazioneUtenteImpostaServizioEnte.impostaServizio.codiceImpostaServizio"));

			ConfigRendicontazioneUtenteImpostaServizioEnte data = doDetail(config.getEnte().getUser().getCompany().getCompanyCode(),config.getEnte().getUser().getUserCode(),config.getEnte().getAnagEnte().getChiaveEnte(),config.getImpostaServizio().getTipologiaServizio().getCodiceTipologiaServizio(),config.getImpostaServizio().getCodiceImpostaServizio());
			if ((data != null) && codOp!=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0) throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configRendicontazioneUtenteImpostaServizioEnte.saveadd.error"));
			if (data != null) 
				callableStatement = prepareCall(Routines.REE_DOUPDATE.routine());
			else 
				callableStatement = prepareCall(Routines.REE_DOINSERT.routine());
				
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

	public void doDelete(ConfigRendicontazioneUtenteImpostaServizioEnte config) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.REE_DODELETE.routine());
			callableStatement = prepareCall(Routines.REE_DODELETE.routine());
			//fine LP PG21XX04 Leak
			
			if (config.getEnte()== null || config.getEnte().getUser() == null || config.getEnte().getUser().getUserCode() == null || config.getEnte().getUser().getUserCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configRendicontazioneUtenteImpostaServizioEnte.ente.userCode"));

			if (                                                                  config.getEnte().getUser().getCompany() == null || config.getEnte().getUser().getCompany().getCompanyCode() == null || config.getEnte().getUser().getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configRendicontazioneUtenteImpostaServizioEnte.ente.user.company.companyCode"));

			if (                            config.getEnte().getAnagEnte() == null || config.getEnte().getAnagEnte().getChiaveEnte() == null || config.getEnte().getAnagEnte().getChiaveEnte().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configRendicontazioneUtenteImpostaServizioEnte.ente.anagEnte.chiaveEnte"));

			if (config.getImpostaServizio() == null || config.getImpostaServizio().getTipologiaServizio() == null || config.getImpostaServizio().getTipologiaServizio().getCodiceTipologiaServizio() == null || config.getImpostaServizio().getTipologiaServizio().getCodiceTipologiaServizio().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configRendicontazioneUtenteImpostaServizioEnte.impostaServizio.tipologiaServizio.codiceTipologiaServizio"));

			if (                                       config.getImpostaServizio().getCodiceImpostaServizio() == null || config.getImpostaServizio().getCodiceImpostaServizio().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configRendicontazioneUtenteImpostaServizioEnte.impostaServizio.codiceImpostaServizio"));


			callableStatement.setString(1, config.getEnte().getUser().getCompany().getCompanyCode());
			callableStatement.setString(2, config.getEnte().getUser().getUserCode());
			callableStatement.setString(3, config.getEnte().getAnagEnte().getChiaveEnte());
			callableStatement.setString(4, config.getImpostaServizio().getTipologiaServizio().getCodiceTipologiaServizio());
			callableStatement.setString(5, config.getImpostaServizio().getCodiceImpostaServizio());
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