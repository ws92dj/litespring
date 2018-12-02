package org.litespring.beans.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.config.ConfigurableBeanFactory;
import org.litespring.utils.ClassUtils;

public class DefaultBeanFactory extends DefaultSingletonBeanRegistry
	implements ConfigurableBeanFactory,BeanDefinitionRegistry {
	
	
	private final Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
	private ClassLoader beanClassLoader;
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
		if(bd.isSingleton()){
			Object bean = this.getSingleton(beanID);
			if(bean == null){
				bean = createBean(bd);
				this.registrySingleton(beanID, bean);
			}
			return bean;	
		}
		return createBean(bd);		
	}
	
	private Object createBean(BeanDefinition bd){
		ClassLoader cl =  this.getBeanClassLoader();
		String beanClassName = bd.getBeanClassName();
		try{
			Class<?> clazz = cl.loadClass(beanClassName);
			return clazz.newInstance();
		}catch(Exception e){
			throw new BeanCreationException("create bean for" + beanClassName+"failed",e);
		}
	}

	public void registryBeanDefinition(String beanID, BeanDefinition bd) {
		this.beanDefinitionMap.put(beanID, bd);
	}



	public void setBeanClassLoader(ClassLoader classLoader) {
		this.beanClassLoader = classLoader;
		
	}



	public ClassLoader getBeanClassLoader() {
		
		return (this.beanClassLoader == null ? ClassUtils.getDefaultClassLoader():this.beanClassLoader) ;
	}
	

}
