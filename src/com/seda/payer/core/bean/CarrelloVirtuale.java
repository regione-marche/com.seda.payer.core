package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CarrelloVirtuale implements Serializable {

	private static final long serialVersionUID = 4765329803681419462L;
	private String shoppingCartId;
	private String securityToken;
    private String codiceSocieta;
    private String canalePagamento;
	private int maxSessions;
	private boolean stateful;
	
	private List<Item> items;
	
	public String getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId(String shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public String getSecurityToken() {
		return securityToken;
	}

	public void setSecurityToken(String securityToken) {
		this.securityToken = securityToken;
	}
  
	public String getCodiceSocieta() {
        return codiceSocieta;
    }

    public void setCodiceSocieta(String codiceSocieta) {
        this.codiceSocieta = codiceSocieta;
    }

    public String getCanalePagamento() {
        return canalePagamento;
    }

    public void setCanalePagamento(String canalePagamento) {
        this.canalePagamento = canalePagamento;
    }
	
	public int getMaxSessions() {
		return maxSessions;
	}

	public void setMaxSessions(int maxSessions) {
		this.maxSessions = maxSessions;
	}

	public boolean isStateful() {
		return stateful;
	}

	public void setStateful(boolean stateful) {
		this.stateful = stateful;
	}

	public List<Item> getItems() {
		return Collections.unmodifiableList(items);
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public CarrelloVirtuale() {
	   	items = new ArrayList<Item>();
	}

	public CarrelloVirtuale(ResultSet data) throws SQLException {
    	if (data == null) {
    		return;
    	}
    	shoppingCartId = data.getString("CAV_KCAVKCAV");
    	securityToken = data.getString("CAV_CCAVSTOK");
    	codiceSocieta = data.getString("CAV_CSOCCSOC");
       	canalePagamento = data.getString("CAV_KCANKCAN");
        maxSessions = data.getInt("CAV_ICAVMAXS");
        stateful = "Y".equalsIgnoreCase(data.getString("CAV_FCAVSFUL"));
   
    	items = new ArrayList<Item>();
    }
    
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, getCodiceSocieta());
		arg.setString(2, getCanalePagamento());
		arg.setString(3, getSecurityToken());
		arg.setInt(4, getMaxSessions());
		arg.setString(5, isStateful()?"Y":"N");
	}

	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, getShoppingCartId());
		arg.setString(2, getSecurityToken());
		arg.setString(3, isStateful()?"Y":"N");
	}

}
