package com.seda.payer.core.wallet.dao;

import java.io.Serializable;
import java.sql.SQLException;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.AnagraficaSoggettoSEPA;
import com.seda.payer.core.bean.Autorizzazione;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.Wallet;
	   
public interface SepaDAO extends Serializable {
	
	public final static String SEPA_XML= "SEPA_XML";
							
//	public String selectRid(String cuteCute, String codBors) throws  DaoException;
	
	public Wallet selectSepa(Wallet wallet) throws  DaoException;

	//inizio LP PG21XX04 Leak
	public Wallet selectSepaWeb(Wallet wallet) throws DaoException;
	//fine LP PG21XX04 Leak

	public String selectSepa(String cuteCute, String rid) throws  DaoException;
	
	public String selectNewRid(Wallet wallet) throws DaoException, SQLException, HelperException;

	//inizio LP 20240828 - PGNTCORE-24/PAGONET-604
	public String selectNewRidTail(boolean FlagUpdateAutocommit, Wallet wallet) throws DaoException, SQLException, HelperException;
	//fine LP 20240828 - PGNTCORE-24/PAGONET-604

	//PG22XX09_SB2 - inizio
	public Integer insertSepaWeb(Autorizzazione autorizzazione) throws DaoException;
	
	public Integer insertSepaDas(AnagraficaSoggettoSEPA anagrafica) throws DaoException;
	
	public String selectSepaEC(String cuteCute, String rid) throws  DaoException;
	//PG22XX09_SB2 - fine
	
	public void closeCallableStatementS(); //LP 20240913 - PGNTCORE-24/PAGONET-604
	
}