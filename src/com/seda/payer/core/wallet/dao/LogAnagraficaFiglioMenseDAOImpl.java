package com.seda.payer.core.wallet.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.sql.DataSource;
import com.seda.data.helper.Helper;
import com.seda.data.procedure.reflection.MetaProcedure;
import com.seda.data.procedure.reflection.ProcedureReflectorException;
import com.seda.payer.core.dao.Routines;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.wallet.bean.LogAnagraficaFiglioMense;

public class LogAnagraficaFiglioMenseDAOImpl  extends  BaseDaoHandler  implements LogAnagraficaFiglioMenseDAO  {
		private static final long serialVersionUID = 1L;
		//inizio LP PG21XX04 Leak
		@Deprecated
		//fine LP PG21XX04 Leak
		public LogAnagraficaFiglioMenseDAOImpl(DataSource dataSource, String schema) throws SQLException {
			super(dataSource.getConnection(), schema);
		}
		public LogAnagraficaFiglioMenseDAOImpl(Connection connection, String schema) throws SQLException {
			super(connection, schema);
		}

	public ArrayList<LogAnagraficaFiglioMense> list(LogAnagraficaFiglioMense logAnagraficaFiglioMense) throws  DaoException {
			//LogAnagraficaFiglioMense logAnagraficaFiglioMense = new LogAnagraficaFiglioMense();	
		ArrayList<LogAnagraficaFiglioMense> logAnagraficaFiglioMenseList = new ArrayList<LogAnagraficaFiglioMense>();
		Connection connection = null;
			CallableStatement callableStatement=null;
			ResultSet resultSet=null;
				try {
					connection = getConnection();
					//inizio LP PGNTCORE-24
					//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYLFMSP_LST.routine());
					callableStatement =  MetaProcedure.prepareCall(connection, getSchema(), Routines.PYLFMSP_LST.routine());
					//fine LP PGNTCORE-24
					callableStatement.setString(1,logAnagraficaFiglioMense.getIdWallet());
					callableStatement.setString(2,logAnagraficaFiglioMense.getCodiceAnagraficaFiglio());
					callableStatement.setString(3,logAnagraficaFiglioMense.getCodiceServizio());				
					callableStatement.execute();
		            resultSet=callableStatement.getResultSet();
		            if (resultSet.next()) {	          	                       
		          	  do {	          		  
		          		 	
		          		 String idWallet=resultSet.getString(1);					
		          		 String codiceServizio=resultSet.getString(2);
		          		 String codiceScuola=resultSet.getString(3);
		          		 String codiceAnagraficaFiglio=resultSet.getString(4);		
		          		 String codiceFiscaleFiglio=resultSet.getString(5);			
		          		 String codiceAnagraficaGenitore=resultSet.getString(6);	
		          		 String codiceFiscaleGenitore=resultSet.getString(7);		
		          		 String denominazioneFiglio=resultSet.getString(8);			
		          		
		          		 Date dataValidita = resultSet.getDate(9);
		          		 BigDecimal importoTariffa=resultSet.getBigDecimal(10);
		          		 String importoTariffaStr = importoTariffa.toString();
		          		 importoTariffaStr= importoTariffaStr.replace(",", "");
		          		 importoTariffaStr= importoTariffaStr.replace(".", ",");
		          		 
		          		 BigDecimal importoIsee=resultSet.getBigDecimal(11);
//		          		 String importoIseeStr = importoIsee.toString();
//		          		 importoIseeStr= importoIseeStr.replace(",", "");
//		          		 importoIseeStr= importoIseeStr.replace(".", ",");
//		          		  
		          		
						String importoIseeStr;
						if (importoIsee != null ) {
							importoIseeStr = importoIsee.toString();
							importoIseeStr= importoIseeStr.replace(",", "");
							importoIseeStr= importoIseeStr.replace(".", ",");
						} else {
							importoIseeStr = null;
						}
						

						
		          		 
		          		 String tipologiaTariffa=resultSet.getString(12);			
		          		 String classeSezione=resultSet.getString(13);				
		          		 Boolean flagAttivazione = resultSet.getString(14).equals("Y")?true:false;			
		          		 Date dataCaricamento=resultSet.getDate(15);
		          		 String descrizioneScuola=resultSet.getString(16);
		          		 String flagSospensione=resultSet.getString(17);	//PG150310_001 GG
		          		 
		          		 
		          		 Calendar dataValiditaCal=Calendar.getInstance();
		          		 dataValiditaCal.setTime(dataValidita);
		          		
		          		DateFormat dv = new SimpleDateFormat("dd/MM/yyyy"); 
						String dataValiditaString = dv.format(dataValiditaCal.getTime());
						
						

		          		 
		          		 Calendar dataCaricamentoCal=Calendar.getInstance();
						 dataCaricamentoCal.setTime(dataCaricamento);
						
						 //PG150310_001 GG - introdotto flagSospensione
						 LogAnagraficaFiglioMense item = new LogAnagraficaFiglioMense(idWallet,codiceServizio,
								 codiceAnagraficaFiglio,codiceFiscaleFiglio,codiceAnagraficaGenitore,codiceFiscaleGenitore,denominazioneFiglio,
								 codiceScuola,dataValiditaCal,importoTariffa,importoIsee,tipologiaTariffa,classeSezione,flagAttivazione,dataCaricamentoCal,flagSospensione);
						
						 item.setAttribute(LogAnagraficaFiglioMenseDAO.DESCRIZIONE_SCUOLA, descrizioneScuola); 
						 item.setAttribute(LogAnagraficaFiglioMenseDAO.DATA_VALIDITA_STRING, dataValiditaString);
						 item.setAttribute(LogAnagraficaFiglioMenseDAO.IMPORTOISEE, importoIseeStr);
						 item.setAttribute(LogAnagraficaFiglioMenseDAO.IMPORTOTARIFFA, importoTariffaStr);
						 
						 logAnagraficaFiglioMenseList.add(item);
		          	  } while(resultSet.next());	          	  					
		            }
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				//inizio LP PGNTCORE-24
				//} catch (HelperException e) {
				//	e.printStackTrace();
				} catch (ProcedureReflectorException e) {
					e.printStackTrace();
				//fine LP PGNTCORE-24
				} finally {
					//inizio LP PG21XX04 Leak
					//DAOHelper.closeIgnoringException(connection);
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
					if (connection != null) {
						try {
							connection.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					//fine LP PG21XX04 Leak
				}
				return logAnagraficaFiglioMenseList;
			}

	

}






