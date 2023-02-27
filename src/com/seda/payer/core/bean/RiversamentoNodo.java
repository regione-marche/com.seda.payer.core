/**
 * 
 */
package com.seda.payer.core.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author rcarnicelli
 *
 */
public class RiversamentoNodo {

	private String REV_COSCCSOC;
	private String REV_CUTECUTE;
	private Date REV_GREVGDAT;
	private String REV_KANEKANE_BEN;
	private String REV_FREVTIPO;
	private String REV_FREVTIPO_D;
	private String REV_FREVRIVE;
	private String REV_FREVRIVE_D;
	private BigDecimal REV_IREVTOTA;
	private BigDecimal REV_IREVCONC;
	private BigDecimal REV_IREVCGTW;
	private BigDecimal REV_IREVSPES;
	private BigDecimal REV_IREVGESC;
	private BigDecimal REV_IREVIREV;
	private String REV_FREVSTAT;
	private String REV_FREVSTAT_D;
	private Date REV_GREVCHIU;
	private String REV_CREVOPEC;
	private Date REV_GREVSOSP;
	private String REV_CREVNOTA;
	private String REV_CREVOPES;
	private Date REV_GREVRSOS;
	private String REV_CREVOPRS;
	private Date REV_GREVEFFE;
	private String REV_CREVOPER;
	private String REV_FREVRCBI;
	private String REV_KCBIKCBI;
	private String REV_DREVREPO;
	private String REV_DREVDOWN;
	private Date REV_GREVGAGG;
	private String REV_CREVCOPE;

	private String ANE_CANECENT;
    private String ANE_CANECUFF;
	private String ANE_TANETUFF;
	
	private String ANE_DANEDENT;
	private String ANE_DANEDUFF;
	
	private String SOC_DSOCDSOC;
	
	private String UTE_CUTECSIA;
	private String UTE_CUTEIBAN;
	private String UTE_DUTEDUTE;
	private String UTE_CUTECFIS;
	private String UTE_DUTEUFTP;
	private String UTE_DUTEPFTP;
	private String UTE_DUTESFTP;
	private String UTE_DUTERDIR;
	private String UTE_FUTEAFTP;

	private String UTE_DUTEORDI;
	
	private String NAME_SUPPORT;
	private String BEN_CBENIBAN;
	private String BEN_CBENCFIS;

//120090
	private String BEN_DBENBENE;
//Fine 120090
	
	private String CBI_DCBIFILE;
	
	private String BEN_TBENFCSV;
	/**
	 * 
	 */
	public RiversamentoNodo() {
		REV_COSCCSOC = "";
		REV_CUTECUTE = "";
		REV_GREVGDAT = null;
		REV_KANEKANE_BEN = "";
		REV_FREVTIPO = "";
		REV_FREVTIPO_D = "";
		REV_FREVRIVE = "";
		REV_FREVRIVE_D = "";
		REV_IREVTOTA = new BigDecimal(0.0);
		REV_IREVCONC = new BigDecimal(0.0);
		REV_IREVCGTW = new BigDecimal(0.0);
		REV_IREVSPES = new BigDecimal(0.0);
		REV_IREVGESC = new BigDecimal(0.0);
		REV_IREVIREV = new BigDecimal(0.0);
		REV_FREVSTAT = "";
		REV_FREVSTAT_D = "";
		REV_GREVCHIU = null;
		REV_CREVOPEC = "";
		REV_GREVSOSP = null;
		REV_CREVNOTA = "";
		REV_CREVOPES = "";
		REV_GREVRSOS = null;
		REV_CREVOPRS = "";
		REV_GREVEFFE = null;
		REV_CREVOPER = "";
		REV_FREVRCBI = "";
		REV_KCBIKCBI = "";
		REV_DREVREPO = "";
		REV_DREVDOWN = "";
		REV_GREVGAGG = null;
		REV_CREVCOPE = "";
		ANE_CANECUFF = "";
		ANE_TANETUFF = "";
		
		ANE_DANEDENT = "";
		ANE_CANECENT = "";
		
		UTE_CUTECSIA = "";
		UTE_CUTEIBAN = "";
		UTE_DUTEDUTE = "";
		UTE_CUTECFIS = "";
		UTE_DUTEUFTP = "";
		UTE_DUTEPFTP = "";
		UTE_DUTESFTP = "";
		UTE_DUTERDIR = "";
		UTE_FUTEAFTP = "";
		
		UTE_DUTEORDI = "";
		
		NAME_SUPPORT = "";
		BEN_CBENIBAN = "";
		BEN_CBENCFIS = "";

		//120090
		BEN_DBENBENE = "";
		//fine 120090
		
		SOC_DSOCDSOC = "";
		
		setBEN_TBENFCSV("");
	}
	public String getANE_DANEDENT() {
		return ANE_DANEDENT;
	}
	public void setANE_DANEDENT(String aNEDANEDENT) {
		ANE_DANEDENT = aNEDANEDENT;
	}
	public String getANE_CANECENT() {
		return ANE_CANECENT;
	}
	public void setANE_CANECENT(String aNECANECENT) {
		ANE_CANECENT = aNECANECENT;
	}
	public String getUTE_CUTECSIA() {
		return UTE_CUTECSIA;
	}
	public void setUTE_CUTECSIA(String uTECUTECSIA) {
		UTE_CUTECSIA = uTECUTECSIA;
	}
	public String getUTE_CUTEIBAN() {
		return UTE_CUTEIBAN;
	}
	public void setUTE_CUTEIBAN(String uTECUTEIBAN) {
		UTE_CUTEIBAN = uTECUTEIBAN;
	}
	public String getUTE_DUTEDUTE() {
		return UTE_DUTEDUTE;
	}
	public void setUTE_DUTEDUTE(String uTEDUTEDUTE) {
		UTE_DUTEDUTE = uTEDUTEDUTE;
	}
	public String getUTE_CUTECFIS() {
		return UTE_CUTECFIS;
	}
	public void setUTE_CUTECFIS(String uTECUTECFIS) {
		UTE_CUTECFIS = uTECUTECFIS;
	}
	public String getUTE_DUTEUFTP() {
		return UTE_DUTEUFTP;
	}
	public void setUTE_DUTEUFTP(String uTEDUTEUFTP) {
		UTE_DUTEUFTP = uTEDUTEUFTP;
	}
	public String getUTE_DUTEPFTP() {
		return UTE_DUTEPFTP;
	}
	public void setUTE_DUTEPFTP(String uTEDUTEPFTP) {
		UTE_DUTEPFTP = uTEDUTEPFTP;
	}
	public String getUTE_DUTESFTP() {
		return UTE_DUTESFTP;
	}
	public void setUTE_DUTESFTP(String uTEDUTESFTP) {
		UTE_DUTESFTP = uTEDUTESFTP;
	}
	public String getUTE_DUTERDIR() {
		return UTE_DUTERDIR;
	}
	public void setUTE_DUTERDIR(String uTEDUTERDIR) {
		UTE_DUTERDIR = uTEDUTERDIR;
	}
	public String getUTE_FUTEAFTP() {
		return UTE_FUTEAFTP;
	}
	public void setUTE_FUTEAFTP(String uTEFUTEAFTP) {
		UTE_FUTEAFTP = uTEFUTEAFTP;
	}
	public String getNAME_SUPPORT() {
		return NAME_SUPPORT;
	}
	public void setNAME_SUPPORT(String nAMESUPPORT) {
		NAME_SUPPORT = nAMESUPPORT;
	}
	public String getBEN_CBENIBAN() {
		return BEN_CBENIBAN;
	}
	public void setBEN_CBENIBAN(String bENCBENIBAN) {
		BEN_CBENIBAN = bENCBENIBAN;
	}
	public String getBEN_CBENCFIS() {
		return BEN_CBENCFIS;
	}
	public void setBEN_CBENCFIS(String bENCBENCFIS) {
		BEN_CBENCFIS = bENCBENCFIS;
	}
	public String getREV_COSCCSOC() {
		return REV_COSCCSOC;
	}
	public void setREV_COSCCSOC(String rEVCOSCCSOC) {
		REV_COSCCSOC = rEVCOSCCSOC;
	}
	public String getREV_CUTECUTE() {
		return REV_CUTECUTE;
	}
	public void setREV_CUTECUTE(String rEVCUTECUTE) {
		REV_CUTECUTE = rEVCUTECUTE;
	}
	public Date getREV_GREVGDAT() {
		return REV_GREVGDAT;
	}
	public void setREV_GREVGDAT(Date rEVGREVGDAT) {
		REV_GREVGDAT = rEVGREVGDAT;
	}
	public String getREV_KANEKANE_BEN() {
		return REV_KANEKANE_BEN;
	}
	public void setREV_KANEKANE_BEN(String rEVKANEKANEBEN) {
		REV_KANEKANE_BEN = rEVKANEKANEBEN;
	}
	public String getREV_FREVTIPO() {
		return REV_FREVTIPO;
	}
	public void setREV_FREVTIPO(String rEVFREVTIPO) {
		REV_FREVTIPO = rEVFREVTIPO;
	}
	public String getREV_FREVRIVE() {
		return REV_FREVRIVE;
	}
	public void setREV_FREVRIVE(String rEVFREVRIVE) {
		REV_FREVRIVE = rEVFREVRIVE;
	}
	public BigDecimal getREV_IREVTOTA() {
		return REV_IREVTOTA;
	}
	public void setREV_IREVTOTA(BigDecimal rEVIREVTOTA) {
		REV_IREVTOTA = rEVIREVTOTA;
	}
	public BigDecimal getREV_IREVCONC() {
		return REV_IREVCONC;
	}
	public void setREV_IREVCONC(BigDecimal rEVIREVCONC) {
		REV_IREVCONC = rEVIREVCONC;
	}
	public BigDecimal getREV_IREVCGTW() {
		return REV_IREVCGTW;
	}
	public void setREV_IREVCGTW(BigDecimal rEVIREVCGTW) {
		REV_IREVCGTW = rEVIREVCGTW;
	}
	public BigDecimal getREV_IREVSPES() {
		return REV_IREVSPES;
	}
	public void setREV_IREVSPES(BigDecimal rEVIREVSPES) {
		REV_IREVSPES = rEVIREVSPES;
	}
	public BigDecimal getREV_IREVGESC() {
		return REV_IREVGESC;
	}
	public void setREV_IREVGESC(BigDecimal rEVIREVGESC) {
		REV_IREVGESC = rEVIREVGESC;
	}
	public BigDecimal getREV_IREVIREV() {
		return REV_IREVIREV;
	}
	public void setREV_IREVIREV(BigDecimal rEVIREVIREV) {
		REV_IREVIREV = rEVIREVIREV;
	}
	public String getREV_FREVSTAT() {
		return REV_FREVSTAT;
	}
	public void setREV_FREVSTAT(String rEVFREVSTAT) {
		REV_FREVSTAT = rEVFREVSTAT;
	}
	public Date getREV_GREVCHIU() {
		return REV_GREVCHIU;
	}
	public void setREV_GREVCHIU(Date rEVGREVCHIU) {
		REV_GREVCHIU = rEVGREVCHIU;
	}
	public String getREV_CREVOPEC() {
		return REV_CREVOPEC;
	}
	public void setREV_CREVOPEC(String rEVCREVOPEC) {
		REV_CREVOPEC = rEVCREVOPEC;
	}
	public Date getREV_GREVSOSP() {
		return REV_GREVSOSP;
	}
	public void setREV_GREVSOSP(Date rEVGREVSOSP) {
		REV_GREVSOSP = rEVGREVSOSP;
	}
	public String getREV_CREVNOTA() {
		return REV_CREVNOTA;
	}
	public void setREV_CREVNOTA(String rEVCREVNOTA) {
		REV_CREVNOTA = rEVCREVNOTA;
	}
	public String getREV_CREVOPES() {
		return REV_CREVOPES;
	}
	public void setREV_CREVOPES(String rEVCREVOPES) {
		REV_CREVOPES = rEVCREVOPES;
	}
	public Date getREV_GREVRSOS() {
		return REV_GREVRSOS;
	}
	public void setREV_GREVRSOS(Date rEVGREVRSOS) {
		REV_GREVRSOS = rEVGREVRSOS;
	}
	public String getREV_CREVOPRS() {
		return REV_CREVOPRS;
	}
	public void setREV_CREVOPRS(String rEVCREVOPRS) {
		REV_CREVOPRS = rEVCREVOPRS;
	}
	public Date getREV_GREVEFFE() {
		return REV_GREVEFFE;
	}
	public void setREV_GREVEFFE(Date rEVGREVEFFE) {
		REV_GREVEFFE = rEVGREVEFFE;
	}
	public String getREV_CREVOPER() {
		return REV_CREVOPER;
	}
	public void setREV_CREVOPER(String rEVCREVOPER) {
		REV_CREVOPER = rEVCREVOPER;
	}
	public String getREV_FREVRCBI() {
		return REV_FREVRCBI;
	}
	public void setREV_FREVRCBI(String rEVFREVRCBI) {
		REV_FREVRCBI = rEVFREVRCBI;
	}
	public String getREV_KCBIKCBI() {
		return REV_KCBIKCBI;
	}
	public void setREV_KCBIKCBI(String rEVKCBIKCBI) {
		REV_KCBIKCBI = rEVKCBIKCBI;
	}
	public String getREV_DREVREPO() {
		return REV_DREVREPO;
	}
	public void setREV_DREVREPO(String rEVDREVREPO) {
		REV_DREVREPO = rEVDREVREPO;
	}
	public String getREV_DREVDOWN() {
		return REV_DREVDOWN;
	}
	public void setREV_DREVDOWN(String rEVDREVDOWN) {
		REV_DREVDOWN = rEVDREVDOWN;
	}
	public Date getREV_GREVGAGG() {
		return REV_GREVGAGG;
	}
	public void setREV_GREVGAGG(Date rEVGREVGAGG) {
		REV_GREVGAGG = rEVGREVGAGG;
	}
	public String getREV_CREVCOPE() {
		return REV_CREVCOPE;
	}
	public void setREV_CREVCOPE(String rEVCREVCOPE) {
		REV_CREVCOPE = rEVCREVCOPE;
	}
	public String getANE_CANECUFF() {
		return ANE_CANECUFF;
	}
	public void setANE_CANECUFF(String aNECANECUFF) {
		ANE_CANECUFF = aNECANECUFF;
	}
	public String getANE_TANETUFF() {
		return ANE_TANETUFF;
	}
	public void setANE_TANETUFF(String aNETANETUFF) {
		ANE_TANETUFF = aNETANETUFF;
	}
	public String getCBI_DCBIFILE() {
		return CBI_DCBIFILE;
	}
	public void setCBI_DCBIFILE(String cBIDCBIFILE) {
		CBI_DCBIFILE = cBIDCBIFILE;
	}
	public String getSOC_DSOCDSOC() {
		return SOC_DSOCDSOC;
	}
	public void setSOC_DSOCDSOC(String sOCDSOCDSOC) {
		SOC_DSOCDSOC = sOCDSOCDSOC;
	}
	public String getREV_FREVTIPO_D() {
		return REV_FREVTIPO_D;
	}
	public void setREV_FREVTIPO_D(String rEVFREVTIPOD) {
		REV_FREVTIPO_D = rEVFREVTIPOD;
	}
	public String getREV_FREVRIVE_D() {
		return REV_FREVRIVE_D;
	}
	public void setREV_FREVRIVE_D(String rEVFREVRIVED) {
		REV_FREVRIVE_D = rEVFREVRIVED;
	}
	public String getREV_FREVSTAT_D() {
		return REV_FREVSTAT_D;
	}
	public void setREV_FREVSTAT_D(String rEVFREVSTATD) {
		REV_FREVSTAT_D = rEVFREVSTATD;
	}
	public String getANE_DANEDUFF() {
		return ANE_DANEDUFF;
	}
	public void setANE_DANEDUFF(String aNEDANEDUFF) {
		ANE_DANEDUFF = aNEDANEDUFF;
	}
	public String getUTE_DUTEORDI() {
		return UTE_DUTEORDI;
	}
	public void setUTE_DUTEORDI(String uTEDUTEORDI) {
		UTE_DUTEORDI = uTEDUTEORDI;
	}
	
	//120090

	public String getBEN_DBENBENE() {
		return BEN_DBENBENE;
	}
	public void setBEN_DBENBENE(String bENDBENBENE) {
		BEN_DBENBENE = bENDBENBENE;
	}
	//fine 120090
	public void setBEN_TBENFCSV(String bEN_TBENFCSV) {
		BEN_TBENFCSV = bEN_TBENFCSV;
	}
	public String getBEN_TBENFCSV() {
		return BEN_TBENFCSV;
	}
	
	
}
