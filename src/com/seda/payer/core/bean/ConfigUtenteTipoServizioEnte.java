package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.payer.commons.bean.Lifecycle;

public class ConfigUtenteTipoServizioEnte extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
    private Ente ente;
    private TipologiaServizio tipoServizio;
    private Bollettino bol;
    private String numCc;//CFE_NCFENCCP
    private String intestatarioCc;//CFE_DCFEDINT
    private String tipoDoc;//CFE_TCFETDOC
    private String flagConRange;//CFE_FCFEFCTR
    private String emailDest;//CFE_ECFEEDES
    private String emailCcNascosta;//CFE_ECFEECCN
    private String emailMitt;//CFE_ECFEEMIT
    private String desMittente;//CFE_DCFEDMIT
    private String flagAllegato;//CFE_FCFEFALL
    private String codiceSia;//CFE_CCFECSIA
    private String codiceIban;//CFE_CCFEIBAN
    private String secondoCodiceIban;	//PG150180_001 GG
    private String funzionePag;//CFE_DCFEFUNZ
    private String flagPagProtetta;//CFE_FCFEPROT
    private String urlServWeb;//CFE_DCFESRVC
    private String flagTipoPag;//CFE_FCFEPAGA
    private String opertoreUltimoAggiornamento;//CFE_CCFECOPE
    private String flagIntegrazioneSeda;
    private String codiceUtenteSeda;
    private String flagNotificaPagamento; //CFE_FCFENOTI //PG170150 CT
	private String urlServizioWebNotificaPagamento; //PG170150 CT
    private String flagPagoPA;	//CFE_FCFEFPPA
    //inizio LP PG180290
    private String tipoDovuto;
    //fine LP PG180290
    //inizio SD PG200180
    private String flagStampaAvvisoPagoPa; // CFE_FCFEFSTA
    private String giorniStampaAvvisoPagoPa; // CFE_NCFEGSTA
    private String autorizzazioneStampaAvvisoPagoPa; // CFE_CCFEAUTO
    private String cbillStampaAvvisoPagoPa; // CFE_CCFECBIL
    private String infoEnteStampaAvvisoPagoPa; // CFE_CCFEINEN
    //fine SD PG200180
    //inizio LP PG200360
    private String chiaveTassonomia;
    private String causali;
    //fine LP PG200360
    //inizio SB PG210140
    private String articolo;
    private String codiceContabilita;
    private String capitolo;
    private String annoCompetenza;
    //fine SB PG210140
    private String dataDicituraPagamento; //SB PG210170
    
    public ConfigUtenteTipoServizioEnte() { 
    	ente = new Ente();
    	tipoServizio = new TipologiaServizio();
    	bol = new Bollettino();
    	
    }

    public ConfigUtenteTipoServizioEnte(ResultSet data) throws SQLException {
    	if (data == null)
    		return;
        ente = new Ente(); {
        	ente.getUser().getCompany().setCompanyCode(data.getString("CFE_CSOCCSOC"));
        	ente.getUser().setUserCode(data.getString("CFE_CUTECUTE"));
        	ente.getAnagEnte().setChiaveEnte(data.getString("CFE_KANEKENT")); 	
        }
        tipoServizio = new TipologiaServizio(); {
        	tipoServizio.setCodiceTipologiaServizio(data.getString("CFE_CTSECTSE"));
        } 
        bol = new Bollettino();{
        	bol.setTipoBolletino(data.getString("CFE_TBOLTBOL"));
        }
        
        numCc = data.getString("CFE_NCFENCCP");
        intestatarioCc = data.getString("CFE_DCFEDINT");
        tipoDoc = data.getString("CFE_TCFETDOC");
        flagConRange = data.getString("CFE_FCFEFCTR");
        emailDest = data.getString("CFE_ECFEEDES");
        emailCcNascosta = data.getString("CFE_ECFEECCN");
        emailMitt= data.getString("CFE_ECFEEMIT");
        desMittente= data.getString("CFE_DCFEDMIT");
        flagAllegato= data.getString("CFE_FCFEFALL");
        codiceSia= data.getString("CFE_CCFECSIA");
        codiceIban= data.getString("CFE_CCFEIBAN");
        funzionePag= data.getString("CFE_DCFEFUNZ");
        flagPagProtetta= data.getString("CFE_FCFEPROT");
        urlServWeb= data.getString("CFE_DCFESRVC");
        flagTipoPag= data.getString("CFE_FCFEPAGA");
        opertoreUltimoAggiornamento= data.getString("CFE_CCFECOPE");
        flagIntegrazioneSeda = data.getString("CFE_FCFESEDA");
        codiceUtenteSeda = data.getString("CFE_CCFESEDA");
        secondoCodiceIban=data.getString("CFE_CCFEIBA2");	//PG150180_001 GG
        flagNotificaPagamento = data.getString("CFE_FCFENOTI"); //PG170150 CT
        urlServizioWebNotificaPagamento = data.getString("CFE_DCFEUNOT"); //PG170150 CT
        flagPagoPA = data.getString("CFE_FCFEFPPA");
        //inizio LP PG180290
        if(data.getString("CFE_CCFEMYTD") != null)
        	tipoDovuto = data.getString("CFE_CCFEMYTD").trim();
        //fine LP PG180290
        //inizio SD PG200180
    	flagStampaAvvisoPagoPa = data.getString("CFE_FCFEFSTA");
    	giorniStampaAvvisoPagoPa = data.getString("CFE_NCFEGSTA");
    	
    	autorizzazioneStampaAvvisoPagoPa = data.getString("CFE_CCFEAUTO");
    	cbillStampaAvvisoPagoPa = data.getString("CFE_CCFECBIL");
    	infoEnteStampaAvvisoPagoPa = data.getString("CFE_CCFEINEN");
        //fine SD PG200180
    	  //inizio LP PG200360
        chiaveTassonomia = data.getString("CFE_CTASDSPI");
        causali = data.getString("CFE_CCFECAUS");
        //fine LP PG200360
        //inizio SB PG210140
        try { articolo = data.getString("CFE_DCFEDART"); }catch (Exception e) {articolo = "";}
        try { codiceContabilita = data.getString("CFE_CCFECCON"); }catch (Exception e) {codiceContabilita = "";}
        try { capitolo = data.getString("CFE_DCFECAPT"); }catch (Exception e) {capitolo = "";}
        try { annoCompetenza = data.getString("CFE_DCFEANNC"); }catch (Exception e) {annoCompetenza = "";}
        //fine SB PG210140
        try { dataDicituraPagamento = data.getString("CFE_DCFESCAP"); }catch (Exception e) {dataDicituraPagamento = "";} //SB PG210170
    }
    
    
	public String getFlagTipoPag() {
		return flagTipoPag;
	}

	public void setFlagTipoPag(String flagTipoPag) {
		this.flagTipoPag = flagTipoPag;
	}

	public Bollettino getBol() {
		return bol;
	}

	public void setBol(Bollettino bol) {
		this.bol = bol;
	}

	public String getNumCc() {
		return numCc;
	}

	public void setNumCc(String numCc) {
		this.numCc = numCc;
	}

	public String getIntestatarioCc() {
		return intestatarioCc;
	}

	public void setIntestatarioCc(String intestatarioCc) {
		this.intestatarioCc = intestatarioCc;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getFlagConRange() {
		return flagConRange;
	}

	public void setFlagConRange(String flagConRange) {
		this.flagConRange = flagConRange;
	}

	public String getEmailDest() {
		return emailDest;
	}

	public void setEmailDest(String emailDest) {
		this.emailDest = emailDest;
	}

	public String getEmailCcNascosta() {
		return emailCcNascosta;
	}

	public void setEmailCcNascosta(String emailCcNascosta) {
		this.emailCcNascosta = emailCcNascosta;
	}

	public String getEmailMitt() {
		return emailMitt;
	}

	public void setEmailMitt(String emailMitt) {
		this.emailMitt = emailMitt;
	}

	public String getDesMittente() {
		return desMittente;
	}

	public void setDesMittente(String desMittente) {
		this.desMittente = desMittente;
	}

	public String getFlagAllegato() {
		return flagAllegato;
	}

	public void setFlagAllegato(String flagAllegato) {
		this.flagAllegato = flagAllegato;
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

	public String getFunzionePag() {
		return funzionePag;
	}

	public void setFunzionePag(String funzionePag) {
		this.funzionePag = funzionePag;
	}

	public String getFlagPagProtetta() {
		return flagPagProtetta;
	}

	public void setFlagPagProtetta(String flagPagProtetta) {
		this.flagPagProtetta = flagPagProtetta;
	}

	public String getUrlServWeb() {
		return urlServWeb;
	}

	public void setUrlServWeb(String urlServWeb) {
		this.urlServWeb = urlServWeb;
	}

	public String getOpertoreUltimoAggiornamento() {
		return opertoreUltimoAggiornamento;
	}

	public void setOpertoreUltimoAggiornamento(
			String opertoreUltimoAggiornamento) {
		this.opertoreUltimoAggiornamento = opertoreUltimoAggiornamento;
	}

	public Ente getEnte() {
		return ente;
	}

	public void setEnte(Ente ente) {
		this.ente = ente;
	}

	public TipologiaServizio getTipoServizio() {
		return tipoServizio;
	}

	public void setTipoServizio(TipologiaServizio tipoServizio) {
		this.tipoServizio = tipoServizio;
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
	
	public String getFlagPagoPA() {
		return flagPagoPA;
	}

	public void setFlagPagoPA(String flagPagoPA) {
		this.flagPagoPA = flagPagoPA;
	}

	public void setUrlServizioWebNotificaPagamento(
			String urlServizioWebNotificaPagamento) {
		this.urlServizioWebNotificaPagamento = urlServizioWebNotificaPagamento;
	}

	public String getUrlServizioWebNotificaPagamento() {
		return urlServizioWebNotificaPagamento;
	}

    //inizio LP PG180290
	public String getTipoDovuto() {
		return tipoDovuto;
	}

	public void setTipoDovuto(String tipoDovuto) {
		this.tipoDovuto = tipoDovuto;
	}
	//fine LP PG180290
	
	//inizio SD PG200180
	public String getFlagStampaAvvisoPagoPa() {
		return flagStampaAvvisoPagoPa;
	}

	public void setFlagStampaAvvisoPagoPa(String flagStampaAvvisoPagoPa) {
		this.flagStampaAvvisoPagoPa = flagStampaAvvisoPagoPa;
	}
	
	public String getGiorniStampaAvvisoPagoPa() {
		return giorniStampaAvvisoPagoPa;
	}

	public void setGiorniStampaAvvisoPagoPa(String giorniStampaAvvisoPagoPa) {
		this.giorniStampaAvvisoPagoPa = giorniStampaAvvisoPagoPa;
	}
	
	public String getAutorizzazioneStampaAvvisoPagoPa() {
		return autorizzazioneStampaAvvisoPagoPa;
	}

	public void setAutorizzazioneStampaAvvisoPagoPa(String autorizzazioneStampaAvvisoPagoPa) {
		this.autorizzazioneStampaAvvisoPagoPa = autorizzazioneStampaAvvisoPagoPa;
	}

	public String getCbillStampaAvvisoPagoPa() {
		return cbillStampaAvvisoPagoPa;
	}

	public void setCbillStampaAvvisoPagoPa(String cbillStampaAvvisoPagoPa) {
		this.cbillStampaAvvisoPagoPa = cbillStampaAvvisoPagoPa;
	}

	public String getInfoEnteStampaAvvisoPagoPa() {
		return infoEnteStampaAvvisoPagoPa;
	}

	public void setInfoEnteStampaAvvisoPagoPa(String infoEnteStampaAvvisoPagoPa) {
		this.infoEnteStampaAvvisoPagoPa = infoEnteStampaAvvisoPagoPa;
	}
    //fine SD PG200180

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

	public String getArticolo() {
		return articolo;
	}

	public String getCodiceContabilita() {
		return codiceContabilita;
	}

	public String getCapitolo() {
		return capitolo;
	}

	public String getAnnoCompetenza() {
		return annoCompetenza;
	}

	public void setArticolo(String articolo) {
		this.articolo = articolo;
	}

	public void setCodiceContabilita(String codiceContabilita) {
		this.codiceContabilita = codiceContabilita;
	}

	public void setCapitolo(String capitolo) {
		this.capitolo = capitolo;
	}

	public void setAnnoCompetenza(String annoCompetenza) {
		this.annoCompetenza = annoCompetenza;
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.ente.getUser().getCompany().getCompanyCode());
		arg.setString(2, this.ente.getUser().getUserCode());
		arg.setString(3, this.ente.getAnagEnte().getChiaveEnte());		
		arg.setString(4, this.tipoServizio.getCodiceTipologiaServizio());
		arg.setString(5, this.getNumCc());
		arg.setString(6, this.getIntestatarioCc());
		arg.setString(7, this.getTipoDoc());
		arg.setString(8, this.getFlagConRange());
		arg.setString(9, this.getEmailDest());
		arg.setString(10, this.getEmailCcNascosta());
		arg.setString(11, this.getEmailMitt());
		arg.setString(12, this.getDesMittente());
		arg.setString(13, this.getFlagAllegato());
		arg.setString(14, this.getCodiceSia());
		arg.setString(15, this.getCodiceIban());
		arg.setString(16, this.bol.getTipoBollettino());
		arg.setString(17, this.getFunzionePag());
		arg.setString(18, this.getFlagPagProtetta());
		arg.setString(19, this.getUrlServWeb());
		arg.setString(20, this.getFlagTipoPag());
		arg.setString(21, this.getOpertoreUltimoAggiornamento());
		arg.setString(22, this.flagIntegrazioneSeda);
		arg.setString(23, this.codiceUtenteSeda);
		arg.setString(24, this.secondoCodiceIban);	//PG150180_001 GG		arg.setString(25, this.flagNotificaPagamento); //PG170150 CT
		arg.setString(26, this.urlServizioWebNotificaPagamento); //PG170150 CT
        //inizio LP PG200060
		//arg.setString(27, this.flagPagoPA);
		arg.setString(27, (this.flagPagoPA != null ? this.flagPagoPA : ""));
        //fine LP PG200060
		//inizio LP PG180290
		if(tipoDovuto != null)
			arg.setString(28, tipoDovuto);
		else
			arg.setNull(28, Types.VARCHAR);
        //fine LP PG180290
		//inizio SD PG200180
		if(this.flagStampaAvvisoPagoPa != null)
			arg.setString(29, this.flagStampaAvvisoPagoPa);
		else
			arg.setString(29, "N");
		if(this.giorniStampaAvvisoPagoPa != null)
			arg.setString(30, this.giorniStampaAvvisoPagoPa);
		else
			arg.setString(30, "30");
		if(autorizzazioneStampaAvvisoPagoPa != null)
			arg.setString(31, autorizzazioneStampaAvvisoPagoPa);
		else
			arg.setNull(31, Types.VARCHAR);
		if(cbillStampaAvvisoPagoPa != null)
			arg.setString(32, cbillStampaAvvisoPagoPa);
		else
			arg.setNull(32, Types.VARCHAR);
		if(infoEnteStampaAvvisoPagoPa != null)
			arg.setString(33, infoEnteStampaAvvisoPagoPa);
		else
			arg.setNull(33, Types.VARCHAR);
        //fine SD PG200180
	    //inizio LP PG200360
		if(chiaveTassonomia != null)
			arg.setString(34, chiaveTassonomia);
		else
			arg.setNull(34, Types.VARCHAR);
		if(causali != null)
			arg.setString(35, causali);
		else
			arg.setNull(35, Types.VARCHAR);
	    //fine LP PG200360
		
		//inizio SB PG2100140
			if(articolo != null)
				arg.setString(36, articolo);
			else
				arg.setNull(36, Types.VARCHAR);
			if(codiceContabilita != null)
				arg.setString(37, codiceContabilita);
			else
				arg.setNull(37, Types.VARCHAR);
			if(capitolo != null)
				arg.setString(38, capitolo);
			else
				arg.setNull(38, Types.VARCHAR);
			if(annoCompetenza != null)
				arg.setString(39, annoCompetenza);
			else
				arg.setNull(39, Types.VARCHAR);
		//fine SB PG2100140
			//inizio SB PG210170
			if(dataDicituraPagamento != null)
				arg.setString(40, dataDicituraPagamento);
			else
				arg.setNull(40, Types.VARCHAR);
			//fine SB PG210170
	}
	
	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.ente.getUser().getCompany().getCompanyCode());
		arg.setString(2, this.ente.getUser().getUserCode());
		arg.setString(3, this.ente.getAnagEnte().getChiaveEnte());		
		arg.setString(4, this.tipoServizio.getCodiceTipologiaServizio());
		arg.setString(5, this.getNumCc());
		arg.setString(6, this.getIntestatarioCc());
		arg.setString(7, this.getTipoDoc());
		arg.setString(8, this.getFlagConRange());
		arg.setString(9, this.getEmailDest());
		arg.setString(10, this.getEmailCcNascosta());
		arg.setString(11, this.getEmailMitt());
		arg.setString(12, this.getDesMittente());
		arg.setString(13, this.getFlagAllegato());
		arg.setString(14, this.getCodiceSia());
		arg.setString(15, this.getCodiceIban());
		arg.setString(16, this.bol.getTipoBollettino());
		arg.setString(17, this.getFunzionePag());
		arg.setString(18, this.getFlagPagProtetta());
		arg.setString(19, this.getUrlServWeb());
		arg.setString(20, this.getFlagTipoPag());
		arg.setString(21, this.getOpertoreUltimoAggiornamento());
		arg.setString(22, this.flagIntegrazioneSeda);
		arg.setString(23, this.codiceUtenteSeda);
		arg.setString(24, this.secondoCodiceIban);	//PG150180_001 GG		arg.setString(25, this.flagNotificaPagamento); //PG170150 CT
		arg.setString(26, this.urlServizioWebNotificaPagamento); //PG170150 CT
        //inizio LP PG200060
		//arg.setString(27, this.flagPagoPA);
		arg.setString(27, (this.flagPagoPA != null ? this.flagPagoPA : ""));
        //fine LP PG200060
		//inizio LP PG180290
		if(tipoDovuto != null)
			arg.setString(28, tipoDovuto);
		else
			arg.setNull(28, Types.VARCHAR);
        //fine LP PG180290
		//inizio SD PG200180
		if(flagStampaAvvisoPagoPa != null)
			arg.setString(29, flagStampaAvvisoPagoPa);
		else
			arg.setString(29, "N");
		if(giorniStampaAvvisoPagoPa != null)
			arg.setString(30, giorniStampaAvvisoPagoPa);
		else
			arg.setString(30, "30");
		if(autorizzazioneStampaAvvisoPagoPa != null)
			arg.setString(31, autorizzazioneStampaAvvisoPagoPa);
		else
			arg.setNull(31, Types.VARCHAR);
		if(cbillStampaAvvisoPagoPa != null)
			arg.setString(32, cbillStampaAvvisoPagoPa);
		else
			arg.setNull(32, Types.VARCHAR);
		if(infoEnteStampaAvvisoPagoPa != null)
			arg.setString(33, infoEnteStampaAvvisoPagoPa);
		else
			arg.setNull(33, Types.VARCHAR);
        //fine SD PG200180
	    //inizio LP PG200360
		if(chiaveTassonomia != null)
			arg.setString(34, chiaveTassonomia);
		else
			arg.setNull(34, Types.VARCHAR);
		if(causali != null)
			arg.setString(35, causali);
		else
			arg.setNull(35, Types.VARCHAR);
	    //fine LP PG200360
	}
	
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {

	}

	public String getDataDicituraPagamento() {
		return dataDicituraPagamento;
	}

	public void setDataDicituraPagamento(String dataDicituraPagamento) {
		this.dataDicituraPagamento = dataDicituraPagamento;
	}
	
}
