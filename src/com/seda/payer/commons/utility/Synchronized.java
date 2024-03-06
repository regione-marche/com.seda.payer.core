package com.seda.payer.commons.utility;

import java.io.Serializable;

public final class Synchronized implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final Object Mutex = new Object();
}

