package pe.com.registro2026.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

// Configuration - define como una clase de configuracion
@Configuration
public class WebConfig implements WebMvcConfigurer{

	//@Bean -> define metodos que seran incluidos en Spring
	//Creamos una funcion para definir la resolucion de pantallas
	@Bean
	public ClassLoaderTemplateResolver templateResolver() {
		//declaramos una variable de tipo ClassLoaderTemplateResolver
		var templateResolver = new ClassLoaderTemplateResolver();
		
		//definimos la carpeta donde se encuentra las paginas web
		templateResolver.setPrefix("templates/");
		
		//deshabilitamos la opcion de cacheable de la aplicacion
		templateResolver.setCacheable(false);
		
		//Definimos el tipo de paginas que van a cargar
		templateResolver.setSuffix(".html");
		
		//definimos el modo de las plantillas de las paginas
		templateResolver.setTemplateMode("HTML5");
		
		//definimos que permita caracteres especiales
		templateResolver.setCharacterEncoding("UTF-8");
		
		return templateResolver;
	}


//creamos un Bean para definir el motor de integracion del Thymeleaf
@Bean
public SpringTemplateEngine templateEngine() {
	//declaramos una variable de tipo SpringTemplateEngine
	var templateEngine = new SpringTemplateEngine();
	templateEngine.setTemplateResolver(templateResolver());
	return templateEngine;
}

//creamos un Bean para la resolucion de vistas
@Bean
public ViewResolver viewResolver() {
	//creamos un objeto de tipo ThymeLeafResolver
	var viewResolver= new ThymeleafViewResolver();
	viewResolver.setTemplateEngine(templateEngine());
	viewResolver.setCharacterEncoding("UTF-8");
	return viewResolver;
}

//sobrecargamos el metodo addResourceHandlers para definir la carpeta de recurso
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//WebMvcConfigurer.super.addResourceHandlers(registry);
	registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
}

}