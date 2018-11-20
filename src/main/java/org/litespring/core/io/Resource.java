package org.litespring.core.io;

import java.io.IOException;
import java.io.InputStream;
/**
 * 用于获取配置文件
 * @author diingjian
 *
 */
public interface Resource {
	 InputStream getInputStream() throws Exception;
	 
	 String getDescription();
}
