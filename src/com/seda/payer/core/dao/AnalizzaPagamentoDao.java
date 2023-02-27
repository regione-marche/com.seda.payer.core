package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.seda.payer.core.bean.AnalisiPagamenti;
import com.seda.payer.core.bean.DettaglioAnalisiPagamento;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class AnalizzaPagamentoDao  extends BaseDaoHandler{

	public AnalizzaPagamentoDao(Connection connection, String schema) {
		super(connection, schema);
	}
	
	@SuppressWarnings("null")
	public AnalisiPagamenti getListaTransazioniAnalisiPagamento (int periodoindagine, int maxtentativi) 
																			throws DaoException  {
		
		CallableStatement callableStatement = null;
		ResultSet data = null;
		int i = 0;
		
		DettaglioAnalisiPagamento risultato ;
		try {
			callableStatement = prepareCall(Routines.PYTRA_ANALISI_TENTATIVI.routine());
			callableStatement.setInt(1, periodoindagine);
			callableStatement.setInt(2, maxtentativi);
			
			List<DettaglioAnalisiPagamento> listaCF = new ArrayList<DettaglioAnalisiPagamento>();
			List<DettaglioAnalisiPagamento> listaIP = new ArrayList<DettaglioAnalisiPagamento>();
			List<DettaglioAnalisiPagamento> listaEmail = new ArrayList<DettaglioAnalisiPagamento>();
			List<DettaglioAnalisiPagamento> listaBollettino = new ArrayList<DettaglioAnalisiPagamento>();
			List<DettaglioAnalisiPagamento> listaCanale = new ArrayList<DettaglioAnalisiPagamento>();
			
			boolean resultsAvailable = callableStatement.execute();
			
			while (resultsAvailable) {
				i++;
				data = callableStatement.getResultSet();
				
				while (data.next()) {
					
					risultato=new DettaglioAnalisiPagamento();
					risultato.setChiaveTrx(data.getString("TRA_KTRAKTRA"));
					risultato.setData(data.getString("TRA_GTRADTRA"));
					
					switch (i)
					{
						case (1):
							listaCF.add(risultato);
							break;
						case (2): 
							listaIP.add(risultato);
							break;
						case (3): 
							listaEmail.add(risultato);
							break;
						case (4): 
							listaBollettino.add(risultato);
							break;
						case (5): 
							listaCanale.add(risultato);
							break;
					}
					
					risultato=null;

				}
				resultsAvailable = callableStatement.getMoreResults();
			}
			AnalisiPagamenti ListaTotale = new AnalisiPagamenti();
			ListaTotale.setListCF(listaCF);
			ListaTotale.setListIP(listaIP);
			ListaTotale.setListEmail(listaEmail);
			ListaTotale.setListBollettino(listaBollettino);
			ListaTotale.setListCanalePag(listaCanale);
	
			return ListaTotale;
			
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
			if(data != null) {
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
}

