package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConfigSessioneCarrello implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codiceSocieta;
    private String canalePagamento;
	private int maxSessions;
	private boolean stateful;
	public String getCodiceSocieta() {
		return codiceSocieta;
	}
	public void setCodiceSocieta(String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}
	public String getCanalePagamento() {
		return canalePagamento;
	}
	public void setCanalePagamento(String canalePagamento) {
		this.canalePagamento = canalePagamento;
	}
	public int getMaxSessions() {
		return maxSessions;
	}
	public void setMaxSessions(int maxSessions) {
		this.maxSessions = maxSessions;
	}
	public boolean isStateful() {
		return stateful;
	}
	public void setStateful(boolean stateful) {
		this.stateful = stateful;
	}
	public ConfigSessioneCarrello(ResultSet data) throws SQLException {
    	if (data == null) {
    		return;
    	}
    	codiceSocieta = data.getString("CAS_CSOCCSOC");
       	canalePagamento = data.getString("CAS_KCANKCAN");
        maxSessions = data.getInt("CAS_ICASNUMS");
        stateful = "Y".equalsIgnoreCase(data.getString("CAS_FCASSFUL"));
    }
    
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, getCodiceSocieta());
		arg.setString(2, getCanalePagamento());
		arg.setInt(3, getMaxSessions());
		arg.setString(4, isStateful()?"Y":"N");
	}
}
