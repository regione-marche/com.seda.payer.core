package com.seda.payer.commons.geos;

public class Bollettino
{
	private static String Versione = "002";
	private static int UltimoBollettino = 999;

	public String tipoBoll = "";
	public int ProgressivoBoll;
	public String ScadenzaRata;
	public String Codeline1Boll;
	public String Codeline2Boll;
	public String Codeline4Boll = "";
	public String Codeline12Boll;
	public String Descon60Boll;
	public String BarcodePagoPa;
	public String QRcodePagoPa;
	public String AvvisoPagoPa;
	public String IUVPagoPa;
	public String AutorizCcp;
	public String scadenzaBollettino;
		
	public Bollettino()	{}

	public Bollettino(String tipoboll, String intestazioneCCP, String numeroCCP, String numeroIUV, int numBollettino, String numeroAvvisoPagoPa, String dataScadenzaIta, String importoInCent, String autorizzazioneCCP, String barcodePagoPa, String qrCcodePagoPa)
	{
		String dataScadenza = dataScadenzaIta.replace("/","");  

		tipoBoll = (tipoboll != null ? tipoboll.trim() : "");
		ProgressivoBoll = numBollettino;
		ScadenzaRata = (dataScadenza != null ? dataScadenza.trim() : "");
		Codeline1Boll = (numeroAvvisoPagoPa != null ? numeroAvvisoPagoPa.trim() : "");
		Codeline2Boll = (importoInCent != null ? importoInCent.trim() : "000");
		Codeline12Boll = (numeroCCP != null ? numeroCCP.trim() : "");
		Descon60Boll = (intestazioneCCP != null ? intestazioneCCP.trim() : "");
		//String importoCentesimi = GeosUtil.leftZeroPad(importoInCent, GeosUtil.lenEuro, GeosUtil.lenEuro);
		//BarcodePagoPa = String.format("(415)%s(8020)%s(3902)%s", GeosUtil.getBarcodeParameter1(cuteCute, codFiscEnte, numeroBollettino), numeroBollettino, importoCentesimi);
		BarcodePagoPa = (barcodePagoPa != null ? barcodePagoPa.trim() : "");
		//PAGOPA|002|301185850000001130|80002270074|0000069496
		//QRcodePagoPa = "PAGOPA|" + Versione + "|" + numeroBollettino + "|" + codFiscEnte + "|" + importoCentesimi;
		QRcodePagoPa = (qrCcodePagoPa != null ? qrCcodePagoPa.trim() : ""); 
		AvvisoPagoPa = (numeroAvvisoPagoPa != null ? numeroAvvisoPagoPa.trim() : "");
		IUVPagoPa = (numeroIUV != null ? numeroIUV.trim() : "");
		AutorizCcp = (autorizzazioneCCP != null ? autorizzazioneCCP.trim() : "");
	}
	
	public Bollettino(String tipoboll, String intestazioneCCP, String numeroCCP, String numeroIUV, String numeroAvvisoPagoPa, String dataScadenzaIta, String importoInCent, String autorizzazioneCCP, String barcodePagoPa, String qrCcodePagoPa)
	{
		String dataScadenza = dataScadenzaIta.replace("/","");  

		tipoBoll = (tipoboll != null ? tipoboll.trim() : "");
		ProgressivoBoll = UltimoBollettino;
		ScadenzaRata = (dataScadenza != null ? dataScadenza.trim() : "");
		Codeline1Boll = (numeroAvvisoPagoPa != null ? numeroAvvisoPagoPa.trim() : "");
		Codeline2Boll = (importoInCent != null ? importoInCent.trim() : "000");
		Codeline12Boll = (numeroCCP != null ? numeroCCP.trim() : "");
		Descon60Boll = (intestazioneCCP != null ? intestazioneCCP.trim() : "");
		//String importoCentesimi = GeosUtil.leftZeroPad(importoInCent, GeosUtil.lenEuro, GeosUtil.lenEuro);
		//BarcodePagoPa = String.format("(415)%s(8020)%s(3902)%s", GeosUtil.getBarcodeParameter1(cuteCute, codFiscEnte, numeroBollettino), numeroBollettino, importoCentesimi);
		BarcodePagoPa = (barcodePagoPa != null ? barcodePagoPa.trim() : "");
		//PAGOPA|002|301185850000001130|80002270074|0000069496
		//QRcodePagoPa = "PAGOPA|" + Versione + "|" + numeroBollettino + "|" + codFiscEnte + "|" + importoCentesimi;
		QRcodePagoPa = (qrCcodePagoPa != null ? qrCcodePagoPa.trim() : ""); 
		AvvisoPagoPa = (numeroAvvisoPagoPa != null ? numeroAvvisoPagoPa.trim() : "");
		IUVPagoPa = (numeroIUV != null ? numeroIUV.trim() : "");
		AutorizCcp = (autorizzazioneCCP != null ? autorizzazioneCCP.trim() : "");
	}
	
	public Bollettino(String tipoboll, String intestazioneCCP, String numeroCCP, String numeroIUV, int numBollettino, String numeroAvvisoPagoPa, String dataScadenzaIta, String importoInCent, String autorizzazioneCCP, String barcodePagoPa, String qrCcodePagoPa, String codeline4Boll)
	{
		String dataScadenza = dataScadenzaIta.replace("/","");  

		tipoBoll = (tipoboll != null ? tipoboll.trim() : "");
		ProgressivoBoll = numBollettino;
		ScadenzaRata = (dataScadenza != null ? dataScadenza.trim() : "");
		Codeline1Boll = (numeroAvvisoPagoPa != null ? numeroAvvisoPagoPa.trim() : "");
		Codeline2Boll = (importoInCent != null ? importoInCent.trim() : "000");
		Codeline4Boll = (codeline4Boll != null ? codeline4Boll.trim() : "");	//TODO da verificare se serve
		Codeline12Boll = (numeroCCP != null ? numeroCCP.trim() : "");
		Descon60Boll = (intestazioneCCP != null ? intestazioneCCP.trim() : "");
		if(Descon60Boll.contains("/"))
			Descon60Boll = Descon60Boll.split("/")[0].trim();
		//String importoCentesimi = GeosUtil.leftZeroPad(importoInCent, GeosUtil.lenEuro, GeosUtil.lenEuro);
		//BarcodePagoPa = String.format("(415)%s(8020)%s(3902)%s", GeosUtil.getBarcodeParameter1(cuteCute, codFiscEnte, numeroBollettino), numeroBollettino, importoCentesimi);
		BarcodePagoPa = (barcodePagoPa != null ? barcodePagoPa.trim() : "");
		//PAGOPA|002|301185850000001130|80002270074|0000069496
		//QRcodePagoPa = "PAGOPA|" + Versione + "|" + numeroBollettino + "|" + codFiscEnte + "|" + importoCentesimi;
		QRcodePagoPa = (qrCcodePagoPa != null ? qrCcodePagoPa.trim() : ""); 
		AvvisoPagoPa = (numeroAvvisoPagoPa != null ? numeroAvvisoPagoPa.trim() : "");
		IUVPagoPa = (numeroIUV != null ? numeroIUV.trim() : "");
		AutorizCcp = (autorizzazioneCCP != null ? autorizzazioneCCP.trim() : "");
	}
	
	public Bollettino(String tipoboll, String intestazioneCCP, String numeroCCP, String numeroIUV, String numeroAvvisoPagoPa, String dataScadenzaIta, String importoInCent, String autorizzazioneCCP, String barcodePagoPa, String qrCcodePagoPa, String codeline4Boll)
	{
		String dataScadenza = dataScadenzaIta.replace("/","");  

		tipoBoll = (tipoboll != null ? tipoboll.trim() : "");
		ProgressivoBoll = UltimoBollettino;
		ScadenzaRata = (dataScadenza != null ? dataScadenza.trim() : "");
		Codeline1Boll = (numeroAvvisoPagoPa != null ? numeroAvvisoPagoPa.trim() : "");
		Codeline2Boll = (importoInCent != null ? importoInCent.trim() : "000");
		Codeline4Boll = (codeline4Boll != null ? codeline4Boll.trim() : "");	//TODO da verificare se serve
		Codeline12Boll = (numeroCCP != null ? numeroCCP.trim() : "");
		Descon60Boll = (intestazioneCCP != null ? intestazioneCCP.trim() : "");
		//String importoCentesimi = GeosUtil.leftZeroPad(importoInCent, GeosUtil.lenEuro, GeosUtil.lenEuro);
		//BarcodePagoPa = String.format("(415)%s(8020)%s(3902)%s", GeosUtil.getBarcodeParameter1(cuteCute, codFiscEnte, numeroBollettino), numeroBollettino, importoCentesimi);
		BarcodePagoPa = (barcodePagoPa != null ? barcodePagoPa.trim() : "");
		//PAGOPA|002|301185850000001130|80002270074|0000069496
		//QRcodePagoPa = "PAGOPA|" + Versione + "|" + numeroBollettino + "|" + codFiscEnte + "|" + importoCentesimi;
		QRcodePagoPa = (qrCcodePagoPa != null ? qrCcodePagoPa.trim() : ""); 
		AvvisoPagoPa = (numeroAvvisoPagoPa != null ? numeroAvvisoPagoPa.trim() : "");
		IUVPagoPa = (numeroIUV != null ? numeroIUV.trim() : "");
		AutorizCcp = (autorizzazioneCCP != null ? autorizzazioneCCP.trim() : "");
	}
}