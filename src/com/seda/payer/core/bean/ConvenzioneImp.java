package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.Date;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.payer.commons.bean.Lifecycle;

public class ConvenzioneImp extends Lifecycle implements Serializable {

		private static final long serialVersionUID = 1L;

/*
codSocieta
codProvincia
codUtente
codImpositore
codCanale
codGateway
aggregPagamenti (10,15 o 30) CEN_CCENPAGA DECIMAL(2 , 0) NOT NULL,
giorniLatenza	     >0	CEN_NCENGLAT DECIMAL(3 , 0) NOT NULL,
indFesta     (A ANTICIPO, D POSTICIPO, U DATA INVARIATA) CEN_FCENFEST CHAR(1) NOT NULL,
limMaxGiorni	     >0	CEN_NCENNGIO DECIMAL(3 , 0) NOT NULL,
compPercRisc     CEN_NCENPERC DECIMAL(5 , 2) NOT NULL,
compQuotaFissa	     CEN_NCENQUOF DECIMAL(15 , 2) NOT NULL,
tipoQuotaFissa    (T TRANSAZIONE, P Pagamento)		CEN_FCENQUOF CHAR(1) NOT NULL,
UtenteAgg		CEN_CCENCOPE VARCHAR(50) NOT NULL
dataValidita 		
 */
		
		private User user;					 //contiene la società
	    private AnagEnte anagImpositore;	 //contiene la provincia
	    private GatewayPagamento gatewayPag; //contiene il canale pagamento

	    private java.math.BigDecimal aggregPagamenti;
	    private java.math.BigDecimal giorniLatenza;
	    private String indFesta;
	    private java.math.BigDecimal limMaxGiorni;
	    private java.math.BigDecimal compPercRisc;
	    private java.math.BigDecimal compQuotaFissa;
	    private String tipoQuotaFissa;

	    private String dataValidita;
	    private String codiceOperatore;

	    public ConvenzioneImp() { 
	    	user = new User();
	    	anagImpositore = new AnagEnte();
	    	gatewayPag = new GatewayPagamento();
	    }

	    public ConvenzioneImp(String companyCode, String codProvincia, String userCode, 
	    		           String codImpositore, String codCanale, 
	    		           String codGateway, 
	    		           String dataValidita) { 
	    	user = new User();
	    	user.getCompany().setCompanyCode(companyCode);
	    	user.setUserCode(userCode);
	    	anagImpositore = new AnagEnte();
	    	anagImpositore.setChiaveEnte(codImpositore);
	    	anagImpositore.getAnagProvCom().setCodiceBelfiore(codProvincia);
	    	gatewayPag = new GatewayPagamento();
	    	gatewayPag.getCanale().setChiaveCanalePagamento(codCanale);
	    	gatewayPag.setChiaveGateway(codGateway);

	    	this.dataValidita = dataValidita;
	    }

	    public ConvenzioneImp(ResultSet data) throws SQLException {
	    	if (data == null)
	    		return;

/*
		CEN_CSOCCSOC CHAR(5) NOT NULL,
		CEN_CUTECUTE CHAR(5) NOT NULL,
		CEN_KANEKENT_IMP CHAR(10) NOT NULL,
		CEN_KGTWKGTW CHAR(10) NOT NULL,
		CEN_CCENPAGA DECIMAL(2 , 0) NOT NULL,
		
		CEN_NCENGLAT DECIMAL(3 , 0) NOT NULL,
		CEN_FCENFEST CHAR(1) NOT NULL,
		CEN_NCENNGIO DECIMAL(3 , 0) NOT NULL,
		CEN_NCENPERC DECIMAL(5 , 2) NOT NULL,
		CEN_NCENQUOF DECIMAL(15 , 2) NOT NULL,
		CEN_FCENQUOF CHAR(1) NOT NULL,
		CEN_GCENGAGG TIMESTAMP NOT NULL,
		CEN_CCENCOPE VARCHAR(50) NOT NULL	    	
 */
	    	user = new User();
	    	user.getCompany().setCompanyCode(data.getString("CEN_CSOCCSOC"));
	    	user.setUserCode(data.getString("CEN_CUTECUTE"));
	    	anagImpositore = new AnagEnte();
	    	anagImpositore.setChiaveEnte(data.getString("CEN_KANEKENT_IMP"));
	    	gatewayPag = new GatewayPagamento();
            gatewayPag.setChiaveGateway(data.getString("CEN_KGTWKGTW"));
            gatewayPag.getCanale().setChiaveCanalePagamento("CEN_CCENPAGA");

            aggregPagamenti = data.getBigDecimal("CEN_CCENPAGA");
            giorniLatenza = data.getBigDecimal("CEN_NCENGLAT");
            indFesta = data.getString("CEN_FCENFEST");				//(A ANTICIPO, D POSTICIPO, U DATA INVARIATA)
            limMaxGiorni = data.getBigDecimal("CEN_NCENNGIO");
            compPercRisc = data.getBigDecimal("CEN_NCENPERC");
            compQuotaFissa = data.getBigDecimal("CEN_NCENQUOF");  //(T transazione, P pagamento)
            tipoQuotaFissa = data.getString("CEN_FCENQUOF");
	    	dataValidita = data.getString("CEN_GCENGDAT");

	    	codiceOperatore = data.getString("CEN_CCENCOPE");

	    }
	    
		public void onSave(CallableStatement arg) throws SQLException {

			arg.setString(1, this.user.getCompany().getCompanyCode());		//CEN_CSOCCSOC
			arg.setString(2, this.user.getUserCode());						//CEN_CUTECUTE
			arg.setString(3, this.anagImpositore.getChiaveEnte());			//CEN_KANEKENT_IMP
			arg.setString(4, this.gatewayPag.getChiaveGateway());			//CEN_KGTWKGTW
			arg.setString(5, this.dataValidita);							//CEN_GCENGDAT
			arg.setBigDecimal(6, this.aggregPagamenti);						//CEN_CCENPAGA
			arg.setBigDecimal(7, this.giorniLatenza);						//CEN_NCENGLAT
			arg.setString(8, this.indFesta);									// CEN_FCENFEST
			arg.setBigDecimal(9, this.limMaxGiorni);						//CEN_NCENNGIO
			arg.setBigDecimal(10, this.compPercRisc);						//CEN_NCENPERC
			arg.setBigDecimal(11, this.compQuotaFissa);						//CEN_NCENQUOF
			arg.setString(12, this.tipoQuotaFissa);							//CEN_FCENQUOF
			
			arg.setString(13, this.codiceOperatore);		//CEN_CCENCOPE
			arg.registerOutParameter(14, Types.INTEGER);
		}
		
		public void onUpdate(CallableStatement arg) throws SQLException {

		}
		
		public void onLoad(CallableStatement arg) throws SQLException {

		}
		
		public void onDelete(CallableStatement arg) throws SQLException {
			
			arg.setString(1, this.user.getCompany().getCompanyCode());		//CEN_CSOCCSOC
			arg.setString(2, this.user.getUserCode());						//CEN_CUTECUTE
			arg.setString(3, this.anagImpositore.getChiaveEnte());			//CEN_KANEKENT_IMP
			arg.setString(4, this.gatewayPag.getChiaveGateway());			//CEN_KGTWKGTW
			arg.setString(5, this.dataValidita);							//CEN_GCENGDAT
			arg.registerOutParameter(6, Types.INTEGER);
			
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public AnagEnte getAnagImpositore() {
			return anagImpositore;
		}

		public void setAnagImpositore(AnagEnte anagImpositore) {
			this.anagImpositore = anagImpositore;
		}

		public GatewayPagamento getGatewayPag() {
			return gatewayPag;
		}

		public void setGatewayPag(GatewayPagamento gatewayPag) {
			this.gatewayPag = gatewayPag;
		}

		public java.math.BigDecimal getAggregPagamenti() {
			return aggregPagamenti;
		}

		public void setAggregPagamenti(java.math.BigDecimal aggregPagamenti) {
			this.aggregPagamenti = aggregPagamenti;
		}

		public java.math.BigDecimal getGiorniLatenza() {
			return giorniLatenza;
		}

		public void setGiorniLatenza(java.math.BigDecimal giorniLatenza) {
			this.giorniLatenza = giorniLatenza;
		}

		public String getIndFesta() {
			return indFesta;
		}

		public void setIndFesta(String indFesta) {
			this.indFesta = indFesta;
		}

		public java.math.BigDecimal getLimMaxGiorni() {
			return limMaxGiorni;
		}

		public void setLimMaxGiorni(java.math.BigDecimal limMaxGiorni) {
			this.limMaxGiorni = limMaxGiorni;
		}

		public java.math.BigDecimal getCompPercRisc() {
			return compPercRisc;
		}

		public void setCompPercRisc(java.math.BigDecimal compPercRisc) {
			this.compPercRisc = compPercRisc;
		}

		public java.math.BigDecimal getCompQuotaFissa() {
			return compQuotaFissa;
		}

		public void setCompQuotaFissa(java.math.BigDecimal compQuotaFissa) {
			this.compQuotaFissa = compQuotaFissa;
		}

		public String getTipoQuotaFissa() {
			return tipoQuotaFissa;
		}

		public void setTipoQuotaFissa(String tipoQuotaFissa) {
			this.tipoQuotaFissa = tipoQuotaFissa;
		}

		public String getDataValidita() {
			return dataValidita;
		}

		public void setDataValidita(String dataValidita) {
			this.dataValidita = dataValidita;
		}

		public String getCodiceOperatore() {
			return codiceOperatore;
		}

		public void setCodiceOperatore(String codiceOperatore) {
			this.codiceOperatore = codiceOperatore;
		}



}