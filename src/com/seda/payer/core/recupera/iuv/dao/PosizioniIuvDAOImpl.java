package com.seda.payer.core.recupera.iuv.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Properties;

import javax.sql.DataSource;

import com.seda.data.procedure.reflection.MetaProcedure;
import com.seda.data.procedure.reflection.ProcedureReflectorException;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.recupera.iuv.bean.PosizioneIuv;


public class PosizioniIuvDAOImpl   extends BaseDaoHandler  implements PosizioniIuvDAO  { 
	private static final long serialVersionUID = 1L;
	
	Properties attributes = new Properties();
	
	public int getIntegerAttribute(String name) {
		return Integer.parseInt(attributes.getProperty(name));
	}
	public String getStringAttribute(String name) {
		return attributes.getProperty(name);
	}

	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public PosizioniIuvDAOImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}
	public PosizioniIuvDAOImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema); 
	}
	
		//inizio LP PG21XX04 Leak
		//CallableStatement callableStatement=null;
		//fine LP PG21XX04 Leak
		public PosizioneIuv select(String idDominio, String codEnte, String codIuv, String codIuv_Iuv) throws DaoException {
		Connection connection = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement=null;
		ResultSet data=null;
		//fine LP PG21XX04 Leak
		PosizioneIuv posizioneIuv = null;
		try {
			System.out.println("idDominio = " + idDominio);
			System.out.println("codEnte = " + codEnte);
			System.out.println("codIuv = " + codIuv);
			System.out.println("codIuv_Iuv = " + codIuv_Iuv);
			
			connection = getConnection();
			//callableStatement = Helper.prepareCall(connection, getSchema(), "CNDOCSP_SEL");
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), "CNDOCSP_SEL2");
            callableStatement = MetaProcedure.prepareCall(connection, getSchema(), "CNDOCSP_SEL2");
			//fine LP PGNTCORE-24
			callableStatement.setString(1, idDominio);
			callableStatement.setString(2, codEnte);
			callableStatement.setString(3, codIuv==null?"":codIuv);
			callableStatement.setString(4, codIuv_Iuv==null?"":codIuv_Iuv);
			callableStatement.registerOutParameter(5, Types.CHAR);
			callableStatement.registerOutParameter(6, Types.VARCHAR);
			
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next()) {
					String identificativoDominio = data.getString(1);
					String codiceEnte = data.getString(2);
					String codiceIuv = data.getString(3);
					String identificativoFlusso = data.getString(4);
					Date dataCreazione = data.getDate(5);	//date
					String tipoRecord = data.getString(6);
					String identificativoDocumento = data.getString(7);
					String numeroRata = data.getString(8);
					Date dataScadenza =	data.getDate(9);	//date 
					String codiceFiscale = data.getString(10);
					BigDecimal importo = data.getBigDecimal(11);	//decimal(15,0)
					String denominazioneDebitore = data.getString(12);
					String indirizzoContribuente = data.getString(13);
					String localitaContribuente = data.getString(14);
					String provinciaContribuente = data.getString(15);
					String flagAnnullamento = data.getString(16);
					Timestamp dataAggiornamento = data.getTimestamp(17);	//datetime
					String codiceIban = data.getString(18);
					String codiceIuv_Iuv = data.getString(19);
					// PG200120 introduzione flagPagamento per controllo pagamento eseguito
					String flagPagato = data.getString(20);
					String codiceIban2 = data.getString(21);
					String causale = data.getString(22);
					String cespite = data.getString(23);
					String annoRif = data.getString(24);
					String codiceUtente = data.getString(25);
					String codiceSocieta = data.getString(26);
					String chiaveEnte = data.getString(27);
					String tipologiaServizio = data.getString(28);
					//inizio LP PG200370
					//posizioneIuv = new PosizioneIuv(identificativoDominio,codiceEnte,codiceIuv,identificativoFlusso,dataCreazione,
					//		tipoRecord,identificativoDocumento,numeroRata,dataScadenza,codiceFiscale,importo,
					//		denominazioneDebitore,indirizzoContribuente,localitaContribuente,provinciaContribuente,
					//		flagAnnullamento,dataAggiornamento,codiceIban,codiceIuv_Iuv,flagPagato,codiceIban2,causale,cespite,annoRif,codiceUtente, codiceSocieta, chiaveEnte, tipologiaServizio);
					String tassonomia = data.getString(29);
					
					//INIZIO SB PG210170
					String targa = data.getString(30);
					String dataScadenzaVerbale = data.getString(31);
					String numeroVerbale = data.getString(32);
					String numeroBollettinoCDS = data.getString(33);
					//fine SB PG210170
					String flagPoste = data.getString(34); //SB PGNTCORE-14
					posizioneIuv = new PosizioneIuv(identificativoDominio,codiceEnte,codiceIuv,identificativoFlusso,dataCreazione,
							tipoRecord,identificativoDocumento,numeroRata,dataScadenza,codiceFiscale,importo,
							denominazioneDebitore,indirizzoContribuente,localitaContribuente,provinciaContribuente,
							flagAnnullamento,dataAggiornamento,codiceIban,codiceIuv_Iuv,flagPagato,codiceIban2,causale,
							cespite,annoRif,codiceUtente, codiceSocieta, chiaveEnte, tipologiaServizio,
							tassonomia, targa, dataScadenzaVerbale, numeroVerbale, numeroBollettinoCDS, flagPoste
							);
					//fine LP PG200370
				}
			}
			attributes = new Properties();
			attributes.setProperty("CODESITO", callableStatement.getString(5));
			attributes.setProperty("MSGESITO", callableStatement.getString(6));
			return posizioneIuv;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		//inizio LP PGNTCORE-24
		//} catch (HelperException e) {
		//	throw new DaoException(e);
		} catch (ProcedureReflectorException e) {
			throw new DaoException(e);
		//fine LP PGNTCORE-24
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
			//DAOHelper.closeIgnoringException(connection);
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
			
		}
	}
	
}
