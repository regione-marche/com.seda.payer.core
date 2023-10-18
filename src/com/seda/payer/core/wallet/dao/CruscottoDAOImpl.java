package com.seda.payer.core.wallet.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Properties;

import javax.sql.DataSource;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.data.dao.DAOHelper;

import com.seda.data.helper.Helper;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

import com.seda.payer.core.wallet.bean.StatisticheForCruscotto;
import com.seda.payer.core.wallet.bean.Wallet;

public class CruscottoDAOImpl   extends BaseDaoHandler  implements CruscottoDAO  {
	private static final long serialVersionUID = 1L;

	BufferedWriter fileScuole;
	BufferedWriter fileBorsellini;
	BufferedWriter fileRicariche;
	BufferedWriter fileDettagliRicariche;
	BufferedWriter fileFigli;
	BufferedWriter fileAddebiti;
	BufferedWriter fileSolleciti;
	BufferedWriter fileConsumiForfettari;
	BufferedWriter fileConsumiGiornalieri;
	
	private static final LoggerWrapper logger =  CustomLoggerManager.get(CruscottoDAOImpl.class);
	
	protected CallableStatement callableStatement512 = null;

	Properties attributes = new Properties();
	
	public int getIntegerAttribute(String name) {
		return Integer.parseInt(attributes.getProperty(name));
	}
	public String getStringAttribute(String name) {
		return attributes.getProperty(name);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public CruscottoDAOImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}
	public CruscottoDAOImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}
	public  StatisticheForCruscotto generaFlussoStatistiche(String cutecute,String filePathWork) throws DaoException {
		StatisticheForCruscotto statisticheForCruscotto = new StatisticheForCruscotto();
		Connection connection = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		//fine LP PG21XX04 Leak
		try {
			connection = getConnection();
			
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = Helper.prepareCall(connection, getSchema(), STATISTICHE_CRUSCOTTO);
			callableStatement = Helper.prepareCall(connection, getSchema(), STATISTICHE_CRUSCOTTO);
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, cutecute);
			callableStatement.registerOutParameter(2, Types.INTEGER);
			callableStatement.registerOutParameter(3, Types.VARCHAR);
			callableStatement.execute();

			// save output parameters as attributes
			attributes = new Properties();
			attributes.setProperty("RETURNCODE", callableStatement.getString(2));
			attributes.setProperty("RETURNMESSAGE", callableStatement.getString(3));
			
			attributes.setProperty("nomeFileScuole", defineFile(filePathWork,"/Scuole.txt"));
			attributes.setProperty("nomeFileBorsellini", defineFile(filePathWork,"/Borsellini.txt"));
			attributes.setProperty("nomeFileRicariche", defineFile(filePathWork,"/Ricariche.txt"));
			attributes.setProperty("nomeFileDettagliRicariche", defineFile(filePathWork,"/Dettagli_Ricariche.txt"));
			attributes.setProperty("nomeFileFigli", defineFile(filePathWork,"/Figli.txt"));
			attributes.setProperty("nomeFileAddebiti", defineFile(filePathWork,"/Addebiti.txt"));
			attributes.setProperty("nomeFileSolleciti", defineFile(filePathWork,"/Solleciti.txt"));
			attributes.setProperty("nomeFileConsumiForfettari", defineFile(filePathWork,"/Consumi_forfettari.txt"));
			attributes.setProperty("nomeFileConsumiGiornalieri", defineFile(filePathWork,"/Consumi_giornalieri.txt"));
					
			do {
				//inizio LP PG21XX04 Leak
				//ResultSet resultSet = callableStatement.getResultSet();
				resultSet = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (resultSet != null && resultSet.next()) {
					do {					
						scriviFlusso(statisticheForCruscotto,resultSet.getString(1), resultSet.getString(2));
					} while(resultSet.next());
				}
			} while (callableStatement.getMoreResults());
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeFile();
			if (resultSet != null) {
				try {
					resultSet.close();
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
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new DaoException(e);
				}
			}
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			closeFile();
			//fine LP PG21XX04 Leak
		} 
		
		return statisticheForCruscotto;
	
	}

	private void closeFile(){
		try {
			fileScuole.close();
			fileBorsellini.close();
			fileRicariche.close();
			fileDettagliRicariche.close();
			fileFigli.close();
			fileAddebiti.close();
			fileSolleciti.close();
			fileConsumiForfettari.close();
			fileConsumiGiornalieri.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new DaoException(e);
		}
	}
	

		private BufferedWriter getFilePath(String nomeFilePath) throws FileNotFoundException  {
			// TODO[AA]
			return  new BufferedWriter( new OutputStreamWriter( new FileOutputStream( new File(nomeFilePath) , false) )  );   // il true finale indica che siamo in append
		}		


		
		private String defineFile(String nomePath,String nomeFile){

			if (nomeFile.equals("/Scuole.txt")){ 
				try {
					fileScuole = getFilePath(nomePath.concat(nomeFile));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
			if (nomeFile.equals("/Borsellini.txt")){ 
				try {
					fileBorsellini = getFilePath(nomePath.concat(nomeFile));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
			if (nomeFile.equals("/Ricariche.txt")){ 
				try {
					fileRicariche = getFilePath(nomePath.concat(nomeFile));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
			if (nomeFile.equals("/Dettagli_Ricariche.txt")){ 
				try {
					fileDettagliRicariche = getFilePath(nomePath.concat(nomeFile));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
			if (nomeFile.equals("/Figli.txt")){ 
				try {
					fileFigli = getFilePath(nomePath.concat(nomeFile));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
			if (nomeFile.equals("/Addebiti.txt")){ 
				try {
					fileAddebiti = getFilePath(nomePath.concat(nomeFile));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
			if (nomeFile.equals("/Solleciti.txt")){ 
				try {
					fileSolleciti = getFilePath(nomePath.concat(nomeFile));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
			if (nomeFile.equals("/Consumi_forfettari.txt")){ 
				try {
					fileConsumiForfettari = getFilePath(nomePath.concat(nomeFile));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
			if (nomeFile.equals("/Consumi_giornalieri.txt")){ 
				try {
					fileConsumiGiornalieri = getFilePath(nomePath.concat(nomeFile));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			}
			}
			}
			}
			}
			}
			}
			}
			return nomePath.concat(nomeFile);

		}

		private StatisticheForCruscotto scriviFlusso(StatisticheForCruscotto stat,String flusso, String dati){
			StringBuffer file = new StringBuffer();            
			String row = "";
//			row += dati + ";";
			row += dati;
			row += "\r";
			row += "\n";	//19012015 GG Ticket 0121497100000100 per file in formato DOS
			file.append(row);
			
			if (flusso.equals("SCU")){ 
				try { 
					int num = stat.getRecordsScuole();
					num ++;
					stat.setRecordsScuole(num);
					fileScuole.write(file.toString());
				}
				catch(Exception e){
					logger.error("Errore durante la scrittura del flusso " + attributes.getProperty("nomeFileScuole") , e);
				}
			}
			if (flusso.equals("BOR")){ 
				try { 
					int num = stat.getRecordsBorsellino();
					num ++;
					stat.setRecordsBorsellino(num);
					fileBorsellini.write(file.toString());
				}
				catch(Exception e){
					logger.error("Errore durante la scrittura del flusso " + attributes.getProperty("nomeFileBorsellini") , e);
				}
			}
			if (flusso.equals("RIC")){ 
				try { 
					int num = stat.getRecordsRicariche();
					num ++;
					stat.setRecordsRicariche(num);
					fileRicariche.write(file.toString());
				}
				catch(Exception e){
					logger.error("Errore durante la scrittura del flusso " + attributes.getProperty("nomeFileRicariche") , e);
				}
			}
			if (flusso.equals("DER")){ 
				try { 
					int num = stat.getRecordsDetteglioRicariche();
					num ++;
					stat.setRecordsDetteglioRicariche(num);
					fileDettagliRicariche.write(file.toString());
				}
				catch(Exception e){
					logger.error("Errore durante la scrittura del flusso " + attributes.getProperty("nomeFileDettagliRicariche") , e);
				}
			}
			if (flusso.equals("FIG")){ 
				try { 
					int num = stat.getRecordsFigli();
					num ++;
					stat.setRecordsFigli(num);
					fileFigli.write(file.toString());
				}
				catch(Exception e){
					logger.error("Errore durante la scrittura del flusso " + attributes.getProperty("nomeFileFigli") , e);
				}
			}
			if (flusso.equals("ADD")){ 
				try { 
					int num = stat.getRecordsAddebiti();
					num ++;
					stat.setRecordsAddebiti(num);
					fileAddebiti.write(file.toString());
				}
				catch(Exception e){
					logger.error("Errore durante la scrittura del flusso " + attributes.getProperty("nomeFileAddebiti") , e);
				}
			}
			if (flusso.equals("SOL")){ 
				try { 
					int num = stat.getRecordsSolleciti();
					num ++;
					stat.setRecordsSolleciti(num);
					fileSolleciti.write(file.toString());
				}
				catch(Exception e){
					logger.error("Errore durante la scrittura del flusso " + attributes.getProperty("nomeFileSolleciti") , e);
				}
			}
			if (flusso.equals("FOR")){ 
				try { 
					int num = stat.getRecordsPresenzeForfettarie();
					num ++;
					stat.setRecordsPresenzeForfettarie(num);
					fileConsumiForfettari.write(file.toString());
				}
				catch(Exception e){
					logger.error("Errore durante la scrittura del flusso " + attributes.getProperty("nomeFileConsumiForfettari") , e);
				}
			}
			if (flusso.equals("GIO")){ 
				try { 
					int num = stat.getRecordsPresenzeGiornaliere();
					num ++;
					stat.setRecordsPresenzeGiornaliere(num);
					fileConsumiGiornalieri.write(file.toString());
				}
				catch(Exception e){
					logger.error("Errore durante la scrittura del flusso " + attributes.getProperty("nomeFileConsumiGiornalieri") , e);
				}
			}
			return stat;
		}


}
