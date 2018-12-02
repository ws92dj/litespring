package org.litespring.test.v1;

import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinitionException;

import org.litespring.beans.support.DefaultBeanFactory;
import org.litespring.beans.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;

import org.litespring.service.v1.PetStoreService;

import static org.junit.Assert.*;


import org.junit.Assert;
import org.junit.Before;
public class BeanFactoryTest {
	DefaultBeanFactory factory = null;
	XmlBeanDefinitionReader reader = null;
	
	@Before
	public void setUp(){
		factory = new DefaultBeanFactory();
		reader = new XmlBeanDefinitionReader(factory);
	}
	@Test
	public void testGetBean(){
		
		reader.loadBeanDefinitions(new ClassPathResource("petstore_v1.xml"));
		BeanDefinition bd = factory.getBeanDefinition("petStore");
		assertTrue(bd.isSingleton());
		assertFalse(bd.isPrototype());
		assertEquals(BeanDefinition.SCOPE_SINGLETON,bd.getScope());
		
		assertEquals("org.litespring.service.v1.PetStoreService", bd.getBeanClassName());
		
		PetStoreService petStore = (PetStoreService)factory.getBean("petStore");
		
		assertNotNull(petStore);
		PetStoreService petStore1 = (PetStoreService)factory.getBean("petStore");
		
		assertTrue(petStore.equals(petStore1));
	}
	
	@Test
	public void testInvalidBean(){
		
		try{
			factory.getBean("invalidBean");
		}catch(BeanCreationException e){
			return;
		}
		
		Assert.fail("expect BeanCreationException");
		
	}
	
	@Test
	public void testInvalidXML(){
		try{
			
			reader.loadBeanDefinitions(new ClassPathResource("XXXX.xml"));
		}catch(BeanDefinitionException e){
			return;
		}
		
		Assert.fail("expect BeanDefinitionException");
	}
}
