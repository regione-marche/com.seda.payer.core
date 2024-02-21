package com.seda.payer.core.bean;

import com.seda.payer.commons.bean.Lifecycle;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class PrenotazioneFatturazione extends Lifecycle implements Serializable {
    private String chiavePrenotazione; // FAT_KFATKFAT
    private String codiceEnte; // FAT_CANECENT
    private String codiceFiscaleEnte; // FAT_CENTCFIS
    private String descrizioneEnte; // FAT_DANEDENT
    private String dataPrenotazione; // FAT_GFATGFAT
    private String descrizioneCausale; // FAT_DFATDCAUS
    private String periodoFatturazione; // FAT_DFATPFAT
    private String periodoEsportazione; // FAT_DFATDPER
    private String qtaMinimaFatturabile; // FAT_NFATQMIN
    private String qtaMassimaFatturabile; // FAT_NFATQMAX
    private String nTransazioniPrimoSemestre; // FAT_NFATNTRAI
    private String nTransazioniSecondoSemestre; // FAT_NFATNTRAII
    private String qtaDaFatturare; // FAT_NFATQTFAT
    private String prezzoUnitario; // FAT_IFATIUNI
    private String importo; // FAT_IFATIFAT
    private String flagFatturabile; // FAT_FFATFFAT
    private String tipoDocumento; // FAT_CFATTDOC
    private String contropartita; // FAT_CFATCCPAR
    private String importoTransatoPrimoSemestre; // FAT_IFATITRAI
    private String importoTransatoAnno; // FAT_IFATITRA
    private String dataAggiuntaRecord; // FTR_GFTRCREA
    private String statoPrenotazione; // FAT_CFATCSTAT
    private String dataInizioValiditaRegole; // FAT_GFATIVAL

    public PrenotazioneFatturazione(String chiavePrenotazione, String codiceEnte, String codiceFiscaleEnte, String descrizioneEnte, String dataPrenotazione, String descrizioneCausale, String periodoFatturazione, String periodoEsportazione, String qtaMinimaFatturabile, String qtaMassimaFatturabile, String nTransazioniPrimoSemestre, String nTransazioniSecondoSemestre, String qtaDaFatturare, String prezzoUnitario, String importo, String flagFatturabile, String tipoDocumento, String contropartita, String importoTransatoPrimoSemestre, String importoTransatoAnno, String dataAggiuntaRecord, String statoPrenotazione, String dataInizioValiditaRegole) {
        super();
        chiavePrenotazione = this.chiavePrenotazione;
        codiceEnte = this.codiceEnte;
        codiceFiscaleEnte = this.codiceFiscaleEnte;
        descrizioneEnte = this.descrizioneEnte;
        dataPrenotazione = this.dataPrenotazione;
        descrizioneCausale = this.descrizioneCausale;
        periodoFatturazione = this.periodoFatturazione;
        periodoEsportazione = this.periodoEsportazione;
        qtaMinimaFatturabile = this.qtaMinimaFatturabile;
        qtaMassimaFatturabile = this.qtaMassimaFatturabile;
        nTransazioniPrimoSemestre = this.nTransazioniPrimoSemestre;
        nTransazioniSecondoSemestre = this.nTransazioniSecondoSemestre;
        qtaDaFatturare = this.qtaDaFatturare;
        prezzoUnitario = this.prezzoUnitario;;
        importo = this.importo;
        flagFatturabile = this.flagFatturabile;
        tipoDocumento= this.tipoDocumento;
        contropartita= this.contropartita;
        importoTransatoPrimoSemestre = this.importoTransatoPrimoSemestre;
        importoTransatoAnno = this.importoTransatoAnno;
        dataAggiuntaRecord = this.dataAggiuntaRecord;
        statoPrenotazione = this.statoPrenotazione;
        dataInizioValiditaRegole = this.dataInizioValiditaRegole;
    }

    @Override
    public void onLoad(CallableStatement arg) throws SQLException {
        // TODO
    }

    @Override
    public void onSave(CallableStatement arg) throws SQLException {
        arg.setString(1, this.chiavePrenotazione);
        arg.setString(2, this.codiceEnte);
        arg.setString(3, this.codiceFiscaleEnte);
        arg.setString(4, this.descrizioneEnte);
        arg.setString(5, this.dataPrenotazione);
        arg.setString(6, this.descrizioneCausale);
        arg.setString(7, this.periodoFatturazione);
        arg.setString(8, this.periodoEsportazione);
        arg.setString(9, this.qtaMinimaFatturabile);
        arg.setString(10, this.qtaMassimaFatturabile);
        arg.setString(11, this.nTransazioniPrimoSemestre);
        arg.setString(12, this.nTransazioniSecondoSemestre);
        arg.setString(13, this.qtaDaFatturare);
        arg.setString(14, this.prezzoUnitario);
        arg.setString(15, this.importo);
        arg.setString(16, this.flagFatturabile);
        arg.setString(17, this.tipoDocumento);
        arg.setString(18, this.contropartita);
        arg.setString(19, this.importoTransatoPrimoSemestre);
        arg.setString(20, this.importoTransatoAnno);
        arg.setString(21, this.dataAggiuntaRecord);
        arg.setString(22, this.statoPrenotazione);
        arg.setString(23, this.dataAggiuntaRecord);
    }

    @Override
    public void onUpdate(CallableStatement arg) throws SQLException {
        // TODO
    }

    @Override
    public void onDelete(CallableStatement arg) throws SQLException {
        // TODO
    }

    public String getChiavePrenotazione() {
        return chiavePrenotazione;
    }

    public void setChiavePrenotazione(String chiavePrenotazione) {
        this.chiavePrenotazione = chiavePrenotazione;
    }

    public String getCodiceEnte() {
        return codiceEnte;
    }

    public void setCodiceEnte(String codiceEnte) {
        this.codiceEnte = codiceEnte;
    }

    public String getCodiceFiscaleEnte() {
        return codiceFiscaleEnte;
    }

    public void setCodiceFiscaleEnte(String codiceFiscaleEnte) {
        this.codiceFiscaleEnte = codiceFiscaleEnte;
    }

    public String getDescrizioneEnte() {
        return descrizioneEnte;
    }

    public void setDescrizioneEnte(String descrizioneEnte) {
        this.descrizioneEnte = descrizioneEnte;
    }

    public String getDataPrenotazione() {
        return dataPrenotazione;
    }

    public void setDataPrenotazione(String dataPrenotazione) {
        this.dataPrenotazione = dataPrenotazione;
    }

    public String getDescrizioneCausale() {
        return descrizioneCausale;
    }

    public void setDescrizioneCausale(String descrizioneCausale) {
        this.descrizioneCausale = descrizioneCausale;
    }

    public String getPeriodoFatturazione() {
        return periodoFatturazione;
    }

    public void setPeriodoFatturazione(String periodoFatturazione) {
        this.periodoFatturazione = periodoFatturazione;
    }

    public String getPeriodoEsportazione() {
        return periodoEsportazione;
    }

    public void setPeriodoEsportazione(String periodoEsportazione) {
        this.periodoEsportazione = periodoEsportazione;
    }

    public String getQtaMinimaFatturabile() {
        return qtaMinimaFatturabile;
    }

    public void setQtaMinimaFatturabile(String qtaMinimaFatturabile) {
        this.qtaMinimaFatturabile = qtaMinimaFatturabile;
    }

    public String getQtaMassimaFatturabile() {
        return qtaMassimaFatturabile;
    }

    public void setQtaMassimaFatturabile(String qtaMassimaFatturabile) {
        this.qtaMassimaFatturabile = qtaMassimaFatturabile;
    }

    public String getnTransazioniPrimoSemestre() {
        return nTransazioniPrimoSemestre;
    }

    public void setnTransazioniPrimoSemestre(String nTransazioniPrimoSemestre) {
        this.nTransazioniPrimoSemestre = nTransazioniPrimoSemestre;
    }

    public String getnTransazioniSecondoSemestre() {
        return nTransazioniSecondoSemestre;
    }

    public void setnTransazioniSecondoSemestre(String nTransazioniSecondoSemestre) {
        this.nTransazioniSecondoSemestre = nTransazioniSecondoSemestre;
    }

    public String getQtaDaFatturare() {
        return qtaDaFatturare;
    }

    public void setQtaDaFatturare(String qtaDaFatturare) {
        this.qtaDaFatturare = qtaDaFatturare;
    }

    public String getPrezzoUnitario() {
        return prezzoUnitario;
    }

    public void setPrezzoUnitario(String prezzoUnitario) {
        this.prezzoUnitario = prezzoUnitario;
    }

    public String getImporto() {
        return importo;
    }

    public void setImporto(String importo) {
        this.importo = importo;
    }

    public String getFlagFatturabile() {
        return flagFatturabile;
    }

    public void setFlagFatturabile(String flagFatturabile) {
        this.flagFatturabile = flagFatturabile;
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

    public String getImportoTransatoPrimoSemestre() {
        return importoTransatoPrimoSemestre;
    }

    public void setImportoTransatoPrimoSemestre(String importoTransatoPrimoSemestre) {
        this.importoTransatoPrimoSemestre = importoTransatoPrimoSemestre;
    }

    public String getImportoTransatoAnno() {
        return importoTransatoAnno;
    }

    public void setImportoTransatoAnno(String importoTransatoAnno) {
        this.importoTransatoAnno = importoTransatoAnno;
    }

    public String getDataAggiuntaRecord() {
        return dataAggiuntaRecord;
    }

    public void setDataAggiuntaRecord(String dataAggiuntaRecord) {
        this.dataAggiuntaRecord = dataAggiuntaRecord;
    }

    public String getStatoPrenotazione() {
        return statoPrenotazione;
    }

    public void setStatoPrenotazione(String statoPrenotazione) {
        this.statoPrenotazione = statoPrenotazione;
    }

    public String getDataInizioValiditaRegole() {
        return dataInizioValiditaRegole;
    }

    public void setDataInizioValiditaRegole(String dataInizioValiditaRegole) {
        this.dataInizioValiditaRegole = dataInizioValiditaRegole;
    }
}
