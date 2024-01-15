/**
 * 
 */
package com.seda.j2ee5.maf.defender.rule;

/**
 * @author Seda Lab
 *
 */
public class Severity {
	
//	public final static String IGNORE = "ignore";
    public final static String CONTINUE = "continue";
    public final static String FATAL = "fatal";
    
    private String severity = null;
    
    public Severity() {
        this.severity = CONTINUE;
    }
    
    public Severity(String severity) {this.severity = severity;}
    
//    public boolean isIgnore() {return IGNORE.equals(severity);}
    
    public boolean isContinue() {return CONTINUE.equals(severity);}
    
    public boolean isFatal() {return FATAL.equals(severity);}
    
    public boolean equals(Object o) {
        boolean equals = false;
        
        if(o instanceof String) {
            equals = severity.equals(o.toString());
        } else if(o instanceof Severity) {
            equals = severity.equals(o.toString());
        } else {
            throw new IllegalArgumentException();
        }
        
        return equals;
    }

	@Override
	public String toString() {
		return "Severity [severity=" + severity + "]";
	}

}
