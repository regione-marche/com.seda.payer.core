package com.seda.tag.core;

public class IfSeda {

	public boolean test;
	public String left;
	  public String right;
	  public String control;
	  public String sElse;
	  public String sThen;
	  
	  public String secondLeft;
	  public String secondRight;
	  public String secondControl;
	  public String operator;
	public String getRight() {
		return right;
	}
	public void setRight(String right) {
		this.right = right;
	}
	public String getControl() {
		return control;
	}
	public void setControl(String control) {
		this.control = control;
	}
	public String getSecondLeft() {
		return secondLeft;
	}
	public void setSecondLeft(String secondLeft) {
		this.secondLeft = secondLeft;
	}
	public String getSecondRight() {
		return secondRight;
	}
	public void setSecondRight(String secondRight) {
		this.secondRight = secondRight;
	}
	public String getSecondControl() {
		return secondControl;
	}
	public void setSecondControl(String secondControl) {
		this.secondControl = secondControl;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String check) {
		this.operator = check;
	}
	public String getSElse() {
		return sElse;
	}
	public void setSElse(String else1) {
		sElse = else1;
	}
	public String getSThen() {
		return sThen;
	}
	public void setSThen(String then) {
		sThen = then;
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
		    else if(sControl.equalsIgnoreCase("eqList"))
		    { test = false;
		     String[] checkString = sRight.split(";");
		     for(int i = 0;i< checkString.length;i++)
		     { sRight= checkString[i].replace(";", "");
		     
		    	 if (sLeft.trim().compareTo(sRight.trim()) == 0)
		    	   return true;
		     }
		    }
		     else if(sControl.equalsIgnoreCase("neqList"))
			    {
		    	 test = true;
			     String[] checkString = sRight.split(";");
			     for(int i = 0;i< checkString.length;i++)
			     { sRight= checkString[i].replace(";", "");
			    	 if (sLeft.trim().compareTo(sRight.trim()) == 0)
			    	   test = false;
			    	
			     }
		    	
		    }
		    else test = false;
		  return test;
	  }
	
	
}
