package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seda.data.dao.ModelAttributes;

public class AnagraficaBollettinoECReports  extends ModelAttributes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codiceSocieta;
	private String codiceUtente;
	private String chiaveEnte;
	private int ECA_TotAnagraficheInviate;
	private int ECA_TotAnagraficheRegistrate;
	private int BRS_TotAnagraficheInviate;
	private int BRS_TotAnagraficheRegistrate;
	private int TotAnaRegBRS_ECA;
	String anaBollLogListXml;
	
	
	public String getAnaBollLogListXml() {
		return anaBollLogListXml;
	}

	public void setAnaBollLogListXml(String anaBollLogListXml) {
		this.anaBollLogListXml = anaBollLogListXml;
	}

	public String getCodiceSocieta() {
		return codiceSocieta;
	}

	public void setCodiceSocieta(String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}

	public String getCodiceUtente() {
		return codiceUtente;
	}

	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}

	public String getChiaveEnte() {
		return chiaveEnte;
	}

	public void setChiaveEnte(String chiaveEnte) {
		this.chiaveEnte = chiaveEnte;
	}

	public int getECA_TotAnagraficheInviate() {
		return ECA_TotAnagraficheInviate;
	}

	public void setECA_TotAnagraficheInviate(int eCATotAnagraficheInviate) {
		ECA_TotAnagraficheInviate = eCATotAnagraficheInviate;
	}

	public int getECA_TotAnagraficheRegistrate() {
		return ECA_TotAnagraficheRegistrate;
	}

	public void setECA_TotAnagraficheRegistrate(int eCATotAnagraficheRegistrate) {
		ECA_TotAnagraficheRegistrate = eCATotAnagraficheRegistrate;
	}

	public int getBRS_TotAnagraficheInviate() {
		return BRS_TotAnagraficheInviate;
	}

	public void setBRS_TotAnagraficheInviate(int bRSTotAnagraficheInviate) {
		BRS_TotAnagraficheInviate = bRSTotAnagraficheInviate;
	}

	public int getBRS_TotAnagraficheRegistrate() {
		return BRS_TotAnagraficheRegistrate;
	}

	public void setBRS_TotAnagraficheRegistrate(int bRSTotAnagraficheRegistrate) {
		BRS_TotAnagraficheRegistrate = bRSTotAnagraficheRegistrate;
	}

	public int getTotAnaRegBRS_ECA() {
		return TotAnaRegBRS_ECA;
	}

	public void setTotAnaRegBRS_ECA(int totAnaRegBRSECA) {
		TotAnaRegBRS_ECA = totAnaRegBRSECA;
	}

	public AnagraficaBollettinoECReports(){
		Initialize();
	}
	
	public AnagraficaBollettinoECReports(String codiceSocieta,
			String codiceUtente,
			String chiaveEnte,
			int ECA_TotAnagraficheInviate,
			int ECA_TotAnagraficheRegistrate,
			int BRS_TotAnagraficheInviate,
			int BRS_TotAnagraficheRegistrate,
			int TotAnaRegBRS_ECA){
		Initialize();
		this.codiceSocieta=codiceSocieta;
		this.codiceUtente=codiceUtente;
		this.chiaveEnte=chiaveEnte;
		this.ECA_TotAnagraficheInviate=ECA_TotAnagraficheInviate;
		this.ECA_TotAnagraficheRegistrate=ECA_TotAnagraficheRegistrate;
		this.BRS_TotAnagraficheInviate=BRS_TotAnagraficheInviate;
		this.BRS_TotAnagraficheRegistrate=BRS_TotAnagraficheRegistrate;
		this.TotAnaRegBRS_ECA=TotAnaRegBRS_ECA;
	}

	public AnagraficaBollettinoECReports(ResultSet data) throws SQLException {
    	if (data == null)
    		return;
    	Initialize();
    	
    	if (data.findColumn("CSOCCSOC")>-1 && data.getString("CSOCCSOC") !=null) codiceSocieta = data.getString("CSOCCSOC");
    	if (data.findColumn("CUTECUTE")>-1 && data.getString("CUTECUTE") !=null) codiceUtente = data.getString("CUTECUTE");
    	if (data.findColumn("KANEKENT")>-1 && data.getString("KANEKENT") !=null) chiaveEnte = data.getString("KANEKENT");
    	if (data.getString("ECA_RPTREG") !=null) ECA_TotAnagraficheRegistrate = data.getInt("ECA_RPTREG");
    	if (data.getString("ECA_RPTINV") !=null) ECA_TotAnagraficheInviate = data.getInt("ECA_RPTINV");
    	if (data.getString("BRS_RPTREG") !=null) BRS_TotAnagraficheRegistrate = data.getInt("BRS_RPTREG");
    	if (data.getString("BRS_RPTINV") !=null) BRS_TotAnagraficheInviate = data.getInt("BRS_RPTINV");
    	if (data.getString("TOTREGIS") !=null) TotAnaRegBRS_ECA = data.getInt("TOTREGIS");

    }
	public void Initialize(){
		this.codiceSocieta="";
		this.codiceUtente="";
		this.chiaveEnte="";
		this.ECA_TotAnagraficheInviate=0;
		this.ECA_TotAnagraficheRegistrate=0;
		this.BRS_TotAnagraficheInviate=0;
		this.BRS_TotAnagraficheRegistrate=0;
		this.TotAnaRegBRS_ECA=0;
		this.anaBollLogListXml="";
	}
}
