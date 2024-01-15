package com.seda.payer.commons.geos;

public class DatiCreditore
{
	public String Cf;
	public String Denominazione1;
	public String Denominazione2;
	public String Denominazione3;
	public String Indirizzo;
	public String Cap;
	public String Citta;
	public String Provincia;
	public String CodiceInterbancario;
	
	public String LogoEnte;
		
	public DatiCreditore() {}

	public DatiCreditore(String codFiscEnte, String descEnte)
	{
		Cf =(codFiscEnte != null ? codFiscEnte.trim() : "");
		Denominazione1 = (descEnte != null ? descEnte.trim() : ""); 
		Denominazione2 = "";
		Indirizzo =  "";
		Cap = "";
		Citta = "";
		Provincia = "";
		CodiceInterbancario = "00000";
	}

	public DatiCreditore(String codFiscEnte, String descEnte, String cBill)
	{
		Cf = (codFiscEnte != null ? codFiscEnte.trim() : "");
		Denominazione1 = (descEnte != null ? descEnte.trim() : ""); 
		if(Denominazione1.contains("/"))
			Denominazione1 = Denominazione1.split("/")[0].trim();
		Denominazione2 = "";
		Indirizzo =  "";
		Cap = "";
		Citta = "";
		Provincia = "";
		CodiceInterbancario = (cBill != null ? cBill.trim() : "00000");
	}

	public DatiCreditore(String codFiscEnte, String descEnte, String ufficioEnte, String cBill)
	{
		Cf = (codFiscEnte != null ? codFiscEnte.trim() : "");
		Denominazione1 = (descEnte != null ? descEnte.trim() : "");
		Denominazione2 = (ufficioEnte != null ? ufficioEnte.trim() : "");
		Indirizzo =  "";
		Cap = "";
		Citta = "";
		Provincia = "";
		CodiceInterbancario = (cBill != null ? cBill.trim() : "00000");
	}

	public DatiCreditore(String codFiscEnte, String descEnte, String ufficioEnte, String indirizzo, String cap, String citta, String siglaProv, String cBill)
	{
		Cf = (codFiscEnte != null ? codFiscEnte.trim() : "");
		Denominazione1 = (descEnte != null ? descEnte.trim() : ""); 
		Denominazione2 = (ufficioEnte != null ? ufficioEnte.trim() : "");
		Indirizzo =  (indirizzo != null ? indirizzo.trim() : "");
		Cap = (cap != null ? cap.trim() : "");
		Citta = (citta != null ? citta.trim() : "");
		Provincia = (siglaProv != null ? siglaProv.trim() : "");
		CodiceInterbancario = (cBill != null ? cBill.trim() : "00000");
	}

}