package org.litespring.context.support;

import org.litespring.beans.support.DefaultBeanFactory;
import org.litespring.beans.xml.XmlBeanDefinitionReader;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.Resource;

public abstract class AbstractApplicationContext implements ApplicationContext {
	private DefaultBeanFactory factory = null;
	
	public AbstractApplicationContext(String configFile){
		this.factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		Resource resource = this.getResourceByPath(configFile);
		reader.loadBeanDefinitions(resource);
		
	}
	
	public Object getBean(String beanID) {
		
		return this.factory.getBean(beanID);
	}
	protected abstract Resource getResourceByPath(String path);
}
