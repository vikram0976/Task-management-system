package com.muster_dekho;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AllowForEndPoints implements WebMvcConfigurer{
	/**
	 * Configure Cross-Origin Resource Sharing (CORS) mappings.
	 * This method allows requests from all origins and permits all HTTP methods.
	  */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("*");
	}

}
