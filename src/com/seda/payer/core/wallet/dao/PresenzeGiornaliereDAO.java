package com.seda.payer.core.wallet.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.PresenzeGiornaliere;
import com.seda.payer.core.wallet.bean.Wallet;


public interface PresenzeGiornaliereDAO  {
	public ArrayList<String> listTributi(Wallet wallet) throws  DaoException;
	public PresenzeGiornaliere insertBatch(PresenzeGiornaliere presenzeGiornaliere) throws  Exception;
	public ArrayList<PresenzeGiornaliere> listAnnoScolastico(PresenzeGiornaliere presenzeGiornaliere) throws  DaoException;
	public ArrayList<Calendar> listGiorniPresenze(String idWallet,String anno,String mese,String codAnaFiglio,String codScuola, String tariffa, String causale) throws  DaoException;
	public HashMap<Calendar,String> listCausalePresenze(String idWallet,String anno,String mese,String codAnaFiglio,String codScuola, String tariffa, String causale) throws  DaoException;
	public HashMap<Calendar,String> listCausalePresenzeAbilitazione(String idWallet,String anno,String mese,String codAnaFiglio,String codScuola, String tariffa) throws  DaoException;
	public PresenzeGiornaliere insertDiscarico(PresenzeGiornaliere presenzeGiornaliere) throws  Exception;
}
