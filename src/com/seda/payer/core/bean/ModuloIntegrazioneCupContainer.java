package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.SQLException;

public class ModuloIntegrazioneCupContainer implements Serializable {

	private static final long serialVersionUID = 1L;
    private ModuloIntegrazioneCupStatus micStatus;
    private boolean pidCheck;
    private boolean faseRedirect;
    
    
    public ModuloIntegrazioneCupContainer() { 
    }

    public ModuloIntegrazioneCupContainer(ModuloIntegrazioneCupStatus micStatus, boolean pidCheck, boolean faseRedirect) throws SQLException {
    	this.setMicStatus(micStatus);
    	this.setPidCheck(pidCheck);
    	this.setFaseRedirect(faseRedirect);
    }

	public void setMicStatus(ModuloIntegrazioneCupStatus micStatus) {
		this.micStatus = micStatus;
	}

	public ModuloIntegrazioneCupStatus getMicStatus() {
		return micStatus;
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