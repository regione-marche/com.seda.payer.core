package com.seda.payer.core.wallet.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.AnagraficaRivestizione350;
import com.seda.payer.core.wallet.bean.AnagraficaRivestizioneCSI;
import com.seda.payer.core.wallet.bean.AnagraficaRivestizione500;
import com.seda.payer.core.wallet.bean.AnagraficaRivestizione512;

public interface AnagraficaRivestizioneDAO extends Serializable {
	
	public final static String ANAGRAFICA_RIVESTIZIONE = "PYANASP_SEQ";
	public final static String ANAGRAFICA_RIVESTIZIONE_350 = "PY350SP_LST";
	public final static String ANAGRAFICA_RIVESTIZIONE_CSI = "PYCSISP_LST";
	public final static String ANAGRAFICA_RIVESTIZIONE_500 = "PY500SP_LST";
	public final static String ANAGRAFICA_RIVESTIZIONE_511 = "PY511SP_UPD";
	public final static String ANAGRAFICA_RIVESTIZIONE_512 = "PY512SP_LST";
	//12022015 GG PG140450 - inizio
	public final static String ANAGRAFICA_RIVESTIZIONE_512_EC = "PY512SP_LST_EC";
	//12022015 GG PG140450 - fine
	public final static String ANAGRAFICA_RIVESTIZIONE_512_ONLY_ONE = "PY512SP_ONLY_ONE";
	
	public int seqAnagraficaRivestizione() throws  DaoException;
	public ArrayList<AnagraficaRivestizione350> listAnagraficaRivestizione350(String welcomeKit) throws  DaoException;
	public ArrayList<AnagraficaRivestizioneCSI> listAnagraficaRivestizioneCSI(String welcomeKit) throws  DaoException;
	public ArrayList<AnagraficaRivestizione500> listAnagraficaRivestizione500(String welcomeKit, String societa, String ente) throws  DaoException;
	public void updateAnagraficaRivestizione511(String codiceFiscale, String cap, String indirizzo, String provincia, String comune) throws DaoException;
	public TreeSet<AnagraficaRivestizione512> listAnagraficaRivestizione512(String welcomeKit, int lunghezzaAnagrafica, String ente, String tipoElab, String codiceIban, String flagRivestizione) throws  DaoException;
	//12022015 GG PG140450 - inizio
	public TreeSet<AnagraficaRivestizione512> listAnagraficaRivestizione512Ec(String welcomeKit, int lunghezzaAnagrafica, String ente, String tipoElab, String codiceIban, String flagRivestizione, String flagPresenzaEc, String flagAttivazioneEc) throws  DaoException;
	//12022015 GG PG140450 - fine
}