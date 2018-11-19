package org.litespring.context.support;

import org.litespring.beans.support.DefaultBeanFactory;
import org.litespring.beans.xml.XmlBeanDefinitionReader;
import org.litespring.context.ApplicationContext;
/**
 * 通过classpath读取配置文件
 * @author dingjian
 *
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {
	
	private DefaultBeanFactory factory = null;
	
	public ClassPathXmlApplicationContext(String configFile) {
		this.factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinitions(configFile);;
		
	}
	/**
	 * 获取bean的实例
	 */
	
	public Object getBean(String beanID) {
		
		return factory.getBean(beanID);
	}

}
