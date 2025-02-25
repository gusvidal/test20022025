package cl.gvidal.techtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class SpringSecurityJwtApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(SpringSecurityJwtApplication.class, args);

	}

}
