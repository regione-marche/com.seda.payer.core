package com.seda.payer.core.wallet.bean;

import java.io.Serializable;
import java.util.Calendar;


import com.seda.data.dao.ModelAttributes;

public class Scuola extends ModelAttributes implements Serializable{

	/**
	 * 
	 */
	// Tabella PYSCUTB
	private static final long serialVersionUID = 1L;	
	private String codiceSocieta;        	//	1	SCU_CSOCCSOC VARCHAR(5)
	private String cuteCute;				//	2	SCU_CUTECUTE VARCHAR(5),
	private String chiaveEnte;				//	3	SCU_KANEKENT CHAR(10),
	private String codiceScuola;			//	4	SCU_CSCUSCOD VARCHAR(6),
	private String tipoScuola;				//	5	SCU_TSCUTIPO VARCHAR(1),
	private String descrizioneScuola;		//	6	SCU_DSCUDENO VARCHAR(50),
	private String indirizzoScuola;			//	7	SCU_DSCUINDI VARCHAR(50),
	private String comuneScuola;			//	8	SCU_DSCUCOMU VARCHAR(50),   
	private String capScuola;				//	9	SCU_CSCUCCAP VARCHAR(5),    
	private String compenzaScuola;			//	10	SCU_CSCUCOMP VARCHAR(1),
	private String descrizioneOrdineScuola; // 	11  SCU_DSCUDORD VARCHAR(30)
	private Calendar dataCaricamento;			//      SCU_GSCUGCAR  TimeStamp
	private String tipoVariazioneScuola;	//  NON lo salvo


//	11	I_SCU_FSCUFATT CHAR(1),
			

	public Scuola(){
		
	}
	
	public Scuola(
			Calendar dataCaricamento,
			String codiceSocieta,
			String cuteCute,
			String chiaveEnte,
			String codiceScuola,
			String tipoScuola,
			String descrizioneScuola,
			String indirizzoScuola,
			String capScuola,
			String compenzaScuola,
			String descrizioneOrdineScuola,
			String tipoVariazioneScuola
			){			
			this.codiceSocieta=codiceSocieta;
			this.cuteCute=cuteCute;
			this.chiaveEnte=chiaveEnte;
			this.codiceScuola=codiceScuola;
			this.tipoScuola=tipoScuola;
			this.descrizioneScuola=descrizioneScuola;
			this.indirizzoScuola=indirizzoScuola;
			this.capScuola=capScuola;
			this.compenzaScuola=compenzaScuola;
			this.descrizioneOrdineScuola=descrizioneOrdineScuola;
			this.tipoVariazioneScuola=tipoVariazioneScuola;
			this.dataCaricamento=dataCaricamento;
	}

	public Calendar getDataCAricamento() {
		return dataCaricamento;
	}

	public void setDataCAricamento(Calendar dataCAricamento) {
		this.dataCaricamento = dataCAricamento;
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

	public String getCodiceScuola() {
		return codiceScuola;
	}

	public void setCodiceScuola(String codiceScuola) {
		this.codiceScuola = codiceScuola;
	}

	public String getComuneScuola() {
		return comuneScuola;
	}

	public void setComuneScuola(String comuneScuola) {
		this.comuneScuola = comuneScuola;
	}

	public String getDescrizioneOrdineScuola() {
		return descrizioneOrdineScuola;
	}

	public void setDescrizioneOrdineScuola(String descrizioneOrdineScuola) {
		this.descrizioneOrdineScuola = descrizioneOrdineScuola;
	}

	public String getTipoScuola() {
		return tipoScuola;
	}

	public void setTipoScuola(String tipoScuola) {
		this.tipoScuola = tipoScuola;
	}

	
	public String getIndirizzoScuola() {
		return indirizzoScuola;
	}

	public void setIndirizzoScuola(String indirizzoScuola) {
		this.indirizzoScuola = indirizzoScuola;
	}

	public String getCapScuola() {
		return capScuola;
	}

	public void setCapScuola(String capScuola) {
		this.capScuola = capScuola;
	}

	public String getDescrizioneScuola() {
		return descrizioneScuola;
	}

	public void setDescrizioneScuola(String descrizioneScuola) {
		this.descrizioneScuola = descrizioneScuola;
	}

	public String getCompenzaScuola() {
		return compenzaScuola;
	}

	public void setCompenzaScuola(String compenzaScuola) {
		this.compenzaScuola = compenzaScuola;
	}

	public String getTipoVariazioneScuola() {
		return tipoVariazioneScuola;
	}

	public void setTipoVariazioneScuola(String tipoVariazioneScuola) {
		this.tipoVariazioneScuola = tipoVariazioneScuola;
	}

	public Calendar getDataCaricamento() {
		return dataCaricamento;
	}

	public void setDataCaricamento(Calendar dataCaricamento) {
		this.dataCaricamento = dataCaricamento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	
}
