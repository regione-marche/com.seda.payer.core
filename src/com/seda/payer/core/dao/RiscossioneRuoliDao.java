//da com.seda.payer.core.dao;
package com.seda.payer.core.dao;

//import com.seda.payer.riversamento.facade.logger.LogWriter;

//import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.InfoEnte;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.bean.Ruolo;
import com.seda.payer.core.bean.PartitaRuolo;
import com.seda.payer.core.bean.ArticoloPartitaRuolo;
import com.seda.payer.core.bean.PagamentoPartitaRuolo;
import com.seda.payer.core.bean.AnagraficaPartitaRuolo;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

//import javax.sql.rowset.WebRowSet;


public class RiscossioneRuoliDao extends BaseDaoHandler {

	/*
	private final String PYENTSP_LST_RUOLI = "PYENTSP_LST_RUOLI";
	private final String PYENTSP_IS_REG = "PYENTSP_IS_REG";
	private final String PYRLGSP_INS_UPD = "PYRLGSP_INS_UPD";
	
	private final String PYRANSP_INS = "PYRANSP_INS";	
	private final String PYRANSP_UPD = "PYRANSP_UPD";	
	private final String PYRRUSP_INS = "PYRRUSP_INS";
	private final String PYRRUSP_UPD = "PYRRUSP_UPD";	
	private final String PYRPASP_INS = "PYRPASP_INS";	
	private final String PYRPASP_UPD = "PYRPASP_UPD";	
	private final String PYRARSP_INS = "PYRARSP_INS";
	private final String PYRARSP_UPD = "PYRARSP_UPD";	
	private final String PYRMOSP_INS = "PYRMOSP_INS";
	private final String PYRMOSP_UPD = "PYRMOSP_UPD";	

	private final String PYRLFSP_INS = "PYRLFSP_INS";

	private final String PYRRUSP_ELAB = "PYRRUSP_ELAB";
	private final String PYRRUSP_DEL_TOMB_FLUSSO = "PYRRUSP_DEL_TOMB_FLUSSO";
	*/
	
	public RiscossioneRuoliDao(Connection connection, String schema) {
		super(connection, schema);
	}
	
	
	
	public 	long logElaborazioneFlussoRuoli(String nomeFileRuoli,
			String nomeFilePartite, // impostato con il nome del file partite/articoli,
			String nomeFilePagamenti, // impostato con il nome del file pagamenti, 
			String cartellaFile, // impostato con il percorso indicato nel file .properties, 
			String tipologiaLog,  
			long progressivoFlusso,
			String codiceSocieta, 
			String codiceUtente,
			String chiaveEnte) throws DaoException
	{
		long risultato = -1;
		
		Long numeroProg = 0L;
		Date test =  new Date();    
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{

			if (tipologiaLog.equals("I"))
			{
				numeroProg = Long.parseLong(nomeFileRuoli.substring(21,24));
			    test = new SimpleDateFormat("yyyyMMdd").parse(nomeFileRuoli.substring(13,21));
			}
		    
//				CallableStatement callableStatement = prepareCall(PYRLGSP_INS_UPD);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYRLGSP_INS_UPD.routine());
			callableStatement = prepareCall(Routines.PYRLGSP_INS_UPD.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, tipologiaLog);				
			callableStatement.setLong(2, progressivoFlusso);				
			callableStatement.setString(3, codiceSocieta);
			callableStatement.setString(4, codiceUtente);
			callableStatement.setString(5, chiaveEnte);
			callableStatement.setDate(6, new java.sql.Date(test.getTime()));
			callableStatement.setLong(7, numeroProg);
			callableStatement.setString(8, nomeFileRuoli);
			callableStatement.setString(9, nomeFilePartite);
			callableStatement.setString(10, nomeFilePagamenti);
			
			callableStatement.registerOutParameter(11, Types.BIGINT);

			callableStatement.execute();
			
			if (tipologiaLog.equals("I"))
				risultato = new Long(callableStatement.getLong(11));
			else
				risultato = 0;
						
		}
	catch (Exception e)
	{
	 throw new DaoException(e);
	}
	//inizio LP PG21XX04 Leak
	finally
	{
		if (callableStatement != null) {
			try {
				callableStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//fine LP PG21XX04 Leak
	return risultato;
	
		
	}		

	
	public ArrayList<InfoEnte> getListaEntiRuoli() throws DaoException 
	{
		ArrayList<InfoEnte> result = new ArrayList<InfoEnte>();
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
//			CallableStatement callableStatement = prepareCall(PYENTSP_LST_RUOLI);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYENTSP_LST_RUOLI.routine());
			callableStatement = prepareCall(Routines.PYENTSP_LST_RUOLI.routine());
			//fine LP PG21XX04 Leak
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				while (data.next())
				{
					
					
					InfoEnte ent = new InfoEnte(data.getString("ENT_CSOCCSOC"),
							            data.getString("ENT_CUTECUTE"),
							            data.getString("ANE_CANECENT"));
					
			//		Ente ent = new Ente(data);
					result.add(ent);					
				}
			}
			return result;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally
		{
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

	public boolean isEnteRegistered(InfoEnte ente) throws DaoException 
	{
	//inizio LP PG21XX04 Leak
	CallableStatement callableStatement = null;
	//fine LP PG21XX04 Leak
	boolean risultato = false;
	try
	{
//		CallableStatement callableStatement = prepareCall(PYENTSP_IS_REG);
		//inizio LP PG21XX04 Leak
		//CallableStatement callableStatement = prepareCall(Routines.PYENTSP_IS_REG.routine());
		callableStatement = prepareCall(Routines.PYENTSP_IS_REG.routine());
		//fine LP PG21XX04 Leak
		callableStatement.setString(1, ente.getCodSocieta());
		callableStatement.setString(2, ente.getCodUtente());
		callableStatement.setString(3, ente.getCodEnte());
		callableStatement.registerOutParameter(4, Types.VARCHAR);
		callableStatement.registerOutParameter(5, Types.VARCHAR);
		callableStatement.execute();
		ente.setChiaveEnte(callableStatement.getString(4));
        risultato = callableStatement.getString(5).equals("Y");			
 	} catch (SQLException x) {
		throw new DaoException(x);
	} catch (IllegalArgumentException x) {
		throw new DaoException(x);
	} catch (HelperException x) {
		throw new DaoException(x);
	}
	//inizio LP PG21XX04 Leak
	finally
	{
		if (callableStatement != null) {
			try {
				callableStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//fine LP PG21XX04 Leak
	return risultato;
		
	}
	
	
	public void salvaRuolo(Ruolo ruolo, boolean inserimento, ArrayList<String> messaggiLog) throws DaoException
	{
		CallableStatement callableStatement = null;
		try	{
			if (inserimento)
//				callableStatement = prepareCall(PYRRUSP_INS);
				callableStatement = prepareCall(Routines.PYRRUSP_INS.routine());
			else 
				//callableStatement = prepareCall(PYRRUSP_UPD);
				callableStatement = prepareCall(Routines.PYRRUSP_UPD.routine());

			ruolo.save(callableStatement);	
			callableStatement.execute();
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			System.out.println("doSave failed generic error due to: " + x.getMessage());
			throw new DaoException(101, x.getMessage());
		} catch (Exception x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally
		{
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

	public void salvaPartita(PartitaRuolo partita, boolean inserimento, ArrayList<String> messaggiLog) throws DaoException
	{
		CallableStatement callableStatement = null;
		try	{
			if (inserimento)
				//callableStatement = prepareCall(PYRPASP_INS);
				callableStatement = prepareCall(Routines.PYRPASP_INS.routine());
			else 
				//callableStatement = prepareCall(PYRPASP_UPD);
				callableStatement = prepareCall(Routines.PYRPASP_UPD.routine());

			partita.save(callableStatement);
			callableStatement.execute();
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			System.out.println("doSave failed generic error due to: " + x.getMessage());
			throw new DaoException(101, x.getMessage());
		} catch (Exception x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally
		{
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


	public void salvaArticolo(ArticoloPartitaRuolo articolo, boolean inserimento, ArrayList<String> messaggiLog) throws DaoException
	{
		CallableStatement callableStatement = null;
		try	{
			if (inserimento)
				//callableStatement = prepareCall(PYRARSP_INS);
				callableStatement = prepareCall(Routines.PYRARSP_INS.routine());
			else 
				//callableStatement = prepareCall(PYRARSP_UPD);
				callableStatement = prepareCall(Routines.PYRARSP_UPD.routine());
			
			articolo.save(callableStatement);
			callableStatement.execute();
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			System.out.println("doSave failed generic error due to: " + x.getMessage());
			throw new DaoException(101, x.getMessage());
		} catch (Exception x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally
		{
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

	
	public void salvaPagamento(PagamentoPartitaRuolo pagamento, boolean inserimento, ArrayList<String> messaggiLog) throws DaoException
	{
		CallableStatement callableStatement = null;
		try	{
			if (inserimento)
				//callableStatement = prepareCall(PYRMOSP_INS);
				callableStatement = prepareCall(Routines.PYRMOSP_INS.routine());
			else 
				//callableStatement = prepareCall(PYRMOSP_UPD);
				callableStatement = prepareCall(Routines.PYRMOSP_UPD.routine());

			pagamento.save(callableStatement);
			callableStatement.execute();
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			System.out.println("doSave failed generic error due to: " + x.getMessage());
			throw new DaoException(101, x.getMessage());
		} catch (Exception x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally
		{
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

	public void cancellaPagamento(PagamentoPartitaRuolo pagamento, ArrayList<String> messaggiLog) throws DaoException
	{
		
	}

	public void salvaAnagrafica(AnagraficaPartitaRuolo anagrafica, boolean inserimento, ArrayList<String> messaggiLog) throws DaoException
	{
		CallableStatement callableStatement = null;
		try	{
			if (inserimento)
				//callableStatement = prepareCall(PYRANSP_INS);
				callableStatement = prepareCall(Routines.PYRANSP_INS.routine());
			else 
				//callableStatement = prepareCall(PYRANSP_UPD);
				callableStatement = prepareCall(Routines.PYRANSP_UPD.routine());

			anagrafica.save(callableStatement);
			callableStatement.execute();
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			System.out.println("doSave failed generic error due to: " + x.getMessage());
			throw new DaoException(101, x.getMessage());
		} catch (Exception x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally
		{
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
	
	public void elaboraFlussiRuoli(String codSocieta, String codUtente, String chiaveEnte, String condConcessione) throws DaoException
	{
		CallableStatement callableStatement = null;
		try	{
//			callableStatement = prepareCall(PYRRUSP_ELAB);
			callableStatement = prepareCall(Routines.PYRRUSP_ELAB.routine());
			callableStatement.setString(1, codSocieta);
			callableStatement.setString(2, codUtente);
			callableStatement.setString(3, chiaveEnte);			
			callableStatement.setString(4, condConcessione);
			callableStatement.registerOutParameter(5, Types.INTEGER);
			callableStatement.execute();
		} 
		catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			System.out.println("doSave failed generic error due to: " + x.getMessage());
			throw new DaoException(101, x.getMessage());
		} catch (Exception x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally
		{
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

	public void cancellaFlussoRuoli(long progFlusso, String codSocieta, String codUtente, String chiaveEnte, String condConcessione) throws DaoException
	{
		CallableStatement callableStatement = null;
		try	{
//			callableStatement = prepareCall(PYRRUSP_DEL_TOMB_FLUSSO);
			callableStatement = prepareCall(Routines.PYRRUSP_DEL_TOMB_FLUSSO.routine());
			callableStatement.setLong(1, progFlusso);
			callableStatement.setString(2, codSocieta);
			callableStatement.setString(3, codUtente);
			callableStatement.setString(4, chiaveEnte);			
			callableStatement.setString(5, condConcessione);
			callableStatement.setString(6, "N");
			callableStatement.registerOutParameter(7, Types.INTEGER);
			callableStatement.execute();
		} 
		catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			System.out.println("doSave failed generic error due to: " + x.getMessage());
			throw new DaoException(101, x.getMessage());
		} catch (Exception x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally
		{
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

	
	public void logFasiFlussoRuoli(long progFlusso, Date inizioFase, Date fineFase, String codFase, String messaggioFase, String descrizioneFase) throws DaoException
	{
		CallableStatement callableStatement = null;
		try	{
//			callableStatement = prepareCall(PYRLFSP_INS);
			callableStatement = prepareCall(Routines.PYRLFSP_INS.routine());
			callableStatement.setLong(1, progFlusso);
			callableStatement.setString(2, codFase);			
			callableStatement.setString(3, descrizioneFase);			
			callableStatement.setTimestamp(4, new Timestamp(inizioFase.getTime()));
			callableStatement.setTimestamp(5, new Timestamp(fineFase.getTime()));
			callableStatement.setString(6, messaggioFase);			
			callableStatement.execute();
		} 
		catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			System.out.println("doSave failed generic error due to: " + x.getMessage());
			throw new DaoException(101, x.getMessage());
		} catch (Exception x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally
		{
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