package br.com.paulo.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.paulo.serializacao.converter.YamlJackson2HttpMessageConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	private static final MediaType MEDIA_TYPE_YAML = MediaType.valueOf("application/x-yaml");
	
	
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new YamlJackson2HttpMessageConverter());
	}
	
	// HABILITAR CORS PARA A APLICACAO INTEIRA 
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//HABILITAR ACESSOS PARA TODOS
		registry.addMapping("*/**").
		allowedMethods("GET","POST","PUT","PATCH","DELETE","OPTIONS","HEAD","TRACE","CONNECT");
	}
	
	//CONTENT NEGOTIATION TIPOS DE ARQUIVOS DE ENTRADA E SAIDA
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		
		//ESCOLHA DO TIPO VIA EXTENÇÃO http://localhost:8080/api/pessoa/v1/1.xml
		
//		configurer.favorParameter(false).ignoreAcceptHeader(false).defaultContentType(MediaType.APPLICATION_JSON)
//		.mediaType("json", MediaType.APPLICATION_JSON)
//		.mediaType("xml", MediaType.APPLICATION_XML);
		
		
		//VIA Query Params http://localhost:8080/api/pessoa/v1/1?tipoMidia=xml
		/*
		 * configurer.favorPathExtension(false).
		 * favorParameter(true).parameterName("tipoMidia"). ignoreAcceptHeader(true).
		 * useRegisteredExtensionsOnly(false).
		 * defaultContentType(MediaType.APPLICATION_JSON) .mediaType("json",
		 * MediaType.APPLICATION_JSON) .mediaType("xml", MediaType.APPLICATION_XML);
		 */
		
		//VIA Query Params http://localhost:8080/api/pessoa/v1/1?tipoMidia=xml
		
		
		
		configurer.favorPathExtension(false).
		favorParameter(false).
		ignoreAcceptHeader(false).
		useRegisteredExtensionsOnly(false).
		defaultContentType(MediaType.APPLICATION_JSON)
		.mediaType("json", MediaType.APPLICATION_JSON)
		.mediaType("xml", MediaType.APPLICATION_XML).
		mediaType("x-yaml", MEDIA_TYPE_YAML);
		
		
	}

}
