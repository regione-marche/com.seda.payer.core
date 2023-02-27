package com.seda.payer.core.mercato.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

import com.seda.commons.string.Convert;
import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.Helper;
import com.seda.data.helper.HelperException;
import com.seda.data.spi.PageInfo;
import com.seda.payer.core.dao.Routines;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
//import com.seda.payer.core.mercato.bean.EsitoRisposte;
//import com.seda.payer.core.mercato.bean.MercatoPageList;
//import com.seda.payer.core.mercato.bean.ConfigurazioneAnagAutorizzazione;
//import com.seda.payer.core.mercato.bean.ConfigurazioneTariffe;
import com.seda.payer.core.mercato.bean.MercatoPageList;
import com.seda.payer.core.mercato.bean.MonitoraggioMercati;
//import com.seda.payer.core.mercato.dao.MercatoDAO;

public class MonitoraggioMercatiDAOImpl extends BaseDaoHandler implements MonitoraggioMercatiDAO  {
	private static final long serialVersionUID = 1L;
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public MonitoraggioMercatiDAOImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}
	//inizio LP PG21XX04 Leak
	public MonitoraggioMercatiDAOImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}
	//fine LP PG21XX04 Leak
	

	public List<MonitoraggioMercati> doList(MonitoraggioMercati monitor) throws DaoException {
		CallableStatement callableStatement=null;
		List<MonitoraggioMercati> listMonitoraggioMercati = null;
		Connection connection = null;
		ResultSet resultSet=null;
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYTAMSP_MTT.routine());
			callableStatement.setString(1, monitor.getCodiceSocieta());
			callableStatement.setString(2, monitor.getCodUt());
			callableStatement.setString(3, monitor.getDescrizioneEnte());
			callableStatement.setString(4, monitor.getCodiceFiscaleAutorizzazione());
			callableStatement.setString(5, monitor.getCodiceKeyMercato());
			callableStatement.setString(6, monitor.getCodiceKeyPiazzola());
			callableStatement.setString(7, monitor.getCodiceAutorizzazione());
			if (monitor.getDataInizio()==null) {
				callableStatement.setTimestamp(8, null);
			} else {
				callableStatement.setTimestamp(8, new java.sql.Timestamp(monitor.getDataInizio().getTimeInMillis()));
			}
			if (monitor.getDataFine()==null) {
				callableStatement.setTimestamp(9, null);
			} else {
			    callableStatement.setTimestamp(9, new java.sql.Timestamp(monitor.getDataFine().getTimeInMillis()));
			}
			if ((monitor.getPagato()==null)||(monitor.getPagato().equals(""))) {
				callableStatement.setString(10, "");
			} else if (monitor.getPagato().equals("Y")) {
				callableStatement.setString(10, "Y");
			} else if (monitor.getPagato().equals("N")) {
				callableStatement.setString(10, "N");
			}
			
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();
			
			if ( !resultSet.next() ) {
				listMonitoraggioMercati = new ArrayList<MonitoraggioMercati>();
			} else {
				listMonitoraggioMercati = new ArrayList<MonitoraggioMercati>();
				do {
					MonitoraggioMercati item = new MonitoraggioMercati();
					item.setCodiceAutorizzazione(resultSet.getString(9));
					item.setCodiceFiscaleAutorizzazione(resultSet.getString(11));
					item.setCodiceMercato(resultSet.getString(5));
					item.setCodicePiazzola(resultSet.getString(7));
					item.setCodUt(resultSet.getString(2));
					item.setDescrizioneEnte(resultSet.getString(3));
					item.setCodiceSocieta(resultSet.getString(1));
					item.setDescrizioneMercato(resultSet.getString(6));
					item.setDescrizionePiazzola(resultSet.getString(8));
					switch (resultSet.getInt(14)) {
					case 1:
						item.setGiornoSettimana("Lunedì");
						break;
					case 2:
						item.setGiornoSettimana("Martedì");
						break;
					case 3:
						item.setGiornoSettimana("Mercoledì");
						break;
					case 4:
						item.setGiornoSettimana("Giovedì");
						break;
					case 5:
						item.setGiornoSettimana("Venerdì");
						break;
					case 6:
						item.setGiornoSettimana("Sabato");
						break;
					case 7:
						item.setGiornoSettimana("Domenica");
						break;
					default:
						item.setGiornoSettimana("//");
						break;
					}
					item.setNominativoAutorizzazione(resultSet.getString(10));
					item.setImportoCosap(resultSet.getFloat(16));
					item.setImportoTari(resultSet.getFloat(15));
					item.setImportoDovuto(resultSet.getFloat(19));
					item.setImportoCompenso(resultSet.getFloat(20) + resultSet.getFloat(22));
					item.setPeriodoGiornaliero(resultSet.getString(13));
					item.setTipologiaBanco(resultSet.getString(12));
					GregorianCalendar DataPrenot = new GregorianCalendar();
					DataPrenot.setTimeInMillis(resultSet.getTimestamp(18).getTime());					
					item.setDataPrenotazione(DataPrenot);
					if (resultSet.getFloat(19)>0.0) {
						item.setPagato("Y");
					} else {
						item.setPagato("N");
					}
					GregorianCalendar DataPagam = new GregorianCalendar();
					if (resultSet.getTimestamp(21)== null) {
						DataPagam.setTimeInMillis(4102444799000L);
					} else {
						DataPagam.setTimeInMillis(resultSet.getTimestamp(21).getTime());
					}
					item.setDataOraPagamento(DataPagam);
					listMonitoraggioMercati.add(item);
				} while(resultSet.next());
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
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
		
		return listMonitoraggioMercati;
		
	}	
	
	public MercatoPageList ListMonitoraggioMercati(
			MonitoraggioMercati monitoraggio,
			int rowsPerPage, int pageNumber, String OrderBy)
			throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		PageInfo pageInfo = null;
		MercatoPageList mercatoPageList = null;
		try {

//			    IN I_PAGENO SMALLINT,
//				IN I_ROWSPERPAGE SMALLINT,
//				IN I_TAM_CSOCCSOC VARCHAR(5),
//				IN I_TAM_CUTECUTE VARCHAR(5),
//				IN I_TAM_KANEKENT CHAR(10),
//				IN I_TAM_CTAMCFPI VARCHAR(20),
//				IN I_TAM_CTAMKMRC VARCHAR(10),
//				IN I_TAM_CTAMKPZL VARCHAR(64),
//				IN I_TAM_CAUTNMAU VARCHAR(30),
//				IN I_TAM_GTAMDTIN TIMESTAMP,
//				IN I_TAM_GTAMDTFN TIMESTAMP,
//				IN I_TAM_CTAMFLPG CHAR(1),
//				OUT O_ROWINI INTEGER,
//				OUT O_ROWEND INTEGER,
//				OUT O_TOTROWS INTEGER,
//				OUT O_TOTPAGES SMALLINT
				
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYTAMSP_MNL.routine());
			callableStatement.setInt(1, pageNumber);                         /* rows per page */
			callableStatement.setInt(2, rowsPerPage);                        /* page number*/
			callableStatement.setString(3,monitoraggio.getCodiceSocieta());
			callableStatement.setString(4,monitoraggio.getCodUt());
			callableStatement.setString(5,monitoraggio.getDescrizioneEnte());
			callableStatement.setString(6,monitoraggio.getCodiceFiscaleAutorizzazione());
			callableStatement.setString(7,monitoraggio.getCodiceKeyMercato());
			callableStatement.setString(8,monitoraggio.getCodiceKeyPiazzola());
			callableStatement.setString(9,monitoraggio.getCodiceAutorizzazione());
			if (monitoraggio.getDataInizio()==null) {
				callableStatement.setTimestamp(10, null);
			} else {
				callableStatement.setTimestamp(10, new java.sql.Timestamp(monitoraggio.getDataInizio().getTimeInMillis()));
			}
			if (monitoraggio.getDataFine()==null) {
				callableStatement.setTimestamp(11, null);
			} else {
				callableStatement.setTimestamp(11, new java.sql.Timestamp(monitoraggio.getDataFine().getTimeInMillis()));
			}
			if ((monitoraggio.getPagato()==null)||(monitoraggio.getPagato().equals(""))) {
				callableStatement.setString(12, null);
			} else if (monitoraggio.getPagato().equals("Y")) {
				callableStatement.setString(12, "Y");
			} else if (monitoraggio.getPagato().equals("N")) {
				callableStatement.setString(12, "N");
			}
			/* we register row start */
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(14, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(15, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(16, Types.SMALLINT);
			 
			/* we execute procedure */
			if(callableStatement.execute())	{
				pageInfo = new PageInfo();
				pageInfo.setPageNumber(pageNumber);
				pageInfo.setRowsPerPage(rowsPerPage);
				pageInfo.setFirstRow(callableStatement.getInt(13));
				pageInfo.setLastRow(callableStatement.getInt(14));
				pageInfo.setNumRows(callableStatement.getInt(15));
				pageInfo.setNumPages(callableStatement.getInt(16));
				
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				mercatoPageList = new MercatoPageList(pageInfo, "00","",getWebRowSetXml());
				return mercatoPageList;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			mercatoPageList = new MercatoPageList(pageInfo, "01","Sql-Exception","");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			mercatoPageList = new MercatoPageList(pageInfo, "01","Sql-Exception","");
		} catch (HelperException e) {
			e.printStackTrace();
			mercatoPageList = new MercatoPageList(pageInfo, "01","Sql-Exception","");
		} finally {
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
		return mercatoPageList;	
	}

	public MonitoraggioMercati getPerKey(MonitoraggioMercati monitor)
		throws DaoException 
	{
		CallableStatement callableStatement=null;
		ResultSet resultSet=null;
		Connection connection = null;
		CachedRowSet rowSet = null;
		try {
			connection = getConnection();
		
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYTAMSP_MON.routine());
//			IN I_TAM_KTAMKTAM VARCHAR(64), 
//			IN I_PRN_KPRNKPRN VARCHAR(64)
					
			callableStatement.setString(1, monitor.getCodiceTariffa());
			callableStatement.setString(2, monitor.getCodicePrenotazione());
			callableStatement.execute();
			
			resultSet=callableStatement.getResultSet();
			loadWebRowSet(resultSet);
			String selectXml = getWebRowSetXml();
			try {
				rowSet = Convert.stringToWebRowSet(selectXml);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (rowSet.next() ) {
				String codUt=rowSet.getString(1);                  
				String codiceTariffa=rowSet.getString(2);
				String codiceAutorizzazione=rowSet.getString(3);
				String codiceSocieta=rowSet.getString(4);
				String descrizioneEnte=rowSet.getString(5);
				String codiceKeyMercato=rowSet.getString(6);
				String codiceMercato=rowSet.getString(7);
				String descrizioneMercato=rowSet.getString(8);
				String codiceKeyPiazzola=rowSet.getString(9);
				String codicePiazzola=rowSet.getString(10);
				String descrizionePiazzola=rowSet.getString(11);
				String nominativoAutorizzazione=rowSet.getString(12);
				String codiceFiscaleAutor=rowSet.getString(13);
				String tipologiaBanco=rowSet.getString(14);
				String periodoGiornaliero=rowSet.getString(15);
				String giornoSettimana=rowSet.getString(16);
				String codicePrenotazione=rowSet.getString(17);
				Calendar dataOraPagamento = new GregorianCalendar();
				dataOraPagamento.setTimeInMillis(rowSet.getTimestamp(18).getTime());
				Calendar dataPrenotazione = new GregorianCalendar();
				dataPrenotazione.setTimeInMillis(rowSet.getTimestamp(19).getTime());
				Calendar dataInizio = new GregorianCalendar();
				dataInizio.setTimeInMillis(rowSet.getTimestamp(20).getTime());
				Calendar dataFine = new GregorianCalendar();
				dataFine.setTimeInMillis(rowSet.getTimestamp(21).getTime());
				Float importoDovuto=rowSet.getFloat(22);
				Float importoTari=rowSet.getFloat(23);
				Float importoCosap=rowSet.getFloat(24);
				Float importoCompenso=rowSet.getFloat(25) + rowSet.getFloat(27);
				String pagato=rowSet.getString(26);

				 monitor = new MonitoraggioMercati(
						 codUt, codiceTariffa, codiceAutorizzazione, codiceSocieta, descrizioneEnte, codiceKeyMercato, codiceMercato,
						 descrizioneMercato, codiceKeyPiazzola, codicePiazzola, descrizionePiazzola, nominativoAutorizzazione, codiceFiscaleAutor,  
						 tipologiaBanco, periodoGiornaliero, giornoSettimana, codicePrenotazione, dataOraPagamento, dataPrenotazione, 
						 dataInizio, dataFine, importoDovuto, importoTari, importoCosap, importoCompenso, pagato);
			
				 monitor.setAttribute(MercatoDAO.SELECT_XML, selectXml);
			} 
			
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		}finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if (rowSet != null) {
				try {
					rowSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
		return monitor;	
		
	}
	
// Termine della Classe	
}
