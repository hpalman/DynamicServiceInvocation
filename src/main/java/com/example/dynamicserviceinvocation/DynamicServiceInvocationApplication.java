package com.example.dynamicserviceinvocation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
        basePackages = { "com.example.dynamicserviceinvocation","kr.co.buttle.corebase.web.dto" }
)
public class DynamicServiceInvocationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamicServiceInvocationApplication.class, args);
	}

}
