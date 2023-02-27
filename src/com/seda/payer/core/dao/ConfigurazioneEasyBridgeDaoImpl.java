package com.seda.payer.core.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;

import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

import com.seda.commons.string.Convert;
import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.Helper;
import com.seda.data.helper.HelperException;
import com.seda.data.spi.PageInfo;
import com.seda.payer.core.bean.BlackBoxPagelist;
import com.seda.payer.core.bean.ConfigurazioneBlackBox;
import com.seda.payer.core.bean.ConfigurazioneEasyBridge;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.wallet.bean.ConfigurazioneSolleciti;
import com.seda.payer.core.wallet.bean.EsitoRisposte;
import com.seda.payer.core.wallet.bean.Wallet;
import com.seda.payer.core.wallet.bean.WalletPageList;
import com.seda.payer.core.wallet.dao.WalletDAO;

public class ConfigurazioneEasyBridgeDaoImpl extends BaseDaoHandler implements ConfigurazioneEasyBridgeDao   {
	private static final long serialVersionUID = 1L;

	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public ConfigurazioneEasyBridgeDaoImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}
	public ConfigurazioneEasyBridgeDaoImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}

	public EsitoRisposte insert(ConfigurazioneEasyBridge configurazioneEasyBridge )	throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte  esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.EYUTESP_INS.routine());
			callableStatement.setString(1, configurazioneEasyBridge.getCodiceIdentificativoDominio());
			callableStatement.setString(2, configurazioneEasyBridge.getCutecute());
			callableStatement.setString(3, configurazioneEasyBridge.getOperatore());
			callableStatement.registerOutParameter(4, Types.INTEGER);		
			callableStatement.execute();
			esitoRisposte.setCodiceMessaggio(callableStatement.getString(4));
			esitoRisposte.setDescrizioneMessaggio("OK");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (HelperException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return esitoRisposte;
	}
	
	
	public ConfigurazioneEasyBridge select(ConfigurazioneEasyBridge configurazioneEasyBridge )	throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		ResultSet dataRs = null;
		//fine LP PG21XX04 Leak
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.EYUTESP_SEL.routine());
			callableStatement.setString(1, configurazioneEasyBridge.getCodiceIdentificativoDominio());
			callableStatement.execute();
			//inizio LP PG21XX04 Leak
			//ResultSet data = callableStatement.getResultSet();
			data = callableStatement.getResultSet();
			//fine LP PG21XX04 Leak
			if(data.next()){
				//inizio LP PG21XX04 Leak
				//configurazioneEasyBridge = new ConfigurazioneEasyBridge(callableStatement.getResultSet());
				dataRs = callableStatement.getResultSet();
				configurazioneEasyBridge = new ConfigurazioneEasyBridge(dataRs);
				//fine LP PG21XX04 Leak
			}else{
				configurazioneEasyBridge = new ConfigurazioneEasyBridge();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (HelperException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if (dataRs != null) {
				try {
					dataRs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return configurazioneEasyBridge;
	}
}
