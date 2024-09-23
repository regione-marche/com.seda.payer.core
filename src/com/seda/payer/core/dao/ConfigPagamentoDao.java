package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

//inizio LP PG21XX04 Leak
//import com.seda.data.dao.DAOHelper;
//fine LP PG21XX04 Leak
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.ConfigPagamento;
import com.seda.payer.core.bean.TemplateFunzioniPagamento;
import com.seda.payer.core.bean.ValidazioneRange;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class ConfigPagamentoDao extends BaseDaoHandler {
	
	//inizio LP 20240921 - PGNTECCSV-10
	private CallableStatement callableStatementList = null;
	//fine LP 20240921 - PGNTECCSV-10

	public ConfigPagamentoDao(Connection connection, String schema) {
		super(connection, schema);
	}
	
	public List<ConfigPagamento> doList(String codSocieta, String codUtente, String chiaveEnte, String canalePagamento) throws DaoException {
	//inizio LP 20240921 - PGNTECCSV-10
		return doListBatch(false, false, codSocieta, codUtente, chiaveEnte, canalePagamento);
	}

	public List<ConfigPagamento> doListBatch(boolean bFlagUpdateAutocommit, boolean bcloseStat, String codSocieta, String codUtente, String chiaveEnte, String canalePagamento) throws DaoException {
	//fine LP 20240921 - PGNTECCSV-10
		CallableStatement callableStatement = null;
		ResultSet data = null;
		ResultSet rsRange = null;
		ResultSet rsTemplFunzPag = null;
		try	{
			//inizio LP 20240921 - PGNTECCSV-10
			//callableStatement = prepareCall(Routines.CES_DOLIST_CONFIG.routine());
			if(callableStatementList == null) {
				callableStatementList = prepareCall(Routines.CES_DOLIST_CONFIG.routine());
			}
			callableStatement = callableStatementList;
			//fine LP 20240921 - PGNTECCSV-10
			callableStatement.setString(1, codSocieta);
			callableStatement.setString(2, codUtente);
			callableStatement.setString(3, chiaveEnte);
			callableStatement.setString(4, canalePagamento);
			if (callableStatement.execute()) {
				//resultset 1
				data = callableStatement.getResultSet();
				//la chiave è il CTSE (codice tipologia servizio) e il valore è l'oggetto ConfigPagamento
				//NB: la LinkedHashMap mantiene l'ordine di inserimento
				LinkedHashMap<String,ConfigPagamento> hmConfig = new LinkedHashMap<String,ConfigPagamento>();
				ConfigPagamento conf;
				while (data.next()) {
					conf = new ConfigPagamento(data);
					hmConfig.put(conf.getCodTipologiaServizio(), conf);
				}
				//resultset 2: range di validazione (li suddivido per tipologia servizio e li inserisco nell'oggetto ConfigPagamento corretto)
				if (callableStatement.getMoreResults()) {
					rsRange = callableStatement.getResultSet();
					ValidazioneRange range;
					while (rsRange.next()) {
						range = new ValidazioneRange(rsRange);
						if (hmConfig.containsKey(range.getCodTipologiaServizio())) {
							ConfigPagamento c = hmConfig.get(range.getCodTipologiaServizio());
							c.getListValidazioneRange().add(range);
						}
					}
				}
				//resultset 3: template funzioni pagamento (obbligatorietà campi) 
				//li suddivido per tipologia servizio e li inserisco nell'oggetto ConfigPagamento corretto
				if (callableStatement.getMoreResults()) {
					rsTemplFunzPag = callableStatement.getResultSet();
					TemplateFunzioniPagamento templfunzpag;
					while (rsTemplFunzPag.next()) {
						templfunzpag = new TemplateFunzioniPagamento(rsTemplFunzPag);
						if (hmConfig.containsKey(templfunzpag.getCodTipologiaServizio())) {
							ConfigPagamento c = hmConfig.get(templfunzpag.getCodTipologiaServizio());
							c.getListTemplateFunzioniPagamento().add(templfunzpag);
						}
					}
				}
				//converto l'hashmap in lista (prendendo solo i valori e tralasciando le chiavi)
				List<ConfigPagamento> listConf = new ArrayList<ConfigPagamento>();
				for (ConfigPagamento confRes : hmConfig.values())
					listConf.add(confRes);
				return listConf;
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
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
			//if (data != null)
			//	DAOHelper.closeIgnoringException(data);
			//if (rsRange != null)
			//	DAOHelper.closeIgnoringException(rsRange);
			//if (rsTemplFunzPag != null)
			//	DAOHelper.closeIgnoringException(rsTemplFunzPag);
			if (rsRange != null) {
				try {
					rsRange.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rsTemplFunzPag != null) {
				try {
					rsTemplFunzPag.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//inizio LP 20240921 - PGNTECCSV-10
			if(bcloseStat) {
			//fine LP 20240921 - PGNTECCSV-10
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240921 - PGNTECCSV-10
				callableStatement = null;
				callableStatementList = null;
			}
			//fine LP 20240921 - PGNTECCSV-10
			//fine LP PG21XX04 Leak
		}
	}
	
	public ConfigPagamento doDetail(String codSocieta, String codUtente, String chiaveEnte, String codTipologiaServizio, String canalePagamento) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		ConfigPagamento conf = null;
		try	{
			callableStatement = prepareCall(Routines.CES_DODETAIL_CONFIG.routine());
			callableStatement.setString(1, codSocieta);
			callableStatement.setString(2, codUtente);
			callableStatement.setString(3, chiaveEnte);
			callableStatement.setString(4, codTipologiaServizio);
			callableStatement.setString(5, canalePagamento);
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next())
					conf = new ConfigPagamento(data);
				return conf;
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
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
			//if (data != null)
			//	DAOHelper.closeIgnoringException(data);
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
	
	//PAGONET-537 SB - inizio
	public ConfigPagamento doDetailIdDominio(String idDominio, String canalePagamento, String codiceTipologiaServizio) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		ConfigPagamento conf = null;
		try	{
			callableStatement = prepareCall("PYCESSP_SEL_IDDOMINIO");
			callableStatement.setString(1, idDominio);
			callableStatement.setString(2, canalePagamento);
			callableStatement.setString(3, codiceTipologiaServizio);
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next())
					conf = new ConfigPagamento(data);
				return conf;
			}	
			return null;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
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
		}
	}
	//PAGONET-537 SB - fine
}
