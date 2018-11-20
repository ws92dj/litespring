package org.litespring.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * 读取properties配置文件
 * @author diingjian
 *
 */
public class PropertiesUtils {
	
	public static String getProperties() throws IOException{
		 Properties properties = new Properties();
	     // 使用InPutStream流读取properties文件
		  BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/diingjian/workspace/litespring/src/test/resources/path.properties"));
		  
		 properties.load(bufferedReader);
		 // 获取key对应的value值
		Enumeration e = properties.propertyNames();
		while(e.hasMoreElements()){
			String key = (String) e.nextElement();
			String path = properties.getProperty(key);
			
			return path;
		}
		
		return null ;
	}
	
}
