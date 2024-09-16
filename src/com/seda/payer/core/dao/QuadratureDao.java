package com.seda.payer.core.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.seda.data.spi.PageInfo;
import com.seda.payer.core.bean.RiepilogoMovimentiCBI;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class QuadratureDao  extends BaseDaoHandler{

	private PageInfo pageInfo = null;

	public QuadratureDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}


	public int insertMovimento (String nomeFile, Date dataCreazione, String nomeSupporto, String codiceSIA, String codiceCIN, String codiceABI, String codiceCAB, 
			String contoCorrente, Date dataValuta, Date dataContabile, BigDecimal importoMovimento, String cro, 
			Date dataInizio, Date dataFine, int numeroTransazioni, String tipoCarta)throws DaoException{
		CallableStatement callableStatement = null;
		try{
			callableStatement = prepareCall(Routines.TMG_INSERT_MOVIMENTO.routine());
			callableStatement.setString(1, nomeFile);
			callableStatement.setTimestamp(2, new java.sql.Timestamp(dataCreazione.getTime()));
			callableStatement.setString(3, nomeSupporto);
			callableStatement.setString(4, codiceSIA);
			callableStatement.setString(5, codiceCIN);
			callableStatement.setString(6, codiceABI);
			callableStatement.setString(7, codiceCAB);
			callableStatement.setString(8, contoCorrente);
			callableStatement.setTimestamp(9, new java.sql.Timestamp(dataValuta.getTime()));
			callableStatement.setTimestamp(10, new java.sql.Timestamp(dataContabile.getTime()));
			callableStatement.setBigDecimal(11, importoMovimento);
			callableStatement.setString(12, cro);
			callableStatement.setTimestamp(13, new java.sql.Timestamp(dataInizio.getTime()));
			callableStatement.setTimestamp(14, new java.sql.Timestamp(dataFine.getTime()));
			callableStatement.setInt(15, numeroTransazioni);
			callableStatement.setString(16, tipoCarta);
			callableStatement.setString(17, "BATCH_SEDA"); 
			callableStatement.registerOutParameter(18, Types.INTEGER);
			callableStatement.executeUpdate();
			int numRighe =  callableStatement.getInt(18);
			/*
			if (numrighe == 1) return true;
			else return false;
			 */
			return numRighe;
		} 
		catch (Exception x) {
			throw new DaoException(x);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeStatement(callableStatement);
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


	//RNINCA

	//	QUA_RNINCA_QUAINS=PYQUASP_RNINCA_INS

	public int insertQuadraturaRNINCA(String nomeFile, Date dataCreazione, String nomeSupporto, String codiceSIA, String codiceCIN, String codiceABI, String codiceCAB, 
			String contoCorrente, Date dataValuta, Date dataContabile, BigDecimal importoMovimento, String cro, 
			Date dataInizio, Date dataFine, int numeroTransazioni, String chiaveTransazione, String operatore)throws DaoException
			{
		int chiaveQuadratura=0;
		int numrighe=0;
		String messaggio="";
		CallableStatement callableStatement = null;
		try{
			//			callableStatement = prepareCall(PYQUASP_RNINCA_INS);
			callableStatement = prepareCall(Routines.QUA_RNINCA_QUAINS.routine());
			callableStatement.setString(1, nomeFile);
			callableStatement.setTimestamp(2, new java.sql.Timestamp(dataCreazione.getTime()));
			callableStatement.setString(3, nomeSupporto);
			callableStatement.setString(4, codiceSIA);
			callableStatement.setString(5, codiceCIN);
			callableStatement.setString(6, codiceABI);
			callableStatement.setString(7, codiceCAB);
			callableStatement.setString(8, contoCorrente);
			callableStatement.setTimestamp(9, new java.sql.Timestamp(dataValuta.getTime()));
			callableStatement.setTimestamp(10, new java.sql.Timestamp(dataContabile.getTime()));
			callableStatement.setBigDecimal(11, importoMovimento);
			callableStatement.setString(12, cro);
			callableStatement.setTimestamp(13, new java.sql.Timestamp(dataInizio.getTime()));
			callableStatement.setTimestamp(14, new java.sql.Timestamp(dataFine.getTime()));
			callableStatement.setInt(15, numeroTransazioni);
			callableStatement.setString(16, chiaveTransazione);
			callableStatement.setString(17, operatore);			
			callableStatement.registerOutParameter(18, Types.BIGINT);
			callableStatement.registerOutParameter(19, Types.INTEGER);
			callableStatement.registerOutParameter(20, Types.VARCHAR);
			callableStatement.executeUpdate();
			chiaveQuadratura =  callableStatement.getInt(18);
			numrighe =  callableStatement.getInt(19);
			messaggio =  callableStatement.getString(20);

		} 
		catch (Exception x) 
		{
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
		if (numrighe == 1) 
			return chiaveQuadratura;
		else 
			throw new DaoException(1, messaggio);
			}

	
	public int insertQuadraturaUNICREDIT(String nomeFile, Date dataCreazione, String nomeSupporto, String codiceSIA, String codiceCIN, String codiceABI, String codiceCAB, 
			String contoCorrente, Date dataValuta, Date dataContabile, BigDecimal importoMovimento, String cro, 
			Date dataInizio, Date dataFine, int numeroTransazioni, String chiaveTransazione, String operatore)throws DaoException
			{
		int chiaveQuadratura=0;
		int numrighe=0;
		String messaggio="";
		CallableStatement callableStatement = null;
		try{
			callableStatement = prepareCall(Routines.QUA_UNICREDIT_QUAINS.routine());
			callableStatement.setString(1, nomeFile);
			callableStatement.setTimestamp(2, new java.sql.Timestamp(dataCreazione.getTime()));
			callableStatement.setString(3, nomeSupporto);
			callableStatement.setString(4, codiceSIA);
			callableStatement.setString(5, codiceCIN);
			callableStatement.setString(6, codiceABI);
			callableStatement.setString(7, codiceCAB);
			callableStatement.setString(8, contoCorrente);
			callableStatement.setTimestamp(9, new java.sql.Timestamp(dataValuta.getTime()));
			callableStatement.setTimestamp(10, new java.sql.Timestamp(dataContabile.getTime()));
			callableStatement.setBigDecimal(11, importoMovimento);
			callableStatement.setString(12, cro);
			callableStatement.setTimestamp(13, new java.sql.Timestamp(dataInizio.getTime()));
			callableStatement.setTimestamp(14, new java.sql.Timestamp(dataFine.getTime()));
			callableStatement.setInt(15, numeroTransazioni);
			callableStatement.setString(16, chiaveTransazione);
			callableStatement.setString(17, operatore);			
			callableStatement.registerOutParameter(18, Types.BIGINT);
			callableStatement.registerOutParameter(19, Types.INTEGER);
			callableStatement.registerOutParameter(20, Types.VARCHAR);
			callableStatement.executeUpdate();
			chiaveQuadratura =  callableStatement.getInt(18);
			numrighe =  callableStatement.getInt(19);
			messaggio =  callableStatement.getString(20);

		} 
		catch (Exception x) 
		{
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
		if (numrighe == 1) 
			return chiaveQuadratura;
		else 
			throw new DaoException(1, messaggio);
			}

	
	public int riconciliaTransazioneRNINCA(int chiaveQuadratura, 
			String chiaveTransazione,
			BigDecimal importo,
			String operatore) throws DaoException
			{
		CallableStatement callableStatement = null;
		try{
			//			callableStatement = prepareCall(PYQUASP_RNINCA_SEARCH);
			callableStatement = prepareCall(Routines.QUA_RNINCA_TRARIC.routine());
			callableStatement.setLong(1, chiaveQuadratura);
			callableStatement.setString(2, chiaveTransazione);
			callableStatement.setBigDecimal(3, importo);
			callableStatement.setString(4, operatore);

			callableStatement.registerOutParameter(5, Types.DECIMAL);
			callableStatement.registerOutParameter(6, Types.INTEGER);
			callableStatement.registerOutParameter(7, Types.INTEGER);
			callableStatement.registerOutParameter(8, Types.VARCHAR);
			callableStatement.executeUpdate();
			return callableStatement.getInt(7);
		} 
		catch (Exception x) 
		{
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

	public int riconciliaTransazioneUNICREDIT(int chiaveQuadratura, 
			String chiaveTransazione,
			BigDecimal importo,
			String operatore) throws DaoException
			{
		CallableStatement callableStatement = null;
		try{
			callableStatement = prepareCall(Routines.QUA_UNICREDIT_TRARIC.routine());
			callableStatement.setLong(1, chiaveQuadratura);
			callableStatement.setString(2, chiaveTransazione);
			callableStatement.setBigDecimal(3, importo);
			callableStatement.setString(4, operatore);

			callableStatement.registerOutParameter(5, Types.DECIMAL);
			callableStatement.registerOutParameter(6, Types.INTEGER);
			callableStatement.registerOutParameter(7, Types.INTEGER);
			callableStatement.registerOutParameter(8, Types.VARCHAR);
			callableStatement.executeUpdate();
			return callableStatement.getInt(7);
		} 
		catch (Exception x) 
		{
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

	
	// STEP2	
	//	QUA_RNINCA_QUAEND=PYQUASP_RNINCA_END	
	//	public void chiudiQuadraduraRNINCA(int chiaveQuadratura) throws DaoException
	public int chiudiQuadraduraRNINCA(int chiaveQuadratura, String quadrareMovimentiSquadrati ) throws DaoException
	{
		int scritture = 0;
		CallableStatement callableStatement = null;
		try
		{
			//			callableStatement = prepareCall(PYQUASP_RNINCA_END);
			callableStatement = prepareCall(Routines.QUA_RNINCA_QUAEND.routine());
			callableStatement.setLong(1, chiaveQuadratura);
			callableStatement.setString(2, quadrareMovimentiSquadrati);
			callableStatement.registerOutParameter(3, Types.INTEGER);
			callableStatement.executeUpdate();
			scritture = callableStatement.getInt(3);
		} 
		catch (Exception x) 
		{
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
		return scritture;
	}
	
	public int chiudiQuadraduraUNICREDIT(int chiaveQuadratura, String quadrareMovimentiSquadrati ) throws DaoException
	{
		int scritture = 0;
		CallableStatement callableStatement = null;
		try
		{
			callableStatement = prepareCall(Routines.QUA_UNICREDIT_QUAEND.routine());
			callableStatement.setLong(1, chiaveQuadratura);
			callableStatement.setString(2, quadrareMovimentiSquadrati);
			callableStatement.registerOutParameter(3, Types.INTEGER);
			callableStatement.executeUpdate();
			scritture = callableStatement.getInt(3);
		} 
		catch (Exception x) 
		{
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
		return scritture;
	}

	//PAYPAL

	//	QUA_PAYPAL_QUAINS=PYQUASP_PAYPAL_INS

	public int insertQuadraturaPAYPAL(String nomeFile, Date dataCreazione, String nomeSupporto, String codiceSIA, String codiceCIN, String codiceABI, String codiceCAB, 
			String contoCorrente, Date dataValuta, Date dataContabile, BigDecimal importoMovimento, String cro, 
			Date dataInizio, Date dataFine, int numeroTransazioni, String chiaveTransazione, String operatore)throws DaoException
			{
		int chiaveQuadratura=0;
		int numrighe=0;
		String messaggio="";
		CallableStatement callableStatement = null;
		try{
			callableStatement = prepareCall(Routines.QUA_PAYPAL_QUAINS.routine());
			callableStatement.setString(1, nomeFile);
			callableStatement.setTimestamp(2, new java.sql.Timestamp(dataCreazione.getTime()));
			callableStatement.setString(3, nomeSupporto);
			callableStatement.setString(4, codiceSIA);
			callableStatement.setString(5, codiceCIN);
			callableStatement.setString(6, codiceABI);
			callableStatement.setString(7, codiceCAB);
			callableStatement.setString(8, contoCorrente);
			callableStatement.setTimestamp(9, new java.sql.Timestamp(dataValuta.getTime()));
			callableStatement.setTimestamp(10, new java.sql.Timestamp(dataContabile.getTime()));
			callableStatement.setBigDecimal(11, importoMovimento);
			callableStatement.setString(12, cro);
			callableStatement.setTimestamp(13, new java.sql.Timestamp(dataInizio.getTime()));
			callableStatement.setTimestamp(14, new java.sql.Timestamp(dataFine.getTime()));
			callableStatement.setInt(15, numeroTransazioni);
			callableStatement.setString(16, chiaveTransazione);
			callableStatement.setString(17, operatore);			
			callableStatement.registerOutParameter(18, Types.BIGINT);
			callableStatement.registerOutParameter(19, Types.INTEGER);
			callableStatement.registerOutParameter(20, Types.VARCHAR);
			callableStatement.executeUpdate();
			chiaveQuadratura =  callableStatement.getInt(18);
			numrighe =  callableStatement.getInt(19);
			messaggio =  callableStatement.getString(20);

		} 
		catch (Exception x) 
		{
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
		if (numrighe == 1) 
			return chiaveQuadratura;
		else 
			throw new DaoException(1, messaggio);
			}

	public int riconciliaTransazionePAYPAL(int chiaveQuadratura, 
			String chiaveTransazione,
			BigDecimal importo,
			BigDecimal spese,
			String operatore) throws DaoException
			{
		CallableStatement callableStatement = null;
		try{
			callableStatement = prepareCall(Routines.QUA_PAYPAL_TRARIC.routine());
			callableStatement.setLong(1, chiaveQuadratura);
			callableStatement.setString(2, chiaveTransazione);
			callableStatement.setBigDecimal(3, importo);
			callableStatement.setBigDecimal(4, spese);
			callableStatement.setString(5, operatore);

			callableStatement.registerOutParameter(6, Types.DECIMAL);
			callableStatement.registerOutParameter(7, Types.DECIMAL);
			callableStatement.registerOutParameter(8, Types.INTEGER);
			callableStatement.registerOutParameter(9, Types.INTEGER);
			callableStatement.registerOutParameter(10, Types.VARCHAR);
			callableStatement.executeUpdate();
			return callableStatement.getInt(9);
		} 
		catch (Exception x) 
		{
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

	// STEP2	
	//	QUA_PAYPAL_QUAEND=PYQUASP_PAYPAL_END	
	public int chiudiQuadraduraPAYPAL(int chiaveQuadratura, String quadrareMovimentiSquadrati ) throws DaoException
	{
		int scritture = 0;
		CallableStatement callableStatement = null;
		try
		{
			callableStatement = prepareCall(Routines.QUA_PAYPAL_QUAEND.routine());
			callableStatement.setLong(1, chiaveQuadratura);
			callableStatement.setString(2, quadrareMovimentiSquadrati);
			callableStatement.registerOutParameter(3, Types.INTEGER);
			callableStatement.executeUpdate();
			scritture = callableStatement.getInt(3);
		} 
		catch (Exception x) 
		{
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
		return scritture;
	}
	/*	
	private MovimentoRendicontazione findByPrimaryKey(Integer chiaveMovimento) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try{
			callableStatement = prepareCall(Routines.TMG_DETTAGLIO_MOVIMENTO.routine());
			callableStatement.setInt(1, chiaveMovimento);
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next()) { 
					return new MovimentoRendicontazione(data);
				}
			}
		} 
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			DaoUtil.closeResultSet(data);
			DaoUtil.closeStatement(callableStatement);
		}
		return null;
	}
	 */

	public String getListaMovimentiXml(int rowsPerPage, int pageNumber, String order, 
			int chiaveMovimento, String tx_societa, String tx_provincia, String tx_utente, String mostraMovimenti, String tx_tipo_carta,
			String codiceAbi, String codiceCab, String codiceSia, String cro, String ccb, String nomeSupporto, String importo_da, String importo_a, 
			String data_creazione_da,String data_creazione_a,String data_valuta_da,String data_valuta_a,
			String data_contabile_da,String data_contabile_a) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try{
			BigDecimal importo_da_bd = new BigDecimal("0");
			BigDecimal importo_a_bd = new BigDecimal("0");
			if((importo_da != null)&&(!importo_da.equals("")))importo_da_bd = new BigDecimal(importo_da);
			if((importo_a != null)&&(!importo_a.equals("")))importo_a_bd = new BigDecimal(importo_a);
			callableStatement = prepareCall(Routines.TMG_RECUPERA_MOVIMENTI.routine());
			callableStatement.setInt(1, pageNumber);
			callableStatement.setInt(2, rowsPerPage);
			callableStatement.setString(3, order == null ? "" : order);
			callableStatement.setLong(4, chiaveMovimento);
			callableStatement.setString(5, (tx_societa == null) || (tx_societa.equals(StoredProcConf.SOCIETA_ALL_VALUE.getParam())) ? "" :  tx_societa);
			callableStatement.setString(6, (tx_provincia == null) || (tx_provincia.equals(StoredProcConf.PROVINCIA_ALL_VALUE.getParam())) ? "" :  tx_provincia);
			callableStatement.setString(7, (tx_utente == null) || (tx_utente.equals(StoredProcConf.UTENTE_ALL_VALUE.getParam())) ? "" :  tx_utente);
			callableStatement.setString(8, mostraMovimenti == null ? "" :mostraMovimenti);
			callableStatement.setString(9, (tx_tipo_carta == null) ||(tx_tipo_carta.equals(StoredProcConf.CARTA_ALL_VALUE.getParam())) ? "" :  tx_tipo_carta);
			callableStatement.setString(10, codiceAbi == null ? "" :  codiceAbi);
			callableStatement.setString(11, codiceSia == null ? "" :  codiceSia);
			callableStatement.setString(12, cro == null ? "" :  cro);
			callableStatement.setString(13, ccb == null ? "" :  ccb);
			callableStatement.setString(14, nomeSupporto == null ? "" :  nomeSupporto);
			callableStatement.setBigDecimal(15, importo_da_bd);
			callableStatement.setBigDecimal(16, importo_a_bd);
			callableStatement.setString(17,data_creazione_da == null ? "" : data_creazione_da);
			callableStatement.setString(18,data_creazione_a == null ? "" : data_creazione_a);
			callableStatement.setString(19,data_valuta_da == null ? "" : data_valuta_da);
			callableStatement.setString(20,data_valuta_a == null ? "" : data_valuta_a);
			callableStatement.setString(21,data_contabile_da == null ? "" : data_contabile_da);
			callableStatement.setString(22,data_contabile_a == null ? "" : data_contabile_a);
			callableStatement.registerOutParameter(23, Types.VARCHAR);
			callableStatement.registerOutParameter(24, Types.INTEGER);
			callableStatement.registerOutParameter(25, Types.INTEGER);
			callableStatement.registerOutParameter(26, Types.INTEGER);
			callableStatement.registerOutParameter(27, Types.SMALLINT);
			callableStatement.setString(28, codiceCab == null ? "" :  codiceCab);
			if(callableStatement.execute())	{
				pageInfo = new PageInfo();
				pageInfo.setPageNumber(pageNumber);
				pageInfo.setRowsPerPage(rowsPerPage);
				pageInfo.setFirstRow(callableStatement.getInt(24));
				pageInfo.setLastRow(callableStatement.getInt(25));
				pageInfo.setNumRows(callableStatement.getInt(26));
				pageInfo.setNumPages(callableStatement.getInt(27));

				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;

		} catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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

	public List<RiepilogoMovimentiCBI> getListaMovimentiGroupedXml(
			int chiaveMovimento, String tx_societa, String tx_provincia, String tx_utente, String mostraMovimenti, String tx_tipo_carta,
			String codiceAbi, String codiceCab, String codiceSia, String cro, String ccb, String nomeSupporto, String importo_da, String importo_a, 
			String data_creazione_da, String data_creazione_a, String data_valuta_da, String data_valuta_a,
			String data_contabile_da, String data_contabile_a) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try{
			BigDecimal importo_da_bd = new BigDecimal("0");
			BigDecimal importo_a_bd = new BigDecimal("0");
			if((importo_da != null)&&(!importo_da.equals("")))importo_da_bd = new BigDecimal(importo_da);
			if((importo_a != null)&&(!importo_a.equals("")))importo_a_bd = new BigDecimal(importo_a);
			callableStatement = prepareCall(Routines.TMG_RECUPERA_MOVIMENTI_GROUPED.routine());
			callableStatement.setLong(1, chiaveMovimento);
			callableStatement.setString(2, (tx_societa == null) || (tx_societa.equals(StoredProcConf.SOCIETA_ALL_VALUE.getParam())) ? "" :  tx_societa);
			callableStatement.setString(3, (tx_provincia == null) || (tx_provincia.equals(StoredProcConf.PROVINCIA_ALL_VALUE.getParam())) ? "" :  tx_provincia);
			callableStatement.setString(4, (tx_utente == null) || (tx_utente.equals(StoredProcConf.UTENTE_ALL_VALUE.getParam())) ? "" :  tx_utente);
			callableStatement.setString(5, mostraMovimenti == null ? "" :mostraMovimenti);
			callableStatement.setString(6, (tx_tipo_carta == null) ||(tx_tipo_carta.equals(StoredProcConf.CARTA_ALL_VALUE.getParam())) ? "" :  tx_tipo_carta);
			callableStatement.setString(7, codiceAbi == null ? "" :  codiceAbi);
			callableStatement.setString(8, codiceSia == null ? "" :  codiceSia);
			callableStatement.setString(9, cro == null ? "" :  cro);
			callableStatement.setString(10, ccb == null ? "" :  ccb);
			callableStatement.setString(11, nomeSupporto == null ? "" :  nomeSupporto);
			callableStatement.setBigDecimal(12, importo_da_bd);
			callableStatement.setBigDecimal(13, importo_a_bd);
			callableStatement.setString(14,data_creazione_da == null ? "" : data_creazione_da);
			callableStatement.setString(15,data_creazione_a == null ? "" : data_creazione_a);
			callableStatement.setString(16,data_valuta_da == null ? "" : data_valuta_da);
			callableStatement.setString(17,data_valuta_a == null ? "" : data_valuta_a);
			callableStatement.setString(18,data_contabile_da == null ? "" : data_contabile_da);
			callableStatement.setString(19,data_contabile_a == null ? "" : data_contabile_a);
			callableStatement.setString(20, codiceCab == null ? "" :  codiceCab);
			List<RiepilogoMovimentiCBI> list = new ArrayList<RiepilogoMovimentiCBI>();
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
				while(data.next()) {
					RiepilogoMovimentiCBI bean = new RiepilogoMovimentiCBI(data);
					list.add(bean);
				}
			}
			return list;

		} catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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

	//STEP2	
	//	public boolean elaboraMovimentiQuadratura() throws DaoException {
	public boolean elaboraMovimentiQuadratura(String quadrareMovimentiSquadrati) throws DaoException {
		CallableStatement callableStatement = null;
		try{
			callableStatement = prepareCall(Routines.TMG_ELABORA_MOVIMENTI.routine());
			//			callableStatement.setString(1, quadrareMovimentiSquadrati);			
			if (callableStatement.execute()) {
				return true;
			}
			return false;
		} 
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeStatement(callableStatement);
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

	public boolean forzaChiusuraQuadratura(int chiaveQuadratura, String user) throws DaoException {
		CallableStatement callableStatement = null;
		boolean risultato = false;
		try{
			callableStatement = prepareCall(Routines.TMG_CHIUDI_MOVIMENTO.routine());
			callableStatement.setLong(1, chiaveQuadratura);
			callableStatement.setString(2,user);
			callableStatement.execute();
			risultato = true;
		} 
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeStatement(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}

		return risultato;
	}

	public String recuperaMovimentiAperti() throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try{
			callableStatement = prepareCall(Routines.TMG_RECUPERA_MOVIMENTI_APERTI.routine());
			if(callableStatement.execute())	{
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;
		} 
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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

	//flusso fittizio
	public int assegnaTranAFittizio(String chiaveQuadratura, String multiGTW, String user) throws DaoException {
		CallableStatement callableStatement = null;
		int risultato;
		try{
			if (multiGTW.equals("0"))
				callableStatement = prepareCall(Routines.TRA_ASSOCIA_FITTIZIO.routine());
			else
				callableStatement = prepareCall(Routines.TRA_ASSOCIA_FITTIZIO_MULTIGTW.routine());

			callableStatement.setString(1, chiaveQuadratura);
			callableStatement.setString(2,user);
			callableStatement.registerOutParameter(3, Types.INTEGER);		
			callableStatement.execute();
			risultato = callableStatement.getInt(3);
		} 
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeStatement(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}

		return risultato;
	}

}
