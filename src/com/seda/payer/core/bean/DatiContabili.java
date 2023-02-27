package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class DatiContabili implements Serializable {
	private static final long serialVersionUID = 1L;

	    private String codiceContabilita;

	    private int importo;

	    private String capitolo;

	    private String articolo;

	    private String annoCompetenza;
	    
	    
	    public DatiContabili() {
	    }

	    public DatiContabili(
	    		String codiceContabilita,
	    		BigDecimal importo,
	    		String capitolo,
	    		String articolo,
	    		String annoCompetenza) {
				this.codiceContabilita = codiceContabilita;
				this.importo = convertImportoToInt(importo);
				this.capitolo = capitolo;
				this.articolo = articolo;
				this.annoCompetenza = annoCompetenza;
	    }

		public String getCodiceContabilita() {
			return codiceContabilita;
		}

		public int getImporto() {
			return importo;
		}

		public String getCapitolo() {
			return capitolo;
		}

		public String getArticolo() {
			return articolo;
		}

		public String getAnnoCompetenza() {
			return annoCompetenza;
		}

		public void setCodiceContabilita(String codiceContabilita) {
			this.codiceContabilita = codiceContabilita;
		}

		public void setImporto(int importo) {
			this.importo = importo;
		}

		public void setCapitolo(String capitolo) {
			this.capitolo = capitolo;
		}

		public void setArticolo(String articolo) {
			this.articolo = articolo;
		}

		public void setAnnoCompetenza(String annoCompetenza) {
			this.annoCompetenza = annoCompetenza;
		}
		
		public int convertImportoToInt(BigDecimal importoBollettino){
			int importo = 0;
			try {
				//Tolgo la , o il . dall'importo
				String importoString = importoBollettino.toString();
				if(importoString.contains(",")) {
					String importoIntero = importoString.replace(",", "");
					importo = Integer.parseInt(importoIntero);
				}else if (importoString.contains(".")) {
					String importoIntero = importoString.replace(".", "");
					importo = Integer.parseInt(importoIntero);
				}else {
					throw new Exception("L'importo del bollettino non è nel formato corretto");
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return importo;
		}
}
