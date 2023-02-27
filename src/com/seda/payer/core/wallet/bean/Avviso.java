package com.seda.payer.core.wallet.bean;

import java.io.Serializable;

import com.seda.data.dao.ModelAttributes;

public class Avviso extends ModelAttributes implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private String cuteCute;				    //	"MSG_CUTECUTE" VARCHAR(5)
	private String codSoc;					    //	"MSG_CSOCCSOC" VARCHAR(5)
	private String chiaveEnte;		            //	"MSG_KANEKENT"   CHAR(10)
	private String nomeCampo;		   		    //	"MSG_CMSGNOME" VARCHAR(50)
	private String descrizioneCampo;		    //	"MSG_DMSGDESC" VARCHAR(100)
	private String testo;  						//	"MSG_CMSGTEXT   CLOB(50000000)
	 
	
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
	public String getNomeCampo() {
		return nomeCampo;
	}
	public void setNomeCampo(String nomeCampo) {
		this.nomeCampo = nomeCampo;
	}
	public String getDescrizioneCampo() {
		return descrizioneCampo;
	}
	public void setDescrizioneCampo(String descrizioneCampo) {
		this.descrizioneCampo = descrizioneCampo;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}

	@Override
	public String toString() {
		return "Avviso [chiaveEnte=" + chiaveEnte + ", codSoc=" + codSoc
				+ ", cuteCute=" + cuteCute + ", descrizioneCampo="
				+ descrizioneCampo + ", nomeCampo=" + nomeCampo + ", testo="
				+ testo + "]";
	}
	
	
	
	

}
