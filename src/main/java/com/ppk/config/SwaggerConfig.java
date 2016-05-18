//package com.ppk.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//import com.mangofactory.swagger.plugin.EnableSwagger;
//
//@Configuration
//@Profile({ "local" })
//@EnableSwagger
//@EnableWebMvc
//public class SwaggerConfig extends WebMvcConfigurerAdapter {
//
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		System.out.println("Loading swagger static assets..");
//		registry.addResourceHandler("/api/**").addResourceLocations(
//				"classpath:api/");
//		System.out.println("Done loading the swagger resources..");
//	}
//
//}