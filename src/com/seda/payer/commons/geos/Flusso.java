package com.seda.payer.commons.geos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement(name="PrintRequest")
public class Flusso
{
	public String CodiceUtente;
	public String TipoStampa; //"B" = banca, "P" = poste
	public Boolean tedesco = false;
	public String Provenienza = "PPO";
	public String CodiceEnte;
	public String CuteCute;
	public String DataFornitura;
	public String ProgZero = "";
	public String VersioneZero= "";
	public ArrayList<Documento> Documentdata = new ArrayList<Documento>();
	@XmlTransient
	public String idFlusso = "";
	
	public Flusso()	{}
	
	public void setTedesco(Boolean x) {
		this.tedesco = x;
	}
	
	public Flusso(String cuteCute, String ente)
	{
		CodiceUtente = cuteCute;
		TipoStampa = "B";
		Provenienza = "PPO";
		CodiceEnte = ente;
		CuteCute = cuteCute;
		SimpleDateFormat data = new SimpleDateFormat("ddMMyyyy");
		DataFornitura = data.format(new Date(System.currentTimeMillis()));
	}
	
	public Flusso(String cuteCute, String ente, String tipoStampa)
	{
		CodiceUtente = cuteCute;
		TipoStampa = tipoStampa;
		Provenienza = "PPO";
		CodiceEnte = ente;
		CuteCute = cuteCute;
		SimpleDateFormat data = new SimpleDateFormat("ddMMyyyy");
		DataFornitura = data.format(new Date(System.currentTimeMillis()));
	}
	
	public Flusso(String cuteCute, String ente, String tipoStampa, String idFlu)
	{
		CodiceUtente = cuteCute;
		TipoStampa = tipoStampa;
		Provenienza = "PPO";
		CodiceEnte = ente;
		CuteCute = cuteCute;
		SimpleDateFormat data = new SimpleDateFormat("ddMMyyyy");
		DataFornitura = data.format(new Date(System.currentTimeMillis()));
		idFlusso = idFlu;
	}
	
	public Flusso(String cuteCute, String ente, String tipoStampa, String idFlu, String provenienza)
	{
		CodiceUtente = cuteCute;
		TipoStampa = tipoStampa;
		Provenienza = provenienza;
		CodiceEnte = ente;
		CuteCute = cuteCute;
		SimpleDateFormat data = new SimpleDateFormat("ddMMyyyy");
		DataFornitura = data.format(new Date(System.currentTimeMillis()));
		idFlusso = idFlu;
	}
	
	public void addDocumento(Documento curDoc) {
		Documentdata.add(curDoc);
	}
}