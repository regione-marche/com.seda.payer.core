package com.seda.payer.commons.inviaAvvisiForGeos;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AvvisoRata {
	/** Data Scadenza: GGMMAAAA */
	public String dataScadenza;

	/** Importo da Pagare: stringa, importo in centesimi, positivo, con 15 cifre 0-padded. */
	public String importo;
	
	/** Progressivo Bollettino */
	public int progressivoBollettino;
	
	/** Numero Avviso PagoPa rata */
	public String numeroAvviso;
	
	/** IUV rata; */
	public String codiceIUV;
	
	/** Stringa per QRcode */
	public String codiceQRcode;
	
	/** Stringa per Barcode (come da documentazione PagoPA allegata) */
	public String codiceBarcode;

	
	/** documento a cui si riferisce la presente rata/avviso*/
	@JsonIgnore
	public Documento documento;	
}
