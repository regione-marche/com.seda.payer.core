package com.seda.payer.commons.inviaAvvisiForGeos;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Ogni {@link Documento} giustifica l'importo totale come la somma di uno o più importi, 
 * ciascuno relativo al singolo tributo.
 **/

public class Tributo {
  public int anno;
  public String codiceTributo;
  public String importo;
  public String note;

  @JsonIgnore
  public Documento documento;
}
