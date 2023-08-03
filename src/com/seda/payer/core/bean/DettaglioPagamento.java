/**
 * DettaglioPagamento.java
 */

package com.seda.payer.core.bean;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Indica i dati di dettaglio del pagamento
 */
public class DettaglioPagamento  implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
    private int numeroRata;
    
    private String numeroBollettinoPagoPA;

    /* Identificativo dominio, identifica il CF dell'ente */
    private String identificativoDominio;

    private BigDecimal importo;

    private String IBANBancario;

    private String IBANPostale;

    //inizio LP PG22XX05
    private String codiceTipologiaServizio;
    //fine LP PG22XX05
    
    //inizio GG PGNTCORE-6
    private String chiaveTari;
    private String valoreTari;
    //fine GG PGNTCORE-6

    public DettaglioPagamento() {
    }

    public DettaglioPagamento(
           String identificativoDominio,
           BigDecimal importo,
           String IBANBancario,
           String IBANPostale
           , String codiceTipologiaServizio
           , String chiaveTari
           , String valoreTari
           ) {
           this.identificativoDominio = identificativoDominio;
           this.importo = importo;
           this.IBANBancario = IBANBancario;
           this.IBANPostale = IBANPostale;
           this.codiceTipologiaServizio = codiceTipologiaServizio; //LP PG22XX05  
           this.chiaveTari = chiaveTari;	//PGNTCORE-6
           this.valoreTari = valoreTari; 	//PGNTCORE-6
    }

	public DettaglioPagamento(ResultSet data) throws SQLException {
		if (data == null)
			return;
		
		setIdentificativoDominio(data.getString(1));
		setImporto(data.getBigDecimal(2));
		setIBANBancario(data.getString(3));
		setIBANPostale(data.getString(4));
		//inizio LP PG22XX05 
		try {
			String appo = data.getString(5);
			setCodiceTipologiaServizio(appo != null ? appo : "");   
		} catch (Exception e) {
			setCodiceTipologiaServizio("");
		}
		//fine LP PG22XX05 
		//inizio GG PGNTCORE-6
		try {
			String appo = data.getString(6);
			setChiaveTari(appo != null ? appo : "");   
		} catch (Exception e) {
			setChiaveTari("");
		}
		try {
			String appo = data.getString(7);
			setValoreTari(appo != null ? appo : "");   
		} catch (Exception e) {
			setValoreTari("");
		}
		//fine GG PGNTCORE-6
	}

    public int getNumeroRata() {
		return numeroRata;
	}

	public void setNumeroRata(int numeroRata) {
		this.numeroRata = numeroRata;
	}

	public String getNumeroBollettinoPagoPA() {
		return numeroBollettinoPagoPA;
	}

	public void setNumeroBollettinoPagoPA(String numeroBollettinoPagoPA) {
		this.numeroBollettinoPagoPA = numeroBollettinoPagoPA;
	}

	/**
     * Gets the identificativoDominio value for this DettaglioPagamento.
     * 
     * @return identificativoDominio   * Identificativo dominio, identifica il CF dell'ente
     */
    public String getIdentificativoDominio() {
        return identificativoDominio;
    }


    /**
     * Sets the identificativoDominio value for this DettaglioPagamento.
     * 
     * @param identificativoDominio   * Identificativo dominio, identifica il CF dell'ente
     */
    public void setIdentificativoDominio(String identificativoDominio) {
        this.identificativoDominio = identificativoDominio;
    }


    /**
     * Gets the importo value for this DettaglioPagamento.
     * 
     * @return importo
     */
    public BigDecimal getImporto() {
        return importo;
    }


    /**
     * Sets the importo value for this DettaglioPagamento.
     * 
     * @param importo
     */
    public void setImporto(BigDecimal importo) {
        this.importo = importo;
    }


    /**
     * Gets the IBANBancario value for this DettaglioPagamento.
     * 
     * @return IBANBancario
     */
    public String getIBANBancario() {
        return IBANBancario;
    }


    /**
     * Sets the IBANBancario value for this DettaglioPagamento.
     * 
     * @param IBANBancario
     */
    public void setIBANBancario(String IBANBancario) {
        this.IBANBancario = IBANBancario;
    }


    /**
     * Gets the IBANPostale value for this DettaglioPagamento.
     * 
     * @return IBANPostale
     */
    public String getIBANPostale() {
        return IBANPostale;
    }


    /**
     * Sets the IBANPostale value for this DettaglioPagamento.
     * 
     * @param IBANPostale
     */
    public void setIBANPostale(String IBANPostale) {
        this.IBANPostale = IBANPostale;
    }

    //inizio LP PG22XX05
    public String getCodiceTipologiaServizio() {
		return codiceTipologiaServizio;
	}

	public void setCodiceTipologiaServizio(String codiceTipologiaServizio) {
		this.codiceTipologiaServizio = codiceTipologiaServizio;
	}
    //fine LP PG22XX05
	
	//inizio GG PGNTCORE-6
	public String getChiaveTari() {
		return chiaveTari;
	}

	public void setChiaveTari(String chiaveTari) {
		this.chiaveTari = chiaveTari;
	}

	public String getValoreTari() {
		return valoreTari;
	}

	public void setValoreTari(String valoreTari) {
		this.valoreTari = valoreTari;
	}
	//fine GG PGNTCORE-6
	
	public String toString() {
    	return "DettaglioPagamento ["
    			+ "identificativoDominio="  + identificativoDominio
    		    + "importo="  + importo
    			+ "IBANBancario="  + IBANBancario
    			+ "IBANPostale="  + IBANPostale
    			+ "codiceTipologiaServizio=" + codiceTipologiaServizio //LP PG22XX05 
    			+ "chiaveTari=" + chiaveTari //PGNTCORE-6
    			+ "valoreTari=" + valoreTari //PGNTCORE-6
    			+ "]";    	
    }
    
}
