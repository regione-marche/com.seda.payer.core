package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class RegolaFatturazione implements Serializable {

    private static final long serialVersionUID = 1L;
    private Date dataInizioValidita;
    private String descrizioneCausale;
    private int qtaMinimaFatturabile;
    private int qtaMassimaFatturabile;
    private BigDecimal prezzoUnitario;
    private String tipoDocumento;
    private String contropartita;

    public RegolaFatturazione(ResultSet data) throws SQLException {
        this.dataInizioValidita = data.getDate("REG_DREGINI");
        this.descrizioneCausale =  data.getString("REG_DREGDCAUS");
        this.qtaMinimaFatturabile =  data.getInt("REG_IREGQTMIN");
        this.qtaMassimaFatturabile =  data.getInt("REG_IREGQTMAX");
        this.prezzoUnitario =  data.getBigDecimal("REG_IREGIUNI");
        this.tipoDocumento =  data.getString("REG_TREGTDOC");
        this.contropartita =  data.getString("REG_CREGCCPAR");
    }

    public Date getDataInizioValidita() {
        return dataInizioValidita;
    }

    public void setDataInizioValidita(Date dataInizioValidita) {
        this.dataInizioValidita = dataInizioValidita;
    }

    public String getDescrizioneCausale() {
        return descrizioneCausale;
    }

    public void setDescrizioneCausale(String descrizioneCausale) {
        this.descrizioneCausale = descrizioneCausale;
    }

    public int getQtaMinimaFatturabile() {
        return qtaMinimaFatturabile;
    }

    public void setQtaMinimaFatturabile(int qtaMinimaFatturabile) {
        this.qtaMinimaFatturabile = qtaMinimaFatturabile;
    }

    public int getQtaMassimaFatturabile() {
        return qtaMassimaFatturabile;
    }

    public void setQtaMassimaFatturabile(int qtaMassimaFatturabile) {
        this.qtaMassimaFatturabile = qtaMassimaFatturabile;
    }

    public BigDecimal getPrezzoUnitario() {
        return prezzoUnitario;
    }

    public void setPrezzoUnitario(BigDecimal prezzoUnitario) {
        this.prezzoUnitario = prezzoUnitario;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getContropartita() {
        return contropartita;
    }

    public void setContropartita(String contropartita) {
        this.contropartita = contropartita;
    }

    @Override
    public String toString() {
        return "RegoleFatturazione{" +
                "dataInizioValidita=" + dataInizioValidita +
                ", descrizioneCausale='" + descrizioneCausale + '\'' +
                ", qtaMinimaFatturabile='" + qtaMinimaFatturabile + '\'' +
                ", qtaMassimaFatturabile='" + qtaMassimaFatturabile + '\'' +
                ", prezzoUnitario=" + prezzoUnitario +
                ", tipoDocumento='" + tipoDocumento + '\'' +
                ", contropartita='" + contropartita + '\'' +
                '}';
    }
}
