package com.seda.payer.core.mercato.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.seda.data.dao.ModelAttributes;

public class ConfigurazioneCompenso extends ModelAttributes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Tabella PYCOMTB
	private String codiceKeyCompenso;				//  "COM_KCOMKCOM" VARCHAR(10)					
	private String codiceSocieta;        			//	"COM_CSOCCSOC" CHAR(5)
	private String cuteCute;						//	"COM_CUTECUTE" CHAR(5)
	private String chiaveEnte;						//	"COM_KANEKENT" CHAR(10)
	private BigDecimal importoFisso;				//  "COM_ICOMFISS" DECIMAL(15,2)
	private Double percentualeCompenso;  			//	"COM_DCOMPRCO" DECIMAL(6,3)
	private Double percentualeIva;					//	"COM_DCOMPRCI" DECIMAL(6,3))
	private Calendar dataInizioValidita;			//  "COM_GCOMDTIN" TIMESTAMP
	private Calendar dataFineValidita;				//  "COM_GCOMDTFN" TIMESTAMP
	private Calendar dataAInizioValidita;
	private Calendar dataAFineValidita;
	

	public ConfigurazioneCompenso(){
	}

	public ConfigurazioneCompenso(String codiceKeyCompenso,
			String codiceSocieta, String cuteCute, String chiaveEnte,
			BigDecimal importoFisso, Double percentualeCompenso,
			Double percentualeIva, Calendar dataInizioValidita,
			Calendar dataFineValidita, Calendar dataAInizioValidita,
			Calendar dataAFineValidita) {
		super();
		this.codiceKeyCompenso = codiceKeyCompenso;
		this.codiceSocieta = codiceSocieta;
		this.cuteCute = cuteCute;
		this.chiaveEnte = chiaveEnte;
		this.importoFisso = importoFisso;
		this.percentualeCompenso = percentualeCompenso;
		this.percentualeIva = percentualeIva;
		this.dataInizioValidita = dataInizioValidita;
		this.dataFineValidita = dataFineValidita;
		this.dataAInizioValidita = dataAInizioValidita;
		this.dataAFineValidita = dataAFineValidita;
	}

	@Override
	public String toString() {
		return "ConfigurazioneAreaMercantile [codiceKeyCompenso=" + codiceKeyCompenso
				+ ",chiaveEnte=" + chiaveEnte
				+ ", codiceSocieta="
				+ codiceSocieta + ", cuteCute=" + cuteCute 
				+ ", importoFisso=" + importoFisso  
				+ ", percentualeCompenso=" + percentualeCompenso
				+ ", percentualeIva=" + percentualeIva
				+ ", dataInizioValidita=" + dataInizioValidita
				+ ", dataFineValidita=" + dataFineValidita
				+ "]";
	}

	public String getCodiceKeyCompenso() {
		return codiceKeyCompenso;
	}
	
	public void setCodiceKeyCompenso(String codiceKeyCompenso) {
		this.codiceKeyCompenso = codiceKeyCompenso;
	}
	
	public String getCodiceSocieta() {
		return codiceSocieta;
	}

	public void setCodiceSocieta(String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}

	public String getCuteCute() {
		return cuteCute;
	}

	public void setCuteCute(String cuteCute) {
		this.cuteCute = cuteCute;
	}

	public String getChiaveEnte() {
		return chiaveEnte;
	}

	public void setChiaveEnte(String chiaveEnte) {
		this.chiaveEnte = chiaveEnte;
	}

	public BigDecimal getImportoFisso() {
		return importoFisso;
	}

	public void setImportoFisso(BigDecimal importoFisso) {
		this.importoFisso = importoFisso;
	}

	public Double getPercentualeCompenso() {
		return percentualeCompenso;
	}

	public void setPercentualeCompenso(Double percentualeCompenso) {
		this.percentualeCompenso = percentualeCompenso;
	}
	
	public Double getPercentualeIva() {
		return percentualeIva;
	}

	public void setPercentualeIva(Double percentualeIva) {
		this.percentualeIva = percentualeIva;
	}
	
	public Calendar getDataInizioValidita() {
		return dataInizioValidita;
	}
	
	public void setDataInizioValidita(Calendar dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public Calendar getDataFineValidita() {
		return dataFineValidita;
	}
	
	public void setDataFineValidita(Calendar dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	public Calendar getDataAInizioValidita() {
		return dataAInizioValidita;
	}

	public void setDataAInizioValidita(Calendar dataAInizioValidita) {
		this.dataAInizioValidita = dataAInizioValidita;
	}

	public Calendar getDataAFineValidita() {
		return dataAFineValidita;
	}

	public void setDataAFineValidita(Calendar dataAFineValidita) {
		this.dataAFineValidita = dataAFineValidita;
	}

}
