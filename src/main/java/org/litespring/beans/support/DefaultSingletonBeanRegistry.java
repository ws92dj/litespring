package org.litespring.beans.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.litespring.beans.factory.config.SingletonBeanRegistry;
import org.litespring.utils.Assert;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
	
	private final Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(64);
	
	public void registrySingleton(String beanName, Object singletonObject) {
		Assert.notNull(beanName, "'beanName' must be not null");
		
		Object oldObject = this.singletonObjects.get(beanName);
		if(oldObject != null){
			throw new IllegalStateException("Could not register Object ["+
		singletonObject+"] under bean  name" +beanName+" : there is already object ["+oldObject+"] bound");
		}
		this.singletonObjects.put(beanName, singletonObject);
		
	}

	public Object getSingleton(String beanName) {
		
		return this.singletonObjects.get(beanName);
	}


}
