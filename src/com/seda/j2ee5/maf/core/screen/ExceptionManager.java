/**
 * 
 */
package com.seda.j2ee5.maf.core.screen;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author f.ricci
 *
 */
public class ExceptionManager implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private HashMap<String, ExceptionData> exceptions;

    public ExceptionManager (HashMap<String, ExceptionData>  exceptions) {
        this.exceptions = exceptions;
    }

    public HashMap<String, ExceptionData>  getExceptions() {
        return exceptions;
    }
    
    public ExceptionData getExceptionData(String exceptionClass) {
    	return exceptions.get(exceptionClass);
    }
}
