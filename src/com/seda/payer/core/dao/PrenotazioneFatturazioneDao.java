package com.seda.payer.core.dao;

import com.seda.commons.security.TokenGenerator;
import com.seda.commons.string.Convert;
import com.seda.data.helper.HelperException;
import com.seda.data.spi.DaoHandler;
import com.seda.data.spi.PageInfo;
import com.seda.payer.core.bean.PrenotazioneFatturazione;
import com.seda.payer.core.bean.PrenotazioneFatturazionePagelist;
import com.seda.payer.core.bean.RegolaFatturazione;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.EsitoRisposte;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrenotazioneFatturazioneDao extends DaoHandler {

    public PrenotazioneFatturazioneDao(Connection connection, String schema) {
        super(connection, schema);
    }

    public PrenotazioneFatturazionePagelist getPrenotazioneFatturazioneList(PrenotazioneFatturazione prenotazione) throws DaoException {
        String[] prenotazioneList = new String[2];
        //inizio LP 20240912 - PAGONET-604
        ResultSet data = null;
        CallableStatement callableStatement = null;
        //fine LP 20240912 - PAGONET-604
        try {
            //inizio LP 20240912 - PAGONET-604
            //Connection connection = getConnection();
            //CallableStatement callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PRE_DOLIST.routine());
            callableStatement = prepareCall(Routines.PRE_DOLIST.routine());
            //fine LP 20240912 - PAGONET-604
            callableStatement.setInt(1, prenotazione.getPageNumber());
            callableStatement.setInt(2, prenotazione.getRowsPerPage());
            callableStatement.setString(3, prenotazione.getOrder());
            callableStatement.setString(4, prenotazione.getCodiceSocieta() != null ? prenotazione.getCodiceSocieta() : "");
            callableStatement.setString(5, prenotazione.getCodiceUtente() != null ?prenotazione.getCodiceUtente() : "");
            callableStatement.setString(6, prenotazione.getCodiceEnte() != null ? prenotazione.getCodiceEnte() : "");
            callableStatement.setString(7, prenotazione.getDataPeriodoDa() != null ? prenotazione.getDataPeriodoDa() : "");
            callableStatement.setString(8, prenotazione.getDataPeriodoA() != null ? prenotazione.getDataPeriodoA() : "");
            callableStatement.setString(9, prenotazione.getFlagFatturazione() != null ? prenotazione.getFlagFatturazione() : "");
            callableStatement.setString(10, prenotazione.getDataRichiestaDa() != null ? prenotazione.getDataRichiestaDa() : "");
            callableStatement.setString(11, prenotazione.getDataRichiestaA() != null ? prenotazione.getDataRichiestaA() : "");
            callableStatement.setString(12, prenotazione.getTipologiRichiesta() != null ? prenotazione.getTipologiRichiesta() : "");
            callableStatement.registerOutParameter(13, Types.VARCHAR);
            callableStatement.registerOutParameter(14, Types.INTEGER);
            callableStatement.registerOutParameter(15, Types.INTEGER);
            callableStatement.registerOutParameter(16, Types.INTEGER);
            callableStatement.registerOutParameter(17, Types.SMALLINT);

            PageInfo pageInfo = null;

            if (callableStatement.execute()) {
                pageInfo = new PageInfo();
                pageInfo.setPageNumber(prenotazione.getPageNumber());
                pageInfo.setRowsPerPage(prenotazione.getRowsPerPage());
                pageInfo.setFirstRow(callableStatement.getInt(14));
                pageInfo.setLastRow(callableStatement.getInt(15));
                pageInfo.setNumRows(callableStatement.getInt(16));
                pageInfo.setNumPages(callableStatement.getInt(17));
                //inizio LP 20240912 - PAGONET-604
                //ResultSet data = callableStatement.getResultSet();
                data = callableStatement.getResultSet();
                //fine LP 20240912 - PAGONET-604
                this.loadWebRowSet(data);

                prenotazioneList[0] = Convert.webRowSetToString(this.getWebRowSetImpl());
                if (callableStatement.getMoreResults()) {
                    try {
                        data.close();
                    } catch (SQLException e) {
                        throw new DaoException(e);
                    }
                    data = callableStatement.getResultSet();
                    loadWebRowSet(data);
                    prenotazioneList[1] = Convert.webRowSetToString(this.getWebRowSetImpl());
                }
            }
            return new PrenotazioneFatturazionePagelist(pageInfo, "00", "", prenotazioneList);
        } catch (SQLException e) {
            throw new DaoException(e);
        } catch (HelperException e) {
            throw new RuntimeException(e);
        //inizio LP 20240912 - PAGONET-604
        } finally {
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				data = null;
			}
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				callableStatement = null;
			}
        //fine LP 20240912 - PAGONET-604
        }
    }

    public EsitoRisposte inserisciPrenotazione(PrenotazioneFatturazione prenotazione, String cfOperatore) throws DaoException {
        EsitoRisposte esitoRisposte = new EsitoRisposte();
        //inizio LP 20240912 - PAGONET-604
        CallableStatement callableStatement = null;
        //fine LP 20240912 - PAGONET-604
        try {
            //inizio LP 20240912 - PAGONET-604
            //Connection connection = getConnection();
            //CallableStatement callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PRE_DOSAVE.routine());
            callableStatement = prepareCall(Routines.PRE_DOSAVE.routine());
            //fine LP 20240912 - PAGONET-604
            callableStatement.setString(1, TokenGenerator.generateUUIDToken());
            callableStatement.setString(2, prenotazione.getCodiceSocieta() != null ? prenotazione.getCodiceSocieta() : "");
            callableStatement.setString(3, prenotazione.getCodiceUtente() != null ? prenotazione.getCodiceUtente() : "");
            callableStatement.setString(4, prenotazione.getCodiceEnte() != null ? prenotazione.getCodiceEnte() : "");
            callableStatement.setString(5, new Timestamp(System.currentTimeMillis()).toString());
            callableStatement.setString(6, cfOperatore != null ? cfOperatore : "");
            callableStatement.setString(7, prenotazione.getTipologiRichiesta()==null ? "FAT" : prenotazione.getTipologiRichiesta());
            callableStatement.setString(8, prenotazione.getDataRichiestaDa() != null ? prenotazione.getDataRichiestaDa() : "");
            callableStatement.setString(9, prenotazione.getDataRichiestaA() != null ? prenotazione.getDataRichiestaA() : "" );
            callableStatement.setString(10, "1"); // in attesa
            callableStatement.setString(11, ""); // nome flusso vuoto (lo valorizza il batch)
            callableStatement.registerOutParameter(12, Types.VARCHAR);
            callableStatement.registerOutParameter(13, Types.VARCHAR);
            callableStatement.execute();
            esitoRisposte.setCodiceMessaggio(callableStatement.getString(12));
            esitoRisposte.setDescrizioneMessaggio(callableStatement.getString(13));
        } catch (SQLException e) {
            throw new DaoException(e);
        } catch (Exception e) {
            e.printStackTrace();
        //inizio LP 20240912 - PAGONET-604
        } finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				callableStatement = null;
			}
        //fine LP 20240912 - PAGONET-604
        }
        return esitoRisposte;
    }

    //inizio LP 20240912 - PAGONET-604
    public List<PrenotazioneFatturazione> getPrenotazioni() throws DaoException {
    	return getPrenotazioniTail(true);
    }
    	
    public List<PrenotazioneFatturazione> getPrenotazioniTail(boolean bFlagUpdateAutocomit) throws DaoException {
    	CallableStatement callableStatement = null;
    	ResultSet data = null;
    //fine LP 20240912 - PAGONET-604
        List<PrenotazioneFatturazione> list = new ArrayList<>();

        try {
            //inizio LP 20240912 - PAGONET-604
            //Connection connection = getConnection();
            //CallableStatement callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PRE_DOSELECT_REQ.routine());
    		callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.PRE_DOSELECT_REQ.routine());
            //fine LP 20240912 - PAGONET-604
            if (callableStatement.execute()) {
                //inizio LP 20240912 - PAGONET-604
                //ResultSet data = callableStatement.getResultSet();
                data = callableStatement.getResultSet();
                //fine LP 20240912 - PAGONET-604
                while (data.next()){
                    list.add(new PrenotazioneFatturazione(callableStatement.getResultSet()));
                }
            }
            return list;
        } catch (SQLException e) {
            throw new DaoException(e);
        } catch (HelperException e) {
            throw new RuntimeException(e);
        //inizio LP 20240912 - PAGONET-604
        } finally {
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				data = null;
			}
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				callableStatement = null;
			}
		//fine LP 20240912 - PAGONET-604
	    }
    }

    //inizio LP 20240912 - PAGONET-604
    public boolean aggiornaPrenotazione(String chiavePrenotazione, String flagElaborazione, String nomeFile) throws DaoException {
    	return aggiornaPrenotazioneTail(true, chiavePrenotazione, flagElaborazione, nomeFile);
    }
    
    public boolean aggiornaPrenotazioneTail(boolean bFlagUpdateAutocomit, String chiavePrenotazione, String flagElaborazione, String nomeFile) throws DaoException {
    	CallableStatement callableStatement = null;
    //fine LP 20240912 - PAGONET-604
        //Connection connection = getConnection(); //LP 20240912 - PAGONET-604
        try {
			//inizio LP PGNTCORE-24 
            //CallableStatement callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PRE_DOUPDATE.routine());
    		callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.PRE_DOUPDATE.routine());
			//fine LP PGNTCORE-24 
            callableStatement.setString(1, chiavePrenotazione);
            callableStatement.setString(2, flagElaborazione);
            callableStatement.setString(3, nomeFile);
            callableStatement.registerOutParameter(4, Types.INTEGER);
            callableStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new DaoException(e);
        } catch (HelperException e) {
            throw new RuntimeException(e);
        //inizio LP 20240912 - PAGONET-604
        } finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				callableStatement = null;
			}
		//fine LP 20240912 - PAGONET-604
	    }
    }

    public RegolaFatturazione getRegolaFatturazione(Date dataInizioValidita) throws DaoException {
    //inizio LP 20240912 - PGNTCORE-24/PGNTFATT-5
    	return getRegolaFatturazioneTail(true, dataInizioValidita);
    }
    	
    public RegolaFatturazione getRegolaFatturazioneTail(boolean bFlagUpdateAutocomit, Date dataInizioValidita) throws DaoException {
    	ResultSet data = null;
    	CallableStatement callableStatement = null;
    //fine LP 20240912 - PGNTCORE-24/PGNTFATT-5
        try {
            //inizio LP 20240912 - PGNTCORE-24/PGNTFATT-5
            //Connection connection = getConnection();
            //CallableStatement callableStatement = Helper.prepareCall(connection, getSchema(), Routines.REG_DOSELECT.routine());
            callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.REG_DOSELECT.routine());
            //fine LP 20240912 - PGNTCORE-24/PGNTFATT-5
            callableStatement.setDate(1, dataInizioValidita);

            if (callableStatement.execute()) {
                //inizio LP 20240912 - PGNTCORE-24/PGNTFATT-5
                //ResultSet data = callableStatement.getResultSet();
                data = callableStatement.getResultSet();
                //fine LP 20240912 - PGNTCORE-24/PGNTFATT-5
                if (data.next()){
                    return new RegolaFatturazione(callableStatement.getResultSet());
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } catch (HelperException e) {
            throw new RuntimeException(e);
        //inizio LP 20240912 - PAGONET-604/PGNTFATT-5
        } finally {
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				data = null;
			}
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				callableStatement = null;
			}
        //fine LP 20240912 - PAGONET-604/PGNTFATT-5
	    }
        return null;
    }

    public boolean cancellaPrenotazione(String chiave) {
		CallableStatement callableStatement = null; //LP 20240912 - PGNTCORE-24/PGNTFATT-5
		try {
			//inizio LP 20240912 - PAGONET-604
			//Connection connection = getConnection();
			//CallableStatement callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PRE_DODELETE.routine());
			callableStatement = prepareCall(Routines.PRE_DODELETE.routine());
			//fine LP 20240912 - PAGONET-604
			callableStatement.setString(1, chiave);
			if (callableStatement.execute()) {
				return true;
			} else {
				return false;
			}
		} catch (Throwable e) {
			e.printStackTrace();
			return false;
        //inizio LP 20240912 - PAGONET-604/PGNTFATT-5
        } finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				callableStatement = null;
			}
        //fine LP 20240912 - PAGONET-604/PGNTFATT-5
		}
	}
}
