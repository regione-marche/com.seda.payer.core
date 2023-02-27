package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.seda.payer.core.exception.DaoException;

public class PgBollIV implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal bdNumeroRav; 
	
	private BigDecimal bdImportoRav;
	private String numeroDoc;
	private String numeroRata;
	private String dataScadenza;
	private String denominazione;
	private String codiceFiscale;
	private String indirizzo;
	private String citta;
	private String ente;
	private String imse;
	private String flOneri;
	private String dtInteressi;
	private String flMora;
	private BigDecimal bdImMora;
	private String flSpese;
	private BigDecimal bdImSpese;
	private String dtCalc;
	private String contoCorrente;
	private String changeIMP;
	private String flagAC;
	private String flagSanzione;
	private String idDocBarcode;
	private String flagMultiDocumento;
	private String flagMultiDocumentoContenzioso;
	private String message;
	private String motivoPagamento;
	private String codiceFreccia;
	// QF ANNO DOCUMENTO: 	
	private String annoDocumento;
	
	//PG180200_000 SB - inizio
	private String ibanPostale;
	private String ibanTesoreria;
	//PG180200_000 SB - fine

	//inizio LP PG200360
	private String tassonomia;
	//fine LP PG200360

	//inizio LP EP22405X
	private String causalePreavvisiBRAV;
	//fine LP EP22405X
	
	public String getAnnoDocumento() {
		return annoDocumento;
	}

	public void setAnnoDocumento(String annoDocumento) {
		this.annoDocumento = annoDocumento;
	}

	private List<PgBollIVDocumento> listDocumenti;
	
	//inizio LP PG210130
	private boolean flagMultiBeneficiario;
	private List<DettaglioPagamento> dettaglioPagamento;
	private List<DettaglioContabile> dettaglioContabile;
	//fine LP PG210130
	
	//PAGONET - 371 - inizio
	//public PgBollIV(BigDecimal numeroRav, CallableStatement cs) throws SQLException, DaoException {
	public PgBollIV(BigDecimal numeroRav, CallableStatement cs, boolean flagPrec) throws SQLException, DaoException {
		
		String appo = "";	//PG200400
		
		this.bdNumeroRav = numeroRav;
		//inizio LP PG210130
		flagMultiBeneficiario = false;
		//fine LP PG210130
		if (cs == null)
			return;
		
		ResultSet data = null;
		try{
			this.setBdImportoRav(cs.getBigDecimal(7));
			this.setNumeroDoc(cs.getString(8));
			this.setNumeroRata(cs.getString(9));
			this.setDataScadenza(cs.getString(10));
			this.setDenominazione(cs.getString(11));
			this.setCodiceFiscale(cs.getString(12));
			//PG200400
			appo = cs.getString(13).trim();
			if(appo != null && appo.length()>0) {
				this.setIndirizzo(appo);
			} else {
				this.setIndirizzo("");
			}
			//FINE PG200400
			this.setCitta(cs.getString(14));
			this.setEnte(cs.getString(15));
			this.setImse(cs.getString(16));
			this.setFlOneri(cs.getString(17));
			this.setDtInteressi(cs.getString(18));
			this.setFlMora(cs.getString(19));
			this.setBdImMora(cs.getBigDecimal(20));
			this.setFlSpese(cs.getString(21));
			this.setBdImSpese(cs.getBigDecimal(22));
			this.setDtCalc(cs.getString(23));
			this.setContoCorrente(cs.getString(24));
			this.setChangeIMP(cs.getString(25));
			this.setFlagAC(cs.getString(26));
			this.setFlagSanzione(cs.getString(27));
			this.setIdDocBarcode(cs.getString(28));
			this.setFlagMultiDocumento(cs.getString(29));
			this.setFlagMultiDocumentoContenzioso(cs.getString(30));
			this.setCodiceFreccia(cs.getString(31));
			this.setMotivoPagamento(cs.getString(32));
			
		    //QF CAUSALE: setMessage 
			//QF ANNODOCUMENTO:this.setAnnoDocumento(cs.getString(30));
			this.setAnnoDocumento(cs.getString(33));
			//PG180200_000 SB - inizio
			this.setIbanPostale(cs.getString(34));
			this.setIbanTesoreria(cs.getString(35));
			//inizio LP PG200360
			appo = cs.getString(36);
			if(appo != null) {
				this.tassonomia = appo.trim();
			} else {
				this.tassonomia = "";
			}
			//fine LP PG200360
			//PG180200_000 SB - fine
			//inizio LP PG200360
			//this.setMessage(cs.getString(36));
			//PAGONET-371 - inizio
			if (flagPrec) {
				this.causalePreavvisiBRAV = null;	//TODO da verificare se null o ""
				this.setMessage(cs.getString(37));
			} else {
				//inizio LP EP22405X
				//this.setMessage(cs.getString(37));
				appo = cs.getString(37);
				if(appo != null)
					this.causalePreavvisiBRAV = appo.trim();
				else
					this.causalePreavvisiBRAV = null;
				this.setMessage(cs.getString(38));
				//fine LP EP22405X
			}
			//PAGONET-371 - fine
			//fine LP PG200360			
			data = cs.getResultSet();
		    if (data != null)
		    {
		    	while (data.next())
		    	{
		    		if (listDocumenti == null)
		    			listDocumenti = new ArrayList<PgBollIVDocumento>();
		    		
		    		listDocumenti.add(new PgBollIVDocumento(data));
		    	}
		    }
			//inizio LP PG210130
		    try {
				//resultset 2: datiPagamento
				if (cs.getMoreResults()) {
					data.close();
					data = cs.getResultSet();
					int iPag = 0;
			    	while (data.next()) {
			    		if (dettaglioPagamento == null)
			    			dettaglioPagamento = new ArrayList<DettaglioPagamento>();
			    		dettaglioPagamento.add(new DettaglioPagamento(data));
			    		if(++iPag > 5) {
			    			throw new Exception("Errore: sono presenti piu' di 5 dettaglio pagamento");
			    		}
			    	}
				}
		    } catch (SQLException e) {
				e.printStackTrace();
		    	dettaglioPagamento = null;
			}
		    flagMultiBeneficiario = (dettaglioPagamento != null && !dettaglioPagamento.isEmpty());
			try {
				//resultset 3: datiContabili
				if (cs.getMoreResults()) {
					data.close();
					data = cs.getResultSet();
			    	while (data.next()) {
			    		if (dettaglioContabile == null)
			    			dettaglioContabile = new ArrayList<DettaglioContabile>();
			    		dettaglioContabile.add(new DettaglioContabile(data));
			    	}
				}
		    } catch (SQLException e) {
				e.printStackTrace();
		    	dettaglioContabile = null;
			}
			//fine LP PG210130
		}
		catch(Exception e){
			e.printStackTrace();
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//if (data != null)
			//	DAOHelper.closeIgnoringException(data);
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return;
	}

	public PgBollIV() {
	}

	public BigDecimal getBdNumeroRav() {
		return bdNumeroRav;
	}

	public void setBdImportoRav(BigDecimal bdImportoRav) {
		this.bdImportoRav = bdImportoRav;
	}

	public BigDecimal getBdImportoRav() {
		return bdImportoRav;
	}

	public void setNumeroDoc(String numeroDoc) {
		this.numeroDoc = numeroDoc;
	}

	public String getNumeroDoc() {
		return numeroDoc;
	}

	public void setNumeroRata(String numeroRata) {
		this.numeroRata = numeroRata;
	}

	public String getNumeroRata() {
		return numeroRata;
	}

	public void setDataScadenza(String dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public String getDataScadenza() {
		return dataScadenza;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getCitta() {
		return citta;
	}

	public void setEnte(String ente) {
		this.ente = ente;
	}

	public String getEnte() {
		return ente;
	}

	public void setImse(String imse) {
		this.imse = imse;
	}

	public String getImse() {
		return imse;
	}

	public void setFlOneri(String flOneri) {
		this.flOneri = flOneri;
	}

	public String getFlOneri() {
		return flOneri;
	}

	public void setDtInteressi(String dtInteressi) {
		this.dtInteressi = dtInteressi;
	}

	public String getDtInteressi() {
		return dtInteressi;
	}

	public void setFlMora(String flMora) {
		this.flMora = flMora;
	}

	public String getFlMora() {
		return flMora;
	}

	public void setBdImMora(BigDecimal bdImMora) {
		this.bdImMora = bdImMora;
	}

	public BigDecimal getBdImMora() {
		return bdImMora;
	}

	public void setFlSpese(String flSpese) {
		this.flSpese = flSpese;
	}

	public String getFlSpese() {
		return flSpese;
	}

	public void setBdImSpese(BigDecimal bdImSpese) {
		this.bdImSpese = bdImSpese;
	}

	public BigDecimal getBdImSpese() {
		return bdImSpese;
	}

	public void setDtCalc(String dtCalc) {
		this.dtCalc = dtCalc;
	}

	public String getDtCalc() {
		return dtCalc;
	}

	public void setContoCorrente(String contoCorrente) {
		this.contoCorrente = contoCorrente;
	}

	public String getContoCorrente() {
		return contoCorrente;
	}

	public void setChangeIMP(String changeIMP) {
		this.changeIMP = changeIMP;
	}

	public String getChangeIMP() {
		return changeIMP;
	}

	public void setFlagAC(String flagAC) {
		this.flagAC = flagAC;
	}

	public String getFlagAC() {
		return flagAC;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setFlagSanzione(String flagSanzione) {
		this.flagSanzione = flagSanzione;
	}

	public String getFlagSanzione() {
		return flagSanzione;
	}

	public void setIdDocBarcode(String idDocBarcode) {
		this.idDocBarcode = idDocBarcode;
	}

	public String getIdDocBarcode() {
		return idDocBarcode;
	}

	public void setFlagMultiDocumento(String flagMultiDocumento) {
		this.flagMultiDocumento = flagMultiDocumento;
	}

	public String getFlagMultiDocumento() {
		return flagMultiDocumento;
	}

	public void setFlagMultiDocumentoContenzioso(
			String flagMultiDocumentoContenzioso) {
		this.flagMultiDocumentoContenzioso = flagMultiDocumentoContenzioso;
	}

	public String getFlagMultiDocumentoContenzioso() {
		return flagMultiDocumentoContenzioso;
	}

	public void setMotivoPagamento(String motivoPagamento) {
		this.motivoPagamento = motivoPagamento;
	}

	public String getMotivoPagamento() {
		return motivoPagamento;
	}

	public void setCodiceFreccia(String codiceFreccia) {
		this.codiceFreccia = codiceFreccia;
	}

	public String getCodiceFreccia() {
		return codiceFreccia;
	}

	public void setListDocumenti(List<PgBollIVDocumento> listDocumenti) {
		this.listDocumenti = listDocumenti;
	}

	public List<PgBollIVDocumento> getListDocumenti() {
		return listDocumenti;
	}

	public String getIbanPostale() {
		return ibanPostale;
	}

	public String getIbanTesoreria() {
		return ibanTesoreria;
	}

	public void setIbanPostale(String ibanPostale) {
		this.ibanPostale = ibanPostale;
	}

	public void setIbanTesoreria(String ibanTesoreria) {
		this.ibanTesoreria = ibanTesoreria;
	}

	//inizio LP PG200360
	public String getTassonomia() {
		return tassonomia;
	}

	public void setTassonomia(String tassonomia) {
		this.tassonomia = tassonomia;
	}
	//fine LP PG200360

	//inizio LP PG210130
	public boolean isFlagMultiBeneficiario() {
		return flagMultiBeneficiario;
	}

	public void setFlagMultiBeneficiario(boolean flagMultiBeneficiario) {
		this.flagMultiBeneficiario = flagMultiBeneficiario;
	}

	public List<DettaglioPagamento> getDettaglioPagamento() {
		return dettaglioPagamento;
	}

	public void setDettaglioPagamento(List<DettaglioPagamento> dettaglioPagamento) {
		this.dettaglioPagamento = dettaglioPagamento;
	}

	public List<DettaglioContabile> getDettaglioContabile() {
		return dettaglioContabile;
	}

	public void setDettaglioContabile(List<DettaglioContabile> dettaglioContabile) {
		this.dettaglioContabile = dettaglioContabile;
	}
	//fine LP PG210130

	//inizio LP EP22405X
	public String getCausalePreavvisiBRAV() {
		return causalePreavvisiBRAV;
	}

	public void setCausalePreavvisiBRAV(String causalePreavvisiBRAV) {
		this.causalePreavvisiBRAV = causalePreavvisiBRAV;
	}
	//fine LP EP22405X
}
