package org.litespring.context.support;

import org.litespring.beans.support.DefaultBeanFactory;
import org.litespring.beans.xml.XmlBeanDefinitionReader;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

public class FileSystemApplicationContext implements ApplicationContext {
	private DefaultBeanFactory factory = null;
	
	public FileSystemApplicationContext(String configFile){
		this.factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		Resource resource = new FileSystemResource(configFile);
		reader.loadBeanDefinitions(resource);
		
	}
	public Object getBean(String beanID) {
		
		return this.factory.getBean(beanID);
	}

}
