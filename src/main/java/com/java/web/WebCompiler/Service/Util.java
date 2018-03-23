package com.java.web.WebCompiler.Service;

import java.io.IOException;

public interface Util {

	public String getClassName(String code);

	public void writeCodeToFile(String className, String path, String code) throws IOException;

	public void deleteFiles(String className, String path);
	
	public String runProcess(String command) throws Exception;
}
