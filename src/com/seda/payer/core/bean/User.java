package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import com.seda.payer.commons.bean.Lifecycle;

public class User extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
    private java.lang.String userCode;
    private java.lang.String scopeCncCode;
    private java.lang.String userDescription;
    
    private java.lang.String ordinanteDescription;
    
    private java.lang.String codiceFiscale;           
    private java.lang.String codiceSia;               
    private java.lang.String codiceIban;              
    private java.lang.String utenteFtp;               
    private java.lang.String passwordFtp;             
    private java.lang.String serverFtp;               
    private java.lang.String dirRemotaServerFtp;      
    private java.lang.String flagAbilitazioneInvioFtp;

    /*
     * 
    private java.lang.String accountNumber;
    private java.lang.String accountHolder;
    private java.lang.String flagCheckRangeAbi;
    private java.lang.String emailFrom;
    private java.lang.String emailCcn;
    private java.lang.String emailTo;
    private java.lang.String descrMittente;
    private java.lang.String attachFlag;
    */
    
    private java.lang.String operatorCode;
    private Company company;

    public User() { 
    	company = new Company();
    }

    /**
     * Costruttore per la Routines.USER_DODETAIL
     * @param data
     * @throws SQLException
     */
    public User(CallableStatement cs) throws SQLException {
    	if (cs == null)
    		return;
    	
		//inizio LP PG21XX04 Leak
    	//ResultSet data = cs.getResultSet();
    	ResultSet data = null;
    	try {
    		data = cs.getResultSet();
		//fine LP PG21XX04 Leak
			if (data.next())
			{
		    	userCode = data.getString("UTE_CUTECUTE");
		        scopeCncCode = data.getString("UTE_CUTECCNC");
		        userDescription = data.getString("UTE_DUTEDUTE");
		        ordinanteDescription = data.getString("UTE_DUTEORDI");
		        codiceFiscale= data.getString("UTE_CUTECFIS");           
		        codiceSia= data.getString("UTE_CUTECSIA");               
		        codiceIban= data.getString("UTE_CUTEIBAN");              
		        utenteFtp= data.getString("UTE_DUTEUFTP");               
		        passwordFtp= data.getString("UTE_DUTEPFTP");             
		        serverFtp= data.getString("UTE_DUTESFTP");               
		        dirRemotaServerFtp= data.getString("UTE_DUTERDIR");      
		        flagAbilitazioneInvioFtp= data.getString("UTE_FUTEAFTP");
		        operatorCode = data.getString("UTE_CUTECOPE");	        
		        company = new Company(); 
		    	company.setCompanyCode(data.getString("UTE_CSOCCSOC"));
			}
		//inizio LP PG21XX04 Leak
    	} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
    }
    
    public User(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

        userCode = data.getString("UTE_CUTECUTE");
        scopeCncCode = data.getString("UTE_CUTECCNC");
        userDescription = data.getString("UTE_DUTEDUTE");
        ordinanteDescription = data.getString("UTE_DUTEORDI");
        codiceFiscale= data.getString("UTE_CUTECFIS");           
        codiceSia= data.getString("UTE_CUTECSIA");               
        codiceIban= data.getString("UTE_CUTEIBAN");              
        utenteFtp= data.getString("UTE_DUTEUFTP");               
        passwordFtp= data.getString("UTE_DUTEPFTP");             
        serverFtp= data.getString("UTE_DUTESFTP");               
        dirRemotaServerFtp= data.getString("UTE_DUTERDIR");      
        flagAbilitazioneInvioFtp= data.getString("UTE_FUTEAFTP");
        operatorCode = data.getString("UTE_CUTECOPE");
        company = new Company(); {
        	company.setCompanyCode(data.getString("UTE_CSOCCSOC"));
       
        }    
    }

	public java.lang.String getUserCode() {
		return userCode;
	}

	public void setUserCode(java.lang.String userCode) {
		this.userCode = userCode;
	}

	public java.lang.String getScopeCncCode() {
		return scopeCncCode;
	}

	public void setScopeCncCode(java.lang.String scopeCncCode) {
		this.scopeCncCode = scopeCncCode;
	}

	public java.lang.String getUserDescription() {
		return userDescription;
	}

	public void setUserDescription(java.lang.String userDescription) {
		this.userDescription = userDescription;
	}

	public java.lang.String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(java.lang.String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public java.lang.String getCodiceSia() {
		return codiceSia;
	}

	public void setCodiceSia(java.lang.String codiceSia) {
		this.codiceSia = codiceSia;
	}

	public java.lang.String getCodiceIban() {
		return codiceIban;
	}

	public void setCodiceIban(java.lang.String codiceIban) {
		this.codiceIban = codiceIban;
	}

	public java.lang.String getUtenteFtp() {
		return utenteFtp;
	}

	public void setUtenteFtp(java.lang.String utenteFtp) {
		this.utenteFtp = utenteFtp;
	}

	public java.lang.String getPasswordFtp() {
		return passwordFtp;
	}

	public void setPasswordFtp(java.lang.String passwordFtp) {
		this.passwordFtp = passwordFtp;
	}

	public java.lang.String getServerFtp() {
		return serverFtp;
	}

	public void setServerFtp(java.lang.String serverFtp) {
		this.serverFtp = serverFtp;
	}

	public java.lang.String getDirRemotaServerFtp() {
		return dirRemotaServerFtp;
	}

	public void setDirRemotaServerFtp(java.lang.String dirRemotaServerFtp) {
		this.dirRemotaServerFtp = dirRemotaServerFtp;
	}

	public java.lang.String getFlagAbilitazioneInvioFtp() {
		return flagAbilitazioneInvioFtp;
	}

	public void setFlagAbilitazioneInvioFtp(
			java.lang.String flagAbilitazioneInvioFtp) {
		this.flagAbilitazioneInvioFtp = flagAbilitazioneInvioFtp;
	}

	public java.lang.String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(java.lang.String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	
	public java.lang.String getOrdinanteDescription() {
		return ordinanteDescription;
	}

	public void setOrdinanteDescription(java.lang.String ordinanteDescription) {
		this.ordinanteDescription = ordinanteDescription;
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
/*
		arg.setString(1, this.company.getCompanyCode());
		arg.setString(2, this.userCode);
		arg.setString(3, this.scopeCncCode);
		arg.setString(4, this.userDescription);
		arg.setString(5, this.codiceFiscale);
		arg.setString(6, this.codiceSia);
		arg.setString(7, this.codiceIban);
		arg.setString(8, this.utenteFtp);
		arg.setString(9, this.passwordFtp);
		arg.setString(10, this.serverFtp);
		arg.setString(11, this.dirRemotaServerFtp); 
		arg.setString(12, this.flagAbilitazioneInvioFtp);		
		arg.setTimestamp(13, new Timestamp(System.currentTimeMillis()));
		arg.setString(14, this.operatorCode); 
*/		  
		arg.setString(1, this.company.getCompanyCode());
		arg.setString(2, this.userCode);
		arg.setString(3, this.scopeCncCode);
		arg.setString(4, this.userDescription);
		arg.setString(5, this.ordinanteDescription);
		
		arg.setString(6, this.codiceFiscale);
		arg.setString(7, this.codiceSia);
		arg.setString(8, this.codiceIban);
		arg.setString(9, this.utenteFtp);
		arg.setString(10, this.passwordFtp);
		arg.setString(11, this.serverFtp);
		arg.setString(12, this.dirRemotaServerFtp); 
		arg.setString(13, this.flagAbilitazioneInvioFtp);		
		arg.setTimestamp(14, new Timestamp(System.currentTimeMillis()));
		arg.setString(15, this.operatorCode); 
	}
	
	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {

	}
	
}