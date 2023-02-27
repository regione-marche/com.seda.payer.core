package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Item implements Serializable {

	private static final long serialVersionUID = 8912506504094984829L;
	private List<ItemAttribute> attributeList = new ArrayList<ItemAttribute>(); ;
	private String idItem;
	private String idCarrello;
	private String codiceSocieta;
    private String codiceUtente;
    private String chiaveEnte;
    private String codiceTipologiaServizio;
    private String codiceImpostaServizio;
    private String tipoBollettino;
    private String descrizione;
    private BigDecimal prezzo;
    private Integer quantita;

    public Item() {
    	
    }
    
    public Item(ResultSet data) throws SQLException {
    	if (data == null) {
    		return;
    	}
    	idItem = data.getString("ITE_KITEKITE");
    	idCarrello = data.getString("ITE_KCAVKCAV");
    	codiceSocieta = data.getString("ITE_CSOCCSOC");
    	codiceUtente = data.getString("ITE_CUTECUTE");
    	chiaveEnte = data.getString("ITE_KANEKENT");
    	codiceTipologiaServizio = data.getString("ITE_CTSECTSE");
    	codiceImpostaServizio = data.getString("ITE_CISECISE");
    	tipoBollettino = data.getString("ITE_TBOLTBOL");
    	descrizione = data.getString("ITE_DITEDESC");
    	prezzo = data.getBigDecimal("ITE_IITEPREZ");
    	quantita = data.getInt("ITE_IITEQUAN");
    }
    
	
    public String getIdItem() {
		return idItem;
	}


	public void setIdItem(String idItem) {
		this.idItem = idItem;
	}


	public String getIdCarrello() {
		return idCarrello;
	}


	public void setIdCarrello(String idCarrello) {
		this.idCarrello = idCarrello;
	}


	public String getCodiceSocieta() {
        return codiceSocieta;
    }

    public void setCodiceSocieta(String codiceSocieta) {
        this.codiceSocieta = codiceSocieta;
    }

    public String getCodiceUtente() {
        return codiceUtente;
    }

    public void setCodiceUtente(String codiceUtente) {
        this.codiceUtente = codiceUtente;
    }

    public String getChiaveEnte() {
        return chiaveEnte;
    }

    public void setChiaveEnte(String chiaveEnte) {
        this.chiaveEnte = chiaveEnte;
    }

    public String getCodiceTipologiaServizio() {
        return codiceTipologiaServizio;
    }

    public void setCodiceTipologiaServizio(String codiceTipologiaServizio) {
        this.codiceTipologiaServizio = codiceTipologiaServizio;
    }

    public String getCodiceImpostaServizio() {
        return codiceImpostaServizio;
    }

    public void setCodiceImpostaServizio(String codiceImpostaServizio) {
        this.codiceImpostaServizio = codiceImpostaServizio;
    }

    public String getTipoBollettino() {
        return tipoBollettino;
    }

    public void setTipoBollettino(String tipoBollettino) {
        this.tipoBollettino = tipoBollettino;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	public List<ItemAttribute> getAttributeList() {
        return Collections.unmodifiableList(attributeList);
    }

    public void setAttributeList(List<ItemAttribute> attributeList) {
        this.attributeList = attributeList;
    }

    public String getAttributesAsBuffer() {
    	StringBuffer buffer = new StringBuffer();
    	for (ItemAttribute itemAttribute : attributeList) {
			String key = itemAttribute.getKey();
			String value = itemAttribute.getValue();
			buffer.append(leftPad(""+key.length(), '0', 5))
				.append(leftPad(""+value.length(), '0', 5))
				.append(key).append(value);
		}
    	return buffer.toString();
    }
    
    private String leftPad(String in, char pad, int len) {
    	StringBuffer buffer = new StringBuffer();
    	while (buffer.length() < len-in.length()) {
    		buffer.append(pad);
    	}
    	buffer.append(in);
    	return buffer.toString();
    }
    
    public void addAttribute(ItemAttribute itemAttribute) {
        attributeList.add(itemAttribute);
    }

	public void onDelete(CallableStatement arg) throws SQLException {
		int i = 1;
		arg.setString(i++, getIdItem());
		arg.setString(i++, getIdCarrello());
	}

	public void onLoad(CallableStatement arg0) throws SQLException {

	}

	public void onSave(CallableStatement arg) throws SQLException {
		int i = 1;
		System.out.println("getIdCarrello() = " + getIdCarrello());
		System.out.println("getCodiceSocieta() = " + getCodiceSocieta());
		System.out.println("getCodiceUtente() = " + getCodiceUtente());
		System.out.println("getChiaveEnte() = " + getChiaveEnte());
		System.out.println("getCodiceTipologiaServizio() = " + getCodiceTipologiaServizio());
		System.out.println("getCodiceImpostaServizio() = " + getCodiceImpostaServizio());
		System.out.println("getTipoBollettino() = " + getTipoBollettino());
		System.out.println("getDescrizione() = " + getDescrizione());
		System.out.println("getPrezzo() = " + getPrezzo());
		System.out.println("getQuantita() = " + getQuantita());
		System.out.println("getAttributesAsBuffer() = " + getAttributesAsBuffer());
		arg.setString(i++, getIdCarrello());
		arg.setString(i++,getCodiceSocieta());
		arg.setString(i++,getCodiceUtente());
		arg.setString(i++,getChiaveEnte());
		arg.setString(i++,getCodiceTipologiaServizio());
		arg.setString(i++,getCodiceImpostaServizio());
		arg.setString(i++,getTipoBollettino());
		arg.setString(i++,getDescrizione());
		arg.setBigDecimal(i++, getPrezzo());
		arg.setInt(i++, getQuantita());
		arg.setString(i++, getAttributesAsBuffer());
		

		System.out.println("getIdCarrello: "+ getIdCarrello());
		System.out.println("getCodiceSocieta: "+ getCodiceSocieta());
		System.out.println("getCodiceUtente: "+ getCodiceUtente());
		System.out.println("getChiaveEnte: "+ getChiaveEnte());
		System.out.println("getCodiceTipologiaServizio: "+ getCodiceTipologiaServizio());
		System.out.println("getCodiceImpostaServizio: "+ getCodiceImpostaServizio());
		System.out.println("getTipoBollettino: "+ getTipoBollettino());
		System.out.println("getDescrizione: "+ getDescrizione());
		System.out.println("getPrezzo: "+ getPrezzo());
		System.out.println("getQuantita: "+ getQuantita());
		System.out.println("getAttributesAsBuffer: "+ getAttributesAsBuffer());
	}

	public void onUpdate(CallableStatement arg) throws SQLException {
		int i = 1;
		arg.setString(i++, getIdItem());
		arg.setString(i++, getIdCarrello());
		arg.setString(i++, getDescrizione());
		arg.setBigDecimal(i++, getPrezzo());
		arg.setInt(i++, getQuantita());
		arg.setString(i++, getAttributesAsBuffer());
	}

}
