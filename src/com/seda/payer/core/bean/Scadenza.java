/**
 * Documento.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.seda.payer.core.bean;

public class Scadenza  implements java.io.Serializable {

    private int numeroRata;
    private java.lang.String dataScadenzaRata;
    private java.lang.String numeroBollettinoPagoPA;
    private java.math.BigDecimal impBollettinoRata;
    private java.lang.String identificativoUnivocoVersamento;

    public Scadenza() {
    }

    public int getNumeroRata() {
      return numeroRata;
    }

    public void setNumeroRata(int numeroRata) {
      this.numeroRata = numeroRata;
    }

    public java.lang.String getDataScadenzaRata() {
      return dataScadenzaRata;
    }

    public void setDataScadenzaRata(java.lang.String dataScadenzaRata) {
      this.dataScadenzaRata = dataScadenzaRata;
    }

    public java.lang.String getNumeroBollettinoPagoPA() {
      return numeroBollettinoPagoPA;
    }

    public void setNumeroBollettinoPagoPA(java.lang.String numeroBollettinoPagoPA) {
      this.numeroBollettinoPagoPA = numeroBollettinoPagoPA;
    }

    public java.math.BigDecimal getImpBollettinoRata() {
      return impBollettinoRata;
    }

    public void setImpBollettinoRata(java.math.BigDecimal impBollettinoRata) {
      this.impBollettinoRata = impBollettinoRata;
    }

    public java.lang.String getIdentificativoUnivocoVersamento() {
      return identificativoUnivocoVersamento;
    }

    public void setIdentificativoUnivocoVersamento(java.lang.String identificativoUnivocoVersamento) {
      this.identificativoUnivocoVersamento = identificativoUnivocoVersamento;
    }


}
