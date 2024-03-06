package com.seda.payer.commons.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;

import com.seda.data.dao.ModelAttributes;
/**
 * 
 * @author mmontisano
 *
 */
public abstract class Lifecycle extends ModelAttributes implements Serializable {

	private static final long serialVersionUID = 1L;

	public void load(CallableStatement arg) throws SQLException { 
		onLoad(arg); 
	}

	public abstract void onLoad(CallableStatement arg) throws SQLException;
	
	public void load(CallableStatement arg, int rowsPerPage, int pageNumber, String order) throws SQLException { 
		onLoad(arg, rowsPerPage, pageNumber, order);
	}

	public void onLoad(CallableStatement arg, int rowsPerPage, int pageNumber, String order) throws SQLException { }

	public void load(CallableStatement arg, int scope) throws SQLException { 
		onLoad(arg, scope);
	}

	public void onLoad(CallableStatement arg, int scope) throws SQLException { }

	public void save(CallableStatement arg) throws SQLException { 
		onSave(arg); 
	}

	public abstract void onSave(CallableStatement arg) throws SQLException;
	

	public void update(CallableStatement arg) throws SQLException { 
		onUpdate(arg); 
	}

	public abstract void onUpdate(CallableStatement arg) throws SQLException;
	

	public void delete(CallableStatement arg) throws SQLException { 
		onDelete(arg); 
	}

	public abstract void onDelete(CallableStatement arg) throws SQLException;
}