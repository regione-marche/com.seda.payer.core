/**
 * 
 */
package com.seda.commons.params;

/**
 * 
 * A factory for getting the default set of parameters to use when creating an instance of 
 * <code>Parameters</code>.
 * 
 * 
 * @author f.ricci
 *
 */
public interface ParametersFactory {

	
	/**
     * Gets the default parameters.  This method may be called more than once and is not required
     * to always return the same value.
     * 
     * @return an instance of Parameters
     */
	Parameters getDefaultParameters();	
	
}
