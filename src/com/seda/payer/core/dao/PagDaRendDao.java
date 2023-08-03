package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.FlussiRen;
import com.seda.payer.core.bean.PagDaRend_Contabilita;
import com.seda.payer.core.bean.PagDaRend_Freccia;
import com.seda.payer.core.bean.PagDaRend_ICI;
import com.seda.payer.core.bean.PagDaRend_IV;
import com.seda.payer.core.bean.RendPos;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

/**
 * 
 * @author f.vadicamo
 * Classe DAO che contiene i metodi che restuiscono le collection dei pagamenti
 * da renticontare per i tre tipi di transazioni (IV, ICI e Freccia)
 */

public class PagDaRendDao extends BaseDaoHandler{

	public PagDaRendDao(Connection connection, String schema) {
		super(connection, schema);
	}
	
	/**
	 * Restituisce la collection dei pagamenti "IV Campo" ancora da rendicontare
	 * con la data di effettivo pagamento precedente a quella di esecuzione
	 * 
	 * @return List<PagDaRend_IV>
	 * @throws DaoException
	 */
	public List<PagDaRend_IV> ListaPagDaRend_IV (String dataEsecuzione, String tipologieServizioExcluded, String flagRendQuad) throws DaoException
	{
		//inizio LP PG21XX04 Leak
		//CallableStatement callableStatement;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		List<PagDaRend_IV> lista = null;
		try
		{
			if((dataEsecuzione != null) && (!dataEsecuzione.equals("")))
			{
				callableStatement = prepareCall(Routines.TDT_LISTA_PAG_REND.routine());
				callableStatement.setString(1, dataEsecuzione);
				callableStatement.setString(2, tipologieServizioExcluded);
				callableStatement.setString(3, flagRendQuad);
				
				if(callableStatement.execute())
				{
					lista = new Vector<PagDaRend_IV>();
					//inizio LP PG21XX04 Leak
					//ResultSet data = callableStatement.getResultSet();
					data = callableStatement.getResultSet();
					//fine LP PG21XX04 Leak
					while (data.next())
					{
						lista.add(PagDaRend_IV.getBean(data));
					}
				}
			}
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
		return lista;
	}

	
	public List<PagDaRend_IV> ListaPagDaRend_IV_CUP(String dataEsecuzione, String tipologieServizioIncluded, String flagRendQuad) throws DaoException
	{
		//inizio LP PG21XX04 Leak
		//CallableStatement callableStatement;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		List<PagDaRend_IV> lista = null;
		try
		{
			if((dataEsecuzione != null) && (!dataEsecuzione.equals("")))
			{
				callableStatement = prepareCall(Routines.TDT_LISTA_PAG_REND_CUP.routine());
				callableStatement.setString(1, dataEsecuzione);
				callableStatement.setString(2, tipologieServizioIncluded);
				callableStatement.setString(3, flagRendQuad);
				if(callableStatement.execute())
				{
					lista = new Vector<PagDaRend_IV>();
					//inizio LP PG21XX04 Leak
					//ResultSet data = callableStatement.getResultSet();
					data = callableStatement.getResultSet();
					//fine LP PG21XX04 Leak
					while (data.next())
					{
						lista.add(PagDaRend_IV.getBeanCUP(data));
					}
				}
			}
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
		return lista;
	}
	
	/**
	 * Restituisce la collection dei pagamenti "Freccia" ancora da rendicontare
	 * con la data di effettivo pagamento precedente a quella di esecuzione
	 * 
	 * @return List<PagDaRend_Freccia>
	 * @throws DaoException
	 */
	public List<PagDaRend_Freccia> ListaPagDaRend_Freccia (String dataEsecuzione) throws DaoException
	{
		//inizio LP PG21XX04 Leak
		//CallableStatement callableStatement;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		List<PagDaRend_Freccia> lista = null;
		try
		{
			if((dataEsecuzione != null) && (!dataEsecuzione.equals("")))
			{
				callableStatement = prepareCall(Routines.TFR_LISTA_PAG_REND.routine());
				callableStatement.setString(1, dataEsecuzione);
				if(callableStatement.execute())
				{
					lista = new Vector<PagDaRend_Freccia>();
					//inizio LP PG21XX04 Leak
					//ResultSet data = callableStatement.getResultSet();
					data = callableStatement.getResultSet();
					//fine LP PG21XX04 Leak
					while (data.next())
					{
						lista.add(PagDaRend_Freccia.getBean(data));
					}
				}
			}
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
		return lista;
	}

	/**
	 * Restituisce la collection dei pagamenti "ICI" ancora da rendicontare
	 * con la data di effettivo pagamento precedente a quella di esecuzione
	 * 
	 * @return List<PagDaRend_ICI>
	 * @throws DaoException
	 */
	public List<PagDaRend_ICI> ListaPagDaRend_ICI (String dataEsecuzione, String flagTipoRendicontazione) throws DaoException
	{
		//inizio LP PG21XX04 Leak
		//CallableStatement callableStatement;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		List<PagDaRend_ICI> lista = null;
		try
		{
			if((dataEsecuzione != null) && (!dataEsecuzione.equals("")))
			{
				callableStatement = prepareCall(Routines.TIC_LISTA_PAG_REND.routine());
				callableStatement.setString(1, dataEsecuzione);
				callableStatement.setString(2, flagTipoRendicontazione);
				if(callableStatement.execute())
				{
					lista = new Vector<PagDaRend_ICI>();
					//inizio LP PG21XX04 Leak
					//ResultSet data = callableStatement.getResultSet();
					data = callableStatement.getResultSet();
					//fine LP PG21XX04 Leak
					while (data.next())
					{
						if (flagTipoRendicontazione.equals("Y"))
							lista.add(PagDaRend_ICI.getBeanSeda(data));
						else
							lista.add(PagDaRend_ICI.getBean(data));
					}
				}
			}

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
		return lista;
	}
	
	public boolean aggiornaChiaveRen (String chiaveSpedizione,String szTipiFlusso,String szKeyRendUpdate,FlussiRen fr)throws DaoException{
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.FLREN_UPD_GLOBAL.routine());
			callableStatement = prepareCall(Routines.FLREN_UPD_GLOBAL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, chiaveSpedizione);
			callableStatement.setString(2, szTipiFlusso);
			callableStatement.setString(3, szKeyRendUpdate );
			
			callableStatement.setString(4,chiaveSpedizione);
			callableStatement.setString(5, fr.getTipologiaFlusso());
			callableStatement.setString(6, fr.getCodiceSocieta());
			callableStatement.setString(7, fr.getCodiceUtente());
			callableStatement.setString(8, fr.getChiaveEnte());
			callableStatement.setInt(9, fr.getProgressivoFlusso());
			callableStatement.setString(10, fr.getCodiceTiplogiaServizio());
			callableStatement.setString(11, fr.getCodiceImpostaServizio());
			callableStatement.setString(12, fr.getChiaveGateway());
			callableStatement.setString(13, fr.getNumeroContoCorrentePostale());
			callableStatement.setString(14, fr.getNomeFile());
			callableStatement.setString(15, "N");
			callableStatement.setString(16, "N");
			callableStatement.setString(17, fr.getOperatoreUltimoAggiornamento());
			callableStatement.registerOutParameter(18, Types.INTEGER);
			//inizio LP PG200060
			//callableStatement.setString(19, fr.getIdPSP());  //PG190180_001 - 20190528
			callableStatement.setString(19, (fr.getIdPSP() != null ? fr.getIdPSP() : ""));
			//fine LP PG200060
			
			callableStatement.executeUpdate();
			
			int numrighe =  callableStatement.getInt(18);
			
			//System.err.println("Result =" + numrighe + " chiaveSpedizione = " + chiaveSpedizione + " szTipiFlusso = " + szTipiFlusso + " szKeyRendUpdate = " +szKeyRendUpdate);
						
			if (numrighe == 0) 
				return true;
			
			return false;
			
			
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

//inizio LP 20180711
	public boolean aggiornaChiaveRenEvol(String chiaveSpedizione, FlussiRen fr) throws DaoException{
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.FLREN_UPD_GLOBAL_EVOL.routine());
			callableStatement = prepareCall(Routines.FLREN_UPD_GLOBAL_EVOL.routine());
			//fine LP PG21XX04 Leak
			System.out.println("aggiornaChiaveRenEvol - chiaveSpedizione: " + chiaveSpedizione);
			System.out.println("aggiornaChiaveRenEvol - fr.getTipologiaFlusso: " + fr.getTipologiaFlusso());
			System.out.println("aggiornaChiaveRenEvol - fr.getCodiceSocieta: " + fr.getCodiceSocieta());
			System.out.println("aggiornaChiaveRenEvol - fr.getCodiceUtente: " + fr.getCodiceUtente());
			System.out.println("aggiornaChiaveRenEvol - fr.getChiaveEnte: " + fr.getChiaveEnte());
			System.out.println("aggiornaChiaveRenEvol - fr.getProgressivoFlusso: " + fr.getProgressivoFlusso());
			System.out.println("aggiornaChiaveRenEvol - fr.getCodiceTiplogiaServizio: " + fr.getCodiceTiplogiaServizio());
			System.out.println("aggiornaChiaveRenEvol - fr.getCodiceImpostaServizio: " + fr.getCodiceImpostaServizio());
			System.out.println("aggiornaChiaveRenEvol - fr.getChiaveGateway: " + fr.getChiaveGateway());
			System.out.println("aggiornaChiaveRenEvol - fr.getNumeroContoCorrentePostale: " + fr.getNumeroContoCorrentePostale());
			System.out.println("aggiornaChiaveRenEvol - fr.getNomeFile: " + fr.getNomeFile());
			System.out.println("aggiornaChiaveRenEvol - fr.getOperatoreUltimoAggiornamento: " + fr.getOperatoreUltimoAggiornamento());
			System.out.println("aggiornaChiaveRenEvol - fr.getIdPSP: " + fr.getIdPSP());
			
			callableStatement.setString(1, chiaveSpedizione);
			callableStatement.setString(2, fr.getTipologiaFlusso());
			callableStatement.setString(3, fr.getCodiceSocieta());
			callableStatement.setString(4, fr.getCodiceUtente());
			callableStatement.setString(5, fr.getChiaveEnte());
			callableStatement.setInt(6, fr.getProgressivoFlusso());
			callableStatement.setString(7, fr.getCodiceTiplogiaServizio());
			callableStatement.setString(8, fr.getCodiceImpostaServizio());
			callableStatement.setString(9, fr.getChiaveGateway());
			callableStatement.setString(10, fr.getNumeroContoCorrentePostale());
			callableStatement.setString(11, fr.getNomeFile());
			callableStatement.setString(12, "N");
			callableStatement.setString(13, "N");
			callableStatement.setString(14, fr.getOperatoreUltimoAggiornamento());
			callableStatement.registerOutParameter(15, Types.INTEGER);
			callableStatement.setString(16, fr.getIdPSP());
			
			callableStatement.executeUpdate();
			
			int numrighe =  callableStatement.getInt(15);
			
			//System.err.println("Result =" + numrighe + " chiaveSpedizione = " + chiaveSpedizione);
						
			if (numrighe == 0) 
				return true;
			
			return false;
			
			
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

	public boolean aggiornaChiaveRenEvolList(String chiaveSpedizione, String szTipiFlusso, String szKeyRendUpdate) throws DaoException{
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.FLREN_UPD_GLOBAL_LIST.routine());
			callableStatement = prepareCall(Routines.FLREN_UPD_GLOBAL_LIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, chiaveSpedizione);
			callableStatement.setString(2, szTipiFlusso);
			callableStatement.setString(3, szKeyRendUpdate );
			
			callableStatement.registerOutParameter(4, Types.INTEGER);
			
			callableStatement.executeUpdate();
			
			int numrighe =  callableStatement.getInt(4);
			
			//System.err.println("Result =" + numrighe + " chiaveSpedizione = " + chiaveSpedizione + " szTipiFlusso = " + szTipiFlusso + " szKeyRendUpdate = " +szKeyRendUpdate);
						
			if (numrighe == 0) 
				return true;
			
			return false;
			
			
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
	//fine LP 20180711
	
	/*
	 * CREATE PROCEDURE PYRENTB_SEL_CONT (  IN I_DATA_ESECUZIONE VARCHAR(10),
	IN I_REN_TBOLTFLU  CHAR(3) ,IN I_REN_CSOCCSOC CHAR(5) ,
	IN I_REN_CUTECUTE CHAR(5) ,IN I_REN_KANEKENT CHAR(10))
	 * */
	public int PagDaRend_Counter (String dataEsecuzione,String szBollettino,String szSocieta,
			String szUtente,String szEnt) throws DaoException
	{
		//inizio LP PG21XX04 Leak
		//CallableStatement callableStatement;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		int nResult = 0;
		try
		{
			if((dataEsecuzione != null) && (!dataEsecuzione.equals("")))
			{
				callableStatement = prepareCall(Routines.FLREN_SEL_FOR_CONT.routine());
				callableStatement.setString(1, dataEsecuzione);
				callableStatement.setString(2, szBollettino);
				callableStatement.setString(3, szSocieta);
				callableStatement.setString(4, szUtente);
				callableStatement.setString(5, szEnt);
				if(callableStatement.execute())
				{
					//inizio LP PG21XX04 Leak
					//ResultSet data = callableStatement.getResultSet();
					data = callableStatement.getResultSet();
					//fine LP PG21XX04 Leak
					if (data.next())
					{
						nResult = data.getInt(2);
					}
				}
			}
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
		return nResult;
	}
	
	public List<RendPos> ListaRendPos () throws DaoException
	{
		//inizio LP PG21XX04 Leak
		//CallableStatement callableStatement;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		List<RendPos> lista = null;
		try
		{
			callableStatement = prepareCall(Routines.TX_LISTA_REND_POS.routine());
			if(callableStatement.execute())
			{
				lista = new Vector<RendPos>();
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				while (data.next())
				{
					lista.add(RendPos.getBean(data));
				}
			}
		}
		catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} catch (Exception e) {
			e.printStackTrace();
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
		return lista;
	}
	
	/*****FLUSSO CONTABILITA' PER RENDICONTAZIONE SEDA*******/

	/**
	 * Seleziona le chiavi di rendicontazione che hanno tipologia rendicontazione seda
     * e per cui non è ancora stato prodotto il flusso di contabilità
	 */
	public List<String> getListaRenContabilita() throws DaoException
	{
		CallableStatement callableStatement = null;
		List<String> listaChiaviRen = null;
		ResultSet data = null;
		try
		{
			callableStatement = prepareCall(Routines.CNB_DOLISTREN.routine());
			
			if(callableStatement.execute())
			{
				listaChiaviRen = new ArrayList<String>();
				data = callableStatement.getResultSet();
				while (data.next())
					listaChiaviRen.add(data.getString("REN_KRENKREN"));
			}
		}
		catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
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
		return listaChiaviRen;
	}
	
	/**
	 * Ritorna la lista delle transazioni da inserire nel flusso di contabilità
	 * @param listChiaviRen
	 * @return
	 * @throws DaoException
	 */
	public List<PagDaRend_Contabilita> getListaTransazioniContabilita(String listChiaviRen) throws DaoException
	{
		//inizio LP 20180713
		/*
		CallableStatement callableStatement = null;
		ResultSet data = null;
		List<PagDaRend_Contabilita> lista = null;
		
		try
		{
			callableStatement = prepareCall(Routines.CNB_DOLISTTRA.routine());
			callableStatement.setString(1, listChiaviRen);
			
			if(callableStatement.execute())
			{
				lista = new ArrayList<PagDaRend_Contabilita>();
				data = callableStatement.getResultSet();
				while (data.next())
					lista.add(PagDaRend_Contabilita.getBean(data));
			}
		}
		catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (callableStatement != null)
				DAOHelper.closeIgnoringException(callableStatement);
			if (data != null)
				DAOHelper.closeIgnoringException(data);
		}
		*/
		
		List<PagDaRend_Contabilita> lista = null;
		boolean bVal = true;
		//Bisogna suddividere la stringa in max "soglia" transazioni alla volta
		int soglia = 200; //La soglia puo' essere al piu' 320 == circa 32000 / 33 / 3 dove 33 == (30 x "keyren" + 2 x "'" + 1 x ",")] 
		String[] splitKey = listChiaviRen.split("\\,");
		int cont = splitKey.length;
		try
		{
			lista = new ArrayList<PagDaRend_Contabilita>();
			if(cont <= soglia) {
				bVal = getListaTransazioniContabilitaTail(listChiaviRen, lista);
			} else {
				String sparzKey = "";
				int q = cont / soglia;
				int r = cont % soglia;
				int i = 0;
				int j = 0;
				for( ; i < q && bVal; i++) {
					sparzKey = "";
					for(j = 0; j < soglia; j++) {
						sparzKey += splitKey[(i * soglia) + j] + ",";	
					}
					bVal = getListaTransazioniContabilitaTail(sparzKey.substring(0, sparzKey.length() - 1), lista);
				}
				if(bVal && (r > 0)) {
					sparzKey = "";
					for(j = 0; j < r; j++) {
						sparzKey += splitKey[(i * soglia) + j] + ",";	
					}
					bVal = getListaTransazioniContabilitaTail(sparzKey.substring(0, sparzKey.length() - 1), lista);
				}
			}
	    } catch (DaoException e) {
		  throw new DaoException(e);
	    } catch (Exception e) {
			e.printStackTrace();
			bVal = false;
		}
		if(!bVal) {
			return null;
		}
		//fine LP 20180713
        return lista;
	}
	
	//inizio LP 20180713
	public boolean getListaTransazioniContabilitaTail(String listChiaviRen, List<PagDaRend_Contabilita> lista) throws DaoException
	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		boolean bOk = true;
		try
		{
			callableStatement = prepareCall(Routines.CNB_DOLISTTRA.routine());
			callableStatement.setString(1, listChiaviRen);
			
			if(callableStatement.execute())
			{
				data = callableStatement.getResultSet();
				while (data.next())
					lista.add(PagDaRend_Contabilita.getBean(data));
			}
		}
		catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} catch (Exception e) {
			e.printStackTrace();
			bOk = false;
		}
		finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
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
		return bOk;
	}
	//fine LP 20180713
	
	/**
	 * Ritorna il progressivo per il flusso di contabilità, per data e dbSchemaCodSocieta
	 * @param data
	 * @param dbSchemaCodSocieta
	 * @return
	 * @throws DaoException
	 */
	public int getProgressivoContabilita(String data, String dbSchemaCodSocieta) throws DaoException
	{
		CallableStatement callableStatement = null;
		try
		{
			callableStatement = prepareCall(Routines.CNB_DOSEL_PROG.routine());
			callableStatement.setString(1, data);
			callableStatement.setString(2, dbSchemaCodSocieta);
			
			callableStatement.registerOutParameter(3, Types.INTEGER);
			
			callableStatement.execute();
			
			return callableStatement.getInt(3);
			
		}
		catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return 0;
	}
	
	/**
	 * Inserisce un record nella tabella di contabilità (PYCNBTB) e aggiorna tutte le righe della tabella 
	 * di rendicontazione interessate, settando la chiave della tabella di contabilità
	 * @param listaChiaviRen
	 * @param dbSchemaCodSocieta
	 * @param progressivo
	 * @param nomeFile
	 * @param operatore
	 * @return
	 * @throws DaoException
	 */
	public boolean aggiornaRenContabilita(String listaChiaviRen, String dbSchemaCodSocieta, int progressivo, 
			String nomeFile, String operatore) throws DaoException
	{
		CallableStatement callableStatement = null;
		try
		{
			callableStatement = prepareCall(Routines.CNB_DOUPDATE_GLOBAL.routine());
			callableStatement.setString(1, listaChiaviRen);
			callableStatement.setString(2, dbSchemaCodSocieta);
			callableStatement.setInt(3, progressivo);
			callableStatement.setString(4, nomeFile);
			callableStatement.setString(5, operatore);
			
			callableStatement.registerOutParameter(6, Types.INTEGER);
			
			callableStatement.executeUpdate();
			
			int retCode =  callableStatement.getInt(6);

			if (retCode == 0) 
				return true;
			
			return false;
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
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
	
	//inizio LP 20180713
	public String aggiornaRenContabilitaEvol(String dbSchemaCodSocieta, int progressivo, 
			                                 String nomeFile, String operatore) throws DaoException
	{
		CallableStatement callableStatement = null;
		String chiaveRen = "";
		try
		{
			callableStatement = prepareCall(Routines.CNB_DOUPDATE_GLOBAL_EVOL.routine());
			callableStatement.setString(1, dbSchemaCodSocieta);
			callableStatement.setInt(2, progressivo);
			callableStatement.setString(3, nomeFile);
			callableStatement.setString(4, operatore);
			
			callableStatement.registerOutParameter(5, Types.CHAR);
			callableStatement.registerOutParameter(6, Types.INTEGER);
			
			callableStatement.executeUpdate();
			
			chiaveRen = callableStatement.getString(5);
			int retCode =  callableStatement.getInt(6);

			if (retCode == 0) 
				return chiaveRen;
			
			return "";
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
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
	
	public boolean aggiornaRenContabilitaList(String listaChiaviRen, String chiaveRen) throws DaoException
	{
		CallableStatement callableStatement = null;
		try
		{
			callableStatement = prepareCall(Routines.CNB_DOUPDATE_GLOBAL_LIST.routine());
			callableStatement.setString(1, listaChiaviRen);
			callableStatement.setString(2, chiaveRen);
			
			callableStatement.registerOutParameter(3, Types.INTEGER);
			
			callableStatement.executeUpdate();
			
			int retCode =  callableStatement.getInt(3);

			if (retCode == 0) 
				return true;
			
			return false;
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
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
	//fine LP 20180713
	
	//PGNTCORE-6 GG - inizio
	public Boolean getFlagDaRendicontare (String chiaveEnte, String numeroAvviso, String flagRendQuad) throws DaoException
	{
		CallableStatement callableStatement = null;
		Boolean flagDaRendicontare = false;
		try
		{
			callableStatement = prepareCall(Routines.TDT_REND_COR.routine());
			callableStatement.setString(1, chiaveEnte);
			callableStatement.setString(2, numeroAvviso);
			callableStatement.setString(3, flagRendQuad);
			callableStatement.registerOutParameter(4, Types.CHAR);
			callableStatement.execute();
			String res = callableStatement.getString(4);
			if (res!=null && res.trim().equals("Y")) {
				flagDaRendicontare = true;
			}
		}
		catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		}
		finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return flagDaRendicontare;
	}
	//PGNTCORE-6 GG - fine

}
