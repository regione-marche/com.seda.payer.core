package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import javax.xml.bind.annotation.XmlRootElement;
import com.seda.payer.commons.bean.Lifecycle;

@XmlRootElement
public class LogPap extends Lifecycle implements Serializable
{
	private static final long serialVersionUID = 1L;
	private BigInteger idLog;
	private String codiceUtente;
	private String idDominio;
	private String iuv;
	private Timestamp dataInizioChiamata;
	private Timestamp dataFineChiamata;
	private String xmlRequest;
	private String xmlResponse;
	private String esito;
	private String errore;
	private Timestamp dataInserimento;
	private String operazione;
	private String tagSuffissoTabella;
	private String dbSchemaCodSocieta;
	
	
	public LogPap() {
		super();
	}
	
	
	public BigInteger getIdLog() {
		return idLog;
	}
	public void setIdLog(BigInteger idLog) {
		this.idLog = idLog;
	}
	
	
	public String getIuv() {
		return iuv;
	}
	public void setIuv(String iuv) {
		this.iuv = iuv;
	}
	
	
	public String getCodiceUtente() {
		return codiceUtente;
	}
	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}
	
	
	public String getIdDominio() {
		return idDominio;
	}
	public void setIdDominio(String idDominio) {
		this.idDominio = idDominio;
	}
	
	
	public Timestamp getDataInizioChiamata() {
		return dataInizioChiamata;
	}
	public void setDataInizioChiamata(Timestamp dataInizioChiamata) {
		this.dataInizioChiamata = dataInizioChiamata;
	}
	

	public Timestamp getDataFineChiamata() {
		return dataFineChiamata;
	}
	public void setDataFineChiamata(Timestamp dataFineChiamata) {
		this.dataFineChiamata = dataFineChiamata;
	}

	
	public String getXmlRequest() {
		return xmlRequest;
	}
	public void setXmlRequest(String xmlRequest) {
		this.xmlRequest = xmlRequest;
	}

	
	public String getXmlResponse() {
		return xmlResponse;
	}
	public void setXmlResponse(String xmlResponse) {
		this.xmlResponse = xmlResponse;
	}
	
	
	public String getEsito() {
		return esito;
	}
	public void setEsito(String esito) {
		this.esito = esito;
	}
	
	public String getErrore() {
		return errore;
	}
	public void setErrore(String errore) {
		this.errore = errore;
	}
	
	
	public Timestamp getDataInserimento() {
		return dataInserimento;
	}
	public void setDataInserimento(Timestamp dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	
	public String getTagSuffissoTabella() {
		return tagSuffissoTabella;
	}

	public void setTagSuffissoTabella(String tagSuffissoTabella) {
		this.tagSuffissoTabella = tagSuffissoTabella;
	}

	public String getDbSchemaCodSocieta() {
		return dbSchemaCodSocieta;
	}
	public void setDbSchemaCodSocieta(String dbSchemaCodSocieta) {
		this.dbSchemaCodSocieta = dbSchemaCodSocieta;
	}
		
	public LogPap(ResultSet data) throws SQLException {
		if (data == null)
			return;
		this.idLog = BigInteger.valueOf(data.getInt("PAP_KPAPPKEY"));
		this.codiceUtente =  data.getString("PAP_CPAPCUTE");
		this.idDominio = data.getString("PAP_CPAPIDDM");
		this.iuv = data.getString("PAP_CPAPCIUV");
		this.dataInizioChiamata = data.getTimestamp("PAP_GPAPDTIN");
		this.dataFineChiamata = data.getTimestamp("PAP_GPAPDTFI");
		this.xmlRequest = data.getString("PAP_DPAPXMLI");
		this.xmlResponse = data.getString("PAP_DPAPXMLO");
		this.esito = data.getString("PAP_CPAPESIT");
		this.errore = data.getString("PAP_CPAPCERR");
		this.dataInserimento = data.getTimestamp("PAP_GPAPGAGG");
		this.operazione = data.getString("PAP_CPAPOPER");
		
		try {
			this.tagSuffissoTabella = data.getString("PAP_TGSUFTAB");
		} catch (Exception e) {
			this.tagSuffissoTabella = "";
		}
	}

	public String toString() {
		return "LogPap ["
				+ "idLog=" + idLog
				+ ", codiceUtente=" + codiceUtente
				+ ", idDominio=" + idDominio
				+ ", iuv=" + iuv
				+ ", dataInizioChiamata=" + dataInizioChiamata
				+ ", dataFineChiamata=" + dataFineChiamata
				+ ", xmlRequest=" + xmlRequest
				+ ", xmlResponse=" + xmlResponse
				+ ", esito=" + esito
				+ ", errore=" + errore
				+ ", operazione=" + operazione
				+ ", tagSuffissoTabella=" + tagSuffissoTabella
				+ "]";
	}




	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.codiceUtente);	
		arg.setString(2, this.idDominio);	
		arg.setString(3, this.iuv);
		arg.setTimestamp(4, this.dataInizioChiamata);
		arg.setTimestamp(5, this.dataFineChiamata);
		arg.setString(6, this.xmlRequest);
		arg.setString(7, this.xmlResponse);
		arg.setString(8, this.esito);
		arg.setString(9, this.errore);
		arg.setString(10, this.operazione);
		arg.registerOutParameter(11, Types.INTEGER);
		arg.registerOutParameter(12, Types.BIGINT);
	}

	
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setInt(1, this.idLog.intValue());
		arg.setString(2, this.codiceUtente);
		arg.setString(3, this.idDominio);	
		arg.setString(4, this.iuv);	
		arg.setTimestamp(5, this.dataInizioChiamata);
		arg.setTimestamp(6, this.dataFineChiamata);
		arg.setString(7, this.xmlRequest);
		arg.setString(8, this.xmlResponse);
		arg.setString(9, this.esito);
		arg.setString(10, this.errore);
		arg.setTimestamp(11, this.dataInserimento);
		arg.setString(12, this.operazione);
		arg.registerOutParameter(13, Types.INTEGER);
	}

	
	public void onDelete(CallableStatement arg) throws SQLException {
		if (this.idLog != null)
			arg.setInt(1, this.idLog.intValue());
		else
			throw new SQLException("id request is null");
	}

	
	public void onLoad(CallableStatement arg) throws SQLException {
		if (this.idLog != null) {
			arg.setInt(1, this.idLog.intValue());
			if(this.tagSuffissoTabella == null) {
				arg.setNull(2, Types.VARCHAR);
			} else {
				arg.setString(2, this.tagSuffissoTabella);
			}
		} else
			throw new SQLException("id request is null");
	}

	
	public void onLoad(CallableStatement arg, int rowsPerPage, int pageNumber, String order) throws SQLException {
		arg.setInt(1, pageNumber);
		arg.setInt(2, rowsPerPage);
		arg.setString(3, order);
		arg.setString(4, (this.codiceUtente != null ? this.codiceUtente : ""));
		arg.setString(5, (this.idDominio != null ? this.idDominio : ""));
		arg.setString(6, (this.iuv != null ? this.iuv : ""));
		arg.setTimestamp(7, (this.dataInizioChiamata != null ? this.dataInizioChiamata : null));
		arg.setTimestamp(8, (this.dataFineChiamata != null ? this.dataFineChiamata : null));
		arg.setString(9, (this.xmlRequest != null ? this.xmlRequest : ""));
		arg.setString(10, (this.xmlResponse != null ? this.xmlResponse : ""));
		arg.setString(11, (this.esito != null ? this.esito : ""));
		arg.setString(12, (this.errore != null ? this.errore : ""));
		arg.setTimestamp(13, (this.dataInserimento != null ? this.dataInserimento : null ));
		arg.setString(14, (this.operazione != null ? this.operazione : null ));
		/**/
		/* we register out_select */
		arg.registerOutParameter(15, Types.VARCHAR);
		/* we register row start */
		arg.registerOutParameter(16, Types.INTEGER);
		/* we register row end */
		arg.registerOutParameter(17, Types.INTEGER);
		/* we register total rows */
		arg.registerOutParameter(18, Types.INTEGER);
		/* we register total pages */
		arg.registerOutParameter(19, Types.INTEGER);
	}


	public String getOperazione() {
		return operazione;
	}


	public void setOperazione(String operazione) {
		this.operazione = operazione;
	}
}