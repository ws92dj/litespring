package org.litespring.context.support;

import org.litespring.beans.support.DefaultBeanFactory;
import org.litespring.beans.xml.XmlBeanDefinitionReader;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
/**
 * 通过classpath读取配置文件
 * @author dingjian
 *
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext{

	public ClassPathXmlApplicationContext(String configFile) {
		super(configFile);
		
	}

	@Override
	protected Resource getResourceByPath(String path) {
		
		return new ClassPathResource(path);
	}
	


}
