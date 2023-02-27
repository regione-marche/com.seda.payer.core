package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;



public class ConfiguraPerUtenteEnteServizio implements Serializable
{
	private static final long serialVersionUID = 1L;
    public java.lang.String emailAmministratori;
    public java.lang.String emailConoscenzaNascosta;
    public java.lang.String emailMittente;
    public java.lang.String descEmailMittente;
    public java.lang.String urlIntegraente;  //PG190240_001 SB
    public java.lang.String codiceEnte;  //PG190240_001 SB
    public java.lang.String tipologiaServizio;  //PG190240_001 SB
    
    public ConfiguraPerUtenteEnteServizio()  {
    	
    }
    
    public static  ConfiguraPerUtenteEnteServizio getBean(ResultSet data,int nType) throws SQLException 
    {
    	ConfiguraPerUtenteEnteServizio bean = null;
    	if (data == null)
    		return null ;
    	bean = new ConfiguraPerUtenteEnteServizio();
    	
        if (nType ==0)
        {
	    	bean.emailAmministratori = data.getString("CFE_ECFEEDES");
	    	bean.emailConoscenzaNascosta = data.getString("CFE_ECFEECCN");
	    	bean.emailMittente = data.getString("CFE_ECFEEMIT");
	    	bean.descEmailMittente = data.getString("CFE_DCFEDMIT");
	    	bean.urlIntegraente = data.getString("CFE_DCFESRVC");  	//PG190240_001 SB
	    	bean.codiceEnte = data.getString("CFE_KANEKENT");  	//PG190240_001 SB
	    	bean.tipologiaServizio = data.getString("CFE_CTSECTSE");  	//PG190240_001 SB
	    }
        else
        {
        	bean.emailAmministratori = data.getString("CFS_ECFSEDES");
        	bean.emailConoscenzaNascosta = data.getString("CFS_ECFSECCN");
        	bean.emailMittente = data.getString("CFS_ECFSEMIT");
        	bean.descEmailMittente = data.getString("CFS_DCFSDMIT");
	    	bean.urlIntegraente = data.getString("CFS_DCFSSRVC");   //PG190240_001 SB
	    	bean.tipologiaServizio = data.getString("CFS_CTSECTSE");  	//PG190240_001 SB
	    }
        
        return bean;
    }


	public java.lang.String getEmailAmministratori() {
		return emailAmministratori;
	}

	public void setEmailAmministratori(java.lang.String emailAmministratori) {
		this.emailAmministratori = emailAmministratori;
	}

	public java.lang.String getEmailConoscenzaNascosta() {
		return emailConoscenzaNascosta;
	}

	public void setEmailConoscenzaNascosta(java.lang.String emailConoscenzaNascosta) {
		this.emailConoscenzaNascosta = emailConoscenzaNascosta;
	}

	public java.lang.String getEmailMittente() {
		return emailMittente;
	}

	public void setEmailMittente(java.lang.String emailMittente) {
		this.emailMittente = emailMittente;
	}

	public java.lang.String getDescEmailMittente() {
		return descEmailMittente;
	}

	public void setDescEmailMittente(java.lang.String descEmailMittente) {
		this.descEmailMittente = descEmailMittente;
	}

	public java.lang.String getUrlIntegraente() {
		return urlIntegraente;
	}

	public void setUrlIntegraente(java.lang.String urlIntegraente) {
		this.urlIntegraente = urlIntegraente;
	}

	public java.lang.String getCodiceEnte() {
		return codiceEnte;
	}

	public void setCodiceEnte(java.lang.String codiceEnte) {
		this.codiceEnte = codiceEnte;
	}

	public java.lang.String getTipologiaServizio() {
		return tipologiaServizio;
	}

	public void setTipologiaServizio(java.lang.String tipologiaServizio) {
		this.tipologiaServizio = tipologiaServizio;
	}
    
}
