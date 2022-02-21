package com.ibm.academia.restapi.universidad.configuraciones;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxSwagger{
	@Bean
	public Docket getDocket(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ibm.academia.restapi.universidad.controladores"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo(){
        return new ApiInfo(
                "Universidad Backend API",
                "API para el manejo de nuestra universidad",
                "V1",
                "Terminos del servicio",
                new Contact("Alejandro Medero", "Slack:@Alejandro Medero", "alexmedero10@gmail.com"),
                "Licencia de API", "API licencia url", Collections.emptyList()
        );
    } 
}