package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.GatewayPagamento;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;
import com.seda.data.helper.HelperException;


public class GatewayPagamentoDao extends BaseDaoHandler {
	
	public GatewayPagamentoDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public GatewayPagamento doDetail(String chiaveGateway) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.GTW_DODETAIL.routine());
			callableStatement = prepareCall(Routines.GTW_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, chiaveGateway);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new GatewayPagamento(data);
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

	public void doRowSets(GatewayPagamento gateway,String strDescrUser,String strDescrCanalePagamento,String strDescrCartaPagamento,String strDescrSocieta) throws DaoException {
		rowSets(gateway,0,0,strDescrUser,strDescrCanalePagamento,strDescrCartaPagamento,strDescrSocieta);
	}

	public void doRowSets(GatewayPagamento gateway, int rowsPerPage, int pageNumber,String strDescrUser,String strDescrCanalePagamento,String strDescrCartaPagamento,String strDescrSocieta) throws DaoException {
		
			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
 
			rowSets(gateway,rowsPerPage,pageNumber,strDescrUser,strDescrCanalePagamento,strDescrCartaPagamento,strDescrSocieta);

	}
	
	public void rowSets(GatewayPagamento gateway, int rowsPerPage, int pageNumber,String strDescrUser,String strDescrCanalePagamento,String strDescrCartaPagamento,String strDescrSocieta) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.GTW_DOLIST.routine());
			callableStatement = prepareCall(Routines.GTW_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, gateway.getChiaveGateway());
			callableStatement.setString(2, gateway.getUser().getCompany().getCompanyCode());
			callableStatement.setString(3, gateway.getUser().getUserCode());
			callableStatement.setString(4, gateway.getCanale().getChiaveCanalePagamento());
			callableStatement.setString(5, gateway.getCarta().getCodiceCartaPagamento());
			callableStatement.setString(6, gateway.getDescrizioneGateway());
			callableStatement.setString(7, strDescrUser);
			callableStatement.setString(8, strDescrCanalePagamento);
			callableStatement.setString(9, strDescrCartaPagamento);
			callableStatement.setString(10, strDescrSocieta);
			callableStatement.setString(11, gateway.getTipoGateway()==null?"":gateway.getTipoGateway());	//04082016 GG PG160130
			callableStatement.setString(12, "");
			callableStatement.setInt(13, rowsPerPage);
			callableStatement.setInt(14, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(15, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(16, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(17, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(18, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(15), callableStatement.getInt(16), 
								 callableStatement.getInt(17), callableStatement.getInt(18));
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

	public void doSave(GatewayPagamento gateway,String codOp) throws DaoException {
	    CallableStatement callableStatement=null;
		try	{
			if ((gateway.getChiaveGateway() == null || gateway.getChiaveGateway().length() == 0) && codOp.compareTo(TypeRequest.EDIT_SCOPE.scope())==0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("gatewayPagamento.chiaveGateway"));
			
			GatewayPagamento data = doDetail(gateway.getChiaveGateway());
			if ((data != null) && codOp!=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0) throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("gatewayPagamento.saveadd.error"));
			if (data != null) {
				callableStatement = prepareCall(Routines.GTW_DOUPDATE.routine());
				gateway.update(callableStatement);
			}
			else {
				callableStatement = prepareCall(Routines.GTW_DOINSERT.routine());
				gateway.save(callableStatement);
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

	public void doDelete(GatewayPagamento gateway) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.GTW_DODELETE.routine());
			callableStatement = prepareCall(Routines.GTW_DODELETE.routine());
			//fine LP PG21XX04 Leak
			if ((gateway.getChiaveGateway() == null || gateway.getChiaveGateway().length() == 0))
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("gatewayPagamento.chiaveGateway"));

			callableStatement.setString(1, gateway.getChiaveGateway());
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

	/**
	 * Restituisce un webrowset XML utilizzato per popolare 
	 * una drop-down-list nella pagina web di ricerca flussi 
	 * di rendicontazione - I campi restiuiti sono 
	 * GTW_KGTWKGTW e GTW_DGTWDGTW
	 * 
	 * @param codiceSocieta
	 * @param codiceUtente
	 * @param codiceProvincia
	 * @return String
	 * @throws DaoException
	 */
	public String getListaGatewayXml(String codiceSocieta,String codiceUtente, String codiceProvincia)  throws DaoException
	{
		//inizio LP PG21XX04 Leak
		//CallableStatement callableStatement;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try
		{
			callableStatement = prepareCall(Routines.GTW_RECUPERA_LISTA_DDL.routine());
			callableStatement.setString(1, codiceSocieta == null ? "" : codiceSocieta);
			callableStatement.setString(2, codiceProvincia == null ? "" : codiceProvincia);
			callableStatement.setString(3, codiceUtente == null ? "" : codiceUtente);
			callableStatement.registerOutParameter(4, Types.VARCHAR);
			if(callableStatement.execute())
			{
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;
		}
		catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
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
}