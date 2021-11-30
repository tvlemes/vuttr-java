package com.vuttr.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
	
	public static final String AUTHORIZATION_HEADER = "Authorization";
	
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.securityContexts(Arrays.asList(securityContext()))
		        .securitySchemes(Arrays.asList(apiKey()))
		.select()
		.apis(RequestHandlerSelectors.basePackage("com.vuttr.controllers"))
		.build()	
		.useDefaultResponseMessages(false)
        .globalResponseMessage(RequestMethod.GET, responseMessageForGET())        
		.apiInfo(metaData());
	}
	
	/* Message return */
	private List<ResponseMessage> responseMessageForGET()
	{
	    return new ArrayList<ResponseMessage>() {			
			private static final long serialVersionUID = 1L;

		{
	        add(new ResponseMessageBuilder()
	            .code(500)
	            .message("500 ERROR INTERNAL SERVER!!!")
	            .responseModel(new ModelRef("Error"))
	            .build());
	        add(new ResponseMessageBuilder()
	            .code(403)
	            .message("403 ACCESS DENIED!!!")
	            .build());
	    }};
	}
	
	/* Info data*/
	private ApiInfo metaData() {
	    return new ApiInfo(
	      "REST API VUTTR", 
	      "Project developed to BossaBox.", 
	      "API REST", 
	      "", 
	      new Contact("Thiago Lemes", "https://thiagolemes.tech", "lemes_vilarinho@yahoo.com.br"), 
	      "License of API", "https://github.com/thiagovilarinholemes/project-vuttr-back-end/blob/main/LICENSE", Collections.emptyList());
	}
	
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
		.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**")
		.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	/* Authentication */
	private ApiKey apiKey() {
	    return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	  }

	  private SecurityContext securityContext() {
	    return SecurityContext.builder()
	        .securityReferences(defaultAuth())
	        .build();
	  }

	  List<SecurityReference> defaultAuth() {
		  AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
		    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
		    authorizationScopes[0] = authorizationScope; 
		    return Arrays.asList(new SecurityReference("JWT", authorizationScopes)); 
	  }
}