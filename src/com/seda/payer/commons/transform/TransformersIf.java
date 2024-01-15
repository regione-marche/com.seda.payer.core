package com.seda.payer.commons.transform;

import java.io.Serializable;
/**
 * 
 * @author mmontisano
 *
 */
public interface TransformersIf {

	public Serializable beanToBean(Object bean) throws Exception;
}