package com.seda.payer.core.wallet.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.seda.data.dao.ModelAttributes;

public class AddebitiBorsellino extends ModelAttributes implements Serializable{

	private static final long serialVersionUID = 1L;
    
	//Tabella PYADBTB
	private String codSoc;					    //	"ADB_CSOCCSOC" VARCHAR(5)
	private String cutecute;				    //	"ADB_CUTECUTE" VARCHAR(5)
	private String chiaveEnte;		            //	"ADB_KANEKENT"   CHAR(10)
	private String idPagamenti;			        //	"ADB_KPGMKPGB" VARCHAR(64)
	private String idWallet;	                //	"ADB_KBRSKBRS" VARCHAR(18) 
	private String dettPagato;                  //  "ADB_CADBTPAG" VARCHAR(3) 
	private String idTabella;                   //  "ADB_KADBKPRS" VARCHAR(64)                  
	private BigDecimal importoPagamento ;       //  "ADB_IADBIPAG" DECIMAL(15 , 2)                     
    private Calendar data;                      //  "ADB_GADBGAGG" TIMESTAMP 
    private String   progressivoAddebito;       //  "ADB_NADBPROG" CHAR(5) 
    
     
    
	public AddebitiBorsellino() {
	}
	
	public AddebitiBorsellino(String codSoc, String cutecute,
			String chiaveEnte, String idPagamenti, String idWallet,
			String dettPagato, String idTabella, BigDecimal importoPagamento,
			Calendar data, String progressivoAddebito) {
		this.codSoc = codSoc;
		this.cutecute = cutecute;
		this.chiaveEnte = chiaveEnte;
		this.idPagamenti = idPagamenti;
		this.idWallet = idWallet;
		this.dettPagato = dettPagato;
		this.idTabella = idTabella;
		this.importoPagamento = importoPagamento;
		this.data = data;
		this.progressivoAddebito = progressivoAddebito;
	}
	
	public String getCodSoc() {
		return codSoc;
	}
	
	public void setCodSoc(String codSoc) {
		this.codSoc = codSoc;
	}
	public String getCutecute() {
		return cutecute;
	}
	public void setCutecute(String cutecute) {
		this.cutecute = cutecute;
	}
	public String getChiaveEnte() {
		return chiaveEnte;
	}
	public void setChiaveEnte(String chiaveEnte) {
		this.chiaveEnte = chiaveEnte;
	}
	public String getIdPagamenti() {
		return idPagamenti;
	}
	public void setIdPagamenti(String idPagamenti) {
		this.idPagamenti = idPagamenti;
	}
	public String getIdWallet() {
		return idWallet;
	}
	public void setIdWallet(String idWallet) {
		this.idWallet = idWallet;
	}
	public String getDettPagato() {
		return dettPagato;
	}
	public void setDettPagato(String dettPagato) {
		this.dettPagato = dettPagato;
	}
	public String getIdTabella() {
		return idTabella;
	}
	public void setIdTabella(String idTabella) {
		this.idTabella = idTabella;
	}
	public BigDecimal getImportoPagamento() {
		return importoPagamento;
	}
	public void setImportoPagamento(BigDecimal importoPagamento) {
		this.importoPagamento = importoPagamento;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public String getProgressivoAddebito() {
		return progressivoAddebito;
	}
	public void setProgressivoAddebito(String progressivoAddebito) {
		this.progressivoAddebito = progressivoAddebito;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AddebitiBorsellino [chiaveEnte=" + chiaveEnte + ", codSoc="
				+ codSoc + ", cutecute=" + cutecute + ", data=" + data
				+ ", dettPagato=" + dettPagato + ", idPagamenti=" + idPagamenti
				+ ", idTabella=" + idTabella + ", idWallet=" + idWallet
				+ ", importoPagamento=" + importoPagamento
				+ ", progressivoAddebito=" + progressivoAddebito + "]";
	}
	
                                                  
                                                     
}