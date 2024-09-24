package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
//inizio LP PG21XX04 Leak
//import com.seda.data.dao.DAOHelper;
//fine LP PG21XX04 Leak
import com.seda.data.helper.HelperException;
import com.seda.data.procedure.reflection.DriverType;
import com.seda.payer.core.bean.ArchivioCarichiDocumento;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class ElaborazioneFlussiDao extends BaseDaoHandler {

	public ElaborazioneFlussiDao(Connection connection, String schema) {
		super(connection, schema);
	}
	
	//inizio LP PG21XX04 Leak
	//private void closeConnection(CallableStatement callableStatement) {
	//	if (callableStatement != null)
	//		DAOHelper.closeIgnoringException(callableStatement);
	//}
	//fine LP PG21XX04 Leak

	//inizio LP 20240920 - PGNTECCSV-10
	public int doMaxPrgressivoFlussi(String codiceUtente, Date dataFlusso) throws DaoException {
		return doMaxPrgressivoFlussiBatch(true, true, codiceUtente, dataFlusso);
	}

	public int doMaxPrgressivoFlussiBatch(boolean bFlagUpdateAutocommit, boolean bCloseStat, String codiceUtente, Date dataFlusso) throws DaoException 
	//fine LP 20240920 - PGNTECCSV-10
	{
		CallableStatement callableStatement = null;	
		try	{
			//inizio LP 20240920 - PGNTECCSV-10
			callableStatement = prepareCall(bFlagUpdateAutocommit, Routines.ELG_DODETAIL_NEXT_PELGFILE.routine());
			//fine LP 20240920 - PGNTECCSV-10
			callableStatement.setString(1, codiceUtente);
			callableStatement.setDate(2, dataFlusso);
			/* we register row start */
			callableStatement.registerOutParameter(3, Types.INTEGER);
			callableStatement.execute();
			int i = callableStatement.getInt(3);
			return i;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			//inizio LP 20240920 - PGNTECCSV-10
			if(bCloseStat) {
			//fine LP 20240920 - PGNTECCSV-10
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240920 - PGNTECCSV-10
				callableStatement = null;
			//fine LP 20240920 - PGNTECCSV-10
			}
			//fine LP PG21XX04 Leak
		}
	}

	//inizio LP 20240920 - PGNTECCSV-10
	public int getProgressivoFlusso(String fileName) throws DaoException {
		return getProgressivoFlussoBatch(true, true, fileName);
	}

	public int getProgressivoFlussoBatch(boolean bFlagUpdateAutocommit, boolean bCloseStat, String fileName) throws DaoException {
	//fine LP 20240920 - PGNTECCSV-10
		CallableStatement callableStatement = null;	
		try	{
			//inizio LP 20240920 - PGNTECCSV-10
			callableStatement = prepareCall(bFlagUpdateAutocommit, Routines.ELG_DODETAIL_PELGFLUS_BY_NAME.routine());
			//fine LP 20240920 - PGNTECCSV-10
			callableStatement.setString(1, fileName);
			/* we register row start */
			callableStatement.registerOutParameter(2, Types.INTEGER);
			callableStatement.execute();
			int i = callableStatement.getInt(2);
			return i;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			//inizio LP 20240920 - PGNTECCSV-10
			if(bCloseStat) {
			//fine LP 20240920 - PGNTECCSV-10
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240920 - PGNTECCSV-10
				callableStatement = null;
			//fine LP 20240920 - PGNTECCSV-10
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	public int doUpdateActiveNewRecord(String tipoRecord) throws DaoException 
	{
		CallableStatement callableStatement = null;	
		try {
			callableStatement = prepareCall(Routines.EHX_DOUPDATE_ACTIVE_NEW.routine());	
			callableStatement.setString(1, tipoRecord);
			callableStatement.registerOutParameter(2, Types.INTEGER);
			callableStatement.execute();
			int i = callableStatement.getInt(2);
			return i;
			
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
	
	//PG170070_001 GG 20170517 - Introdotto flagElabStampaAvviso
	//inizio LP 20240920 - PGNTECCSV-10
	public int doInsertLogFlussi(String codiceUtente, Date dataFlusso, int progressivo, String procGestione, 
			            String tipoServizio, String nomeFile, Timestamp inizioElabFlusso, Timestamp fineElabFlusso, 
			            String flussiInElab, String flagElabStampaAvviso) throws DaoException {
		return doInsertLogFlussiBatch(true, true, codiceUtente, dataFlusso, progressivo, procGestione, tipoServizio, nomeFile, inizioElabFlusso, fineElabFlusso, flussiInElab, flagElabStampaAvviso);
	}

	public int doInsertLogFlussiBatch(boolean bFlagUpdateAutocommit, boolean bCloseStat, String codiceUtente, Date dataFlusso, int progressivo, String procGestione, 
            String tipoServizio, String nomeFile, Timestamp inizioElabFlusso, Timestamp fineElabFlusso, 
            String flussiInElab, String flagElabStampaAvviso) throws DaoException 
	//fine LP 20240920 - PGNTECCSV-10
	{
		CallableStatement callableStatement = null;	
		try	{
			//inizio LP 20240920 - PGNTECCSV-10
			callableStatement = prepareCall(bFlagUpdateAutocommit, Routines.ELG_DOINSERT.routine());
			//fine LP 20240920 - PGNTECCSV-10
			callableStatement.setString(1, codiceUtente);
			callableStatement.setDate(2, dataFlusso);
			callableStatement.setInt(3, progressivo);
			callableStatement.setString(4, procGestione);
			callableStatement.setString(5, tipoServizio);
			callableStatement.setTimestamp(6, inizioElabFlusso);
			callableStatement.setTimestamp(7, fineElabFlusso);
			callableStatement.setString(8, nomeFile);
			callableStatement.setString(9, flussiInElab);
			callableStatement.setString(10, flagElabStampaAvviso);
			/* we register row start */
			//inizio LP 20240924 - Differenze tra i tipi di dati tra postgres e altri DB (?)
			if(DriverType.isPostgres(getConnection())) {
				/* Differenza tra parametro dichiarato su sql (java) bigint == (sql)int8 per postgres */
				callableStatement.registerOutParameter(11, Types.BIGINT);
			} else { 	
				/* Questo corrisponde alla chiamata per la sp in db2 (?) */
			//fine LP 20240924
				callableStatement.registerOutParameter(11, Types.INTEGER);
			//inizio LP 20240924 - Differenze tra i tipi di dati tra postgres e altri DB (?)
			}
			//fine LP 20240924
			callableStatement.execute();
			int i = (int) callableStatement.getLong(11);
			return i;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			//inizio LP 20240920 - PGNTECCSV-10
			if(bCloseStat) {
			//fine LP 20240920 - PGNTECCSV-10
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			//inizio LP 20240920 - PGNTECCSV-10
					callableStatement = null;
				}
			//fine LP 20240920 - PGNTECCSV-10
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	//PG170070_001 GG 20170517 - Introdotto flagElabStampaAvviso
	//inizio LP 20240920 - PGNTECCSV-10
	public int doUpdateLogFlussi(int prog, String codiceUtente, Date dataFlusso, int progressivo, String procGestione,
            String tipoServizio, String nomeFile, Timestamp inizioElabFlusso, Timestamp fineElabFlusso, 
            String flussiInElab, String flagElabStampaAvviso) throws DaoException {
		return doUpdateLogFlussiBatch(true, true, prog, codiceUtente, dataFlusso, progressivo, procGestione, tipoServizio, nomeFile, inizioElabFlusso, fineElabFlusso, flussiInElab, flagElabStampaAvviso);	
	}

	public int doUpdateLogFlussiBatch(boolean bFlagUpdateAutocommit, boolean bCloseStat, int prog, String codiceUtente, Date dataFlusso, int progressivo, String procGestione, 
            String tipoServizio, String nomeFile, Timestamp inizioElabFlusso, Timestamp fineElabFlusso, 
            String flussiInElab, String flagElabStampaAvviso) throws DaoException {
	//fine LP 20240920 - PGNTECCSV-10
		CallableStatement callableStatement = null;	
		try	{
			//inizio LP 20240920 - PGNTECCSV-10
			callableStatement = prepareCall(bFlagUpdateAutocommit, Routines.ELG_DOUPDATE.routine());
			//fine LP 20240920 - PGNTECCSV-10
			callableStatement.setLong(1, prog);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setDate(3, dataFlusso);
			if (progressivo > 0)
				callableStatement.setInt(4, progressivo);
			else callableStatement.setNull(4, Types.INTEGER);
			callableStatement.setString(5, procGestione);
			callableStatement.setString(6, tipoServizio);
			callableStatement.setTimestamp(7, inizioElabFlusso);
			callableStatement.setTimestamp(8, fineElabFlusso);
			callableStatement.setString(9, nomeFile);
			callableStatement.setString(10, flussiInElab);
			callableStatement.setString(11, flagElabStampaAvviso);
			/* we register row start */
			callableStatement.execute();
			return prog;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			//inizio LP 20240920 - PGNTECCSV-10
			if(bCloseStat) {
			//fine LP 20240920 - PGNTECCSV-10
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240920 - PGNTECCSV-10
				callableStatement = null;
			}
			//fine LP 20240920 - PGNTECCSV-10
			//fine LP PG21XX04 Leak
		}
	}
	
	public int doPrgressivo(String nomeFile) throws DaoException 
	{
		CallableStatement callableStatement = null;	
		try {
			callableStatement = prepareCall(Routines.ELG_DO_ELG_PELGFLUS.routine());
			callableStatement.setString(1, nomeFile);
			callableStatement.registerOutParameter(2, Types.INTEGER);
			callableStatement.execute();
			int i = (int) callableStatement.getLong(2);
			return i;
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
	
	public int doInsertLogPhases(int progressivoFlusso, String codiceFase, String descFase, Timestamp inizioElabFase, Timestamp fineElabFase, 
					             String msgError) throws DaoException 
	{
	     CallableStatement callableStatement = null;	
	     try {
			callableStatement = prepareCall(Routines.ELF_DOINSERT.routine());	
			callableStatement.setString(1, codiceFase);
			callableStatement.setString(2, descFase);
			callableStatement.setTimestamp(3, inizioElabFase);
			callableStatement.setTimestamp(4, fineElabFase);
			callableStatement.setString(5, msgError);
			callableStatement.setLong(6, progressivoFlusso);
			/* we register row start */
			callableStatement.registerOutParameter(7, Types.INTEGER);
			callableStatement.execute();
			int i = callableStatement.getInt(7);
			return i;
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

	public int doUpdateLogPhases(String codiceFase, String descFase, Timestamp inizioElabFase, Timestamp fineElabFase, 
								 String msgError, int progressivo) throws DaoException {
		CallableStatement callableStatement = null;
		int rowsUpd = -1;
		try {
			callableStatement = prepareCall(Routines.ELF_DOUPDATE.routine());	
			callableStatement.setString(1, codiceFase);
			callableStatement.setString(2, descFase);
			callableStatement.setTimestamp(3, inizioElabFase);
			callableStatement.setTimestamp(4, fineElabFase);
			callableStatement.setString(5, msgError);
			if (progressivo > 0)
				callableStatement.setLong(6, progressivo);
			else callableStatement.setNull(6, Types.BIGINT);
			callableStatement.registerOutParameter(7, Types.INTEGER);
			callableStatement.execute();
			rowsUpd = callableStatement.getInt(7);
			return rowsUpd > 0 ? progressivo : rowsUpd;
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

	//PG170070_001 GG 20170517 - Introdotti flag stampa avviso, flag generazione IUV e informazioni per generazione IUV
	//PG200140 - Introdotto carattere di servizio
	//inizio LP 20240920 - PGNTECCSV-10
	public int doInsertEH0(int prog, String tipoRecord, String codiceUtente, Date dataFlusso, String procGestione, String tipoServizio, char stato, String flagStampaAvviso,
						   String flagGenerazioneIUV, String identificativoDominio, String auxDigit, String applicationCode, String segregationCode,
						   String carattereServizio) throws DaoException {
		return doInsertEH0Batch(true, true,
				prog, tipoRecord, codiceUtente, dataFlusso, procGestione, tipoServizio, stato, flagStampaAvviso,
				flagGenerazioneIUV, identificativoDominio, auxDigit, applicationCode, segregationCode,
				carattereServizio);
	}

	public int doInsertEH0Batch(boolean bFlagUpdateAutocommit, boolean bCloseStat, 
			int prog, String tipoRecord, String codiceUtente, Date dataFlusso, String procGestione, String tipoServizio, char stato, String flagStampaAvviso,
			String flagGenerazioneIUV, String identificativoDominio, String auxDigit, String applicationCode, String segregationCode,
			String carattereServizio) throws DaoException {
	//fine LP 20240920 - PGNTECCSV-10
		CallableStatement callableStatement = null;	
		try{
			//inizio LP 20240920 - PGNTECCSV-10
			callableStatement = prepareCall(Routines.EH0_DOINSERT.routine());
			//fine LP 20240920 - PGNTECCSV-10
			callableStatement.setString(1, tipoRecord);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setDate(3, dataFlusso);
			callableStatement.setString(4, procGestione);
			callableStatement.setString(5, tipoServizio);
			callableStatement.setString(6, String.valueOf(stato));
			callableStatement.setString(7, flagStampaAvviso);
			callableStatement.setString(8, flagGenerazioneIUV);
			callableStatement.setString(9, identificativoDominio);
			callableStatement.setString(10, auxDigit);
			callableStatement.setString(11, applicationCode);
			callableStatement.setString(12, segregationCode);
			callableStatement.setLong(13, prog);
			callableStatement.setString(14, carattereServizio);
			callableStatement.registerOutParameter(15, Types.INTEGER);
			callableStatement.execute();
			int i = callableStatement.getInt(15);
			return i;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			//inizio LP 20240920 - PGNTECCSV-10
			if(bCloseStat) {
			//fine LP 20240920 - PGNTECCSV-10
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240920 - PGNTECCSV-10
				callableStatement = null;
			}
			//fine LP 20240920 - PGNTECCSV-10
			//fine LP PG21XX04 Leak
		}
	}
	
	//PG170070_001 GG 20171804 - Introdotti ibanAccredito, flagFattElettronica e codiceIUV
	//inizio LP 20240920 - PGNTECCSV-10
	public int doInsertEH1(int prog, String tipoRecord, String codiceUtente, Date dataFlusso, String tipoServizio, 
						   String codEnte, String tipoUff, String codUfficio, String codImposta, String numDoc, String codFiscale,
						   String tipoDoc, String riforma, String annoDoc, String emissione, String idCartella, String rendicontato, Date notifica,
						   String docEntrate, String bollettino, double importo, String codEnteEntrate, String sospensione, String procInCorso, String inps, 
						   String rateazione, String codServizio, char stato, String ibanAccredito, String flagFattElettronica, String codiceIUV
						   , String causale //PG180020 CT
						   , String ibanAppoggio //PG200140
						   , String tassonomia //PG200360 LP
						   ) throws DaoException {
		return doInsertEH1Batch(false, false,
				prog, tipoRecord, codiceUtente, dataFlusso, tipoServizio,
				codEnte, tipoUff, codUfficio, codImposta, numDoc, codFiscale,
				tipoDoc, riforma, annoDoc, emissione, idCartella, rendicontato, notifica,
				docEntrate, bollettino, importo, codEnteEntrate, sospensione, procInCorso, inps,
				rateazione, codServizio, stato, ibanAccredito, flagFattElettronica, codiceIUV,
				causale, ibanAppoggio, tassonomia);
	}

	public int doInsertEH1Batch(boolean bFlagUpdateAutocommit, boolean bCloseStat,
			int prog, String tipoRecord, String codiceUtente, Date dataFlusso, String tipoServizio, 
			String codEnte, String tipoUff, String codUfficio, String codImposta, String numDoc, String codFiscale,
			String tipoDoc, String riforma, String annoDoc, String emissione, String idCartella, String rendicontato, Date notifica,
			String docEntrate, String bollettino, double importo, String codEnteEntrate, String sospensione, String procInCorso, String inps, 
			String rateazione, String codServizio, char stato, String ibanAccredito, String flagFattElettronica, String codiceIUV
			, String causale //PG180020 CT
			, String ibanAppoggio //PG200140
			, String tassonomia //PG200360 LP
			) throws DaoException {
	//fine LP 20240920 - PGNTECCSV-10
		CallableStatement callableStatement = null;	
		System.out.println("DAO ibanAppoggio: "+ibanAppoggio);
		try {
			//inizio LP 20240920 - PGNTECCSV-10
			callableStatement = prepareCall(bFlagUpdateAutocommit, Routines.EH1_DOINSERT.routine());
			//fine LP 20240920 - PGNTECCSV-10
			callableStatement.setString(1, tipoRecord);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setDate(3, dataFlusso);
			callableStatement.setString(4, tipoServizio);
			callableStatement.setString(5, codEnte);
			callableStatement.setString(6, tipoUff);
			callableStatement.setString(7, codUfficio);
			callableStatement.setString(8, codImposta);
			callableStatement.setString(9, numDoc);
			callableStatement.setString(10, codFiscale);
			callableStatement.setString(11, String.valueOf(tipoDoc));
			callableStatement.setString(12, String.valueOf(riforma));
			callableStatement.setString(13, annoDoc);
			callableStatement.setString(14, emissione);
			callableStatement.setString(15, idCartella);
			callableStatement.setString(16, String.valueOf(rendicontato));
			callableStatement.setDate(17, notifica);
			callableStatement.setString(18, String.valueOf(docEntrate));
			callableStatement.setString(19, bollettino);
			callableStatement.setDouble(20, importo);
			callableStatement.setString(21, codEnteEntrate);
			callableStatement.setString(22, String.valueOf(sospensione));
			callableStatement.setString(23, String.valueOf(procInCorso));
			callableStatement.setString(24, String.valueOf(inps));
			callableStatement.setString(25, String.valueOf(rateazione));
			callableStatement.setString(26, String.valueOf(codServizio));
			callableStatement.setString(27, String.valueOf(stato));
			callableStatement.setString(28, String.valueOf(ibanAccredito));
			callableStatement.setString(29, String.valueOf(flagFattElettronica));
			callableStatement.setString(30, String.valueOf(codiceIUV));
			callableStatement.setString(31, String.valueOf(causale));
			callableStatement.setLong(32, prog);
			callableStatement.setString(33, String.valueOf(ibanAppoggio));
			//inizio LP PG200260
			//callableStatement.registerOutParameter(34, Types.INTEGER);
			callableStatement.setString(34, String.valueOf(tassonomia));
			callableStatement.registerOutParameter(35, Types.INTEGER);
			//fine LP PG200260
			callableStatement.execute();
			//inizio LP PG200260
			int i = callableStatement.getInt(35);
			//fine LP PG200260
			return i;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			//inizio LP 20240920 - PGNTECCSV-10
			if(bCloseStat) {
			//fine LP 20240920 - PGNTECCSV-10
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240920 - PGNTECCSV-10
				callableStatement = null;
			}
			//fine LP 20240920 - PGNTECCSV-10
			//fine LP PG21XX04 Leak
		}
	}
	
	//PG170070_001 GG 20171804 - Introdotto codiceIUV
	//inizio LP 20240920 - PGNTECCSV-10
	public int doInsertEH2(int prog, String tipoRecord, String codiceUtente, Date dataFlusso, String tipoServizio, 
						   String codEnte, String tipoUff, String codUfficio, String codImposta, String numDoc, double tributo, double tribPagato, 
						   Date scadenza, int numRata, String rataBollettino, double importoBoll, double mora, double aggioDovuto, double aggioOriginario, 
						   double aggioScadenza, double aggioPagato, double notifica, double notificaPagata, char stato, String codiceIUV) throws DaoException {
		return doInsertEH2Batch(true, true,  
				prog, tipoRecord, codiceUtente, dataFlusso, tipoServizio,
				codEnte, tipoUff, codUfficio, codImposta, numDoc, tributo, tribPagato,
				scadenza, numRata, rataBollettino, importoBoll, mora, aggioDovuto, aggioOriginario,
				aggioScadenza,   aggioPagato, notifica, notificaPagata, stato, codiceIUV);
	}

	public int doInsertEH2Batch(boolean bFlagUpdateAutocommit, boolean bCloseStat,
			int prog, String tipoRecord, String codiceUtente, Date dataFlusso, String tipoServizio, 
			String codEnte, String tipoUff, String codUfficio, String codImposta, String numDoc, double tributo, double tribPagato, 
			Date scadenza, int numRata, String rataBollettino, double importoBoll, double mora, double aggioDovuto, double aggioOriginario, 
			double aggioScadenza, double aggioPagato, double notifica, double notificaPagata, char stato, String codiceIUV) throws DaoException {
	//fine LP 20240920 - PGNTECCSV-10
		CallableStatement callableStatement = null;	
		try {
			//inizio LP 20240920 - PGNTECCSV-10
			callableStatement = prepareCall(bFlagUpdateAutocommit, Routines.EH2_DOINSERT.routine());
			//fine LP 20240920 - PGNTECCSV-10
			callableStatement.setString(1, tipoRecord);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setDate(3, dataFlusso);
			callableStatement.setString(4, tipoServizio);
			callableStatement.setString(5, codEnte);
			callableStatement.setString(6, tipoUff);
			callableStatement.setString(7, codUfficio);
			callableStatement.setString(8, codImposta);
			callableStatement.setString(9, numDoc);
			callableStatement.setDouble(10, tributo);
			callableStatement.setDouble(11, tribPagato);
			callableStatement.setDate(12, scadenza);
			callableStatement.setInt(13, numRata);
			callableStatement.setString(14, rataBollettino);
			callableStatement.setDouble(15, importoBoll);
			callableStatement.setDouble(16, mora);
			callableStatement.setDouble(17, aggioDovuto);
			callableStatement.setDouble(18, aggioOriginario);
			callableStatement.setDouble(19, aggioScadenza);
			callableStatement.setDouble(20, aggioPagato);
			callableStatement.setDouble(21, notifica);
			callableStatement.setDouble(22, notificaPagata);
			callableStatement.setString(23, String.valueOf(stato));
			callableStatement.setString(24, codiceIUV);
			callableStatement.setLong(25, prog);
			callableStatement.registerOutParameter(26, Types.INTEGER);
			callableStatement.execute();
			int i = callableStatement.getInt(26);
			return i;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			//inizio LP 20240920 - PGNTECCSV-10
			if(bCloseStat) {
			//fine LP 20240920 - PGNTECCSV-10
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240920 - PGNTECCSV-10
				callableStatement = null;
			}
			//fine LP 20240920 - PGNTECCSV-10
			//fine LP PG21XX04 Leak
		}
	}

	//inizio LP PG210130
	//inizio LP 20240920 - PGNTECCSV-10
	public int doInsertEHD(int prog, String tipoRecord, String codiceUtente, Date dataFlusso, String tipoServizio, 
			   String codEnte, String tipoUff, String codUfficio, String codImposta, String numDoc, 
			   String bollettino, String idDominio, double importo, String ibanBancario, String ibanPostale,  
			   String codiceTipologiaServizio, //LP PG22XX05
			   char stato,
			   //inizio SB PGNTCORE-4
			   String metadatiPagoPATariTefaKey,
			   String metadatiPagoPATariTefaValue
			   //fine SB PGNTCORE-4
			   ) throws DaoException {
		return doInsertEHDBatch(true, true,
			prog, tipoRecord, codiceUtente, dataFlusso, tipoServizio,
			codEnte, tipoUff, codUfficio, codImposta, numDoc, 
			bollettino, idDominio, importo, ibanBancario, ibanPostale, 
			codiceTipologiaServizio,
			stato,
			metadatiPagoPATariTefaKey,
			metadatiPagoPATariTefaValue);
	}

	public int doInsertEHDBatch(boolean bFlagUpdateAutocommit, boolean bCloseStat,
			int prog, String tipoRecord, String codiceUtente, Date dataFlusso, String tipoServizio, 
			String codEnte, String tipoUff, String codUfficio, String codImposta, String numDoc, 
			String bollettino, String idDominio, double importo, String ibanBancario, String ibanPostale,  
			String codiceTipologiaServizio, //LP PG22XX05
			char stato,
			//inizio SB PGNTCORE-4
			String metadatiPagoPATariTefaKey,
			String metadatiPagoPATariTefaValue
			//fine SB PGNTCORE-4
			) throws DaoException
	//fine LP 20240920 - PGNTECCSV-10
	{
		CallableStatement callableStatement = null;	
		try {
			//inizio LP 20240920 - PGNTECCSV-10
			callableStatement = prepareCall(bFlagUpdateAutocommit, Routines.EHD_DOINSERT.routine());
			//fine LP 20240920 - PGNTECCSV-10
			callableStatement.setString(1, tipoRecord);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setDate(3, dataFlusso);
			callableStatement.setString(4, tipoServizio);
			callableStatement.setString(5, codEnte);
			callableStatement.setString(6, tipoUff);
			callableStatement.setString(7, codUfficio);
			callableStatement.setString(8, codImposta);
			callableStatement.setString(9, numDoc);
			//callableStatement.setInt(13, numRata);
			callableStatement.setString(10, bollettino);
			callableStatement.setString(11, idDominio);
			callableStatement.setDouble(12, importo);
			callableStatement.setString(13, ibanBancario);
			if(ibanPostale != null)
				callableStatement.setString(14, ibanPostale);
			else
				callableStatement.setString(14, "");
			callableStatement.setLong(15, prog);
			callableStatement.setString(16, String.valueOf(stato));
			//inizio LP PG22XX05
			//callableStatement.registerOutParameter(17, Types.INTEGER);
			//callableStatement.execute();
			//int i = callableStatement.getInt(17);
			if(codiceTipologiaServizio != null)
				callableStatement.setString(17, codiceTipologiaServizio);
			else
				callableStatement.setString(17, "");
			//inizio SB PGNTCORE-4
			if(metadatiPagoPATariTefaKey != null)
				callableStatement.setString(18, metadatiPagoPATariTefaKey);
			else
				callableStatement.setString(18, "");
			if(metadatiPagoPATariTefaValue != null)
				callableStatement.setString(19, metadatiPagoPATariTefaValue);
			else
				callableStatement.setString(19, "");
			//fine SB PGNTCORE-4
			callableStatement.registerOutParameter(20, Types.INTEGER);
			callableStatement.execute();
			int i = callableStatement.getInt(20);
			//fine LP PG22XX05
			return i;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP 20240920 - PGNTECCSV-10
			if(bCloseStat) {
			//fine LP 20240920 - PGNTECCSV-10
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240920 - PGNTECCSV-10
				callableStatement = null;
			}
			//fine LP 20240920 - PGNTECCSV-10
		}
	}

	//inizio LP 20240920 - PGNTECCSV-10
	public int doInsertEHC(int progFlusso, String tipoRecord, String codiceUtente, Date dataFlusso, String tipoServizio, 
			   String codEnte, String tipoUff, String codUfficio, String codImposta, String numDoc, 
			   String bollettino, int numTrib, String idDominio, String codiceContabile,
			   double importo, String capitolo, String articolo, String anno, char stato
			   ) throws DaoException
	{
		return doInsertEHCBatch(true, true,
				progFlusso, tipoRecord, codiceUtente, dataFlusso, tipoServizio,
				codEnte, tipoUff, codUfficio, codImposta, numDoc,
				bollettino, numTrib, idDominio, codiceContabile,
				importo, capitolo, articolo, anno, stato);
	}

	public int doInsertEHCBatch(boolean bFlagUpdateAutocommit, boolean bCloseStat,
			int progFlusso, String tipoRecord, String codiceUtente, Date dataFlusso, String tipoServizio, 
			String codEnte, String tipoUff, String codUfficio, String codImposta, String numDoc, 
			String bollettino, int numTrib, String idDominio, String codiceContabile,
			double importo, String capitolo, String articolo, String anno, char stato
			) throws DaoException
	{
	//fine LP 20240920 - PGNTECCSV-10
		CallableStatement callableStatement = null;	
		try {
			//inizio LP 20240920 - PGNTECCSV-10
			callableStatement = prepareCall(bFlagUpdateAutocommit, "PYEHCSP_INS");
			//fine LP 20240920 - PGNTECCSV-10
			callableStatement.setString(1, tipoRecord);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setDate(3, dataFlusso);
			callableStatement.setString(4, tipoServizio);
			callableStatement.setString(5, codEnte);
			callableStatement.setString(6, tipoUff);
			callableStatement.setString(7, codUfficio);
			callableStatement.setString(8, codImposta);
			callableStatement.setString(9, numDoc);
			callableStatement.setString(10, bollettino);
			callableStatement.setInt(11, numTrib);
			callableStatement.setString(12, idDominio);
			callableStatement.setString(13, codiceContabile);
			callableStatement.setDouble(14, importo);
			if(capitolo != null)
				callableStatement.setString(15, capitolo);
			else
				callableStatement.setNull(15, Types.VARCHAR);
			if(articolo != null)
				callableStatement.setString(16, articolo);
			else
				callableStatement.setNull(16, Types.VARCHAR);
			if(anno != null)
				callableStatement.setString(17, anno);
			else
				callableStatement.setNull(17, Types.VARCHAR);
			callableStatement.setLong(18, progFlusso);
			callableStatement.setString(19, String.valueOf(stato));
			callableStatement.registerOutParameter(20, Types.INTEGER);
			callableStatement.execute();
			int i = callableStatement.getInt(20);
			return i;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP 20240920 - PGNTECCSV-10
			if(bCloseStat) {
			//fine LP 20240920 - PGNTECCSV-10
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240920 - PGNTECCSV-10
				callableStatement = null;
			}
			//fine LP 20240920 - PGNTECCSV-10
		}
	}

	//fine LP PG210130

	//inizio LP 20240828 - PGNTACWS-22
	public int doInsertEH3(int prog, String tipoRecord, String codiceUtente, Date dataFlusso, String tipoServizio, 
						   String codEnte, String tipoUff, String codUfficio, String codImposta, String numDoc, int  progPagamento, String movimento, 
						   Date dataMovimento, String causale, String segno, double importoPagato, double notifica, double aggioCoattivo, double spese,
						   double mora, int numRate, int primaRata, String canPagamento, String tipoPagamento, String stato) throws DaoException {
		//inizio LP 20240920 - PGNTECCSV-10
		//return doInsertEH3Tail(true
		return doInsertEH3Batch(true, true,
		//fine LP 20240828 - PGNTACWS-22
					prog, tipoRecord, codiceUtente, dataFlusso, tipoServizio, 
					codEnte, tipoUff, codUfficio, codImposta, numDoc,  progPagamento, movimento, 
					dataMovimento, causale, segno,  importoPagato,  notifica,  aggioCoattivo,  spese,
					mora, numRate, primaRata, canPagamento, tipoPagamento, stato);
	}

	//inizio LP 20240920 - PGNTECCSV-10
	//public int doInsertEH3Tail(boolean bFlagUpdateAutocommit,
	public int doInsertEH3Batch(boolean bFlagUpdateAutocommit, boolean bCloseStat,
	//inizio LP 20240920 - PGNTECCSV-10
						int prog, String tipoRecord, String codiceUtente, Date dataFlusso, String tipoServizio, 
						String codEnte, String tipoUff, String codUfficio, String codImposta, String numDoc, int  progPagamento, String movimento, 
						Date dataMovimento, String causale, String segno, double importoPagato, double notifica, double aggioCoattivo, double spese,
						double mora, int numRate, int primaRata, String canPagamento, String tipoPagamento, String stato) throws DaoException {
	//fine LP 20240828 - PGNTACWS-22
		CallableStatement callableStatement = null;	
		try {
			//inizio LP 20240828 - PGNTACWS-22
			//callableStatement = prepareCall(Routines.EH3_DOINSERT.routine());	
			//inizio LP 20240920 - PGNTECCSV-10
			//callableStatement = prepareCall(bFlagUpdateAutocommit, Routines.EH3_DOINSERT.routine());	
			callableStatement = prepareCall(bFlagUpdateAutocommit, Routines.EH3_DOINSERT.routine());
			//fine LP 20240920 - PGNTECCSV-10
			//fine LP 20240828 - PGNTACWS-22
			callableStatement.setString(1, tipoRecord);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setDate(3, dataFlusso);
			callableStatement.setString(4, tipoServizio);
			callableStatement.setString(5, codEnte);
			callableStatement.setString(6, tipoUff);
			callableStatement.setString(7, codUfficio);
			callableStatement.setString(8, codImposta);
			callableStatement.setString(9, numDoc);
			callableStatement.setInt(10, progPagamento);
			callableStatement.setString(11, String.valueOf(movimento));
			callableStatement.setDate(12, dataMovimento);
			callableStatement.setString(13, String.valueOf(causale));
			callableStatement.setString(14, String.valueOf(segno));
			callableStatement.setDouble(15, importoPagato);
			callableStatement.setDouble(16, notifica);
			callableStatement.setDouble(17, aggioCoattivo);
			callableStatement.setDouble(18, spese);
			callableStatement.setDouble(19, mora);
			callableStatement.setInt(20, numRate);
			callableStatement.setInt(21, primaRata);
			callableStatement.setString(22, String.valueOf(canPagamento));
			callableStatement.setString(23, String.valueOf(tipoPagamento));
			callableStatement.setString(24, String.valueOf(stato));
			callableStatement.setLong(25, prog);
			callableStatement.registerOutParameter(26, Types.INTEGER);
			callableStatement.execute();
			int i = callableStatement.getInt(26);
			return i;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			//inizio LP 20240920 - PGNTECCSV-10
			if(bCloseStat) {
			//fine LP 20240920 - PGNTECCSV-10
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240920 - PGNTECCSV-10
				callableStatement = null;
			}
			//fine LP 20240920 - PGNTECCSV-10
			//fine LP PG21XX04 Leak
		}
	}
	
	//inizio LP 20240920 - PGNTECCSV-10
	public int doInsertEH4(int prog, String tipoRecord, String codiceUtente, Date dataFlusso, String tipoServizio, 
						   String codEnte,String tipoUff, String codUfficio, String codImposta, String numDoc, double speseDovute, 
						   double spesePagate, double mora, char stato) throws DaoException {
		return doInsertEH4Batch(true, true,
				prog, tipoRecord, codiceUtente, dataFlusso, tipoServizio,
				codEnte, tipoUff, codUfficio, codImposta, numDoc, speseDovute,
				spesePagate, mora, stato);
	}

	public int doInsertEH4Batch(boolean bFlagUpdateAutocommit, boolean bCloseStat,
		int prog, String tipoRecord, String codiceUtente, Date dataFlusso, String tipoServizio, 
		String codEnte,String tipoUff, String codUfficio, String codImposta, String numDoc, double speseDovute, 
		double spesePagate, double mora, char stato) throws DaoException {
	//fine LP 20240920 - PGNTECCSV-10
		CallableStatement callableStatement = null;	
		try {
			//inizio LP 20240920 - PGNTECCSV-10
			//callableStatement = prepareCall(Routines.EH4_DOINSERT.routine());	
			callableStatement = prepareCall(bFlagUpdateAutocommit, Routines.EH4_DOINSERT.routine());
			//fine LP 20240920 - PGNTECCSV-10
			callableStatement.setString(1, tipoRecord);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setDate(3, dataFlusso);
			callableStatement.setString(4, tipoServizio);
			callableStatement.setString(5, codEnte);
			callableStatement.setString(6, tipoUff);
			callableStatement.setString(7, codUfficio);
			callableStatement.setString(8, codImposta);
			callableStatement.setString(9, numDoc);
			callableStatement.setDouble(10, speseDovute);
			callableStatement.setDouble(11, spesePagate);
			callableStatement.setDouble(12, mora);
			callableStatement.setString(13, String.valueOf(stato));
			callableStatement.setLong(14, prog);
			callableStatement.registerOutParameter(15, Types.INTEGER);
			callableStatement.execute();
			int i = callableStatement.getInt(15);
			return i;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			//inizio LP 20240920 - PGNTECCSV-10
			if(bCloseStat) {
			//fine LP 20240920 - PGNTECCSV-10
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240920 - PGNTECCSV-10
				callableStatement = null;
			}
			//fine LP 20240920 - PGNTECCSV-10
			//fine LP PG21XX04 Leak
		}			
	}
	
	//inizio LP 20240828 - PGNTACWS-22
	//inizio LP 20240920 - PGNTECCSV-10
	public int doInsertEH5(int progF, String tipoRecord, String codiceUtente, Date dataFlusso, String tipoServizio, 
						   String codEnte, String tipoUff,String codUfficio, String codImposta, String numDoc, String idCartella,
						   int prog, String tipoColl, String collegamento, char stato) throws DaoException {
		return doInsertEH5Batch(true, true,
				progF, tipoRecord, codiceUtente, dataFlusso, tipoServizio,
				codEnte, tipoUff, codUfficio, codImposta, numDoc, idCartella,
				prog, tipoColl, collegamento, stato);
	}

	public int doInsertEH5Batch(boolean bFlagUpdateAutocommit, boolean bCloseStat,
			int progF, String tipoRecord, String codiceUtente, Date dataFlusso, String tipoServizio, 
			   String codEnte, String tipoUff,String codUfficio, String codImposta, String numDoc, String idCartella,
			   int prog, String tipoColl, String collegamento, char stato) throws DaoException {
	//fine LP 20240920 - PGNTECCSV-10
		CallableStatement callableStatement = null;	
		try {
			//inizio LP 20240920 - PGNTECCSV-10
			//callableStatement = prepareCall(Routines.EH5_DOINSERT.routine());	
			callableStatement = prepareCall(bFlagUpdateAutocommit, Routines.EH5_DOINSERT.routine());
			//fine LP 20240920 - PGNTECCSV-10
			callableStatement.setString(1, tipoRecord);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setDate(3, dataFlusso);
			callableStatement.setString(4, tipoServizio);
			callableStatement.setString(5, codEnte);
			callableStatement.setString(6, tipoUff);
			callableStatement.setString(7, codUfficio);
			callableStatement.setString(8, codImposta);
			callableStatement.setString(9, numDoc);
			callableStatement.setString(10, idCartella);
			callableStatement.setInt(11, prog);
			callableStatement.setString(12, String.valueOf(tipoColl));
			callableStatement.setString(13, collegamento);
			callableStatement.setString(14, String.valueOf(stato));
			callableStatement.setLong(15, progF);
			callableStatement.registerOutParameter(16, Types.INTEGER);
			callableStatement.execute();
			int i = callableStatement.getInt(16);
			return i;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			//inizio LP 20240920 - PGNTECCSV-10
			if(bCloseStat) {
			//fine LP 20240920 - PGNTECCSV-10
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240920 - PGNTECCSV-10
				callableStatement = null;
			}
			//fine LP 20240920 - PGNTECCSV-10
			//fine LP PG21XX04 Leak
		}	
	}

	//inizio LP 20240828 - PGNTACWS-22
	//inizio LP 20240920 - PGNTECCSV-10
	public int doInsertEH6(int prog, String tipoRecord, String codiceUtente, Date dataFlusso, String tipoServizio, 
						   String codEnte, String tipoUff, String codUfficio, String codImposta, String descImposta,
						   String servizio, String descrizione, char stato) throws DaoException {
		return doInsertEH6Batch(true, true,
				prog, tipoRecord, codiceUtente, dataFlusso, tipoServizio,
				codEnte, tipoUff, codUfficio, codImposta, descImposta,
				servizio, descrizione, stato);
	}

	public int doInsertEH6Batch(boolean bFlagUpdateAutocommit, boolean bCloseStat,
			int prog, String tipoRecord, String codiceUtente, Date dataFlusso, String tipoServizio, 
			String codEnte, String tipoUff, String codUfficio, String codImposta, String descImposta,
			String servizio, String descrizione, char stato) throws DaoException {
	//fine LP 20240920 - PGNTECCSV-10
		CallableStatement callableStatement = null;	
		try{
			//inizio LP 20240920 - PGNTECCSV-10
			//callableStatement = prepareCall(Routines.EH6_DOINSERT.routine());	
			callableStatement = prepareCall(bFlagUpdateAutocommit, Routines.EH6_DOINSERT.routine());
			//fine LP 20240920 - PGNTECCSV-10
			callableStatement.setString(1, tipoRecord);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setDate(3, dataFlusso);
			callableStatement.setString(4, tipoServizio);
			callableStatement.setString(5, codEnte);
			callableStatement.setString(6, tipoUff);
			callableStatement.setString(7, codUfficio);
			callableStatement.setString(8, codImposta);
			callableStatement.setString(9, descImposta);
			callableStatement.setString(10, servizio);
			callableStatement.setString(11, descrizione);
			callableStatement.setString(12, String.valueOf(stato));
			callableStatement.setLong(13, prog);
			callableStatement.registerOutParameter(14, Types.INTEGER);
			callableStatement.execute();
			int i = callableStatement.getInt(14);
			return i;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			//inizio LP 20240920 - PGNTECCSV-10
			if(bCloseStat) {
			//fine LP 20240920 - PGNTECCSV-10
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240920 - PGNTECCSV-10
				callableStatement = null;
			}
			//fine LP 20240920 - PGNTECCSV-10
			//fine LP PG21XX04 Leak
		}	
	}
	
	//inizio LP 20240828 - PGNTACWS-22
	public int doInsertEH7(int progressivoFlusso, String tipoRecord, String codiceUtente, Date dataFlusso,String tipoServizio, 
						   String codiceEnte, String tipoUfficio, String codiceUfficio, String codiceImposta, String numeroDocumento,
						   String codiceTributo, String annoTributo, int progressivoTributo, 
						   String tipoTributo, double importoTributo, double importoPagatoPiuSgravi, 
						   //inizio LP PG210130
						   //String noteTributo, char stato, String codiceCapitolo, String accertamento) throws DaoException {
		   				   String noteTributo, char stato, String codiceCapitolo, String accertamento
		   				   , String articolo, String idDominio, String ibanBancario, String ibanPostale
		   				   , String codiceTipologiaServizio //LP PG22XX05
		   				   , String metadatiPagoPATariTefaKey, String metadatiPagoPATariTefaValue //SB PGNTCORE-4
		   				  ) throws DaoException {
						   //fine LP PG210130
	//inizio LP 20240920 - PGNTECCSV-10
		return doInsertEH7Batch(true, true,
				progressivoFlusso, tipoRecord, codiceUtente, dataFlusso, tipoServizio,
				codiceEnte, tipoUfficio, codiceUfficio, codiceImposta, numeroDocumento,
				codiceTributo, annoTributo, progressivoTributo,
				tipoTributo, importoTributo, importoPagatoPiuSgravi,
				noteTributo, stato, codiceCapitolo, accertamento,
				articolo, idDominio, ibanBancario, ibanPostale,
				codiceTipologiaServizio,
				metadatiPagoPATariTefaKey, metadatiPagoPATariTefaValue);
	}	

	public int doInsertEH7Batch(boolean bFlagUpdateAutocommit, boolean bCloseStat,
			int progressivoFlusso, String tipoRecord, String codiceUtente, Date dataFlusso,String tipoServizio, 
			String codiceEnte, String tipoUfficio, String codiceUfficio, String codiceImposta, String numeroDocumento,
			String codiceTributo, String annoTributo, int progressivoTributo, 
			String tipoTributo, double importoTributo, double importoPagatoPiuSgravi, 
			//inizio LP PG210130
			//String noteTributo, char stato, String codiceCapitolo, String accertamento) throws DaoException {
			String noteTributo, char stato, String codiceCapitolo, String accertamento
			, String articolo, String idDominio, String ibanBancario, String ibanPostale
			, String codiceTipologiaServizio //LP PG22XX05
			 , String metadatiPagoPATariTefaKey, String metadatiPagoPATariTefaValue //SB PGNTCORE-4
			) throws DaoException {
	//fine LP 20240920 - PGNTECCSV-10
		CallableStatement callableStatement = null;
		try {
			//inizio LP 20240920 - PGNTECCSV-10
			//callableStatement = prepareCall(Routines.EH7_DOINSERT.routine());	
			callableStatement = prepareCall(bFlagUpdateAutocommit, Routines.EH7_DOINSERT.routine());
			//fine LP 20240920 - PGNTECCSV-10
			callableStatement.setString(1, tipoRecord);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setDate(3, dataFlusso);
			callableStatement.setString(4, tipoServizio);
			callableStatement.setString(5, codiceEnte);
			callableStatement.setString(6, tipoUfficio);
			callableStatement.setString(7, codiceUfficio);
			callableStatement.setString(8, codiceImposta);
			callableStatement.setString(9, numeroDocumento);
			callableStatement.setString(10, codiceTributo);
			callableStatement.setString(11, annoTributo);
			callableStatement.setInt(12, progressivoTributo);
			callableStatement.setString(13, String.valueOf(tipoTributo));
			callableStatement.setDouble(14, importoTributo);
			callableStatement.setDouble(15, importoPagatoPiuSgravi);
			callableStatement.setString(16, noteTributo);
			callableStatement.setString(17, String.valueOf(stato));
			callableStatement.setLong(18, progressivoFlusso);
			callableStatement.setString(19, codiceCapitolo);
			callableStatement.setString(20, accertamento);
			//inizio LP PG210130
			//callableStatement.registerOutParameter(21, Types.INTEGER);
			if(articolo == null) articolo = "";
			callableStatement.setString(21, articolo);
			if(idDominio == null) idDominio = "";
			callableStatement.setString(22, idDominio);
			if(ibanBancario == null) ibanBancario = "";
			callableStatement.setString(23, ibanBancario);
			if(ibanPostale == null) ibanPostale = "";
			callableStatement.setString(24, ibanPostale);
			//inizio LP PG22XX05
			//callableStatement.registerOutParameter(25, Types.INTEGER);
			if(codiceTipologiaServizio != null)
				callableStatement.setString(25, codiceTipologiaServizio);
			else
				callableStatement.setString(25, "");
			//inizio SB PGNTCORE-4
			if(metadatiPagoPATariTefaKey == null) metadatiPagoPATariTefaKey = "";
			callableStatement.setString(26, metadatiPagoPATariTefaKey);
			if(metadatiPagoPATariTefaValue == null) metadatiPagoPATariTefaValue = "";
			callableStatement.setString(27, metadatiPagoPATariTefaValue);
			//fine SB PGNTCORE-4
			callableStatement.registerOutParameter(28, Types.INTEGER);
			//fine LP PG22XX05
			//fine LP PG210130
			callableStatement.execute();
			//inizio LP PG210130
			//int i = callableStatement.getInt(21);
			//inizio LP PG22XX05
			//int i = callableStatement.getInt(25);
			int i = callableStatement.getInt(28);
			//fine LP PG22XX05
			//fine LP PG210130
			return i;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			//inizio LP 20240920 - PGNTECCSV-10
			if(bCloseStat) {
			//fine LP 20240920 - PGNTECCSV-10
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240920 - PGNTECCSV-10
				callableStatement = null;
			}
			//fine LP 20240920 - PGNTECCSV-10
			//fine LP PG21XX04 Leak
		}	
	}

	//PG170070_001 GG 20170418 - Introdotti email e emailPec
	//inizio LP 20240828 - PGNTACWS-22
	public int doInsertEH8(int prog, String tipoRecord, String codiceUtente, Date dataFlusso, String tipoServizio, 
						   String codEnte, String tipoUff, String codUfficio, String codImposta, String codfiscale,
						   String denominazione, String anagrafica, String bNascita, Date dataNascita, String status, String indirizzo, 
						   String bFiscale, char stato, String email, String emailPec) throws DaoException {
	//inizio LP 20240920 - PGNTECCSV-10
		return doInsertEH8Batch(true, true,
				prog, tipoRecord, codiceUtente, dataFlusso, tipoServizio,
				codEnte, tipoUff, codUfficio, codImposta, codfiscale,
				denominazione, anagrafica, bNascita, dataNascita, status, indirizzo,
				bFiscale, stato, email, emailPec);
	}

	public int doInsertEH8Batch(boolean bFlagUpdateAutocommit, boolean bCloseStat,
			int prog, String tipoRecord, String codiceUtente, Date dataFlusso, String tipoServizio, 
			String codEnte, String tipoUff, String codUfficio, String codImposta, String codfiscale,
			String denominazione, String anagrafica, String bNascita, Date dataNascita, String status, String indirizzo, 
			String bFiscale, char stato, String email, String emailPec) throws DaoException {
	//fine LP 20240920 - PGNTECCSV-10
		CallableStatement callableStatement = null;	
		try {
			//inizio LP 20240920 - PGNTECCSV-10
			//callableStatement = prepareCall(Routines.EH8_DOINSERT.routine());	
			callableStatement = prepareCall(bFlagUpdateAutocommit, Routines.EH8_DOINSERT.routine());
			//fine LP 20240920 - PGNTECCSV-10
			callableStatement.setString(1, tipoRecord);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setDate(3, dataFlusso);
			callableStatement.setString(4, tipoServizio);
			callableStatement.setString(5, codEnte);
			callableStatement.setString(6, tipoUff);
			callableStatement.setString(7, codUfficio);
			callableStatement.setString(8, codImposta);
			callableStatement.setString(9, codfiscale);
			callableStatement.setString(10, denominazione);
			callableStatement.setString(11, String.valueOf(anagrafica));
			callableStatement.setString(12, bNascita);
			callableStatement.setDate(13, dataNascita);
			callableStatement.setString(14, String.valueOf(status));
			callableStatement.setString(15, indirizzo);
			callableStatement.setString(16, bFiscale);
			callableStatement.setString(17, String.valueOf(stato));
			callableStatement.setString(18, email);
			callableStatement.setString(19, emailPec);
			callableStatement.setLong(20, prog);
			callableStatement.registerOutParameter(21, Types.INTEGER);
			callableStatement.execute();
			int i = callableStatement.getInt(21);
			return i;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			//inizio LP 20240920 - PGNTECCSV-10
			if(bCloseStat) {
			//fine LP 20240920 - PGNTECCSV-10
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240920 - PGNTECCSV-10
				callableStatement = null;
			}
			//fine LP 20240920 - PGNTECCSV-10
			//fine LP PG21XX04 Leak
		}	
	}
	
	//inizio LP 20240828 - PGNTACWS-22
	public int doInsertEH9(int prog, String tipoRecord, String codiceUtente, Date dataFlusso,  String tipoServizio, 
						   int numRec, char stato) throws DaoException {
	//inizio LP 20240920 - PGNTECCSV-10
		return doInsertEH9Batch(true, true,
				prog, tipoRecord, codiceUtente, dataFlusso, tipoServizio,
				numRec, stato);
	}

	public int doInsertEH9Batch(boolean bFlagUpdateAutocommit, boolean bCloseStat,
			int prog, String tipoRecord, String codiceUtente, Date dataFlusso,  String tipoServizio, 
			int numRec, char stato) throws DaoException {
	//fine LP 20240920 - PGNTECCSV-10
		CallableStatement callableStatement = null;	
		try {
			//inizio LP 20240920 - PGNTECCSV-10
			//callableStatement = prepareCall(Routines.EH9_DOINSERT.routine());	
			callableStatement = prepareCall(bFlagUpdateAutocommit, Routines.EH9_DOINSERT.routine());
			//fine LP 20240920 - PGNTECCSV-10
			callableStatement.setString(1, tipoRecord);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setDate(3, dataFlusso);
			callableStatement.setString(4, tipoServizio);
			callableStatement.setInt(5, numRec);
			callableStatement.setString(6, String.valueOf(stato));
			callableStatement.setLong(7, prog);
			callableStatement.registerOutParameter(8, Types.INTEGER);
			callableStatement.execute();
			int i = callableStatement.getInt(8);
			return i;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			//inizio LP 20240920 - PGNTECCSV-10
			if(bCloseStat) {
			//fine LP 20240920 - PGNTECCSV-10
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240920 - PGNTECCSV-10
				callableStatement = null;
			}
			//fine LP 20240920 - PGNTECCSV-10
			//fine LP PG21XX04 Leak
		}	
	}

//	public int doUpdateEHX(String tipoRecord, char tomb) throws DaoException {
//	CallableStatement callableStatement = null;	
//	try{
//		callableStatement = prepareCall(Routines.EHX_DOUPDATE.routine());	
//		callableStatement.setString(1, tipoRecord);
//		callableStatement.setString(2, String.valueOf(tomb));
//		callableStatement.registerOutParameter(3, Types.INTEGER);
//		callableStatement.execute();
//		int i = callableStatement.getInt(3);
//		return i;
//	} catch (SQLException x) {
//		throw new DaoException(x);
//	} catch (IllegalArgumentException x) {
//		throw new DaoException(x);
//	} catch (HelperException x) {
//		throw new DaoException(x);
//	} finally {
//		closeConnection(callableStatement);
//	}	
//}
//	public void doDeleteEHX(String tipoRecord, char tomb) throws DaoException {
//		CallableStatement callableStatement = null;	
//		try{
//			callableStatement = prepareCall(Routines.EHX_DODELETE.routine());	
//			callableStatement.setString(1, tipoRecord);
//			callableStatement.setString(2, String.valueOf(tomb));
//			callableStatement.execute();
//		} catch (SQLException x) {
//			throw new DaoException(x);
//		} catch (IllegalArgumentException x) {
//			throw new DaoException(x);
//		} catch (HelperException x) {
//			throw new DaoException(x);
//		} finally {
//			closeConnection(callableStatement);
//		}	
//	}
//	
	//inizio LP 20240828 - PGNTACWS-22
	public void doElaboraFlussiEc() throws DaoException {
	//inizio LP 20240920 - PGNTECCSV-10
		doElaboraFlussiEcBatch(true, true);
	}

	public void doElaboraFlussiEcBatch(boolean bFlagUpdateAutocommit, boolean bCloseStat) throws DaoException {
	//fine LP 20240920 - PGNTECCSV-10
		CallableStatement callableStatement = null;	
		try{
			//inizio LP 20240920 - PGNTECCSV-10
			//callableStatement = prepareCall(Routines.EHX_DOPROCESSFLOW.routine());	
			callableStatement = prepareCall(bFlagUpdateAutocommit, Routines.EHX_DOPROCESSFLOW.routine());
			//fine LP 20240920 - PGNTECCSV-10
			callableStatement.execute();
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			//inizio LP 20240920 - PGNTECCSV-10
			if(bCloseStat) {
			//fine LP 20240920 - PGNTECCSV-10
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240920 - PGNTECCSV-10
				callableStatement = null;
			}
			//fine LP 20240920 - PGNTECCSV-10
			//fine LP PG21XX04 Leak
		}	
	}

	//inizio LP 20240828 - PGNTACWS-22
	public void doRollbackElaboraFlussoEc(int progressivoFlusso) throws DaoException {
	//inizio LP 20240920 - PGNTECCSV-10
		doRollbackElaboraFlussoEcBatch(true, true, progressivoFlusso);
	}

	public void doRollbackElaboraFlussoEcBatch(boolean bFlagUpdateAutocommit, boolean bCloseStat, int progressivoFlusso) throws DaoException {
	//fine LP 20240920 - PGNTECCSV-10
		CallableStatement callableStatement = null;	
		try {
			//inizio LP 20240920 - PGNTECCSV-10
			//callableStatement = prepareCall(Routines.EHX_DOPROCESSFLOWROLLBACK.routine());	
			callableStatement = prepareCall(bFlagUpdateAutocommit, Routines.EHX_DOPROCESSFLOWROLLBACK.routine());
			//fine LP 20240920 - PGNTECCSV-10
			callableStatement.setLong(1, progressivoFlusso);
//			callableStatement.setString(2, "N");
			callableStatement.execute();
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			//inizio LP 20240920 - PGNTECCSV-10
			if(bCloseStat) {
			//fine LP 20240920 - PGNTECCSV-10
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240920 - PGNTECCSV-10
				callableStatement = null;
			}
			//fine LP 20240920 - PGNTECCSV-10
			//fine LP PG21XX04 Leak
		}	
	}
	
	//INIZIO - 13032019 PG1900XX_002_SB
	//inizio LP 20240828 - PGNTACWS-22
	public String deleteDocumento(ArchivioCarichiDocumento dto) throws DaoException {
	//inizio LP 20240920 - PGNTECCSV-10
		return deleteDocumentoBatch(true, true, dto);
	}

	public String deleteDocumentoBatch(boolean bFlagUpdateAutocommit, boolean bCloseStat, ArchivioCarichiDocumento dto) throws DaoException {
	//fine LP 20240920 - PGNTECCSV-10
		String retMessage = "";
		CallableStatement callableStatement = null;
		try	{		
			//inizio LP 20240920 - PGNTECCSV-10
			//callableStatement = prepareCall(Routines.PYEH1SP_DE2.routine());	
			callableStatement = prepareCall(bFlagUpdateAutocommit, Routines.PYEH1SP_DE2.routine());
			//fine LP 20240920 - PGNTECCSV-10
			callableStatement.setString(1, dto.getCodiceUtente());
			callableStatement.setString(2, dto.getTipoServizio());
			callableStatement.setString(3, dto.getCodiceEnte());			
			callableStatement.setString(4, dto.getTipoUfficio());
			callableStatement.setString(5, dto.getCodiceUfficio());
			callableStatement.setString(6, dto.getImpostaServizio());
			callableStatement.setString(7, dto.getNumeroDocumento());
			callableStatement.registerOutParameter(8, Types.VARCHAR);
			/* we execute procedure */
			callableStatement.execute(); 
			retMessage = callableStatement.getString(8);
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//closeCallableStatement(callableStatement);
			//inizio LP PG21XX04 Leak
			//inizio LP 20240920 - PGNTECCSV-10
			if(bCloseStat) {
			//fine LP 20240920 - PGNTECCSV-10
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240920 - PGNTECCSV-10
				callableStatement = null;
			}
			//fine LP 20240920 - PGNTECCSV-10
			//fine LP PG21XX04 Leak
		}
		return retMessage;
	}
	//FINE - 13032019 PG1900XX_002_SB
}