package org.litespring.context.support;

import org.litespring.beans.support.DefaultBeanFactory;
import org.litespring.beans.xml.XmlBeanDefinitionReader;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.Resource;
import org.litespring.utils.ClassUtils;

public abstract class AbstractApplicationContext implements ApplicationContext {
	private DefaultBeanFactory factory = null;
	private ClassLoader beanClassLoader = null;
	public AbstractApplicationContext(String configFile){
		this.factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		Resource resource = this.getResourceByPath(configFile);
		reader.loadBeanDefinitions(resource);
		factory.setBeanClassLoader(beanClassLoader);
	}
	
	public Object getBean(String beanID) {
		
		return this.factory.getBean(beanID);
	}
	protected abstract Resource getResourceByPath(String path);
	
	public void setBeanClassLoader(ClassLoader classLoader) {
		this.beanClassLoader = classLoader;
		
	}



	public ClassLoader getBeanClassLoader() {
		
		return (this.beanClassLoader == null ? ClassUtils.getDefaultClassLoader():this.beanClassLoader) ;
	}
}
