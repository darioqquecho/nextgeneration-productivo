package net.royal.spring.alertas.boot;

import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
//import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.royal.spring.framework.web.filter.GenericoSeguridadFiltroPrivado;
import net.royal.spring.framework.web.filter.GenericoSeguridadFiltroPublico;

//@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@SpringBootApplication
@ComponentScan({ "net.royal.spring" })
//@EnableSwagger2
@EnableScheduling
public class SpringAlertasAplicacionBoot extends SpringBootServletInitializer {
	private static Logger logger = LogManager.getLogger(SpringAlertasAplicacionBoot.class);

	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(SpringAlertasAplicacionBoot.class, args);
	}

	@Bean
	public FilterRegistrationBean<GenericoSeguridadFiltroPrivado> setSecurityFilter() {
		FilterRegistrationBean<GenericoSeguridadFiltroPrivado> registration = new FilterRegistrationBean<GenericoSeguridadFiltroPrivado>();
		registration.setFilter(new GenericoSeguridadFiltroPrivado(env));
		registration.addUrlPatterns("/spring/alertas/*");
		registration.setName("SecurityFilter");
		registration.setOrder(1);
		return registration;
	}

	@Bean
	public FilterRegistrationBean<GenericoSeguridadFiltroPublico> setSecurityPublicFilter() {
		FilterRegistrationBean<GenericoSeguridadFiltroPublico> registration = new FilterRegistrationBean<GenericoSeguridadFiltroPublico>();
		registration.setFilter(new GenericoSeguridadFiltroPublico(env));
		registration.addUrlPatterns("/spring/publico/*");
		registration.setName("SecurityPublicFilter");
		registration.setOrder(1);
		return registration;
	}

	@Bean
	@Primary
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setTimeZone(TimeZone.getTimeZone("America/Lima"));
		return mapper;
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

	/*
	 * @Bean public Docket productApi() { return new
	 * Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO).select()
	 * .apis(RequestHandlerSelectors.basePackage("net.royal.spring.sistema.rest_")).
	 * build(); }
	 */

	/*
	 * @Bean public String securityServer() { return securityServer; }
	 */

}
