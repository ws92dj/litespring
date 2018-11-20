package org.litespring.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class FileSystemResource implements Resource {
	private final String path;
	private final File file;
	public FileSystemResource(String path){
		this.path = path;
		this.file = new File(path);
		
	}
	public InputStream getInputStream() throws Exception {
		
		return new FileInputStream(file);
	}

	public String getDescription() {
		
		return "file ["+this.file.getAbsolutePath()+"]";
	}

}
