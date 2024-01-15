package com.seda.payer.commons.geos;

public class Tributo
{
	public String CodiceTributo;
	public String AnnoTributo;
	public String DenomTributo;
	public String ImportoTributo;
	
	public Tributo() {}
	
	public Tributo(String anno, String codice, String importoInCent, String note) {
		AnnoTributo = anno;
		CodiceTributo = codice;
		//ImportoTributo = GeosUtil.leftZeroPad(importoInCent, GeosUtil.lenEuro, GeosUtil.lenEuro);
		ImportoTributo = importoInCent;
		DenomTributo = note;
	}
}