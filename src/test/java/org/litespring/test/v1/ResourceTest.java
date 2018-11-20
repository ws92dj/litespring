package org.litespring.test.v1;

import java.io.InputStream;

import  org.junit.Assert;

import org.junit.Test;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;
import org.litespring.utils.PropertiesUtils;

public class ResourceTest {
	
	@Test
	public void testClassPathResource() throws Exception {
		Resource r = new ClassPathResource("petstore_v1.xml");
		InputStream is = null;
		try{
			is = r.getInputStream();
			Assert.assertNotNull(is);
		}finally{
			if(is != null)
				is.close();
			
		}
		
	}
	
	@Test
	public void testFileSystemResource()throws Exception{
		Resource r = new FileSystemResource(PropertiesUtils.getProperties());
		InputStream is = null;
		try{
			is = r.getInputStream();
			Assert.assertNotNull(is);
			
			
		}finally{
			if(is != null)
				is.close();
		}
		
		
	}

}
