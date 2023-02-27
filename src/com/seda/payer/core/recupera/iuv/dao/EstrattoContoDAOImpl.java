package com.seda.payer.core.recupera.iuv.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

import javax.sql.DataSource;

import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.dao.Routines;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;


public class EstrattoContoDAOImpl   extends BaseDaoHandler  implements EstrattoContoDAO  { 
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
	public EstrattoContoDAOImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}
	public EstrattoContoDAOImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema); 
	}
	
	//PG200120  recupero lista enti estratto conto
	/**
	 * @param codiceFiscale
	 * @param tipoRichiesta (I=Insoluti altrimenti Storico)
	 * @throws DaoException
	 */

	public String[] getListEnti(String codiceFiscale, String tipoRichiesta) throws DaoException {
		CallableStatement callableStatement = null;
		try	
		{ 	
			String[] outRes = new String[2];
			outRes[0] = "KO";
			outRes[1] = "Error";
			String flagPagato = (tipoRichiesta!=null && tipoRichiesta.equals("I"))?"":"X";
			
			callableStatement = prepareCall(Routines.CNDOCSP_LSTENT.routine());
			callableStatement.setString(1, codiceFiscale);
			callableStatement.setString(2, flagPagato);
			
			if (callableStatement.execute()) 
			{
				this.loadWebRowSets(callableStatement);
				outRes[0] = "OK";
				outRes[1] = "";
			}

			return outRes;
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	public String[] getListDoc(String codiceEnte, String codiceFiscale, String tipoRichiesta) throws DaoException {
		 
		CallableStatement callableStatement = null;
		try	
		{ 
			String[] outRes = new String[2];
			outRes[0] = "KO";
			outRes[1] = "Error";
			
			String flagPagato = (tipoRichiesta!=null && tipoRichiesta.equals("I"))?"":"X";
			callableStatement = prepareCall(Routines.CNDOCSP_LST_EC.routine());
			callableStatement.setString(1, codiceEnte);
			callableStatement.setString(2, codiceFiscale);
			callableStatement.setString(3, flagPagato);
			
			int ik = 0;
			System.out.println("Parametri in input per SP CNDOCSP_LST_EC: ");
			System.out.println(++ik + " - codiceEnte             <" + codiceEnte + ">");
			System.out.println(++ik + " - codiceFiscale          <" + codiceFiscale + ">");
			System.out.println(++ik + " - flagPagato          	 <" + flagPagato + ">");
			
//			callableStatement.registerOutParameter(4, Types.VARCHAR);
//			/* indicazione errore */
//			callableStatement.registerOutParameter(5, Types.VARCHAR);
			
			if (callableStatement.execute()) 
			{
				this.loadWebRowSets(callableStatement);
				outRes[0] = "OK";
				outRes[1] = "";
			}
			
//			outRes[0] = callableStatement.getString(4).trim();
//			outRes[1] = callableStatement.getString(5).trim();
				
			return outRes;
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}	
	
	public String[] getListScadenze(String codiceEnte, String codiceFiscale, String tipoRichiesta, String numeroDoc) throws DaoException 
	{
		CallableStatement callableStatement = null;
		try	
		{ 
			String[] outRes = new String[4];
			outRes[0] = "KO";
			outRes[1] = "Error";
			String flagPagato = (tipoRichiesta!=null && tipoRichiesta.equals("I"))?"":"X";
			
			callableStatement = prepareCall(Routines.CNDOCSP_DET_EC.routine());
			callableStatement.setString(1, codiceEnte);
			callableStatement.setString(2, codiceFiscale);
			callableStatement.setString(3, flagPagato);
			callableStatement.setString(4, numeroDoc);
//			callableStatement.registerOutParameter(5, Types.CHAR);
//			callableStatement.registerOutParameter(6, Types.VARCHAR);
						
			if (callableStatement.execute()) 
			{
				this.loadWebRowSets(callableStatement);	
				outRes[0] = "OK";
				outRes[1] = "";
			}			

//			outRes[0] = callableStatement.getString(5).trim();
//			outRes[1] = callableStatement.getString(6).trim();
			return outRes;
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	public String[] getListMovimenti(String codiceEnte, String codiceFiscale, String numeroDoc) throws DaoException 
	{	
		CallableStatement callableStatement = null;	
		try	
		{
			String[] outRes = new String[2];
			outRes[0] = "KO";
			outRes[1] = "Error";
			
			callableStatement = prepareCall(Routines.CNDOCSP_MOV_EC.routine());
			callableStatement.setString(1, codiceEnte);
			callableStatement.setString(2, codiceFiscale);
			callableStatement.setString(3, numeroDoc);
//			callableStatement.registerOutParameter(4, Types.VARCHAR);
//			callableStatement.registerOutParameter(5, Types.VARCHAR);
			
			if (callableStatement.execute()) 
			{
				this.loadWebRowSets(callableStatement);	
				outRes[0] = "OK";
				outRes[1] = "";
			}	
			
//			outRes[0] = callableStatement.getString(4).trim();
//			outRes[1] = callableStatement.getString(5).trim();
			return outRes;
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
//	public PosizioneIuv select(String idDominio, String codEnte, String codIuv, String codIuv_Iuv) throws DaoException {
//		CallableStatement callableStatement=null;
//		Connection connection = null;
//		PosizioneIuv posizioneIuv = null;
//		try {
//			connection = getConnection();
//			callableStatement = Helper.prepareCall(connection, getSchema(), "CNDOCSP_SEL");
//			callableStatement.setString(1, idDominio);
//			callableStatement.setString(2, codEnte);
//			callableStatement.setString(3, codIuv==null?"":codIuv);
//			callableStatement.setString(4, codIuv_Iuv==null?"":codIuv_Iuv);
//			callableStatement.registerOutParameter(5, Types.CHAR);
//			callableStatement.registerOutParameter(6, Types.VARCHAR);
//			
//			if (callableStatement.execute()) {
//				ResultSet data = callableStatement.getResultSet();
//				if (data.next()) {
//					String identificativoDominio = data.getString(1);
//					String codiceEnte = data.getString(2);
//					String codiceIuv = data.getString(3);
//					String identificativoFlusso = data.getString(4);
//					Date dataCreazione = data.getDate(5);	//date
//					String tipoRecord = data.getString(6);
//					String identificativoDocumento = data.getString(7);
//					String numeroRata = data.getString(8);
//					Date dataScadenza =	data.getDate(9);	//date 
//					String codiceFiscale = data.getString(10);
//					BigDecimal importo = data.getBigDecimal(11);	//decimal(15,0)
//					String denominazioneDebitore = data.getString(12);
//					String indirizzoContribuente = data.getString(13);
//					String localitaContribuente = data.getString(14);
//					String provinciaContribuente = data.getString(15);
//					String flagAnnullamento = data.getString(16);
//					Timestamp dataAggiornamento = data.getTimestamp(17);	//datetime
//					String codiceIban = data.getString(18);
//					String codiceIuv_Iuv = data.getString(19);
//					posizioneIuv = new PosizioneIuv(identificativoDominio,codiceEnte,codiceIuv,identificativoFlusso,dataCreazione,
//							tipoRecord,identificativoDocumento,numeroRata,dataScadenza,codiceFiscale,importo,
//							denominazioneDebitore,indirizzoContribuente,localitaContribuente,provinciaContribuente,
//							flagAnnullamento,dataAggiornamento,codiceIban,codiceIuv_Iuv);
//				}
//			}
//			attributes = new Properties();
//			attributes.setProperty("CODESITO", callableStatement.getString(5));
//			attributes.setProperty("MSGESITO", callableStatement.getString(6));
//			return posizioneIuv;
//		} catch (SQLException e) {
//			throw new DaoException(e);
//		} catch (IllegalArgumentException e) {
//			throw new DaoException(e);
//		} catch (HelperException e) {
//			throw new DaoException(e);
//		}finally {
//			DAOHelper.closeIgnoringException(callableStatement);
//			DAOHelper.closeIgnoringException(connection);
//		}
//	}
	
	//inizio LP PG21XX04 Leak
	//private void closeConnection(CallableStatement callableStatement)
	//{
	//	if (callableStatement != null)
	//		DAOHelper.closeIgnoringException(callableStatement);
	//}
	//fine LP PG21XX04 Leak
	
}
