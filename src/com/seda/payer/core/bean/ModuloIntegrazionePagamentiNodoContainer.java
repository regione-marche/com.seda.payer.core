package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.SQLException;

public class ModuloIntegrazionePagamentiNodoContainer implements Serializable {

	private static final long serialVersionUID = 1L;
    private ModuloIntegrazionePagamentiNodoPaymentStatus mipStatus;
    private boolean pidCheck;
    private boolean faseRedirect;
    
    
    public ModuloIntegrazionePagamentiNodoContainer() { 
    }

    public ModuloIntegrazionePagamentiNodoContainer(ModuloIntegrazionePagamentiNodoPaymentStatus mipStatus, boolean pidCheck, boolean faseRedirect) throws SQLException {
    	this.setMipStatus(mipStatus);
    	this.setPidCheck(pidCheck);
    	this.setFaseRedirect(faseRedirect);
    }

	public void setMipStatus(ModuloIntegrazionePagamentiNodoPaymentStatus mipStatus2) {
		this.mipStatus = mipStatus2;
	}

	public ModuloIntegrazionePagamentiNodoPaymentStatus getMipStatus() {
		return mipStatus;
	}

	public void setPidCheck(boolean pidCheck) {
		this.pidCheck = pidCheck;
	}

	public boolean isPidCheck() {
		return pidCheck;
	}

	public void setFaseRedirect(boolean faseRedirect) {
		this.faseRedirect = faseRedirect;
	}

	public boolean isFaseRedirect() {
		return faseRedirect;
	}

	
}