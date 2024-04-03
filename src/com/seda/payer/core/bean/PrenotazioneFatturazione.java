package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrenotazioneFatturazione implements Serializable {
    private static final long serialVersionUID = 1L;
    private int rowsPerPage;
    private int pageNumber;
    private String order;
    private String codiceSocieta;
    private String codiceUtente;
    private String codiceEnte;
    private String dataPeriodoDa;
    private String dataPeriodoA;
    private String flagFatturazione;
    private String dataRichiestaDa;
    private String dataRichiestaA;

    // dati per elaborazione fatturazione
    private String chiavePrenotazione;
    private String dataRichiesta;
    ;

    public PrenotazioneFatturazione() {}

    public PrenotazioneFatturazione(int rowsPerPage, int pageNumber, String order, String codiceSocieta, String codiceUtente, String codiceEnte, String dataPeriodoDa, String dataPeriodoA, String flagFatturazione, String dataRichiestaDa, String dataRichiestaA) {
        this.rowsPerPage = rowsPerPage;
        this.pageNumber = pageNumber;
        this.order = order;
        this.codiceSocieta = codiceSocieta;
        this.codiceUtente = codiceUtente;
        this.codiceEnte = codiceEnte;
        this.dataPeriodoDa = dataPeriodoDa;
        this.dataPeriodoA = dataPeriodoA;
        this.flagFatturazione = flagFatturazione;
        this.dataRichiestaDa = dataRichiestaDa;
        this.dataRichiestaA = dataRichiestaA;
    }

    public PrenotazioneFatturazione(ResultSet data) throws SQLException {
        this.chiavePrenotazione = data.getString("PRE_KPREKPRE");
        this.codiceSocieta =  data.getString("PRE_CSOCCSOC");
        this.codiceUtente =  data.getString("PRE_CUTECUTE");
        this.codiceEnte = data.getString("PRE_KANEKENT");
        this.dataRichiesta =  data.getString("PRE_GPREDPRE");
        this.dataPeriodoDa =  data.getString("PRE_GPREGPRDA");
        this.dataPeriodoA =  data.getString("PRE_GPREGPRA");
        this.flagFatturazione =  data.getString("PRE_FPREFELAB");
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }

    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getCodiceSocieta() {
        return codiceSocieta;
    }

    public void setCodiceSocieta(String codiceSocieta) {
        this.codiceSocieta = codiceSocieta;
    }

    public String getCodiceUtente() {
        return codiceUtente;
    }

    public void setCodiceUtente(String codiceUtente) {
        this.codiceUtente = codiceUtente;
    }

    public String getCodiceEnte() {
        return codiceEnte;
    }

    public void setCodiceEnte(String codiceEnte) {
        this.codiceEnte = codiceEnte;
    }

    public String getDataPeriodoDa() {
        return dataPeriodoDa;
    }

    public void setDataPeriodoDa(String dataPeriodoDa) {
        this.dataPeriodoDa = dataPeriodoDa;
    }

    public String getDataPeriodoA() {
        return dataPeriodoA;
    }

    public void setDataPeriodoA(String dataPeriodoA) {
        this.dataPeriodoA = dataPeriodoA;
    }

    public String getFlagFatturazione() {
        return flagFatturazione;
    }

    public void setFlagFatturazione(String flagFatturazione) {
        this.flagFatturazione = flagFatturazione;
    }

    public String getDataRichiestaDa() {
        return dataRichiestaDa;
    }

    public void setDataRichiestaDa(String dataRichiestaDa) {
        this.dataRichiestaDa = dataRichiestaDa;
    }

    public String getDataRichiestaA() {
        return dataRichiestaA;
    }

    public void setDataRichiestaA(String dataRichiestaA) {
        this.dataRichiestaA = dataRichiestaA;
    }

    public String getChiavePrenotazione() {
        return chiavePrenotazione;
    }

    public void setChiavePrenotazione(String chiavePrenotazione) {
        this.chiavePrenotazione = chiavePrenotazione;
    }

    @Override
    public String toString() {
        return "PrenotazioneFatturazioni{" +
                "rowsPerPage=" + rowsPerPage +
                ", pageNumber=" + pageNumber +
                ", order='" + order + '\'' +
                ", codiceSocieta='" + codiceSocieta + '\'' +
                ", codiceUtente='" + codiceUtente + '\'' +
                ", codiceEnte='" + codiceEnte + '\'' +
                ", dataPeriodoDa='" + dataPeriodoDa + '\'' +
                ", dataPeriodoA='" + dataPeriodoA + '\'' +
                ", flagFatturazione='" + flagFatturazione + '\'' +
                ", dataRichiestaDa='" + dataRichiestaDa + '\'' +
                ", dataRichiestaA='" + dataRichiestaA + '\'' +
                '}';
    }

}
