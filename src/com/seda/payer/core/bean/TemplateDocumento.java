package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import com.seda.payer.commons.bean.Lifecycle;
import com.seda.payer.commons.utility.StringUtility;
/**
 * @author aniello.labua
 */
public class TemplateDocumento extends Lifecycle implements Serializable{

	private static final long serialVersionUID = 1L;
	public static final int VIEW_SCOPE = 0;
	public static final int OVERLAY_SCOPE = 1;
	private String chiaveTemplate;
	private String tipoDocumento;
	private String codiceSocieta;
	private String tipologiaServizio;
	private String codiceUtente;
	private String codiceEnte;
	private String tipoBollettino;
	private Timestamp dataInizio;
	private Timestamp dataFine;
	private String riferimentoTemplate;
	private String siglaProvincia;
	private Timestamp dataUltimoAggiornamento;
	private String operatoreUltimoAggiornamento;

	/**
	 * Default constructor
	 */
	public TemplateDocumento() {
		super();
	}

	/**
	 * 
	 * @param data
	 * @throws SQLException
	 */
	public TemplateDocumento(ResultSet data) throws SQLException {
		if (data == null)
			return;

		this.chiaveTemplate = data.getString("GTD_KGTDKGTD");
		this.tipoDocumento = data.getString("GTD_FGTDTIPO");
		this.codiceSocieta = data.getString("GTD_CSOCCSOC");
		this.tipologiaServizio = data.getString("GTD_CTSECTSE");
		this.codiceUtente = data.getString("GTD_CUTECUTE");
		this.codiceEnte = data.getString("GTD_KANEKENT");
		this.tipoBollettino = data.getString("GTD_FGTDTIPB");
		this.dataInizio = data.getTimestamp("GTD_GGTDDTIN");
		this.dataFine = data.getTimestamp("GTD_GGTDDTFI");
		this.riferimentoTemplate = data.getString("GTD_CGTDTEMP");
		this.dataUltimoAggiornamento = data.getTimestamp("GTD_GGTDGAGG");
		this.operatoreUltimoAggiornamento = data.getString("GTD_CGTDCOPE");
	}

	public String getChiaveTemplate() {
		return chiaveTemplate;
	}

	public void setChiaveTemplate(String chiaveTemplate) {
		this.chiaveTemplate = chiaveTemplate;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getCodiceSocieta() {
		return codiceSocieta;
	}

	public void setCodiceSocieta(String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}

	public String getTipologiaServizio() {
		return tipologiaServizio;
	}

	public void setTipologiaServizio(String tipologiaServizio) {
		this.tipologiaServizio = tipologiaServizio;
	}

	public String getCodiceUtente() {
		return codiceUtente;
	}

	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}

	public String getCodiceEnte() {
		return codiceEnte;
	}

	public void setCodiceEnte(String codiceEnte) {
		this.codiceEnte = codiceEnte;
	}

	public String getTipoBollettino() {
		return tipoBollettino;
	}

	public void setTipoBollettino(String tipoBollettino) {
		this.tipoBollettino = tipoBollettino;
	}

	public Timestamp getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Timestamp dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Timestamp getDataFine() {
		return dataFine;
	}

	public String getRiferimentoTemplate() {
		return riferimentoTemplate;
	}

	public void setRiferimentoTemplate(String riferimentoTemplate) {
		this.riferimentoTemplate = riferimentoTemplate;
	}
	
	public String getSiglaProvincia() {
		return siglaProvincia;
	}

	public void setSiglaProvincia(String siglaProvincia) {
		this.siglaProvincia = siglaProvincia;
	}

	public void setDataFine(Timestamp dataFine) {
		this.dataFine = dataFine;
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

	public String toString() {
		return "TemplateDocumento [chiaveTemplate=" + chiaveTemplate
				+ ", codiceEnte=" + codiceEnte + ", codiceSocieta="
				+ codiceSocieta + ", codiceUtente=" + codiceUtente
				+ ", dataFine=" + dataFine + ", dataInizio=" + dataInizio
				+ ", dataUltimoAggiornamento=" + dataUltimoAggiornamento + ", operatoreUltimoAggiornamento="
				+ operatoreUltimoAggiornamento + ", riferimentoTemplate="
				+ riferimentoTemplate + ", tipoBollettino=" + tipoBollettino
				+ ", tipoDocumento=" + tipoDocumento + ", tipologiaServizio="
				+ tipologiaServizio + "]";
	}

	/* <code></code>
	 * @see com.seda.payer.commons.bean.Lifecycle#onSave(java.sql.CallableStatement)
	 */
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.tipoDocumento);
		arg.setString(2, this.codiceSocieta);	
		arg.setString(3, this.tipologiaServizio);	
		arg.setString(4, this.codiceUtente);
		arg.setString(5, this.codiceEnte);
		arg.setString(6, this.tipoBollettino);	
		arg.setTimestamp(7, this.dataInizio);
		arg.setTimestamp(8, this.dataFine);
		arg.setString(9, this.riferimentoTemplate);
		arg.setString(10, this.operatoreUltimoAggiornamento);
	}

	/* <code></code>
	 * @see com.seda.payer.commons.bean.Lifecycle#onUpdate(java.sql.CallableStatement)
	 */
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.chiaveTemplate);
		arg.setString(2, this.tipoDocumento);
		arg.setString(3, this.codiceSocieta);	
		arg.setString(4, this.tipologiaServizio);	
		arg.setString(5, this.codiceUtente);
		arg.setString(6, this.codiceEnte);
		arg.setString(7, this.tipoBollettino);	
		arg.setTimestamp(8, this.dataInizio);
		arg.setTimestamp(9, this.dataFine);
		arg.setString(10, this.riferimentoTemplate);
		arg.setString(11, this.operatoreUltimoAggiornamento);
	}

	/* <code></code>
	 * @see com.seda.payer.commons.bean.Lifecycle#onDelete(java.sql.CallableStatement)
	 */
	public void onDelete(CallableStatement arg) throws SQLException { }

	/* <code></code>
	 * @see com.seda.payer.commons.bean.Lifecycle#onLoad(java.sql.CallableStatement)
	 */
	public void onLoad(CallableStatement arg) throws SQLException {

		if (this.tipoDocumento != null)
			arg.setString(1, this.tipoDocumento);
		else arg.setNull(1, Types.VARCHAR);
			
		if(this.codiceSocieta!=null)
			arg.setString(2, this.codiceSocieta);
		else arg.setNull(2, Types.VARCHAR);
		
		if(this.tipologiaServizio!=null)
			arg.setString(3, this.tipologiaServizio);
			else arg.setNull(3, Types.VARCHAR);
		
		if(this.codiceUtente!=null)
			arg.setString(4, this.codiceUtente);
			else arg.setNull(4, Types.VARCHAR);
		
		if(this.codiceEnte!=null)
			arg.setString(5, this.codiceEnte);
			else arg.setNull(5, Types.VARCHAR);
		
		if(this.tipoBollettino!=null)
			arg.setString(6, this.tipoBollettino);
			else arg.setNull(6, Types.VARCHAR);
		
		if(this.dataInizio!=null)
			arg.setString(7, this.dataInizio.toString() );
			else arg.setNull(7, Types.VARCHAR);
		
		if(this.dataFine!=null)
			arg.setString(8, this.dataFine.toString());
			else arg.setNull(8, Types.VARCHAR);

		if(this.chiaveTemplate!=null)
			arg.setString(9, this.chiaveTemplate);
			else arg.setNull(9, Types.VARCHAR);
	}

	/* <code></code>
	 * @see com.seda.payer.commons.bean.Lifecycle#onLoad(java.sql.CallableStatement, int)
	 */
	public void onLoad(CallableStatement arg, int scope) throws SQLException {
		if (VIEW_SCOPE == scope) {
			if (this.tipoDocumento != null)
				arg.setString(1, this.tipoDocumento);
			else arg.setNull(1, Types.VARCHAR);

			if (this.codiceSocieta != null)
				arg.setString(2, this.codiceSocieta);
			else arg.setNull(2, Types.VARCHAR);

			if (this.tipologiaServizio != null)
				arg.setString(3, this.tipologiaServizio);
			else arg.setNull(3, Types.VARCHAR);
			
			if (this.codiceUtente != null)
				arg.setString(4, this.codiceUtente);
			else arg.setNull(4, Types.VARCHAR);
			
			if (this.codiceEnte != null)
				arg.setString(5, this.codiceEnte);
			else arg.setNull(5, Types.VARCHAR);
			
			if (this.tipoBollettino != null)
				arg.setString(6, this.tipoBollettino);
			else arg.setNull(6, Types.VARCHAR);

			if (this.dataInizio != null)
				arg.setString(7, new java.sql.Date(this.dataInizio.getTime()).toString());
			else arg.setNull(7, Types.VARCHAR);

			if (this.dataFine != null)
				arg.setString(8, new java.sql.Date(this.dataFine.getTime()).toString());
			else arg.setNull(8, Types.VARCHAR);

			if (this.chiaveTemplate != null)
				arg.setString(9, this.chiaveTemplate);
			else arg.setNull(9, Types.VARCHAR);

		} else if (OVERLAY_SCOPE == scope) {
			arg.setString(1, this.tipoDocumento);
			arg.setString(2, this.codiceSocieta);
			arg.setString(3, this.tipologiaServizio);
			arg.setString(4, this.codiceUtente);
			arg.setString(5, this.codiceEnte);
			arg.setString(6, new java.sql.Date(this.dataInizio.getTime()).toString());
			arg.setString(7, new java.sql.Date(this.dataFine.getTime()).toString());
			if (StringUtility.isEmpty(this.chiaveTemplate))
				arg.setString(8, "");
			else arg.setString(8, this.chiaveTemplate);
		}
	}

	/* <code></code>
	 * @see com.seda.payer.commons.bean.Lifecycle#onLoad(java.sql.CallableStatement, int, int, java.lang.String)
	 */
	public void onLoad(CallableStatement arg, int rowsPerPage, int pageNumber, String order) throws SQLException {
		arg.setString(1, this.codiceSocieta);
		arg.setString(2, this.codiceUtente);
		arg.setString(3, this.codiceEnte);
		arg.setString(4, this.siglaProvincia);
		if (this.dataInizio != null)
			arg.setString(5, new java.sql.Date(this.dataInizio.getTime()).toString());
		else arg.setNull(5, Types.VARCHAR);

		if(this.dataFine != null)
			arg.setString(6, new java.sql.Date(this.dataFine.getTime()).toString());
		else arg.setNull(6, Types.VARCHAR);

		arg.setString(7, this.tipologiaServizio);
		arg.setString(8, this.tipoDocumento);
		/**/
		arg.setString(9, order);
		arg.setInt(10, rowsPerPage);
		arg.setInt(11, pageNumber);
		/* we register row start */
		arg.registerOutParameter(12, Types.INTEGER);
		/* we register row end */
		arg.registerOutParameter(13, Types.INTEGER);
		/* we register total rows */
		arg.registerOutParameter(14, Types.INTEGER);
		/* we register total pages */
		arg.registerOutParameter(15, Types.SMALLINT);
	}
}