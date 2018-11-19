package org.litespring.beans.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanDefinitionException;
import org.litespring.beans.support.BeanDefinitionRegistry;
import org.litespring.beans.support.GenericBeanDefinition;
import org.litespring.utils.ClassUtils;

public class XmlBeanDefinitionReader {
public static final String ID_ATTRIBUTE = "id";
	
	public static final String CLASS_ATTRIBUTE = "class";
	
	
	
	BeanDefinitionRegistry registry;

	public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
		this.registry = registry;
	}
	
	public void loadBeanDefinitions(String configFile){
		InputStream is = null;
		
		try{
			ClassLoader cl = ClassUtils.getDefaultClassLoader();
			is = cl.getResourceAsStream(configFile);
			
			SAXReader reader = new SAXReader();
			Document doc = reader.read(is);
			
			Element root = doc.getRootElement();
			Iterator<Element> itr = root.elementIterator();
			while(itr.hasNext()){
				Element ele = itr.next();
				String id = ele.attributeValue(ID_ATTRIBUTE);
				String beanClassName =  ele.attributeValue(CLASS_ATTRIBUTE);
				
				BeanDefinition bd = new GenericBeanDefinition(id, beanClassName);
				this.registry.registryBeanDefinition(id, bd);
			}
			
		}catch(Exception e){
			throw new BeanDefinitionException("IOException parsing XML document " + configFile);
		}finally{
			if(is != null){
				try{
					is.close();
				}catch(IOException e){
					e.printStackTrace();
				}
				
			}
		}
	}
	
	
}