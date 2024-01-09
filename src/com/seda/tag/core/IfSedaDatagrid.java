package com.seda.tag.core;

import java.util.ArrayList;

public class IfSedaDatagrid {

	public boolean test;
	public String left;
	  public String right;
	  public String control;
	  public ArrayList<DgColumn> dgElse = new ArrayList<DgColumn>() ;
	  public ArrayList<DgColumn> dgThen = new ArrayList<DgColumn>();

	  public ArrayList<DgColumn> getDgElse() {
		return dgElse;
	}
	public void setDgElse(ArrayList<DgColumn> dgElse) {
		this.dgElse = dgElse;
	}
	public ArrayList<DgColumn> getDgThen() {
		return dgThen;
	}
	public void setDgThen(ArrayList<DgColumn> dgThen) {
		this.dgThen = dgThen;
	}
	
	public void AddDgElse(DgColumn dg){
		this.dgElse.add(dg); 
	}
	public void AddDgThen(DgColumn dg){
		this.dgThen.add(dg); 
	}
	public String getControl() {
		return control;
	}
	public void setControl(String control) {
		this.control = control;
	}

	  
	
	  public boolean Check(String sLeft,String sRight,String sControl){
		  
		  if(sControl.equalsIgnoreCase("le"))
		    {
		    	test = sLeft.equals(sRight) || (sLeft.compareTo(sRight)<0);
		    }
		    else if (sControl.equalsIgnoreCase("lt"))
		    {
		    	test = sLeft.compareTo(sRight)<0;
		    }
		    else if (sControl.equalsIgnoreCase("gt"))
		    {
		    	test = sLeft.compareTo(sRight)>0;
		    }
		    else if (sControl.equalsIgnoreCase("ge"))
		    {
		    	test = sLeft.equals(sRight) || (sLeft.compareTo(sRight)>0);
		    }
		    else if (sControl.equalsIgnoreCase("eq"))
		    {
		    	test = sLeft.equals(sRight);
		    }
		    else if (sControl.equalsIgnoreCase("ne"))
		    {
		    	test = !sLeft.equals(sRight);
		    	
		    }
		    else test = false;
		  return test;
	  }
	
	
}
