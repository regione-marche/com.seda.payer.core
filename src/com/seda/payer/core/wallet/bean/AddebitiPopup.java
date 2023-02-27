package com.seda.payer.core.wallet.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.seda.data.dao.ModelAttributes;

public class AddebitiPopup extends ModelAttributes implements Serializable{

	private static final long serialVersionUID = 1L;
    

	private String denom;	                 
	private String consumo;                   
	private String desc;                                     
	private String importo;                            

     
    
	public AddebitiPopup() {
	}
	

	
	public AddebitiPopup(String denom, String consumo, String desc,
			String importo) {
		super();
		this.denom = denom;
		this.consumo = consumo;
		this.desc = desc;
		this.importo = importo;
	}



	public String getDenom() {
		return denom;
	}



	public void setDenom(String denom) {
		this.denom = denom;
	}



	public String getConsumo() {
		return consumo;
	}



	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}



	public String getDesc() {
		return desc;
	}



	public void setDesc(String desc) {
		this.desc = desc;
	}



	public String getImporto() {
		return importo;
	}



	public void setImporto(String importo) {
		this.importo = importo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AddebitiPopup [consumo=" + consumo + ", denom=" + denom
				+ ", desc=" + desc + ", importo=" + importo + "]";
	}
	
                                                  
                                                     
}