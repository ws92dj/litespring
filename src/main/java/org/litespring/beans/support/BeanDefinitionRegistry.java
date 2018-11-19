package org.litespring.beans.support;

import org.litespring.beans.BeanDefinition;

public interface BeanDefinitionRegistry {
	BeanDefinition getBeanDefinition(String beanID);
	
	/**
	 * 注册BeanDefinition
	 * @param beanID
	 * @param bd
	 */
	void registryBeanDefinition(String beanID,BeanDefinition bd);
	
}
