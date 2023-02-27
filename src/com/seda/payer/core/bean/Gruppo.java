package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import com.seda.payer.commons.bean.Lifecycle;

public class Gruppo extends Lifecycle implements Serializable{

	private static final long serialVersionUID = 1L;
	public static final int VIEW_SCOPE = 0;
	public static final int OVERLAY_SCOPE = 1;
	private BigInteger chiaveGruppo;
	private String codiceGruppo;
	private String descrizioneLinguaItaliana;
	private String descrizioneAltraLingua;
	private Timestamp dataUltimoAggiornamento;
	private String operatoreUltimoAggiornamento;

	/**
	 * Default constructor
	 */
	public Gruppo() {
		super();
	}

	/**
	 * 
	 * @param data
	 * @throws SQLException
	 */
	public Gruppo(ResultSet data) throws SQLException {
		if (data == null)
			return;

		this.chiaveGruppo = BigInteger.valueOf(data.getInt("GRP_KGRPPKEY"));
		this.codiceGruppo = data.getString("GRP_CGRPCODE");
		this.descrizioneLinguaItaliana = data.getString("GRP_DGRPDSIT");
		this.descrizioneAltraLingua = data.getString("GRP_DGRPDSAL");
		this.dataUltimoAggiornamento = data.getTimestamp("GRP_GGRPGAGG");
		this.operatoreUltimoAggiornamento = data.getString("GRP_CGRPCOPE");
	}


	public BigInteger getChiaveGruppo() {
		return chiaveGruppo;
	}

	public void setChiaveGruppo(BigInteger chiaveGruppo) {
		this.chiaveGruppo = chiaveGruppo;
	}

	public String getCodiceGruppo() {
		return codiceGruppo;
	}

	public void setCodiceGruppo(String codiceGruppo) {
		this.codiceGruppo = codiceGruppo;
	}

	public String getDescrizioneLinguaItaliana() {
		return descrizioneLinguaItaliana;
	}

	public void setDescrizioneLinguaItaliana(String descrizioneLinguaItaliana) {
		this.descrizioneLinguaItaliana = descrizioneLinguaItaliana;
	}

	public String getDescrizioneAltraLingua() {
		return descrizioneAltraLingua;
	}

	public void setDescrizioneAltraLingua(String descrizioneAltraLingua) {
		this.descrizioneAltraLingua = descrizioneAltraLingua;
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
		return "Gruppo ["
				+ "chiaveGruppo=" + chiaveGruppo
				+ ", codiceGruppo=" + codiceGruppo
				+ ", descrizioneLinguaItaliana=" + descrizioneLinguaItaliana
				+ ", descrizioneAltraLingua=" + descrizioneAltraLingua
				+ ", dataUltimoAggiornamento=" + dataUltimoAggiornamento
				+ ", operatoreUltimoAggiornamento="	+ operatoreUltimoAggiornamento + "]";
	}

	/* <code></code>
	 * @see com.seda.payer.commons.bean.Lifecycle#onSave(java.sql.CallableStatement)
	 */
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.codiceGruppo);	
		arg.setString(2, this.descrizioneLinguaItaliana);	
		arg.setString(3, this.descrizioneAltraLingua);	
		arg.setString(4, this.operatoreUltimoAggiornamento);
		arg.registerOutParameter(5, Types.INTEGER);
		arg.registerOutParameter(6, Types.BIGINT);
	}

	/* <code></code>
	 * @see com.seda.payer.commons.bean.Lifecycle#onUpdate(java.sql.CallableStatement)
	 */
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setInt(1, this.chiaveGruppo.intValue());	
		arg.setString(2, this.codiceGruppo);	
		arg.setString(3, this.descrizioneLinguaItaliana);	
		arg.setString(4, this.descrizioneAltraLingua);	
		arg.setString(5, this.operatoreUltimoAggiornamento);
		arg.registerOutParameter(6, Types.INTEGER);
	}

	/* <code></code>
	 * @see com.seda.payer.commons.bean.Lifecycle#onDelete(java.sql.CallableStatement)
	 */
	public void onDelete(CallableStatement arg) throws SQLException { }

	/* <code></code>
	 * @see com.seda.payer.commons.bean.Lifecycle#onLoad(java.sql.CallableStatement)
	 */
	public void onLoad(CallableStatement arg) throws SQLException {

		if (this.chiaveGruppo != null)
			arg.setInt(1, this.chiaveGruppo.intValue());
		else
			throw new SQLException("chiave gruppo is null");
			
	}

	/* <code></code>
	 * @see com.seda.payer.commons.bean.Lifecycle#onLoad(java.sql.CallableStatement, int)
	 */
	public void onLoad(CallableStatement arg, int scope) throws SQLException {
		if (VIEW_SCOPE == scope) {
			if (this.chiaveGruppo != null)
				arg.setInt(1, this.chiaveGruppo.intValue());
			else
				throw new SQLException("chiave gruppo is null");
		} else if (OVERLAY_SCOPE == scope) {
			if (this.codiceGruppo == null || this.codiceGruppo.trim().length() == 0)
				throw new SQLException("codice gruppo non valorizzato");
			else {
				arg.setString(1, this.codiceGruppo.trim());
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
		arg.setString(4, (this.codiceGruppo != null ? this.codiceGruppo : ""));
		arg.setString(5, (this.descrizioneLinguaItaliana != null ? this.descrizioneLinguaItaliana : ""));
		arg.setString(6, (this.descrizioneAltraLingua != null ? this.descrizioneAltraLingua : ""));	
		/**/
		/* we register out_select */
		arg.registerOutParameter(7, Types.VARCHAR);
		/* we register row start */
		arg.registerOutParameter(8, Types.INTEGER);
		/* we register row end */
		arg.registerOutParameter(9, Types.INTEGER);
		/* we register total rows */
		arg.registerOutParameter(10, Types.INTEGER);
		/* we register total pages */
		arg.registerOutParameter(11, Types.INTEGER);
	}
}