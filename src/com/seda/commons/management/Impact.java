package com.seda.commons.management;

/**
 * The impact of this operation
 */
public enum Impact {INFO(0), ACTION(1), ACTION_INFO(2), UNKNOWN(3);

	public final int impactValue;
    private Impact(int impactValue) {
        this.impactValue = impactValue;
    }
    
    public static int getImpact(String name) {
    	Impact impact = Impact.valueOf(name);
    	if (impact==null) return UNKNOWN.impactValue;
    	return impact.impactValue;
    }
    
}