package com.seda.payer.core.dao;

import com.seda.commons.security.TokenGenerator;
import com.seda.commons.string.Convert;
import com.seda.data.procedure.reflection.MetaProcedure;
import com.seda.data.procedure.reflection.ProcedureReflectorException;
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

        try {
            Connection connection = getConnection();
			//inizio LP PGNTCORE-24 
            //CallableStatement callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PRE_DOLIST.routine());
            CallableStatement callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PRE_DOLIST.routine());
			//fine LP PGNTCORE-24 

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
                ResultSet data = callableStatement.getResultSet();
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
		//inizio LP PGNTCORE-24 
        //} catch (HelperException e) {
        //    throw new RuntimeException(e);
        //}
	    } catch (ProcedureReflectorException e) {
	        throw new RuntimeException(e);
	    }
		//fine LP PGNTCORE-24 
    }

    public EsitoRisposte inserisciPrenotazione(PrenotazioneFatturazione prenotazione, String cfOperatore) throws DaoException {
        EsitoRisposte esitoRisposte = new EsitoRisposte();

        try {
            Connection connection = getConnection();
			//inizio LP PGNTCORE-24 
            //CallableStatement callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PRE_DOSAVE.routine());
            CallableStatement callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PRE_DOSAVE.routine());
			//fine LP PGNTCORE-24 

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
        }

        return esitoRisposte;
    }

    public List<PrenotazioneFatturazione> getPrenotazioni() throws DaoException {
        List<PrenotazioneFatturazione> list = new ArrayList<>();

        try {
            Connection connection = getConnection();
			//inizio LP PGNTCORE-24 
            //CallableStatement callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PRE_DOSELECT_REQ.routine());
            CallableStatement callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PRE_DOSELECT_REQ.routine());
			//fine LP PGNTCORE-24 
            if (callableStatement.execute()) {
                ResultSet data = callableStatement.getResultSet();
                while (data.next()){
                    list.add(new PrenotazioneFatturazione(callableStatement.getResultSet()));
                }
            }
            return list;
        } catch (SQLException e) {
            throw new DaoException(e);
		//inizio LP PGNTCORE-24 
        //} catch (HelperException e) {
        //    throw new RuntimeException(e);
        //}
	    } catch (ProcedureReflectorException e) {
	        throw new RuntimeException(e);
	    }
		//fine LP PGNTCORE-24 
    }

    public boolean aggiornaPrenotazione(String chiavePrenotazione, String flagElaborazione, String nomeFile) throws DaoException {
        Connection connection = getConnection();
        try {
			//inizio LP PGNTCORE-24 
            //CallableStatement callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PRE_DOUPDATE.routine());
            CallableStatement callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PRE_DOUPDATE.routine());
			//fine LP PGNTCORE-24 
            callableStatement.setString(1, chiavePrenotazione);
            callableStatement.setString(2, flagElaborazione);
            callableStatement.setString(3, nomeFile);
            callableStatement.registerOutParameter(4, Types.INTEGER);
            callableStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new DaoException(e);
		//inizio LP PGNTCORE-24 
        //} catch (HelperException e) {
        //    throw new RuntimeException(e);
        //}
	    } catch (ProcedureReflectorException e) {
	        throw new RuntimeException(e);
	    }
		//fine LP PGNTCORE-24 
    }

    public RegolaFatturazione getRegolaFatturazione(Date dataInizioValidita) throws DaoException {
        try {
            Connection connection = getConnection();
			//inizio LP PGNTCORE-24 
            //CallableStatement callableStatement = Helper.prepareCall(connection, getSchema(), Routines.REG_DOSELECT.routine());
            CallableStatement callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.REG_DOSELECT.routine());
			//fine LP PGNTCORE-24 
            callableStatement.setDate(1, dataInizioValidita);

            if (callableStatement.execute()) {
                ResultSet data = callableStatement.getResultSet();
                if (data.next()){
                    return new RegolaFatturazione(callableStatement.getResultSet());
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
		//inizio LP PGNTCORE-24 
        //} catch (HelperException e) {
        //    throw new RuntimeException(e);
        //}
	    } catch (ProcedureReflectorException e) {
	        throw new RuntimeException(e);
	    }
		//fine LP PGNTCORE-24 
        return null;
    }


    public boolean cancellaPrenotazione(String chiave) {
      try {
          Connection connection = getConnection();
          //inizio LP PGNTCORE-24 
          //CallableStatement callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PRE_DODELETE.routine());
          CallableStatement callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PRE_DODELETE.routine());
          //fine LP PGNTCORE-24 

          callableStatement.setString(1, chiave);

          if (callableStatement.execute()) {
              return true;
          } else {
              return false;
          }
      } catch (Throwable e) {
          e.printStackTrace();
          return false;
      }

    }



}
