package com.seda.payer.core.wallet.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import javax.sql.DataSource;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.dao.Routines;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.wallet.bean.PresenzeGiornaliere;
import com.seda.payer.core.wallet.bean.Wallet;

public class PresenzeGiornaliereDAOImpl extends BaseDaoHandler implements PresenzeGiornaliereDAO  { 
	//inizio LP 20240918 - PGNTCORE-24/PGNTWPB-3
	//private static final long serialVersionUID = 1L;
	//fine LP 20240918 - PGNTCORE-24/PGNTWPB-3

	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public PresenzeGiornaliereDAOImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}
	public PresenzeGiornaliereDAOImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema); 
	}
	public ArrayList <String> listTributi(Wallet wallet) throws DaoException {
		CallableStatement callableStatement=null;
		ArrayList<String> tributoList = null;
		Connection connection = null;
		ResultSet resultSet=null;
		try {
			connection = getConnection();
			//inizio LP 20240921 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYSRVSP_LST.routine());
			callableStatement = prepareCall(Routines.PYSRVSP_LST.routine());
			//fine LP 20240921 - PGNTCORE-24
			callableStatement.setString(1,wallet.getCodiceSocieta());
			callableStatement.setString(2,wallet.getCuteCute());
			callableStatement.setString(3,wallet.getChiaveEnte());
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();
			
			if ( !resultSet.next() ) {
				tributoList = new ArrayList<String>();
			} else {
				tributoList = new ArrayList<String>();
				do {
					String item;
					item = resultSet.getString("PRM_CPRMTRIB");
					tributoList.add(item);
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
		return tributoList;
	}

	public ArrayList<PresenzeGiornaliere> listAnnoScolastico(PresenzeGiornaliere presenzeGiornaliere) throws DaoException {
		CallableStatement callableStatement=null;
		ArrayList<PresenzeGiornaliere> listAnnoscolastico = null;
		Connection connection = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			//inizio LP 20240921 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYAFMSP_LST_ANNO_SCOL.routine());
			callableStatement = prepareCall(Routines.PYAFMSP_LST_ANNO_SCOL.routine());
			//fine LP 20240921 - PGNTCORE-24
			callableStatement.setString(1,presenzeGiornaliere.getIdWallet());
			callableStatement.setString(2,presenzeGiornaliere.getCodiceServizio());
			callableStatement.setString(3,presenzeGiornaliere.getCodiceScuola());
			callableStatement.setString(4,presenzeGiornaliere.getCodiceAnagraficaFiglio());
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();
			if ( !resultSet.next() ) {
				listAnnoscolastico = new ArrayList<PresenzeGiornaliere>();
			} else {
				listAnnoscolastico = new ArrayList<PresenzeGiornaliere>();
				do {
					PresenzeGiornaliere item = new PresenzeGiornaliere();
					item.setAnnoScolastico(resultSet.getString("ANNO_SCOLASTICO"));
					listAnnoscolastico.add(item);
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
		return listAnnoscolastico;
	}

	//inizio LP PG21XX04
	//Nota. La chiusura della connection è affidata al chiamante.
	//fine LP PG21XX04
	//inizio LP 20240918 - PGNTCORE-24/PGNTWPB-3
	public PresenzeGiornaliere insertBatch(PresenzeGiornaliere presenzeGiornaliere) throws Exception  {
		return insertBatchTail(true, true, presenzeGiornaliere);
	}

	public PresenzeGiornaliere insertBatchTail(boolean bflagUpdateCommit, boolean bCloseStat, PresenzeGiornaliere presenzeGiornaliere) throws Exception  {
	//fine LP 20240918 - PGNTCORE-24/PGNTWPB-3
		CallableStatement callableStatement = null;
		//Connection connection = null; //LP 20240918 - PGNTCORE-24/PGNTWPB-3
		String errori = "";
		try {
			//inizio LP 20240918 - PGNTCORE-24/PGNTWPB-3
			//connection = getConnection();
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPRMSP_INS.routine());
			callableStatement = prepareCall(bflagUpdateCommit, Routines.PYPRMSP_INS.routine());
			//fine LP 20240918 - PGNTCORE-24/PGNTWPB-3
			callableStatement.setString(1, presenzeGiornaliere.getCuteCute()); 
			callableStatement.setString(2, presenzeGiornaliere.getCodiceEnte());
			callableStatement.setString(3, presenzeGiornaliere.getCodiceAnagraficaFiglio());
			callableStatement.setString(4, presenzeGiornaliere.getCodiceAnagraficaGenitore());
			callableStatement.setString(5, presenzeGiornaliere.getAnnoScolastico());
			callableStatement.setString(6, presenzeGiornaliere.getAnnoTributo());
			callableStatement.setString(7, presenzeGiornaliere.getCodiceTributo());
			callableStatement.setBigDecimal(8, presenzeGiornaliere.getImportoTributo());
			callableStatement.setString(9, presenzeGiornaliere.getCausale());
			callableStatement.setString(10, presenzeGiornaliere.getDescrizioneServizio());
			callableStatement.setString(11, presenzeGiornaliere.getChiaveMovimento());
			callableStatement.setString(12, presenzeGiornaliere.getCodiceScuola());
			Timestamp dataPresenza =  new Timestamp(presenzeGiornaliere.getPresenzaScuola().getTimeInMillis());
			callableStatement.setTimestamp(13,dataPresenza);
			callableStatement.setString(14,presenzeGiornaliere.getIdentificativoRecord());
			callableStatement.registerOutParameter(15, Types.VARCHAR);
			callableStatement.execute();
			errori = callableStatement.getString(15);
			presenzeGiornaliere.setAttribute("errori", errori);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new Exception(e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
			//inizio LP 20240918 - PGNTCORE-24/PGNTWPB-3
			if(bCloseStat) {
			//fine LP 20240918 - PGNTCORE-24/PGNTWPB-3
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240918 - PGNTCORE-24/PGNTWPB-3
			}
			//fine LP 20240918 - PGNTCORE-24/PGNTWPB-3
			//fine LP PG21XX04 Leak
		}
		return presenzeGiornaliere;
	}
	
	public ArrayList<Calendar> listGiorniPresenze(String idWallet,String anno,String mese,String codAnaFiglio,String codScuola,String tariffa,String causale) throws  DaoException{
		ArrayList<Calendar> listPresenze=null;
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet resultSet=null;
		try {
			connection = getConnection();
			//inizio LP 20240921 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPRMSP_SEL_DAY.routine());
			callableStatement = prepareCall(Routines.PYPRMSP_SEL_DAY.routine());
			//fine LP 20240921 - PGNTCORE-24
			callableStatement.setString(1,codAnaFiglio);
			callableStatement.setString(2,idWallet);
			callableStatement.setString(3,codScuola);
			callableStatement.setString(4,tariffa);
			callableStatement.setString(5,mese);
			callableStatement.setString(6,anno);
			callableStatement.setString(7,causale);
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();
			if ( !resultSet.next() ) {
				listPresenze = new ArrayList<Calendar>();
			} else {
				listPresenze = new ArrayList<Calendar>();
				do {
					Calendar calendar = Calendar.getInstance();
					Date date=null;				
					date = new SimpleDateFormat("yyyy-MM-dd").parse(anno.concat("-")+resultSet.getString(2).concat("-").concat(resultSet.getString(1)));
					calendar.setTime(date);
					listPresenze.add(calendar);
				} while(resultSet.next());
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} catch (ParseException e) {
			e.printStackTrace();
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
		return listPresenze;
	}
	
	public HashMap<Calendar, String> listCausalePresenze(String idWallet,String anno,String mese,String codAnaFiglio,String codScuola,String tariffa,String causale) throws  DaoException{
		HashMap<Calendar, String> listPresenzeCausale=null;
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet resultSet=null;
		try {
			connection = getConnection();
			//inizio LP 20240921 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPRMSP_SEL_DAY.routine());
			callableStatement = prepareCall(Routines.PYPRMSP_SEL_DAY.routine());
			//fine LP 20240921 - PGNTCORE-24
			callableStatement.setString(1,codAnaFiglio);
			callableStatement.setString(2,idWallet);
			callableStatement.setString(3,codScuola);
			callableStatement.setString(4,tariffa);
			callableStatement.setString(5,mese);
			callableStatement.setString(6,anno);
			callableStatement.setString(7,causale);
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();
			if (!resultSet.next()) {
				listPresenzeCausale = new HashMap<Calendar, String>();
			} else {
				listPresenzeCausale = new HashMap<Calendar, String>();
				do {
					Calendar calendar = Calendar.getInstance();
					Date date=null;				
					date = new SimpleDateFormat("yyyy-MM-dd").parse(anno.concat("-")+resultSet.getString(2).concat("-").concat(resultSet.getString(1)));
					calendar.setTime(date);
					String caus = resultSet.getString(3); 
					listPresenzeCausale.put(calendar,caus);
				} while(resultSet.next());
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} catch (ParseException e) {
			e.printStackTrace();
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
		return listPresenzeCausale;
	}
	public HashMap<Calendar, String> listCausalePresenzeAbilitazione(String idWallet,
			String anno, String mese, String codAnaFiglio, String codScuola,
			String tariffa) throws DaoException {
		HashMap<Calendar, String> listPresenzeCausale=null;
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet resultSet=null;
		try {
			connection = getConnection();
			//inizio LP 20240921 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPRMSP_SEL_ABD.routine());
			callableStatement = prepareCall(Routines.PYPRMSP_SEL_ABD.routine());
			//fine LP 20240921 - PGNTCORE-24
			callableStatement.setString(1,codAnaFiglio);
			callableStatement.setString(2,idWallet);
			callableStatement.setString(3,codScuola);
			callableStatement.setString(4,tariffa);
			callableStatement.setString(5,mese);
			callableStatement.setString(6,anno);
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();
			if ( !resultSet.next() ) {
				listPresenzeCausale = new HashMap<Calendar, String>();
			} else {
				listPresenzeCausale = new HashMap<Calendar, String>();
				do {
					Calendar calendar = Calendar.getInstance();
					Date date=null;				
					date = new SimpleDateFormat("yyyy-MM-dd").parse(anno.concat("-")+resultSet.getString(2).concat("-").concat(resultSet.getString(1)));
					calendar.setTime(date);
					String caus = resultSet.getString(3); 
					caus=caus.concat("|"+resultSet.getString(4));
					caus=caus.concat("|"+resultSet.getString(5));
					caus=caus.concat("|"+resultSet.getString(7)); 
					caus=caus.concat("|"+resultSet.getString(8));
					String importo=resultSet.getString(9);
					importo=importo.replace(".", ",");
					caus=caus.concat("|"+importo);
					caus=caus.concat("|"+resultSet.getString(6));
					caus=caus.concat("|"+resultSet.getString(10));
					while(listPresenzeCausale.containsKey(calendar)){
						calendar.add(Calendar.SECOND, 1);
					}
					listPresenzeCausale.put(calendar,caus);
				} while(resultSet.next());
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} catch (ParseException e) {
			e.printStackTrace();
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
		return listPresenzeCausale;
	}
	public PresenzeGiornaliere insertDiscarico(PresenzeGiornaliere presenzeGiornaliere) throws Exception {
		CallableStatement callableStatement=null;
		Connection connection = null;
		String errori = "";
		try {
//			 I_PRM_CPRMCAUS VARCHAR(1)
//			,I_PRM_KPRMKPRM  VARCHAR(64)
//			,I_PRM_NPRMPROG INTEGER
//			,I_PRM_KBRSKBRS VARCHAR(18)
//			,I_PRM_GPRMGPRE
			connection = getConnection();
			//inizio LP 20240921 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPRMSP_INS_DIS.routine());
			callableStatement = prepareCall(Routines.PYPRMSP_INS_DIS.routine());
			//fine LP 20240921 - PGNTCORE-24
			callableStatement.setString(1, presenzeGiornaliere.getCausale()); 
			callableStatement.setString(2, presenzeGiornaliere.getIdentificativoRecord());
			callableStatement.setInt(3, Integer.valueOf((String) presenzeGiornaliere.getAttribute("progressivo")));
			callableStatement.setString(4, presenzeGiornaliere.getIdWallet());
			Timestamp dataPresenza =  new Timestamp(presenzeGiornaliere.getPresenzaScuola().getTimeInMillis());
			callableStatement.setTimestamp(5,dataPresenza);
			callableStatement.setString(6, (String) presenzeGiornaliere.getAttribute("operatoreInserimento"));
			callableStatement.registerOutParameter(7, Types.VARCHAR);
			callableStatement.execute();
			errori = callableStatement.getString(7);
			presenzeGiornaliere.setAttribute("errori", errori);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new Exception(e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
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
		return presenzeGiornaliere;
	}

	//PG150310_001 GG - inizio
	//inizio LP PG21XX04
	//Nota. La chiusura della connection è affidata al chiamante.
	//fine LP PG21XX04
	//inizio LP 20240918 - PGNTCORE-24/PGNTWPB-3
	public ArrayList<String> getCodiciFiscali(PresenzeGiornaliere presenzeGiornaliere) throws DaoException {
		return getCodiciFiscaliBatch(true, true, presenzeGiornaliere);
	}
		
	public ArrayList<String> getCodiciFiscaliBatch(boolean bflagUpdateCommit, boolean bCloseStat, PresenzeGiornaliere presenzeGiornaliere) throws DaoException {
	//fine LP 20240918 - PGNTCORE-24/PGNTWPB-3
		ArrayList<String>codiciFiscaliGenitoreFiglio=null;
		CallableStatement callableStatement=null;
		//Connection connection = null; //LP 20240918 - PGNTCORE-24/PGNTWPB-3
		String codiceFiscaleGenitore = "";
		String codiceFiscaleFiglio = "";
		try {
			//inizio LP 20240918 - PGNTCORE-24/PGNTWPB-3
			//connection = getConnection();
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYAFMSP_SEL_CFIS.routine());
			callableStatement = prepareCall(bflagUpdateCommit, Routines.PYAFMSP_SEL_CFIS.routine());
			//fine LP 20240918 - PGNTCORE-24/PGNTWPB-3
			callableStatement.setString(1, presenzeGiornaliere.getCuteCute());
			callableStatement.setString(2, presenzeGiornaliere.getCodiceEnte());
			callableStatement.setString(3, presenzeGiornaliere.getCodiceAnagraficaGenitore());
			callableStatement.setString(4, presenzeGiornaliere.getCodiceAnagraficaFiglio());
			callableStatement.setString(5, presenzeGiornaliere.getCodiceScuola());
			callableStatement.registerOutParameter(6,Types.VARCHAR);
			callableStatement.registerOutParameter(7,Types.VARCHAR);
			callableStatement.execute();
			codiciFiscaliGenitoreFiglio = new ArrayList<String>();
			codiceFiscaleGenitore = callableStatement.getString(6);
			codiceFiscaleFiglio = callableStatement.getString(7);
			codiciFiscaliGenitoreFiglio.add(codiceFiscaleGenitore);
			codiciFiscaliGenitoreFiglio.add(codiceFiscaleFiglio);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
			//inizio LP 20240918 - PGNTCORE-24/PGNTWPB-3
			if(bCloseStat) {
			//fine LP 20240918 - PGNTCORE-24/PGNTWPB-3
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240918 - PGNTCORE-24/PGNTWPB-3
				callableStatement = null;
			}
			//fine LP 20240918 - PGNTCORE-24/PGNTWPB-3
			//fine LP PG21XX04 Leak
		}
		return codiciFiscaliGenitoreFiglio;
	}		
	//PG150310_001 GG - fine
}
