/**
 * DettaglioContabile.java
 */

package com.seda.payer.core.bean;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Indica i dettagli contabili del pagamento
 */
public class DettaglioContabile  implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

    private int numeroRata;
    
    private String numeroBollettinoPagoPA;
    
    private String codiceContabilita;

    private BigDecimal importo;

    private String capitolo;

    private String articolo;

    private String annoCompetenza;
    
    private String idDominio;

    public DettaglioContabile() {
    }

    public DettaglioContabile(
    		String codiceContabilita,
    		BigDecimal importo,
    		String capitolo,
    		String articolo,
    		String annoCompetenza,
    		String idDominio) {
			this.codiceContabilita = codiceContabilita;
			this.importo = importo;
			this.capitolo = capitolo;
			this.articolo = articolo;
			this.annoCompetenza = annoCompetenza;
			this.idDominio = idDominio;
    }
    
    public DettaglioContabile(ResultSet data) throws SQLException {
		if (data == null)
			return;
		
		setCodiceContabilita(data.getString(1));
		setImporto(data.getBigDecimal(2));
		setCapitolo(data.getString(3));
		setArticolo(data.getString(4));
		setAnnoCompetenza(data.getString(5));
		setIdDominio(data.getString(6));
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
     * Gets the importo value for this DettaglioContabile.
     * 
     * @return importo
     */
    public BigDecimal getImporto() {
        return importo;
    }


    /**
     * Sets the importo value for this DettaglioContabile.
     * 
     * @param importo
     */
    public void setImporto(BigDecimal importo) {
        this.importo = importo;
    }


    /**
     * Gets the codiceContabilita value for this DettaglioContabile.
     * 
     * @return codiceContabilita
     */
    public String getCodiceContabilita() {
        return codiceContabilita;
    }


    /**
     * Sets the codiceContabilita value for this DettaglioContabile.
     * 
     * @param codiceContabilita
     */
    public void setCodiceContabilita(String codiceContabilita) {
        this.codiceContabilita = codiceContabilita;
    }


    /**
     * Gets the capitolo value for this DettaglioContabile.
     * 
     * @return capitolo
     */
    public String getCapitolo() {
        return capitolo;
    }


    /**
     * Sets the capitolo value for this DettaglioContabile.
     * 
     * @param capitolo
     */
    public void setCapitolo(String capitolo) {
        this.capitolo = capitolo;
    }


    /**
     * Gets the articolo value for this DettaglioContabile.
     * 
     * @return articolo
     */
    public String getArticolo() {
        return articolo;
    }


    /**
     * Sets the articolo value for this DettaglioContabile.
     * 
     * @param articolo
     */
    public void setArticolo(String articolo) {
        this.articolo = articolo;
    }


    /**
     * Gets the annoCompetenza value for this DettaglioContabile.
     * 
     * @return annoCompetenza
     */
    public String getAnnoCompetenza() {
        return annoCompetenza;
    }


    /**
     * Sets the annoCompetenza value for this DettaglioContabile.
     * 
     * @param annoCompetenza
     */
    public void setAnnoCompetenza(String annoCompetenza) {
        this.annoCompetenza = annoCompetenza;
    }

    public String getIdDominio() {
		return idDominio;
	}

	public void setIdDominio(String idDominio) {
		this.idDominio = idDominio;
	}

	public String toString() {
    	return "DettaglioContabile ["
    		    + "importo="  + importo
    			+ "codiceContabilita="  + codiceContabilita
    			+ "capitolo="  + capitolo
    			+ "articolo="  + articolo
    			+ "annoCompetenza="  + annoCompetenza
    			+ "idDominio="  + idDominio
    			+ "]";    	
    }
}
