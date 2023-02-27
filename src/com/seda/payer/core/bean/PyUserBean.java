package com.seda.payer.core.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Questa classe viene usata dal metodo "validate" della classe
 * "SignOnSupport" in fase di login.
 * 
 * Contiene il bean dell'utente e la lista delle applicazioni
 * a cui è abilitato.
 * @author f.vadicamo
 *
 */
@SuppressWarnings("serial")
public class PyUserBean implements Serializable{
	private PyUser pyUser = null;
	private List<String> applicazioni = null;
	
	public PyUserBean() {
		super();
	}
	public PyUserBean(PyUser pyUser, List<String> applicazioni) {
		super();
		this.pyUser = pyUser;
		this.applicazioni = applicazioni;
	}
	public PyUser getPyUser() {
		return pyUser;
	}
	public void setPyUser(PyUser pyUser) {
		this.pyUser = pyUser;
	}
	public List<String> getApplicazioni() {
		return applicazioni;
	}
	public void setApplicazioni(List<String> applicazioni) {
		this.applicazioni = applicazioni;
	}
	

}
