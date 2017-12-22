package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.demo.interceptor.MyInterceptor;

@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {
	
	
	@Autowired
	MyInterceptor interceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor);
		
		
		
	}
	

}
