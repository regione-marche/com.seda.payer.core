package com.seda.payer.commons.geos;

public class DatiAnagrafici
{
	public String Cf;
	public String Denominazione1;
	public String Denominazione2 = "";
	public String Indirizzo;
	public String Cap;
	public String Citta;
	public String Provincia;

	public DatiAnagrafici() {}

	public DatiAnagrafici(String denominazione, String codFis, String indirizzo, String cap, String citta, String siglaProv)
	{
		Denominazione1 = (denominazione != null ? denominazione.trim() : "");
		Cf = (codFis != null ? codFis.trim() : "");
		Indirizzo = (indirizzo != null ? indirizzo.trim() : "");
		Cap = (cap != null ? cap.trim() : "");
		Citta = (citta != null ? citta.trim() : "");
		Provincia = (siglaProv != null ? siglaProv.trim() : "");
	}
}