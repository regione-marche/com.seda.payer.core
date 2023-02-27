package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.payer.commons.bean.Lifecycle;

public class ConfigUtenteTipoServizio extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
    private User user;
    private TipologiaServizio tipoServizio;
    private Bollettino bollettino;
    private String numCC;//"CFS_NCFSNCCP" VARCHAR(20) NOT NULL,
    private String intCC;//"CFS_DCFSDINT" VARCHAR(256) NOT NULL,
    private String tipoDoc;//"CFS_TCFSTDOC" CHAR(3) NOT NULL,
    private String flagRangeAbi;//"CFS_FCFSFCTR" CHAR(1) NOT NULL,
    private String mailDest;//"CFS_ECFSEDES" VARCHAR(100) NOT NULL,
    private String mailCCNascosta;//"CFS_ECFSECCN" VARCHAR(100) NOT NULL,
    private String mailMitt;//"CFS_ECFSEMIT" VARCHAR(100) NOT NULL,
    private String desMitt;//"CFS_DCFSDMIT" VARCHAR(100) NOT NULL,
    private String flagAll;//"CFS_FCFSFALL" CHAR(1) NOT NULL,
    private String codiceSia;//"CFS_CCFSCSIA" CHAR(5) NOT NULL,
    private String codiceIban;//"CFS_CCFSIBAN" CHAR(27) NOT NULL,
    private String secondoCodiceIban;//"CFS_CCFSIBAN" CHAR(27)"		//PG150180_001 GG
    private String funzPag;//"CFS_DCFSFUNZ" VARCHAR(256) NOT NULL,
    private String flagFunzPagProt;//"CFS_FCFSPROT" CHAR(1) NOT NULL,
    private String urlWeb;//"CFS_DCFSSRVC" VARCHAR(256) NOT NULL,
    private String flagTpPag;//"CFS_FCFSPAGA" CHAR(1) NOT NULL,
    private String operatorCode;//"CFS_CCFSCOPE" VARCHAR(50) NOT NULL
    private String flagIntegrazioneSeda;
    private String codiceUtenteSeda;
    private String flagNotificaPagamento; //"CFS_FCFSNOTI" //PG170150 CT
	private String urlServizioWebNotificaPagamento; //PG170150 CT
    private String flagPagoPA;	//CFS_FCFSFPPA
    //inizio LP PG180290
    private String tipoDovuto;
    //fine LP PG180290
    //inizio LP PG200360
    private String chiaveTassonomia;
    private String causali;
    //fine LP PG200360
    
    //inizio SB PG210140
    private String capitolo;
    private String articolo;
    private String codiceContabilita;
    private String annoCompetenza;
    //fine SB PG210140
   
    public ConfigUtenteTipoServizio() { 
    	user = new User();
    	tipoServizio = new TipologiaServizio();
    	bollettino = new Bollettino();
    }

    public ConfigUtenteTipoServizio(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

        user = new User(); {
        	user.getCompany().setCompanyCode(data.getString("CFS_CSOCCSOC"));
        	user.setUserCode(data.getString("CFS_CUTECUTE"));
        }
        tipoServizio = new TipologiaServizio(); {
        	tipoServizio.getCompany().setCompanyCode(data.getString("CFS_CSOCCSOC"));
        	tipoServizio.setCodiceTipologiaServizio(data.getString("CFS_CTSECTSE"));
        } 
        bollettino = new Bollettino();{
        	bollettino.setTipoBolletino(data.getString("CFS_TBOLTBOL"));
        }
        numCC = data.getString("CFS_NCFSNCCP");
    	intCC= data.getString("CFS_DCFSDINT");
    	tipoDoc= data.getString("CFS_TCFSTDOC");
    	flagRangeAbi= data.getString("CFS_FCFSFCTR");
    	mailDest= data.getString("CFS_ECFSEDES");
    	mailCCNascosta= data.getString("CFS_ECFSECCN");
    	mailMitt= data.getString("CFS_ECFSEMIT");
    	desMitt= data.getString("CFS_DCFSDMIT");
    	flagAll= data.getString("CFS_FCFSFALL");
    	codiceSia= data.getString("CFS_CCFSCSIA");
    	codiceIban= data.getString("CFS_CCFSIBAN");
    	secondoCodiceIban=data.getString("CFS_CCFSIBA2");	//PG150180_001 GG
    	funzPag= data.getString("CFS_DCFSFUNZ");
    	flagFunzPagProt= data.getString("CFS_FCFSPROT");
    	urlWeb= data.getString("CFS_DCFSSRVC");
    	flagTpPag= data.getString("CFS_FCFSPAGA");
    	operatorCode= data.getString("CFS_CCFSCOPE");
    	flagIntegrazioneSeda = data.getString("CFS_FCFSSEDA");
    	codiceUtenteSeda = data.getString("CFS_CCFSSEDA");
    	flagNotificaPagamento = data.getString("CFS_FCFSNOTI");
    	urlServizioWebNotificaPagamento  = data.getString("CFS_DCFSUNOT");
    	flagPagoPA = data.getString("CFS_FCFSFPPA");
        //inizio LP PG180290
        if(data.getString("CFS_CCFSMYTD") != null)
        	tipoDovuto = data.getString("CFS_CCFSMYTD").trim();
        //fine LP PG180290
        //inizio LP PG200360
        chiaveTassonomia = data.getString("CFS_CTASDSPI");
        causali = data.getString("CFS_CCFSCAUS");
        //fine LP PG200360
        //inizio SB PG210140
        try { articolo = data.getString("CFS_DCFSDART"); }catch (Exception e) {articolo = "";}
        try { codiceContabilita = data.getString("CFS_CCFSCCON"); }catch (Exception e) {codiceContabilita = "";}
        try { capitolo = data.getString("CFS_DCFSCAPT"); }catch (Exception e) {capitolo = "";}
        try { annoCompetenza = data.getString("CFS_DCFSANNC"); }catch (Exception e) {annoCompetenza = "";}
        //fine SB PG210140
    }
    

	public User getUser() {
		return user;
	}

	public void setUser(User user) {	
		this.user = user;
	}

	public TipologiaServizio getTipoServizio() {
		return tipoServizio;
	}

	public void setTipoServizio(TipologiaServizio tipoServizio) {
		this.tipoServizio = tipoServizio;
	}

	public Bollettino getBollettino() {
		return bollettino;
	}

	public void setBollettino(Bollettino bollettino) {
		this.bollettino = bollettino;
	}

	public String getNumCC() {
		return numCC;
	}

	public void setNumCC(String numCC) {
		this.numCC = numCC;
	}

	public String getIntCC() {
		return intCC;
	}

	public void setIntCC(String intCC) {
		this.intCC = intCC;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getFlagRangeAbi() {
		return flagRangeAbi;
	}

	public void setFlagRangeAbi(String flagRangeAbi) {
		this.flagRangeAbi = flagRangeAbi;
	}

	public String getMailDest() {
		return mailDest;
	}

	public void setMailDest(String mailDest) {
		this.mailDest = mailDest;
	}

	public String getMailCCNascosta() {
		return mailCCNascosta;
	}

	public void setMailCCNascosta(String mailCCNascosta) {
		this.mailCCNascosta = mailCCNascosta;
	}

	public String getMailMitt() {
		return mailMitt;
	}

	public void setMailMitt(String mailMitt) {
		this.mailMitt = mailMitt;
	}

	public String getDesMitt() {
		return desMitt;
	}

	public void setDesMitt(String desMitt) {
		this.desMitt = desMitt;
	}

	public String getFlagAll() {
		return flagAll;
	}

	public void setFlagAll(String flagAll) {
		this.flagAll = flagAll;
	}

	public String getCodiceSia() {
		return codiceSia;
	}

	public void setCodiceSia(String codiceSia) {
		this.codiceSia = codiceSia;
	}

	public String getCodiceIban() {
		return codiceIban;
	}

	public void setCodiceIban(String codiceIban) {
		this.codiceIban = codiceIban;
	}

	public String getFunzPag() {
		return funzPag;
	}

	public void setFunzPag(String funzPag) {
		this.funzPag = funzPag;
	}

	public String getFlagFunzPagProt() {
		return flagFunzPagProt;
	}

	public void setFlagFunzPagProt(String flagFunzPagProt) {
		this.flagFunzPagProt = flagFunzPagProt;
	}

	public String getUrlWeb() {
		return urlWeb;
	}

	public void setUrlWeb(String urlWeb) {
		this.urlWeb = urlWeb;
	}

	public String getFlagTpPag() {
		return flagTpPag;
	}

	public void setFlagTpPag(String flagTpPag) {
		this.flagTpPag = flagTpPag;
	}
	
	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public void setFlagIntegrazioneSeda(String flagIntegrazioneSeda) {
		this.flagIntegrazioneSeda = flagIntegrazioneSeda;
	}

	public String getFlagIntegrazioneSeda() {
		return flagIntegrazioneSeda;
	}

	public void setCodiceUtenteSeda(String codiceUtenteSeda) {
		this.codiceUtenteSeda = codiceUtenteSeda;
	}

	public String getCodiceUtenteSeda() {
		return codiceUtenteSeda;
	}

	//PG150180_001 GG - inizio
	public String getSecondoCodiceIban() {
		return secondoCodiceIban;
	}

	public void setSecondoCodiceIban(String secondoCodiceIban) {
		this.secondoCodiceIban = secondoCodiceIban;
	}
	//PG150180_001 GG - fine

	public void setFlagNotificaPagamento(String flagNotificaPagamento) {
		this.flagNotificaPagamento = flagNotificaPagamento;
	}

	public String getFlagNotificaPagamento() {
		return flagNotificaPagamento;
	}

	public void setUrlServizioWebNotificaPagamento(
			String urlServizioWebNotificaPagamento) {
		this.urlServizioWebNotificaPagamento = urlServizioWebNotificaPagamento;
	}

	public String getUrlServizioWebNotificaPagamento() {
		return urlServizioWebNotificaPagamento;
	}

	public String getFlagPagoPA() {
		return flagPagoPA;
	}

	public void setFlagPagoPA(String flagPagoPA) {
		this.flagPagoPA = flagPagoPA;
	}

    //inizio LP PG180290
	public String getTipoDovuto() {
		return tipoDovuto;
	}

	public void setTipoDovuto(String tipoDovuto) {
		this.tipoDovuto = tipoDovuto;
	}
	//fine LP PG180290

    //inizio LP PG200360
	public String getChiaveTassonomia() {
		return chiaveTassonomia;
	}

	public void setChiaveTassonomia(String chiaveTassonomia) {
		this.chiaveTassonomia = chiaveTassonomia;
	}

	public String getCausali() {
		return causali;
	}

	public void setCausali(String causali) {
		this.causali = causali;
	}
    //fine LP PG200360

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.getUser().getCompany().getCompanyCode());
		arg.setString(2, this.getUser().getUserCode());
		arg.setString(3, this.getTipoServizio().getCodiceTipologiaServizio());
		arg.setString(4, this.numCC);
		arg.setString(5, this.intCC);
		arg.setString(6, this.tipoDoc);
		arg.setString(7, this.flagRangeAbi);
		arg.setString(8, this.mailDest);
		arg.setString(9, this.mailCCNascosta);
		arg.setString(10, this.mailMitt);
		arg.setString(11, this.desMitt);
		arg.setString(12, this.flagAll);
		arg.setString(13, this.codiceSia);
		arg.setString(14, this.codiceIban);
		arg.setString(15, this.getBollettino().getTipoBollettino());
		arg.setString(16, this.funzPag);
		arg.setString(17, this.flagFunzPagProt);
		arg.setString(18, this.urlWeb);
		arg.setString(19, this.flagTpPag);
		arg.setString(20, this.operatorCode);
		arg.setString(21, this.flagIntegrazioneSeda);
		arg.setString(22, this.codiceUtenteSeda);
		arg.setString(23, this.secondoCodiceIban);	//PG150180_001 GG
		arg.setString(24, this.flagNotificaPagamento); //PG170150 CT
		arg.setString(25, this.urlServizioWebNotificaPagamento); //PG170150 CT
		//inizio LP PG200060
		//arg.setString(26, this.flagPagoPA);
		arg.setString(26, (this.flagPagoPA != null ? this.flagPagoPA : ""));
		//fine LP PG200060
		//inizio LP PG180290
		if(tipoDovuto != null)
			arg.setString(27, tipoDovuto);
		else
			arg.setNull(27, Types.VARCHAR);
        //fine LP PG180290
	    //inizio LP PG200360
		if(chiaveTassonomia != null)
			arg.setString(28, chiaveTassonomia);
		else
			arg.setNull(28, Types.VARCHAR);
		if(causali != null)
			arg.setString(29, causali);
		else
			arg.setNull(29, Types.VARCHAR);
	    //fine LP PG200360
		//inizio SB PG210140
			if(articolo != null)
				arg.setString(30, articolo);
			else
				arg.setNull(30, Types.VARCHAR);
			if(codiceContabilita != null)
				arg.setString(31, codiceContabilita);
			else
				arg.setNull(31, Types.VARCHAR);
			if(capitolo != null)
				arg.setString(32, capitolo);
			else
				arg.setNull(32, Types.VARCHAR);
			if(annoCompetenza != null)
				arg.setString(33, annoCompetenza);
			else
				arg.setNull(33, Types.VARCHAR);
		//fine SB PG210140
	}
	
	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.getUser().getCompany().getCompanyCode());
		arg.setString(2, this.getUser().getUserCode());
		arg.setString(3, this.getTipoServizio().getCodiceTipologiaServizio());
		arg.setString(4, this.numCC);
		arg.setString(5, this.intCC);
		arg.setString(6, this.tipoDoc);
		arg.setString(7, this.flagRangeAbi);
		arg.setString(8, this.mailDest);
		arg.setString(9, this.mailCCNascosta);
		arg.setString(10, this.mailMitt);
		arg.setString(11, this.desMitt);
		arg.setString(12, this.flagAll);
		arg.setString(13, this.codiceSia);
		arg.setString(14, this.codiceIban);
		arg.setString(15, this.getBollettino().getTipoBollettino());
		arg.setString(16, this.funzPag);
		arg.setString(17, this.flagFunzPagProt);
		arg.setString(18, this.urlWeb);
		arg.setString(19, this.flagTpPag);
		arg.setString(20, this.operatorCode);
		arg.setString(21, this.flagIntegrazioneSeda);
		arg.setString(22, this.codiceUtenteSeda);
		arg.setString(22, this.secondoCodiceIban);	//PG150180_001 GG
		arg.setString(24, this.flagNotificaPagamento); //PG170150 CT
		arg.setString(25, this.urlServizioWebNotificaPagamento); //PG170150 CT
		//inizio LP PG200060
		//arg.setString(26, this.flagPagoPA);
		arg.setString(26, (this.flagPagoPA != null ? this.flagPagoPA : ""));
		//fine LP PG200060
		//inizio LP PG180290
		if(tipoDovuto != null)
			arg.setString(27, tipoDovuto);
		else
			arg.setNull(27, Types.VARCHAR);
        //fine LP PG180290
	    //inizio LP PG200360
		if(chiaveTassonomia != null)
			arg.setString(28, chiaveTassonomia);
		else
			arg.setNull(28, Types.VARCHAR);
		if(causali != null)
			arg.setString(29, causali);
		else
			arg.setNull(29, Types.VARCHAR);
	    //fine LP PG200360
		//inizio SB PG210140
				if(articolo != null)
					arg.setString(30, articolo);
				else
					arg.setNull(30, Types.VARCHAR);
				if(codiceContabilita != null)
					arg.setString(31, codiceContabilita);
				else
					arg.setNull(31, Types.VARCHAR);
				if(capitolo != null)
					arg.setString(32, capitolo);
				else
					arg.setNull(32, Types.VARCHAR);
				if(annoCompetenza != null)
					arg.setString(33, annoCompetenza);
				else
					arg.setNull(33, Types.VARCHAR);
				//fine SB PG210140
	}
	
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {

	}

	public String getCapitolo() {
		return capitolo;
	}

	public String getArticolo() {
		return articolo;
	}

	public String getCodiceContabilita() {
		return codiceContabilita;
	}

	public String getAnnoCompetenza() {
		return annoCompetenza;
	}

	public void setCapitolo(String capitolo) {
		this.capitolo = capitolo;
	}

	public void setArticolo(String articolo) {
		this.articolo = articolo;
	}

	public void setCodiceContabilita(String codiceContabilita) {
		this.codiceContabilita = codiceContabilita;
	}

	public void setAnnoCompetenza(String annoCompetenza) {
		this.annoCompetenza = annoCompetenza;
	}
}