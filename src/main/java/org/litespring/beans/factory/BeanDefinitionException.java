package org.litespring.beans.factory;

import org.litespring.beans.BeansException;

public class BeanDefinitionException extends BeansException {

	public BeanDefinitionException(String msg, Throwable cause) {
		super(msg, cause);
		
	}

	public BeanDefinitionException(String msg) {
		super(msg);
	}

}
