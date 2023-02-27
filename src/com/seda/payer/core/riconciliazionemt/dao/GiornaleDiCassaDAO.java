package com.seda.payer.core.riconciliazionemt.dao;

import java.sql.Connection;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.riconciliazionemt.bean.ContoList;
import com.seda.payer.core.riconciliazionemt.bean.FlussiAbbinatiList;
import com.seda.payer.core.riconciliazionemt.bean.Flusso;
import com.seda.payer.core.riconciliazionemt.bean.FlussoPageList;
import com.seda.payer.core.riconciliazionemt.bean.GiornaleDiCassa;
import com.seda.payer.core.riconciliazionemt.bean.GiornaleDiCassaPageList;
import com.seda.payer.core.riconciliazionemt.bean.MittenteList;
import com.seda.payer.core.riconciliazionemt.bean.MovimentoDiCassa;
import com.seda.payer.core.riconciliazionemt.bean.MovimentoDiCassaPageList;
import com.seda.payer.core.riconciliazionemt.bean.NumeroDocumentoList;
import com.seda.payer.core.riconciliazionemt.bean.PspList;
import com.seda.payer.core.riconciliazionemt.bean.Transazione;
import com.seda.payer.core.riconciliazionemt.bean.TransazionePageList;
import com.seda.payer.core.riconciliazionemt.bean.TransazioniAbbinateList;

public interface GiornaleDiCassaDAO {
	//public List<GiornaleDiCassa> doList(GiornaleDiCassa gdc) throws DaoException;
	public GiornaleDiCassaPageList ListGiornaliDiCassa(GiornaleDiCassa giornaleDiCassa, int rowsPerPage, int pageNumber,String OrderBy) throws  DaoException;
	public PspList ListPsp(GiornaleDiCassa giornaleDiCassa) throws  DaoException;
	public ContoList ListConto(long IDGiornaleDiCassa) throws  DaoException;
	public MovimentoDiCassaPageList ListMovimentiDiCassa(GiornaleDiCassa gdc, MovimentoDiCassa movimentoDiCassa, int rowsPerPage, int pageNumber,String OrderBy) throws  DaoException;
	public MovimentoDiCassa dettaglioMovimentoDiCassa(long idMdc) throws DaoException;
	public FlussiAbbinatiList FlussiAbbinati(long idMdc) throws DaoException;
	public TransazioniAbbinateList TransazioniAbbinate(long idMdc) throws DaoException;
	public NumeroDocumentoList ListNumeroDocumento(GiornaleDiCassa giornaleDiCassa) throws  DaoException;
	public FlussoPageList ListFlusso(Flusso flusso, int rowsPerPage, int pageNumber,String OrderBy) throws  DaoException;
	public TransazionePageList ListTransazione(Transazione transazione, int rowsPerPage, int pageNumber,String OrderBy) throws  DaoException;
	public void AggiungiFlusso(long idMdc, long idFlusso) throws  DaoException;
	public void EliminaFlusso(long idMdc, long idFlusso) throws  DaoException;
	public void AggiungiTransazione(long idMdc, long idTransazione) throws  DaoException;
	public void EliminaTransazione(long idMdc, long idTransazione) throws  DaoException;
	public MittenteList ListMittente(long idMdc) throws  DaoException;
	public void RegolarizzaSospeso(long idMdc, String user, String nota) throws DaoException;
	public Connection getGDCConnection();
}