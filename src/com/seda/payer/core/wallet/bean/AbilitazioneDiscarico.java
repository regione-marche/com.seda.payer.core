package com.seda.payer.core.wallet.bean;

import java.io.Serializable;

import com.seda.data.dao.ModelAttributes;

public class AbilitazioneDiscarico extends ModelAttributes implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codSoc;					    //	"ABD_CSOCCSOC" VARCHAR(5)
	private String cuteCute;				    //	"ABD_CUTECUTE" VARCHAR(5)
	private String chiaveEnte;		            //	"ABD_KANEKENT"   CHAR(10)
	private String codiceTributo;		        //	"ABD_CTRBCODI"   CHAR(10)
	private String descrizioneTributo;		    //	"ABD_DTRBDESC"   CHAR(10)
	private String flagAbilitazioneDiscarico;   //	"ABD_FABDDISC   CHAR(10)
	private String operatoreInserimento;        //  "ABD_CABDOPER VARCHAR(50)" 
	
	public String getCodSoc() {
		return codSoc;
	}
	public void setCodSoc(String codSoc) {
		this.codSoc = codSoc;
	}
	public String getCuteCute() {
		return cuteCute;
	}
	public void setCuteCute(String cutecute) {
		this.cuteCute = cutecute;
	}
	public String getChiaveEnte() {
		return chiaveEnte;
	}
	public void setChiaveEnte(String chiaveEnte) {
		this.chiaveEnte = chiaveEnte;
	}
	public String getCodiceTributo() {
		return codiceTributo;
	}
	public void setCodiceTributo(String codiceTributo) {
		this.codiceTributo = codiceTributo;
	}
	public String getDescrizioneTributo() {
		return descrizioneTributo;
	}
	public void setDescrizioneTributo(String descrizioneTributo) {
		this.descrizioneTributo = descrizioneTributo;
	}
	public String getFlagAbilitazioneDiscarico() {
		return flagAbilitazioneDiscarico;
	}
	public void setFlagAbilitazioneDiscarico(String flagAbilitazioneDiscarico) {
		this.flagAbilitazioneDiscarico = flagAbilitazioneDiscarico;
	}
	public String getOperatoreInserimento() {
		return operatoreInserimento;
	}
	public void setOperatoreInserimento(String operatoreInserimento) {
		this.operatoreInserimento = operatoreInserimento;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AbilitazioneDiscarico [chiaveEnte=");
		builder.append(chiaveEnte);
		builder.append(", codSoc=");
		builder.append(codSoc);
		builder.append(", codiceTributo=");
		builder.append(codiceTributo);
		builder.append(", cuteCute=");
		builder.append(cuteCute);
		builder.append(", descrizioneTributo=");
		builder.append(descrizioneTributo);
		builder.append(", flagAbilitazioneDiscarico=");
		builder.append(flagAbilitazioneDiscarico);
		builder.append(", operatoreInserimento=");
		builder.append(operatoreInserimento);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	

}
