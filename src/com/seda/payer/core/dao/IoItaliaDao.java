package com.seda.payer.core.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.IoItaliaConfigurazione;
import com.seda.payer.core.bean.IoItaliaConfigurazioniList;
import com.seda.payer.core.bean.IoItaliaFornitura;
import com.seda.payer.core.bean.IoItaliaFornitureList;
import com.seda.payer.core.bean.IoItaliaMessaggiList;
import com.seda.payer.core.bean.IoItaliaMessaggio;
import com.seda.payer.core.bean.IoItaliaPagamentoInAttesa;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class IoItaliaDao extends BaseDaoHandler {

	public IoItaliaDao(Connection connection, String schema) {
		super(connection, schema);
	}

//	public String selectCodiceSocieta(String cutecute, String chiaveEnte, String tipologiaServizio,
//			String impostaServizio) throws DaoException {
//		String codiceSocieta = "";
//		CallableStatement cs = null;
//
//		try {
//			cs = prepareCall(Routines.PYIICSP_SEL5.routine());
//
//			int p = 1;
//			cs.setString(p++, cutecute);
//			cs.setString(p++, chiaveEnte);
//			cs.setString(p++, tipologiaServizio);
//			cs.setString(p++, impostaServizio);
//
//			try (ResultSet rs = cs.executeQuery()) {
//				if (rs.next())
//					codiceSocieta = rs.getString("IIC_CSOCCSOC");
//			}
//		} catch (SQLException | HelperException e) {
//			throw new DaoException(e);
//		} finally {
//			if (cs != null) {
//				try {
//					cs.close();
//				} catch (SQLException e) {
//				}
//			}
//		}
//
//		return codiceSocieta;
//	}

	public long insertConfigurazione(String codiceSocieta, String codiceUtente, String codiceEnte, String tipologiaServizio, String impostaServizio, String wsKey1, String wsKey2, String ioKey1, String ioKey2, String email, boolean abilitato, BigDecimal importoDa, BigDecimal importoA) throws DaoException {
		long idConfigurazione = 0;
		CallableStatement cs = null;
		try {
			cs = prepareCall(Routines.PYIICSP_INS.routine());
			int p = 1;
			cs.setString(p++, codiceSocieta);
			cs.setString(p++, codiceUtente);
			cs.setString(p++, codiceEnte);
			cs.setString(p++, tipologiaServizio);
			cs.setString(p++, impostaServizio);
			cs.setString(p++, wsKey1);
			cs.setString(p++, wsKey2);
			cs.setString(p++, ioKey1);
			cs.setString(p++, ioKey2);
			cs.setString(p++, email);
			cs.setString(p++, abilitato ? "1" : "0");
			cs.setBigDecimal(p++, importoDa);
			cs.setBigDecimal(p++, importoA);
			cs.registerOutParameter(p++, Types.INTEGER);
			cs.registerOutParameter(p++, Types.BIGINT);
			cs.execute();
			idConfigurazione = cs.getLong(15);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			if (cs != null) {
				try { cs.close(); } catch (SQLException e) { }
			}
		}
		return idConfigurazione;
	}

//	YLM PG22XX06 INI
	public IoItaliaConfigurazione selectConfigurazione(String codiceSocieta, String codiceUtente, String codiceEnte, String tipologiaServizio, String impostaServizio) throws DaoException {
		IoItaliaConfigurazione configurazione = selectConfigurazioneTail ( codiceSocieta,  codiceUtente,  codiceEnte,  tipologiaServizio,  impostaServizio, false );
		return configurazione;
	}
//	YLM PG22XX06 FINE
	
	public IoItaliaConfigurazione selectConfigurazioneTail(String codiceSocieta, String codiceUtente, String codiceEnte, String tipologiaServizio, String impostaServizio , boolean evol) throws DaoException {
		IoItaliaConfigurazione configurazione = null;
		CallableStatement cs = null;
		try {
//    		YLM PG22XX06 INI
    		if (evol) {
    			cs = prepareCall(Routines.PYIICSP_SEL_APPIO2.routine());
    		} else {
    			cs = prepareCall(Routines.PYIICSP_SEL2.routine());		
    		}
//    		YLM PG22XX06 FINE
    		System.out.println("selectConfigurazioneTail evol = " + evol);
    		System.out.println("selectConfigurazioneTail codiceSocieta = " + codiceSocieta);
    		System.out.println("selectConfigurazioneTail codiceUtente = " + codiceUtente);
    		System.out.println("selectConfigurazioneTail codiceEnte = " + codiceEnte);
    		System.out.println("selectConfigurazioneTail tipologiaServizio = " + tipologiaServizio);
    		System.out.println("selectConfigurazioneTail impostaServizio = " + impostaServizio);
			int p = 1;
			cs.setString(p++, codiceSocieta);
			cs.setString(p++, codiceUtente);
			cs.setString(p++, codiceEnte);
			cs.setString(p++, tipologiaServizio);
			cs.setString(p++, impostaServizio);
			//inizio LP 20240810 - PGNTCORE-24
			//ResultSet rs = cs.executeQuery();
			if(cs.execute()) {
				ResultSet rs = cs.getResultSet();
				if(rs != null) {
			//fine LP 20240810 - PGNTCORE-24
					try {
						if (rs.next())
							configurazione = new IoItaliaConfigurazione(rs);
					} finally {
						if (rs != null)
							try { rs.close(); } catch (SQLException e) { }
					}
			//inizio LP 20240810 - PGNTCORE-24
				}
			}
			//fine LP 20240810 - PGNTCORE-24
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			if (cs != null) {
				try { cs.close(); } catch (SQLException e) { }
			}
		}
		return configurazione;
	}

//	YLM PG22XX06 INI
	public IoItaliaConfigurazione selectConfigurazione(long idConfigurazione) throws DaoException {
		IoItaliaConfigurazione configurazione = selectConfigurazioneTail (idConfigurazione, false );
		return configurazione;
	}
//	YLM PG22XX06 FINE
	public IoItaliaConfigurazione selectConfigurazioneTail(long idConfigurazione,boolean evol ) throws DaoException {
		IoItaliaConfigurazione configurazione = null;
		CallableStatement cs = null;
		try {
//    		YLM PG22XX06 INI
    		if (evol) {
    			cs = prepareCall(Routines.PYIICSP_SEL_APPIO.routine());
    		} else {
    			cs = prepareCall(Routines.PYIICSP_SEL.routine());		
    		}
//    		YLM PG22XX06 FINE
			int p = 1;
			cs.setLong(p++, idConfigurazione);
			//inizio LP 20240810 - PGNTCORE-24
			//ResultSet rs = cs.executeQuery();
			if(cs.execute()) {
				ResultSet rs = cs.getResultSet();
				if(rs != null) {
			//fine LP 20240810 - PGNTCORE-24
				try {
					if (rs.next())
						configurazione = new IoItaliaConfigurazione(rs);
				} finally {
					if (rs != null) {
						try { rs.close(); } catch (SQLException e) { }
					}
				}
			//inizio LP 20240810 - PGNTCORE-24
				}
			}
			//fine LP 20240810 - PGNTCORE-24
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			if (cs != null) {
				try { cs.close(); } catch (SQLException e) { }
			}
		}
		return configurazione;
	}

//	YLM PG22XX06 INI
	public IoItaliaConfigurazione selectConfigurazione(String wsKey1) throws DaoException {
		IoItaliaConfigurazione configurazione = selectConfigurazioneTail (wsKey1, false);
		return configurazione;
	}
//	YLM PG22XX06 FINE

	public IoItaliaConfigurazione selectConfigurazioneTail(String wsKey1, boolean evol) throws DaoException {
		IoItaliaConfigurazione configurazione = null;
		CallableStatement cs = null;
		try {
//    		YLM PG22XX06 INI
    		if (evol) {
    			cs = prepareCall(Routines.PYIICSP_SEL_APPIO3.routine());
    		} else {
    			cs = prepareCall(Routines.PYIICSP_SEL3.routine());		
    		}
//			cs = prepareCall(Routines.PYIICSP_SEL3.routine());
//    		YLM PG22XX06 FINE
			int p = 1;
			cs.setString(p++, wsKey1);
			//inizio LP 20240810 - PGNTCORE-24
			//ResultSet rs = cs.executeQuery();
			if(cs.execute()) {
				ResultSet rs = cs.getResultSet();
				if(rs != null) {
			//fine LP 20240810 - PGNTCORE-24
				try {
					if (rs.next())
						configurazione = new IoItaliaConfigurazione(rs);
				} finally {
					if (rs != null) {
						try { rs.close(); } catch (SQLException e) { }
					}
				}
			//inizio LP 20240810 - PGNTCORE-24
				}
			}
			//fine LP 20240810 - PGNTCORE-24
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			if (cs != null) {
				try { cs.close(); } catch (SQLException e) { }
			}
		}
		return configurazione;
	}
	
//	public IoItaliaConfigurazione selectConfigurazioneByIoKeys(String codiceSocieta, String codiceUtente, String codiceEnte, String ioKey1, String ioKey2) throws DaoException {
//		
//		IoItaliaConfigurazione configurazione = null;
//		
//		CallableStatement cs = null;
//		
//		try {
//			
//			cs = prepareCall(Routines.PYIICSP_SEL4.routine());
//			
//			int p = 1;
//			cs.setString(p++, codiceSocieta);
//			cs.setString(p++, codiceUtente);
//			cs.setString(p++, codiceEnte);
//			cs.setString(p++, ioKey1);
//			cs.setString(p++, ioKey2);
//
//			try (ResultSet rs = cs.executeQuery()) {
//				if (rs.next())
//					configurazione = new IoItaliaConfigurazione(rs);
//			}
//		} catch (SQLException | HelperException e) {
//			throw new DaoException(e);
//		} finally {
//			if (cs != null) {
//				try { cs.close(); } catch (SQLException e) { }
//			}
//		}
//
//		return configurazione;
//	}

	public int updateConfigurazione(IoItaliaConfigurazione configurazione) throws DaoException {
		int numRighe = 0;
		CallableStatement cs = null;
		try {
			int p = 1;
			cs = prepareCall(Routines.PYIICSP_UPD.routine());
			cs.setLong(p++, configurazione.getIdConfigurazione());
			cs.setString(p++, configurazione.getCodiceSocieta());
			cs.setString(p++, configurazione.getCodiceUtente());
			cs.setString(p++, configurazione.getCodiceEnte());
			cs.setString(p++, configurazione.getTipologiaServizio());
			cs.setString(p++, configurazione.getImpostaServizio());
			cs.setString(p++, configurazione.getWsKey1());
			cs.setString(p++, configurazione.getWsKey2());
			cs.setString(p++, configurazione.getIoKey1());
			cs.setString(p++, configurazione.getIoKey2());
			cs.setString(p++, configurazione.getEmail());
			cs.setString(p++, configurazione.isAbilitato() ? "1" : "0");
			cs.setBigDecimal(p++, configurazione.getImportoDa());
			cs.setBigDecimal(p++, configurazione.getImportoA());
			cs.registerOutParameter(p++, Types.INTEGER);
			cs.execute();
			numRighe = cs.getInt(15);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			if (cs != null) {
				try { cs.close(); } catch (SQLException e) { }
			}
		}
		return numRighe;
	}
	
	public int deleteConfigurazione(long idConfigurazione) throws DaoException {
		int numRighe = 0;
		CallableStatement cs = null;
		try {
			int p = 1;
			cs = prepareCall(Routines.PYIICSP_DEL.routine());
			cs.setLong(p++, idConfigurazione);
			cs.registerOutParameter(p++, Types.INTEGER);
			cs.execute();
			numRighe = cs.getInt(2);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			if (cs != null) {
				try { cs.close(); } catch (SQLException e) { }
			}
		}
		return numRighe;
		
	}

//	YLM PG22XX06 INI
	public IoItaliaConfigurazioniList configurazioniList(String codiceSocieta, String codiceUtente, String codiceEnte,
			String tipologiaServizio, String impostaServizio, int pageNumber, int rowsPerPage, String orderBy) throws DaoException {
		
		IoItaliaConfigurazioniList ioItaliaConfigurazioniList = configurazioniListTail (codiceSocieta,codiceUtente,codiceEnte,tipologiaServizio,impostaServizio,pageNumber,rowsPerPage,orderBy, false );
		
		return ioItaliaConfigurazioniList;
	}
//	YLM PG22XX06 FINE

	public IoItaliaConfigurazioniList configurazioniListTail(String codiceSocieta, String codiceUtente, String codiceEnte,
			String tipologiaServizio, String impostaServizio, int pageNumber, int rowsPerPage, String orderBy, boolean evol) throws DaoException {
		
		IoItaliaConfigurazioniList ioItaliaConfigurazioniList = null;
		CallableStatement cs = null;
		ResultSet data = null;
		try {
			int p = 1;
//    		YLM PG22XX06 INI
    		if (evol) {
    			cs = prepareCall(Routines.PYIICSP_LST_APPIO.routine());
    		} else {
    			cs = prepareCall(Routines.PYIICSP_LST.routine());		
    		}
//    		YLM PG22XX06 FINE
			cs.setString(p++, codiceSocieta);
			cs.setString(p++, codiceUtente);
			cs.setString(p++, codiceEnte);
			cs.setString(p++, tipologiaServizio);
			cs.setString(p++, impostaServizio);
			cs.setString(p++, orderBy);
			cs.setInt(p++, rowsPerPage);
			cs.setInt(p++, pageNumber);
			cs.registerOutParameter(p++, Types.INTEGER);
			cs.registerOutParameter(p++, Types.INTEGER);
			cs.registerOutParameter(p++, Types.INTEGER);
			cs.registerOutParameter(p++, Types.SMALLINT);
			if (cs.execute()) {
				ioItaliaConfigurazioniList = new IoItaliaConfigurazioniList();
				ioItaliaConfigurazioniList.setPageNumber(pageNumber);
				ioItaliaConfigurazioniList.setRowsPerPage(rowsPerPage);
				ioItaliaConfigurazioniList.setFirstRow(cs.getInt(9));
				ioItaliaConfigurazioniList.setLastRow(cs.getInt(10));
				ioItaliaConfigurazioniList.setNumRows(cs.getInt(11));
				ioItaliaConfigurazioniList.setNumPages(cs.getInt(12));
				data = cs.getResultSet();
				loadWebRowSet(data);
				ioItaliaConfigurazioniList.setConfigurazioniListXml(getWebRowSetXml());
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			if (data != null) {
				try { data.close(); } catch (SQLException e) { }
			}
			if (cs != null) {
				try { cs.close(); } catch (SQLException e) { }
			}
		}
		return ioItaliaConfigurazioniList;
	}

   	public long insertFornitura(String codiceSocieta, String codiceUtente, String codiceEnte,
			String tipologiaServizio, String impostaServizio, String codiceFornitura) throws DaoException {
		
		long idFornitura = 0;
		CallableStatement cs = null;
		try {
			int p = 1;
			cs = prepareCall(Routines.PYFORSP_INS.routine());
			cs.setString(p++, codiceSocieta);
			cs.setString(p++, codiceUtente);
			cs.setString(p++, codiceEnte);
			cs.setString(p++, tipologiaServizio);
			cs.setString(p++, impostaServizio);
			cs.setString(p++, codiceFornitura);
			cs.registerOutParameter(p++, Types.INTEGER);
			cs.registerOutParameter(p++, Types.BIGINT);
			cs.execute();
			idFornitura = cs.getLong(8);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			if (cs != null) {
				try { cs.close(); } catch (SQLException e) { }
			}
		}
		return idFornitura;
	}

//	YLM PG22XX06 INI
   	public IoItaliaFornitura selectFornitura(long idFornitura) throws DaoException {
   		IoItaliaFornitura fornitura  = selectFornituraTail (idFornitura,false );
		return fornitura;
	}
//	YLM PG22XX06 FINE

	public IoItaliaFornitura selectFornituraTail(long idFornitura, boolean evol) throws DaoException {
		IoItaliaFornitura fornitura = null;
		CallableStatement cs = null;
		try {
			int p = 1;
//    		YLM PG22XX06 INI
    		if (evol) {
    			cs = prepareCall(Routines.PYFORSP_SEL_APPIO.routine());
    		} else {
    			cs = prepareCall(Routines.PYFORSP_SEL.routine());		
    		}
//    		YLM PG22XX06 FINE
			cs.setLong(p++, idFornitura);
			//inizio LP 20240810 - PGNTCORE-24
			//ResultSet rs = cs.executeQuery();
			if(cs.execute()) {
				ResultSet rs = cs.getResultSet();
				if(rs != null) {
			//fine LP 20240810 - PGNTCORE-24
					try {
						if (rs.next())
							fornitura = new IoItaliaFornitura(rs);
					} finally {
						if (rs != null) {
							try { rs.close(); } catch (SQLException e) { }
						}
					}
			//inizio LP 20240810 - PGNTCORE-24
				}
			}
			//fine LP 20240810 - PGNTCORE-24
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			if (cs != null) {
				try { cs.close(); } catch (SQLException e) { }
			}
		}
		return fornitura;
	}

//	YLM PG22XX06 INI
	public IoItaliaFornitureList fornitureList(String codiceSocieta, String codiceUtente, String provincia, String codiceEnte,
			String tipologiaServizio, String impostaServizio, String codiceFornitura, String codiceFiscale, String dataInserimentoDa,
			String dataInserimentoA, String esito, int pageNumber, int rowsPerPage, String orderBy) throws DaoException {
		
		IoItaliaFornitureList ioItaliaFornitureList = fornitureListTail (codiceSocieta, codiceUtente, provincia, codiceEnte,
				tipologiaServizio, impostaServizio, codiceFornitura, codiceFiscale, dataInserimentoDa,
				dataInserimentoA, esito, pageNumber, rowsPerPage, orderBy ,false );
		
		return ioItaliaFornitureList;
	}
//	YLM PG22XX06 FINE

	public IoItaliaFornitureList fornitureListTail(String codiceSocieta, String codiceUtente, String provincia, String codiceEnte,
			String tipologiaServizio, String impostaServizio, String codiceFornitura, String codiceFiscale, String dataInserimentoDa,
			String dataInserimentoA, String esito, int pageNumber, int rowsPerPage, String orderBy, boolean evol) throws DaoException {

		IoItaliaFornitureList ioItaliaFornitureList = null;
		CallableStatement cs = null;
		ResultSet data = null;
		try {
			int p = 1;
//    		YLM PG22XX06 INI
    		if (evol) {
    			cs = prepareCall(Routines.PYFORSP_LST_APPIO.routine());
    		} else {
    			cs = prepareCall(Routines.PYFORSP_LST.routine());		
    		}
//    		YLM PG22XX06 FINE
			cs.setString(p++, codiceSocieta);
			cs.setString(p++, codiceUtente);
			cs.setString(p++, provincia);
			cs.setString(p++, codiceEnte);
			cs.setString(p++, tipologiaServizio);
			cs.setString(p++, impostaServizio);
			cs.setString(p++, codiceFornitura);
			cs.setString(p++, codiceFiscale);
			cs.setString(p++, dataInserimentoDa);
			cs.setString(p++, dataInserimentoA);
			cs.setString(p++, (esito == null || esito.equals("") ? "" : (esito.equals("OK") ? "1" : "0")));
			cs.setString(p++, orderBy);
			cs.setInt(p++, rowsPerPage);
			cs.setInt(p++, pageNumber);
			cs.registerOutParameter(p++, Types.INTEGER);
			cs.registerOutParameter(p++, Types.INTEGER);
			cs.registerOutParameter(p++, Types.INTEGER);
			cs.registerOutParameter(p++, Types.SMALLINT);
			if (cs.execute()) {
				ioItaliaFornitureList = new IoItaliaFornitureList();
				ioItaliaFornitureList.setPageNumber(pageNumber);
				ioItaliaFornitureList.setRowsPerPage(rowsPerPage);
				ioItaliaFornitureList.setFirstRow(cs.getInt(15));
				ioItaliaFornitureList.setLastRow(cs.getInt(16));
				ioItaliaFornitureList.setNumRows(cs.getInt(17));
				ioItaliaFornitureList.setNumPages(cs.getInt(18));
				data = cs.getResultSet();
				loadWebRowSet(data);
				ioItaliaFornitureList.setFornitureListXml(getWebRowSetXml());
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			if (data != null) {
				try { data.close(); } catch (SQLException e) { }
			}
			if (cs != null) {
				try { cs.close(); } catch (SQLException e) { }
			}
		}
		return ioItaliaFornitureList;
	}

	public long insertMessaggio(IoItaliaMessaggio messaggio) {
		CallableStatement cs = null;
		long idMessaggio = 0;
		try {
			cs = prepareCall(Routines.PYMESSP_INS.routine());
			cs.setString(1, messaggio.getCutecute());
			cs.setString(2, messaggio.getIdDominio());
			cs.setString(3, messaggio.getTipologiaServizio());
			cs.setString(4, messaggio.getTimestampParsingFile());
			cs.setInt(5, messaggio.getPosizione());
			cs.setString(6, messaggio.getCodiceFiscale());
			cs.setString(7, messaggio.getOggettoMessaggio());
			cs.setString(8, messaggio.getCorpoMessaggio());
			cs.setDate(9, new java.sql.Date(messaggio.getDataScadenzaMessaggio().getTime()));
			cs.setBigDecimal(10, messaggio.getImporto());
			cs.setString(11, messaggio.getAvvisoPagoPa());
			cs.setString(12, messaggio.getScadenzaPagamento());
			cs.setString(13, messaggio.getEmail());
			cs.setString(14, "0");
			cs.setLong(15, messaggio.getIdFornitura());
			cs.setString(16, messaggio.getImpostaServizio());
			cs.registerOutParameter(17, Types.INTEGER);
			cs.registerOutParameter(18, Types.BIGINT);
			cs.execute();
			idMessaggio = cs.getLong(18);
		} catch (SQLException e) {
			new DaoException(e);
		} catch (IllegalArgumentException e) {
			new DaoException(e);
		} catch (HelperException e) {
			new DaoException(e);
		} finally {
			if (cs != null) {
				try { cs.close(); } catch (SQLException e) { }
			}
		}
		return idMessaggio;
	}

	public int updateMessaggio(IoItaliaMessaggio messaggio) {
		CallableStatement cs = null;
		int totRows = 0;
		try {
			cs = prepareCall(Routines.PYMESSP_UPD.routine());
			cs.setLong(1, messaggio.getIdMessaggio());
			cs.setString(2, messaggio.getTimestampParsingFile());
			cs.setString(3, messaggio.getCodiceFiscale());
			cs.setString(4, messaggio.getOggettoMessaggio());
			cs.setString(5, messaggio.getCorpoMessaggio());
			cs.setDate(6, new java.sql.Date(messaggio.getDataScadenzaMessaggio().getTime()));
			cs.setBigDecimal(7, messaggio.getImporto());
			cs.setString(8, messaggio.getAvvisoPagoPa());
			cs.setString(9, messaggio.getScadenzaPagamento());
			cs.setString(10, messaggio.getEmail());
			cs.registerOutParameter(11, Types.INTEGER);
			cs.execute();
			totRows = cs.getInt(11);
		} catch (SQLException e) {
			new DaoException(e);
		} catch (IllegalArgumentException e) {
			new DaoException(e);
		} catch (HelperException e) {
			new DaoException(e);
		} finally {
			if (cs != null) {
				try { cs.close(); } catch (SQLException e) { }
			}
		}
		return totRows;
	}

	public int updateStatoInvioMessaggio(IoItaliaMessaggio messaggio) throws DaoException {
		CallableStatement cs = null;
		int totRows = 0;
		try {
			cs = prepareCall(Routines.PYMESSP_UPD_STA.routine());
			cs.setLong(1, messaggio.getIdMessaggio());
			cs.setString(2, messaggio.getStato());
			cs.setString(3, messaggio.getMessaggioErrore());
			cs.setDate(4, new java.sql.Date(messaggio.getDataInvioMessaggio().getTime()));
			cs.setString(5, messaggio.getIdInvioMessaggio());
			cs.registerOutParameter(6, Types.INTEGER);
			cs.execute();
			totRows = cs.getInt(6);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			if (cs != null) {
				try { cs.close(); } catch (SQLException e) { }
			}
		}
		return totRows;
	}

	public List<IoItaliaMessaggio> getListaMessaggi(String cutecute, String stato) throws DaoException {
		CallableStatement cs = null;
		ResultSet rs = null;
		List<IoItaliaMessaggio> listMessaggioEnte = new ArrayList<IoItaliaMessaggio>();
		try {
			cs = prepareCall(Routines.PYMESSP_SEL_STA.routine());
			cs.setString(1, cutecute);
			cs.setString(2, stato);
			//inizio LP 20240810 - PGNTCORE-24
			//rs = cs.executeQuery();
			if(cs.execute()) {
				rs = cs.getResultSet();
				if(rs != null ) {
			//fine LP 20240810 - PGNTCORE-24
					while (rs.next()) {
						listMessaggioEnte.add(new IoItaliaMessaggio(rs));
					}
			//inizio LP 20240810 - PGNTCORE-24
				}
			}
			//fine LP 20240810 - PGNTCORE-24
 		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { }
			}
			if (cs != null) {
				try { cs.close(); } catch (SQLException e) { }
			}
		}
		return listMessaggioEnte;
	}

	public int deleteMessaggio(long idMessaggio) {
		CallableStatement cs = null;
		int totRows = 0;
		try {
			cs = prepareCall(Routines.PYMESSP_DEL.routine());
			cs.setLong(1, idMessaggio);
			cs.registerOutParameter(2, Types.INTEGER);
			cs.execute();
			totRows = cs.getInt(2);
		} catch (SQLException e) {
			new DaoException(e);
		} catch (IllegalArgumentException e) {
			new DaoException(e);
		} catch (HelperException e) {
			new DaoException(e);
		} finally {
			if (cs != null) {
				try { cs.close(); } catch (SQLException e) { }
			}
		}
		return totRows;
	}

	public IoItaliaMessaggiList messaggiList(long idFornitura, String codiceFiscale, String esito, int pageNumber, int rowsPerPage, String orderBy) throws DaoException {
		IoItaliaMessaggiList ioItaliaMessaggiList = null;
		CallableStatement cs = null;
		ResultSet data = null;
		try {
			int p = 1;
			cs = prepareCall(Routines.PYMESSP_LST.routine());
			cs.setLong(p++, idFornitura);
			cs.setString(p++, codiceFiscale);
			cs.setString(p++, (esito == null || esito.equals("") ? "" : (esito.equals("OK") ? "1" : "0")));
			cs.setString(p++, orderBy);
			cs.setInt(p++, rowsPerPage);
			cs.setInt(p++, pageNumber);
			cs.registerOutParameter(p++, Types.INTEGER);
			cs.registerOutParameter(p++, Types.INTEGER);
			cs.registerOutParameter(p++, Types.INTEGER);
			cs.registerOutParameter(p++, Types.SMALLINT);
			if (cs.execute()) {
				ioItaliaMessaggiList = new IoItaliaMessaggiList();
				ioItaliaMessaggiList.setPageNumber(pageNumber);
				ioItaliaMessaggiList.setRowsPerPage(rowsPerPage);
				ioItaliaMessaggiList.setFirstRow(cs.getInt(7));
				ioItaliaMessaggiList.setLastRow(cs.getInt(8));
				ioItaliaMessaggiList.setNumRows(cs.getInt(9));
				ioItaliaMessaggiList.setNumPages(cs.getInt(10));
				data = cs.getResultSet();
				loadWebRowSet(data);
				ioItaliaMessaggiList.setMessaggiListXml(getWebRowSetXml());
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			if (data != null) {
				try { data.close(); } catch (SQLException e) { }
			}
			if (cs != null) {
				try { cs.close(); } catch (SQLException e) { }
			}
		}
		return ioItaliaMessaggiList;
	}

	public IoItaliaMessaggiList messaggiListAPPIO(long idFornitura, String codiceFiscale, String esito, int pageNumber, int rowsPerPage, String orderBy) throws DaoException {
		IoItaliaMessaggiList ioItaliaMessaggiList = null;
		CallableStatement cs = null;
		ResultSet data = null;
		try {
			int p = 1;
			cs = prepareCall(Routines.PYMESSP_LST_APPIO.routine());
			cs.setLong(p++, idFornitura);
			cs.setString(p++, codiceFiscale);
			cs.setString(p++, (esito == null || esito.equals("") ? "" : (esito.equals("OK") ? "1" : "0")));
			cs.setString(p++, orderBy);
			cs.setInt(p++, rowsPerPage);
			cs.setInt(p++, pageNumber);
			cs.registerOutParameter(p++, Types.INTEGER);
			cs.registerOutParameter(p++, Types.INTEGER);
			cs.registerOutParameter(p++, Types.INTEGER);
			cs.registerOutParameter(p++, Types.SMALLINT);
			if (cs.execute()) {
				ioItaliaMessaggiList = new IoItaliaMessaggiList();
				ioItaliaMessaggiList.setPageNumber(pageNumber);
				ioItaliaMessaggiList.setRowsPerPage(rowsPerPage);
				ioItaliaMessaggiList.setFirstRow(cs.getInt(7));
				ioItaliaMessaggiList.setLastRow(cs.getInt(8));
				ioItaliaMessaggiList.setNumRows(cs.getInt(9));
				ioItaliaMessaggiList.setNumPages(cs.getInt(10));
				data = cs.getResultSet();
				loadWebRowSet(data);
				ioItaliaMessaggiList.setMessaggiListXml(getWebRowSetXml());
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			if (data != null) {
				try { data.close(); } catch (SQLException e) { }
			}
			if (cs != null) {
				try { cs.close(); } catch (SQLException e) { }
			}
		}
		return ioItaliaMessaggiList;
	}

	public IoItaliaMessaggio selectMessaggio(long idMessaggio) throws DaoException {
		IoItaliaMessaggio messaggio = null;
		CallableStatement cs = null;
		try {
			cs = prepareCall(Routines.PYMESSP_SEL.routine());
			int p = 1;
			cs.setLong(p++, idMessaggio);
			//inizio LP 20240810 - PGNTCORE-24
			//ResultSet rs = cs.executeQuery();
			if(cs.execute()) {
				ResultSet rs = cs.getResultSet();
				if(rs != null) {
			//fine LP 20240810 - PGNTCORE-24
					try {
						if (rs.next())
							messaggio = new IoItaliaMessaggio(rs);
					} finally {
						if (rs!=null) {
							try { rs.close(); } catch (SQLException e) { }
						}
					}
			//inizio LP 20240810 - PGNTCORE-24
				}
			}
			//fine LP 20240810 - PGNTCORE-24
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		//inizio LP 20240810 - PGNTCORE-24
		} finally {
			if (cs != null) {
				try { cs.close(); } catch (SQLException e) { }
			}
		//fine LP 20240810 - PGNTCORE-24
		}
		return messaggio;
	}

	public List<IoItaliaPagamentoInAttesa> getArchivioPagamentiInAttesa(IoItaliaConfigurazione configurazione) throws DaoException {
		List<IoItaliaPagamentoInAttesa> lista = new ArrayList<IoItaliaPagamentoInAttesa>();
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			cs = prepareCall(Routines.PYEH2SP_IOIT.routine());
			cs.setString(1, configurazione.getCodiceUtente());
			cs.setString(2, configurazione.getCodiceSocieta());
			cs.setString(3, configurazione.getCodiceEnte());
			cs.setString(4, configurazione.getTipologiaServizio());
			cs.setString(5, configurazione.getImpostaServizio());
			LocalDate now = LocalDate.now();
			cs.setDate(6, Date.valueOf(now));
			cs.setDate(7, Date.valueOf(now.plusDays(30)));
			BigDecimal importoDa = configurazione.getImportoDa() == null ? BigDecimal.ZERO : configurazione.getImportoDa();
			BigDecimal importoA = configurazione.getImportoA() == null ? new BigDecimal(999999999) : configurazione.getImportoA();
			cs.setBigDecimal(8, importoDa);
			cs.setBigDecimal(9, importoA);
			//inizio LP 20240810 - PGNTCORE-24
			//rs = cs.executeQuery();
			if(cs.execute()) {
				rs = cs.getResultSet();
				if(rs != null) {
			//fine LP 20240810 - PGNTCORE-24
					while (rs.next())
						lista.add(new IoItaliaPagamentoInAttesa(rs));
			//inizio LP 20240810 - PGNTCORE-24
				}
			}
			//fine LP 20240810 - PGNTCORE-24
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { }
			}
			if (cs != null) {
				try { cs.close(); } catch (SQLException e) { }
			}
		}
		return lista;
	}

	public List<IoItaliaConfigurazione> getConfigurazioniAbilitate(String codiceUtente) throws DaoException {
		List<IoItaliaConfigurazione> configurazioniList = new ArrayList<IoItaliaConfigurazione>();
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			cs = prepareCall(Routines.PYIICSP_ABIL_SEL.routine());
			cs.setString(1, codiceUtente);
			//inizio LP 20240810 - PGNTCORE-24
			//rs = cs.executeQuery();
			if(cs.execute()) {
				rs = cs.getResultSet();
				if(rs != null) {
			//fine LP 20240810 - PGNTCORE-24
					while (rs.next())
						configurazioniList.add(new IoItaliaConfigurazione(rs));
			//inizio LP 20240810 - PGNTCORE-24
				}
			}
			//fine LP 20240810 - PGNTCORE-24
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { }
			}
			if (cs != null) {
				try { cs.close(); } catch (SQLException e) { }
			}
		}
		return configurazioniList;
	}

	public IoItaliaMessaggio selectMessaggio(String cutecute, String idDominio, String tipologiaServizio, String impostaServizio, String avvisoPagoPa) throws DaoException {
		IoItaliaMessaggio messaggio = null;
		CallableStatement cs = null;
		try {
			cs = prepareCall(Routines.PYMESSP_SEL2.routine());
			int p = 1;
			cs.setString(p++, cutecute);
			cs.setString(p++, idDominio);
			cs.setString(p++, tipologiaServizio);
			cs.setString(p++, impostaServizio);
			cs.setString(p++, avvisoPagoPa);
			//inizio LP 20240810 - PGNTCORE-24
			//ResultSet rs = cs.executeQuery();
			if(cs.execute()) {
				ResultSet rs = cs.getResultSet();
				if(rs != null) {
			//fine LP 20240810 - PGNTCORE-24
					try {
						if (rs.next())
							messaggio = new IoItaliaMessaggio(rs);
					} finally {
						if (rs != null) {
							try { rs.close(); } catch (SQLException e) { }
						}
					}
			//inizio LP 20240810 - PGNTCORE-24
				}
			}
			//fine LP 20240810 - PGNTCORE-24
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		//inizio LP 20240810 - PGNTCORE-24
		} finally {
			if (cs != null) {
				try { cs.close(); } catch (SQLException e) { }
			}
		//fine LP 20240810 - PGNTCORE-24
		}
		return messaggio;
	}

}
