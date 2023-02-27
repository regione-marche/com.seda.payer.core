package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seda.payer.commons.bean.Lifecycle;

public class ModuloIntegrazionePagamentiOneri extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
    
	private String chiaveTransazione;
    private String codiceEntePortaleEsterno;
    private String descrizioneEntePortaleEsterno;
    private String causaleOnere;
    private BigDecimal importoOnere;
    private BigDecimal importoContabileInIngresso;
    private BigDecimal importoContabileInUscita;
    private String codiceSocietaEnteBeneficiario;
    private String codiceUtenteEnteBeneficiario;
    private String chiaveEnteBeneficiario;
    private String codiceEnteBeneficiario;
    private String tipoUfficioEnteBeneficiario;
    private String codiceUfficioEnteBeneficiario;
    

    public ModuloIntegrazionePagamentiOneri() { 
    }

    public ModuloIntegrazionePagamentiOneri(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

    	chiaveTransazione = data.getString("ONE_KTRAKTRA");
    	codiceEntePortaleEsterno = data.getString("ONE_CONEENTE");
    	descrizioneEntePortaleEsterno = data.getString("ONE_DONEENTE");
    	importoOnere = data.getBigDecimal("ONE_IONEVALO");
    	causaleOnere = data.getString("ONE_DONECAUS");
    	importoContabileInIngresso = data.getBigDecimal("ONE_IONECOIN");
    	importoContabileInUscita = data.getBigDecimal("ONE_IONECOUS");
    	codiceSocietaEnteBeneficiario = data.getString("ONE_CSOCCSOC");
        codiceUtenteEnteBeneficiario = data.getString("ONE_CUTECUTE");
        chiaveEnteBeneficiario = data.getString("ONE_KANEKENT");
        codiceEnteBeneficiario = data.getString("ONE_CONECENT");
        tipoUfficioEnteBeneficiario = data.getString("ONE_TONETUFF");
        codiceUfficioEnteBeneficiario = data.getString("ONE_CONECUFF");
    }
    
    public ModuloIntegrazionePagamentiOneri(ResultSet data, boolean bReduced) throws SQLException {
    	if (data == null)
    		return;

    	codiceEntePortaleEsterno = data.getString("ONE_CONEENTE");
    	descrizioneEntePortaleEsterno = data.getString("ONE_DONEENTE");
    	importoOnere = data.getBigDecimal("ONE_IONEVALO");
    	importoContabileInIngresso = data.getBigDecimal("ONE_IONECOIN");
    	importoContabileInUscita = data.getBigDecimal("ONE_IONECOUS");
    }
    

	/**
	 * @return the chiaveTransazione
	 */
	public String getChiaveTransazione() {
		return chiaveTransazione;
	}

	/**
	 * @param chiaveTransazione the chiaveTransazione to set
	 */
	public void setChiaveTransazione(String chiaveTransazione) {
		this.chiaveTransazione = chiaveTransazione;
	}

	/**
	 * @return the codiceEntePortaleEsterno
	 */
	public String getCodiceEntePortaleEsterno() {
		return codiceEntePortaleEsterno;
	}

	/**
	 * @param codiceEntePortaleEsterno the codiceEntePortaleEsterno to set
	 */
	public void setCodiceEntePortaleEsterno(String codiceEntePortaleEsterno) {
		this.codiceEntePortaleEsterno = codiceEntePortaleEsterno;
	}

	/**
	 * @return the descrizioneEntePortaleEsterno
	 */
	public String getDescrizioneEntePortaleEsterno() {
		return descrizioneEntePortaleEsterno;
	}

	/**
	 * @param descrizioneEntePortaleEsterno the descrizioneEntePortaleEsterno to set
	 */
	public void setDescrizioneEntePortaleEsterno(
			String descrizioneEntePortaleEsterno) {
		this.descrizioneEntePortaleEsterno = descrizioneEntePortaleEsterno;
	}

	/**
	 * @return the causaleOnere
	 */
	public String getCausaleOnere() {
		return causaleOnere;
	}

	/**
	 * @param causaleOnere the causaleOnere to set
	 */
	public void setCausaleOnere(String causaleOnere) {
		this.causaleOnere = causaleOnere;
	}

	/**
	 * @return the importoOnere
	 */
	public BigDecimal getImportoOnere() {
		return importoOnere;
	}

	/**
	 * @param importoOnere the importoOnere to set
	 */
	public void setImportoOnere(BigDecimal importoOnere) {
		this.importoOnere = importoOnere;
	}

	/**
	 * @return the importoContabileInIngresso
	 */
	public BigDecimal getImportoContabileInIngresso() {
		return importoContabileInIngresso;
	}

	/**
	 * @param importoContabileInIngresso the importoContabileInIngresso to set
	 */
	public void setImportoContabileInIngresso(BigDecimal importoContabileInIngresso) {
		this.importoContabileInIngresso = importoContabileInIngresso;
	}

	/**
	 * @return the importoContabileInUscita
	 */
	public BigDecimal getImportoContabileInUscita() {
		return importoContabileInUscita;
	}

	/**
	 * @param importoContabileInUscita the importoContabileInUscita to set
	 */
	public void setImportoContabileInUscita(BigDecimal importoContabileInUscita) {
		this.importoContabileInUscita = importoContabileInUscita;
	}

	
	public void setCodiceSocietaEnteBeneficiario(
			String codiceSocietaEnteBeneficiario) {
		this.codiceSocietaEnteBeneficiario = codiceSocietaEnteBeneficiario;
	}

	public String getCodiceSocietaEnteBeneficiario() {
		return codiceSocietaEnteBeneficiario;
	}

	public void setCodiceUtenteEnteBeneficiario(
			String codiceUtenteEnteBeneficiario) {
		this.codiceUtenteEnteBeneficiario = codiceUtenteEnteBeneficiario;
	}

	public String getCodiceUtenteEnteBeneficiario() {
		return codiceUtenteEnteBeneficiario;
	}

	public void setChiaveEnteBeneficiario(String chiaveEnteBeneficiario) {
		this.chiaveEnteBeneficiario = chiaveEnteBeneficiario;
	}

	public String getChiaveEnteBeneficiario() {
		return chiaveEnteBeneficiario;
	}

	public void setCodiceEnteBeneficiario(String codiceEnteBeneficiario) {
		this.codiceEnteBeneficiario = codiceEnteBeneficiario;
	}

	public String getCodiceEnteBeneficiario() {
		return codiceEnteBeneficiario;
	}

	public void setTipoUfficioEnteBeneficiario(
			String tipoUfficioEnteBeneficiario) {
		this.tipoUfficioEnteBeneficiario = tipoUfficioEnteBeneficiario;
	}

	public String getTipoUfficioEnteBeneficiario() {
		return tipoUfficioEnteBeneficiario;
	}

	public void setCodiceUfficioEnteBeneficiario(
			String codiceUfficioEnteBeneficiario) {
		this.codiceUfficioEnteBeneficiario = codiceUfficioEnteBeneficiario;
	}

	public String getCodiceUfficioEnteBeneficiario() {
		return codiceUfficioEnteBeneficiario;
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
    	arg.setString(1, this.chiaveTransazione);
		arg.setString(2, this.codiceEntePortaleEsterno != null ? this.codiceEntePortaleEsterno : "");
		arg.setString(3, this.descrizioneEntePortaleEsterno != null ? this.descrizioneEntePortaleEsterno : "");
		arg.setBigDecimal(4, this.importoOnere != null ? this.importoOnere : new BigDecimal(0));
		arg.setString(5, this.causaleOnere != null ? this.causaleOnere : "");
		arg.setBigDecimal(6, this.importoContabileInIngresso != null ? this.importoContabileInIngresso : new BigDecimal(0));
		arg.setBigDecimal(7, this.importoContabileInUscita != null ? this.importoContabileInUscita : new BigDecimal(0));
		arg.setString(8, this.codiceSocietaEnteBeneficiario != null ? this.codiceSocietaEnteBeneficiario : "");
		arg.setString(9, this.codiceUtenteEnteBeneficiario != null ? this.codiceUtenteEnteBeneficiario : "");
		arg.setString(10, this.chiaveEnteBeneficiario != null ? this.chiaveEnteBeneficiario : "");
		arg.setString(11, this.codiceEnteBeneficiario != null ? this.codiceEnteBeneficiario : "");
		arg.setString(12, this.tipoUfficioEnteBeneficiario != null ? this.tipoUfficioEnteBeneficiario : "");
		arg.setString(13, this.codiceUfficioEnteBeneficiario != null ? this.codiceUfficioEnteBeneficiario : "");

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