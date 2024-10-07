package com.seda.payer.core.wallet.dao;

import java.io.IOException;
import java.math.BigDecimal;
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
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import com.seda.commons.string.Convert;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.PgHost;
import com.seda.payer.core.dao.Routines;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.rest.RestBaseDaoHandler;
import com.seda.payer.core.wallet.bean.PagamentoBorsellino;
import com.seda.payer.core.wallet.bean.Servizio;
import com.seda.payer.core.wallet.bean.Wallet;


public class PagamentoBorsellinoDAOImpl extends RestBaseDaoHandler implements PagamentoBorsellinoDAO {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public PagamentoBorsellinoDAOImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}

	public PagamentoBorsellinoDAOImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}
	
	public PagamentoBorsellinoDAOImpl(Connection connection, String schema, boolean isRest, String baseUrl) {
		super(connection, schema, isRest, baseUrl);
	}

	public ArrayList <PagamentoBorsellino> listPagamenti(PagamentoBorsellino pagamento) throws DaoException {
		CallableStatement callableStatement=null;
		ArrayList<PagamentoBorsellino> pagamentiList = null;
		Connection connection = null;
		ResultSet resultSet=null;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPGBSP_LST.routine());
			callableStatement = prepareCall(Routines.PYPGBSP_LST.routine());
			//fine LP PGNTCORE-24
			callableStatement.setString(1,pagamento.getIdWallet());
			callableStatement.setString(2,pagamento.getCodUtente());
			callableStatement.setString(3,pagamento.getCodEnte());
			callableStatement.setString(4,pagamento.getCodSocieta());
			callableStatement.setString(5,(String)pagamento.getAttribute("anno"));
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();

			if ( !resultSet.next() ) {
				pagamentiList = new ArrayList<PagamentoBorsellino>();
			} else {
				pagamentiList = new ArrayList<PagamentoBorsellino>();
				do {
					PagamentoBorsellino item = new PagamentoBorsellino();
					String dataPagamentoStr = resultSet.getString(1);
					SimpleDateFormat formatterIT = null;
					formatterIT = new SimpleDateFormat("yyyy-MM-dd");
					Date utilDate = (Date)formatterIT.parse(dataPagamentoStr);
					formatterIT = new SimpleDateFormat("dd/MM/yyyy");
					dataPagamentoStr = formatterIT.format(utilDate);
					item.setAttribute(PagamentoBorsellinoDAO.DATA_PAGAMENTO, dataPagamentoStr);
					item.setCanalePagamento(resultSet.getString(2));
					item.setImporto(resultSet.getBigDecimal(3));
					String importoStr = item.getImporto().toString();
					importoStr= importoStr.replace(",", "");
					importoStr= importoStr.replace(".", ",");
					item.setFlagTombstoned(resultSet.getString(6).equals("C")?false:true);
					item.setTipoPagamento(resultSet.getString(7));//Giulia
					Boolean flagCompressione = resultSet.getString(4).equals("C")?true:false;
					item.setAttribute(PagamentoBorsellinoDAO.FLAG_COMPRESSIONE, resultSet.getString(4));
					String descrizione = "";
					item.setAttribute(PagamentoBorsellinoDAO.MESE, dataPagamentoStr.substring(3,5));
					item.setAttribute(PagamentoBorsellinoDAO.ANNO, dataPagamentoStr.substring(6));
					if (flagCompressione) {
						int mese = Integer.valueOf(dataPagamentoStr.substring(3,5));
						switch (mese) {
						case 1:  descrizione = "Gennaio";
						break;
						case 2:  descrizione = "Febbraio";
						break;
						case 3:  descrizione = "Marzo";
						break;
						case 4:  descrizione = "Aprile";
						break;
						case 5:  descrizione = "Maggio";
						break;
						case 6:  descrizione = "Giugno";
						break;
						case 7:  descrizione = "Luglio";
						break;
						case 8:  descrizione = "Agosto";
						break;
						case 9:  descrizione = "Settembre";
						break;
						case 10: descrizione = "Ottobre";
						break;
						case 11: descrizione = "Novembre";
						break;
						case 12: descrizione = "Dicembre";
						break;
						}
						descrizione = descrizione + " " + dataPagamentoStr.substring(dataPagamentoStr.length()-4);
						item.setAttribute(PagamentoBorsellinoDAO.DESCRIZIONE, descrizione);
					}
					item.setAttribute(PagamentoBorsellinoDAO.IMPORTOSTR, importoStr);
					//PG180040 GG - inizio
					//boolean flagInAcquisizione = resultSet.getString(8).equals("T")?false:true;
					item.setAttribute(PagamentoBorsellinoDAO.FLAG_APPLICATO, resultSet.getString(8));
					//PG180040 GG - fine
					pagamentiList.add(item);
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
			//DAOHelper.closeIgnoringException(callableStatement);
			//DAOHelper.closeIgnoringException(resultSet);
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

		return pagamentiList;

	}

	public ArrayList<PagamentoBorsellino> pagamentoListDett(PagamentoBorsellino pagamento,String anno,String mese)
	throws DaoException {
		CallableStatement callableStatement = null;
		Connection connection = null;
		ResultSet data = null;
		ArrayList<PagamentoBorsellino> pagamentoBorsellinoList = null;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(),	Routines.PYPGBSP_LST_DETT.routine());
			callableStatement = prepareCall(Routines.PYPGBSP_LST_DETT.routine());
			//fine LP PGNTCORE-24
			callableStatement.setString(1, pagamento.getIdWallet());
			callableStatement.setString(2, pagamento.getCodUtente());
			callableStatement.setString(3, pagamento.getCodEnte());
			callableStatement.setString(4, pagamento.getCodSocieta());
			callableStatement.setString(5, anno);
			callableStatement.setString(6, mese);
			/* we execute procedure */
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				pagamentoBorsellinoList = new ArrayList<PagamentoBorsellino>();
				while (data.next()) {
					PagamentoBorsellino pagamentoBorsellino=new PagamentoBorsellino();
					pagamentoBorsellino.setCanalePagamento(data.getString(2));
					pagamentoBorsellino.setImporto(data.getBigDecimal(3));
					String importoStr = pagamentoBorsellino.getImporto().toString();
					importoStr= importoStr.replace(",", "");
					importoStr= importoStr.replace(".", ",");
					pagamentoBorsellino.setAttribute(PagamentoBorsellinoDAO.FLAG_COMPRESSIONE,data.getString(4)); 
					pagamentoBorsellino.setFlagTombstoned(data.getString(6).equals("C")?false:true);
					pagamentoBorsellino.setTipoPagamento(data.getString(7));//Giulia
					String dataAdd = data.getString(1);
					SimpleDateFormat formatterIT = null;
					formatterIT = new SimpleDateFormat("dd-MM-yyyy");
					Date utilDate = (Date)formatterIT.parse(dataAdd);
					formatterIT = new SimpleDateFormat("dd/MM/yyyy");
					dataAdd = formatterIT.format(utilDate);
					pagamentoBorsellino.setAttribute(PagamentoBorsellinoDAO.DATA_PAGAMENTO, dataAdd);
					pagamentoBorsellino.setAttribute(PagamentoBorsellinoDAO.IMPORTOSTR, importoStr);
					pagamentoBorsellino.setAttribute(PagamentoBorsellinoDAO.FLAG_APPLICATO, data.getString(8)); //PG180040 GG
					pagamentoBorsellinoList.add(pagamentoBorsellino);
				}
				return pagamentoBorsellinoList;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
			//DAOHelper.closeIgnoringException(data);
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
		return pagamentoBorsellinoList;
	}

	public ArrayList<PagamentoBorsellino> pagamentoListDettTot(PagamentoBorsellino pagamento,String anno,String mese)
	throws DaoException {
		CallableStatement callableStatement = null;
		Connection connection = null;
		ResultSet data = null;
		ArrayList<PagamentoBorsellino> pagamentoBorsellinoList = null;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(),	Routines.PYPGHSP_LST_DETT.routine());
			callableStatement =  prepareCall(Routines.PYPGHSP_LST_DETT.routine());
			//fine LP PGNTCORE-24
			callableStatement.setString(1, pagamento.getIdWallet());
			callableStatement.setString(2, pagamento.getCodUtente());
			callableStatement.setString(3, pagamento.getCodEnte());
			callableStatement.setString(4, pagamento.getCodSocieta());
			callableStatement.setString(5, anno);
			callableStatement.setString(6, mese);
			/* we execute procedure */
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				pagamentoBorsellinoList = new ArrayList<PagamentoBorsellino>();
				while (data.next()) {
					PagamentoBorsellino pagamentoBorsellino=new PagamentoBorsellino();
					pagamentoBorsellino.setCanalePagamento(data.getString(2));
					pagamentoBorsellino.setImporto(data.getBigDecimal(3));
					String importoStr = pagamentoBorsellino.getImporto().toString();
					importoStr= importoStr.replace(",", "");
					importoStr= importoStr.replace(".", ",");
					pagamentoBorsellino.setAttribute(PagamentoBorsellinoDAO.FLAG_COMPRESSIONE,data.getString(4)); 
					pagamentoBorsellino.setFlagTombstoned(data.getString(6).equals("C")?false:true);
					pagamentoBorsellino.setTipoPagamento(data.getString(7));//Giulia
					String dataAdd = data.getString(1);
					SimpleDateFormat formatterIT = null;
					formatterIT = new SimpleDateFormat("dd-MM-yyyy");
					Date utilDate = (Date)formatterIT.parse(dataAdd);
					formatterIT = new SimpleDateFormat("dd/MM/yyyy");
					dataAdd = formatterIT.format(utilDate);
					pagamentoBorsellino.setAttribute(PagamentoBorsellinoDAO.DATA_PAGAMENTO, dataAdd);
					pagamentoBorsellino.setAttribute(PagamentoBorsellinoDAO.IMPORTOSTR, importoStr);
					pagamentoBorsellino.setAttribute(PagamentoBorsellinoDAO.FLAG_APPLICATO, data.getString(8)); //PG180040 GG
					pagamentoBorsellinoList.add(pagamentoBorsellino);
				}
				return pagamentoBorsellinoList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
			//DAOHelper.closeIgnoringException(data);
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
		return pagamentoBorsellinoList;
	}

	public boolean listPagamentiPdf(String wallet, String ente, String cutecute, String societa, String annoString , String codAnagGen, String  codiceFiscaleGenitore, Servizio servizio)
	throws DaoException {
		CallableStatement callableStatement = null;
		//Connection connection = null; //LP PGNTCORE-24
		ResultSet data = null;
		boolean Ok = false;
		try {
			//inizio LP PGNTCORE-24
			//connection = getConnection();
			//callableStatement = Helper.prepareCall(connection, getSchema(),	Routines.PYPGBSP_LST_TRI.routine());
			callableStatement = prepareCall(Routines.PYPGBSP_LST_TRI.routine());
			//fine LP PGNTCORE-24
			callableStatement.setString(1, wallet);
			callableStatement.setString(2, ente);
			callableStatement.setString(3, cutecute);
			callableStatement.setString(4, societa);
			callableStatement.setString(5, annoString);
			callableStatement.setString(6, codAnagGen);
			callableStatement.setString(7, codiceFiscaleGenitore);
			callableStatement.setString(8, servizio.getCodiceServizio());
			callableStatement.setString(9, " ");	//PG170120 GG 02052017

			/* we execute procedure */
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next()) {
					Ok = true;
				}
				return Ok;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
			//DAOHelper.closeIgnoringException(data);
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
			//Nota. Non va chiusa la connection il metodo è usato in un ciclo
			//      la chiusura è demandata al chiamante
			//if (connection != null) {
			//	try {
			//		connection.close();
			//	} catch (SQLException e) {
			//		e.printStackTrace();
			//	}
			//}
			//fine LP PG21XX04 Leak
		}
		return Ok;
	}

	//	
	//	public ArrayList<PresenzeGiornaliere> listAnnoScolastico(PresenzeGiornaliere presenzeGiornaliere) throws DaoException {
	//
	//		CallableStatement callableStatement=null;
	//		ArrayList<PresenzeGiornaliere> listAnnoscolastico = null;
	//		Connection connection = null;
	//		ResultSet resultSet=null;
	//		try {
	//			connection = getConnection();
	//			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYAFMSP_LST_ANNO_SCOL.routine());
	//			callableStatement.setString(1,presenzeGiornaliere.getIdWallet());
	//			callableStatement.setString(2,presenzeGiornaliere.getCodiceServizio());
	//			callableStatement.setString(3,presenzeGiornaliere.getCodiceScuola());
	//			callableStatement.setString(4,presenzeGiornaliere.getCodiceAnagraficaFiglio());
	//			callableStatement.execute();
	//			resultSet=callableStatement.getResultSet();
	//			
	//			if ( !resultSet.next() ) {
	//				listAnnoscolastico = new ArrayList<PresenzeGiornaliere>();
	//			} else {
	//				listAnnoscolastico = new ArrayList<PresenzeGiornaliere>();
	//				do {
	//					PresenzeGiornaliere item = new PresenzeGiornaliere();
	//					item.setAnnoScolastico(resultSet.getString("ANNO_SCOLASTICO"));
	//					listAnnoscolastico.add(item);
	//				} while(resultSet.next());
	//			}
	//		} catch (SQLException e) {
	//			throw new DaoException(e);
	//		} catch (IllegalArgumentException e) {
	//			throw new DaoException(e);
	//		} catch (HelperException e) {
	//			throw new DaoException(e);
	//		} finally {
	//			DAOHelper.closeIgnoringException(connection);
	//		} 
	//		
	//		return listAnnoscolastico;
	//
	//	}
	//	

	public String listDdlAnno(Wallet wallet) throws  DaoException{
		String ddlAnniXml="";
		CallableStatement callableStatement = null;
		Connection connection = null;
		ResultSet data = null;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(),	Routines.PYPGBSP_LST_ANN.routine());
			callableStatement = prepareCall(Routines.PYPGBSP_LST_ANN.routine());
			//fine LP PGNTCORE-24
			callableStatement.setString(1, wallet.getIdWallet());
			callableStatement.setString(2, wallet.getCodiceSocieta());
			callableStatement.setString(3, wallet.getCuteCute());
			callableStatement.setString(4, wallet.getChiaveEnte());

			/* we execute procedure */
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				ddlAnniXml = getWebRowSetXml();
				return ddlAnniXml;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
			//DAOHelper.closeIgnoringException(data);
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
		return ddlAnniXml;
	}

	public ArrayList<String> listDdlAnnoObject(Wallet wallet) throws  DaoException{
		CallableStatement callableStatement = null;
		Connection connection = null;
		ArrayList<String> ddlAnni = null;
		ResultSet data = null;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(),	Routines.PYPGBSP_LST_ANN.routine());
			callableStatement = prepareCall(Routines.PYPGBSP_LST_ANN.routine());
			//fine LP PGNTCORE-24
			callableStatement.setString(1, wallet.getIdWallet());
			callableStatement.setString(2, wallet.getCodiceSocieta());
			callableStatement.setString(3, wallet.getCuteCute());
			callableStatement.setString(4, wallet.getChiaveEnte());

			/* we execute procedure */
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				ddlAnni = new ArrayList<String>();
				while (data.next()) {
					ddlAnni.add(data.getString(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
			//DAOHelper.closeIgnoringException(data);
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
		return ddlAnni;
	}

	//Giulia 5102013--Aggiunto metodo di Select relativo alla SP PYPGBSP_SEL_ACQ 
	//creata per selezionare la sommma degli importi in acquisizione



	public PagamentoBorsellino select_acq(PagamentoBorsellino pagamento, String flagGestioneMercati) throws DaoException {
		CallableStatement callableStatement=null;
		ResultSet resultSet=null;
		Connection connection = null;
		CachedRowSet rowSet = null;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPGBSP_SEL_ACQ.routine());
			callableStatement = prepareCall(Routines.PYPGBSP_SEL_ACQ.routine());
			//fine LP PGNTCORE-24
			callableStatement.setString(1, pagamento.getCodSocieta());
			callableStatement.setString(2, pagamento.getCodUtente());
			callableStatement.setString(3, pagamento.getCodEnte()); //  "ANE0000009"
			callableStatement.setString(4, pagamento.getIdWallet());
			callableStatement.setString(5, flagGestioneMercati);	//PG180040
			callableStatement.execute();

			resultSet=callableStatement.getResultSet();
			loadWebRowSet(resultSet);
			String selectXml = getWebRowSetXml();
			try {
				rowSet = Convert.stringToWebRowSet(selectXml);
			} catch (IOException e) {

				e.printStackTrace();
			}

			if (rowSet.next() ) {
				BigDecimal importo = rowSet.getBigDecimal(1);
				pagamento = new PagamentoBorsellino("","","","","","","",importo,null,null,true);
				pagamento.setAttribute(PagamentoBorsellinoDAO.SELECT_XML, selectXml);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
			//DAOHelper.closeIgnoringException(resultSet);
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
		return pagamento;
	}

	public PgHost pagamentiHost(Wallet wallet) throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.PGBHOST.routine());
			
			callableStatement.setString(1, wallet.getCuteCute());
			callableStatement.setString(2, "0"+wallet.getIdWallet().replace("'", "").substring(0,5)); // codice ente da 6
			callableStatement.setString(3, wallet.getIdWallet());
			callableStatement.registerOutParameter(4, Types.CHAR);
			callableStatement.registerOutParameter(5, Types.CHAR);

			callableStatement.execute();
			String retCode = callableStatement.getString(4);

			//  Legenda ReturnCode:
			//- OK: presenti pagamenti in acquisizione
			//- KL: nessun pagamento in acquisizione
			//- KO: errore in esecuzione

			if (!retCode.equalsIgnoreCase("OK")) {
				return null; 
			} 
			
			return new PgHost(callableStatement);


		} catch (SQLException x) {
			x.printStackTrace();
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
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

	public void insertPgHost(PagamentoBorsellino pagamento, ArrayList<PagamentoBorsellino> pagaHost)	throws DaoException {
		CallableStatement callableStatement = null;
		Connection connection = null;
		//inizio LP PG21XX04 Leak
		//connection = getConnection();
		//fine LP PG21XX04 Leak
		try {
			//inizio LP PG21XX04 Leak
			connection = getConnection();
			//fine LP PG21XX04 Leak
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPGHSP_INS.routine());
			callableStatement = prepareCall(Routines.PYPGHSP_INS.routine());
			//fine LP PGNTCORE-24
			for (int i = 0; i < pagaHost.size(); i++) {
				callableStatement.setString(1, String.valueOf(i)); //come chiave pagamento inserisco un progressivo per non avere problemi in inserimento con chiave duplicata.
				callableStatement.setString(2, pagamento.getCodSocieta());
				callableStatement.setString(3, pagamento.getCodUtente());
				callableStatement.setString(4, pagamento.getCodEnte());
				callableStatement.setString(5, pagamento.getIdWallet());
				callableStatement.setString(6, "Y"); //Tipo Pagamento NON Annullato
				String canale = pagaHost.get(i).getCanalePagamento();
				String codCanale = "";

				if  ("ALTRO/GENERICHE".equalsIgnoreCase(canale.trim())) 
					codCanale="*";

				if ("BANCA".equalsIgnoreCase(canale.trim())) 
					codCanale="B";

				if ("COOP".equalsIgnoreCase(canale.trim())) 
					codCanale="C";

				if ("DATA-ENTRY".equalsIgnoreCase(canale.trim()))
					codCanale="D";

				if ("C/O ENTE".equalsIgnoreCase(canale.trim()))
					codCanale="E";

				if ("CASSA SGRAVI".equalsIgnoreCase(canale.trim()))
					codCanale="G";

				if ("SIISAL".equalsIgnoreCase(canale.trim()))
					codCanale="H";

				if ("INTERNET".equalsIgnoreCase(canale.trim()))
					codCanale="I";

				if ("LOTTOMATICA".equalsIgnoreCase(canale.trim()))
					codCanale="L";

				if ("MAZZETTE/DOMICILIATI".equalsIgnoreCase(canale.trim()))
					codCanale="M";

				if ("NORMALE (SPORTELLO)".equalsIgnoreCase(canale.trim()))
					codCanale="N";

				if ("CONCESSIONE POSTE".equalsIgnoreCase(canale.trim()))
					codCanale="O";

				if ("POSTE ITALIANE".equalsIgnoreCase(canale.trim()))
					codCanale="P";

				if ("RID".equalsIgnoreCase(canale.trim()))
					codCanale="R";

				if ("UNIRISCOSSIONI".equalsIgnoreCase(canale.trim()))
					codCanale="S";

				if ("TELEMATICO".equalsIgnoreCase(canale.trim()))
					codCanale="T";

				if ("C/O VIGILI URBANI".equalsIgnoreCase(canale.trim()))
					codCanale="V";

				if ("TOTEM".equalsIgnoreCase(canale.trim()))
					codCanale="W";

				if ("CASSA VIGILI CDS".equalsIgnoreCase(canale.trim()))
					codCanale="Z";

				if ("FITTIZIO A SEGUITO DI DISCARICO PER QUOTA INVIATA AD HOST".equalsIgnoreCase(canale.trim()))
					codCanale="1";

				if ("FITTIZIO A SEGUITO DI DISCARICO PER QUOTA NON INVIATA AD HOST".equalsIgnoreCase(canale.trim()))
					codCanale="0";

				callableStatement.setString(7, codCanale);

				BigDecimal impo = pagaHost.get(i).getImporto();
				BigDecimal divisor = new BigDecimal("100.00");
				impo = impo.divide(divisor);
				callableStatement.setBigDecimal(8, impo);
				Calendar cal = Calendar.getInstance();
				cal = pagaHost.get(i).getDataPagamento();
				callableStatement.setTimestamp(9, new Timestamp(cal.getTimeInMillis()));
				Calendar now = Calendar.getInstance();
				callableStatement.setTimestamp(10, new Timestamp(now.getTimeInMillis())); //imposto la data aggiornamento uguale alla data corrente
				callableStatement.setString(11, "N"); //sempre in acquisizione

				callableStatement.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
		} finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null) {
			//	DAOHelper.closeIgnoringException(callableStatement);
			//	DAOHelper.closeIgnoringException(connection);
			//}
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

	public void deletePgHost(PagamentoBorsellino pagamento)	throws DaoException {
		CallableStatement callableStatement = null;
		Connection connection = null;
		//inizio LP PG21XX04 Leak
		//connection = getConnection();
		//fine LP PG21XX04 Leak
		try {
			//inizio LP PG21XX04 Leak
			connection = getConnection();
			//fine LP PG21XX04 Leak
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPGHSP_DEL.routine());
			callableStatement = prepareCall(Routines.PYPGHSP_DEL.routine());
			//fine LP PGNTCORE-24
			callableStatement.setString(1, pagamento.getCodSocieta());
			callableStatement.setString(2, pagamento.getCodUtente());
			callableStatement.setString(3, pagamento.getCodEnte());
			callableStatement.setString(4, pagamento.getIdWallet());
			callableStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
		} finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null) {
			//	DAOHelper.closeIgnoringException(callableStatement);
			//	DAOHelper.closeIgnoringException(connection);
			//}
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

	public ArrayList <PagamentoBorsellino> listPagamentiTot(PagamentoBorsellino pagamento) throws DaoException {
		CallableStatement callableStatement=null;
		ArrayList<PagamentoBorsellino> pagamentiList = null;
		Connection connection = null;
		ResultSet resultSet=null;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPGHSP_LST.routine());
			callableStatement = prepareCall(Routines.PYPGHSP_LST.routine());
			//fine LP PGNTCORE-24
			callableStatement.setString(1,pagamento.getIdWallet());
			callableStatement.setString(2,pagamento.getCodUtente());
			callableStatement.setString(3,pagamento.getCodEnte());
			callableStatement.setString(4,pagamento.getCodSocieta());
			callableStatement.setString(5,(String)pagamento.getAttribute("anno"));
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();

			if ( !resultSet.next() ) {
				pagamentiList = new ArrayList<PagamentoBorsellino>();
			} else {
				pagamentiList = new ArrayList<PagamentoBorsellino>();
				do {
					PagamentoBorsellino item = new PagamentoBorsellino();
					String dataPagamentoStr = resultSet.getString(1);
					SimpleDateFormat formatterIT = null;
					formatterIT = new SimpleDateFormat("yyyy-MM-dd");
					Date utilDate = (Date)formatterIT.parse(dataPagamentoStr);
					formatterIT = new SimpleDateFormat("dd/MM/yyyy");
					dataPagamentoStr = formatterIT.format(utilDate);
					item.setAttribute(PagamentoBorsellinoDAO.DATA_PAGAMENTO, dataPagamentoStr);
					item.setCanalePagamento(resultSet.getString(2));
					item.setImporto(resultSet.getBigDecimal(3));
					String importoStr = item.getImporto().toString();
					importoStr= importoStr.replace(",", "");
					importoStr= importoStr.replace(".", ",");
					item.setFlagTombstoned(resultSet.getString(6).equals("C")?false:true);
					item.setTipoPagamento(resultSet.getString(7));//Giulia
					Boolean flagCompressione = resultSet.getString(4).equals("C")?true:false;
					item.setAttribute(PagamentoBorsellinoDAO.FLAG_COMPRESSIONE, resultSet.getString(4));
					String descrizione = "";
					item.setAttribute(PagamentoBorsellinoDAO.MESE, dataPagamentoStr.substring(3,5));
					item.setAttribute(PagamentoBorsellinoDAO.ANNO, dataPagamentoStr.substring(6));
					if (flagCompressione) {
						int mese = Integer.valueOf(dataPagamentoStr.substring(3,5));
						switch (mese) {
						case 1:  descrizione = "Gennaio";
						break;
						case 2:  descrizione = "Febbraio";
						break;
						case 3:  descrizione = "Marzo";
						break;
						case 4:  descrizione = "Aprile";
						break;
						case 5:  descrizione = "Maggio";
						break;
						case 6:  descrizione = "Giugno";
						break;
						case 7:  descrizione = "Luglio";
						break;
						case 8:  descrizione = "Agosto";
						break;
						case 9:  descrizione = "Settembre";
						break;
						case 10: descrizione = "Ottobre";
						break;
						case 11: descrizione = "Novembre";
						break;
						case 12: descrizione = "Dicembre";
						break;
						}
						descrizione = descrizione + " " + dataPagamentoStr.substring(dataPagamentoStr.length()-4);
						item.setAttribute(PagamentoBorsellinoDAO.DESCRIZIONE, descrizione);
					}
					item.setAttribute(PagamentoBorsellinoDAO.IMPORTOSTR, importoStr);
					//PG180040 GG - inizio
					//boolean flagInAcquisizione = resultSet.getString(8).equals("T")?false:true;
					item.setAttribute(PagamentoBorsellinoDAO.FLAG_APPLICATO, resultSet.getString(8));
					//PG180040 GG - fine
					pagamentiList.add(item);
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
			//DAOHelper.closeIgnoringException(callableStatement);
			//DAOHelper.closeIgnoringException(resultSet);
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

		return pagamentiList;

	}

	//PG180040 GG 18062018 - inizio
	//creata per selezionare la sommma degli importi che hanno influito sulla disponibilità del borsellino e non sono ancora stati applicati ai consumi tramite il proceso PG2WAPAG
	public PagamentoBorsellino select_appl(PagamentoBorsellino pagamento) throws DaoException {
		CallableStatement callableStatement=null;
		ResultSet resultSet=null;
		Connection connection = null;
		CachedRowSet rowSet = null;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPGBSP_SEL_APPL.routine());
			callableStatement = prepareCall(Routines.PYPGBSP_SEL_APPL.routine());
			//fine LP PGNTCORE-24
			callableStatement.setString(1, pagamento.getCodSocieta());
			callableStatement.setString(2, pagamento.getCodUtente());
			callableStatement.setString(3, pagamento.getCodEnte()); //  "ANE0000009"
			callableStatement.setString(4, pagamento.getIdWallet());
			callableStatement.registerOutParameter(5, Types.CHAR);
			callableStatement.execute();
			
			String flagServiziAConsumo = callableStatement.getString(5); 
			resultSet=callableStatement.getResultSet();
			loadWebRowSet(resultSet);
			String selectXml = getWebRowSetXml();
			try {
				rowSet = Convert.stringToWebRowSet(selectXml);
			} catch (IOException e) {

				e.printStackTrace();
			}

			if (rowSet.next() ) {
				BigDecimal importo = rowSet.getBigDecimal(1);
				pagamento = new PagamentoBorsellino("","","","","","","",importo,null,null,true);
				pagamento.setAttribute(PagamentoBorsellinoDAO.SELECT_XML, selectXml);
				pagamento.setAttribute(PagamentoBorsellinoDAO.FLAG_SERVIZI_A_CONSUMO, flagServiziAConsumo);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
			//DAOHelper.closeIgnoringException(resultSet);
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
		return pagamento;
	}
	//PG180040 GG 18062018
}
