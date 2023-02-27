package com.seda.payer.core.bean;

import java.io.Serializable;

import com.seda.payer.commons.transform.TransformersIf;

public class ObjectKeyKey implements Serializable, TransformersIf{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String kren;
	String Ktra;
		
	public ObjectKeyKey(String ktra, String kren) {
		this.Ktra = ktra;
		this.kren = kren;
	}
	
	public String getKren() {
		return kren;
	}
	
	public void setKren(String kren) {
		this.kren = kren;
	}
	
	public String getKtra() {
		return Ktra;
	}
	
	public void setKtra(String ktra) {
		Ktra = ktra;
	}

	public Serializable beanToBean(Object bean) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

}
