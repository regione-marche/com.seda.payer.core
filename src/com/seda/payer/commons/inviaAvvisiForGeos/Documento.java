package com.seda.payer.commons.inviaAvvisiForGeos;

import java.util.ArrayList;

public class Documento {
  /** Codice Ente; */
  public String codiceEnte;
  /** Descrizione Ente; */
  public String descrizioneEnte;
  /** Anno Documento; */
  public int anno;
  /** Numero Documento 19 caratteri (NB: non deve essere troncato, non devo aggiungere ZERI a sinistra) */
  public String numero;
  /** Tipologia Servizio; */
  public String tipologiaServizio;
  /** Descrizione Servizio; */
  public String descrizioneServizio;
  /** Importo bollettino totale documento; positivo 15 cifre 0-padded, in centesimi */
  public String importoTotale;
  /** flag fatturazione elettronica */
  public boolean fatturazioneElettronica;
  /** IBAN; */
  public String iban;
  /** Numero Avviso PagoPa; */
  public String numAvvisoPagoPa;
  /** IUV documento; */
  public String codiceIUV;
  /** Stringa per QRcode (come da documentazione PagoPA allegata) */
  public String codiceQRcode;
  /** Stringa per Barcode (come da documentazione PagoPA allegata) */
  public String codiceBarcode;
  public String chiaveEnte;
  public String causale;
  public Debitore debitore;
  
  public String oggettoPagamento;
  
  /** Codice Imposta Servizio; */
  public String codiceImpostaServizio;	//PG22XX01_GG1

  /** lista di tributi/importi che compongono questo documento */
  public ArrayList<Tributo> listaTributi = new ArrayList<Tributo>();
  
  public String flagMultiBeneficiario;	//PAGONET-541

  /** Aggiungo alla lista tributi, se già presente sovrascrivo */
  public void putTributo(Tributo t) {
    t.documento = this;
    // già presente?
    for (int i = 0; i < listaTributi.size(); i++) {
      Tributo el = listaTributi.get(i);
      if (el.codiceTributo.equals(t.codiceTributo)) {
        // trovato...sovrascrivo
        listaTributi.set(i, t);
        return;
      }
    }
    listaTributi.add(t);
  }

  /** lista di avvisi/rate per questo documento */
  public ArrayList<AvvisoRata> listaAvvisi = new ArrayList<AvvisoRata>();

  public void addAvviso(AvvisoRata curAvv) {
    listaAvvisi.add(curAvv);
    curAvv.documento = this;
  }
}
