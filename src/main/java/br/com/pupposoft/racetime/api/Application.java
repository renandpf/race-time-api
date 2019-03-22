package br.com.pupposoft.racetime.api;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@EnableDiscoveryClient
//@EnableAutoConfiguration
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	private static Class<Application> applicationClass = Application.class;
	
    public static void main(String[] args) throws Exception {
    	Locale.setDefault(new Locale( "pt", "BR" ));
        SpringApplication.run(Application.class, args);
    }
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }
}
