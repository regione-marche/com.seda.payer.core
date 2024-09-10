package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.PyUser;
import com.seda.payer.core.bean.PyUserBean;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;

// DOM OK
public class PyUserDao extends BaseDaoHandler{

	public PyUserDao(Connection connection, String schema) {
		super(connection, schema);
	}

	/**
	 * Restituisce il bean di un utente Payer dalla tabella PYUSRTB
	 * 
	 * @param chiaveUtente - Chiave univoca dell'utente
	 * @return PyUser - Il bean dell'utente
	 * @throws DaoException
	 */
	//inizio LP 20240909 - PGNTBOLDER-1
	public PyUser selectPyUserByKey(Long chiaveUtente) throws DaoException
	{
		return selectPyUserByKeyTail(true, chiaveUtente);
	}

	public PyUser selectPyUserByKeyTail(boolean bFlagUpdateAutocomit, Long chiaveUtente) throws DaoException
	//fine LP 20240909 - PGNTBOLDER-1
	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		PyUser pyUser = null;

		try{
			//inizio LP 20240909 - PGNTBOLDER-1
			//callableStatement = prepareCall(Routines.USR_DODETAIL.routine());
			callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.USR_DODETAIL.routine());
			//fine LP 20240909 - PGNTBOLDER-1
			callableStatement.setLong(1, chiaveUtente);
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if(data.next()) {
					pyUser =  PyUser.getBean(data, false);
					return pyUser;
				}
			}
			return null;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
            //DAOHelper.closeIgnoringException(data);
			//DAOHelper.closeIgnoringException(callableStatement);
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
	}
	
	/**
	 * Restituisce una lista di utenti dalla tabella PYUSRTB
	 * e l'elenco delle  applicazioni a cui l'utente è abilitato
	 * dalla tabella PYMNATB
	 * 
	 * @param chiaveUtente - Chiave univoca dell'utente
	 * @return PyUserBean
	 * @throws DaoException
	 */
	public PyUserBean selectPyUserAppByKey(Long chiaveUtente) throws DaoException
	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		PyUser pyUser = null;
		PyUserBean pyUserBean = null;
		List<String> applicazioni = null;

		try{
			callableStatement = prepareCall(Routines.USR_GET_PYUSER_AND_APP.routine());
			callableStatement.setLong(1, chiaveUtente);
			/*
			 * Recupero il bean dell'utente dal primo resultset 
			 */
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next())
				{
					pyUser =  PyUser.getBean(data,false);
				}
			}
			/*
			 * Recupero la lista delle applicazioni
			 */
			if(callableStatement.getMoreResults())
			{
				//inizio LP PG21XX04 Leak
				if (data != null) {
					try {
						data.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				//fine LP PG21XX04 Leak
				data = callableStatement.getResultSet();
				if(data != null)
				{
					applicazioni = new Vector<String>();
					while(data.next()) applicazioni.add(data.getString("MNA_CMNAAPPL"));
				}
			}
			if (pyUser != null) 
				pyUserBean = new PyUserBean(pyUser, applicazioni);
			return pyUserBean;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally
		{
			//inizio LP PG21XX04 Leak
            //DAOHelper.closeIgnoringException(data);
			//DAOHelper.closeIgnoringException(callableStatement);
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
	}
	
	/***
	 * Restituisce una lista di profili utente per lo username passato come parametro
	 * 
	 * @param username
	 * @return
	 * @throws DaoException
	 */
	public List<PyUser> selectPyUserListByUserName(String username) throws DaoException
	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		List<PyUser> listPyUser = null;
		PyUser pyUser = null;
		
		try{
			callableStatement = prepareCall(Routines.USR_DODETAIL_USER.routine());
			callableStatement.setString(1, username);
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				while (data.next())
				{
					if (listPyUser == null)
						listPyUser = new ArrayList<PyUser>();
					
					pyUser =  PyUser.getBean(data, true);
					listPyUser.add(pyUser);
				}
			}
			return listPyUser;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally
		{
			//inizio LP PG21XX04 Leak
            //DAOHelper.closeIgnoringException(data);
			//DAOHelper.closeIgnoringException(callableStatement);
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
	}

	/**
	 * Restituisce la lista degli utenti Payer come WebRowSet in formato XML
	 * ma senza paginazione
	 * 
	 * @param order
	 * @param userName
	 * @param userProfile
	 * @param codiceSocieta
	 * @param codiceUtente
	 * @param siglaProvincia
	 * @param chiaveEnteConsorzio
	 * @param applicazione
	 * @return String
	 * @throws DaoException
	 */
	public void listPyUsersXml
	(
			String order,
			String userName,
			String userNameLike,
			String userProfile,
			String codiceSocieta,
			String codiceUtente,
			String siglaProvincia,
			String chiaveEnteConsorzio,
			String applicazione,
			String flagAttivazione,
			int rowsPerPage,
			int pageNumber,
			String pertinenza,
			String gruppoAgenzia  //RE180181_001 SB
			
	) throws DaoException
	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		
		try {
			System.out.println("ricercato esistenza profilo ");
			System.out.println("userName = " + userName);
			System.out.println("userProfile = " + userProfile);
			System.out.println("codiceSocieta = " + codiceSocieta);
			System.out.println("codiceUtente = " + codiceUtente);
			System.out.println("chiaveEnteConsorzio = " + chiaveEnteConsorzio);
			callableStatement = prepareCall(Routines.USR_SEL_ADMIN.routine());
			callableStatement.setString(1, order == null ? "" : order);
			callableStatement.setString(2, userName == null ? "" : userName);
			callableStatement.setString(3, userNameLike == null ? "N" : userNameLike);
			callableStatement.setString(4, userProfile == null ? "" : userProfile);
			callableStatement.setString(5, codiceSocieta == null ? "" : codiceSocieta);
			callableStatement.setString(6, codiceUtente == null ? "" : codiceUtente);
			callableStatement.setString(7, siglaProvincia == null ? "" : siglaProvincia);
			callableStatement.setString(8, chiaveEnteConsorzio == null ? "" : chiaveEnteConsorzio);
			callableStatement.setString(9, applicazione == null ? "" : applicazione);
			callableStatement.setString(10, flagAttivazione == null ? "Y" : flagAttivazione);
			callableStatement.setString(11, pertinenza == null ? "" : pertinenza);
			callableStatement.setString(12, gruppoAgenzia == null ? "" : gruppoAgenzia); //RE180181_001 SB
			callableStatement.setInt(13, rowsPerPage);
			callableStatement.setInt(14, pageNumber);
			
			
			
			


			callableStatement.registerOutParameter(15, Types.INTEGER);
			callableStatement.registerOutParameter(16, Types.INTEGER);
			callableStatement.registerOutParameter(17, Types.INTEGER);
			callableStatement.registerOutParameter(18, Types.SMALLINT);

			callableStatement.registerOutParameter(19, Types.VARCHAR);

			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(15), callableStatement.getInt(16), 
								 callableStatement.getInt(17), callableStatement.getInt(18));
			}
			
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		}
		finally
		{
			//inizio LP PG21XX04 Leak
            //DAOHelper.closeIgnoringException(data);
			//DAOHelper.closeIgnoringException(callableStatement);
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
	}
	
	
	public void listProfiliPyUsersXml(String userName) throws DaoException
	{
		CallableStatement callableStatement = null;
		
		try {
			callableStatement = prepareCall(Routines.USR_DOLIST_PRF.routine());
			callableStatement.setString(1, userName == null ? "" : userName);
			
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
			}
			
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
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

	/**
	 * Inserisce un nuovo utente Payer --- Se il bean è nullo oppure se
	 * l'utente e/o il suo profilo non sono definiti genera una eccezione.
	 * 
	 * @param PyUser pyUser
	 * @return boolean
	 * @throws DaoException
	 */
	public Long insertPyUser ( PyUser pyUser ) throws DaoException
	{
		/*
		 * Se il bean è nullo genero una eccezione.
		 */
		if(pyUser == null) 
			throw new DaoException(1, Messages.PYUSER_INS_ERR_BEAN_NULL.format("PyUserDao","insertPyUser"));
		/*
		 * Se "userName" e/o "userProfile" non sono definiti genero una eccezione.
		 */
		if(pyUser.getUserName() == null || pyUser.getUserName().equals("") || pyUser.getUserProfile() == null || pyUser.getUserProfile().equals(""))
			throw new DaoException(2, Messages.PYUSER_INS_ERR_USERNAME_PROFILE_NULL.format("PyUserDao","insertPyUser"));
		/*
		 * Chiamo la SP
		 */
		CallableStatement callableStatement = null;
		try {
			callableStatement = prepareCall(Routines.USR_DOINSERT.routine());
			callableStatement.setString(1, pyUser.getUserName());
			callableStatement.setString(2, pyUser.getUserProfile());
			callableStatement.setString(3, pyUser.getFlagAttivazione() == null ? "N" : pyUser.getFlagAttivazione());
			callableStatement.setString(4, pyUser.getCodiceSocieta() == null ? "" : pyUser.getCodiceSocieta());
			callableStatement.setString(5, pyUser.getCodiceUtente() == null ? "" : pyUser.getCodiceUtente());
			callableStatement.setString(6, pyUser.getChiaveEnteConsorzio() == null ? "" : pyUser.getChiaveEnteConsorzio());
			callableStatement.setString(7, pyUser.getChiaveEnteConsorziato() == null ? "" : pyUser.getChiaveEnteConsorziato());
			callableStatement.setString(8, pyUser.getDownloadFlussiRendicontazione() == null ? "N" : pyUser.getDownloadFlussiRendicontazione());
			callableStatement.setString(9, pyUser.getInvioFlussiRendicontazioneViaFtp() == null ? "N" : pyUser.getInvioFlussiRendicontazioneViaFtp());
			callableStatement.setString(10, pyUser.getInvioFlussiRendicontazioneViaEmail() == null ? "N" : pyUser.getInvioFlussiRendicontazioneViaEmail());
			callableStatement.setString(11, pyUser.getAzioniPerTransazioniOK() == null ? "N" : pyUser.getAzioniPerTransazioniOK());
			callableStatement.setString(12, pyUser.getAzioniPerTransazioniKO() == null ? "N" : pyUser.getAzioniPerTransazioniKO());
			callableStatement.setString(13, pyUser.getAzioniPerRiconciliazioneManuale() == null ? "N" : pyUser.getAzioniPerRiconciliazioneManuale() );
			callableStatement.setString(14, pyUser.getAttivazioneEstrattoContoManager() == null ? "N" : pyUser.getAttivazioneEstrattoContoManager());
			callableStatement.setString(15, pyUser.getAbilitazioneProfiloRiversamento() == null ? "N" : pyUser.getAbilitazioneProfiloRiversamento() );
			callableStatement.setString(16, pyUser.getAbilitazioneMultiUtente() == null ? "N" : pyUser.getAbilitazioneMultiUtente());
			callableStatement.setTimestamp(17, pyUser.getDataUltimoAggiornamento());
			callableStatement.setString(18, pyUser.getOperatoreUltimoAggiornamento() == null ? "" : pyUser.getOperatoreUltimoAggiornamento());
			callableStatement.setString(19, pyUser.getListaTipologieServizio() == null ? "" : pyUser.getListaTipologieServizio());

			callableStatement.setString(20, pyUser.getMailContoGestione() == null ? "N" : pyUser.getMailContoGestione());
			callableStatement.setString(21, pyUser.getEntePertinenza() == null ? "" : pyUser.getEntePertinenza());	//EP160510_001 GG 03112016
			callableStatement.setString(22, pyUser.getGruppoAgenzia() == null ? "" : pyUser.getGruppoAgenzia());   //RE180181_001 SB

			callableStatement.setString(23, pyUser.getAssociazioniProvvisorieRiconciliazionemt() == null ? "N" : pyUser.getAssociazioniProvvisorieRiconciliazionemt());
			callableStatement.setString(24, pyUser.getAssociazioniDefinitiveRiconciliazionemt() == null ? "N" : pyUser.getAssociazioniDefinitiveRiconciliazionemt());
			//RE180181_001 SB - inizio
			callableStatement.setString(25, pyUser.getMail() == null ? "" : pyUser.getMail());
			callableStatement.setString(26, pyUser.getMailPec() == null ? "" : pyUser.getMailPec());
			callableStatement.setString(27, pyUser.getPinCodeMail() == null ? "" : pyUser.getPinCodeMail());
			callableStatement.setString(28, pyUser.getPinCodePec() == null ? "" : pyUser.getPinCodePec());
			callableStatement.setString(29, pyUser.getFlagValidazioneMail() == null ? "N" : pyUser.getFlagValidazioneMail());
			callableStatement.setString(30, pyUser.getFlagValidazionePec() == null ? "N" : pyUser.getFlagValidazionePec());
			//RE180181_001 SB - fine
			callableStatement.setString(31, pyUser.getInvioFlussiRendicontazioneViaWs() == null ? "N" : pyUser.getInvioFlussiRendicontazioneViaWs());
			callableStatement.registerOutParameter(32, Types.INTEGER);
			callableStatement.registerOutParameter(33, Types.BIGINT);
			callableStatement.executeUpdate();
			if (callableStatement.getInt(32) == 1 ) 
				return callableStatement.getLong(33); //ritorno la chiave generata per l'inserimento
			else return null;
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		}
		finally
		{
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
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

	/**
	 * Modifica le proprietà di un utente Payer
	 * 
	 * @param PyUser pyUser
	 * @return boolean
	 * @throws DaoException
	 */
	//inizio LP 20240909 - PGNTBOLDER-1
	public boolean updatePyUser(PyUser pyUser) throws DaoException
	{
		return updatePyUserTail(true, pyUser);
	}

	public boolean updatePyUserTail(boolean bFlagUpdateAutocomit, PyUser pyUser) throws DaoException
	//fine LP 20240909 - PGNTBOLDER-1
	{
		/*
		 * Se il bean è nullo genero una eccezione.
		 */
		if(pyUser == null) 
			throw new DaoException(1, Messages.PYUSER_UPD_ERR_BEAN_NULL.format("PyUserDao","updatePyUser"));
		/*
		 * Se "userName" non è definito genero una eccezione.
		 */
		if(pyUser.getChiaveUtente() == null || pyUser.getChiaveUtente().longValue() == 0)
			throw new DaoException(2, Messages.PYUSER_UPD_ERR_USERNAME_NULL.format("PyUserDao","updatePyUser"));
		/*
		 * Chiamo la SP
		 */
		CallableStatement callableStatement = null;
		try {
			//inizio LP 20240909 - PGNTBOLDER-1
			callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.USR_DOUPDATE.routine());
			//fine LP 20240909 - PGNTBOLDER-1
			callableStatement.setLong(1, pyUser.getChiaveUtente());
			callableStatement.setString(2, pyUser.getUserName());
			
			if(pyUser.getUserProfile()==null) callableStatement.setNull(3, Types.VARCHAR);
				else callableStatement.setString(3, pyUser.getUserProfile());
			
			if(pyUser.getFlagAttivazione()==null) callableStatement.setNull(4, Types.VARCHAR);
				else callableStatement.setString(4, pyUser.getFlagAttivazione());
			
			if(pyUser.getCodiceSocieta()==null) callableStatement.setNull(5, Types.VARCHAR);
				else callableStatement.setString(5, pyUser.getCodiceSocieta());
			
			if(pyUser.getCodiceUtente()==null) callableStatement.setNull(6, Types.VARCHAR);
				else callableStatement.setString(6, pyUser.getCodiceUtente());
			
			if(pyUser.getChiaveEnteConsorzio()==null) callableStatement.setNull(7, Types.VARCHAR);
				else callableStatement.setString(7, pyUser.getChiaveEnteConsorzio());
			
			if(pyUser.getChiaveEnteConsorziato()==null) callableStatement.setNull(8, Types.VARCHAR);
				else callableStatement.setString(8, pyUser.getChiaveEnteConsorziato());
			
			if(pyUser.getDownloadFlussiRendicontazione()==null) callableStatement.setNull(9, Types.VARCHAR);
				else callableStatement.setString(9, pyUser.getDownloadFlussiRendicontazione());
			
			if(pyUser.getInvioFlussiRendicontazioneViaFtp()==null) callableStatement.setNull(10, Types.VARCHAR);
				else callableStatement.setString(10, pyUser.getInvioFlussiRendicontazioneViaFtp());
			
			if(pyUser.getInvioFlussiRendicontazioneViaEmail()==null) callableStatement.setNull(11, Types.VARCHAR);
				else callableStatement.setString(11, pyUser.getInvioFlussiRendicontazioneViaEmail());
			
			if(pyUser.getAzioniPerTransazioniOK()==null) callableStatement.setNull(12, Types.VARCHAR);
				else callableStatement.setString(12, pyUser.getAzioniPerTransazioniOK());
			
			if(pyUser.getAzioniPerTransazioniKO()==null) callableStatement.setNull(13, Types.VARCHAR);
				else callableStatement.setString(13, pyUser.getAzioniPerTransazioniKO());
			
			if(pyUser.getAzioniPerRiconciliazioneManuale()==null) callableStatement.setNull(14, Types.VARCHAR);
				else callableStatement.setString(14, pyUser.getAzioniPerRiconciliazioneManuale());
			
			if(pyUser.getAttivazioneEstrattoContoManager()==null) callableStatement.setNull(15, Types.VARCHAR);
				else callableStatement.setString(15, pyUser.getAttivazioneEstrattoContoManager());
			
			if(pyUser.getAbilitazioneProfiloRiversamento()==null) callableStatement.setNull(16, Types.VARCHAR);
				else callableStatement.setString(16, pyUser.getAbilitazioneProfiloRiversamento());
			
			if(pyUser.getAbilitazioneMultiUtente()==null) callableStatement.setNull(17, Types.VARCHAR);
				else callableStatement.setString(17, pyUser.getAbilitazioneMultiUtente());
			
			if(pyUser.getDataUltimoAggiornamento() == null) callableStatement.setNull(18, Types.TIMESTAMP);
				else callableStatement.setTimestamp(18, pyUser.getDataUltimoAggiornamento());
			
			if(pyUser.getOperatoreUltimoAggiornamento()==null) callableStatement.setNull(19, Types.VARCHAR);
				else callableStatement.setString(19, pyUser.getOperatoreUltimoAggiornamento());
			
			if(pyUser.getListaTipologieServizio()==null) callableStatement.setNull(20, Types.VARCHAR);
				else callableStatement.setString(20, pyUser.getListaTipologieServizio());

			if(pyUser.getMailContoGestione()==null) callableStatement.setNull(21, Types.VARCHAR);
				else callableStatement.setString(21, pyUser.getMailContoGestione());
			
			//EP160510_001 GG 03112016 - inizio
			if(pyUser.getEntePertinenza()==null) callableStatement.setNull(22, Types.VARCHAR);
				else callableStatement.setString(22, pyUser.getEntePertinenza());
			//EP160510_001 GG 03112016 - fine
			
			//RE180181_001 SB - inizio
			if(pyUser.getGruppoAgenzia()==null) callableStatement.setNull(23, Types.VARCHAR);
				else callableStatement.setString(23, pyUser.getGruppoAgenzia());
			//RE180181_001 SB - fine
			if(pyUser.getAssociazioniProvvisorieRiconciliazionemt()==null) callableStatement.setNull(24, Types.VARCHAR);
				else callableStatement.setString(24, pyUser.getAssociazioniProvvisorieRiconciliazionemt());
			
			if(pyUser.getAssociazioniDefinitiveRiconciliazionemt()==null) callableStatement.setNull(25, Types.VARCHAR);
				else callableStatement.setString(25, pyUser.getAssociazioniDefinitiveRiconciliazionemt());
			
			//RE180181_001 SB - inizio
			if(pyUser.getMail()==null) callableStatement.setNull(26, Types.VARCHAR);
				else callableStatement.setString(26, pyUser.getMail());
			
			if(pyUser.getMailPec()==null) callableStatement.setNull(27, Types.VARCHAR);
			else callableStatement.setString(27, pyUser.getMailPec());
			
			if(pyUser.getPinCodeMail()==null) callableStatement.setNull(28, Types.VARCHAR);
			else callableStatement.setString(28, pyUser.getPinCodeMail());
			
			if(pyUser.getPinCodePec()==null) callableStatement.setNull(29, Types.VARCHAR);
			else callableStatement.setString(29, pyUser.getPinCodePec());
			
			if(pyUser.getFlagValidazioneMail()==null) callableStatement.setNull(30, Types.VARCHAR);
			else callableStatement.setString(30, pyUser.getFlagValidazioneMail());
			
			if(pyUser.getFlagValidazionePec()==null) callableStatement.setNull(31, Types.VARCHAR);
			else callableStatement.setString(31, pyUser.getFlagValidazionePec());
			
			if(pyUser.getInvioFlussiRendicontazioneViaWs()==null) callableStatement.setNull(32, Types.VARCHAR);
			else callableStatement.setString(32, pyUser.getInvioFlussiRendicontazioneViaWs());

			//RE180181_001 SB - fine

			// SR PGNTCORE-23 inizio
			if(pyUser.getFlagPrenotazioneFatturazione()==null) callableStatement.setNull(33, Types.VARCHAR);
			else callableStatement.setString(33, pyUser.getFlagPrenotazioneFatturazione());

			if(pyUser.getFlagRichiesteElaborazioni()==null) callableStatement.setNull(34, Types.VARCHAR);
			else callableStatement.setString(34, pyUser.getFlagRichiesteElaborazioni());
			// SR PGNTCORE-23 fine

			callableStatement.registerOutParameter(35, Types.INTEGER);
			
			callableStatement.executeUpdate();
			if (callableStatement.getInt(35) == 1 ) return true;
			else return false;
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
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
			//fine LP PG21XX04 Leak
		}
	}

	/**
	 * Elimina l'utente Payer dalla tabella "PYUSRTB"
	 * @param userName
	 * @return boolean - "true" se l'utente è stato cancellato
	 * @throws DaoException
	 */
	//inizio LP 20240909 - PGNTBOLDER-1
	public boolean deletePyUser(Long chiaveUtente) throws DaoException {
		return deletePyUserTail(true, chiaveUtente);
	}

	public boolean deletePyUserTail(boolean bFlagUpdateAutocomit, Long chiaveUtente) throws DaoException {
	//fine LP 20240909 - PGNTBOLDER-1
		/*
		 * Se "userName" non è definito genero una eccezione.
		 */
		if(chiaveUtente == null)
			throw new DaoException(1, Messages.PYUSER_UPD_ERR_USERNAME_NULL.format("PyUserDao","updatePyUser"));
		/*
		 * Chiamo la SP
		 */
		CallableStatement callableStatement = null;
		try {
			//inizio LP 20240909 - PGNTBOLDER-1
			//callableStatement = prepareCall(Routines.USR_DODELETE.routine());
			callableStatement = prepareCall(bFlagUpdateAutocomit,  Routines.USR_DODELETE.routine());
			//fine LP 20240909 - PGNTBOLDER-1
			callableStatement.setLong(1, chiaveUtente);
			callableStatement.registerOutParameter(2, Types.INTEGER);
			callableStatement.executeUpdate();
			if (callableStatement.getInt(2) == 1 ) return true;
			else return false;
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
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
			//fine LP PG21XX04 Leak
		}
	}

	/**
	 * Inserisce o sostituisce l'elenco delle applicazioni abilitate per un dato utente
	 * nella tabella "PYMNATB" --- NOTA: Le applicazioni presenti nella tabella
	 * vengono eliminate prima di inserire le nuove e, peranto, l'elenco fornito
	 * deve essere completo
	 *  
	 * @param userName
	 * @param listaApplicazioni
	 * @return int - Il numero di applicazioni inserite.
	 * @throws DaoException
	 */
	public int insertAppls(Long chiaveUtente, List<String> listaApplicazioni) throws DaoException
	{
		String applicazioni = "";
		int numero_applicazioni_inserite = 0;
		/*
		 * Se "userName" e/o "userProfile" non sono definiti 
		 * oppure "listaApplicazioni" è vuota genero una eccezione.
		 */
		if(chiaveUtente == null || listaApplicazioni == null) 
			throw new DaoException(1, Messages.PYUSER_APPLS_INS_ERR_NULL_PARAMETERS.format("PyUserDao","insertAppls"));
		/*
		 * Concateno le applicazioni in un'unica stringa
		 */
		int k = 0;
		for(Iterator<String> i = listaApplicazioni.iterator(); i.hasNext();)
		{
			applicazioni = (k == 0 ? i.next() : applicazioni.concat("|").concat(i.next()));
			//System.out.println("applicationi = " + applicazioni);
			k = 1;
		}
		/*
		 * Chiamo la SP
		 */
		CallableStatement callableStatement = null;
		try {
			callableStatement = prepareCall(Routines.MNA_INS_LISTA_APPL.routine());
			callableStatement.setLong(1, chiaveUtente);
			callableStatement.setString(2, applicazioni);
			callableStatement.registerOutParameter(3, Types.INTEGER);
			callableStatement.executeUpdate();
			numero_applicazioni_inserite = callableStatement.getInt(3); 
			return numero_applicazioni_inserite;
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		}
		finally
		{
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
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

}