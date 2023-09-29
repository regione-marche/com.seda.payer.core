package com.seda.payer.core.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.seda.data.helper.Helper;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.ConfigurazioneImpostaSoggiorno;
import com.seda.payer.core.bean.TestataComunicazioneImpostaSoggiorno;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class ImpostaSoggiornoDao extends BaseDaoHandler {
	
	static SimpleDateFormat sdfIso = new SimpleDateFormat("yyyy-MM-dd");
	private static Logger logger;
	
	protected CallableStatement callableStatementUPDISBATCH = null;
	
	public ImpostaSoggiornoDao(Connection connection, String schema) {
		super(connection, schema);
	}

	//inizio LP PG21XX04 Leak
	//private void closeConnection(CallableStatement callableStatement)
	//{
	//	if (callableStatement != null)
	//		DAOHelper.closeIgnoringException(callableStatement);
	//}
	//fine LP PG21XX04 Leak
	
	
	//servizi batch

//	SRG_ALL_ENTIIS=PYSRGSP_LST_ALL_EIS
		
	public ArrayList<ConfigurazioneImpostaSoggiorno> getAllEntiIS() throws DaoException
	{
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		ArrayList<ConfigurazioneImpostaSoggiorno> risultato = new ArrayList<ConfigurazioneImpostaSoggiorno>();
		try	{ 
			
			callableStatement = prepareCall(Routines.SRG_ALL_ENTIIS.routine());
			//inizio LP PG21XX04 Leak
			//ResultSet data = null;
			//fine LP PG21XX04 Leak
			ConfigurazioneImpostaSoggiorno elemento = null;

			if (callableStatement.execute()) 
			{
				data = callableStatement.getResultSet();
				while (data.next())
				{

					if (elemento == null || !data.getString("SRG_CUTECUTE").equals(elemento.getCodiceUtente())
						|| !data.getString("SRG_CSRGCGES").equals(elemento.getCodiceEnteGestionaleEntrate())
						|| !data.getString("SRG_CSRGCISE").equals(elemento.getImpostaServizioGestionaleEntrate())) {
					elemento = new ConfigurazioneImpostaSoggiorno();
	                
			    	elemento.setCodiceSocieta(data.getString("SRG_CSOCCSOC"));
			    	elemento.setCodiceUtente(data.getString("SRG_CUTECUTE"));
			    	elemento.setChiaveEnte(data.getString("SRG_KANEKENT"));
			    	elemento.setCodiceEnteGestionaleEntrate(data.getString("SRG_CSRGCGES"));
			    	elemento.setImpostaServizioGestionaleEntrate(data.getString("SRG_CSRGCISE"));

			    	risultato.add(elemento);
					}
				}
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
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
			//fine LP PG21XX04 Leak
		}

		return risultato;
	}
	
//	SELECT_PAG_HOST_ENTEIS=SPISOGG4  GET

	private BufferedWriter getFilePath(String nomeFilePath) throws FileNotFoundException  {
		// TODO[AA]
		return  new BufferedWriter( new OutputStreamWriter( new FileOutputStream( new File(nomeFilePath) , false) )  );   // il true finale indica che siamo in append
	}
	
	//selezione documenti x imposta servizio
	public String listDocFromHOSTByEIS(String codUtente, String codEnteHost, String codISHost, ArrayList<TestataComunicazioneImpostaSoggiorno> risultato) throws DaoException
	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		TestataComunicazioneImpostaSoggiorno elemento = null;
		String code;
		String message;
//		BufferedWriter filelog = null;
		try	{ 
			/*
			try {
				if(codUtente.equals("000P5"))
					//filelog = getFilePath("D:\\Applications\\Pagonet2\\Log\\logDAO.log");
					filelog = getFilePath("D:\\FileTemporanei\\Payer\\Log\\polenta\\logDAO.log");
				else
					//filelog = getFilePath("F:\\Pagonet2\\Log\\logDAO.log");
					filelog = getFilePath("D:\\FileTemporanei\\Payer\\Log\\polenta\\logDAO.log");
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				filelog.write("codUtente = " + codUtente);
				filelog.write("codEnteHost = " + codEnteHost);
				filelog.write("codISHost = " + codISHost);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			System.out.println("DATA-IN-CODUTEN: " + codUtente);
			System.out.println("DATA-IN-CODENTE: " + codEnteHost);
			System.out.println("DATA-IN-CODIMSE: " + codISHost);
			System.out.println("DATA-IN-CODFUNZ: " + "GET");
			System.out.println("DATA-IO-DATINIZ: " + "");
			System.out.println("DATA-IO-DATFINE: " + "");
			
			callableStatement = prepareCall(Routines.SELECT_PAG_HOST_ENTEIS.routine());
			callableStatement.setString(1, codUtente);
			callableStatement.setString(2, codEnteHost);
			callableStatement.setString(3, codISHost);

			callableStatement.setString(4, "GET");
			callableStatement.setString(5, "");
			callableStatement.setString(6, "");
			
			callableStatement.registerOutParameter(5, Types.CHAR);
			callableStatement.registerOutParameter(6, Types.CHAR);
			callableStatement.registerOutParameter(7, Types.CHAR);
			callableStatement.registerOutParameter(8, Types.CHAR);
			
			boolean presenza = callableStatement.execute();
			
			/*
			try {
				if (!presenza)
					filelog.write("presenza = FALSO");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			
			code = callableStatement.getString(7);
			message = callableStatement.getString(8);
			
			/*
			try {
				filelog.write("code = " + code.toString());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			
			if (code.equals("OK")&&presenza) 
			{
				
				data = callableStatement.getResultSet();
				/*
				try {
					filelog.write("prima del WHILE");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
				
				while (data.next())
				{
					/*
					try {
						filelog.write("SP letta e Recorset restituiti");

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					*/
					elemento = new TestataComunicazioneImpostaSoggiorno();
	
					//caricamento dati
					elemento.setCodiceUtente(data.getString(1));
					elemento.setNumeroDocumentoGestionaleEntrate(data.getString(5).substring(5).concat(" "));
					String flagPag = data.getString(6);
					elemento.setStatoDocumento(flagPag.equals("N")?"N":"Y"); 
					if (!flagPag.equals("N"))
					{
						try
						{
							elemento.setDataPagamento(sdfIso.parse(data.getString(7))); 
						}
						catch (Exception x) 
						{
							/*
							try {
								filelog.write("errore flagPag: " + x.getMessage());

							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							*/
							x.printStackTrace();
						}
					}
					
					/*
					try {
						filelog.write("aggiungo elemento");
						

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					*/
					
					//campi appoggio x date 
					elemento.setCodiceRID(callableStatement.getString(5));
					elemento.setCodiceBollettino(callableStatement.getString(6));
	
					risultato.add(elemento);
					
					/*
					try {
						filelog.write("elemento aggiunto");
						

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					*/
					
					
					
				}
				/*
				try {
					filelog.write("dopo del WHILE");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
			}
		} catch (SQLException x) {
//			try {
//				filelog.write("errore: "+ x.getMessage());
//
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
//			try {
//				filelog.write("errore: "+ x.getMessage());
//
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			throw new DaoException(x);
		} catch (HelperException x) {
//			try {
//				filelog.write("errore: "+ x.getMessage());
//
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			throw new DaoException(x);
		} finally {
//			try {
//				filelog.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
		
		return code+message;
	}

	
	//	SCT_UPDATE_DOC=PYSCTTB_UPD_DOC
	public int updateDocFromHOST(TestataComunicazioneImpostaSoggiorno testata, String operatore) throws DaoException {
	    CallableStatement callableStatement=null;		
	    int retCode = 1;
	    
	    try	{

			if (callableStatementUPDISBATCH ==null) {
				callableStatementUPDISBATCH  = Helper.prepareCall(getConnection(), getSchema(), Routines.SCT_UPDATE_DOC.routine());
			}

	    	
			/*
			callableStatement = prepareCall(Routines.SCT_UPDATE_DOC.routine());

			callableStatement.setString(1, testata.getNumeroDocumentoGestionaleEntrate());  // SCT_NSCTNDOC
			callableStatement.setString(2, testata.getStatoDocumento());   // SCT_FSCTTPAG
			callableStatement.setString(3, sdfIso.format(testata.getDataPagamento()));	// SCT_GSCTDPAG
			callableStatement.setString(4, operatore);	// SCT_GSCTDPAG

			callableStatement.registerOutParameter(5, Types.INTEGER);
			
			callableStatement.execute();

			retCode = callableStatement.getInt(5);
			*/
			
			callableStatementUPDISBATCH.setString(1, testata.getNumeroDocumentoGestionaleEntrate());  // SCT_NSCTNDOC
			callableStatementUPDISBATCH.setString(2, testata.getStatoDocumento());   // SCT_FSCTTPAG
			callableStatementUPDISBATCH.setString(3, sdfIso.format(testata.getDataPagamento()));	// SCT_GSCTDPAG
			callableStatementUPDISBATCH.setString(4, operatore);	// SCT_GSCTDPAG

			callableStatementUPDISBATCH.registerOutParameter(5, Types.INTEGER);
			
			callableStatementUPDISBATCH.execute();

			retCode = callableStatementUPDISBATCH.getInt(5);


		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		
		return retCode;
	}

//	SELECT_PAG_HOST_ENTEIS=SPISOGG4  SET
	public String setOKToHOST(String codUtente, String codEnteHost, String codISHost, String dataIni, String dataFin) throws DaoException
	{
		CallableStatement callableStatement = null;
		String code = "";
		String message = "";
		try	{ 
			
			System.out.println("DATA-IN-CODUTEN: " + codUtente);
			System.out.println("DATA-IN-CODENTE: " + codEnteHost);
			System.out.println("DATA-IN-CODIMSE: " + codISHost);
			System.out.println("DATA-IN-CODFUNZ: " + "REG");
			System.out.println("DATA-IO-DATINIZ: " + dataIni);
			System.out.println("DATA-IO-DATFINE: " + dataFin);
			
			callableStatement = prepareCall(Routines.SELECT_PAG_HOST_ENTEIS.routine());
			callableStatement.setString(1, codUtente);
			callableStatement.setString(2, codEnteHost);
			callableStatement.setString(3, codISHost);
			callableStatement.setString(4, "REG");
			callableStatement.setString(5, dataIni);
			callableStatement.setString(6, dataFin);
			
			callableStatement.registerOutParameter(5, Types.CHAR);
			callableStatement.registerOutParameter(6, Types.CHAR);
			callableStatement.registerOutParameter(7, Types.CHAR);
			callableStatement.registerOutParameter(8, Types.CHAR);
			
			callableStatement.execute();
			
			code = callableStatement.getString(7);
			message = callableStatement.getString(8);

		} 
		catch (Exception e) 
		{
			code="KO";
			message = e.getMessage();
//			throw new DaoException(e);
		}
		finally {
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
		
		return code+message;
	}

// selezione comunicazioni da dare a Host
	//selezione documenti x imposta servizio con SCT_FSCTSTAT = N o T
//	SCT_TO_SEND_HOST=PYSCTTB_LST_SEND_HOST
	public ArrayList<TestataComunicazioneImpostaSoggiorno> listComunicazioniToSendHost() throws DaoException
	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		TestataComunicazioneImpostaSoggiorno elemento = null;
		ArrayList<TestataComunicazioneImpostaSoggiorno> risultato = new ArrayList<TestataComunicazioneImpostaSoggiorno>();
		
		try	{ 
			callableStatement = prepareCall(Routines.SCT_TO_SEND_HOST.routine());
			if (callableStatement.execute())
			{
				data = callableStatement.getResultSet();
			
				while (data.next())
				{
					elemento = new TestataComunicazioneImpostaSoggiorno(data);
	
					//caricamento dati
					risultato.add(elemento);
				}
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
		
		return risultato;
	}
	
}