package com.seda.payer.core.sosta.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.seda.data.page.Page;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.FattureRep;
import com.seda.payer.core.wallet.bean.Wallet;
import com.seda.payer.core.wallet.bean.WalletHomePageList;
import com.seda.payer.core.wallet.bean.WalletPageList;
	   
public interface SostaDAO extends Serializable { 

	public long calcolaSosta(String codiceUtente, String ente, String targa, String matricola, String nomevia,
			String numerocivico, Calendar dataorainiziososta, Calendar dataorafinesosta, long identificativoagevolazione)  throws DaoException;
}
