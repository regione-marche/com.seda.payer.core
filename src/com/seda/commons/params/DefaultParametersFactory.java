package com.seda.commons.params;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class DefaultParametersFactory  implements ParametersFactory {

	protected Parameters parameters;

	public DefaultParametersFactory() {
		super();
	}

	public synchronized Parameters getDefaultParameters() {
		if (parameters == null) {
			parameters = createParams();
		}

		return parameters;
	}

	protected Parameters createParams() {
		throw new NotImplementedException();
	}  

}    
