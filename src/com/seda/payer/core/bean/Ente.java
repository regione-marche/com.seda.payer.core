package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.payer.commons.bean.Lifecycle;

public class Ente extends Lifecycle implements Serializable {
	private static final long serialVersionUID = 1L;
	private java.lang.String tipoEnte;//ENT_TENTTENT" CHAR(1) NOT NULL,
	private java.lang.String emailAdmin;//"ENT_CENTEMAIL" VARCHAR(100) NOT NULL,
	private java.lang.String cFiscalePIva;//"ENT_CENTCFIS" CHAR(16) NOT NULL,
	private java.lang.String codSia;//"ENT_CENTCSIA" CHAR(5) NOT NULL,
	private java.lang.String flagAutInvSMS;//"ENT_FENTFSMS" CHAR(1) NOT NULL,
// dom
	private java.lang.String flagBeneficiario;//"ENT_FENTRIVE" CHAR(1) NOT NULL,
// fine dom
	private java.lang.String dirFlussiEccInput;//"ENT_CENTDIRE" VARCHAR(50) NOT NULL,
	private java.lang.String dirSaveFlussiEccInput;//"ENT_CENTDIRE_OLD" VARCHAR(50) NOT NULL,
	private java.lang.String dirFlussiRimbOutput; //"ENT_CENTDIRR" VARCHAR(50) NOT NULL,
	private java.lang.String dirSaveFlussiRimbOutput;//"ENT_CENTDIRR_OLD" VARCHAR(50) NOT NULL,
	private java.lang.String siteFtpFlussiRimbEcc;//"ENT_CENTFTPR" VARCHAR(50) NOT NULL,
	private java.lang.String userSiteFtpFlussiRimbEcc;//"ENT_CENTFTPU" VARCHAR(50) NOT NULL,
	private java.lang.String pswSiteFtpFlussiRimbEcc;//"ENT_CENTFTPP" VARCHAR(50) NOT NULL,
	private java.lang.String dirFlussiCbiInput;//"ENT_CENTDIRE_CBI" VARCHAR(50) NOT NULL,
	private java.lang.String dirSaveFlussiCbiInput;//"ENT_CENTDIRE_CBI_OLD" VARCHAR(50) NOT NULL,
	private java.lang.String dirFlussiRimbEccInput;//"ENT_CENTDIRR_CBI" VARCHAR(50) NOT NULL,
	private java.lang.String dirSaveFlussiRimbEccInput;//"ENT_CENTDIRR_CBI_OLD" VARCHAR(50) NOT NULL,
	private java.lang.String userSiteFtpFlussiCbi;//"ENT_CENTFTPU_CBI" VARCHAR(50) NOT NULL,
	private java.lang.String pswSiteFtpFlussiCbi;//"ENT_CENTFTPP_CBI" VARCHAR(50) NOT NULL,
	private java.lang.String siteFtpFlussiCbi; //"ENT_CENTFTPR_CBI"
	private java.lang.String flagCreateMod21; //"ENT_FENTFM21"  CHAR(1) NOT NULL,
	private java.lang.String emailInvioMod21; //"ENT_DENTMAIL"  VARCHAR(256) NOT NULL,
	private java.lang.String flagRuoli; //"ENT_FENTRUOL"  CHAR(1) NOT NULL,
	private java.lang.String dirFtpFlussiRimbEcc; //"ENT_CENTDFRE"  VARCHAR(50) NOT NULL,
	private java.lang.String dirFtpFlussiCbi; //"ENT_CENTDFCB"  VARCHAR(50) NOT NULL,
    private java.lang.String codiceOperatore; //ENT_CENTCOPE
    //inizio LP 20180627
    private java.lang.String flagFlussoRendicontazioneNodo; //ENT_FENTFNOT
    private java.lang.String listaEmailFlussoRendicontazioneNodo;//ENT_CENTLSML
    private java.lang.String urlWebServiceFlussoRendicontazioneNodo;//ENT_CENTWBSR
    //fine LP 20180627
    // inizio PG18010
    private java.lang.String flagGDC;
	private java.lang.String dirGDCInput;
    private java.lang.String dirGDCSave;
    private java.lang.String endpointUniit;
    //fine PG180010
    //inizio LP PG180290
    private java.lang.String codIpaEnte;
    private java.lang.String passwordEnte;
    private java.lang.String passwordSha256Ente;
    //fine LP PG180290
    //inizio LP PG210040
    private java.lang.String codiceGruppo;
	//fine LP PG210040
	private java.lang.String flagIntegrazioneJPPA;
    private User user;
    private AnagEnte anagEnte;

    public Ente() { 
    	user = new User();
    	anagEnte = new AnagEnte();
    }

    public Ente(String companyCode, String userCode, String chiaveEnte) { 
    	user = new User();
    	user.getCompany().setCompanyCode(companyCode);
    	user.setUserCode(userCode);
    	anagEnte = new AnagEnte();
    	anagEnte.setChiaveEnte(chiaveEnte);
    }

    public Ente(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

        
        tipoEnte = data.getString("ENT_TENTTENT");
        emailAdmin = data.getString("ENT_CENTEMAIL");
		//inizio LP 20180628
        //cFiscalePIva = data.getString("ENT_CENTCFIS");
        cFiscalePIva = data.getString("ENT_CENTCFIS").trim();
		//fine LP 20180628
        codSia= data.getString("ENT_CENTCSIA");
        flagAutInvSMS = data.getString("ENT_FENTFSMS");
// dom
        flagBeneficiario = data.getString("ENT_FENTRIVE"); 
// fine dom
        dirFlussiEccInput = data.getString("ENT_CENTDIRE");
        dirSaveFlussiEccInput = data.getString("ENT_CENTDIRE_OLD");
        dirFlussiRimbOutput = data.getString("ENT_CENTDIRR");
        dirSaveFlussiRimbOutput = data.getString("ENT_CENTDIRR_OLD");
    	siteFtpFlussiRimbEcc= data.getString("ENT_CENTFTPR");
    	userSiteFtpFlussiRimbEcc= data.getString("ENT_CENTFTPU");
    	pswSiteFtpFlussiRimbEcc= data.getString("ENT_CENTFTPP");
    	dirFlussiCbiInput= data.getString("ENT_CENTDIRE_CBI");
    	dirSaveFlussiCbiInput= data.getString("ENT_CENTDIRE_CBI_OLD");
    	dirFlussiRimbEccInput = data.getString("ENT_CENTDIRR_CBI");
    	dirSaveFlussiRimbEccInput= data.getString("ENT_CENTDIRR_CBI_OLD");
    	userSiteFtpFlussiCbi= data.getString("ENT_CENTFTPU_CBI");
    	pswSiteFtpFlussiCbi= data.getString("ENT_CENTFTPP_CBI");
    	siteFtpFlussiCbi= data.getString("ENT_CENTFTPR_CBI");
    	flagCreateMod21= data.getString("ENT_FENTFM21");
    	emailInvioMod21= data.getString("ENT_DENTMAIL");
    	flagRuoli= data.getString("ENT_FENTRUOL");
    	dirFtpFlussiRimbEcc= data.getString("ENT_CENTDFRE");
    	dirFtpFlussiCbi= data.getString("ENT_CENTDFCB");
    	
        codiceOperatore = data.getString("ENT_CENTCOPE");
        //inizio LP 20180627
        flagFlussoRendicontazioneNodo = data.getString("ENT_FENTFNOT").trim();
        listaEmailFlussoRendicontazioneNodo = data.getString("ENT_CENTLSML").trim();
        urlWebServiceFlussoRendicontazioneNodo = data.getString("ENT_CENTWBSR").trim();
        //fine LP 20180627
        //inizio PG180010
        flagGDC = data.getString("ENT_FENTFGDC").trim();
        dirGDCInput = data.getString("ENT_CENTDGDC").trim();
        dirGDCSave = data.getString("ENT_CENTDGDC_OLD").trim();
        endpointUniit = data.getString("ENT_CENTEUNI").trim();
        //fine PG180010
        //inizio LP PG180290
        if(data.getString("ENT_CENTMYCO") != null)
        	codIpaEnte = data.getString("ENT_CENTMYCO").trim();
        if(data.getString("ENT_CENTMYPW") != null)
        	passwordEnte = data.getString("ENT_CENTMYPW").trim();
        if(data.getString("ENT_CENTMYPC") != null)
        	passwordSha256Ente = data.getString("ENT_CENTMYPC").trim();
        //fine LP PG180290
        //inizio LP PG210040
        if(data.getString("ENT_CGRPCODE") != null)
        	codiceGruppo = data.getString("ENT_CGRPCODE").trim();
		if(data.getString("ENT_FENTJPPA") != null)
			flagIntegrazioneJPPA = data.getString("ENT_FENTJPPA").trim();
        //fine LP PG210040
        user = new User(); {
        	user.getCompany().setCompanyCode(data.getString("ENT_CSOCCSOC"));
        	user.setUserCode(data.getString("ENT_CUTECUTE"));
        }
        anagEnte = new AnagEnte(); {
        	anagEnte.setChiaveEnte(data.getString("ENT_KANEKENT"));
        }
    }
    

	public java.lang.String getTipoEnte() {
		return tipoEnte;
	}

	public void setTipoEnte(java.lang.String tipoEnte) {
		this.tipoEnte = tipoEnte;
	}

	public java.lang.String getEmailAdmin() {
		return emailAdmin;
	}

	public void setEmailAdmin(java.lang.String emailAdmin) {
		this.emailAdmin = emailAdmin;
	}

	public java.lang.String getcFiscalePIva() {
		return cFiscalePIva;
	}

	public void setcFiscalePIva(java.lang.String cFiscalePIva) {
		this.cFiscalePIva = cFiscalePIva;
	}

	public java.lang.String getCodSia() {
		return codSia;
	}

	public void setCodSia(java.lang.String codSia) {
		this.codSia = codSia;
	}

	public java.lang.String getFlagAutInvSMS() {
		return flagAutInvSMS;
	}

	public void setFlagAutInvSMS(java.lang.String flagAutInvSMS) {
		this.flagAutInvSMS = flagAutInvSMS;
	}

	public java.lang.String getDirFlussiEccInput() {
		return dirFlussiEccInput;
	}

	public void setDirFlussiEccInput(java.lang.String dirFlussiEccInput) {
		this.dirFlussiEccInput = dirFlussiEccInput;
	}

	public java.lang.String getDirSaveFlussiEccInput() {
		return dirSaveFlussiEccInput;
	}

	public void setDirSaveFlussiEccInput(java.lang.String dirSaveFlussiEccInput) {
		this.dirSaveFlussiEccInput = dirSaveFlussiEccInput;
	}

	public java.lang.String getDirFlussiRimbOutput() {
		return dirFlussiRimbOutput;
	}

	public void setDirFlussiRimbOutput(java.lang.String dirFlussiRimbOutput) {
		this.dirFlussiRimbOutput = dirFlussiRimbOutput;
	}

	public java.lang.String getDirSaveFlussiRimbOutput() {
		return dirSaveFlussiRimbOutput;
	}

	public void setDirSaveFlussiRimbOutput(java.lang.String dirSaveFlussiRimbOutput) {
		this.dirSaveFlussiRimbOutput = dirSaveFlussiRimbOutput;
	}

	public java.lang.String getSiteFtpFlussiRimbEcc() {
		return siteFtpFlussiRimbEcc;
	}

	public void setSiteFtpFlussiRimbEcc(java.lang.String siteFtpFlussiRimbEcc) {
		this.siteFtpFlussiRimbEcc = siteFtpFlussiRimbEcc;
	}

	public java.lang.String getUserSiteFtpFlussiRimbEcc() {
		return userSiteFtpFlussiRimbEcc;
	}

	public void setUserSiteFtpFlussiRimbEcc(
			java.lang.String userSiteFtpFlussiRimbEcc) {
		this.userSiteFtpFlussiRimbEcc = userSiteFtpFlussiRimbEcc;
	}

	public java.lang.String getPswSiteFtpFlussiRimbEcc() {
		return pswSiteFtpFlussiRimbEcc;
	}

	public void setPswSiteFtpFlussiRimbEcc(java.lang.String pswSiteFtpFlussiRimbEcc) {
		this.pswSiteFtpFlussiRimbEcc = pswSiteFtpFlussiRimbEcc;
	}

	public java.lang.String getDirFlussiCbiInput() {
		return dirFlussiCbiInput;
	}

	public void setDirFlussiCbiInput(java.lang.String dirFlussiCbiInput) {
		this.dirFlussiCbiInput = dirFlussiCbiInput;
	}

	public java.lang.String getDirSaveFlussiCbiInput() {
		return dirSaveFlussiCbiInput;
	}

	public void setDirSaveFlussiCbiInput(java.lang.String dirSaveFlussiCbiInput) {
		this.dirSaveFlussiCbiInput = dirSaveFlussiCbiInput;
	}

	public java.lang.String getDirFlussiRimbEccInput() {
		return dirFlussiRimbEccInput;
	}

	public void setDirFlussiRimbEccInput(java.lang.String dirFlussiRimbEccInput) {
		this.dirFlussiRimbEccInput = dirFlussiRimbEccInput;
	}

	public java.lang.String getDirSaveFlussiRimbEccInput() {
		return dirSaveFlussiRimbEccInput;
	}

	public void setDirSaveFlussiRimbEccInput(
			java.lang.String dirSaveFlussiRimbEccInput) {
		this.dirSaveFlussiRimbEccInput = dirSaveFlussiRimbEccInput;
	}

	public java.lang.String getUserSiteFtpFlussiCbi() {
		return userSiteFtpFlussiCbi;
	}

	public void setUserSiteFtpFlussiCbi(java.lang.String userSiteFtpFlussiCbi) {
		this.userSiteFtpFlussiCbi = userSiteFtpFlussiCbi;
	}

	public java.lang.String getPswSiteFtpFlussiCbi() {
		return pswSiteFtpFlussiCbi;
	}

	public void setPswSiteFtpFlussiCbi(java.lang.String pswSiteFtpFlussiCbi) {
		this.pswSiteFtpFlussiCbi = pswSiteFtpFlussiCbi;
	}

	public java.lang.String getSiteFtpFlussiCbi() {
		return siteFtpFlussiCbi;
	}

	public void setSiteFtpFlussiCbi(java.lang.String siteFtpFlussiCbi) {
		this.siteFtpFlussiCbi = siteFtpFlussiCbi;
	}

	public java.lang.String getCodiceOperatore() {
		return codiceOperatore;
	}

	public void setCodiceOperatore(java.lang.String codiceOperatore) {
		this.codiceOperatore = codiceOperatore;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AnagEnte getAnagEnte() {
		return anagEnte;
	}

	public void setAnagEnte(AnagEnte anagEnte) {
		this.anagEnte = anagEnte;
	}

// dom
	public java.lang.String getFlagBeneficiario() {
		return flagBeneficiario;
	}

	public void setFlagBeneficiario(java.lang.String flagBeneficiario) {
		this.flagBeneficiario = flagBeneficiario;
	}
	
// fine Dom
	public java.lang.String getFlagCreateMod21() {
		return flagCreateMod21;
	}

	public void setFlagCreateMod21(java.lang.String flagCreateMod21) {
		this.flagCreateMod21 = flagCreateMod21;
	}

	public java.lang.String getEmailInvioMod21() {
		return emailInvioMod21;
	}

	public void setEmailInvioMod21(java.lang.String emailInvioMod21) {
		this.emailInvioMod21 = emailInvioMod21;
	}

	public java.lang.String getFlagRuoli() {
		return flagRuoli;
	}

	public void setFlagRuoli(java.lang.String flagRuoli) {
		this.flagRuoli = flagRuoli;
	}

	public java.lang.String getDirFtpFlussiRimbEcc() {
		return dirFtpFlussiRimbEcc;
	}

	public void setDirFtpFlussiRimbEcc(java.lang.String dirFtpFlussiRimbEcc) {
		this.dirFtpFlussiRimbEcc = dirFtpFlussiRimbEcc;
	}

	public java.lang.String getDirFtpFlussiCbi() {
		return dirFtpFlussiCbi;
	}

	public void setDirFtpFlussiCbi(java.lang.String dirFtpFlussiCbi) {
		this.dirFtpFlussiCbi = dirFtpFlussiCbi;
	}
 
	//inizio LP 20180627
	public void setFlagFlussoRendicontazioneNodo(
			java.lang.String flagFlussoRendicontazioneNodo) {
		this.flagFlussoRendicontazioneNodo = flagFlussoRendicontazioneNodo;
	}

	public java.lang.String getFlagFlussoRendicontazioneNodo() {
		return flagFlussoRendicontazioneNodo;
	}

	public void setListaEmailFlussoRendicontazioneNodo(
			java.lang.String listaEmailFlussoRendicontazioneNodo) {
		this.listaEmailFlussoRendicontazioneNodo = listaEmailFlussoRendicontazioneNodo;
	}

	public java.lang.String getListaEmailFlussoRendicontazioneNodo() {
		return listaEmailFlussoRendicontazioneNodo;
	}

	public void setUrlWebServiceFlussoRendicontazioneNodo(
			java.lang.String urlWebServiceFlussoRendicontazioneNodo) {
		this.urlWebServiceFlussoRendicontazioneNodo = urlWebServiceFlussoRendicontazioneNodo;
	}

	public java.lang.String getUrlWebServiceFlussoRendicontazioneNodo() {
		return urlWebServiceFlussoRendicontazioneNodo;
	}
	//fine LP 20180627
	
	//inizio PG180010

	public java.lang.String getFlagGDC() {
		return flagGDC;
	}

	public void setFlagGDC(java.lang.String flagGDC) {
		this.flagGDC = flagGDC;
	}

	public java.lang.String getDirGDCInput() {
		return dirGDCInput;
	}

	public void setDirGDCInput(java.lang.String dirGDCInput) {
		this.dirGDCInput = dirGDCInput;
	}

	public java.lang.String getDirGDCSave() {
		return dirGDCSave;
	}

	public void setDirGDCSave(java.lang.String dirGDCSave) {
		this.dirGDCSave = dirGDCSave;
	}
	public java.lang.String getEndpointUniit() {
		return endpointUniit;
	}

	public void setEndpointUniit(java.lang.String endpointUniit) {
		this.endpointUniit = endpointUniit;
	}

	//fine PG180010
	//inizio LP PG180290
	public java.lang.String getCodIpaEnte() {
		return codIpaEnte;
	}

	public void setCodIpaEnte(java.lang.String codIpaEnte) {
		this.codIpaEnte = codIpaEnte;
	}

	public java.lang.String getPasswordEnte() {
		return passwordEnte;
	}

	public void setPasswordEnte(java.lang.String passwordEnte) {
		this.passwordEnte = passwordEnte;
	}

	public java.lang.String getPasswordSha256Ente() {
		return passwordSha256Ente;
	}

	public void setPasswordSha256Ente(java.lang.String passwordSha256Ente) {
		this.passwordSha256Ente = passwordSha256Ente;
	}
	//fine LP PG180290
    //inizio LP PG210040
	public java.lang.String getCodiceGruppo() {
		return codiceGruppo;
	}

	public void setCodiceGruppo(java.lang.String codiceGruppo) {
		this.codiceGruppo = codiceGruppo;
	}
    //fine LP PG210040

	public java.lang.String getFlagIntegrazioneJPPA() {
		return flagIntegrazioneJPPA;
	}

	public void setFlagIntegrazioneJPPA(java.lang.String flagIntegrazioneJPPA) {
		this.flagIntegrazioneJPPA = flagIntegrazioneJPPA;
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.user.getCompany().getCompanyCode());
		arg.setString(2, this.user.getUserCode());
		arg.setString(3, this.anagEnte.getChiaveEnte());
		arg.setString(4, this.tipoEnte);
		arg.setString(5, this.emailAdmin);
		arg.setString(6, this.cFiscalePIva);
		arg.setString(7, this.codSia);
		arg.setString(8, this.flagAutInvSMS);
		arg.setString(9, this.dirFlussiEccInput);
		arg.setString(10, this.dirSaveFlussiEccInput);
		arg.setString(11, this.dirFlussiRimbOutput);
		arg.setString(12, this.dirSaveFlussiRimbOutput);
		arg.setString(13, this.siteFtpFlussiRimbEcc);
		arg.setString(14, this.userSiteFtpFlussiRimbEcc);
		arg.setString(15, this.pswSiteFtpFlussiRimbEcc);
		arg.setString(16, this.dirFlussiCbiInput);
		arg.setString(17, this.dirSaveFlussiCbiInput);
		arg.setString(18, this.dirFlussiRimbEccInput);
		arg.setString(19, this.dirSaveFlussiRimbEccInput);
		arg.setString(20, this.userSiteFtpFlussiCbi);
		arg.setString(21, this.pswSiteFtpFlussiCbi);
		arg.setString(22, this.siteFtpFlussiCbi);
		arg.setString(23, this.codiceOperatore);  // (OPERATORE ULTIMO AGGIORNAMENTO)
	// DOM
		arg.setString(24, this.flagBeneficiario);
	// fine DOM
		arg.setString(25, this.flagCreateMod21);
		arg.setString(26, this.emailInvioMod21);
		arg.setString(27, this.flagRuoli);
		arg.setString(28, this.dirFtpFlussiRimbEcc);
		arg.setString(29, this.dirFtpFlussiCbi);
	    //inizio LP 20180627
		arg.setString(30, this.flagFlussoRendicontazioneNodo);
		arg.setString(31, this.listaEmailFlussoRendicontazioneNodo);
		arg.setString(32, this.urlWebServiceFlussoRendicontazioneNodo);
	    //fine LP 20180627
		//inizio PG180010
		//inizio LP PG200060
		//arg.setString(33, this.flagGDC);
		//arg.setString(34, this.dirGDCInput);
		//arg.setString(35, this.dirGDCSave);
		//arg.setString(36, this.endpointUniit);
		arg.setString(33, (this.flagGDC != null ? this.flagGDC : ""));
		arg.setString(34, (this.dirGDCInput != null ? this.dirGDCInput : ""));
		arg.setString(35, (this.dirGDCSave != null ? this.dirGDCSave : ""));
		arg.setString(36, (this.endpointUniit != null ? this.endpointUniit : ""));
		//fine LP PG200060
		//fine PG180010
        //inizio LP PG180290
		if(codIpaEnte != null)
			arg.setString(37, codIpaEnte);
		else
			arg.setNull(37, Types.VARCHAR);
		if(passwordEnte != null)
			arg.setString(38, passwordEnte);
		else
			arg.setNull(38, Types.VARCHAR);
		if(passwordSha256Ente != null)
			arg.setString(39,passwordSha256Ente);
		else
			arg.setNull(39, Types.VARCHAR);
        //fine LP PG180290
	    //inizio LP PG210040
		if(codiceGruppo != null)
			arg.setString(40,codiceGruppo);
		else
			arg.setNull(40, Types.VARCHAR);
	    //fine LP PG210040
		if(flagIntegrazioneJPPA != null)
			arg.setString(41, flagIntegrazioneJPPA);
		else
			arg.setNull(41, Types.VARCHAR);
	}
	

	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.user.getCompany().getCompanyCode());
		arg.setString(2, this.user.getUserCode());
		arg.setString(3, this.anagEnte.getChiaveEnte());
		arg.setString(4, this.tipoEnte);
		arg.setString(5, this.emailAdmin);
		arg.setString(6, this.cFiscalePIva);
		arg.setString(7, this.codSia);
		arg.setString(8, this.flagAutInvSMS);
		arg.setString(9, this.dirFlussiEccInput);
		arg.setString(10, this.dirSaveFlussiEccInput);
		arg.setString(11, this.dirFlussiRimbOutput);
		arg.setString(12, this.dirSaveFlussiRimbOutput);
		arg.setString(13, this.siteFtpFlussiRimbEcc);
		arg.setString(14, this.userSiteFtpFlussiRimbEcc);
		arg.setString(15, this.pswSiteFtpFlussiRimbEcc);
		arg.setString(16, this.dirFlussiCbiInput);
		arg.setString(17, this.dirSaveFlussiCbiInput);
		arg.setString(18, this.dirFlussiRimbEccInput);
		arg.setString(19, this.dirSaveFlussiRimbEccInput);
		arg.setString(20, this.userSiteFtpFlussiCbi);
		arg.setString(21, this.pswSiteFtpFlussiCbi);
		arg.setString(22, this.siteFtpFlussiCbi);
		arg.setString(23, this.codiceOperatore);
//DOM
		arg.setString(24, this.flagBeneficiario);
// fine Dom
		arg.setString(25, this.flagCreateMod21);
		arg.setString(26, this.emailInvioMod21);
		arg.setString(27, this.flagRuoli);
		arg.setString(28, this.dirFtpFlussiRimbEcc);
		arg.setString(29, this.dirFtpFlussiCbi);
	    //inizio LP 20180627
		arg.setString(30, this.flagFlussoRendicontazioneNodo);
		arg.setString(31, this.listaEmailFlussoRendicontazioneNodo);
		arg.setString(32, this.urlWebServiceFlussoRendicontazioneNodo);
	    //fine LP 20180627
		//inizio PG180010
		//inizio LP PG200060
		//arg.setString(33, this.flagGDC);
		//arg.setString(34, this.dirGDCInput);
		//arg.setString(35, this.dirGDCSave);
		//arg.setString(36, this.endpointUniit);
		arg.setString(33, (this.flagGDC != null ? this.flagGDC : ""));
		arg.setString(34, (this.dirGDCInput != null ? this.dirGDCInput : ""));
		arg.setString(35, (this.dirGDCSave != null ? this.dirGDCSave : ""));
		arg.setString(36, (this.endpointUniit != null ? this.endpointUniit : ""));
		//fine LP PG200060
		//fine PG180010
        //inizio LP PG180290
		if(codIpaEnte != null)
			arg.setString(37, codIpaEnte);
		else
			arg.setNull(37, Types.VARCHAR);
		if(passwordEnte != null)
			arg.setString(38, passwordEnte);
		else
			arg.setNull(38, Types.VARCHAR);
		if(passwordSha256Ente != null)
			arg.setString(39,passwordSha256Ente);
		else
			arg.setNull(39, Types.VARCHAR);
        //fine LP PG180290
	    //inizio LP PG210040
		if(codiceGruppo != null)
			arg.setString(40,codiceGruppo);
		else
			arg.setNull(40, Types.VARCHAR);
		if(flagIntegrazioneJPPA != null)
			arg.setString(41, flagIntegrazioneJPPA);
		else
			arg.setNull(41, Types.VARCHAR);
	    //fine LP PG210040
	}
	
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {

	}
	
	
}