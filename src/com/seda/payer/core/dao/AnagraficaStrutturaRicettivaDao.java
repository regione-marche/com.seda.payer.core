package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.AnagraficaStrutturaRicettiva;
import com.seda.payer.core.bean.AnagraficaStrutturaRicettivaCsv;
import com.seda.payer.core.bean.ResponseData;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;

public class AnagraficaStrutturaRicettivaDao extends BaseDaoHandler {

	public AnagraficaStrutturaRicettivaDao(Connection connection, String schema) {
		super(connection, schema);
	}

	private void closeConnection(CallableStatement callableStatement)
	{
		if (callableStatement != null)
			DAOHelper.closeIgnoringException(callableStatement);
	}
	
	public void doUpdate(AnagraficaStrutturaRicettiva anagStruttura) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.SAN_DOUPDATE.routine());
			anagStruttura.update(callableStatement);
			
			callableStatement.execute();
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	public void doSave(AnagraficaStrutturaRicettiva anagStruttura) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.SAN_DOINSERT.routine());
			anagStruttura.save(callableStatement);
			
			callableStatement.execute();
			
		} catch (SQLException x) {
			if(x.getErrorCode()== -803){
				throw new DaoException(55,"esiste già una anagrafica per i parametri selezionati");
			}
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	public void doDelete(String anagStruttura) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.SAN_DODELETE.routine());
			callableStatement = prepareCall(Routines.SAN_DODELETE.routine());
			//fine LP PG21XX04 Leak
			if (anagStruttura == null)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("anagraficaStruttura.chiaveStruttura"));

			callableStatement.setString(1, anagStruttura);
			//callableStatement.registerOutParameter(2, Types.CHAR);
			callableStatement.execute();
			
		} catch (SQLException x) {
			if(x.getErrorCode()== -532){
				throw new DaoException(55,"Impossibile effettuare la cancellazione, sono presenti una o più informazioni correlate");
			}
			throw new DaoException(x.getErrorCode(),x.getMessage(),x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(Integer.parseInt(Messages.ILL_ARG_ERR_CODE.format()),x.getMessage(),x);
		} catch (HelperException x) {
			throw new DaoException(Integer.parseInt(Messages.HELPER_ERR_CODE.format()),x.getMessage(),x);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}


	
	public AnagraficaStrutturaRicettiva doDetail(String chiaveAnagraficaStruttura, String annoComunicazione) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.SAN_DODETAIL.routine());
			callableStatement.setString(1, chiaveAnagraficaStruttura);
			if(annoComunicazione==null){
				callableStatement.setNull(2,Types.VARCHAR);
			}else{
				callableStatement.setString(2,annoComunicazione);
			}
			
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next()){
					AnagraficaStrutturaRicettiva anagStruttura = new AnagraficaStrutturaRicettiva(data);
					anagStruttura.setNumAlloggiTotali(data.getInt("ALL_TOT"));
					anagStruttura.setNumAlloggiAttivi(data.getInt("ALL_ATT"));
					anagStruttura.setStatoComunicazione(data.getString("COM_STATO"));
					anagStruttura.setCodiceIstat(data.getString("CODISTAT")); 	//PG200390 GG
					return anagStruttura;
				}
			}
			return null;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			//if (data != null)
			//	DAOHelper.closeIgnoringException(data);
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	public AnagraficaStrutturaRicettiva doDetail_Autorizzazione(String codiceBelfiore, String codiceAutorizzazione) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.SAN_DODETAIL_AUT.routine());
			callableStatement.setString(1, codiceBelfiore);
			callableStatement.setString(2, codiceAutorizzazione);
			
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next())
					return new AnagraficaStrutturaRicettiva(data);
			}
			return null;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			//if (data != null)
			//	DAOHelper.closeIgnoringException(data);
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	public ResponseData doSaveHost(AnagraficaStrutturaRicettiva anagStruttura, String tipoRichiesta, String descrizioneComune, String siglaProvincia) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			if(anagStruttura != null) {
				System.out.println("DATA-IN-CODUTEN: " + anagStruttura.getCodiceUtente());
				System.out.println("DATA-IN-CENTENTE: " + anagStruttura.getCodiceEnteGestionaleEntrate());
				System.out.println("DATA-IN-CODIMSE: " + anagStruttura.getImpostaServizioGestionaleEntrate());
				System.out.println("DATA-IN-REQUEST: " + tipoRichiesta);
				System.out.println("DATA-IN-OPERATO: " + "WISMANAGER");
				System.out.println("DATA-IN-CODBELF: " + anagStruttura.getCodiceBelfiore());
				System.out.println("DATA-IN-IDAUTHO: " + anagStruttura.getCodiceAutorizzazione());
				System.out.println("DATA-IN-COFPIVA: " + anagStruttura.getPartitaIVAStruttura());
				System.out.println("DATA-IN-RAGSOCS: " + anagStruttura.getRagioneSocialeStruttura());
				System.out.println("DATA-IN-INDIRIZ: " + anagStruttura.getIndirizzoStruttura());
				System.out.println("DATA-IO-DCOMUNE: " + descrizioneComune);
				System.out.println("DATA-IN-CODPROV: " + siglaProvincia);			
				System.out.println("DATA-IN-CODICAP: " + anagStruttura.getCapStruttura());
				System.out.println("DATA-IN-CONTRIB: " + (tipoRichiesta.equals("VARI") ? anagStruttura.getCodiceContribuenteGestionaleEntrate() : " "));
				System.out.println("DATA-IN-INSEGNA: " + anagStruttura.getInsegnaStruttura());
				System.out.println("DATA-IN-TITOLAR: " + anagStruttura.getAnagraficaTitolare());
				System.out.println("DATA-IN-COFITIT: " + anagStruttura.getCodFiscaleTitolare());				
			}

			callableStatement = prepareCall(Routines.IS_ANAG_DOSAVE.routine());
			callableStatement.setString(1, anagStruttura.getCodiceUtente());
			callableStatement.setString(2, anagStruttura.getCodiceEnteGestionaleEntrate());
			callableStatement.setString(3, anagStruttura.getImpostaServizioGestionaleEntrate());
			callableStatement.setString(4, tipoRichiesta);
			callableStatement.setString(5, "WISMANAGER");
			callableStatement.setString(6, anagStruttura.getCodiceBelfiore());
			callableStatement.setString(7, anagStruttura.getCodiceAutorizzazione());
			callableStatement.setString(8, anagStruttura.getPartitaIVAStruttura());
			callableStatement.setString(9, anagStruttura.getRagioneSocialeStruttura());
			callableStatement.setString(10, anagStruttura.getIndirizzoStruttura());
			callableStatement.setString(11, descrizioneComune);
			callableStatement.setString(12, siglaProvincia);
			callableStatement.setString(13, anagStruttura.getCapStruttura());
			//il codice contribuente è valorizzato solo in caso di modifica, altrimenti spazio
			if (tipoRichiesta.equals("VARI"))
				callableStatement.setString(14, anagStruttura.getCodiceContribuenteGestionaleEntrate());
			else
				callableStatement.setString(14, " ");
			
			callableStatement.setString(15, anagStruttura.getInsegnaStruttura());
			callableStatement.setString(16, anagStruttura.getAnagraficaTitolare());
			callableStatement.setString(17, anagStruttura.getCodFiscaleTitolare());
			
			//parametri di output
			callableStatement.registerOutParameter(14, Types.VARCHAR);
			callableStatement.registerOutParameter(18, Types.VARCHAR);
			callableStatement.registerOutParameter(19, Types.VARCHAR);
			
			callableStatement.execute();
			
			ResponseData res = new ResponseData();
			res.setRetCode(callableStatement.getString(18));
			res.setRetMessage(callableStatement.getString(19));
			res.setInfo1(callableStatement.getString(14)); //codice contribuente
			
			/****TEST****/
			/*ResponseData res = new ResponseData();
			res.setRetCode("OK");
			res.setRetMessage("");
			res.setInfo1("COD_TEST");*/
			
			return res;
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	public void doRowSets(AnagraficaStrutturaRicettiva anagStruttura,String descComune) throws DaoException {
		rowSets(anagStruttura, 0, 0,descComune);
	}

	public void doRowSets(AnagraficaStrutturaRicettiva anagStruttura, int rowsPerPage, int pageNumber,String descComune) throws DaoException {
		
			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
 
			rowSets(anagStruttura, rowsPerPage, pageNumber,descComune);

	}
	
	public void rowSets(AnagraficaStrutturaRicettiva anagStruttura, int rowsPerPage, int pageNumber,String descComune) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.SAN_DOLIST.routine());
			/*IN I_APC_DAPCDCOM VARCHAR(100),
			IN I_SAN_CSANCAUT VARCHAR(100),
			IN I_SAN_CSANCFPI CHAR(16),
			IN I_SAN_DSANDINS VARCHAR(256),
			IN I_SAN_CSSRCSSR CHAR(3),
			IN I_SAN_FSANFSUB CHAR(1),
			IN I_SAN_GSANGVAL_INI TIMESTAMP,
			IN I_SAN_GSANGVAL_FIN TIMESTAMP,
			IN I_SAN_GSANGOBC_INI TIMESTAMP,
			IN I_SAN_GSANGOBC_FIN TIMESTAMP,
			IN I_ROWSXPAGE SMALLINT,
			IN I_PAGENO SMALLINT, 
			OUT O_ROWINI INTEGER, 
			OUT O_ROWEND INTEGER ,
			OUT O_TOTROWS INTEGER, 
			OUT O_TOTPAGES SMALLINT  */
			
			callableStatement.setString(1, descComune);
			callableStatement.setString(2, anagStruttura.getCodiceAutorizzazione());
			callableStatement.setString(3, anagStruttura.getPartitaIVAStruttura());
			callableStatement.setString(4, anagStruttura.getInsegnaStruttura());
			callableStatement.setString(5, anagStruttura.getChiaveTipologiaStruttura());
			//PG170240
			callableStatement.setString(6, anagStruttura.getFlagSubentro());
			if(anagStruttura.getDataValiditaInizio()!= null)
				callableStatement.setTimestamp(7, new java.sql.Timestamp(anagStruttura.getDataValiditaInizio().getTimeInMillis()));
			else
				callableStatement.setNull(7, java.sql.Types.TIMESTAMP);
			
			if(anagStruttura.getDataValiditaFine()!= null)
				callableStatement.setTimestamp(8, new java.sql.Timestamp(anagStruttura.getDataValiditaFine().getTimeInMillis()));
			else
				callableStatement.setNull(8, java.sql.Types.TIMESTAMP);
			
			if(anagStruttura.getDataObbligoComunicazioneInizio()!= null)
				callableStatement.setTimestamp(9, new java.sql.Timestamp(anagStruttura.getDataObbligoComunicazioneInizio().getTimeInMillis()));
			else
				callableStatement.setNull(9, java.sql.Types.TIMESTAMP);
			
			if(anagStruttura.getDataObbligoComunicazioneFine()!= null)
				callableStatement.setTimestamp(10, new java.sql.Timestamp(anagStruttura.getDataObbligoComunicazioneFine().getTimeInMillis()));
			else
				callableStatement.setNull(10, java.sql.Types.TIMESTAMP);
			
			callableStatement.setInt(11, rowsPerPage);
			callableStatement.setInt(12, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(14, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(15, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(16, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(13), callableStatement.getInt(14), 
								 callableStatement.getInt(15), callableStatement.getInt(16));
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
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	public List<AnagraficaStrutturaRicettivaCsv> doListCsv(AnagraficaStrutturaRicettiva anagStruttura, String descComune) throws DaoException {
		List<AnagraficaStrutturaRicettivaCsv> listCsv = null;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.SAN_DOLIST_CSV.routine());
			
			callableStatement.setString(1, descComune);
			callableStatement.setString(2, anagStruttura.getCodiceAutorizzazione());
			callableStatement.setString(3, anagStruttura.getPartitaIVAStruttura());
			callableStatement.setString(4, anagStruttura.getInsegnaStruttura());
			callableStatement.setString(5, anagStruttura.getChiaveTipologiaStruttura());
			//PG170240
			callableStatement.setString(6, anagStruttura.getFlagSubentro());
			if(anagStruttura.getDataValiditaInizio()!= null)
				callableStatement.setTimestamp(7, new java.sql.Timestamp(anagStruttura.getDataValiditaInizio().getTimeInMillis()));
			else
				callableStatement.setNull(7, java.sql.Types.TIMESTAMP);
			
			if(anagStruttura.getDataValiditaFine()!= null)
				callableStatement.setTimestamp(8, new java.sql.Timestamp(anagStruttura.getDataValiditaFine().getTimeInMillis()));
			else
				callableStatement.setNull(8, java.sql.Types.TIMESTAMP);
			
			if(anagStruttura.getDataObbligoComunicazioneInizio()!= null)
				callableStatement.setTimestamp(9, new java.sql.Timestamp(anagStruttura.getDataObbligoComunicazioneInizio().getTimeInMillis()));
			else
				callableStatement.setNull(9, java.sql.Types.TIMESTAMP);
			
			if(anagStruttura.getDataObbligoComunicazioneFine()!= null)
				callableStatement.setTimestamp(10, new java.sql.Timestamp(anagStruttura.getDataObbligoComunicazioneFine().getTimeInMillis()));
			else
				callableStatement.setNull(10, java.sql.Types.TIMESTAMP);

			if (callableStatement.execute()) {
				listCsv = new ArrayList<AnagraficaStrutturaRicettivaCsv>();
				data = callableStatement.getResultSet();
				while (data.next())
					listCsv.add(new AnagraficaStrutturaRicettivaCsv(data));
				
				//this.loadWebRowSets(callableStatement);
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
			//if (data != null)
			//	DAOHelper.closeIgnoringException(data);
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return listCsv;
	}
	
	//PG180170 GG 15102017 - inizio
	public List<AnagraficaStrutturaRicettivaCsv> doListCsvTrentino(AnagraficaStrutturaRicettiva anagStruttura, String descComune) throws DaoException {
		List<AnagraficaStrutturaRicettivaCsv> listCsv = null;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.SAN_DOLIST_CSV_TR.routine());
			
			callableStatement.setString(1, descComune);
			callableStatement.setString(2, anagStruttura.getCodiceAutorizzazione());
			callableStatement.setString(3, anagStruttura.getPartitaIVAStruttura());
			callableStatement.setString(4, anagStruttura.getInsegnaStruttura());
			callableStatement.setString(5, anagStruttura.getChiaveTipologiaStruttura());
			//PG170240
			callableStatement.setString(6, anagStruttura.getFlagSubentro());
			if(anagStruttura.getDataValiditaInizio()!= null)
				callableStatement.setTimestamp(7, new java.sql.Timestamp(anagStruttura.getDataValiditaInizio().getTimeInMillis()));
			else
				callableStatement.setNull(7, java.sql.Types.TIMESTAMP);
			
			if(anagStruttura.getDataValiditaFine()!= null)
				callableStatement.setTimestamp(8, new java.sql.Timestamp(anagStruttura.getDataValiditaFine().getTimeInMillis()));
			else
				callableStatement.setNull(8, java.sql.Types.TIMESTAMP);
			
			if(anagStruttura.getDataObbligoComunicazioneInizio()!= null)
				callableStatement.setTimestamp(9, new java.sql.Timestamp(anagStruttura.getDataObbligoComunicazioneInizio().getTimeInMillis()));
			else
				callableStatement.setNull(9, java.sql.Types.TIMESTAMP);
			
			if(anagStruttura.getDataObbligoComunicazioneFine()!= null)
				callableStatement.setTimestamp(10, new java.sql.Timestamp(anagStruttura.getDataObbligoComunicazioneFine().getTimeInMillis()));
			else
				callableStatement.setNull(10, java.sql.Types.TIMESTAMP);

			if (callableStatement.execute()) {
				listCsv = new ArrayList<AnagraficaStrutturaRicettivaCsv>();
				data = callableStatement.getResultSet();
				while (data.next())
					listCsv.add(new AnagraficaStrutturaRicettivaCsv(data));
				
				//this.loadWebRowSets(callableStatement);
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
			//if (data != null)
			//	DAOHelper.closeIgnoringException(data);
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return listCsv;
	}
	//PG180170 GG 15102018 - fine
	
	//PG180050_001 GG - inizio
	public AnagraficaStrutturaRicettiva doDetailByCatMerceologica(String codiceBelfiore, String partitaIVAStruttura, String chiaveTipologiaStruttura) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.SAN_DODETAIL_AU2.routine());
			callableStatement.setString(1, codiceBelfiore);
			callableStatement.setString(2, partitaIVAStruttura);
			callableStatement.setString(3, chiaveTipologiaStruttura);
			
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next())
					return new AnagraficaStrutturaRicettiva(data);
			}
			return null;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			//if (data != null)
			//	DAOHelper.closeIgnoringException(data);
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	public String getProgressivoComunicazione(String chiave, int anno) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;

		try	{
			callableStatement = prepareCall(Routines.PYKEYSP_BY_YEAR.routine());
			callableStatement.setString(1, chiave);
			callableStatement.setInt(2, anno);
			callableStatement.registerOutParameter(3, Types.BIGINT);
			
			callableStatement.execute(); 
			
			Long key=callableStatement.getLong(3);
			
			return String.valueOf(key);
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			//if (data != null)
			//	DAOHelper.closeIgnoringException(data);
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	//PG180050_001 GG - fine
	
	//PG190330 SB - inizio
	//Viene recuperata la lista completa delle anagrafiche per partita iva e tipologia struttura
	public void doAnagAlloggiPIva(String partitaIva, String codiceTipologiaStruttura) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.PYSANSP_LST_ALL.routine());
			callableStatement.setString(1, partitaIva);
			callableStatement.setString(2, codiceTipologiaStruttura);

			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
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
			//if (data != null)
			//	DAOHelper.closeIgnoringException(data);
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	//PG190330 SB - fine

}
