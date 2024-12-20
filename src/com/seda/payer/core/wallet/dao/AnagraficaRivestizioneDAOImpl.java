package com.seda.payer.core.wallet.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeSet;
import javax.sql.DataSource;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.wallet.bean.AnagraficaRivestizione350;
import com.seda.payer.core.wallet.bean.AnagraficaRivestizioneCSI;
import com.seda.payer.core.wallet.bean.AnagraficaRivestizione500;
import com.seda.payer.core.wallet.bean.AnagraficaRivestizione512;

public class AnagraficaRivestizioneDAOImpl extends BaseDaoHandler implements AnagraficaRivestizioneDAO  {
	private static final long serialVersionUID = 1L;
	
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
	public AnagraficaRivestizioneDAOImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}

	public AnagraficaRivestizioneDAOImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}

	public int seqAnagraficaRivestizione() throws DaoException {
	//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
		return seqAnagraficaRivestizioneTail(true, true, true);
	}

	public int seqAnagraficaRivestizioneBatch(boolean bFlagUpdateAutocommit, boolean bCloseStat, boolean bCloseConn) throws DaoException {
		return seqAnagraficaRivestizioneTail(bFlagUpdateAutocommit, bCloseStat, bCloseConn);
	}

	private int seqAnagraficaRivestizioneTail(boolean bFlagUpdateAutocommit, boolean bCloseStat, boolean bCloseConn) throws DaoException {
	//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
		Connection connection = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try {
			//connection = getConnection(); //LP 20240916 - PGNTCORE-24/PGNTWPB-3
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = Helper.prepareCall(connection, getSchema(), ANAGRAFICA_RIVESTIZIONE);
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
			//callableStatement = Helper.prepareCall(connection, getSchema(), ANAGRAFICA_RIVESTIZIONE);
			callableStatement = prepareCall(bFlagUpdateAutocommit, ANAGRAFICA_RIVESTIZIONE);
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
			//fine LP PG21XX04 Leak
			callableStatement.registerOutParameter(1, Types.INTEGER);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.execute();		
			// save output parameters as attributes
			attributes = new Properties();
			attributes.setProperty("RETURNCODE", String.valueOf(callableStatement.getInt(1)));
			attributes.setProperty("RETURNMESSAGE", callableStatement.getString(2));
			return callableStatement.getInt(1);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
			if(bCloseStat) {
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
				//inizio LP PG21XX04 Leak
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				//fine LP PG21XX04 Leak
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
			}
			if(bCloseConn) {
				connection = getConnection();
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						throw new DaoException(e);
					}
				}
				connection = null;
			//inizio LP PG21XX04 Leak
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
			}
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			//fine LP PG21XX04 Leak
		} 
	}
	
	public int seqAnagraficaRivestizioneSolleciti() throws DaoException {
		//Connection connection = null; //LP 20240916 - PGNTCORE-24/PGNTWPB-3
		CallableStatement callableStatement = null ;
		try {
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
			//connection = getConnection();
			//callableStatement = Helper.prepareCall(connection, getSchema(), ANAGRAFICA_RIVESTIZIONE);
			callableStatement = prepareCall(ANAGRAFICA_RIVESTIZIONE);
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
			callableStatement.registerOutParameter(1, Types.INTEGER);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.execute();		
			// save output parameters as attributes
			attributes = new Properties();
			attributes.setProperty("RETURNCODE", callableStatement.getString(1));
			attributes.setProperty("RETURNMESSAGE", callableStatement.getString(2));
			int seqAnagraficaRivestizioneSollecitiInt = callableStatement.getInt(1); 
			return seqAnagraficaRivestizioneSollecitiInt;
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			try {
				callableStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
//			//inizio LP PG21XX04 Leak
//			if (connection != null) {
//				try {
//					connection.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			//fine LP PG21XX04 Leak
		} 
	}

	public ArrayList<AnagraficaRivestizione350> listAnagraficaRivestizione350(String welcomeKit) throws  DaoException {
	//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
		return listAnagraficaRivestizione350Tail(true, true, true, welcomeKit);
	}

	public ArrayList<AnagraficaRivestizione350> listAnagraficaRivestizione350Batch(boolean bFlagUpdateAutocommit, boolean bCloseStat, boolean bCloseConn, String welcomeKit) throws  DaoException {
		return listAnagraficaRivestizione350Tail(bFlagUpdateAutocommit, bCloseStat, bCloseConn, welcomeKit);
	}

	private ArrayList<AnagraficaRivestizione350> listAnagraficaRivestizione350Tail(boolean bFlagUpdateAutocommit, boolean bCloseStat, boolean bCloseConn, String welcomeKit) throws  DaoException {
	//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
		ArrayList<AnagraficaRivestizione350> list = new ArrayList<AnagraficaRivestizione350>();
		Connection connection = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		//fine LP PG21XX04 Leak
		try {
			//connection = getConnection(); //LP 20240916 - PGNTCORE-24/PGNTWPB-3
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = Helper.prepareCall(connection, getSchema(), ANAGRAFICA_RIVESTIZIONE_350);
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
			//callableStatement = Helper.prepareCall(connection, getSchema(), ANAGRAFICA_RIVESTIZIONE_350);
			callableStatement = prepareCall(bFlagUpdateAutocommit, ANAGRAFICA_RIVESTIZIONE_350);
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, welcomeKit);
			callableStatement.registerOutParameter(2, Types.INTEGER);
			callableStatement.registerOutParameter(3, Types.VARCHAR);
			callableStatement.execute();
			// save output parameters as attributes
			attributes = new Properties();
			attributes.setProperty("RETURNCODE", callableStatement.getString(2));
			attributes.setProperty("RETURNMESSAGE", callableStatement.getString(3));
			//inizio LP PG21XX04 Leak
			//ResultSet resultSet = callableStatement.getResultSet();
			resultSet = callableStatement.getResultSet();
			//fine LP PG21XX04 Leak
			if (resultSet != null && resultSet.next()) {
				do {					
					int i = 1;
					AnagraficaRivestizione350 item = new AnagraficaRivestizione350();
					item.setCodiceBelfiore(resultSet.getString(i++));          
					item.setDataFlusso(resultSet.getString(i++));		 
					item.setTipoRecord(resultSet.getString(i++));		 
					item.setTipoAnagrafica(resultSet.getString(i++));		 
					item.setCodiceFiscale(resultSet.getString(i++));		 
					item.setCognomeNome(resultSet.getString(i++));		 
					item.setCodiceBelfioreNascita(resultSet.getString(i++));		 
					item.setCodiceIstatNascita(resultSet.getString(i++));		 
					item.setDescrizioneComuneNascita(resultSet.getString(i++));		 
					item.setSiglaAutomobilisticaNascita(resultSet.getString(i++));		 
					item.setDataDiNascita(resultSet.getString(i++));		 
					item.setSesso(resultSet.getString(i++));		 
					item.setIndirizzo(resultSet.getString(i++));		 
					item.setDenominazioneFrazione(resultSet.getString(i++));		 
					item.setCap(resultSet.getString(i++));
					item.setDataEmigrazione(resultSet.getString(i++));
					item.setCodiceBelfioreEmmigrazione(resultSet.getString(i++));
					item.setCodiceIstatStatoEstero(resultSet.getString(i++));
					item.setDescrizioneComuneEmigrazione(resultSet.getString(i++));
					item.setSiglaAutomobilisticaEmigrazione(resultSet.getString(i++));
					item.setDataDecesso(resultSet.getString(i++));
					item.setStatoCivile(resultSet.getString(i++));
					item.setDataStatoCivile(resultSet.getString(i++));
					item.setIdentificativoDocumento(resultSet.getString(i++));
					list.add(item);
				} while(resultSet.next());
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
			if(bCloseStat) {
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//fine LP PG21XX04 Leak
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
			}
			if(bCloseConn) {
				connection = getConnection();
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						throw new DaoException(e);
					}
				}
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
				connection = null;
			}
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			//fine LP PG21XX04 Leak
		} 
		
		return list;
	}
	
	public ArrayList<AnagraficaRivestizioneCSI> listAnagraficaRivestizioneCSI(String welcomeKit) throws  DaoException {
	//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
		return listAnagraficaRivestizioneCSITail(true, true, true, welcomeKit);
	}

	public ArrayList<AnagraficaRivestizioneCSI> listAnagraficaRivestizioneCSIBatch(boolean bFlagUpdateAutocommit, boolean bCloseStat, boolean bCloseConn, String welcomeKit) throws  DaoException {
		return listAnagraficaRivestizioneCSITail(bFlagUpdateAutocommit, bCloseStat, bCloseConn, welcomeKit);
	}

	private ArrayList<AnagraficaRivestizioneCSI> listAnagraficaRivestizioneCSITail(boolean bFlagUpdateAutocommit, boolean bCloseStat, boolean bCloseConn, String welcomeKit) throws  DaoException {
	//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
		ArrayList<AnagraficaRivestizioneCSI> list = new ArrayList<AnagraficaRivestizioneCSI>();
		Connection connection = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		//fine LP PG21XX04 Leak
		try {
			//connection = getConnection(); //LP 20240916 - PGNTCORE-24/PGNTWPB-3
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = Helper.prepareCall(connection, getSchema(), ANAGRAFICA_RIVESTIZIONE_CSI);
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
			//callableStatement = Helper.prepareCall(connection, getSchema(), ANAGRAFICA_RIVESTIZIONE_CSI);
			callableStatement = prepareCall(bFlagUpdateAutocommit, ANAGRAFICA_RIVESTIZIONE_CSI);
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, welcomeKit);
			callableStatement.registerOutParameter(2, Types.INTEGER);
			callableStatement.registerOutParameter(3, Types.VARCHAR);
			callableStatement.registerOutParameter(4, Types.VARCHAR);
			callableStatement.execute();
			// save output parameters as attributes
			attributes = new Properties();
			attributes.setProperty("RETURNCODE", callableStatement.getString(2));
			attributes.setProperty("RETURNMESSAGE", callableStatement.getString(3));
			attributes.setProperty("IDENTIFICATIVO_FILE", callableStatement.getString(4));
			//inizio LP PG21XX04 Leak
			//ResultSet resultSet = callableStatement.getResultSet();
			resultSet = callableStatement.getResultSet();
			//fine LP PG21XX04 Leak
			if (resultSet != null && resultSet.next()) {
				do {					
					int i = 1;
					AnagraficaRivestizioneCSI item = new AnagraficaRivestizioneCSI();
					item.setCodiceFiscale(resultSet.getString(i++));          
					item.setCodiceAttivazione(resultSet.getString(i++));		 
					item.setDenominazione(resultSet.getString(i++));		 
					list.add(item);
				} while(resultSet.next());
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
			if(bCloseStat) {
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//fine LP PG21XX04 Leak
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
			}
			if(bCloseConn) {
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						throw new DaoException(e);
					}
				}
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
			}
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			//fine LP PG21XX04 Leak
		} 
		return list;
	}

	public ArrayList<AnagraficaRivestizione500> listAnagraficaRivestizione500(String welcomeKit, String societa, String ente) throws  DaoException {
	//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
		return listAnagraficaRivestizione500Tail(true, true, true, welcomeKit, societa, ente);
	}

	public ArrayList<AnagraficaRivestizione500> listAnagraficaRivestizione500Batch(boolean bFlagUpdateAutocommit, boolean bCloseStat, boolean bCloseConn, String welcomeKit, String societa, String ente) throws  DaoException {
		return listAnagraficaRivestizione500Tail(bFlagUpdateAutocommit, bCloseStat, bCloseConn, welcomeKit, societa, ente);
	}

	private ArrayList<AnagraficaRivestizione500> listAnagraficaRivestizione500Tail(boolean bFlagUpdateAutocommit, boolean bCloseStat, boolean bCloseConn, String welcomeKit, String societa, String ente) throws  DaoException {
	//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
		ArrayList<AnagraficaRivestizione500> list = new ArrayList<AnagraficaRivestizione500>();
		Connection connection = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		//fine LP PG21XX04 Leak
		try {
			connection = getConnection(); //LP 20240916 - PGNTCORE-24/PGNTWPB-3
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = Helper.prepareCall(connection, getSchema(), ANAGRAFICA_RIVESTIZIONE_500);
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
			//callableStatement = Helper.prepareCall(connection, getSchema(), ANAGRAFICA_RIVESTIZIONE_500);
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
			callableStatement = prepareCall(ANAGRAFICA_RIVESTIZIONE_500);
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, welcomeKit);
			callableStatement.setString(2, societa);
			callableStatement.setString(3, ente);
			callableStatement.registerOutParameter(4, Types.INTEGER);
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			callableStatement.registerOutParameter(6, Types.VARCHAR);
			callableStatement.registerOutParameter(7, Types.VARCHAR);
			callableStatement.execute();
			// save output parameters as attributes
			attributes = new Properties();
			attributes.setProperty("RETURNCODE", callableStatement.getString(4));
			attributes.setProperty("RETURNMESSAGE", callableStatement.getString(5));
			attributes.setProperty("SOCIETA", societa);
			attributes.setProperty("SOCIETA_DESCRIZIONE", callableStatement.getString(6));
			attributes.setProperty("ENTE", ente);
			attributes.setProperty("ENTE_DESCRIZIONE", callableStatement.getString(7));
			//inizio LP PG21XX04 Leak
			//ResultSet resultSet = callableStatement.getResultSet();
			resultSet = callableStatement.getResultSet();
			//fine LP PG21XX04 Leak
			if (resultSet != null && resultSet.next()) {
				do {					
					int i = 1;
					AnagraficaRivestizione500 item = new AnagraficaRivestizione500();
					item.setIdentificativoContribuente(resultSet.getString(i++));          
					item.setTipoAnagrafica(resultSet.getString(i++));		 
					item.setCfPiva(resultSet.getString(i++));		 
					item.setCognome(resultSet.getString(i++));		 
					item.setNome(resultSet.getString(i++));		 
					item.setSesso(resultSet.getString(i++));		 
					item.setDataNascita(resultSet.getString(i++));		 
					item.setComuneNascita(resultSet.getString(i++));		 
					item.setProvinciaNascita(resultSet.getString(i++));		 
					item.setDenominazione(resultSet.getString(i++));		 
					item.setIndirizzo(resultSet.getString(i++));		 
					item.setComune(resultSet.getString(i++));		 
					item.setProvincia(resultSet.getString(i++));		 
					item.setCap(resultSet.getString(i++));		 
					list.add(item);
				} while(resultSet.next());
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
			if(bCloseStat) {
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//fine LP PG21XX04 Leak
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
			}
			if(bCloseConn) {
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						throw new DaoException(e);
					}
				}
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
			}
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			//fine LP PG21XX04 Leak
		} 
		return list;
	}

	public void updateAnagraficaRivestizione511(String codiceFiscale, String cap, String indirizzo, String provincia, String comune) throws DaoException {
	//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
		updateAnagraficaRivestizione511Tail(true, true, codiceFiscale, cap, indirizzo, provincia, comune); 
	}
	
	public void updateAnagraficaRivestizione511Batch(boolean bFlagUpdateAutocommit, boolean bCloseStat, String codiceFiscale, String cap, String indirizzo, String provincia, String comune) throws DaoException {
		updateAnagraficaRivestizione511Tail(bFlagUpdateAutocommit, bCloseStat, codiceFiscale, cap, indirizzo, provincia, comune); 
	}

	private void updateAnagraficaRivestizione511Tail(boolean bFlagUpdateAutocommit, boolean bCloseStat, String codiceFiscale, String cap, String indirizzo, String provincia, String comune) throws DaoException {
	//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
		//Connection connection = null; //LP 20240916 - PGNTCORE-24/PGNTWPB-3
		CallableStatement callableStatement = null;
		try {
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
			//connection = getConnection();
			//callableStatement = Helper.prepareCall(connection, getSchema(), ANAGRAFICA_RIVESTIZIONE_511);
			callableStatement =  prepareCall(bFlagUpdateAutocommit, ANAGRAFICA_RIVESTIZIONE_511);
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
			callableStatement.setString(1, codiceFiscale);
			callableStatement.setString(2, cap);
			callableStatement.setString(3, indirizzo);
			callableStatement.setString(4, provincia);
			callableStatement.setString(5, comune);
			callableStatement.registerOutParameter(6, Types.INTEGER);
			callableStatement.registerOutParameter(7, Types.VARCHAR);
			callableStatement.execute();		
			// save output parameters as attributes
			attributes = new Properties();
			attributes.setProperty("RETURNCODE", callableStatement.getString(6));
			attributes.setProperty("RETURNMESSAGE", callableStatement.getString(7));
		} catch (SQLException e) {
			System.out.println("ERRORE1:" );
			System.out.println("codiceFiscale:" + codiceFiscale);
			System.out.println("cap:" + cap);
			System.out.println("indirizzo:" + indirizzo);
			System.out.println("provincia:" + provincia);
			System.out.println("comune:" + comune);
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			System.out.println("ERRORE2:" );
			System.out.println("codiceFiscale:" + codiceFiscale);
			System.out.println("cap:" + cap);
			System.out.println("indirizzo:" + indirizzo);
			System.out.println("provincia:" + provincia);
			System.out.println("comune:" + comune);
			throw new DaoException(e);
		} catch (Exception e) {
			System.out.println("ERRORE3:" );
			System.out.println("codiceFiscale:" + codiceFiscale);
			System.out.println("cap:" + cap);
			System.out.println("indirizzo:" + indirizzo);
			System.out.println("provincia:" + provincia);
			System.out.println("comune:" + comune);
			throw new DaoException(e);
		} finally {
//			if (connection != null) {
//				try {
//					connection.close(); 
//				} catch (SQLException e) {
//					throw new DaoException(e);
//				}
//			}
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
			if(bCloseStat) {
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
			}
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
			//fine LP PG21XX04 Leak
		} 
	}

	public TreeSet<AnagraficaRivestizione512> listAnagraficaRivestizione512(String welcomeKit, int lunghezzaAnagrafica, String ente, String tipoElab, String codiceIban, String flagRivestizione) throws DaoException {
	//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
		return listAnagraficaRivestizione512Tail(true, true, true, welcomeKit, lunghezzaAnagrafica, ente, tipoElab, codiceIban, flagRivestizione);
	}

	public TreeSet<AnagraficaRivestizione512> listAnagraficaRivestizione512Batch(boolean bFlagUpdateAutocommit, boolean bCloseStat, boolean bCloseConn, String welcomeKit, int lunghezzaAnagrafica, String ente, String tipoElab, String codiceIban, String flagRivestizione) throws DaoException {
		return listAnagraficaRivestizione512Tail(bFlagUpdateAutocommit, bCloseStat, bCloseConn, welcomeKit, lunghezzaAnagrafica, ente, tipoElab, codiceIban, flagRivestizione);
	}
	
	private TreeSet<AnagraficaRivestizione512> listAnagraficaRivestizione512Tail(boolean bFlagUpdateAutocommit, boolean bCloseStat, boolean bCloseConn, String welcomeKit, int lunghezzaAnagrafica, String ente, String tipoElab, String codiceIban, String flagRivestizione) throws DaoException {
	//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
		TreeSet<AnagraficaRivestizione512> list = new TreeSet<AnagraficaRivestizione512>();
		Connection connection = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		//fine LP PG21XX04 Leak
		try {
			//connection = getConnection(); //LP 20240916 - PGNTCORE-24/PGNTWPB-3
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = Helper.prepareCall(connection, getSchema(), ANAGRAFICA_RIVESTIZIONE_512);
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
			//callableStatement = Helper.prepareCall(connection, getSchema(), ANAGRAFICA_RIVESTIZIONE_512);
			callableStatement = prepareCall(bFlagUpdateAutocommit, ANAGRAFICA_RIVESTIZIONE_512);
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, welcomeKit);
			callableStatement.setInt(2, lunghezzaAnagrafica);
			callableStatement.setString(3, ente);
			callableStatement.setString(4, tipoElab);
			callableStatement.setString(5, codiceIban);
			callableStatement.setString(6, flagRivestizione);
			callableStatement.registerOutParameter(7, Types.INTEGER);
			callableStatement.registerOutParameter(8, Types.VARCHAR);
			callableStatement.execute();
			// save output parameters as attributes
			attributes = new Properties();
			attributes.setProperty("RETURNCODE", callableStatement.getString(7));
			attributes.setProperty("RETURNMESSAGE", callableStatement.getString(8));
			do {
				//inizio LP PG21XX04 Leak
				//ResultSet resultSet = callableStatement.getResultSet();
				resultSet = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (resultSet != null && resultSet.next()) {
					do {					
						String chiave = resultSet.getString(1);
						String stato = resultSet.getString(2); 
						int numero = resultSet.getInt(3);
						
						StringBuilder data = new StringBuilder();
						for(int i = 4; i < numero; i++) {
							String value = resultSet.getString(i);
							data.append(value);          
						}
						
						String sortableKey = new StringBuilder(chiave).reverse().toString();
						
						AnagraficaRivestizione512 item = new AnagraficaRivestizione512();
						item.setChiave(sortableKey);
						item.setStato(stato);
						item.setData(data.toString());
						
						list.add(item);
						
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
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
			if(bCloseStat) {
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//fine LP PG21XX04 Leak
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
			}
			if(bCloseConn) {
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						throw new DaoException(e);
					}
				}
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
			}
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			//fine LP PG21XX04 Leak
		} 
		
		return list;
	}
	
	//12022015 GG PG140450 - inizio
	public TreeSet<AnagraficaRivestizione512> listAnagraficaRivestizione512Ec(String welcomeKit, int lunghezzaAnagrafica, String ente, String tipoElab, String codiceIban,String flagRivestizione, String flagPresenzaEc,String flagAttivazioneEc) throws DaoException {
	//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
		return listAnagraficaRivestizione512EcTail(true, true, true, welcomeKit, lunghezzaAnagrafica, ente, tipoElab, codiceIban, flagRivestizione, flagPresenzaEc, flagAttivazioneEc);
		
	}

	public TreeSet<AnagraficaRivestizione512> listAnagraficaRivestizione512EcBatch(boolean bFlagUpdateAutocommit, boolean bCloseStat, boolean bCloseConn, String welcomeKit, int lunghezzaAnagrafica, String ente,String tipoElab,String codiceIban,String flagRivestizione,String flagPresenzaEc,String flagAttivazioneEc) throws DaoException {
		return listAnagraficaRivestizione512EcTail(bFlagUpdateAutocommit, bCloseStat, bCloseConn, welcomeKit, lunghezzaAnagrafica, ente, tipoElab, codiceIban, flagRivestizione, flagPresenzaEc, flagAttivazioneEc);
	}
		
	private TreeSet<AnagraficaRivestizione512> listAnagraficaRivestizione512EcTail(boolean bFlagUpdateAutocommit, boolean bCloseStat, boolean bCloseConn, String welcomeKit, int lunghezzaAnagrafica, String ente, String tipoElab, String codiceIban, String flagRivestizione, String flagPresenzaEc, String flagAttivazioneEc) throws DaoException {
	//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
		TreeSet<AnagraficaRivestizione512> list = new TreeSet<AnagraficaRivestizione512>();
		Connection connection = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		//fine LP PG21XX04 Leak
		try {
			//connection = getConnection();//LP 20240916 - PGNTCORE-24/PGNTWPB-3
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = Helper.prepareCall(connection, getSchema(), ANAGRAFICA_RIVESTIZIONE_512_EC);
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
			//callableStatement = Helper.prepareCall(connection, getSchema(), ANAGRAFICA_RIVESTIZIONE_512_EC);
			callableStatement = prepareCall(bFlagUpdateAutocommit, ANAGRAFICA_RIVESTIZIONE_512_EC);
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, welcomeKit);
			callableStatement.setInt(2, lunghezzaAnagrafica);
			callableStatement.setString(3, ente);
			callableStatement.setString(4, tipoElab);
			callableStatement.setString(5, codiceIban);
			callableStatement.setString(6, flagRivestizione);
			callableStatement.setString(7, flagPresenzaEc);
			callableStatement.setString(8, flagAttivazioneEc);
			callableStatement.registerOutParameter(9, Types.INTEGER);
			callableStatement.registerOutParameter(10, Types.VARCHAR);
			callableStatement.execute();
			// save output parameters as attributes
			attributes = new Properties();
			attributes.setProperty("RETURNCODE", callableStatement.getString(9));
			attributes.setProperty("RETURNMESSAGE", callableStatement.getString(10));
			do {
				//inizio LP PG21XX04 Leak
				//ResultSet resultSet = callableStatement.getResultSet();
				resultSet = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (resultSet != null && resultSet.next()) {
					do {					
						String chiave = resultSet.getString(1);
						String stato = resultSet.getString(2); 
						int numero = resultSet.getInt(3);
						StringBuilder data = new StringBuilder();
						for(int i = 4; i < numero; i++) {
							String value = resultSet.getString(i);
							data.append(value);          
						}
						String sortableKey = new StringBuilder(chiave).reverse().toString();
						AnagraficaRivestizione512 item = new AnagraficaRivestizione512();
						item.setChiave(sortableKey);
						item.setStato(stato);
						item.setData(data.toString());
						list.add(item);
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
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
			if(bCloseStat) {
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//fine LP PG21XX04 Leak
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
			}
			if(bCloseConn) {
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						throw new DaoException(e);
					}
				}
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
			}
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			//fine LP PG21XX04 Leak
		} 
		
		return list;
	}
	//12022015 GG PG140450 - fine
	
	//public TreeSet<AnagraficaRivestizione512> AnagraficaRivestizione512OnlyOne(int lunghezzaAnagrafica, String ente,String tipoElab,String codiceIban,String IdWallet,String testaCoda) throws DaoException {
	public TreeSet<AnagraficaRivestizione512> AnagraficaRivestizione512OnlyOne(int lunghezzaAnagrafica, String ente,String tipoElab,String codiceIban,String IdWallet,String testaCoda, String numeroCC, String intestatarioCC) throws DaoException {
		TreeSet<AnagraficaRivestizione512> list = new TreeSet<AnagraficaRivestizione512>();
		//Connection connection = null; //LP 20240916 - PGNTCORE-24/PGNTWPB-3
		//CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		CallableStatement callableStatementANARIV512ONE = null; //LP 20240916 - PGNTCORE-24/PGNTWPB-3
		try {
			//connection = getConnection(); //LP 20240916 - PGNTCORE-24/PGNTWPB-3
			callableStatementANARIV512ONE = prepareCall(ANAGRAFICA_RIVESTIZIONE_512_ONLY_ONE);
			callableStatementANARIV512ONE.setInt(1, lunghezzaAnagrafica);
			callableStatementANARIV512ONE.setString(2, ente);
			callableStatementANARIV512ONE.setString(3, tipoElab);
			callableStatementANARIV512ONE.setString(4, codiceIban);
			callableStatementANARIV512ONE.setString(5, IdWallet);
			callableStatementANARIV512ONE.setString(6, testaCoda);
			callableStatementANARIV512ONE.setString(7, numeroCC.trim());
			callableStatementANARIV512ONE.setString(8, intestatarioCC.trim());
			callableStatementANARIV512ONE.registerOutParameter(9, Types.INTEGER);
			callableStatementANARIV512ONE.registerOutParameter(10, Types.VARCHAR);
			callableStatementANARIV512ONE.execute();
			// save output parameters as attributes
			attributes = new Properties();
			attributes.setProperty("RETURNCODE", callableStatementANARIV512ONE.getString(9));
			attributes.setProperty("RETURNMESSAGE", callableStatementANARIV512ONE.getString(10));
			do {
				resultSet = callableStatementANARIV512ONE.getResultSet();
				if (resultSet != null ) {
					if (resultSet.next()) {
						do {					
							String chiave = resultSet.getString(1);
							String stato = resultSet.getString(2); 
							int numero = resultSet.getInt(3);
							StringBuilder data = new StringBuilder();
							for(int i = 4; i < numero; i++) {
								String value = resultSet.getString(i);
								data.append(value);          
							}
							String sortableKey = new StringBuilder(chiave).reverse().toString();
							AnagraficaRivestizione512 item = new AnagraficaRivestizione512();
							item.setChiave(sortableKey);
							item.setStato(stato);
							item.setData(data.toString());
							list.add(item);
						} while(resultSet.next());
				    }
				}
			} while (callableStatementANARIV512ONE.getMoreResults());
		} catch (SQLException e) {
			System.out.println("errore AnagraficaRivestizione512OnlyOne = " + e.getMessage()) ;
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			System.out.println("errore AnagraficaRivestizione512OnlyOne = " + e.getMessage()) ;
			throw new DaoException(e);
		} catch (HelperException e) {
			System.out.println("errore AnagraficaRivestizione512OnlyOne = " + e.getMessage()) ;
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(resultSet);
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//inizioLP 20240916 - PGNTCORE-24/PGNTWPB-3
			if (callableStatementANARIV512ONE != null) {
				try {
					callableStatementANARIV512ONE.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
			//fine LP PG21XX04 Leak
//			DAOHelper.closeIgnoringException(callableStatement);
//			if (connection != null) {
//				try {
//					connection.close();
//				} catch (SQLException e) {
//					throw new DaoException(e);
//				}
//			}
//			DAOHelper.closeIgnoringException(connection);
			
		} 
		
		return list;
	}	
}
