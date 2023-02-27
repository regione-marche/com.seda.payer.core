package com.seda.payer.core.wallet.bean;

import java.io.Serializable;

import com.seda.data.dao.ModelAttributes;

public class TributiForSORINET extends ModelAttributes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Tabella PYSNTTB
	private String idWallet;				//"SNT_KBRSKBRS"     VARCHAR(18)
	private String tipoPres;				//"SNT_CKEYTIPO"     CHAR(3)
	private String keyPres;					//"SNT_CKEYRIFE"     VARCHAR(64)
	private String dataInvioSORINET;		//"SNT_GDTINVIO"     TIMESTAMP		    LA ROUTINE RESTITUISCE STRINGA
	private int numRichiestaSORINET;		//"SNT_NNUMRICH"     INTEGER
	private String codUtente;				//"SNT_CUTECUTE"     CHAR(5)
	private String documento;				//"SNT_KDOCINTE"     CHAR(50)
	private String dataProvvedimento;		//"SNT_GDATPROV"     DATE               LA ROUTINE RESTITUISCE STRINGA
	private String tipoProvvedimento;		//"SNT_TIPOPROV"     CHAR(1)
	private String annoProvvedimento;		//"SNT_ANNOPROV"     CHAR(4)
	private String dataDilazione;			//"SNT_GDATDILA"     DATE               LA ROUTINE RESTITUISCE STRINGA       
	private String note;					//"SNT_DSNTNOTE"     VARCHAR(200)
	private String userForSORINET; 			//"SNT_CSNTUSER"     CHAR(10)
	private String numeroProtocollo;		//"SNT_NNUMPROT"     VARCHAR(9)
	private String dataProtocollo;			//"SNT_GDATPROT"     DATE               LA ROUTINE RESTITUISCE STRINGA 
	private String impCaricoDoc;			//"SNT_IMPCARDC_TOT" DECIMAL(15 , 2)    LA ROUTINE RESTITUISCE STRINGA
	private String impDisCaricoDoc;			//"SNT_IMPDISDC_TOT" DECIMAL(15 , 2)    LA ROUTINE RESTITUISCE STRINGA
	private String codTributo;				//"SNT_CCODTRIB"     CHAR(4)
	private String annoTributo;				//"SNT_CCODTRAN"     CHAR(4)
	private String suffTributo; 			//"SNT_CCODTRSF"     CHAR(3)
	private String impCaricoTrib;		 	//"SNT_IMPCARTR"     DECIMAL(15 , 2)    LA ROUTINE RESTITUISCE STRINGA
	private String impDisCaricoTrib;		//"SNT_IMPDISTR"     DECIMAL(15 , 2)    LA ROUTINE RESTITUISCE STRINGA 
	private String rcSORINET;				//"SNT_CSORRETC"     CHAR(2)
	private String messSORINET;				//"SNT_CSORMESS"     VARCHAR(500)
	private String codiceProvvedimento;		//"SNT_CPRODISC"     VARCHAR(28)
	private int numeroProvvedimento;		//"SNT_NPRODISC"     INTEGER             
	private String validita;				//"SNT_VALIDO"       CHAR(1)
	private String descrizionePresenza;		// contiene la descrizione della presenza o sollecito nel caso questa sia in attesa del documento HOST 
	private String impCaricoDoc_edit;		// contiene l'edit dell'importo
	private String impDisCaricoDoc_edit;	// contiene l'edit dell'importo
	private String impCaricoTrib_edit;		// contiene l'edit dell'importo
	private String impDisCaricoTrib_edit;	// contiene l'edit dell'importo
	private String dataPresenza;			//LA ROUTINE RESTITUISCE STRINGA

	public TributiForSORINET(){
		
	}

	public TributiForSORINET(String idWallet, String tipoPres, String keyPres,
			String dataInvioSORINET, int numRichiestaSORINET,
			String codUtente, String documento, String dataProvvedimento,
			String tipoProvvedimento, String annoProvvedimento,
			String dataDilazione, String note, String userForSORINET,
			String numeroProtocollo, String dataProtocollo,
			String impCaricoDoc, String impDisCaricoDoc,
			String codTributo, String annoTributo, String suffTributo,
			String impCaricoTrib, String impDisCaricoTrib,
			String rcSORINET, String messSORINET, String codiceProvvedimento,
			int numeroProvvedimento, String validita,String descrizionePresenza,
		    String impCaricoDoc_edit, String impDisCaricoDoc_edit,
		    String impCaricoTrib_edit, String impDisCaricoTrib_edit
		    , String dataPresenza) {

		super();
		this.idWallet = idWallet;
		this.tipoPres = tipoPres;
		this.keyPres = keyPres;
		this.dataInvioSORINET = dataInvioSORINET;
		this.numRichiestaSORINET = numRichiestaSORINET;
		this.codUtente = codUtente;
		this.documento = documento;
		this.dataProvvedimento = dataProvvedimento;
		this.tipoProvvedimento = tipoProvvedimento;
		this.annoProvvedimento = annoProvvedimento;
		this.dataDilazione = dataDilazione;
		this.note = note;
		this.userForSORINET = userForSORINET;
		this.numeroProtocollo = numeroProtocollo;
		this.dataProtocollo = dataProtocollo;
		this.impCaricoDoc = impCaricoDoc;
		this.impDisCaricoDoc = impDisCaricoDoc;
		this.codTributo = codTributo;
		this.annoTributo = annoTributo;
		this.suffTributo = suffTributo;
		this.impCaricoTrib = impCaricoTrib;
		this.impDisCaricoTrib = impDisCaricoTrib;
		this.rcSORINET = rcSORINET;
		this.messSORINET = messSORINET;
		this.codiceProvvedimento = codiceProvvedimento;
		this.numeroProvvedimento = numeroProvvedimento;
		this.validita = validita;
		this.descrizionePresenza = descrizionePresenza;
		this.impCaricoDoc_edit = impCaricoDoc_edit;
		this.impDisCaricoDoc_edit = impDisCaricoDoc_edit;
		this.impCaricoTrib_edit = impCaricoTrib_edit;
		this.impDisCaricoTrib_edit = impDisCaricoTrib_edit;
		this.dataPresenza = dataPresenza;
	}

	public String getIdWallet() {
		return idWallet;
	}

	public void setIdWallet(String idWallet) {
		this.idWallet = idWallet;
	}

	public String getTipoPres() {
		return tipoPres;
	}

	public void setTipoPres(String tipoPres) {
		this.tipoPres = tipoPres;
	}

	public String getKeyPres() {
		return keyPres;
	}

	public void setKeyPres(String keyPres) {
		this.keyPres = keyPres;
	}

	public String getDataInvioSORINET() {
		return dataInvioSORINET;
	}

	public void setDataInvioSORINET(String dataInvioSORINET) {
		this.dataInvioSORINET = dataInvioSORINET;
	}

	public int getNumRichiestaSORINET() {
		return numRichiestaSORINET;
	}

	public void setNumRichiestaSORINET(int numRichiestaSORINET) {
		this.numRichiestaSORINET = numRichiestaSORINET;
	}

	public String getCodUtente() {
		return codUtente;
	}

	public void setCodUtente(String codUtente) {
		this.codUtente = codUtente;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getDataProvvedimento() {
		return dataProvvedimento;
	}

	public void setDataProvvedimento(String dataProvvedimento) {
		this.dataProvvedimento = dataProvvedimento;
	}

	public String getTipoProvvedimento() {
		return tipoProvvedimento;
	}

	public void setTipoProvvedimento(String tipoProvvedimento) {
		this.tipoProvvedimento = tipoProvvedimento;
	}

	public String getAnnoProvvedimento() {
		return annoProvvedimento;
	}

	public void setAnnoProvvedimento(String annoProvvedimento) {
		this.annoProvvedimento = annoProvvedimento;
	}

	public String getDataDilazione() {
		return dataDilazione;
	}

	public void setDataDilazione(String dataDilazione) {
		this.dataDilazione = dataDilazione;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getUserForSORINET() {
		return userForSORINET;
	}

	public void setUserForSORINET(String userForSORINET) {
		this.userForSORINET = userForSORINET;
	}

	public String getNumeroProtocollo() {
		return numeroProtocollo;
	}

	public void setNumeroProtocollo(String numeroProtocollo) {
		this.numeroProtocollo = numeroProtocollo;
	}

	public String getDataProtocollo() {
		return dataProtocollo;
	}

	public void setDataProtocollo(String dataProtocollo) {
		this.dataProtocollo = dataProtocollo;
	}

	public String getImpCaricoDoc() {
		return impCaricoDoc;
	}

	public void setImpCaricoDoc(String impCaricoDoc) {
		this.impCaricoDoc = impCaricoDoc;
	}

	public String getImpDisCaricoDoc() {
		return impDisCaricoDoc;
	}

	public void setImpDisCaricoDoc(String impDisCaricoDoc) {
		this.impDisCaricoDoc = impDisCaricoDoc;
	}

	public String getCodTributo() {
		return codTributo;
	}

	public void setCodTributo(String codTributo) {
		this.codTributo = codTributo;
	}

	public String getAnnoTributo() {
		return annoTributo;
	}

	public void setAnnoTributo(String annoTributo) {
		this.annoTributo = annoTributo;
	}

	public String getSuffTributo() {
		return suffTributo;
	}

	public void setSuffTributo(String suffTributo) {
		this.suffTributo = suffTributo;
	}

	public String getImpCaricoTrib() {
		return impCaricoTrib;
	}

	public void setImpCaricoTrib(String impCaricoTrib) {
		this.impCaricoTrib = impCaricoTrib;
	}

	public String getImpDisCaricoTrib() {
		return impDisCaricoTrib;
	}

	public void setImpDisCaricoTrib(String impDisCaricoTrib) {
		this.impDisCaricoTrib = impDisCaricoTrib;
	}

	public String getRcSORINET() {
		return rcSORINET;
	}

	public void setRcSORINET(String rcSORINET) {
		this.rcSORINET = rcSORINET;
	}

	public String getMessSORINET() {
		return messSORINET;
	}

	public void setMessSORINET(String messSORINET) {
		this.messSORINET = messSORINET;
	}

	public String getCodiceProvvedimento() {
		return codiceProvvedimento;
	}

	public void setCodiceProvvedimento(String codiceProvvedimento) {
		this.codiceProvvedimento = codiceProvvedimento;
	}

	public int getNumeroProvvedimento() {
		return numeroProvvedimento;
	}

	public void setNumeroProvvedimento(int numeroProvvedimento) {
		this.numeroProvvedimento = numeroProvvedimento;
	}

	public String getValidita() {
		return validita;
	}

	public void setValidita(String validita) {
		this.validita = validita;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDescrizionePresenza() {
		return descrizionePresenza;
	}

	public void setDescrizionePresenza(String descrizionePresenza) {
		this.descrizionePresenza = descrizionePresenza;
	}
	

	public String getImpCaricoDoc_edit() {
		return impCaricoDoc_edit;
	}

	public void setImpCaricoDoc_edit(String impCaricoDocEdit) {
		impCaricoDoc_edit = impCaricoDocEdit;
	}

	public String getImpDisCaricoDoc_edit() {
		return impDisCaricoDoc_edit;
	}

	public void setImpDisCaricoDoc_edit(String impDisCaricoDocEdit) {
		impDisCaricoDoc_edit = impDisCaricoDocEdit;
	}

	public String getImpCaricoTrib_edit() {
		return impCaricoTrib_edit;
	}

	public void setImpCaricoTrib_edit(String impCaricoTribEdit) {
		impCaricoTrib_edit = impCaricoTribEdit;
	}

	public String getImpDisCaricoTrib_edit() {
		return impDisCaricoTrib_edit;
	}

	public void setImpDisCaricoTrib_edit(String impDisCaricoTribEdit) {
		impDisCaricoTrib_edit = impDisCaricoTribEdit;
	}
	
	public String getDataPresenza() {
		return dataPresenza;
	}

	public void setDataPresenza(String dataPresenza) {
		this.dataPresenza = dataPresenza;
	}

	@Override
	public String toString() {
		return "TributiForSORINET [annoProvvedimento=" + annoProvvedimento
				+ ", annoTributo=" + annoTributo + ", codTributo=" + codTributo
				+ ", codUtente=" + codUtente + ", codiceProvvedimento="
				+ codiceProvvedimento + ", dataDilazione=" + dataDilazione
				+ ", dataInvioSORINET=" + dataInvioSORINET + ", dataPresenza="
				+ dataPresenza + ", dataProtocollo=" + dataProtocollo
				+ ", dataProvvedimento=" + dataProvvedimento
				+ ", descrizionePresenza=" + descrizionePresenza
				+ ", documento=" + documento + ", idWallet=" + idWallet
				+ ", impCaricoDoc=" + impCaricoDoc + ", impCaricoDoc_edit="
				+ impCaricoDoc_edit + ", impCaricoTrib=" + impCaricoTrib
				+ ", impCaricoTrib_edit=" + impCaricoTrib_edit
				+ ", impDisCaricoDoc=" + impDisCaricoDoc
				+ ", impDisCaricoDoc_edit=" + impDisCaricoDoc_edit
				+ ", impDisCaricoTrib=" + impDisCaricoTrib
				+ ", impDisCaricoTrib_edit=" + impDisCaricoTrib_edit
				+ ", keyPres=" + keyPres + ", messSORINET=" + messSORINET
				+ ", note=" + note + ", numRichiestaSORINET="
				+ numRichiestaSORINET + ", numeroProtocollo="
				+ numeroProtocollo + ", numeroProvvedimento="
				+ numeroProvvedimento + ", rcSORINET=" + rcSORINET
				+ ", suffTributo=" + suffTributo + ", tipoPres=" + tipoPres
				+ ", tipoProvvedimento=" + tipoProvvedimento
				+ ", userForSORINET=" + userForSORINET + ", validita="
				+ validita + "]";
	}
	
}
