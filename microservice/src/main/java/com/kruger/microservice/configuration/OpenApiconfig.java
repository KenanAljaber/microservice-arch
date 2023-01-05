package com.kruger.microservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiconfig {
	

    @Bean
    public OpenAPI fortuneAPI() {
    	
    	
      
        		Info i=new Info();
       i.title("Football Players Demo App");
        i.description("This is the documentation of Football Players Demo App");
        i.version("v0.0.1");
        OpenAPI api=new OpenAPI();
        api.setInfo(i);
        return api;
             
    }

}
