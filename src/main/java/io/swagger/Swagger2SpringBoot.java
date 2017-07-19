package io.swagger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.filter.HTTPBearerAuthorizeFilter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = "io.swagger")
@ServletComponentScan
public class Swagger2SpringBoot implements CommandLineRunner {

	@Override
	public void run(String... arg0) throws Exception {
		if (arg0.length > 0 && arg0[0].equals("exitcode")) {
			throw new ExitException();
		}
	}

	public static void main(String[] args) throws Exception {
		new SpringApplication(Swagger2SpringBoot.class).run(args);
	}

	class ExitException extends RuntimeException implements ExitCodeGenerator {
		private static final long serialVersionUID = 1L;

		@Override
		public int getExitCode() {
			return 10;
		}

	}
	
	/*@Bean  
    public FilterRegistrationBean jwtFilterRegistrationBean(){  
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();  
        HTTPBearerAuthorizeFilter httpBearerFilter = new HTTPBearerAuthorizeFilter();  
        registrationBean.setFilter(httpBearerFilter);  
        List<String> urlPatterns = new ArrayList<String>();  
        urlPatterns.add("/user/*");  
        registrationBean.setUrlPatterns(urlPatterns);  
        return registrationBean;  
    }  */
}
