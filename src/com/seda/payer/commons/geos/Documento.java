package com.seda.payer.commons.geos;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;

public class Documento
{
	public String ImpostaServizio;
	public String NumeroDocumento;
	public String CausaleDocumento;
	public String ImportoDocumento;
	public String CartellaRuoli;
	public String BarcodeDocumento;
	public String QRcodeDocumento;
	@XmlTransient
	public String Iban;
	
	public ArrayList<DatiAnagrafici> DatiAnagrafici = new ArrayList<DatiAnagrafici>();
	public ArrayList<DatiCreditore> DatiCreditore = new ArrayList<DatiCreditore>();
	@XmlElementWrapper(name="ElencoTributi")
	@XmlElement(name="Tributo")
	public ArrayList<Tributo> ElencoTributi = new ArrayList<Tributo>();
	@XmlElementWrapper(name="ElencoBollettini")
	@XmlElement(name="Bollettino")
	public ArrayList<Bollettino> DatiBollettino = new ArrayList<Bollettino>();
	
	public Documento () {}
	
	public Documento(String impostaServizio, String importoInCent, String causaleDocumento)
	{
		ImpostaServizio = (impostaServizio != null ? impostaServizio.trim() : "");
		ImportoDocumento = (importoInCent != null ? importoInCent.trim() : "000");
		CausaleDocumento = (causaleDocumento != null ? causaleDocumento.trim() : "");
		CartellaRuoli = "";
		QRcodeDocumento = "";
		BarcodeDocumento = "";
		NumeroDocumento = "";
	}

	public void addDatiAnagrafici(DatiAnagrafici curAna) {
		DatiAnagrafici.add(curAna);
	}

	public void addDatiCreditore(DatiCreditore curCre) {
		DatiCreditore.add(curCre);
	}

	public void addDatiBollettino(Bollettino curBol) {
		DatiBollettino.add(curBol);
	}

	public void addElencoTributi(Tributo t)
	{
		for (int i = 0; i < ElencoTributi.size(); i++) {
			Tributo el = ElencoTributi.get(i);
			if (el.AnnoTributo.equals(t.AnnoTributo) && el.CodiceTributo.equals(t.CodiceTributo)) {
				// se trovato lo sovrascrivo
				ElencoTributi.set(i, t);
				return;
			}
		}
		ElencoTributi.add(t);
	}
}