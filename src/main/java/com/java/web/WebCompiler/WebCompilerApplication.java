package com.java.web.WebCompiler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"com.java.web.WebCompiler"})
public class WebCompilerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebCompilerApplication.class, args);
	}
}
