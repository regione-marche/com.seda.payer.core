package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;

import com.seda.payer.commons.bean.Lifecycle;

public class Pagonet extends Lifecycle implements Serializable  {
/**
	 * 
 */
private static final long serialVersionUID = 1L;
BollettinoIci Ici = new BollettinoIci();
BollettinoCds Cds = new BollettinoCds();
BollettinoGenerale Gen = new BollettinoGenerale();




@Override
public void onDelete(CallableStatement arg0) throws SQLException {
	// TODO Auto-generated method stub
	
}
@Override
public void onLoad(CallableStatement arg0) throws SQLException {
	// TODO Auto-generated method stub
	
}
@Override
public void onSave(CallableStatement arg0) throws SQLException {
	// TODO Auto-generated method stub
	
}
@Override
public void onUpdate(CallableStatement arg0) throws SQLException {
	// TODO Auto-generated method stub
	
}


	
}
