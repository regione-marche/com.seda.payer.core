package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemAttribute implements Serializable {
	
	private String idItem;
    private String key;
    private String value;

    private static final long serialVersionUID = -6886565017682591908L;

    
	public String getIdItem() {
		return idItem;
	}

	public void setIdItem(String idItem) {
		this.idItem = idItem;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ItemAttribute(ResultSet data) throws SQLException {
    	if (data == null) {
    		return;
    	}
    	idItem = data.getString("ITA_KITEKITE");
    	key = data.getString("ITA_CITACHAT");
    	value = data.getString("ITA_CITAVAAT");
	}

	public ItemAttribute() {
	}

	public void onDelete(CallableStatement arg0) throws SQLException {
	}

	public void onLoad(CallableStatement arg0) throws SQLException {
	}

	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, getIdItem());
		arg.setString(2, getKey());
		arg.setString(3, getValue());
	}

	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, getIdItem());
		arg.setString(2, getKey());
		arg.setString(3, getValue());
	}

}
