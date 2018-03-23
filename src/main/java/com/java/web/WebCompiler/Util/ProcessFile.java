package com.java.web.WebCompiler.Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.java.web.WebCompiler.Service.Util;

@Service
public class ProcessFile implements Util {
	
	public static final Logger logger = LoggerFactory.getLogger(ProcessFile.class);
	
	public static String printLines(String name, InputStream ins) throws Exception {
	    String line = null;
	    StringBuffer output=new StringBuffer();
	    BufferedReader in = new BufferedReader(
	        new InputStreamReader(ins));
	    while ((line = in.readLine()) != null) {
	    	String eachLine=line;
	    	output.append(eachLine+"\n");
	    	logger.info(name + " " + line);
	        /*System.out.println(name + " " + line);*/
	    }
	    return output.toString();
	  }
	
	public String runProcess(String command) throws Exception {
		String correctOutput=null;
		String errorOutput=null;
	    Process pro = Runtime.getRuntime().exec(command);
	    correctOutput=printLines(command + " stdout:", pro.getInputStream());
	    errorOutput=printLines(command + " stderr:", pro.getErrorStream());
	    pro.waitFor();
	    logger.info(command + " exitValue() " + pro.exitValue());
	    /*System.out.println(command + " exitValue() " + pro.exitValue());*/
	    return correctOutput.equals("")?errorOutput:correctOutput;
	  }
	
	public String getClassName(String code) {
		code = code.substring(0, code.indexOf("{"));
		code = code.substring((code.lastIndexOf(" ") + 1));
		return code.replaceAll("[\\r\\n]+", "");	
	}
	
	public void writeCodeToFile(String className,String path,String code) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(path + className + ".java"));
		writer.write(code);
		writer.close();
	}
	
	public void deleteFiles(String className,String path) {
		File file = new File(path + className + ".class");
		file.delete();
		file = new File(path + className + ".java");
		file.delete();
	}

}
