package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.ConfigRtEnte;
import com.seda.payer.core.bean.InfoTracciato;
import com.seda.payer.core.bean.InfoTracciatoFlusso;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class ConservazioneDao extends BaseDaoHandler {

	public ConservazioneDao(Connection connection, String schema) {
		super(connection, schema);
	}
	/**
	 * @param cutecute
	 * @return Lista configurazioni abilitate 
	 * @throws DaoException
	 */
	public List<ConfigRtEnte> getConfigurazioniAbilitate(String cutecute) throws DaoException {
		List<ConfigRtEnte> result = new ArrayList<ConfigRtEnte>();
		CallableStatement callableStatement = null;
		ResultSet rs = null;
		try {
			callableStatement = prepareCall(Routines.PYCFTSP_SEL_BATCH.routine());
			
			callableStatement.setString(1, cutecute);
			rs = callableStatement.executeQuery();
			while(rs.next()) {
				result.add(new ConfigRtEnte(rs));
			}
			
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch  (HelperException e) {
			throw new DaoException(e);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { }
			}
			if (callableStatement != null) {
				try { callableStatement.close(); } catch (SQLException e) { }
			}
		}
		
		return result;
	}
	
	/**
	 * @param ente
	 * @return Lista contentente XML di RPT e RT del singolo ente abilitato
	 * @throws DaoException
	 */
	
	public List<InfoTracciato> getListaRPTRT(ConfigRtEnte ente, String tipoReg, LocalDate dataInizioDati) throws DaoException {
		
		List<InfoTracciato> result = new ArrayList<InfoTracciato>();
		CallableStatement callableStatement = null;
		ResultSet rs = null;
		try {
			callableStatement = prepareCall(Routines.PYRPTSP_SEL_BATCH.routine());
			
			callableStatement.setString(1, ente.getCodiceSocieta());
			callableStatement.setString(2, ente.getCuteCute());
			callableStatement.setString(3, ente.getChiaveEnte());
			callableStatement.setTimestamp(4, Timestamp.valueOf(dataInizioDati.atStartOfDay())); // TODO: aggiungere parametro alla SP
			callableStatement.setString(5, tipoReg);
			rs = callableStatement.executeQuery();
			while(rs.next()) {
				result.add(new InfoTracciato(rs));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { }
			}
			if (callableStatement != null) {
				try { callableStatement.close(); } catch (SQLException e) { }
			}
		}
		return result;
	}
	
	/**
	 * @param ente
	 * @return Lista di XML Flusso del singolo ente abilitato
	 * @throws DaoException  
	 */	
	public List<InfoTracciatoFlusso> getListaFRN(ConfigRtEnte ente, LocalDate dataInizioDati) throws DaoException {
		
		List<InfoTracciatoFlusso> result = new ArrayList<InfoTracciatoFlusso>();
		CallableStatement callableStatement = null;
		ResultSet rs = null;
		try {
			callableStatement = prepareCall(Routines.PYQUNSP_SEL_BATCH.routine());
			callableStatement.setString(1, ente.getCodiceSocieta());
			callableStatement.setString(2, ente.getCuteCute());
			callableStatement.setString(3, ente.getChiaveEnte());
			callableStatement.setTimestamp(4, Timestamp.valueOf(dataInizioDati.atStartOfDay())); // TODO: aggiungere parametro alla SP
			rs = callableStatement.executeQuery();
			while(rs.next()) {
				result.add(new InfoTracciatoFlusso(rs));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { }
			}
			if (callableStatement != null) {
				try { callableStatement.close(); } catch (SQLException e) { }
			}
		}
		
		return result;
	}
	
	/**
	 * Update Stato, Errore e IDSIP (FRN)
	 * @param chiave
	 * @param versamentoResult
	 * @throws DaoException 
	 */
	
	public void updateStatoFRN(long chiave, String errorMessage, String transportOutcome, String idSip) throws DaoException  {
		
		CallableStatement callableStatement = null;
		
		int p = 1;
		try {
		callableStatement = prepareCall(Routines.PYQUNSP_UPD_BATCH.routine());
			callableStatement.setLong(p++, chiave);
			callableStatement.setString(p++, errorMessage);
			callableStatement.setString(p++, transportOutcome);
			callableStatement.setString(p++, idSip);
			callableStatement.registerOutParameter(p++, Types.INTEGER);
			callableStatement.execute();
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			if (callableStatement != null) {
				try { callableStatement.close(); } catch (SQLException e) { }
			}
		}
	}

	/**
	 * Update Stato, Errore e IDSIP (RPT|RT)
	 * @param chiave
	 * @param versamentoResult
	 * @param tipoRegistro
	 * @throws DaoException 
	 */
	public void updateStato(long chiave, String errorMessage, String transportOutcome, String idSip, String tipoRegistro) throws DaoException {
		CallableStatement callableStatement = null;
		
		int p = 1;
		try {
			callableStatement = prepareCall(Routines.PYRPTSP_UPD_BATCH.routine());
			callableStatement.setLong(p++, chiave);
			callableStatement.setString(p++, errorMessage);
			callableStatement.setString(p++, transportOutcome);
			callableStatement.setString(p++, idSip);
			callableStatement.setString(p++, tipoRegistro);
			callableStatement.registerOutParameter(p++, Types.INTEGER);
			callableStatement.execute();
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			if (callableStatement != null) {
				try { callableStatement.close(); } catch (SQLException e) { }
			}
		}
	}

}
