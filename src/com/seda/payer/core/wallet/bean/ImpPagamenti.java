package com.seda.payer.core.wallet.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import com.seda.data.dao.ModelAttributes;

public class ImpPagamenti extends ModelAttributes implements Serializable{
	
	
	/**
	 *    imputazione dei pagamenti
	 */
	private static final long serialVersionUID = 1L;
	private String funzElab;
	private String elabSenzaConermaEpgf;
	private String cutecute;
	private int numBorsellini_elab;
	private int numPres_Forf_elab;
	private int numPres_Gior_elab;
	private BigDecimal imp_SI_host; 
	private BigDecimal imp_NO_host;
	private int numBorselliniFitt_elab;
	private int numPres_Forf_suFitt;
	private int numPres_Gior_suFitt;
	private String lastAction;
	private String codiceRitorno;
	private String messagRitorno;
	
 
	public ImpPagamenti(){
		
	}
	
	public ImpPagamenti(
			String funzElab,
			String cutecute,
			int numBorsellini_elab,
			int numPres_Forf_elab,
			int numPres_Gior_elab,
			BigDecimal imp_SI_host,
			BigDecimal imp_NO_host,
			int numBorselliniFitt_elab,
			int numPres_Forf_suFitt,
			int numPres_Gior_suFitt,
			String lastAction,
			String codiceRitorno,
			String messagRitorno
		){
		this.funzElab = funzElab;
		this.cutecute = cutecute;
		this.numBorsellini_elab = numBorsellini_elab;
		this.numPres_Forf_elab = numPres_Forf_elab;
		this.numPres_Gior_elab = numPres_Gior_elab; 
		this.imp_SI_host = imp_SI_host;
		this.imp_NO_host = imp_NO_host;
		this.numBorselliniFitt_elab = numBorselliniFitt_elab;
		this.numPres_Forf_suFitt = numPres_Forf_suFitt;
		this.numPres_Gior_suFitt = numPres_Gior_suFitt;
		this.lastAction = lastAction;
		this.codiceRitorno = codiceRitorno;
		this.messagRitorno = messagRitorno;
	}

	public String getFunzElab() {
		return funzElab;
	}

	public void setFunzElab(String funzElab) {
		this.funzElab = funzElab;
	}

	public String getCutecute() {
		return cutecute;
	}

	public void setCutecute(String cutecute) {
		this.cutecute = cutecute;
	}

	public int getNumBorsellini_elab() {
		return numBorsellini_elab;
	}

	public int getNumBorselliniFitt_elab() {
		return numBorselliniFitt_elab;
	}

	public void setNumBorselliniFitt_elab(int numBorselliniFittElab) {
		numBorselliniFitt_elab = numBorselliniFittElab;
	}

	public void setNumBorsellini_elab(int numBorselliniElab) {
		numBorsellini_elab = numBorselliniElab;
	}

	public int getNumPres_Forf_elab() {
		return numPres_Forf_elab;
	}

	public void setNumPres_Forf_elab(int numPresForfElab) {
		numPres_Forf_elab = numPresForfElab;
	}

	public int getNumPres_Gior_elab() {
		return numPres_Gior_elab;
	}

	public void setNumPres_Gior_elab(int numPresGiorElab) {
		numPres_Gior_elab = numPresGiorElab;
	}

	public BigDecimal getImp_SI_host() {
		return imp_SI_host;
	}

	public void setImp_SI_host(BigDecimal impSIHost) {
		imp_SI_host = impSIHost;
	}

	public BigDecimal getImp_NO_host() {
		return imp_NO_host;
	}

	public void setImp_NO_host(BigDecimal impNOHost) {
		imp_NO_host = impNOHost;
	}

	public int getNumPres_Forf_suFitt() {
		return numPres_Forf_suFitt;
	}

	public void setNumPres_Forf_suFitt(int numPresForfSuFitt) {
		numPres_Forf_suFitt = numPresForfSuFitt;
	}

	public int getNumPres_Gior_suFitt() {
		return numPres_Gior_suFitt;
	}

	public void setNumPres_Gior_suFitt(int numPresGiorSuFitt) {
		numPres_Gior_suFitt = numPresGiorSuFitt;
	}

	public String getLastAction() {
		return lastAction;
	}

	public void setLastAction(String lastAction) {
		this.lastAction = lastAction;
	}

	public String getCodiceRitorno() {
		return codiceRitorno;
	}

	public void setCodiceRitorno(String codiceRitorno) {
		this.codiceRitorno = codiceRitorno;
	}

	public String getMessagRitorno() {
		return messagRitorno;
	}

	public void setMessagRitorno(String messagRitorno) {
		this.messagRitorno = messagRitorno;
	}

	public String getElabSenzaConermaEpgf() {
		return elabSenzaConermaEpgf;
	}

	public void setElabSenzaConermaEpgf(String elabSenzaConermaEpgf) {
		this.elabSenzaConermaEpgf = elabSenzaConermaEpgf;
	}

	@Override
	public String toString() {
		return "ImpPagamenti [codiceRitorno=" + codiceRitorno + ", cutecute="
				+ cutecute + ", funzElab=" + funzElab + ", imp_NO_host="
				+ imp_NO_host + ", imp_SI_host=" + imp_SI_host
				+ ", lastAction=" + lastAction + ", messagRitorno="
				+ messagRitorno + ", numBorselliniFitt_elab="
				+ numBorselliniFitt_elab + ", numBorsellini_elab="
				+ numBorsellini_elab + ", numPres_Forf_elab="
				+ numPres_Forf_elab + ", numPres_Forf_suFitt="
				+ numPres_Forf_suFitt + ", numPres_Gior_elab="
				+ numPres_Gior_elab + ", numPres_Gior_suFitt="
				+ numPres_Gior_suFitt + "]";
	}


	
	
}
