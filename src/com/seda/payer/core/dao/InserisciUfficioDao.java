package com.seda.payer.core.dao;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.security.TokenGenerator;
import com.seda.commons.string.Convert;
import com.seda.data.helper.Helper;
import com.seda.data.spi.DaoHandler;
import com.seda.data.spi.PageInfo;
import com.seda.payer.core.bean.ComunicazioneUfficio;
import com.seda.payer.core.bean.ComunicazioneUfficioPageList;
import com.seda.payer.core.bean.PrenotazioneFatturazionePagelist;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.EsitoRisposte;
import com.seda.payer.core.wallet.bean.EsitoRisposteUfficio;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class InserisciUfficioDao extends DaoHandler {

    protected com.seda.commons.logger.LoggerWrapper logger = CustomLoggerManager.get(getClass());

    /**
     * Constructor of DAO object
     *
     * @param connection The {@link Connection} object used in the DAO method operations
     * @param schema     the <code>String</code> that represents schema of the database objects
     */
    public InserisciUfficioDao(Connection connection, String schema) {
        super(connection, schema);
    }

    public EsitoRisposte insertParametriUfficio(ComunicazioneUfficio parametriUfficio) {

        EsitoRisposte esito = new EsitoRisposteUfficio();

        try{
            Connection connection = getConnection();
            CallableStatement callableStatement = Helper.prepareCall(connection, getSchema(), "PYINFTB_INS");

            callableStatement.setString(1, TokenGenerator.generateUUIDToken());
            callableStatement.setDate(2, Date.valueOf(parametriUfficio.getDataConferma()));
            callableStatement.setDate(3,Date.valueOf(parametriUfficio.getDataScadenza()));
            callableStatement.setString(4,parametriUfficio.getDataesecuzione());
            callableStatement.setString(5,parametriUfficio.getTipoRichiesta());
            callableStatement.setString(6,parametriUfficio.getStato());
            callableStatement.setString(7,parametriUfficio.getOperatore());

            callableStatement.execute();
            esito.setCodiceMessaggio("0");

        }catch (Throwable e) {
            logger.info("errore inserimento richiesta: " + e.getMessage());
            e.printStackTrace();
            esito.setCodiceMessaggio("1");
        }

        return esito;
    }



    public ComunicazioneUfficioPageList getParametriPrenotazione(ComunicazioneUfficio parametriUfficio,String stato) {
        String[] ufficioList = new String[2];

        try{
            Connection connection = getConnection();
            CallableStatement callableStatement = Helper.prepareCall(connection, getSchema(),"PYINFTB_SEL");

            callableStatement.setInt(1, parametriUfficio.getPageNumber());
            callableStatement.setInt(2, parametriUfficio.getRowsPerPage());
            callableStatement.setString(3, parametriUfficio.getOrder());
            if(stato!=null) {
                if(stato.equals("D")) {stato= "Da Elaborare";} else if (stato.equals("T")) {
                    stato="Terminata";
                }
            }
            callableStatement.setString(4, stato != null ? stato : "");
            callableStatement.setString(5, parametriUfficio.getDataesecuzione());
            callableStatement.registerOutParameter(6, Types.VARCHAR);
            callableStatement.registerOutParameter(7, Types.INTEGER);
            callableStatement.registerOutParameter(8, Types.INTEGER);
            callableStatement.registerOutParameter(9, Types.INTEGER);
            callableStatement.registerOutParameter(10, Types.SMALLINT);

            PageInfo pageInfo = null;

            if (callableStatement.execute()) {
                pageInfo = new PageInfo();
                pageInfo.setPageNumber(parametriUfficio.getPageNumber());
                pageInfo.setRowsPerPage(parametriUfficio.getRowsPerPage());
                pageInfo.setFirstRow(callableStatement.getInt(7));
                pageInfo.setLastRow(callableStatement.getInt(8));
                pageInfo.setNumRows(callableStatement.getInt(9));
                pageInfo.setNumPages(callableStatement.getInt(10));
                ResultSet data = callableStatement.getResultSet();
                this.loadWebRowSet(data);

                ufficioList[0] = Convert.webRowSetToString(this.getWebRowSetImpl());
                if (callableStatement.getMoreResults()) {
                    try {
                        data.close();
                    } catch (SQLException e) {
                        throw new DaoException(e);
                    }
                    data = callableStatement.getResultSet();
                    loadWebRowSet(data);
                    ufficioList[1] = Convert.webRowSetToString(this.getWebRowSetImpl());
                }
            }
            return new ComunicazioneUfficioPageList(pageInfo, "00", "", ufficioList);


        }catch(Throwable e){
            logger.info("errore getParametriPrenotazione" + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }



//PYINFSP_SEL_BATCH

public void getParametriBatch(){
        Connection connection = getConnection();

}











}









