package com.seda.payer.core.wallet.dao;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import javax.sql.DataSource;

import com.seda.commons.security.TokenGenerator;
import com.seda.data.helper.Helper;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.dao.Routines;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler; 
import com.seda.payer.core.wallet.bean.FlussoSISE;
import com.seda.payer.core.wallet.bean.ImpPagamenti;
import com.seda.payer.core.wallet.bean.PagamentoBorsellino;
import com.seda.payer.core.wallet.bean.TributiForSORINET;
 

public class ImputaPagamentiDAOImpl extends BaseDaoHandler  implements ImputaPagamentiDAO { 
	CallableStatement callStmt=null;
	Connection connection = null;
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
	public ImputaPagamentiDAOImpl(DataSource ds, String schema) throws SQLException {
		super(ds.getConnection(), schema);
	}

	public ImputaPagamentiDAOImpl(Connection connection, String schema) throws SQLException { //MODIFICA
		super(connection, schema);
	}

//	public void openInsertBatch( )	throws DaoException { 
//		try {
//			connection = getConnection();
//			insertBatchCs = Helper.prepareCall(connection, getSchema(), Routines.PYREPSP_INS.routine());
//		}catch (Exception e) {
//			throw new DaoException(e);
//		}
//	} 
//	
//	// public void commitInsertBatch() throws DaoException{}
//
//	public void closeInsertBatch( ) {
//		Helper.close(insertBatchCs);
//		//Helper.close(connection); 
//	}
// 
//
//	public void executeInsertBatch( ) throws DaoException {
//		try {
//			insertBatchCs.executeBatch();
//		} catch (SQLException e) {
//			throw new DaoException(e);
//		}
//	}
	
	//inizio LP PG21XX04
	//inizio LP PG21XX18
	//Nota. La chiusura della connection è affidata al chiamante.
	//      Andrebbe rivista la procedura del batch per usare il metodo
	//      per gestire la chiusura della connection
	//fine LP PG21XX18
	//fine LP PG21XX04
	public ArrayList<FlussoSISE> assegnaPagamenti(ImpPagamenti impPagamenti)	throws DaoException{
		ArrayList<FlussoSISE> list = new ArrayList<FlussoSISE>();
		CallableStatement callStmt=null;
		Connection connection = null;
		//inizio LP PG21XX04 Leak
		ResultSet resultSet = null;
		//fine LP PG21XX04 Leak
		try {
			connection = getConnection();
			callStmt = Helper.prepareCall(connection, getSchema(), Routines.PYPGBSP_AGG_PAGAM.routine());
			callStmt.setString(1, impPagamenti.getFunzElab());
			callStmt.setString(2, impPagamenti.getCutecute() );
			callStmt.setString(3, impPagamenti.getElabSenzaConermaEpgf() );
			callStmt.registerOutParameter(4, Types.INTEGER); 
			callStmt.registerOutParameter(5, Types.INTEGER); 
			callStmt.registerOutParameter(6, Types.INTEGER); 
			callStmt.registerOutParameter(7, Types.INTEGER); 
			callStmt.registerOutParameter(8, Types.INTEGER); 
			callStmt.registerOutParameter(9, Types.INTEGER); 
			callStmt.registerOutParameter(10, Types.DECIMAL); 
			callStmt.registerOutParameter(11, Types.DECIMAL); 
			callStmt.registerOutParameter(12, Types.VARCHAR); 
			callStmt.registerOutParameter(13, Types.VARCHAR); 
			callStmt.registerOutParameter(14, Types.VARCHAR); 
			callStmt.execute();
			Integer numBorsellini_elab = callStmt.getInt(4);
			Integer numPres_Forf_elab = callStmt.getInt(5);
			Integer numPres_Gior_elab = callStmt.getInt(6);

			Integer numStorni_elab = callStmt.getInt(7);
			Integer numDiscarichi_elab = callStmt.getInt(8);
			Integer numAnnullati_elab = callStmt.getInt(9);
			BigDecimal importoBorsOld = callStmt.getBigDecimal(10);
			BigDecimal importoBorsNew = callStmt.getBigDecimal(11);

			String lastAction = callStmt.getString(12);
			String codiceRitorno = callStmt.getString(13);
			String messagRitorno = callStmt.getString(14);
			// save output parameters as attributes
			attributes = new Properties();
			attributes.setProperty("numBorsellini_elab", numBorsellini_elab.toString());
			attributes.setProperty("numPres_Forf_elab", numPres_Forf_elab.toString());
			attributes.setProperty("numPres_Gior_elab", numPres_Gior_elab.toString());
			attributes.setProperty("numStorni_elab", numStorni_elab.toString());
			attributes.setProperty("numDiscarichi_elab", numDiscarichi_elab.toString());
			attributes.setProperty("numAnnullati_elab", numAnnullati_elab.toString());
			attributes.setProperty("lastAction", lastAction);
			attributes.setProperty("codiceRitorno", codiceRitorno);
			attributes.setProperty("messagRitorno", messagRitorno);

			do {
				//inizio LP PG21XX04 Leak
				//ResultSet resultSet = callStmt.getResultSet();
				resultSet = callStmt.getResultSet();
				//fine LP PG21XX04 Leak
				if (resultSet != null && resultSet.next()) {
					do {					
						int numeroItem = resultSet.getInt(1);
						String tipoRecord = resultSet.getString(2);
						
						StringBuilder data = new StringBuilder();
						for(int i = 3; i < numeroItem; i++) {
							String value = resultSet.getString(i);
							data.append(value);          
						}
						
						FlussoSISE item = new FlussoSISE();
						item.setTipoRecord(tipoRecord);
						item.setDati(data.toString());
						list.add(item);
						
					} while(resultSet.next());
				}
			} while (callStmt.getMoreResults());
			
		} catch (SQLException e) {
			String msg = "Errore nell'esecuzione della stored "+Routines.PYPGBSP_AGG_PAGAM.routine();
			throw new DaoException(1,msg,e);
		} catch (Exception e) {
			String msg = "Errore nell'esecuzione della stored "+Routines.PYPGBSP_AGG_PAGAM.routine();
			throw new DaoException(1,msg,e);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (callStmt != null) {
				try {
					callStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//inizio LP PG21XX18
			//if (connection != null) {
			//	try {
			//		connection.close();
			//	} catch (SQLException e) {
			//		e.printStackTrace();
			//	}
			//}
			//fine LP PG21XX18
		}
		//fine LP PG21XX04 Leak
		return list; 
	}
	//inizio LP PG21XX04
	//inizio LP PG21XX18
	//Nota. La chiusura della connection è affidata al chiamante.
	//      Andrebbe rivista la procedura del batch per usare il metodo
	//      per gestire la chiusura della connection
	//fine LP PG21XX18
	//fine LP PG21XX04
	public void aggiornaDisponibilita()	throws DaoException{
		CallableStatement callStmt=null;
		Connection connection = null;
		
		try {
			connection = getConnection();
			callStmt = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_ALL_DISP.routine());
			callStmt.registerOutParameter(1, Types.INTEGER); 
			callStmt.execute();
			Integer numBorsellini_agg = callStmt.getInt(1);
			
		} catch (SQLException e) {
			System.out.println("Errore nell'esecuzione della stored " + Routines.PYBRSSP_ALL_DISP.routine());
			String msg = "Errore nell'esecuzione della stored "+Routines.PYBRSSP_ALL_DISP.routine();
			throw new DaoException(1,msg,e);
		} catch (Exception e) {
			System.out.println("Errore nell'esecuzione della stored " + Routines.PYBRSSP_ALL_DISP.routine());
			String msg = "Errore nell'esecuzione della stored "+Routines.PYBRSSP_ALL_DISP.routine();
			throw new DaoException(1,msg,e);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (callStmt != null) {
				try {
					callStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//inizio LP PG21XX18
			//if (connection != null) {
			//	try {
			//		connection.close();
			//	} catch (SQLException e) {
			//		e.printStackTrace();
			//	}
			//}
			//fine LP PG21XX18
		}
		//fine LP PG21XX04 Leak
	}
	//inizio LP PG21XX04
	//Nota. La chiusura della connection è affidata al chiamante.
	//      Andrebbe rivista la procedura del batch per usare il metodo
	//      per gestire la chiusura della connection
	//fine LP PG21XX04
	public ArrayList<FlussoSISE> produciFlussoSISE(String cutecute)	throws DaoException{
		ArrayList<FlussoSISE> list = new ArrayList<FlussoSISE>();
		CallableStatement callStmt=null;
		Connection connection = null;
		//inizio LP PG21XX04 Leak
		ResultSet resultSet = null;
		//fine LP PG21XX04 Leak
		try {
			connection = getConnection();
			callStmt = Helper.prepareCall(connection, getSchema(), Routines.PYSISSP_LST.routine());
			callStmt.setString(1, cutecute);
			callStmt.setString(2, "L");
			callStmt.execute();
			do {
				//inizio LP PG21XX04 Leak
				//ResultSet resultSet = callStmt.getResultSet();
				resultSet = callStmt.getResultSet();
				//fine LP PG21XX04 Leak
				if (resultSet != null && resultSet.next()) {
					do {					
						int numeroItem = resultSet.getInt(1);
						String tipoRecord = resultSet.getString(2);
						
						StringBuilder data = new StringBuilder();
						for(int i = 3; i < numeroItem; i++) {
							String value = resultSet.getString(i);
							data.append(value);          
						}
						
						FlussoSISE item = new FlussoSISE();
						item.setTipoRecord(tipoRecord);
						item.setDati(data.toString());
						list.add(item);
						
					} while(resultSet.next());
				}
			} while (callStmt.getMoreResults());
			
		} catch (SQLException e) {
			String msg = "Errore nell'esecuzione della stored "+Routines.PYSISSP_LST.routine();
			throw new DaoException(1,msg,e);
		} catch (Exception e) {
			String msg = "Errore nell'esecuzione della stored "+Routines.PYSISSP_LST.routine();
			throw new DaoException(1,msg,e);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (callStmt != null) {
				try {
					callStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
		return list; 
	}
	//inizio LP PG21XX04
	//Nota. La chiusura della connection è affidata al chiamante.
	//      Andrebbe rivista la procedura del batch  per usare  il metodo
	//      per gestire la chiusura della connessione
	//fine LP PG21XX04
	public void cancellaTabellaSISE(String cutecute)	throws DaoException{
		CallableStatement callStmt=null;
		Connection connection = null;
		
		try {
			connection = getConnection();
			callStmt = Helper.prepareCall(connection, getSchema(), Routines.PYSISSP_LST.routine());
			callStmt.setString(1, cutecute);
			callStmt.setString(2, "D");
			callStmt.execute();
		} catch (SQLException e) {
			String msg = "Errore nell'esecuzione della stored "+Routines.PYSISSP_LST.routine();
			throw new DaoException(1,msg,e);
		} catch (Exception e) {
			String msg = "Errore nell'esecuzione della stored "+Routines.PYSISSP_LST.routine();
			throw new DaoException(1,msg,e);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (callStmt != null) {
				try {
					callStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}
//	public String inserisciPagamentoWeb(PagamentoBorsellino pagamentoBorsellino)	throws DaoException, HelperException {
//		CallableStatement callStmt=null;
//		Connection connection = null;
//		String codiceRitorno = "";
//
//		try {
//			connection = getConnection();
//			callStmt = Helper.prepareCall(connection, getSchema(), Routines.SPEPPAGN.routine());
//			callStmt.setString(1, "N");
//			callStmt.setString(2, TokenGenerator.generateUUIDToken());
//			callStmt.setString(3, pagamentoBorsellino.getCodSocieta());
//			callStmt.setString(4, pagamentoBorsellino.getCodUtente()); 
//			callStmt.setString(5, pagamentoBorsellino.getCodEnte()); 
//			callStmt.setString(6, pagamentoBorsellino.getIdWallet()); 
//			callStmt.setString(7, ""); 
//			callStmt.setString(8, pagamentoBorsellino.getTipoPagamento()); 
//			callStmt.setString(9, pagamentoBorsellino.getCanalePagamento()); 
//			callStmt.setBigDecimal(10, pagamentoBorsellino.getImporto()); 
//			callStmt.setDate(11, new java.sql.Date(pagamentoBorsellino.getDataPagamento().getTime().getTime()));
//			callStmt.setDate(12, new java.sql.Date(pagamentoBorsellino.getDataAggiornamento().getTime().getTime()));
//			callStmt.setBoolean(13, false); // flag TOMBStoned 
//			callStmt.registerOutParameter(14, Types.CHAR); 
//			callStmt.registerOutParameter(15, Types.VARCHAR); 
//			callStmt.registerOutParameter(16, Types.VARCHAR); 
//			callStmt.registerOutParameter(17, Types.VARCHAR); 
//			callStmt.registerOutParameter(18, Types.TIMESTAMP); 
//			callStmt.registerOutParameter(19, Types.VARCHAR); 
//			callStmt.registerOutParameter(20, Types.VARCHAR); 
//			callStmt.registerOutParameter(21, Types.VARCHAR); 
//			callStmt.registerOutParameter(22, Types.VARCHAR); 
//			callStmt.registerOutParameter(23, Types.DECIMAL); 
//			callStmt.registerOutParameter(24, Types.DECIMAL); 
//			callStmt.registerOutParameter(25, Types.CHAR); 
//			callStmt.execute();
//			codiceRitorno = callStmt.getString(14);
//
//		} catch (SQLException e) {
//			String msg = "Errore nell'esecuzione della stored "+Routines.PYPGBSP_AGG_PAGAM.routine();
//			throw new DaoException(1,msg,e);
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return codiceRitorno; 
//	}

	//inizio LP PG21XX04
	//Nota. La chiusura della connection è affidata al chiamante
	//fine LP PG21XX04
	public String inserisciPagamentoWeb(String codTransazione, Calendar dataPagamento, String flagGestioneMercati)	throws DaoException, HelperException {
		CallableStatement callStmt=null;
		Connection connection = null;
		String codiceRitorno = "";

		try {
			connection = getConnection();
			callStmt = Helper.prepareCall(connection, getSchema(), Routines.PYPGBSP_PAG_WEB.routine());
			callStmt.setString(1, codTransazione);
			callStmt.setString(2, TokenGenerator.generateUUIDToken());
			callStmt.setDate(3, new java.sql.Date(dataPagamento.getTime().getTime()));//imposto la data aggiornamento uguale alla data corrente
			//callStmt.setTimestamp(3, new Timestamp(dataPagamento.getTimeInMillis()));
			callStmt.setString(4, flagGestioneMercati);	//PG180040
			callStmt.execute();

		} catch (SQLException e) {
			String msg = "Errore nell'esecuzione della stored "+Routines.PYPGBSP_PAG_WEB.routine();
			throw new DaoException(1,msg,e);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (callStmt != null) {
				try {
					callStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
		return codiceRitorno; 
	}
	
	//inizio LP PG190220
	//inizio LP PG21XX04
	//Nota. La chiusura della connection è affidata al chiamante
	//fine LP PG21XX04
	public boolean annullaPagamentoWeb(String codTransazione, Calendar dataPagamento) throws DaoException, HelperException {
		CallableStatement callStmt = null;
		Connection connection = null;
		boolean bOk = false;

		try {
			connection = getConnection();
			callStmt = Helper.prepareCall(connection, getSchema(), "PYPGBSP_ANN_WEB");
			callStmt.setString(1, codTransazione);
			callStmt.setDate(2, new java.sql.Date(dataPagamento.getTime().getTime()));
			callStmt.execute();
			bOk = true;
		} catch (SQLException e) {
			String msg = "Errore nell'esecuzione della stored " + "PYPGBSP_ANN_WEB";
			throw new DaoException(1,msg,e);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (callStmt != null) {
				try {
					callStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
		return bOk; 
	}
	//fine LP PG190220

	//inizio LP PG21XX04
	//Nota. La chiusura della connection è affidata al chiamante.
	//      Andrebbe rivista la procedura del batch che usa il metodo
	//      per gestire la chiusra della connessione
	//fine LP PG21XX04
	public String generaSISEglobale(String cutecute)	throws DaoException, HelperException {
		CallableStatement callStmt=null;
		Connection connection = null;
		String codiceRitorno = "";
		String messagRitorno = "";

		try {
			connection = getConnection();
			callStmt = Helper.prepareCall(connection, getSchema(), Routines.PYSISSP_GENERA.routine());
			callStmt.setString(1, cutecute);
			callStmt.setString(2, "G");
			callStmt.registerOutParameter(3, Types.CHAR); 
			callStmt.registerOutParameter(4, Types.VARCHAR); 
			callStmt.execute();
			codiceRitorno = callStmt.getString(3);
			messagRitorno = callStmt.getString(4);

		} catch (SQLException e) {
			String msg = "Errore nell'esecuzione della stored "+Routines.PYSISSP_GENERA.routine();
			throw new DaoException(1,msg,e);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (callStmt != null) {
				try {
					callStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
		return codiceRitorno+"_"+messagRitorno;
	}

	//inizio LP PG21XX04
	//Nota. La chiusura della connection è affidata al chiamante.
	//      Andrebbe rivista la procedura del batch che usa il metodo
	//      per gestire la chiusra della connessione
	//fine LP PG21XX04
	public ArrayList<TributiForSORINET> listForSORINET(String cutecute)	throws DaoException{
		ArrayList<TributiForSORINET> list = new ArrayList<TributiForSORINET>();
		CallableStatement callStmt=null;
//		Connection connection = null;
		//inizio LP PG21XX04 Leak
		Connection connection = null;
		ResultSet resultSet = null;
		//fine LP PG21XX04 Leak
		try {
			connection = getConnection();
			callStmt = Helper.prepareCall(connection, getSchema(), Routines.PYSNTSP_FOR_SORINET.routine());
			callStmt.setString(1, cutecute);
			callStmt.registerOutParameter(2, Types.VARCHAR); 
			callStmt.registerOutParameter(3, Types.VARCHAR); 
			callStmt.execute();
			do {
				//inizio LP PG21XX04 Leak
				//ResultSet resultSet = callStmt.getResultSet();
				resultSet = callStmt.getResultSet();
				//fine LP PG21XX04 Leak
				if (resultSet != null && resultSet.next()) {
					do {					
						String idWallet = resultSet.getString(1);				
						String tipoPres = resultSet.getString(2);				
						String keyPres = resultSet.getString(3);					
						String dataInvioSORINET = resultSet.getString(4);		
						int numRichiestaSORINET = resultSet.getInt(5);		
						String codUtente = resultSet.getString(6);				
						String documento = resultSet.getString(7);				
						String dataProvvedimento = resultSet.getString(8);			
						String tipoProvvedimento = resultSet.getString(9);		
						String annoProvvedimento = resultSet.getString(10);		
						String dataDilazione = resultSet.getString(11);				
						String note = resultSet.getString(12);					
						String userForSORINET = resultSet.getString(13); 			
						String numeroProtocollo = resultSet.getString(14);		
						String dataProtocollo = resultSet.getString(15);			
						String impCaricoDoc = resultSet.getString(16);		
						String impDisCaricoDoc = resultSet.getString(17);		
						String codTributo = resultSet.getString(18);				
						String annoTributo = resultSet.getString(19);				
						String suffTributo = resultSet.getString(20); 			
						String impCaricoTrib = resultSet.getString(21);		
						String impDisCaricoTrib = resultSet.getString(22);	
						String rcSORINET = resultSet.getString(23);				
						String messSORINET = resultSet.getString(24);				
						String codiceProvvedimento = resultSet.getString(25);		
						int numeroProvvedimento = resultSet.getInt(26);		
						String validita = resultSet.getString(27);				
						String descrizionePresenza = resultSet.getString(28);				
						String impCaricoDoc_edit = resultSet.getString(29);				
						String impDisCaricoDoc_edit = resultSet.getString(30);				
						String impCaricoTrib_edit = resultSet.getString(31);				
						String impDisCaricoTrib_edit = resultSet.getString(32);
						String dataPresenza = resultSet.getString(33);

						TributiForSORINET item = new TributiForSORINET(idWallet, tipoPres, keyPres, dataInvioSORINET, numRichiestaSORINET,
								codUtente, documento, dataProvvedimento, tipoProvvedimento, annoProvvedimento, dataDilazione, note, userForSORINET,
								numeroProtocollo, dataProtocollo, impCaricoDoc, impDisCaricoDoc, codTributo, annoTributo, suffTributo,
								impCaricoTrib, impDisCaricoTrib, rcSORINET, messSORINET, codiceProvvedimento, numeroProvvedimento, validita,descrizionePresenza,
								impCaricoDoc_edit, impDisCaricoDoc_edit, impCaricoTrib_edit, impDisCaricoTrib_edit, dataPresenza);
						list.add(item);
						
					} while(resultSet.next());
				}
			} while (callStmt.getMoreResults());
			
		} catch (SQLException e) {
			String msg = "Errore nell'esecuzione della stored "+Routines.PYSNTSP_FOR_SORINET.routine();
			throw new DaoException(1,msg,e);
		} catch (Exception e) {
			String msg = "Errore nell'esecuzione della stored "+Routines.PYSNTSP_FOR_SORINET.routine();
			throw new DaoException(1,msg,e);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (callStmt != null) {
				try {
					callStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
		return list; 
	}
	
	//inizio LP PG21XX04
	//Nota. La chiusura della connection è affidata al chiamante.
	//      Andrebbe rivista la procedura del batch che usa il metodo
	//      per gestire la chiusra della connessione
	//fine LP PG21XX04
	public String aggTributiSORINET(String funzione, TributiForSORINET tributiForSORINET)	throws DaoException,HelperException{
		ArrayList<TributiForSORINET> list = new ArrayList<TributiForSORINET>();
		CallableStatement callStmt=null;
		Connection connection = null;
		String retCodeMess = "";
		
		try {
			connection = getConnection();
			callStmt = Helper.prepareCall(connection, getSchema(), Routines.PYSNTSP_AGGIORNA.routine());
			callStmt.setString(1, funzione);
			callStmt.setString(2, tributiForSORINET.getCodUtente());
			callStmt.setString(3, tributiForSORINET.getDocumento());
			callStmt.setString(4, tributiForSORINET.getRcSORINET());
			callStmt.setString(5, tributiForSORINET.getMessSORINET());
			callStmt.setString(6, tributiForSORINET.getCodiceProvvedimento());
			callStmt.setInt(7, tributiForSORINET.getNumeroProvvedimento());
			callStmt.registerOutParameter(8, Types.VARCHAR); 
			callStmt.registerOutParameter(9, Types.VARCHAR); 
			callStmt.execute();
			retCodeMess = callStmt.getString(8).concat(callStmt.getString(9));
			
		} catch (SQLException e) {
			String msg = "Errore nell'esecuzione della stored "+Routines.PYSNTSP_AGGIORNA.routine();
			throw new DaoException(1,msg,e);
		} catch (Exception e) {
			String msg = "Errore nell'esecuzione della stored "+Routines.PYSNTSP_AGGIORNA.routine();
			throw new DaoException(1,msg,e);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (callStmt != null) {
				try {
					callStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
		return retCodeMess; 
	}
}
