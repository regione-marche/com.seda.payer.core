package com.seda.payer.core.wallet.dao;

import java.io.Serializable;
import java.util.ArrayList;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.AddebitiBorsellino;
import com.seda.payer.core.wallet.bean.AddebitiPopup;
import com.seda.payer.core.wallet.bean.Wallet;

public interface AddebitiBorsellinoDAO  extends Serializable {
	
	public final static String ADDEBITI_FLAG_ESPANSIONE= "FLAGESPANSIONE";
	public final static String DATA_ADDEBITO= "DATAADDEBITO";
	public final static String MESE= "MESE";
	public final static String ANNO= "ANNO";
	public final static String DESCRIZIONE= "DESCRIZIONE";
	public final static String IMPORTOSTR= "IMPORTOSTR";
	public final static String SEGNO_ADDEBITO= "SEGNOADDEBITO";
	
    public ArrayList<AddebitiBorsellino> addebitiList(Wallet wallet,String anno)throws  DaoException;
    public ArrayList<AddebitiBorsellino> addebitiListDett(Wallet wallet,String anno,String mese)throws  DaoException;
    public ArrayList<AddebitiPopup> addebitiPopup(Wallet wallet,String data, String segno)throws  DaoException;
}
