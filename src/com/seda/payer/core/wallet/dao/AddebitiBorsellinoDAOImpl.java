package com.seda.payer.core.wallet.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.sql.DataSource;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.dao.Routines;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.wallet.bean.AddebitiBorsellino;
import com.seda.payer.core.wallet.bean.AddebitiPopup;
import com.seda.payer.core.wallet.bean.Wallet;

public class AddebitiBorsellinoDAOImpl extends BaseDaoHandler  implements AddebitiBorsellinoDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public AddebitiBorsellinoDAOImpl(DataSource dataSource, String schema) throws SQLException  {
		super(dataSource.getConnection(), schema);
	}
	public AddebitiBorsellinoDAOImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}

	public ArrayList<AddebitiBorsellino> addebitiList(Wallet wallet,String anno) throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		ArrayList<AddebitiBorsellino> addebitiBorsellinoList = null;
		try {
			connection = getConnection();											
			//inizio LP 20240921 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYADBSP_LST.routine());
            callableStatement = prepareCall(Routines.PYADBSP_LST.routine());
			//fine LP 20240921 - PGNTCORE-24
			callableStatement.setString(1,wallet.getIdWallet());
			callableStatement.setString(2,wallet.getCodiceSocieta());
			callableStatement.setString(3,wallet.getCuteCute());
			callableStatement.setString(4,wallet.getChiaveEnte());
			callableStatement.setString(5,anno);	 
			/* we execute procedure */
			if(callableStatement.execute())	{
				data = callableStatement.getResultSet();
				addebitiBorsellinoList=new ArrayList<AddebitiBorsellino>();
				while(data.next()){

					AddebitiBorsellino addebitiBorsellino=new AddebitiBorsellino();
					addebitiBorsellino.setImportoPagamento(data.getBigDecimal(2));
					String importoStr = addebitiBorsellino.getImportoPagamento().toString();
					importoStr= importoStr.replace(",", "");
					importoStr= importoStr.replace(".", ",");
					addebitiBorsellino.setAttribute(AddebitiBorsellinoDAO.ADDEBITI_FLAG_ESPANSIONE,data.getString(3));
					String dataAdd = data.getString(1);
					SimpleDateFormat formatterIT = null;
					formatterIT = new SimpleDateFormat("yyyy-MM-dd");
					Date utilDate = (Date)formatterIT.parse(dataAdd);
					formatterIT = new SimpleDateFormat("dd/MM/yyyy");
					dataAdd = formatterIT.format(utilDate);
					addebitiBorsellino.setAttribute(AddebitiBorsellinoDAO.DATA_ADDEBITO, dataAdd);
					String descrizione = "";
					addebitiBorsellino.setAttribute(AddebitiBorsellinoDAO.MESE, dataAdd.substring(3,5));
					addebitiBorsellino.setAttribute(AddebitiBorsellinoDAO.ANNO, dataAdd.substring(6));
					
					if (data.getString(3).equals("C")) {
						int mese = Integer.valueOf(dataAdd.substring(3,5));
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
						descrizione = descrizione + " " + dataAdd.substring(dataAdd.length()-4);
						addebitiBorsellino.setAttribute(AddebitiBorsellinoDAO.DESCRIZIONE, descrizione);
					}
					addebitiBorsellino.setAttribute(AddebitiBorsellinoDAO.IMPORTOSTR, importoStr);
					String segno = data.getString(5);	//può assumere valori '', '+', '-'
					String strSegno = segno;
					if (segno.equals("+")) strSegno = "P";
					if (segno.equals("-")) strSegno = "N";
					addebitiBorsellino.setAttribute(AddebitiBorsellinoDAO.SEGNO_ADDEBITO,strSegno);
					addebitiBorsellinoList.add(addebitiBorsellino);
				}
				return addebitiBorsellinoList;
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
		return addebitiBorsellinoList;
	}

	public ArrayList<AddebitiBorsellino> addebitiListDett(Wallet wallet,String anno,String mese)
	throws DaoException {
		CallableStatement callableStatement = null;
		Connection connection = null;
		ResultSet data = null;
		ArrayList<AddebitiBorsellino> addebitiBorsellinoList = null;
		try {
			connection = getConnection();
			//inizio LP 20240921 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(),	Routines.PYADBSP_LST_DETT.routine());
            callableStatement = prepareCall(Routines.PYADBSP_LST_DETT.routine());
			//fine LP 20240921 - PGNTCORE-24
			callableStatement.setString(1, wallet.getIdWallet());
			callableStatement.setString(2, wallet.getCodiceSocieta());
			callableStatement.setString(3, wallet.getCuteCute());
			callableStatement.setString(4, wallet.getChiaveEnte());
			callableStatement.setString(5, anno);
			callableStatement.setString(6, mese);
			/* we execute procedure */
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				addebitiBorsellinoList = new ArrayList<AddebitiBorsellino>();
				while (data.next()) {
					AddebitiBorsellino addebitiBorsellino=new AddebitiBorsellino();
					addebitiBorsellino.setImportoPagamento(data.getBigDecimal(2));
					String importoStr = addebitiBorsellino.getImportoPagamento().toString();
					importoStr= importoStr.replace(",", "");
					importoStr= importoStr.replace(".", ",");
					addebitiBorsellino.setAttribute(AddebitiBorsellinoDAO.ADDEBITI_FLAG_ESPANSIONE,data.getString(3));
					String dataAdd = data.getString(1);
					SimpleDateFormat formatterIT = null;
					formatterIT = new SimpleDateFormat("dd-MM-yyyy");
					Date utilDate = (Date)formatterIT.parse(dataAdd);	
					formatterIT = new SimpleDateFormat("dd/MM/yyyy");
					dataAdd = formatterIT.format(utilDate);
					addebitiBorsellino.setAttribute(AddebitiBorsellinoDAO.DATA_ADDEBITO, dataAdd);
					addebitiBorsellino.setAttribute(AddebitiBorsellinoDAO.IMPORTOSTR, importoStr);
					String segno = data.getString(5);	//può assumere valori '', '+', '-'
					String strSegno = segno;
					if (segno.equals("+")) strSegno = "P";
					if (segno.equals("-")) strSegno = "N";
					addebitiBorsellino.setAttribute(AddebitiBorsellinoDAO.SEGNO_ADDEBITO,strSegno);
					addebitiBorsellinoList.add(addebitiBorsellino);
				}
				return addebitiBorsellinoList;
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
		return addebitiBorsellinoList;
	}

	public ArrayList<AddebitiPopup> addebitiPopup(Wallet wallet,String data,String segno) throws DaoException {
		CallableStatement callableStatement = null;
		Connection connection = null;
		ResultSet rs = null;
		ArrayList<AddebitiPopup> addebitiPopup = null;
		try {
			connection = getConnection();
			//inizio LP 20240921 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(),	Routines.PYADBSP_POPUP.routine());
            callableStatement = prepareCall(Routines.PYADBSP_POPUP.routine());
			//fine LP 20240921 - PGNTCORE-24
			callableStatement.setString(1, wallet.getCodiceSocieta());
			callableStatement.setString(2, wallet.getCuteCute());
			callableStatement.setString(3, wallet.getChiaveEnte());
			callableStatement.setString(4, wallet.getIdWallet());
			callableStatement.setString(5, data);
			callableStatement.setString(6, segno);
			/* we execute procedure */
			if (callableStatement.execute()) {
				rs = callableStatement.getResultSet();
				addebitiPopup = new ArrayList<AddebitiPopup>();
				while (rs.next()) {
					AddebitiPopup adbPopup = new AddebitiPopup();
					adbPopup.setDenom(rs.getString(1));
					adbPopup.setConsumo(rs.getString(2));
					adbPopup.setDesc(rs.getString(3));
					adbPopup.setImporto(rs.getString(4));
					addebitiPopup.add(adbPopup);
				}
				return addebitiPopup;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
			//DAOHelper.closeIgnoringException(rs);
			//DAOHelper.closeIgnoringException(connection);
			if (rs != null) {
				try {
					rs.close();
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
		return addebitiPopup;
	}

}
