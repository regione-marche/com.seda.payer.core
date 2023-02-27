package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import com.seda.payer.commons.bean.Lifecycle;

public class Tassonomia extends Lifecycle implements Serializable{

	private static final long serialVersionUID = 1L;
	public static final int VIEW_SCOPE = 0;
	public static final int OVERLAY_SCOPE = 1;
	private BigInteger chiaveTassonomia;
	private String codiceTipoEnteCreditore;
	private String tipoEnteCreditore;
	private String progressivoMacroAreaPerEnteCreditore;
	private String nomeMacroArea;
	private String descrizioneMacroArea;
	private String codiceTipologiaServizio;
	private String tipoServizio;
	private String descrizioneServizio;
	private String motivoGiuridicoDellaRiscossione;
	private String versioneTassonomia;
	private String datiSpecificiIncasso;
	private Timestamp dataInizioValidita;
	private Timestamp dataFineValidita;
	private Timestamp dataUltimoAggiornamento;
	private String operatoreUltimoAggiornamento;

	private String dataInizioValidita_da;
	private String dataFineValidita_a;

	/**
	 * Default constructor
	 */
	public Tassonomia() {
		super();
	}

	/**
	 * 
	 * @param data
	 * @throws SQLException
	 */
	public Tassonomia(ResultSet data) throws SQLException {
		if (data == null)
			return;

		this.chiaveTassonomia = BigInteger.valueOf(data.getInt("TAS_KTASPKEY"));
		this.codiceTipoEnteCreditore = data.getString("TAS_CTASCTEC");
		this.tipoEnteCreditore = data.getString("TAS_DTASDTEC");
		this.progressivoMacroAreaPerEnteCreditore = data.getString("TAS_CTASPMAE");
		this.nomeMacroArea = data.getString("TAS_DTASNMAE");
		this.descrizioneMacroArea = data.getString("TAS_DTASDMAE");
		this.codiceTipologiaServizio = data.getString("TAS_CTASCTSE");
		this.tipoServizio = data.getString("TAS_DTASNTSE");
		this.descrizioneServizio = data.getString("TAS_DTASDTSE");
		this.motivoGiuridicoDellaRiscossione = data.getString("TAS_CTASMGDR");
		this.versioneTassonomia = data.getString("TAS_CTASVTAS");
		this.datiSpecificiIncasso = data.getString("TAS_CTASDSPI");
		this.dataInizioValidita = data.getTimestamp("TAS_GTASDTIN");
		this.dataFineValidita = data.getTimestamp("TAS_GTASDTFI");
		this.dataUltimoAggiornamento = data.getTimestamp("TAS_GTASGAGG");
		this.operatoreUltimoAggiornamento = data.getString("TAS_CTASCOPE");
	}


	public BigInteger getChiaveTassonomia() {
		return chiaveTassonomia;
	}

	public void setChiaveTassonomia(BigInteger chiaveTassonomia) {
		this.chiaveTassonomia = chiaveTassonomia;
	}

	public String getCodiceTipoEnteCreditore() {
		return codiceTipoEnteCreditore;
	}

	public void setCodiceTipoEnteCreditore(String codiceTipoEnteCreditore) {
		this.codiceTipoEnteCreditore = codiceTipoEnteCreditore;
	}

	public String getTipoEnteCreditore() {
		return tipoEnteCreditore;
	}

	public void setTipoEnteCreditore(String tipoEnteCreditore) {
		this.tipoEnteCreditore = tipoEnteCreditore;
	}

	public String getProgressivoMacroAreaPerEnteCreditore() {
		return progressivoMacroAreaPerEnteCreditore;
	}

	public void setProgressivoMacroAreaPerEnteCreditore(String progressivoMacroAreaPerEnteCreditore) {
		this.progressivoMacroAreaPerEnteCreditore = progressivoMacroAreaPerEnteCreditore;
	}

	public String getNomeMacroArea() {
		return nomeMacroArea;
	}

	public void setNomeMacroArea(String nomeMacroArea) {
		this.nomeMacroArea = nomeMacroArea;
	}

	public String getDescrizioneMacroArea() {
		return descrizioneMacroArea;
	}

	public void setDescrizioneMacroArea(String descrizioneMacroArea) {
		this.descrizioneMacroArea = descrizioneMacroArea;
	}

	public String getCodiceTipologiaServizio() {
		return codiceTipologiaServizio;
	}

	public void setCodiceTipologiaServizio(String codiceTipologiaServizio) {
		this.codiceTipologiaServizio = codiceTipologiaServizio;
	}

	public String getTipoServizio() {
		return tipoServizio;
	}

	public void setTipoServizio(String tipoServizio) {
		this.tipoServizio = tipoServizio;
	}

	public String getDescrizioneServizio() {
		return descrizioneServizio;
	}

	public void setDescrizioneServizio(String descrizioneServizio) {
		this.descrizioneServizio = descrizioneServizio;
	}

	public String getMotivoGiuridicoDellaRiscossione() {
		return motivoGiuridicoDellaRiscossione;
	}

	public void setMotivoGiuridicoDellaRiscossione(String motivoGiuridicoDellaRiscossione) {
		this.motivoGiuridicoDellaRiscossione = motivoGiuridicoDellaRiscossione;
	}

	public String getVersioneTassonomia() {
		return versioneTassonomia;
	}

	public void setVersioneTassonomia(String versioneTassonomia) {
		this.versioneTassonomia = versioneTassonomia;
	}

	public String getDatiSpecificiIncasso() {
		return datiSpecificiIncasso;
	}

	public void setDatiSpecificiIncasso(String datiSpecificiIncasso) {
		this.datiSpecificiIncasso = datiSpecificiIncasso;
	}

	public Timestamp getDataInizioValidita() {
		return dataInizioValidita;
	}

	public void setDataInizioValidita(Timestamp dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public Timestamp getDataFineValidita() {
		return dataFineValidita;
	}

	public void setDataFineValidita(Timestamp dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	public Timestamp getDataUltimoAggiornamento() {
		return dataUltimoAggiornamento;
	}

	public void setDataUltimoAggiornamento(Timestamp dataUltimoAggiornamento) {
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
	}

	public String getOperatoreUltimoAggiornamento() {
		return operatoreUltimoAggiornamento;
	}

	public void setOperatoreUltimoAggiornamento(String operatoreUltimoAggiornamento) {
		this.operatoreUltimoAggiornamento = operatoreUltimoAggiornamento;
	}
	
	public String getDataInizioValidita_da() {
		return dataInizioValidita_da;
	}

	public void setDataInizioValidita_da(String dataInizioValidita_da) {
		this.dataInizioValidita_da = dataInizioValidita_da;
	}

	public String getDataFineValidita_a() {
		return dataFineValidita_a;
	}

	public void setDataFineValidita_a(String dataFineValidita_a) {
		this.dataFineValidita_a = dataFineValidita_a;
	}

	public String toString() {
		return "Tassonomia ["
				+ "chiaveTassonomia=" + chiaveTassonomia
				+ ", codiceTipoEnteCreditore=" + codiceTipoEnteCreditore
				+ ", tipoEnteCreditore=" + tipoEnteCreditore
				+ ", progressivoMacroAreaPerEnteCreditore=" + progressivoMacroAreaPerEnteCreditore
				+ ", nomeMacroArea=" + nomeMacroArea
				+ ", descrizioneMacroArea=" + descrizioneMacroArea
				+ ", codiceTipologiaServizio=" + codiceTipologiaServizio
				+ ", tipoServizio=" + tipoServizio
				+ ", descrizioneServizio=" + descrizioneServizio
				+ ", motivoGiuridicoDellaRiscossione=" + motivoGiuridicoDellaRiscossione
				+ ", versioneTassonomia=" + versioneTassonomia
				+ ", datiSpecificiIncasso=" + datiSpecificiIncasso
				+ ", dataInizioValidita=" + dataInizioValidita
				+ ", dataFineValidita=" + dataFineValidita
				+ ", dataUltimoAggiornamento=" + dataUltimoAggiornamento
				+ ", operatoreUltimoAggiornamento="	+ operatoreUltimoAggiornamento + "]";
	}

	/* <code></code>
	 * @see com.seda.payer.commons.bean.Lifecycle#onSave(java.sql.CallableStatement)
	 */
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.codiceTipoEnteCreditore);	
		arg.setString(2, this.tipoEnteCreditore);	
		arg.setString(3, this.progressivoMacroAreaPerEnteCreditore);	
		arg.setString(4, this.nomeMacroArea);	
		arg.setString(5, this.descrizioneMacroArea);	
		arg.setString(6, this.codiceTipologiaServizio);	
		arg.setString(7, this.tipoServizio);	
		arg.setString(8, this.descrizioneServizio);	
		arg.setString(9, this.motivoGiuridicoDellaRiscossione);
		arg.setString(10, this.versioneTassonomia);	
		arg.setTimestamp(11, this.dataInizioValidita);	
		arg.setTimestamp(12, this.dataFineValidita);	
		arg.setString(13, this.operatoreUltimoAggiornamento);
		arg.registerOutParameter(14, Types.INTEGER);
		arg.registerOutParameter(15, Types.BIGINT);
	}

	/* <code></code>
	 * @see com.seda.payer.commons.bean.Lifecycle#onUpdate(java.sql.CallableStatement)
	 */
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setInt(1, this.chiaveTassonomia.intValue());	
		arg.setString(2, this.codiceTipoEnteCreditore);	
		arg.setString(3, this.tipoEnteCreditore);	
		arg.setString(4, this.progressivoMacroAreaPerEnteCreditore);	
		arg.setString(5, this.nomeMacroArea);	
		arg.setString(6, this.descrizioneMacroArea);	
		arg.setString(7, this.codiceTipologiaServizio);	
		arg.setString(8, this.tipoServizio);	
		arg.setString(9, this.descrizioneServizio);	
		arg.setString(10, this.motivoGiuridicoDellaRiscossione);
		arg.setString(11, this.versioneTassonomia);	
		arg.setTimestamp(12, this.dataInizioValidita);	
		arg.setTimestamp(13, this.dataFineValidita);	
		arg.setString(14, this.operatoreUltimoAggiornamento);
		arg.registerOutParameter(15, Types.INTEGER);
	}

	/* <code></code>
	 * @see com.seda.payer.commons.bean.Lifecycle#onDelete(java.sql.CallableStatement)
	 */
	public void onDelete(CallableStatement arg) throws SQLException { }

	/* <code></code>
	 * @see com.seda.payer.commons.bean.Lifecycle#onLoad(java.sql.CallableStatement)
	 */
	public void onLoad(CallableStatement arg) throws SQLException {

		if (this.chiaveTassonomia != null)
			arg.setInt(1, this.chiaveTassonomia.intValue());
		else
			throw new SQLException("chiave tassonomia is null");
			
	}

	/* <code></code>
	 * @see com.seda.payer.commons.bean.Lifecycle#onLoad(java.sql.CallableStatement, int)
	 */
	public void onLoad(CallableStatement arg, int scope) throws SQLException {
		if (VIEW_SCOPE == scope) {
			if (this.chiaveTassonomia != null)
				arg.setInt(1, this.chiaveTassonomia.intValue());
			else
				throw new SQLException("chiave tassonomia is null");
		} else if (OVERLAY_SCOPE == scope) {
			if (this.codiceTipoEnteCreditore == null || this.codiceTipoEnteCreditore.trim().length() == 0
				|| this.progressivoMacroAreaPerEnteCreditore == null || this.progressivoMacroAreaPerEnteCreditore.trim().length() == 0
				|| this.codiceTipologiaServizio == null || this.codiceTipologiaServizio.trim().length() == 0
				|| this.motivoGiuridicoDellaRiscossione == null || this.motivoGiuridicoDellaRiscossione.trim().length() == 0)
				throw new SQLException("Elementi componenti 'Dati Specifici Incasso' tassonomia non valorizzati");
			else {
				arg.setString(1, this.codiceTipoEnteCreditore.trim());
				arg.setString(2, this.progressivoMacroAreaPerEnteCreditore.trim());
				arg.setString(3, this.codiceTipologiaServizio.trim());
				arg.setString(4, this.motivoGiuridicoDellaRiscossione);
			}
		}
	}

	/* <code></code>
	 * @see com.seda.payer.commons.bean.Lifecycle#onLoad(java.sql.CallableStatement, int, int, java.lang.String)
	 */
	public void onLoad(CallableStatement arg, int rowsPerPage, int pageNumber, String order) throws SQLException {
		arg.setInt(1, pageNumber);
		arg.setInt(2, rowsPerPage);
		arg.setString(3, order);
		arg.setString(4, (this.codiceTipoEnteCreditore != null ? this.codiceTipoEnteCreditore : ""));
		arg.setString(5, (this.tipoEnteCreditore != null ? this.tipoEnteCreditore : ""));
		arg.setString(6, (this.progressivoMacroAreaPerEnteCreditore != null ? this.progressivoMacroAreaPerEnteCreditore : ""));	
		arg.setString(7, (this.nomeMacroArea != null ? this.nomeMacroArea : ""));
		arg.setString(8, (this.descrizioneMacroArea != null ? this.descrizioneMacroArea : ""));
		arg.setString(9, (this.codiceTipologiaServizio != null ? this.codiceTipologiaServizio : ""));
		arg.setString(10, (this.tipoServizio != null ? this.tipoServizio : ""));
		arg.setString(11, (this.descrizioneServizio != null ? this.descrizioneServizio : ""));
		arg.setString(12, (this.motivoGiuridicoDellaRiscossione != null ? this.motivoGiuridicoDellaRiscossione : ""));
		arg.setString(13, (this.versioneTassonomia != null ? this.versioneTassonomia : ""));
		arg.setString(14, (this.datiSpecificiIncasso != null ? this.datiSpecificiIncasso : ""));
		arg.setString(15, (this.dataInizioValidita_da != null ? this.dataInizioValidita_da : ""));
		arg.setString(16, (this.dataFineValidita_a != null ? this.dataFineValidita_a : ""));
		/**/
		/* we register out_select */
		arg.registerOutParameter(17, Types.VARCHAR);
		/* we register row start */
		arg.registerOutParameter(18, Types.INTEGER);
		/* we register row end */
		arg.registerOutParameter(19, Types.INTEGER);
		/* we register total rows */
		arg.registerOutParameter(20, Types.INTEGER);
		/* we register total pages */
		arg.registerOutParameter(21, Types.INTEGER);
	}
}