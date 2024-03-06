/**
 * 
 */
package com.seda.j2ee5.maf.template;

import java.io.Serializable;

/**
 * @author Seda Lab
 *
 */
public class TemplateParameter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String key;
    private String value;
    private boolean direct;
    private String args;    

    public TemplateParameter(String key, String value, boolean direct, String args) {
        this.key = key;
        this.value = value;
        this.direct = direct;
        this.args = args;        
    }

    public boolean isDirect() {
        return direct;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

	public String getArgs() {
		return args;
	}

	@Override
	public String toString() {
		return "TemplateParameter [args=" + args + ", direct=" + direct
				+ ", key=" + key + ", value=" + value + "]";
	}

}
