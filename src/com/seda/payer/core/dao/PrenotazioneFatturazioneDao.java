package com.seda.payer.core.dao;

import com.seda.commons.security.TokenGenerator;
import com.seda.commons.string.Convert;
import com.seda.data.helper.Helper;
import com.seda.data.helper.HelperException;
import com.seda.data.spi.DaoHandler;
import com.seda.data.spi.PageInfo;
import com.seda.payer.core.bean.PrenotazioneFatturazione;
import com.seda.payer.core.bean.PrenotazioneFatturazionePagelist;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.EsitoRisposte;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;

public class PrenotazioneFatturazioneDao extends DaoHandler {

    public PrenotazioneFatturazioneDao(Connection connection, String schema) {
        super(connection, schema);
    }

    public PrenotazioneFatturazionePagelist getPrenotazioneFatturazioneList(PrenotazioneFatturazione prenotazione) throws DaoException {
        String[] prenotazioneList = new String[2];

        try {
            Connection connection = getConnection();
            CallableStatement callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PRE_DOLIST.routine());

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
            callableStatement.registerOutParameter(12, Types.VARCHAR);
            callableStatement.registerOutParameter(13, Types.INTEGER);
            callableStatement.registerOutParameter(14, Types.INTEGER);
            callableStatement.registerOutParameter(15, Types.INTEGER);
            callableStatement.registerOutParameter(16, Types.SMALLINT);

            PageInfo pageInfo = null;

            if (callableStatement.execute()) {
                pageInfo = new PageInfo();
                pageInfo.setPageNumber(prenotazione.getPageNumber());
                pageInfo.setRowsPerPage(prenotazione.getRowsPerPage());
                pageInfo.setFirstRow(callableStatement.getInt(13));
                pageInfo.setLastRow(callableStatement.getInt(14));
                pageInfo.setNumRows(callableStatement.getInt(15));
                pageInfo.setNumPages(callableStatement.getInt(16));
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
        } catch (HelperException e) {
            throw new RuntimeException(e);
        }
    }

    public EsitoRisposte inserisciPrenotazione(PrenotazioneFatturazione prenotazione, String cfOperatore) throws DaoException {
        EsitoRisposte esitoRisposte = new EsitoRisposte();

        try {
            Connection connection = getConnection();
            CallableStatement callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PRE_DOSAVE.routine());

            callableStatement.setString(1, TokenGenerator.generateUUIDToken());
            callableStatement.setString(2, prenotazione.getCodiceSocieta() != null ? prenotazione.getCodiceSocieta() : "");
            callableStatement.setString(3, prenotazione.getCodiceUtente() != null ? prenotazione.getCodiceUtente() : "");
            callableStatement.setString(4, prenotazione.getCodiceEnte() != null ? prenotazione.getCodiceEnte() : "");
            callableStatement.setString(5, new Timestamp(System.currentTimeMillis()).toString());
            callableStatement.setString(6, cfOperatore != null ? cfOperatore : "");
            callableStatement.setString(7, "FAT");
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
}
