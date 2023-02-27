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
import com.seda.payer.core.bean.ConfigurazioneImpostaSoggiorno;
import com.seda.payer.core.bean.ResponseData;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;

public class ConfigurazioneImpostaSoggiornoDao extends BaseDaoHandler {

	public ConfigurazioneImpostaSoggiornoDao(Connection connection, String schema) {
		super(connection, schema);
	}

	//inizio LP PG21XX04 Leak
	//private void closeConnection(CallableStatement callableStatement)
	//{
	//	if (callableStatement != null)
	//		DAOHelper.closeIgnoringException(callableStatement);
	//}
	//fine LP PG21XX04 Leak
	
	public ConfigurazioneImpostaSoggiorno doDetail(String codiceBelfiore) throws DaoException
	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.SRG_DODETAIL.routine());
			callableStatement.setString(1, codiceBelfiore);
			
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next())
					return new ConfigurazioneImpostaSoggiorno(data);
			}
			return null;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
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
	
	public void doRowSets(String descComune) throws DaoException {
		rowSets(0, 0,descComune);
	}

	public void doRowSets(int rowsPerPage, int pageNumber,String descComune) throws DaoException {
		
			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
 
			rowSets(rowsPerPage, pageNumber,descComune);

	}
	
	public void rowSets(int rowsPerPage, int pageNumber,String descComune) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.SRG_DOLIST.routine());
			callableStatement = prepareCall(Routines.SRG_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, descComune);
			callableStatement.setInt(2, rowsPerPage);
			callableStatement.setInt(3, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(4, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(5, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(6, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(7, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(4), callableStatement.getInt(5), 
								 callableStatement.getInt(6), callableStatement.getInt(7));
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
	
	public void doUpdate(ConfigurazioneImpostaSoggiorno configImposta) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.SRG_DOUPDATE.routine());
			configImposta.update(callableStatement);
			
			callableStatement.execute();
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
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
	
	public void doSave(ConfigurazioneImpostaSoggiorno configImposta) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.SRG_DOINSERT.routine());
			configImposta.save(callableStatement);
			
			callableStatement.execute();
			
		} catch (SQLException x) {
			if(x.getErrorCode()== -803){
				throw new DaoException(x.getErrorCode(),"esiste già una anagrafica per i parametri selezionati");
			}
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
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
	
	public void doDelete(ConfigurazioneImpostaSoggiorno configImposta) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.SRG_DODELETE.routine());
			callableStatement = prepareCall(Routines.SRG_DODELETE.routine());
			//inizio LP PG21XX04 Leak
			if (configImposta.getCodiceBelfiore() == null)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configurazioneImmposta.canebelf"));
			callableStatement.setString(1, configImposta.getCodiceBelfiore());
			callableStatement.setString(2, configImposta.getCodiceSocieta());
			callableStatement.setString(3, configImposta.getCodiceUtente());
			callableStatement.setString(4, configImposta.getChiaveEnte());
			//callableStatement.registerOutParameter(2, Types.CHAR);
			callableStatement.execute();
		} catch (SQLException x) {
			if(x.getErrorCode()== -532 || x.getErrorCode()== -438){
				throw new DaoException(55,"Impossibile effettuare la cancellazione, sono presenti una o più informazioni correlate");
			}
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
	
	public ResponseData doSaveHost(ConfigurazioneImpostaSoggiorno configImposta, String codUtente) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.IS_IMPSOGG_DOSAVE.routine());
			callableStatement.setString(1, codUtente);
			callableStatement.setString(2, configImposta.getCodiceEnteGestionaleEntrate());
			callableStatement.setString(3, configImposta.getImpostaServizioGestionaleEntrate());
			callableStatement.setString(4, configImposta.getCodiceTributoGestionaleEntrate());
			
			//parametri di output
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			callableStatement.registerOutParameter(6, Types.VARCHAR);
			
			callableStatement.execute();
			
			ResponseData res = new ResponseData();
			res.setRetCode(callableStatement.getString(5));
			res.setRetMessage(callableStatement.getString(6));
			
			/****TEST****/
			/*ResponseData res = new ResponseData();
			res.setRetCode("OK");
			res.setRetMessage("");
			res.setInfo1("COD_TEST");*/
			
			return res;
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
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
	
	public void doListEnteBelfiore(String codBelf) throws DaoException {
		CallableStatement callableStatement = null;
		try	
		{
			callableStatement = prepareCall(Routines.ENT_DOLIST_BELF.routine());				
			callableStatement.setString(1, codBelf);
			if (callableStatement.execute()) 
				this.loadWebRowSets(callableStatement);	
			
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

	public boolean doCheckAnagEntr(String codEnteGes,String codCiseGes) throws DaoException
	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.SAN_DODETAIL_ENT.routine());
			callableStatement.setString(1, codEnteGes);
			callableStatement.setString(2, codCiseGes);
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next())
					return true;
			}
			return false;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
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
