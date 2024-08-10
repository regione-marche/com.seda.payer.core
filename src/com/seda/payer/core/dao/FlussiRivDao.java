/**
 * 
 */
package com.seda.payer.core.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

// NON USATO

/**
 * @author rcarnicelli
 *
 */
public class FlussiRivDao extends BaseDaoHandler {

	private static String listaCampi = "\"ENTE UFFICIO BENEFICIARIO\",\"TIPOLOGIA DI SERVIZIO\",\"DENOMINAZIONE ANAGRAFICA\",\"CODICE FISCALE\",\"ANNO DI RIFERIMENTO\",\"CANALE INCASSO\",\"STRUMENTO DI PAGAMENTO\"," +
	"\"GATEWAY DI PAGAMENTO\",\"DATA DI PAGAMENTO\",\"IMPORTO PAGATO DAI CONTRIBUENTI\",\"COMMISIONI RICHIESTE DAI GESTORI DEL GATEWAY\",\"RECUPERO SPESE NOTIFICA A FAVORE DEL GESTORE\",\"COMMISIONI CHE POSSONO ESSERE RICHIESTE DAL GESTORE\",\"IMPORTO DA RIVERSARE\",\"IMPORTO DA RECUPERARE\"\n";
	/**
	 * @param connection
	 * @param schema
	 */
	public FlussiRivDao(Connection connection, String schema) {
		super(connection, schema);
	}

	
	public String flussoCsv(String nomeFile,String csvOutputPath, String rev_cosccsoc, String rev_cutecute, Date rev_grevgdat, String rev_kanekane_ben, String rev_frevtipo, String rev_frevrive) throws DaoException
	{
		String filePath = null;
		filePath = (csvOutputPath + nomeFile).trim();
		String dati_csv = null;
		String file_csv = null;
		String tipo = null;
		StringBuffer sb_dati = new StringBuffer();
		StringBuffer sb_intestazione = new StringBuffer();
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			callableStatement = prepareCall("PYENSSP_PDF_DC");
			callableStatement.setString(1, rev_cosccsoc);
			callableStatement.setString(2, rev_cutecute);
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    		String data_format = formatter.format(rev_grevgdat);
			callableStatement.setString(3, data_format);
			callableStatement.setString(4, rev_kanekane_ben);
			callableStatement.setString(5, rev_frevtipo);
			callableStatement.setString(6, rev_frevrive);
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
			}
			if(data == null) return null;
			
			ResultSetMetaData rsmd = data.getMetaData();
			int numcol = rsmd.getColumnCount();
			//
			// Inserisco i dati
			//
			while(data.next())
			{
				for(int i=1;i<numcol;i++)
				{
//					System.out.println("nome colonna = "+rsmd.getColumnName(i));
					if (!"DDC_FREVTIPO".equals(rsmd.getColumnName(i))){
						sb_dati.append("\"");
						sb_dati.append(data.getObject(i).toString());
						sb_dati.append("\";");
					} else {
						tipo = data.getObject(i).toString();
					}
				}
				sb_dati.append("\"");
				if ("R".equals(tipo)) {
					sb_dati.append(data.getObject(numcol).toString());
					sb_dati.append("\";\"--");
				} else {
					sb_dati.append("--\";\"");
					sb_dati.append(data.getObject(numcol).toString());
				}
				sb_dati.append("\"\n");
			}
			dati_csv = sb_dati.toString();
			if ((dati_csv != null) && (!dati_csv.equals("")))
			{
//				
//				 inserisco la prima riga con i nomi
//				 dei campi che prendo dai metadati 
//				
//				for(int i=1;i<numcol;i++)
//				{
//					sb_intestazione.append("\"");
//					sb_intestazione.append(rsmd.getColumnName(i));
//					sb_intestazione.append("\";");
//				}
//				sb_intestazione.append("\"");
//				sb_intestazione.append(rsmd.getColumnName(numcol));
//				sb_intestazione.append("\"\n");
//				
//				 inserisco la prima riga con i nomi
//				 dei campi che prendo dalla lista campi 
//	
				sb_intestazione.append(listaCampi);
				file_csv = sb_intestazione.toString() + dati_csv;
			}
		} catch (Exception e) {
			e.printStackTrace();
			new DaoException(e);
		} finally  {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
		// salvo il file csv e l'equivalente zippato
		
		try {
			FileOutputStream file = new FileOutputStream(filePath+".csv");
			PrintStream output = new PrintStream(file);
			output.print(file_csv);
			file.close();
			
			// definiamo l'output previsto che sarà un file in formato zip 
			ZipOutputStream out = new ZipOutputStream(new 
				      BufferedOutputStream(new FileOutputStream(filePath+".zip")));
			
			// definiamo il buffer per lo stream di bytes 
			byte[] dataBuffer = new byte[1024]; 

			// indichiamo il nome del file che subirà la compressione 
			BufferedInputStream in = new BufferedInputStream
							(new FileInputStream(filePath+".csv"));
			int count;
			
			// processo di compressione del nostro file da zippare
			out.putNextEntry(new ZipEntry(nomeFile.trim()+".csv"));
			while((count = in.read(dataBuffer,0,dataBuffer.length)) != -1)
			{ 
				out.write(dataBuffer, 0, count);
			}
			in.close();
			out.flush();
			out.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePath+".csv";
	}
}
