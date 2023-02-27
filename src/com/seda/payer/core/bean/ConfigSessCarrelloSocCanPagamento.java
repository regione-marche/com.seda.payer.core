package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seda.payer.commons.bean.Lifecycle;

public class ConfigSessCarrelloSocCanPagamento extends Lifecycle implements Serializable{
	
	private static final long serialVersionUID = 1L;
    private int numMaxSessioni;
    private java.lang.String flagCarrello;
    private Company company;
    private CanalePagamento canalePagamento;
    
    
    
	public ConfigSessCarrelloSocCanPagamento() {
		company = new Company();
		canalePagamento = new CanalePagamento();
	}
	
	 public ConfigSessCarrelloSocCanPagamento(ResultSet data) throws SQLException {
	    	if (data == null)
	    		return;

	    	numMaxSessioni = data.getInt("CAS_ICASNUMS");
	    	flagCarrello = data.getString("CAS_FCASSFUL");
	    	company = new Company();{
	    		company.setCompanyCode(data.getString("CAS_CSOCCSOC"));
	    	}
			canalePagamento = new CanalePagamento();{
				canalePagamento.setChiaveCanalePagamento(data.getString("CAS_KCANKCAN"));
			}
	 }

	public int getNumMaxSessioni() {
		return numMaxSessioni;
	}

	public void setNumMaxSessioni(int numMaxSessioni) {
		this.numMaxSessioni = numMaxSessioni;
	}

	public java.lang.String getFlagCarrello() {
		return flagCarrello;
	}

	public void setFlagCarrello(java.lang.String flagCarrello) {
		this.flagCarrello = flagCarrello;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public CanalePagamento getCanalePagamento() {
		return canalePagamento;
	}

	public void setCanalePagamento(CanalePagamento canalePagamento) {
		this.canalePagamento = canalePagamento;
	}
	
	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.getCompany().getCompanyCode());
		arg.setString(2, this.getCanalePagamento().getChiaveCanalePagamento());
		arg.setInt(3, this.getNumMaxSessioni());
		arg.setString(4, this.getFlagCarrello());
	}
	
	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.getCompany().getCompanyCode());
		arg.setString(2, this.getCanalePagamento().getChiaveCanalePagamento());
		arg.setInt(3, this.getNumMaxSessioni());
		arg.setString(4, this.getFlagCarrello());
	}
	
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {

	}
    
   

}
