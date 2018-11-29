package org.litespring.context.support;


import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

public class FileSystemApplicationContext extends AbstractApplicationContext {

	public FileSystemApplicationContext(String configFile) {
		super(configFile);
		
	}

	@Override
	public Resource getResourceByPath(String path) {
		
		return new FileSystemResource(path);
	}
	

}
