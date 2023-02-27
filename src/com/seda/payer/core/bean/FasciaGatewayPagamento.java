package com.seda.payer.core.bean;
 
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.seda.payer.commons.bean.Lifecycle;

public class FasciaGatewayPagamento extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
    private java.lang.String chiaveFascia;
    private java.lang.String tipoFascia;
    private java.math.BigDecimal importoMinimoDa;
    private java.math.BigDecimal importoMassimoDa;
    private java.math.BigDecimal importoFisso;
    private java.math.BigDecimal percentuale;
    private java.lang.String codiceOperatore;
    private GatewayPagamento gateway;

    public FasciaGatewayPagamento() { 
    	gateway = new GatewayPagamento();
    }

    public FasciaGatewayPagamento(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

    	chiaveFascia = data.getString("FSC_KFSCKFSC");
    	tipoFascia = data.getString("FSC_TFSCFASC");
    	importoMinimoDa = data.getBigDecimal("FSC_IFSCFSCD");
    	importoMassimoDa = data.getBigDecimal("FSC_IFSCFSCA");
    	importoFisso = data.getBigDecimal("FSC_IFSCFISS");
    	percentuale = data.getBigDecimal("FSC_NFSCPERC");
        codiceOperatore = data.getString("FSC_CFSCCOPE");
        gateway = new GatewayPagamento(); {
        	gateway.setChiaveGateway(data.getString("FSC_KGTWKGTW"));
        	gateway.setDescrizioneGateway(data.getString("GATEWAY"));
        }
    }

	public java.lang.String getChiaveFascia() {
		return chiaveFascia;
	}

	public void setChiaveFascia(java.lang.String chiaveFascia) {
		this.chiaveFascia = chiaveFascia;
	}

	public java.lang.String getTipoFascia() {
		return tipoFascia;
	}

	public void setTipoFascia(java.lang.String tipoFascia) {
		this.tipoFascia = tipoFascia;
	}

	public java.math.BigDecimal getImportoMinimoDa() {
		return importoMinimoDa;
	}

	public void setImportoMinimoDa(java.math.BigDecimal importoMinimoDa) {
		this.importoMinimoDa = importoMinimoDa;
	}

	public java.math.BigDecimal getImportoMassimoDa() {
		return importoMassimoDa;
	}

	public void setImportoMassimoDa(java.math.BigDecimal importoMassimoDa) {
		this.importoMassimoDa = importoMassimoDa;
	}

	public java.math.BigDecimal getImportoFisso() {
		return importoFisso;
	}

	public void setImportoFisso(java.math.BigDecimal importoFisso) {
		this.importoFisso = importoFisso;
	}

	public java.math.BigDecimal getPercentuale() {
		return percentuale;
	}

	public void setPercentuale(java.math.BigDecimal percentuale) {
		this.percentuale = percentuale;
	}

	public java.lang.String getCodiceOperatore() {
		return codiceOperatore;
	}

	public void setCodiceOperatore(java.lang.String codiceOperatore) {
		this.codiceOperatore = codiceOperatore;
	}

	public GatewayPagamento getGateway() {
		return gateway;
	}

	public void setGateway(GatewayPagamento gateway) {
		this.gateway = gateway;
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.gateway.getChiaveGateway());
		arg.setString(2, this.getTipoFascia());
		arg.setBigDecimal(3, this.getImportoMinimoDa());
		arg.setBigDecimal(4, this.getImportoMassimoDa());
		arg.setBigDecimal(5, this.getImportoFisso());
		arg.setBigDecimal(6, this.getPercentuale());
		arg.setString(7, this.codiceOperatore);  // !! UTE_CUTECURL (OPERTORE ULTIMO AGGIORNAMENTO)
	}
	
	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.chiaveFascia);
		arg.setString(2, this.gateway.getChiaveGateway());
		arg.setString(3, this.getTipoFascia());
		arg.setBigDecimal(4, this.getImportoMinimoDa());
		arg.setBigDecimal(5, this.getImportoMassimoDa());
		arg.setBigDecimal(6, this.getImportoFisso());
		arg.setBigDecimal(7, this.getPercentuale());
		arg.setString(8, this.codiceOperatore);  // !! UTE_CUTECURL (OPERTORE ULTIMO AGGIORNAMENTO)
	}
	
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {

	}
}