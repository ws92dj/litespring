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
import org.litespring.core.io.Resource;
import org.litespring.utils.ClassUtils;

public class XmlBeanDefinitionReader {
	public static final String ID_ATTRIBUTE = "id";
	public static final String CLASS_ATTRIBUTE = "class";
	public static final String SCOPE_ATTRIBUTE = "scope";
	
	
	
	BeanDefinitionRegistry registry;

	public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
		this.registry = registry;
	}
	
	public void loadBeanDefinitions(Resource resource){
		InputStream is = null;
		
		try{
			
			is = resource.getInputStream();
			
			SAXReader reader = new SAXReader();
			Document doc = reader.read(is);
			
			Element root = doc.getRootElement();
			Iterator<Element> itr = root.elementIterator();
			while(itr.hasNext()){
				Element ele = itr.next();
				String id = ele.attributeValue(ID_ATTRIBUTE);
				String beanClassName =  ele.attributeValue(CLASS_ATTRIBUTE);
				
				BeanDefinition bd = new GenericBeanDefinition(id, beanClassName);
				if(ele.attribute(SCOPE_ATTRIBUTE) != null){
					bd.setScope(ele.attributeValue(SCOPE_ATTRIBUTE));
				}
				this.registry.registryBeanDefinition(id, bd);
			}
			
		}catch(Exception e){
			throw new BeanDefinitionException("IOException parsing XML document "+resource.getDescription());
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
