package com.primemobi.iaas.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.primemobi.iaas.action.ClusterAction;

/**
 * 业务逻辑层异常
 * @date 2012/12/11
 * @autor ftuo
 *
 */
public class ServiceException extends Exception{

	private static final long serialVersionUID = -71434260745841874L;
	 private static Logger log = null;
	//private Logger log = null;

	@SuppressWarnings("unchecked")
	public ServiceException(Class clazz, String message) {
		super(message);
		log = LoggerFactory.getLogger(clazz);
		log.error(message);
	}
	
	@SuppressWarnings("unchecked")
	public ServiceException(Class clazz, Throwable throwable) {
		super(throwable);
		log = LoggerFactory.getLogger(clazz);
		log.error(throwable.getMessage());
	}

}
