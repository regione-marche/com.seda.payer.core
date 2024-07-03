package com.seda.payer.core.bean;

import java.time.*;
import java.util.Objects;

public class ComunicazioneUfficio {

    private LocalDate dataConferma;
    private LocalDate dataScadenza;
    private String dataesecuzione;
    private String tipoRichiesta;
    private String stato;
    private String operatore;

    private int pageNumber;
    private int rowsPerPage;
    private String order;
    private String codSoc;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComunicazioneUfficio that = (ComunicazioneUfficio) o;
        return Objects.equals(dataConferma, that.dataConferma) && Objects.equals(dataScadenza, that.dataScadenza) && Objects.equals(dataesecuzione, that.dataesecuzione) && Objects.equals(tipoRichiesta, that.tipoRichiesta) && Objects.equals(stato, that.stato) && Objects.equals(operatore, that.operatore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataConferma, dataScadenza, dataesecuzione, tipoRichiesta, stato, operatore);
    }

    @Override
    public String toString() {
        return "ComunicazioneUfficio{" +
                "dataConferma=" + dataConferma +
                ", dataScadenza=" + dataScadenza +
                ", dataesecuzione=" + dataesecuzione +
                ", tipoRichiesta='" + tipoRichiesta + '\'' +
                ", stato='" + stato + '\'' +
                ", operatore='" + operatore + '\'' +
                '}';
    }

    public String getOperatore() {
        return operatore;
    }

    public void setOperatore(String operatore) {
        this.operatore = operatore;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getTipoRichiesta() {
        return tipoRichiesta;
    }

    public void setTipoRichiesta(String tipoRichiesta) {
        this.tipoRichiesta = tipoRichiesta;
    }

    public String getDataesecuzione() {
        return dataesecuzione;
    }

    public void setDataesecuzione(String dataesecuzione) {
        this.dataesecuzione = dataesecuzione;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public LocalDate getDataConferma() {
        return dataConferma;
    }

    public void setDataConferma(LocalDate dataConferma) {
        this.dataConferma = dataConferma;
    }

    public String getCodSoc() {
        return codSoc;
    }

    public void setCodSoc(String codSoc) {
        this.codSoc = codSoc;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
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
}
