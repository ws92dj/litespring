package org.litespring.beans.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinitionException;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.service.v1.PetStoreService;
import org.litespring.utils.ClassUtils;

public class DefaultBeanFactory implements BeanFactory,BeanDefinitionRegistry {
	
	
	private final Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
	public DefaultBeanFactory() {
	}
	
	
	
	public BeanDefinition getBeanDefinition(String beanID) {
		
		return this.beanDefinitionMap.get(beanID);
	}

	public Object getBean(String beanID) {
		BeanDefinition bd = this.getBeanDefinition(beanID);
		if(bd == null){
			throw new BeanCreationException("Bean Definition does not exists");
		}
		ClassLoader cl = ClassUtils.getDefaultClassLoader();
		String beanClassName = bd.getBeanClassName();
		try{
			Class<?> clz = cl.loadClass(beanClassName);
			return clz.newInstance();
		}catch(Exception e){
			throw new BeanCreationException("create bean for"+beanClassName+" failed");
		}
		
		
	}

	public void registryBeanDefinition(String beanID, BeanDefinition bd) {
		this.beanDefinitionMap.put(beanID, bd);
	}
	

}
