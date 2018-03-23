package com.java.web.WebCompiler.Contoller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.web.WebCompiler.Service.Util;

@RestController
@RequestMapping("/compile")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);
	
	@Autowired
	Util compileUtil;

	@RequestMapping(value = "/java", method = RequestMethod.POST)
	public @ResponseBody String getString(@RequestBody String code) {
		logger.info(code);
		StringBuffer output = new StringBuffer();
		String runOutput = null;
		String compilationOutput = null;
		String path = "D:/document/";
		String className = compileUtil.getClassName(code);
		try {
			compileUtil.writeCodeToFile(className, path, code);
			compilationOutput = compileUtil.runProcess("javac " + path + className + ".java");
			output.append(compilationOutput + "\n");
			if (!compilationOutput.contains("error: ") && !compilationOutput.contains("[0-9] error")) {
				runOutput = compileUtil.runProcess("java -cp " + path + " " + className);
				output.append(runOutput);
			}
			compileUtil.deleteFiles(className, path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output.toString();
	}
}
